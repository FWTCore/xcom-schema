package org.xcom.schema.infra.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.xcom.schema.core.enums.IEnum;

/**
 * 系统角色枚举 集合
 *
 * @author xcom
 * @date 2025/8/17
 */

public class SystemRoleEnum {

    /**
     * 系统角色类型
     *
     * @author xcom
     * @date 2023/6/5
     */
    @Getter
    @RequiredArgsConstructor
    public enum RoleTypeEnum implements IEnum {

        /**
         * 超级管理员
         */
        SUPER_ADMIN(0, "超级管理员"),
        /**
         * 预置角色
         */
        PRESET_ROLE(1, "预置角色"),
        /**
         * 自定义角色
         */
        CUSTOM_ROLE(2, "自定义角色"),;

        private final Integer code;
        private final String  desc;

    }
}
