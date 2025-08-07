package org.xcom.shcema.core.redis;

import org.springframework.util.DigestUtils;
import org.xcom.shcema.core.constant.RedisConstant;
import org.xcom.shcema.core.context.AccessContextHolder;
import org.xcom.shcema.core.context.AccessUser;
import org.xcom.shcema.core.enums.SystemCodeEnum;
import org.xcom.shcema.core.exception.XcomException;

/**
 *  Redis Key类
 *
 * @author xcom
 * @date 2024/8/9
 */

public class KeyRedisUtil {

    /**
     * 获取登录用户UUID
     *
     * @return
     */
    public static String getLoginUUID() {
        // 获取登录用户
        AccessUser accessUser = AccessContextHolder.getAccessUser().orElseThrow(() -> XcomException.create(SystemCodeEnum.REQUEST_UNAUTHORIZED));
        return DigestUtils.md5DigestAsHex(accessUser.getToken().getBytes());
    }


    /**
     * 获取登录角色UUID
     *
     * @param key
     * @return
     */
    public static String getLoginRoleUUID(String key) {
        return String.format("%s_%s", RedisConstant.REDIS_INFRA_ROLE_PREFIX, key);
    }

    /**
     * 获取登录权限UUID
     *
     * @param key
     * @return
     */
    public static String getLoginPermUUID(String key) {
        return String.format("%s_%s", RedisConstant.REDIS_INFRA_PERM_PREFIX, key);
    }

    /**
     * 生成缓存key
     *
     * @param redisKey
     * @return
     */
    public static String getLoginRedisKey(String redisKey) {
        return XcomRedisTemplate.generateRedisKey(redisKey, tempKey -> String.format("%s_%s_%s", RedisConstant.REDIS_INFRA_LOGIN_PREFIX, getLoginUUID(), tempKey));
    }

    /**
     * 生成缓存key
     *
     * @param key
     * @return
     */
    public static String getGlobalRedisKey(String key) {
        return XcomRedisTemplate.generateRedisKey(key, tempKey -> String.format("%s_%s", RedisConstant.REDIS_GLOBAL_PREFIX, tempKey));
    }


}

