package org.xcom.schema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.schema.infra.core.dal.entity.SystemOperationLogDO;

/**
 * 系统操作日志;(system_operation_log)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemOperationLogMapper extends BaseMapper<SystemOperationLogDO>{
}