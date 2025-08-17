package org.xcom.schema.infra.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.schema.infra.core.dal.mapper.SystemRoleMenuMapper;
import org.xcom.schema.infra.core.model.LoginAccountModel;
import org.xcom.schema.infra.core.service.SystemRoleMenuDomainService;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统角色菜单;(system_role_menu)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemRoleMenuDomainServiceImpl implements SystemRoleMenuDomainService {
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