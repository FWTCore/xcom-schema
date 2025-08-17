package org.xcom.shcema.infra.starter.config.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.xcom.infra.XcomException;
import org.xcom.infra.annotation.Authority;
import org.xcom.infra.constant.SystemConstant;
import org.xcom.infra.context.AccessContextHolder;
import org.xcom.infra.context.AccessUser;
import org.xcom.infra.context.XcomApplicationContext;
import org.xcom.infra.core.model.LoginAccountModel;
import org.xcom.infra.core.shared.AccountService;
import org.xcom.infra.enums.AuthTypeEnum;
import org.xcom.infra.enums.SystemCodeEnum;
import org.xcom.infra.model.InfraProperties;
import org.xcom.shcema.core.annotation.Authority;
import org.xcom.shcema.core.context.AccessContextHolder;
import org.xcom.shcema.core.context.AccessUser;
import org.xcom.shcema.core.enums.AuthTypeEnum;
import org.xcom.shcema.core.enums.SystemCodeEnum;
import org.xcom.shcema.core.exception.XcomException;
import org.xcom.shcema.core.model.InfraProperties;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 权限检测
 * 拦截器
 *
 * @author xcom
 * @date 2024/8/11
 */

@Slf4j
@Component
public class UserAuthInterceptor implements HandlerInterceptor {

    private static final PathMatcher MATCHER = new AntPathMatcher();


    @Resource
    private InfraProperties infraProperties;


    @Resource
    private AccountService accountService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 检测是否启用
        if (!BooleanUtil.isTrue(infraProperties.getAuthEnable())) {
            return true;
        }
        // 是否在鉴权白名单中
        String requestUri = request.getRequestURI();
        log.debug("Auth Interceptor request URI = " + requestUri);
        boolean noNeedAuth = hasNeedAuth(requestUri);
        if (noNeedAuth) {
            return true;
        }
        log.debug("开始进入权限认证流程...");
        boolean authPass;
        // 获取登录用户，这里由业务方配置保证，登录白名单必须同时在鉴权白名单中配置，防止未登录却进行鉴权
        AccessUser accessUser = AccessContextHolder.getAccessUser().orElseThrow(() -> {
            log.error("未登录请求进入到鉴权处理，请检查配置，保证登录白名单必须同时在鉴权白名单中。requestUri={}", requestUri);
            return XcomException.create(SystemCodeEnum.REQUEST_UNAUTHORIZED);
        });

        authPass = handlePermissionCodeAuthControl(accessUser, (HandlerMethod) handler);

        if (!authPass) {
            log.error("检测到越权访问,UserId={},RequestURI={}", accessUser.getId(), requestUri);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            throw XcomException.create(SystemCodeEnum.REQUEST_FORBIDDEN);
        }
        log.debug("权限认证通过。UserId={},RequestURI={}", accessUser.getId(), requestUri);
        return true;
    }

    private boolean hasNeedAuth(String requestURI) {
        boolean noNeedAuth = false;
        List<String> noNeedAuthUrlPattern = infraProperties.getNoAuthUrlList();
        for (String pattern : noNeedAuthUrlPattern) {
            boolean match = MATCHER.match(pattern, requestURI);
            if (match) {
                log.debug("匹配到鉴权白名单url-pattern配置,pattern={},requestURI={}", pattern, requestURI);
                return true;
            }
        }
        noNeedAuthUrlPattern = infraProperties.getNoLoginUrlList();
        for (String pattern : noNeedAuthUrlPattern) {
            boolean match = MATCHER.match(pattern, requestURI);
            if (match) {
                log.debug("匹配到鉴权白名单url-pattern配置,pattern={},requestURI={}", pattern, requestURI);
                return true;
            }
        }
        noNeedAuthUrlPattern = infraProperties.getAnonymousUrlList();
        for (String pattern : noNeedAuthUrlPattern) {
            boolean match = MATCHER.match(pattern, requestURI);
            if (match) {
                log.debug("匹配到鉴权白名单url-pattern配置,pattern={},requestURI={}", pattern, requestURI);
                return true;
            }
        }
        return noNeedAuth;
    }


    private boolean handlePermissionCodeAuthControl(AccessUser accessUser,
                                                    HandlerMethod handlerMethod) {
        @Nullable Authority authority = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), Authority.class);
        if (authority == null) {
            authority = AnnotationUtils.getAnnotation(handlerMethod.getBeanType(), Authority.class);
        }
        // 找不到注解都是仅登录
        AuthTypeEnum type = AuthTypeEnum.JUST_LOGIN;
        if (authority != null) {
            type = authority.type();
        }
        boolean authPass = false;
        switch (type) {
            case PERMISSION:
                List<String> permissionCodeList = Arrays.asList(authority.value());
                if (permissionCodeList.size() == 0) {
                    throw new IllegalArgumentException("无配置权限码");
                }
                log.debug("使用权限码鉴权，permissionCode={}", permissionCodeList);
                authPass = checkSystemUserPermission(accessUser, permissionCodeList);
                break;
            case JUST_LOGIN:
                // 仅登录，无需鉴权
                authPass = true;
                break;
            default:
        }
        return authPass;
    }

    /**
     * 验证用户权限
     *
     * @param accessUser
     * @param permissionCodeList
     */
    private boolean checkSystemUserPermission(AccessUser accessUser,
                                              List<String> permissionCodeList) {

        LoginAccountModel.LoginUserPermissionsRespDO loginUserPermissions = accountService.getLoginUserPermissionsByUserId(accessUser.getId());
        if (ObjectUtil.isNull(loginUserPermissions)) {
            return false;
        }

        // 超管都有权限
        if (BooleanUtil.isTrue(loginUserPermissions.getSuperAdminFlag())) {
            return true;
        }
        // 获取缓存用户权限
        if (CollectionUtil.isEmpty(loginUserPermissions.getLoginUserAuthList())) {
            return false;
        }

        // stg环境，除去待发布菜单
        List<LoginAccountModel.LoginUserAuthRespBO> validMenuList = loginUserPermissions.getLoginUserAuthList();
        if (Objects.equals(XcomApplicationContext.getFirstActiveProfiles(), SystemConstant.ENV_STG)) {
            validMenuList = loginUserPermissions.getLoginUserAuthList().stream().filter(LoginAccountModel.LoginUserAuthRespBO::getReleaseFlag).collect(Collectors.toList());
        }
        if (CollectionUtil.isEmpty(validMenuList)) {
            return false;
        }
        if (validMenuList.stream().anyMatch(menu -> permissionCodeList.contains(menu.getAuthKey()))) {
            return true;
        }
        return false;
    }

}
