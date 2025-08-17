package org.xcom.schema.core.redis;

import io.netty.util.internal.StringUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.xcom.schema.core.enums.SystemCodeEnum;
import org.xcom.schema.core.context.XcomApplicationContext;
import org.xcom.schema.core.exception.XcomException;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * StringRedis 封装类
 *
 * @author xcom
 * @date 2024/8/9
 */

public class XcomStringRedis {

    /**
     * StringRedis 对象
     */
    private static StringRedisTemplate INSTANCE;

    /**
     * 初始化Redis对象
     *
     * @param stringRedisTemplate
     */
    public static void initInstance(StringRedisTemplate stringRedisTemplate) {
        INSTANCE = stringRedisTemplate;
    }

    /**
     * 获取Redis对象
     *
     * @return
     */
    public static StringRedisTemplate getInstance() {
        if (ObjectUtils.isEmpty(INSTANCE)) {
            throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION, "StringRedisTemplate 对象未初始化");
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
        return String.format("%s:%s:%s", XcomApplicationContext.getApplicationId(), XcomApplicationContext.getFirstActiveProfiles(), key);
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
        if (org.apache.commons.lang3.ObjectUtils.isEmpty(funKey)) {
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
    public static void setRedisCache(String redisKey, String content, long timeout, TimeUnit unit) {
        if (StringUtil.isNullOrEmpty(content)) {
            return;
        }
        XcomStringRedis.getInstance().opsForValue().set(redisKey, content, timeout, unit);
    }

    /**
     * 设置redis缓存
     *
     * @param redisKey
     * @param content
     */
    public static void setRedisCache(String redisKey, String content) {
        if (StringUtil.isNullOrEmpty(content)) {
            return;
        }
        XcomStringRedis.getInstance().opsForValue().set(redisKey, content);
    }

    /**
     * 获取redis缓存
     *
     * @param redisKey
     * @return
     */
    public static String getRedisCache(String redisKey) {
        return XcomStringRedis.getInstance().opsForValue().get(redisKey);
    }

    /**
     * 移除redis缓存
     *
     * @param redisKey
     * @return
     */
    public static Boolean removeRedisCache(String redisKey) {
        if (Boolean.TRUE.equals(XcomStringRedis.getInstance().hasKey(redisKey))) {
            return XcomStringRedis.getInstance().delete(redisKey);
        }
        return true;
    }

}
