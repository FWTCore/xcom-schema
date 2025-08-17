package org.xcom.shcema.infra.core.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemUserRoleMapper;
import org.xcom.shcema.infra.core.model.LoginAccountModel;
import org.xcom.shcema.infra.core.service.SystemUserRoleDomainService;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户角色;(system_user_role)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemUserRoleDomainServiceImpl implements SystemUserRoleDomainService {
    @Resource
    private SystemUserRoleMapper systemUserRoleMapper;

    @Override
    public List<LoginAccountModel.LoginUserRoleRespBO> listLoginUserRoleByUserId(Long userId) {
        if (ObjectUtil.isNull(userId)) {
            return new ArrayList<>();
        }
        return systemUserRoleMapper.listLoginUserRoleByUserId(userId);
    }
}