package org.xcom.shcema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.shcema.core.model.EntityBaseDO;

/**
 * 系统用户角色;system_user_role数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_user_role")
public class SystemUserRoleDO extends EntityBaseDO {

    /**
     * 系统用户id,;
     */
    private Long systemUserId;

    /**
     * 系统角色id,;
     */
    private Long systemRoleId;
}