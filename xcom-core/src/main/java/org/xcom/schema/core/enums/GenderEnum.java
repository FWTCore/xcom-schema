package org.xcom.schema.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 性别枚举
 *
 * @author xcom
 * @date 2025/8/17
 */

@Getter
@RequiredArgsConstructor
public enum GenderEnum implements IEnum {

    /**
     * 女
     */
    FEMALE(0, "女"),
    /**
     * 男
     */
    MALE(1, "男"),
    /**
     * 其他
     */
    OTHER(2, "其他"),;

    private final Integer code;
    private final String  desc;
}
