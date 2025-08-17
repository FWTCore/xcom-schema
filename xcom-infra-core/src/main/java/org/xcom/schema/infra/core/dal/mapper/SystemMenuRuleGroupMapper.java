package org.xcom.schema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.schema.infra.core.dal.entity.SystemMenuRuleGroupDO;

/**
 * 系统菜单规则组;(system_menu_rule_group)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemMenuRuleGroupMapper extends BaseMapper<SystemMenuRuleGroupDO>{
}