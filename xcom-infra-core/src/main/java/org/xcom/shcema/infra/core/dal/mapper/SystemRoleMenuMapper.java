package org.xcom.shcema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.shcema.infra.core.dal.entity.SystemRoleMenuDO;

/**
 * 系统角色菜单;(system_role_menu)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemRoleMenuMapper extends BaseMapper<SystemRoleMenuDO>{
}