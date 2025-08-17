package org.xcom.schema.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Boolean 枚举
 *
 * @author xcom
 * @date 2025/8/7
 */

@Getter
@RequiredArgsConstructor
public enum BooleanENum implements IEnum {

    /**
     * 否
     */
    FALSE(0, "否"),
    /**
     * 是
     */
    TURE(1, "是"),

    ;

    private final Integer code;
    private final String desc;
}
