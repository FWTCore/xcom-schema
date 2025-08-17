package org.xcom.schema.infra.core.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.schema.infra.core.service.SystemCompanyDomainService;
import org.xcom.schema.infra.core.dal.mapper.SystemCompanyMapper;

/**
 * 系统公司;(system_company)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemCompanyDomainServiceImpl implements SystemCompanyDomainService {
    @Resource
    private SystemCompanyMapper systemCompanyMapper;

}