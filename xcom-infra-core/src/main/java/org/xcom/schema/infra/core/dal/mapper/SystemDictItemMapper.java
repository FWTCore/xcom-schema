package org.xcom.schema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.schema.infra.core.dal.entity.SystemDictItemDO;

/**
 * 系统字典项;(system_dict_item)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemDictItemMapper extends BaseMapper<SystemDictItemDO>{
}