package org.xcom.shcema.infra.core.shared.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.xcom.shcema.core.concurrent.CompletableFutureUtil;
import org.xcom.shcema.core.concurrent.XcomThreadPoolFactory;
import org.xcom.shcema.core.enums.SystemCodeEnum;
import org.xcom.shcema.core.exception.XcomException;
import org.xcom.shcema.core.redis.ActionRedisUtil;
import org.xcom.shcema.core.redis.KeyRedisUtil;
import org.xcom.shcema.infra.core.enums.SystemRoleEnum;
import org.xcom.shcema.infra.core.model.LoginAccountModel;
import org.xcom.shcema.infra.core.service.SystemMenuDomainService;
import org.xcom.shcema.infra.core.service.SystemRoleMenuDomainService;
import org.xcom.shcema.infra.core.service.SystemUserDomainService;
import org.xcom.shcema.infra.core.service.SystemUserRoleDomainService;
import org.xcom.shcema.infra.core.shared.AccountService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * 账户 服务
 *
 * @author xcom
 * @date 2025/8/17
 */

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private SystemUserDomainService     systemUserDomainService;
    @Resource
    private SystemUserRoleDomainService systemUserRoleDomainService;
    @Resource
    private SystemMenuDomainService     systemMenuDomainService;
    @Resource
    private SystemRoleMenuDomainService systemRoleMenuDomainService;

    @Override
    public LoginAccountModel.LoginUserPermissionsRespDO getLoginUserPermissionsByUserId(Long userId) {

        // 多线程获取处理
        ExecutorService ioTheadPool = XcomThreadPoolFactory.IO_THEAD_POOL;
        // 获取异步 登录账户角色
        CompletableFuture<LoginAccountModel.LoginUserRespBO> loginUserResultFuture = CompletableFuture
            .supplyAsync(() -> getLoginUserByUserId(userId), ioTheadPool);
        // 获取异步 登录账户角色
        CompletableFuture<List<LoginAccountModel.LoginUserRoleRespBO>> loginUserRoleResultFuture = CompletableFuture
            .supplyAsync(() -> getLoginUserRoleByUserId(userId), ioTheadPool);

        LoginAccountModel.LoginUserPermissionsRespDO resultData = new LoginAccountModel.LoginUserPermissionsRespDO();

        // 得到用户所属部门结果
        LoginAccountModel.LoginUserRespBO loginUserRespDO = CompletableFutureUtil
            .getCompletableFutureResult(loginUserResultFuture);
        resultData.setUserId(loginUserRespDO.getId());
        resultData.setCompanyId(loginUserRespDO.getCompanyId());
        resultData.setLastLoginTime(loginUserRespDO.getLastLoginTime());
        // 超管设置默认为false
        resultData.setSuperAdminFlag(false);
        // 得到登录账户角色结果
        List<LoginAccountModel.LoginUserRoleRespBO> loginUserRoleList = CompletableFutureUtil
            .getCompletableFutureResult(loginUserRoleResultFuture);
        resultData.setLoginUserRoleList(loginUserRoleList);

        // 角色不为空，才获取 应用以及菜单权限
        if (CollectionUtil.isNotEmpty(loginUserRoleList)) {
            /**
             *  管理员角色 全部拥有
             *  预置角色以及自定义角色 关联
             */
            Boolean hasSuperAdmin = loginUserRoleList.stream()
                .anyMatch(e -> e.getRoleType().equals(SystemRoleEnum.RoleTypeEnum.SUPER_ADMIN.getCode()));
            resultData.setSuperAdminFlag(hasSuperAdmin);

            List<Long> roleIdList = loginUserRoleList.stream().map(LoginAccountModel.LoginUserRoleRespBO::getRoleId)
                .collect(Collectors.toList());
            // 菜单
            List<LoginAccountModel.LoginUserAuthRespBO> systemMenuList = listLoginUserAuth(userId, roleIdList,
                hasSuperAdmin);
            // 账户权限信息转换
            if (CollectionUtil.isNotEmpty(systemMenuList)) {
                resultData.setLoginUserAuthList(systemMenuList);
            }
        }
        return resultData;
    }

    @Override
    public LoginAccountModel.LoginUserRespBO getLoginUserByUserId(Long userId) {
        // 先获取缓存数据
        String redisUserKey = KeyRedisUtil.getLoginRedisKey(userId.toString());
        Object redisObj = ActionRedisUtil.getRedisData(redisUserKey);
        LoginAccountModel.LoginUserRespBO loginUser;
        if (ObjectUtils.isEmpty(redisObj)) {
            loginUser = systemUserDomainService.getLoginUserByUserId(userId);
            if (ObjectUtil.isNull(loginUser)) {
                throw XcomException.create(SystemCodeEnum.REQUEST_UNAUTHORIZED);
            }
            ActionRedisUtil.cacheRedisDataForLogin(redisUserKey, loginUser);
        } else {
            if (!(redisObj instanceof LoginAccountModel.LoginUserRespBO)) {
                throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION, "登录账户缓存数据类型错误");
            }
            loginUser = (LoginAccountModel.LoginUserRespBO) redisObj;
        }
        return loginUser;
    }

    @Override
    public List<LoginAccountModel.LoginUserRoleRespBO> getLoginUserRoleByUserId(Long userId) {
        // 先获取缓存数据
        String redisKey = KeyRedisUtil.getLoginRoleUUID(userId.toString());
        String redisRoleKey = KeyRedisUtil.getLoginRedisKey(redisKey);
        Object redisData = ActionRedisUtil.getRedisData(redisRoleKey);

        List<LoginAccountModel.LoginUserRoleRespBO> resultData;
        if (ObjectUtils.isEmpty(redisData)) {
            resultData = systemUserRoleDomainService.listLoginUserRoleByUserId(userId);
            if (CollectionUtil.isNotEmpty(resultData)) {
                ActionRedisUtil.cacheRedisDataForLogin(redisRoleKey, resultData);
            }
        } else {
            if (!(redisData instanceof List<?>)) {
                throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION, "登录账户角色缓存数据类型错误");
            }
            resultData = (List<LoginAccountModel.LoginUserRoleRespBO>) redisData;
        }
        return resultData;
    }

    @Override
    public List<LoginAccountModel.LoginUserAuthRespBO> listLoginUserAuth(Long userId, List<Long> roleIds,
                                                                         Boolean hasSuperAdmin) {
        // 先获取缓存数据
        String redisKey = KeyRedisUtil.getLoginPermUUID(userId.toString());
        String redisPermKey = KeyRedisUtil.getLoginRedisKey(redisKey);
        Object redisData = ActionRedisUtil.getRedisData(redisPermKey);

        List<LoginAccountModel.LoginUserAuthRespBO> resultData;
        if (ObjectUtils.isEmpty(redisData)) {
            if (BooleanUtil.isTrue(hasSuperAdmin)) {
                resultData = systemMenuDomainService.listAllMenu(Boolean.FALSE);
            } else {
                resultData = systemRoleMenuDomainService.listSystemUserMenuByRoleIds(roleIds);
            }
            if (CollectionUtil.isNotEmpty(resultData)) {
                ActionRedisUtil.cacheRedisDataForLogin(redisPermKey, resultData);
            }
        } else {
            if (!(redisData instanceof List<?>)) {
                throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION, "登录账户权限缓存数据类型错误");
            }
            resultData = (List<LoginAccountModel.LoginUserAuthRespBO>) redisData;
        }

        return resultData;
    }
}
