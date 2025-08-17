package org.xcom.shcema.core.context;

import lombok.Data;

/**
 * 上下文用户
 *
 * @author xcom
 * @date 2024/8/9
 */

@Data
public class AccessUser {

    /**
     * 主键
     */
    private Long   id;

    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 用户名称
     */
    private String displayName;

    /**
     * 用户授权token
     */
    private String token;

    /**
     * 用户登录ip
     */
    private String ipAddress;
}
