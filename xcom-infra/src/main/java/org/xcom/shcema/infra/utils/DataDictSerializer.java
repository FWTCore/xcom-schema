package org.xcom.shcema.infra.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.xcom.infra.annotation.DataDict;
import org.xcom.infra.constant.RedisConstant;
import org.xcom.infra.core.model.SystemDictModel;
import org.xcom.infra.core.shared.DictionaryService;
import org.xcom.infra.redis.ActionRedisUtil;
import org.xcom.infra.redis.KeyRedisUtil;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 数据字典转换处理
 *
 * @author xcom
 * @date 2024/8/9
 */

@Slf4j
public class DataDictSerializer extends StdSerializer<Object> implements ContextualSerializer {

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 字典注解
     */
    private DataDict dataDict;

    public DataDictSerializer() {
        super(Object.class);
    }

    public DataDictSerializer(DataDict dict) {
        super(Object.class);
        this.dataDict = dict;
    }

    private String code;

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (ObjectUtil.isNull(value)) {
            jsonGenerator.writeNull();
            return;
        }
        if (ObjectUtil.isNotNull(dataDict)) {
            code = dataDict.code();
        }

        // 通过数据字典类型和value获取name
        String name = getDictionaryText(code, value.toString(), Boolean.TRUE);

        // 获取当前枚举字段名称，可能为空，需要递归获取
        String currentName = ConverterUtil.getCurrentName(jsonGenerator.getOutputContext());
        jsonGenerator.writeObject(value);
        jsonGenerator.writeFieldName(currentName + "Text");
        jsonGenerator.writeObject(name);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty beanProperty) throws JsonMappingException {
        if (ObjectUtil.isNull(beanProperty)) {
            return prov.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        DataDict dict = beanProperty.getAnnotation(DataDict.class);
        if (ObjectUtil.isNotNull(dict)) {
            code = dict.code();
            return this;
        }

        return prov.findNullValueSerializer(null);
    }


    @Override
    public Class<Object> handledType() {
        return Object.class;
    }


    private String getDictionaryText(String dictCode, String itemCode, Boolean hasTry) {
        // redis 缓存
        String redisKey = KeyRedisUtil.getGlobalRedisKey(RedisConstant.REDIS_INFRA_DICT_PREFIX);
        SystemDictModel.SystemDictRespBO dictData = (SystemDictModel.SystemDictRespBO) ActionRedisUtil.getRedisMapData(redisKey, dictCode);
        if (ObjectUtils.isEmpty(dictData)) {
            // 重试一次
            if (BooleanUtil.isTrue(hasTry)) {
                // 强制加载
                dictionaryService.loadAllDictionary(Boolean.TRUE);
                return getDictionaryText(dictCode, itemCode, Boolean.FALSE);
            } else {
                log.warn("数据字典【dictCode：" + dictCode + "，itemCode:" + itemCode + "】，缓存不存在");
                return "";
            }
        }
        if (CollectionUtil.isEmpty(dictData.getSystemDictItemList())) {
            log.warn("数据字典【dictCode：" + dictCode + "，itemCode:" + itemCode + "】，字典项列不存在");
            return "";
        }
        if (dictData.getSystemDictItemList().stream().noneMatch(e -> e.getItemCode().equals(itemCode))) {
            log.warn("数据字典【dictCode：" + dictCode + "，itemCode:" + itemCode + "】，字典项列Code不存在");
            return "";
        }
        SystemDictModel.SystemDictItemRespBO systemDictItemRespBO = dictData.getSystemDictItemList().stream().filter(e -> e.getItemCode().equals(itemCode)).findFirst().orElse(null);
        if (ObjectUtils.isEmpty(systemDictItemRespBO)) {
            return null;
        }
        return systemDictItemRespBO.getItemText();
    }

}