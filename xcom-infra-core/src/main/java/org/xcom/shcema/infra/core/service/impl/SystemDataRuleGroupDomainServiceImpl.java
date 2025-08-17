package org.xcom.shcema.infra.core.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemDataRuleGroupMapper;
import org.xcom.shcema.infra.core.service.SystemDataRuleGroupDomainService;

/**
 * 系统数据规则组;(system_data_rule_group)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
public class SystemDataRuleGroupDomainServiceImpl implements SystemDataRuleGroupDomainService {
    @Resource
    private SystemDataRuleGroupMapper systemDataRuleGroupMapper;

}