package org.xcom.schema.infra.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.enums.DateStatusEnum;
import org.xcom.schema.core.enums.GenderEnum;
import org.xcom.schema.core.model.AbstractEntityBaseBO;

import java.time.LocalDateTime;

/**
 * 系统用户BO
 *
 * @author xcom
 * @date 2025/9/6
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SystemUserBO extends AbstractEntityBaseBO {

    /**
     * 系统公司id,;
     */
    private Long          systemCompanyId;

    /**
     * 登录名称,;
     */
    private String        loginName;

    /**
     * 登录密码,;
     */
    private String        loginPassword;

    /**
     * 密码密钥,;
     */
    private String        secretKey;

    /**
     * 用户名称,;
     */
    private String        displayName;

    /**
     * 手机号,;
     */
    private String        mobilePhone;

    /**
     * 性别,;
     * @see GenderEnum
     */
    private Short         gender;

    /**
     * 出生日期,;
     */
    private LocalDateTime birth;

    /**
     * 头像,;
     */
    private String        avatar;

    /**
     * 职位,;
     */
    private String        position;

    /**
     * 最后登录时间,;
     */
    private LocalDateTime lastLoginTime;

    /**
     * 数据状态,;
     * @see DateStatusEnum.EnableEnum
     */
    private Short         userStatus;
}
