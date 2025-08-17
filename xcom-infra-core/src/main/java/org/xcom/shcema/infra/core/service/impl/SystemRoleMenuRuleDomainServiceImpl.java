package org.xcom.shcema.infra.core.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemRoleMenuRuleMapper;
import org.xcom.shcema.infra.core.service.SystemRoleMenuRuleDomainService;

/**
 * 系统角色菜单规则;(system_role_menu_rule)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemRoleMenuRuleDomainServiceImpl implements SystemRoleMenuRuleDomainService {
    @Resource
    private SystemRoleMenuRuleMapper systemRoleMenuRuleMapper;

}