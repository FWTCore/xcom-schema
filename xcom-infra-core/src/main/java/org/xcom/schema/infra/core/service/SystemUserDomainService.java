package org.xcom.schema.infra.core.service;

import org.xcom.schema.infra.core.model.LoginAccountModel;

/**
 * 系统用户;(system_user)表 领域
 * @author :xcom
 * @date : 2025-8-17
 */
public interface SystemUserDomainService {

    /**
     * 通过用户id查询登录账户信息
     *
     * @param userId
     * @return
     */
    LoginAccountModel.LoginUserRespBO getLoginUserByUserId(Long userId);
}