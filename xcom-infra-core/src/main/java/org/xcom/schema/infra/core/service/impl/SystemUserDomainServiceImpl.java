package org.xcom.schema.infra.core.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.xcom.schema.infra.core.convert.SystemUserMapping;
import org.xcom.schema.infra.core.dal.dao.SystemUserMapper;
import org.xcom.schema.infra.core.dal.dao.SystemUserRoleMapper;
import org.xcom.schema.infra.core.dal.entity.SystemUserDO;
import org.xcom.schema.infra.core.model.response.LoginAccountModel;
import org.xcom.schema.infra.core.model.SystemUserBO;
import org.xcom.schema.infra.core.service.SystemUserDomainService;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户;(system_user)表服务接口实现类
 *
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemUserDomainServiceImpl implements SystemUserDomainService {
    @Resource
    private SystemUserMapper     systemUserMapper;
    @Resource
    private SystemUserRoleMapper systemUserRoleMapper;

    @Override
    public List<LoginAccountModel.LoginUserRoleRespBO> listLoginUserRoleByUserId(Long userId) {
        if (ObjectUtil.isNull(userId)) {
            return new ArrayList<>();
        }
        return systemUserRoleMapper.listLoginUserRoleByUserId(userId);
    }

    @Override
    public LoginAccountModel.LoginUserRespBO getLoginUserByUserId(Long userId) {
        if (ObjectUtil.isNull(userId)) {
            return null;
        }
        return systemUserMapper.getLoginUserByUserId(userId);
    }

    @Override
    public SystemUserBO getLoginUserByLoginName(String loginName) {
        if (StringUtils.isBlank(loginName)) {
            return null;
        }
        LambdaQueryWrapper<SystemUserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemUserDO::getDeleteFlag, false);
        wrapper.eq(SystemUserDO::getLoginName, loginName);
        SystemUserDO systemUserDO = systemUserMapper.selectOne(wrapper);
        return SystemUserMapping.INSTANCE.toSystemUserServiceBO(systemUserDO);
    }
}