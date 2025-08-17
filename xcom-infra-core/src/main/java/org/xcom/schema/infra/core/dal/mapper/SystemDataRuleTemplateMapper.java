package org.xcom.schema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.schema.infra.core.dal.entity.SystemDataRuleTemplateDO;

/**
 * 系统数据规则模板;(system_data_rule_template)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemDataRuleTemplateMapper extends BaseMapper<SystemDataRuleTemplateDO>{
}
