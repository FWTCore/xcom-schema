package org.xcom.schema.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 鉴权方式
 *
 * @author xcom
 * @date 2024/11/11
 */

@Getter
@RequiredArgsConstructor
public enum AuthTypeEnum implements IEnum {
    /**
     * 仅认证，不鉴权
     */
    JUST_LOGIN(0, "仅认证"),
    /**
     * 基于权限
     */
    PERMISSION(1, "基于权限");

    private final Integer code;
    private final String desc;
}
