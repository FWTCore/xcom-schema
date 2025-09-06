package org.xcom.schema.infra.starter.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xcom.schema.core.annotation.Authority;
import org.xcom.schema.core.annotation.NoNeedLogin;
import org.xcom.schema.core.enums.AuthTypeEnum;
import org.xcom.schema.core.model.XcomResult;
import org.xcom.schema.infra.core.model.request.LoginReqBO;
import org.xcom.schema.infra.core.shared.AccountService;
import org.xcom.schema.infra.core.shared.SystemUserService;
import org.xcom.schema.infra.starter.system.convert.AccountMapping;
import org.xcom.schema.infra.starter.system.request.LoginRequest;
import org.xcom.schema.infra.starter.system.response.LoginUserVO;

/**
 * 账户管理
 * @author xcom
 * @date 2025/9/4
 */

@Slf4j
@RestController
@Tag(name = "系统-账户管理", description = "账户相关操作")
@RequestMapping("/system/account")
public class AccountController {

    @Resource
    private AccountService    accountService;
    @Resource
    private SystemUserService systemUserService;

    @Operation(summary = "账户登录", description = "返回登录用户的token")
    @PostMapping(value = "/login")
    @NoNeedLogin
    public XcomResult<String> login(@NotNull @RequestBody @Validated LoginRequest loginRequest) {
        LoginReqBO loginReqBO = AccountMapping.INSTANCE.toLoginReqBO(loginRequest);
        String token = accountService.userLogin(loginReqBO);
        return XcomResult.success();
    }

    @Operation(summary = "获取登录结果信息", description = "返回登录结果信息")
    @GetMapping(value = "/getLoginInfo")
    @Authority(type = AuthTypeEnum.JUST_LOGIN)
    public XcomResult<LoginUserVO> getLoginInfo() {

        String token = "";
        return XcomResult.success(null);
    }

    @Operation(summary = "退出登录  @author 卓大")
    @GetMapping("/login/logout")
    public XcomResult<Boolean> logout() {
        return XcomResult.success(null);
    }

    @Operation(summary = "获取登录用户权限", description = "返回登录用户权限")
    @GetMapping(value = "/getLoginAuth")
    @Authority(type = AuthTypeEnum.JUST_LOGIN)
    public XcomResult<String> getLoginAuth() {

        String token = "";
        return XcomResult.success(token);
    }

}
