package org.xcom.schema.infra.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.schema.infra.core.dal.dao.SystemRoleMapper;
import org.xcom.schema.infra.core.dal.dao.SystemRoleMenuMapper;
import org.xcom.schema.infra.core.model.response.LoginAccountModel;
import org.xcom.schema.infra.core.service.SystemRoleDomainService;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统角色;(system_role)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemRoleDomainServiceImpl implements SystemRoleDomainService {
    @Resource
    private SystemRoleMapper     systemRoleMapper;
    @Resource
    private SystemRoleMenuMapper systemRoleMenuMapper;

    @Override
    public List<LoginAccountModel.LoginUserAuthRespBO> listSystemUserMenuByRoleIds(List<Long> roleIds) {
        if (CollectionUtil.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        return systemRoleMenuMapper.listSystemUserMenuByRoleIds(roleIds);
    }

}