package org.xcom.shcema.core;


import org.xcom.shcema.core.enums.SystemCodeEnum;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务异常
 *
 * @author xcom
 * @date 2024/11/11
 */

public class XcomException extends RuntimeException {
    private static final long serialVersionUID = -7864604160297181941L;

    private int code;

    private String message;

    private Map<String, Object> attributes;

    public static XcomException create(SystemCodeEnum errorCode) {
        return new XcomException(errorCode);
    }

    public static XcomException create(SystemCodeEnum code, Throwable ex) {
        return new XcomException(code, ex);
    }

    public static XcomException create(SystemCodeEnum code, String message) {
        return new XcomException(code, message);
    }

    public static XcomException create(SystemCodeEnum code, String message, Throwable ex) {
        return new XcomException(code, message, ex);
    }

    protected XcomException(SystemCodeEnum code) {
        super(code.getDesc());
        this.code = code.getCode();
        this.message = code.getDesc();
    }

    protected XcomException(SystemCodeEnum code, Throwable ex) {
        super(code.getDesc(), ex);
        this.code = code.getCode();
        this.message = code.getDesc();
    }

    protected XcomException(SystemCodeEnum code, String message) {
        super(message);
        this.code = code.getCode();
        // 用于控制暴露给前端message
        this.message = message;
    }

    protected XcomException(SystemCodeEnum code, String message, Throwable ex) {
        super(message, ex);
        this.code = code.getCode();
        this.message = message;
    }

    /**
     * 为异常添加属性
     *
     * @param key
     * @param value
     * @return
     */
    public XcomException putAttribute(String key, Object value) {
        if (null == attributes) {
            attributes = new HashMap<>();
        }
        attributes.put(key, value);
        return this;
    }

    /**
     * 获取异常属性
     *
     * @return
     */
    public Map<String, Object> getAttributes() {
        return null == attributes ? Collections.emptyMap() : attributes;
    }

    /**
     * 设置异常属性
     *
     * @return
     */
    public XcomException setAttributes(Map<String, Object> attributes) {
        if (null != attributes) {
            this.attributes = attributes;
        }
        return this;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "XcomException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", attributes=" + attributes +
                "} " + super.toString();
    }
}