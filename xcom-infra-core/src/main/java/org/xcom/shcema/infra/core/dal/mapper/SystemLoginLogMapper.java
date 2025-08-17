package org.xcom.shcema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.shcema.infra.core.dal.entity.SystemLoginLogDO;

/**
 * 系统登录日志;(system_login_log)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemLoginLogMapper extends BaseMapper<SystemLoginLogDO>{
}