package org.xcom.shcema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.shcema.core.enums.DateStatusEnum;
import org.xcom.shcema.core.model.EntityBaseDO;

/**
 * 系统数据规则组;system_data_rule_group数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_data_rule_group")
public class SystemDataRuleGroupDO extends EntityBaseDO {

    /**
     * 规则组名,;
     */
    private String ruleGroupName;

    /**
     * 数据状态,;
     * @see DateStatusEnum.EnableEnum
     */
    private Short  dataStatus;
}