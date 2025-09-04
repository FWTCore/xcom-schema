package org.xcom.schema.infra.starter.common.utils;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.xcom.schema.core.annotation.NoNeedLogin;

/**
 * 不需要登录验证
 *
 * @author xcom
 * @date 2025/9/4
 */

public class NoNeedLoginUtil {

    /**
     * 检查是否不需要登录授权验证
     * @param handler
     * @return
     */
    public static Boolean checkNoNeedLoginUtil(Object handler) {
        // NoNeedLogin 注解直接放行
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NoNeedLogin authority = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), NoNeedLogin.class);
            if (ObjectUtil.isNull(authority)) {
                authority = AnnotationUtils.getAnnotation(handlerMethod.getBeanType(), NoNeedLogin.class);
            }
            if (ObjectUtil.isNull(authority)) {
                return true;
            }
        }
        return false;
    }

}
