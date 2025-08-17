package org.xcom.schema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.model.EntityBaseDO;

/**
 * 系统菜单规则组;system_menu_rule_group数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_menu_rule_group")
public class SystemMenuRuleGroupDO extends EntityBaseDO {

    /**
     * 系统菜单id,;
     */
    private Long systemMenuId;

    /**
     * 系统数据规则组id,;
     */
    private Long systemDataRuleGroupId;
}