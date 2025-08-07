package org.xcom.shcema.core.annotation;

import org.xcom.shcema.core.enums.AuthTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限控制
 *
 * @author xcom
 * @date 2024/8/9
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authority {
    /**
     * 权限码、URL等权限值
     */
    String[] value() default {};

    /**
     * 鉴权方式
     */
    AuthTypeEnum type() default AuthTypeEnum.PERMISSION;

}
