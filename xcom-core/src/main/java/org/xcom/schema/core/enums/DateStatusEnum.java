package org.xcom.schema.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 数据状态标记
 *
 * @author xcom
 * @date 2025/8/17
 */

public class DateStatusEnum {

    /**
     * 启用
     *
     * @author xcom
     * @date 2023/6/5
     */
    @Getter
    @RequiredArgsConstructor
    public enum EnableEnum implements IEnum {

        /**
         * 禁用
         */
        DISABLE(0, "禁用"),
        /**
         * 启用
         */
        ENABLE(1, "启用"),;

        private final Integer code;
        private final String  desc;

    }

    /**
     * 启用
     *
     * @author xcom
     * @date 2023/6/5
     */
    @Getter
    @RequiredArgsConstructor
    public enum ValidEnum implements IEnum {
        /**
         * 无效
         */
        INVALID(0, "无效"),
        /**
         * 有效
         */
        VALID(1, "有效"),;

        private final Integer code;
        private final String  desc;

    }

}
