package org.xcom.schema.core.annotation;

import org.xcom.schema.core.enums.IEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对 { ApiModelProperty}进行增强，支持枚举类展示
 *
 * @author xcom
 * @date 2024/8/11
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiModelPropertyPro {

    /**
     * 优先取 { ApiModelProperty#value()}
     */
    String value() default "";

    /**
     * 优先取 { ApiModelProperty#example()}
     *
     * @return
     */
    String example() default "";

    /**
     * 要翻译的枚举类，枚举类接口需要实现 {@link IEnum} 接口
     */
    Class<? extends IEnum>[] enumClass() default {};

}
