package org.xcom.shcema.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户JWT对象
 *
 * @author xcom
 * @date 2024/8/11
 */

@Data
public class AuthUserJwtDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

}
