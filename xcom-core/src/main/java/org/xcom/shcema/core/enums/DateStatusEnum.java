package org.xcom.shcema.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 数据通用状态枚举
 *
 * @author xcom
 * @date 2024/11/11
 */

@Getter
@RequiredArgsConstructor
public enum DateStatusEnum implements IEnum {

    /**
     * 禁用
     */
    DISABLE(-1, "禁用"),
    /**
     * 启用
     */
    ENABLE(1, "启用"),
    /**
     * 无效
     */
    INVALID(-2, "无效"),
    /**
     * 有效
     */
    VALID(2, "有效"),
    ;

    private final Integer code;
    private final String desc;

}
