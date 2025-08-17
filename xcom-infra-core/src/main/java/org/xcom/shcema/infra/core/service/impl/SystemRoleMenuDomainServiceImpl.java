package org.xcom.shcema.infra.core.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemRoleMenuMapper;
import org.xcom.shcema.infra.core.service.SystemRoleMenuDomainService;

/**
 * 系统角色菜单;(system_role_menu)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
public class SystemRoleMenuDomainServiceImpl implements SystemRoleMenuDomainService {
    @Resource
    private SystemRoleMenuMapper systemRoleMenuMapper;

}