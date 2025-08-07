package org.xcom.shcema.core.redis;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.xcom.shcema.core.exception.XcomException;
import org.xcom.shcema.core.constant.RedisConstant;
import org.xcom.shcema.core.enums.SystemCodeEnum;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis 操作类
 *
 * @author xcom
 * @date 2024/8/9
 */

public class ActionRedisUtil {

    /**
     * 缓存数据
     *
     * @param redisKey
     * @param content
     */
    public static void cacheRedisDataForLogin(String redisKey, Object content) {
        if (StringUtils.isBlank(redisKey) || ObjectUtils.isEmpty(content)) {
            throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION, "Redis 缓存数据不能为空");
        }
        XcomRedisTemplate.setRedisCache(redisKey, content, RedisConstant.REDIS_TIME_OUT_ONE_HOUR, TimeUnit.MINUTES);
    }

    /**
     * 缓存数据
     *
     * @param redisKey
     * @param content
     */
    public static void cacheRedisData(String redisKey, Object content) {
        if (StringUtils.isBlank(redisKey) || ObjectUtils.isEmpty(content)) {
            throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION, "Redis 缓存数据不能为空");
        }
        XcomRedisTemplate.setRedisCache(redisKey, content);
    }

    /**
     * 缓存数据
     *
     * @param redisKey
     * @param content
     */
    public static void cacheRedisData(String redisKey, Object content, long timeout, TimeUnit unit) {
        if (StringUtils.isBlank(redisKey) || ObjectUtils.isEmpty(content)) {
            throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION, "Redis 缓存数据不能为空");
        }
        XcomRedisTemplate.setRedisCache(redisKey, content, timeout, unit);
    }

    /**
     * 缓存数据
     *
     * @param content
     */
    public static void cacheRedisMapData(String redisKey, Map<Object, Object> content) {
        if (StringUtils.isBlank(redisKey) || content.isEmpty()) {
            throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION, "Redis 缓存数据不能为空");
        }
        XcomRedisTemplate.setRedisCacheForHash(redisKey, content);
    }


    /**
     * 获取数据
     *
     * @param redisKey
     * @return
     */
    public static Object getRedisData(String redisKey) {
        if (StringUtils.isBlank(redisKey)) {
            return null;
        }
        return XcomRedisTemplate.getRedisCache(redisKey);
    }

    /**
     * 获取数据
     *
     * @return
     */
    public static Map<Object, Object> getRedisMapData(String redisKey) {
        if (StringUtils.isBlank(redisKey)) {
            return null;
        }
        return XcomRedisTemplate.getRedisCacheForHash(redisKey);
    }

    /**
     * 获取数据
     *
     * @param redisKey
     * @param hashKey
     * @return
     */
    public static Object getRedisMapData(String redisKey, String hashKey) {
        if (StringUtils.isBlank(redisKey) || StringUtils.isBlank(hashKey)) {
            return null;
        }
        return XcomRedisTemplate.getRedisCacheForHash(redisKey, hashKey);
    }

    /**
     * 移除数据
     *
     * @return
     */
    public static void removeRedisData(String redisKey) {
        XcomRedisTemplate.removeRedisCache(redisKey);
    }

    /**
     * 移除数据
     *
     * @return
     */
    public static void removeRedisData(List<String> redisKeys) {
        XcomRedisTemplate.removeRedisCache(redisKeys);
    }

}

