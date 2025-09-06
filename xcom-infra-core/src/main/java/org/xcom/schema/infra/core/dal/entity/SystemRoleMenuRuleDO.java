package org.xcom.schema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.model.AbstractEntityBaseDO;

/**
 * 系统角色菜单规则;system_role_menu_rule数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_role_menu_rule")
public class SystemRoleMenuRuleDO extends AbstractEntityBaseDO {

    /**
     * 系统角色菜单id,;
     */
    private Long systemRoleMenuId;

    /**
     * 系统菜单规则id,;
     */
    private Long systemMenuRuleGroupId;
}