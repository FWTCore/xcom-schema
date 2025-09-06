package org.xcom.schema.infra.starter.system.response;

import lombok.Data;
import org.xcom.schema.core.annotation.ApiModelPropertyPro;
import org.xcom.schema.infra.core.enums.SystemCompanyEnum;

import java.io.Serializable;

/**
 * 登录用户
 *
 * @author xcom
 * @date 2025/9/6
 */

@Data
public class LoginUserVO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    /**
     * 用户id
     */
    private Long              userId;
    /**
     * 用户名称,;
     */
    private String            displayName;
    /**
     * 系统公司id,;
     */
    private Long              companyId;
    /**
     * 公司名称,;
     */
    private String            companyName;

    /**
     * 公司类型,;
     * @see SystemCompanyEnum.CompanyTypeEnum
     */
    @ApiModelPropertyPro(value = "公司类型", enumClass = SystemCompanyEnum.CompanyTypeEnum.class)
    private Short             companyType;

}
