package org.xcom.schema.infra.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.xcom.schema.core.enums.IEnum;

/**
 * 系统菜单枚举 集合
 *
 * @author xcom
 * @date 2025/8/17
 */

public class SystemMenuEnum {

    /**
     * 系统菜单类型
     *
     * @author xcom
     * @date 2023/6/5
     */
    @Getter
    @RequiredArgsConstructor
    public enum MenuTypeEnum implements IEnum {

        /**
         * 目录
         */
        DIRECTORY(1, "目录"),
        /**
         * 页面
         */
        PAGE(2, "页面"),
        /**
         * 按钮
         */
        BUTTON(3, "按钮"),;

        private final Integer code;
        private final String  desc;

    }


}
