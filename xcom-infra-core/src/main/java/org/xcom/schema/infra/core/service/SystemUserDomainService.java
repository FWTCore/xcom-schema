package org.xcom.schema.infra.core.service;

import org.xcom.schema.infra.core.model.response.LoginAccountModel;
import org.xcom.schema.infra.core.model.SystemUserBO;

import java.util.List;

/**
 * 系统用户;(system_user)表 领域
 * @author :xcom
 * @date : 2025-8-17
 */
public interface SystemUserDomainService {
    /**
     * 获取用户角色
     * @param userId
     * @return
     */

    List<LoginAccountModel.LoginUserRoleRespBO> listLoginUserRoleByUserId(Long userId);

    /**
     * 通过用户id查询登录账户信息
     *
     * @param userId
     * @return
     */
    LoginAccountModel.LoginUserRespBO getLoginUserByUserId(Long userId);

    /**
     * 通过登录账户获取账户信息
     * @param loginName
     * @return
     */
    SystemUserBO getLoginUserByLoginName(String loginName);

}