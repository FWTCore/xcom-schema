package org.xcom.shcema.infra.core.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemMenuRuleGroupMapper;
import org.xcom.shcema.infra.core.service.SystemMenuRuleGroupDomainService;

/**
 * 系统菜单规则组;(system_menu_rule_group)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemMenuRuleGroupDomainServiceImpl implements SystemMenuRuleGroupDomainService {
    @Resource
    private SystemMenuRuleGroupMapper systemMenuRuleGroupMapper;

}