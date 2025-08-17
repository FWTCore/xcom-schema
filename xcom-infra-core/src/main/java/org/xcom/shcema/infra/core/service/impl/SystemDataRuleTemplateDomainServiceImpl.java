package org.xcom.shcema.infra.core.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemDataRuleTemplateMapper;
import org.xcom.shcema.infra.core.service.SystemDataRuleTemplateDomainService;

/**
 * 系统数据规则模板;(system_data_rule_template)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemDataRuleTemplateDomainServiceImpl implements SystemDataRuleTemplateDomainService {
    @Resource
    private SystemDataRuleTemplateMapper systemDataRuleTemplateMapper;

}
