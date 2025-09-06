package org.xcom.schema.infra.core.shared;

import org.xcom.schema.infra.core.model.request.LoginReqBO;
import org.xcom.schema.infra.core.model.response.LoginAccountModel;

import java.util.List;

/**
 * 账户 服务
 *
 * @author xcom
 * @date 2025/8/17
 */

public interface AccountService {

    /**
     * 获取登录用户权限
     *
     * @param userId
     * @return
     */
    LoginAccountModel.LoginUserPermissionsRespDO getLoginUserPermissionsByUserId(Long userId);

    /**
     * 通过用户id查询登录账户信息
     *
     * @param userId
     * @return
     */
    LoginAccountModel.LoginUserRespBO getLoginUserByUserId(Long userId);

    /**
     * 获取用户角色
     *
     * @param userId
     * @return
     */
    List<LoginAccountModel.LoginUserRoleRespBO> getLoginUserRoleByUserId(Long userId);

    /**
     * 获取用户授权信息
     *
     * @param userId
     * @param roleIds
     * @param hasSuperAdmin
     * @return
     */
    List<LoginAccountModel.LoginUserAuthRespBO> listLoginUserAuth(Long userId, List<Long> roleIds,
                                                                  Boolean hasSuperAdmin);

    /**
     * 获取登录用户的token
     * @param loginReqBO
     * @return 返回token
     */
    String userLogin(LoginReqBO loginReqBO);

}
