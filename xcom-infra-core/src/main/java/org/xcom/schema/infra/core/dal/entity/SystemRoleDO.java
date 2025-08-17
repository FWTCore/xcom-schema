package org.xcom.schema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.infra.core.enums.SystemRoleEnum;
import org.xcom.schema.core.model.EntityBaseDO;

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