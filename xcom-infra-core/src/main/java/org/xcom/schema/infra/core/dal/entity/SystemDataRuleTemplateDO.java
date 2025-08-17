package org.xcom.schema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.model.EntityBaseDO;

/**
 * 系统数据规则模板;system_data_rule_template数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_data_rule_template")
public class SystemDataRuleTemplateDO extends EntityBaseDO {

    /**
     * 规则名称,;
     */
    private String ruleName;

    /**
     * 规则字段,;
     */
    private String ruleColumn;

    /**
     * 规则条件,;
     * @see org.xcom.schema.core.enums.ConditionEnum
     */
    private Short  ruleCondition;

    /**
     * 规则值,;
     */
    private String ruleValue;
}