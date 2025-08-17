package org.xcom.schema.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 数据库排序枚举
 *
 * @author xcom
 * @date 2024/11/11
 */

@Getter
@RequiredArgsConstructor
public enum DBSortEnum implements IEnum {


    /**
     * 降序
     */
    DESC(0, "降序"),
    /**
     * 升序
     */
    ASC(1, "升序");

    private final Integer code;
    private final String desc;
}
