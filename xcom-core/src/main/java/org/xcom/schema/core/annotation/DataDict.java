package org.xcom.schema.core.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据字典 转换器注解
 *
 * @author xcom
 * @date 2024/8/8
 */

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
//@JsonSerialize(using = DataDictSerializer.class)
public @interface DataDict {
    /**
     * 字典编码
     */
    String code();
}
