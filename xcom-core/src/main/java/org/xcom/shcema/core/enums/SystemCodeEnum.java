package org.xcom.shcema.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 系统编码枚举
 *
 * @author xcom
 * @date 2024/11/11
 */

@Getter
@RequiredArgsConstructor
public enum SystemCodeEnum implements IEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),
    /**
     * 错误请求
     * 未到 controller 异常
     */
    BAD_REQUEST(400, "错误请求"),
    /**
     * "请先完成登录，再继续操作
     */
    REQUEST_UNAUTHORIZED(401, "登录超时，请重新登录！"),
    /**
     * 无访问权限
     */
    REQUEST_FORBIDDEN(403, "无访问权限"),
    /**
     * 您迷路了
     * 访问资源不存在
     */
    NOT_FOUND(404, "您迷路了"),
    /**
     * 服务器内部错误
     * 通用异常信息
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    /**
     * 业务错误
     * 需要关注的业务异常
     */
    BUSINESS_EXCEPTION(10000, "业务错误"),
    /**
     * 数据不存在
     */
    DATA_NOT_EXISTS(10001, "数据不存在"),
    /**
     * 参数非法
     * 参数校验不通过
     */
    PARAMETER_ERROR(10002, "参数非法");

    private final Integer code;
    private final String desc;

}
