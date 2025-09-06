package org.xcom.schema.infra.starter.system.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录返回
 *
 * @author xcom
 * @date 2025/9/6
 */

@Data
public class LoginVO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    /**
     *登录token
     */
    private String            token;
}
