package org.xcom.schema.core.model;

import lombok.Data;
import org.xcom.schema.core.enums.IEnum;
import org.xcom.schema.core.enums.SystemCodeEnum;

import java.io.Serializable;

/**
 * 统一响应模型
 *
 * @author xcom
 * @date 2024/7/27
 */

@Data
public class XcomResult<T> implements Serializable {

    private static final long serialVersionUID = 2081104351106889132L;

    /**
     * 是否成功，true：成功，false：失败
     */
    private boolean success;
    /**
     * 错误码
     * 非异常为200
     */
    private int code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public XcomResult() {
        this.success = true;
        this.code = SystemCodeEnum.SUCCESS.getCode();
        this.message = SystemCodeEnum.SUCCESS.getDesc();
    }

    public XcomResult(T data) {
        this.success = true;
        this.code = SystemCodeEnum.SUCCESS.getCode();
        this.message = SystemCodeEnum.SUCCESS.getDesc();
        this.data = data;
    }

    public XcomResult(int code, String message) {
        this.success = false;
        this.code = code;
        this.message = message;
    }

    public XcomResult(int code, String message, T data) {
        this.success = false;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> XcomResult<T> success() {
        return new XcomResult<>();
    }

    public static <T> XcomResult<T> success(T data) {
        return new XcomResult<>(data);
    }

    public static <T> XcomResult<T> error(int code, String message) {
        return new XcomResult<>(code, message);
    }

    public static <T> XcomResult<T> error(IEnum errorCode) {
        return new XcomResult<>(errorCode.getCode(), errorCode.getDesc());
    }

    public static <T> XcomResult<T> error(IEnum errorCode, T data) {
        return new XcomResult<>(errorCode.getCode(), errorCode.getDesc(), data);
    }

    public static <T> XcomResult<T> badRequest(String message) {
        return new XcomResult<>(SystemCodeEnum.INTERNAL_SERVER_ERROR.getCode(), message);
    }

    public static <T> XcomResult<T> badRequest(String message, T data) {
        return new XcomResult<>(SystemCodeEnum.INTERNAL_SERVER_ERROR.getCode(), message, data);
    }

}
