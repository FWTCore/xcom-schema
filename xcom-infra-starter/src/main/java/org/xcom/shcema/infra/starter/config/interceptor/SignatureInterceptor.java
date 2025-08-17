package org.xcom.shcema.infra.starter.config.interceptor;

import cn.hutool.core.util.BooleanUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.DigestUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.xcom.shcema.core.constant.RequestConstant;
import org.xcom.shcema.core.enums.SystemCodeEnum;
import org.xcom.shcema.core.exception.XcomException;
import org.xcom.shcema.core.model.InfraProperties;

import java.util.List;

/**
 * 签名拦截器
 *
 * @author xcom
 * @date 2024/8/9
 */

@Slf4j
@Component
public class SignatureInterceptor implements HandlerInterceptor {


    private static final PathMatcher MATCHER = new AntPathMatcher();

    @Resource
    private InfraProperties infraProperties;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检测是否启用
        if (!BooleanUtil.isTrue(infraProperties.getSignatureEnable())) {
            return true;
        }
        // 是否在鉴权白名单中
        String requestURI = request.getRequestURI();
        log.debug("Sign Interceptor request URI = " + requestURI);
        boolean noNeedSignature = hasNeedSignature(requestURI);
        if (noNeedSignature) {
            return true;
        }

        /**
         * 用于测试 签名验证
         */
        String test = request.getHeader(RequestConstant.HEADER_TEST);
        if (StringUtils.isNotBlank(test)) {
            if (test.equals(RequestConstant.HEADER_TEST_VALUE)) {
                return true;
            }
        }
        log.info("开始进入签名验证流程...");
        //对参数进行签名验证
        String nonceStr = request.getHeader(RequestConstant.HEADER_NONCE_STR);
        if (StringUtils.isBlank(nonceStr)) {
            throw XcomException.create(SystemCodeEnum.BAD_REQUEST, "系统参数nonceStr不能为空！");
        }
        String timestamp = request.getHeader(RequestConstant.HEADER_TIMESTAMP);
        if (StringUtils.isBlank(timestamp)) {
            throw XcomException.create(SystemCodeEnum.BAD_REQUEST, "系统参数timestamp不能为空！");
        }
        String signature = request.getHeader(RequestConstant.HEADER_SIGNATURE);
        if (StringUtils.isBlank(signature)) {
            throw XcomException.create(SystemCodeEnum.BAD_REQUEST, "系统参数signature不能为空！");
        }
        //客户端时间
        long clientTimestamp = 0L;
        try {
            clientTimestamp = Long.parseLong(timestamp);
        } catch (Exception exception) {
            throw XcomException.create(SystemCodeEnum.BAD_REQUEST, "时间戳不合法！");
        }

        int length1000 = 1000;
        //1.校验签名时间（兼容X_TIMESTAMP的新老格式）
        if ((System.currentTimeMillis() - clientTimestamp) > (infraProperties.getSignatureTimeliness() * length1000)) {
            log.error("签名验证失败:X-TIMESTAMP已过期，注意系统时间和服务器时间是否有误差！");
            throw new IllegalArgumentException("签名验证失败:X-TIMESTAMP已过期");
        }
        //2.校验签名
        String signContent = String.format("%s%s%s%s", infraProperties.getApiSecret(), nonceStr, timestamp, infraProperties.getApiSecret());
        String sign = DigestUtils.md5DigestAsHex(signContent.getBytes());
        if (!signature.equalsIgnoreCase(sign)) {
            log.error("request URI = " + request.getRequestURI());
            log.error("Sign 签名校验失败！Header Sign : {}", String.format("%s:%s-%s", signature, nonceStr, timestamp));
            //校验失败返回前端
            throw XcomException.create(SystemCodeEnum.BAD_REQUEST, "签名校验失败！");
        }
        return true;
    }

    private boolean hasNeedSignature(String requestUri) {
        boolean noNeedSignature = false;
        List<String> noNeedSignatureUrlPattern = infraProperties.getNoSignUrlList();
        for (String pattern : noNeedSignatureUrlPattern) {
            boolean match = MATCHER.match(pattern, requestUri);
            if (match) {
                log.debug("匹配到签名白名单url-pattern配置,pattern={},requestURI={}", pattern, requestUri);
                return true;
            }
        }
        noNeedSignatureUrlPattern = infraProperties.getAnonymousUrlList();
        for (String pattern : noNeedSignatureUrlPattern) {
            boolean match = MATCHER.match(pattern, requestUri);
            if (match) {
                log.debug("匹配到签名白名单url-pattern配置,pattern={},requestURI={}", pattern, requestUri);
                // 如果是登录白名单，直接返回true,走下一个拦截器
                return true;
            }
        }
        return noNeedSignature;
    }


}
