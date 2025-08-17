package org.xcom.schema.infra.core.service;


import org.xcom.schema.infra.core.model.LoginAccountModel;

import java.util.List;

/**
 * 系统用户角色;(system_user_role)表 领域
 * @author :xcom
 * @date : 2025-8-17
 */
public interface SystemUserRoleDomainService{
    /**
     * 获取系统用户角色
     *
     * @param userId
     * @return
     */
    List<LoginAccountModel.LoginUserRoleRespBO> listLoginUserRoleByUserId(Long userId);
}