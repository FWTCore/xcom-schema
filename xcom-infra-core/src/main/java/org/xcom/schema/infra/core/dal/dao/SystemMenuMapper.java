package org.xcom.schema.infra.core.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.schema.infra.core.dal.entity.SystemMenuDO;

/**
 * 系统菜单;(system_menu)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemMenuMapper extends BaseMapper<SystemMenuDO>{
}