package org.xcom.schema.infra.core.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.schema.infra.core.dal.mapper.SystemOperationLogMapper;
import org.xcom.schema.infra.core.service.SystemOperationLogDomainService;

/**
 * 系统操作日志;(system_operation_log)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemOperationLogDomainServiceImpl implements SystemOperationLogDomainService {
    @Resource
    private SystemOperationLogMapper systemOperationLogMapper;

}