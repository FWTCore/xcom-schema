package org.xcom.shcema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.shcema.core.model.EntityBaseDO;
import org.xcom.shcema.infra.core.enums.SystemRoleEnum;

/**
 * 系统角色;system_role数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_role")
public class SystemRoleDO extends EntityBaseDO {

    /**
     * 角色名称,;
     */
    private String roleName;

    /**
     * 角色类型,;
     * @see SystemRoleEnum.RoleTypeEnum
     */
    private Short  roleType;
}