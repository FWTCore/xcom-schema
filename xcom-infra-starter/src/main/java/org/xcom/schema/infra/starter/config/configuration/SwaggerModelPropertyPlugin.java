package org.xcom.schema.infra.starter.config.configuration;

import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.xcom.infra.annotation.ApiModelPropertyPro;
import org.xcom.infra.enums.IEnum;
import springfox.documentation.builders.PropertySpecificationBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SwaggerModelPropertyPlugin
 *
 * @author xcom
 * @date 2024/8/11
 */

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SwaggerModelPropertyPlugin implements ModelPropertyBuilderPlugin {

    private static final String delimiter = "、";


    /**
     * 应用方法，用于为模型属性应用Swagger注解
     *
     * @param context 模型属性上下文
     */
    @Override
    public void apply(ModelPropertyContext context) {

        context.getBeanPropertyDefinition()
                .ifPresent(beanPropertyDefinition -> addDesc(context, beanPropertyDefinition.getField().getAnnotated()));

    }

    /**
     * 为枚举类型或字典类型的属性添加描述
     *
     * @param context 模型属性上下文
     * @param field   属性字段
     */
    private void addDesc(ModelPropertyContext context, Field field) {
        // 获取 apiModelProperty 注解
        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
        // 获取 ApiModelPropertyPro 注解
        ApiModelPropertyPro apiModelPropertyPro = field.getAnnotation(ApiModelPropertyPro.class);
        if (apiModelPropertyPro == null) {
            return;
        }

        List<String> displayValues = new ArrayList<>();

        // 获取枚举值的显示字符串列表
        Class<? extends IEnum>[] classes = apiModelPropertyPro.enumClass();
        if (!ObjectUtils.isEmpty(classes)) {
            displayValues.addAll(getDisplayValues(classes));
        }

        // 设置描述
        if (apiModelProperty == null) {
            // 获取属性的规范构建器
            PropertySpecificationBuilder builder = context.getSpecificationBuilder();
            // 构建描述字符串
            String value = apiModelPropertyPro.value();
            String description = String.format("%s,可用值:（%s）", value, String.join(delimiter, displayValues));
            builder.description(description);
            builder.example(apiModelPropertyPro.example());
        } else {
            try {
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(apiModelProperty);
                Field memberValues = invocationHandler.getClass().getDeclaredField("memberValues");
                memberValues.setAccessible(true);
                Map propertyMap = (Map) memberValues.get(invocationHandler);
                propertyMap.put("allowableValues", String.format("（%s）", String.join(delimiter, displayValues)));
            } catch (Exception exception) {
                log.warn("ApiModelProperty 扩展 ApiModelPropertyPro 异常", exception);
            }
        }


    }

    /**
     * 根据枚举类型获取其显示值列表
     *
     * @param classes 枚举类
     * @return 枚举值的显示字符串列表
     */
    private List<String> getDisplayValues(Class<? extends IEnum>[] classes) {

        List<String> displayValues = new ArrayList<>();

        for (Class<? extends IEnum> enumClass : classes) {
            //获取此枚举类的元素，如果此 Class 对象不是枚举类型，则返回 null
            IEnum[] enumConstants = enumClass.getEnumConstants();
            if (ObjectUtils.isEmpty(enumConstants)) {
                continue;
            }
            for (IEnum enumItem : enumConstants) {
                displayValues.add(String.format("%s-%s-%s", enumItem.getCode(), enumItem.name(), enumItem.getDesc()));
            }
        }
        return displayValues;

    }

    /**
     * 判断插件是否支持文档类型
     *
     * @param documentationType 文档类型
     * @return 如果支持，则返回true；否则返回false
     */
    @Override
    public boolean supports(@NotNull DocumentationType documentationType) {
        return SwaggerPluginSupport.pluginDoesApply(documentationType);
    }

}
