package org.xcom.schema.infra.starter.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xcom.schema.core.annotation.Authority;
import org.xcom.schema.core.enums.AuthTypeEnum;
import org.xcom.schema.core.model.XcomResult;
import org.xcom.schema.infra.starter.system.request.LoginRequest;

/**
 * 账户管理
 * @author xcom
 * @date 2025/9/4
 */

@Slf4j
@RestController
@Api(tags = { "系统-账户管理" })
@RequestMapping("/system/account")
public class AccountController {

    @ApiOperation("账户登录")
    @PostMapping(value = "/login")
    public XcomResult<String> login(@NotNull @RequestBody @Validated LoginRequest loginRequest) {

        String token = "";
        return XcomResult.success(token);
    }

    @ApiOperation("获取登录账户信息")
    @GetMapping(value = "/getUserInfo")
    public XcomResult<String> getUserInfo() {

        String token = "";
        return XcomResult.success(token);
    }

}
