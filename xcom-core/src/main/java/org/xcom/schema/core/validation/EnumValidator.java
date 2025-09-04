package org.xcom.schema.core.validation;

import lombok.SneakyThrows;
import org.xcom.schema.core.annotation.EnumValid;
import org.xcom.schema.core.enums.IEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 枚举字段验证
 *
 * @author xcom
 * @date 2024/11/11
 */

public class EnumValidator implements ConstraintValidator<EnumValid, Object> {

    private Class  clazz;

    private String validField;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        clazz = constraintAnnotation.enumClass();
        validField = constraintAnnotation.field();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null || "".equals(object)) {
            return true;
        }

        if (!clazz.isEnum()) {
            return false;
        }

        Class<IEnum> enumClass = (Class<IEnum>) clazz;
        //获取所有枚举实例
        IEnum[] enumConstants = enumClass.getEnumConstants();

        // 需要比对的字段
        Field field = enumClass.getDeclaredField(validField);
        field.setAccessible(true);

        for (IEnum constant : enumConstants) {
            // 取值final修饰
            Object validValue = field.get(constant);
            if (validValue == null) {
                Method method = enumClass.getMethod(validField);
                validValue = method.invoke(constant);
            }

            if (validValue instanceof Number) {
                validValue = ((Number) validValue).intValue();
                object = ((Number) object).intValue();
            }
            if (Objects.equals(validValue, object)) {
                return true;
            }
        }
        return false;
    }
}
