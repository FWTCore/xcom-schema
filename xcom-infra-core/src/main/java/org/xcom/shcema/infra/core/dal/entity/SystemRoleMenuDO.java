package org.xcom.shcema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.shcema.core.model.EntityBaseDO;

/**
 * 系统角色菜单;system_role_menu数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_role_menu")
public class SystemRoleMenuDO extends EntityBaseDO {

    /**
     * 系统角色id,;
     */
    private Long systemRoleId;

    /**
     * 系统菜单id,;
     */
    private Long systemMenuId;
}