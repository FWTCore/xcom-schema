package org.xcom.schema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.enums.DateStatusEnum;
import org.xcom.schema.core.model.AbstractEntityBaseDO;
import org.xcom.schema.infra.core.enums.SystemCompanyEnum;

/**
 * 系统公司;system_company数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_company")
public class SystemCompanyDO extends AbstractEntityBaseDO {

    /**
     * 公司名称,;
     */
    private String companyName;

    /**
     * 联系人,;
     */
    private String liaison;

    /**
     * 联系方式,;
     */
    private String telephone;

    /**
     * 公司地址,;
     */
    private String address;

    /**
     * 数据状态,;
     * @see DateStatusEnum.EnableEnum
     */
    private Short  companyStatus;

    /**
     * 公司类型,;
     * @see SystemCompanyEnum.CompanyTypeEnum
     */
    private Short  companyType;
}