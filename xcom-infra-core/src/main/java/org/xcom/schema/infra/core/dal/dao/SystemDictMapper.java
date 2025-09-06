package org.xcom.schema.infra.core.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.schema.infra.core.dal.entity.SystemDictDO;

/**
 * 系统字典;(system_dict)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemDictMapper extends BaseMapper<SystemDictDO> {
}