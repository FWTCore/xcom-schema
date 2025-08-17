package org.xcom.shcema.infra.starter.config.interceptor;

import cn.hutool.core.util.BooleanUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.xcom.shcema.core.constant.RequestConstant;
import org.xcom.shcema.core.constant.SystemConstant;
import org.xcom.shcema.core.context.AccessContextHolder;
import org.xcom.shcema.core.context.AccessUser;
import org.xcom.shcema.core.enums.SystemCodeEnum;
import org.xcom.shcema.core.exception.XcomException;
import org.xcom.shcema.core.model.AuthUserJwtDTO;
import org.xcom.shcema.core.model.InfraProperties;
import org.xcom.shcema.core.tool.JsonUtil;
import org.xcom.shcema.core.tool.JwtUtil;
import org.xcom.shcema.infra.core.model.LoginAccountModel;
import org.xcom.shcema.infra.core.shared.AccountService;
import org.xcom.shcema.infra.starter.utils.TokenUtil;

import java.util.List;

/**
 * 用户登录拦截器
 *
 * @author xcom
 * @date 2024/8/11
 */

@Slf4j
@Component
@AllArgsConstructor
public class UserLoginInterceptor implements HandlerInterceptor {
    private static final PathMatcher MATCHER = new AntPathMatcher();

    @Resource
    private InfraProperties infraProperties;


    @Resource
    private AccountService accountService;


    private static final String UNKNOWN_IP = "unknown";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 检测是否启用
        if (!BooleanUtil.isTrue(infraProperties.getLoginEnable())) {
            return true;
        }

        String requestURI = request.getRequestURI();
        log.debug("Login Interceptor request URI = " + requestURI);
        boolean noNeedLogin = hasNeedLogin(requestURI);
        if (noNeedLogin) {
            return true;
        }

        // 如果没有在登录白名单中，进入登录验证流程
        log.debug("开始进入认证流程...");
        boolean alreadyLogin = false;
        // 获取token
        String token = TokenUtil.extractToken(request);

        if (StringUtils.isNotBlank(token)) {
            String authUserJwt = JwtUtil.parseToken(token);
            try {
                AuthUserJwtDTO authUserJwtDto = JsonUtil.parseObject(authUserJwt, AuthUserJwtDTO.class);
                if (ObjectUtils.isEmpty(authUserJwtDto)) {
                    log.warn("通过token解析用户信息异常,token={}", token);
                    throw XcomException.create(SystemCodeEnum.REQUEST_UNAUTHORIZED);
                }
                AccessUser accessUser = new AccessUser();
                accessUser.setToken(token);
                AccessContextHolder.bind(accessUser);

                LoginAccountModel.LoginUserRespBO loginUserDetail = accountService.getLoginUserByUserId(authUserJwtDto.getId());
                if (ObjectUtils.isEmpty(loginUserDetail)) {
                    log.warn("通过token获取用户信息错误,token={},AuthUserJwtDTO={}", token, JsonUtil.toJsonString(authUserJwtDto));
                    throw XcomException.create(SystemCodeEnum.REQUEST_UNAUTHORIZED);
                }
                alreadyLogin = true;

                // 注入上下文
                injectAuthUser(loginUserDetail, token);

            } catch (Exception ex) {
                throw XcomException.create(SystemCodeEnum.REQUEST_UNAUTHORIZED, ex);
            }
        }
        // 未登录，抛出404
        if (!alreadyLogin) {
            log.warn("检测到未登录非法访问请求,requestUri={}", requestURI);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw XcomException.create(SystemCodeEnum.REQUEST_UNAUTHORIZED);
        }
        log.debug("认证通过。AccessUser={}", AccessContextHolder.getAccessUser().orElse(null));

        return true;
    }

    private boolean hasNeedLogin(String requestUri) {
        boolean noNeedLogin = false;
        List<String> noNeedLoginUrlPattern = infraProperties.getNoLoginUrlList();
        for (String pattern : noNeedLoginUrlPattern) {
            boolean match = MATCHER.match(pattern, requestUri);
            if (match) {
                log.debug("匹配到登录白名单url-pattern配置,pattern={},requestURI={}", pattern, requestUri);
                // 如果是登录白名单，直接返回true,走下一个拦截器
                return true;
            }
        }
        noNeedLoginUrlPattern = infraProperties.getAnonymousUrlList();
        for (String pattern : noNeedLoginUrlPattern) {
            boolean match = MATCHER.match(pattern, requestUri);
            if (match) {
                log.debug("匹配到登录白名单url-pattern配置,pattern={},requestURI={}", pattern, requestUri);
                // 如果是登录白名单，直接返回true,走下一个拦截器
                return true;
            }
        }
        return noNeedLogin;
    }

    private void injectAuthUser(LoginAccountModel.LoginUserRespBO loginUserRespBO, String token) {
        AccessUser accessUser = new AccessUser();
        accessUser.setId(loginUserRespBO.getId());
        accessUser.setLoginName(loginUserRespBO.getLoginName());
        accessUser.setDisplayName(loginUserRespBO.getDisplayName());
        accessUser.setToken(token);
        AccessContextHolder.bind(accessUser);
        // 设置用户id
        MDC.put(SystemConstant.SYSTEM_LOG_USER_ID, String.valueOf(accessUser.getId()));
    }

    /***
     * 获取客户端IP地址;这里通过了Nginx获取;X-Real-IP
     */
    public static String getClientIP(HttpServletRequest request) {
        String fromSource = RequestConstant.HEADER_REAL_IP;
        String ip = request.getHeader(fromSource);
        if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            fromSource = RequestConstant.HEADER_FORWARDED_IP;
            ip = request.getHeader(fromSource);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            fromSource = RequestConstant.HEADER_PROXY_CLIENT_IP;
            ip = request.getHeader(fromSource);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            fromSource = RequestConstant.HEADER_WL_PROXY_CLIENT_IP;
            ip = request.getHeader(fromSource);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            fromSource = RequestConstant.REQUEST_REMOTE_ADDR_IP;
        }
        log.info("Ip获取：" + fromSource + ".IP：" + ip);
        return ip;
    }
}
