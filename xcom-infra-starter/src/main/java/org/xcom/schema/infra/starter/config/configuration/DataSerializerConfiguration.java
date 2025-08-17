package org.xcom.schema.infra.starter.config.configuration;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xcom.schema.core.enums.IEnum;
import org.xcom.schema.infra.starter.utils.ConverterUtil;

import java.io.IOException;
import java.util.List;

/**
 * 枚举字段 转换处理
 *
 * @author xcom
 * @date 2024/8/8
 */

@Configuration
public class DataSerializerConfiguration implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().filter(MappingJackson2HttpMessageConverter.class::isInstance).forEach(converter -> {
            MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
            EnumSerializer enumSerializer = new EnumSerializer();
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(enumSerializer);
            jsonConverter.getObjectMapper().registerModule(simpleModule);

        });
    }

    /**
     * 枚举字 段转换处理
     *
     * @author xcom
     * @date 2024/1/27
     */

    public static class EnumSerializer extends JsonSerializer<IEnum> {

        @Override
        public void serialize(IEnum value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

            if (ObjectUtil.isNull(value)) {
                jsonGenerator.writeNull();
                return;
            }

            // 获取当前枚举字段名称，可能为空，需要递归获取
            String currentName = ConverterUtil.getCurrentName(jsonGenerator.getOutputContext());
            jsonGenerator.writeObject(value.getCode());
            jsonGenerator.writeFieldName(currentName + "Name");
            jsonGenerator.writeObject(value.name());
            jsonGenerator.writeFieldName(currentName + "Desc");
            jsonGenerator.writeString(value.getDesc());
        }

        @Override
        public Class<IEnum> handledType() {
            return IEnum.class;
        }

    }
}