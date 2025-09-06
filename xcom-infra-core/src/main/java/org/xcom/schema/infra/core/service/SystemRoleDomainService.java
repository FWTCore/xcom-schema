package org.xcom.schema.infra.core.service;

import org.xcom.schema.infra.core.model.response.LoginAccountModel;

import java.util.List;

/**
 * 系统角色;(system_role)表 领域
 * @author :xcom
 * @date : 2025-8-17
 */
public interface SystemRoleDomainService {

    /**
     * 获取角色菜单
     * @param roleIds
     * @return
     */
    List<LoginAccountModel.LoginUserAuthRespBO> listSystemUserMenuByRoleIds(List<Long> roleIds);
}