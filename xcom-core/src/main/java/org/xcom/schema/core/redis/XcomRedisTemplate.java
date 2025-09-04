package org.xcom.schema.core.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.xcom.schema.core.enums.SystemCodeEnum;
import org.xcom.schema.core.context.XcomApplicationContext;
import org.xcom.schema.core.exception.XcomException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * RedisTemplate 封装类
 *
 * @author xcom
 * @date 2024/8/9
 */

public class XcomRedisTemplate {

    /**
     * RedisTemplate 对象
     */
    private static RedisTemplate<String, Object> INSTANCE;

    /**
     * 初始化Redis对象
     *
     * @param redisTemplate
     */
    public static void initInstance(RedisTemplate<String, Object> redisTemplate) {
        INSTANCE = redisTemplate;
    }

    /**
     * 获取Redis对象
     *
     * @return
     */
    public static RedisTemplate<String, Object> getInstance() {
        if (ObjectUtils.isEmpty(INSTANCE)) {
            throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION, "XcomRedisTemplate 对象未初始化");
        }
        return INSTANCE;
    }

    /**
     * 生成redis key
     * 格式：应用:环境:业务key
     *
     * @param key
     * @return
     */
    public static String generateRedisKey(String key) {
        return String.format("%s:%s:%s", XcomApplicationContext.getApplicationId(),
            XcomApplicationContext.getFirstActiveProfiles(), key);
    }

    /**
     * 生成redis key
     * 格式：应用:环境:业务key
     *
     * @param key
     * @param funKey
     * @return
     */
    public static String generateRedisKey(String key, Function<String, String> funKey) {
        if (ObjectUtils.isEmpty(funKey)) {
            return generateRedisKey(key);
        }
        return generateRedisKey(funKey.apply(key));
    }

    /**
     * 设置redis缓存
     *
     * @param redisKey
     * @param content
     * @param timeout
     * @param unit
     */
    public static void setRedisCache(String redisKey, Object content, long timeout, TimeUnit unit) {
        if (ObjectUtils.isEmpty(content)) {
            return;
        }
        XcomRedisTemplate.getInstance().opsForValue().set(redisKey, content, timeout, unit);
    }

    /**
     * 设置redis缓存
     *
     * @param redisKey
     * @param content
     */
    public static void setRedisCache(String redisKey, Object content) {
        if (ObjectUtils.isEmpty(content)) {
            return;
        }
        XcomRedisTemplate.getInstance().opsForValue().set(redisKey, content);
    }

    /**
     * 设置redis缓存 map
     *
     * @param redisKey
     * @param map
     */
    public static void setRedisCacheForHash(String redisKey, Map<Object, Object> map) {
        if (map.isEmpty()) {
            return;
        }
        XcomRedisTemplate.getInstance().opsForHash().putAll(redisKey, map);
    }

    /**
     * 获取redis缓存
     *
     * @param redisKey
     * @return
     */
    public static Object getRedisCache(String redisKey) {
        return XcomRedisTemplate.getInstance().opsForValue().get(redisKey);
    }

    /**
     * 获取redis缓存 map
     *
     * @param redisKey
     * @return
     */
    public static Map<Object, Object> getRedisCacheForHash(String redisKey) {
        return XcomRedisTemplate.getInstance().opsForHash().entries(redisKey);
    }

    /**
     * 获取redis缓存 map
     *
     * @param redisKey
     * @param hashKey
     * @return
     */
    public static Object getRedisCacheForHash(String redisKey, String hashKey) {
        return XcomRedisTemplate.getInstance().opsForHash().get(redisKey, hashKey);
    }

    /**
     * 移除redis缓存
     *
     * @param redisKey
     * @return
     */
    public static Boolean removeRedisCache(String redisKey) {
        return XcomRedisTemplate.getInstance().delete(redisKey);
    }

    /**
     * 批量移除redis缓存
     *
     * @param keys
     */
    public static void removeRedisCache(List<String> keys) {
        XcomRedisTemplate.getInstance().delete(keys);
    }

}
