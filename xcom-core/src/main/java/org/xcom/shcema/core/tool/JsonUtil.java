package org.xcom.shcema.core.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;
import java.util.TimeZone;

/**
 * Json对象转换工具类
 *
 * @author xcom
 * @date 2024/8/9
 */

public class JsonUtil {


    private static final ObjectMapper MAPPER = new JsonMapper();

    static {

        SimpleModule module = new SimpleModule();

        //禁止将Date序列化为时间戳
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        MAPPER.registerModule(module);
        //设置在反序列化时忽略在JSON字符串中存在，而在Java中不存在的属性
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 序列化对象：将对象转换成json字符串
     *
     * @param target pojo对象
     * @return 返回json字符串
     */
    public static String toJsonString(Object target) {
        try {
            return MAPPER.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("序列化失败: ", e);
        }
    }

    /**
     * 反序列化对象：将字符串转换成对象
     *
     * @param jsonStr json字符串
     * @param clazz     pojo类型
     */
    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        try {
            return MAPPER.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("反序列化失败, 需要被反序列化的字符串: " + jsonStr, e);
        }
    }

    /**
     * 反序列化对象：将字符串转换成对象
     *
     * @param jsonStr json字符串
     * @param valueTypeRef     pojo类型
     */
    public static <T> T parseObject(String jsonStr, TypeReference<T> valueTypeRef) {
        try {
            return MAPPER.readValue(jsonStr, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("反序列化失败, 需要被反序列化的字符串: " + jsonStr, e);
        }
    }

    /**
     * 反序列化列表：将字符串转换成List
     *
     * @param jsonStr json字符串
     * @param clazz     pojo类型
     */
    public static <T> List<T> parseList(String jsonStr, Class<T> clazz) {
        try {
            CollectionType javaType = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            return MAPPER.readValue(jsonStr, javaType);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("反序列化失败, 需要被反序列化的字符串: " + jsonStr, e);
        }
    }

    public JsonNode readTree(String jsonStr){
        try {
            return MAPPER.readTree(jsonStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
