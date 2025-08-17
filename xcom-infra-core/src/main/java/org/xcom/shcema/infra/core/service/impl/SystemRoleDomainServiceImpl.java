package org.xcom.shcema.infra.core.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemRoleMapper;
import org.xcom.shcema.infra.core.service.SystemRoleDomainService;

/**
 * 系统角色;(system_role)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
public class SystemRoleDomainServiceImpl implements SystemRoleDomainService {
    @Resource
    private SystemRoleMapper systemRoleMapper;

}