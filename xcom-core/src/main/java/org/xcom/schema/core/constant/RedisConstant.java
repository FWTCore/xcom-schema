package org.xcom.schema.core.constant;

/**
 * Redis 常量
 *
 * @author xcom
 * @date 2024/11/11
 */

public class RedisConstant {

    /**
     * redis 缓存5分
     */
    public static final short REDIS_TIME_OUT_FIVE_MINUTE = 5;
    /**
     * redis 缓存10分
     */
    public static final short REDIS_TIME_OUT_TEN_MINUTE = 10;

    /**
     * redis 缓存30分
     */
    public static final short REDIS_TIME_OUT_HALF_HOUR = 30;
    /**
     * redis 缓存1小时
     */
    public static final short REDIS_TIME_OUT_ONE_HOUR = 60;
    /**
     * redis 缓存5小时
     */
    public static final short REDIS_TIME_OUT_FIVE_HOUR = 300;
    /**
     * redis 缓存10小时
     */
    public static final short REDIS_TIME_OUT_TEN_HOUR = 600;
    /**
     * redis 缓存1天
     */
    public static final short REDIS_TIME_OUT_ONE_DAY = 1440;


    /**
     * INFRA登录 缓存前缀
     */
    public static final String REDIS_INFRA_LOGIN_PREFIX = "infra_login";

    /**
     * 角色缓存key
     */
    public static final String REDIS_INFRA_ROLE_PREFIX = "infra_role";

    /**
     * 权限缓存key
     */
    public static final String REDIS_INFRA_PERM_PREFIX = "infra_perm";

    /**
     * 字典缓存key
     */
    public static final String REDIS_INFRA_DICT_PREFIX = "infra_dict";

    /**
     * 枚举缓存key
     */
    public static final String REDIS_INFRA_ENUM_PREFIX = "infra_enum";

    /**
     * 菜单缓存key
     */
    public static final String REDIS_INFRA_MENU_PREFIX = "infra_menu";



    /**
     * 全局 缓存前缀
     */
    public static final String REDIS_GLOBAL_PREFIX = "global";




}
