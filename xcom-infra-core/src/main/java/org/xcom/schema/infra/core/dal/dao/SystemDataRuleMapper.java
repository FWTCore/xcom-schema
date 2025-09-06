package org.xcom.schema.infra.core.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.schema.infra.core.dal.entity.SystemDataRuleDO;

/**
 * 系统数据规则;(system_data_rule)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemDataRuleMapper extends BaseMapper<SystemDataRuleDO>{
}