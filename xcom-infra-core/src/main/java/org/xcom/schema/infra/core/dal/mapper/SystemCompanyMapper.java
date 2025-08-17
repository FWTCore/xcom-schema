package org.xcom.schema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xcom.schema.infra.core.dal.entity.SystemCompanyDO;

/**
 * 系统公司;(system_company)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemCompanyMapper extends BaseMapper<SystemCompanyDO> {
}