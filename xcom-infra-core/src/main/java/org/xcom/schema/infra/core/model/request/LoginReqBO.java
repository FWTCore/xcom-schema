package org.xcom.schema.infra.core.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求
 *
 * @author xcom
 * @date 2025/9/6
 */
@Data
public class LoginReqBO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
     *登录账户
     */
    private String            loginName;
    /**
     * 登录密码
     */
    private String            loginPassword;
}
