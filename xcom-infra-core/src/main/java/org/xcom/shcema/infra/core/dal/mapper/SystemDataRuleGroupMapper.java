package org.xcom.shcema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.shcema.infra.core.dal.entity.SystemDataRuleGroupDO;

/**
 * 系统数据规则组;(system_data_rule_group)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemDataRuleGroupMapper extends BaseMapper<SystemDataRuleGroupDO>{
}