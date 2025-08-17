package org.xcom.shcema.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 表达式枚举
 *
 * @author xcom
 * @date 2025/8/17
 */

@Getter
@RequiredArgsConstructor
public enum ConditionEnum implements IEnum {
    /**
     * ==
     */
    EQ(1,"=="),
    /**
     * !=
     */
    NE(2,"!="),
    /**
     * >
     */
    GT(3,">"),
    /**
     * >=
     */
    GE(4,">="),
    /**
     * <
     */
    LT(5,"<"),
    /**
     * <=
     */
    LE(6,"<="),
    /**
     * like
     */
    LIKE(7,"like"),
    /**
     * likeLeft
     */
    LIKE_LEFT(8,"likeLeft"),
    /**
     * likeRight
     */
    LIKE_RIGHT(9,"likeRight"),
    /**
     * notLike
     */
    NOT_LIKE(10,"notLike"),
    /**
     * in
     */
    IN(11,"in"),
    /**
     * notIn
     */
    NOT_IN(12,"notIn"),
    /**
     * isNull
     */
    IS_NULL(13,"isNull"),
    /**
     * isNotNull
     */
    IS_NOT_NULL(14,"isNotNull"),
    ;

    private final Integer code;
    private final String  desc;
}
