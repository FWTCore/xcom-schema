package org.xcom.shcema.infra.core.service;


import org.xcom.shcema.infra.core.model.LoginAccountModel;

import java.util.List;

/**
 * 系统角色菜单;(system_role_menu)表 领域
 * @author :xcom
 * @date : 2025-8-17
 */
public interface SystemRoleMenuDomainService{

    /**
     * 获取角色菜单
     *
     * @param roleIds
     * @return
     */
    List<LoginAccountModel.LoginUserAuthRespBO> listSystemUserMenuByRoleIds(List<Long> roleIds);
}