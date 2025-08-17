package org.xcom.shcema.infra.core.shared.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.xcom.shcema.core.concurrent.XcomThreadPoolFactory;
import org.xcom.shcema.core.enums.SystemCodeEnum;
import org.xcom.shcema.core.exception.XcomException;
import org.xcom.shcema.core.redis.ActionRedisUtil;
import org.xcom.shcema.core.redis.KeyRedisUtil;
import org.xcom.shcema.infra.core.model.LoginAccountModel;
import org.xcom.shcema.infra.core.service.SystemUserDomainService;
import org.xcom.shcema.infra.core.service.SystemUserRoleDomainService;
import org.xcom.shcema.infra.core.shared.AccountService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

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
    private SystemUserDomainService systemUserDomainService;
    @Resource
    private SystemUserRoleDomainService systemUserRoleDomainService;

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


        return null;
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
}
