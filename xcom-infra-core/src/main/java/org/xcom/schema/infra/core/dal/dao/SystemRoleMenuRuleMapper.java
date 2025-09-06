package org.xcom.schema.infra.core.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.schema.infra.core.dal.entity.SystemRoleMenuRuleDO;

/**
 * 系统角色菜单规则;(system_role_menu_rule)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemRoleMenuRuleMapper extends BaseMapper<SystemRoleMenuRuleDO> {
}