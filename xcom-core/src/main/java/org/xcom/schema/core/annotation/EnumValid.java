package org.xcom.schema.core.annotation;

import org.xcom.schema.core.enums.IEnum;
import org.xcom.schema.core.validation.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 枚举校验
 *
 * @author xcom
 * @date 2024/11/11
 */

@Constraint(validatedBy = {EnumValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface EnumValid {
    /**
     * 不合法时 抛出异常信息
     */
    String message() default "值不合法";

    /**
     * 校验的枚举类
     *
     * @return
     */
    Class enumClass() default IEnum.class;

    /**
     * 对应枚举类中需要比对的字段
     *
     * @return
     */
    String field() default "code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
