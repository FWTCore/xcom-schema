package org.xcom.shcema.infra.core.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemCompanyMapper;
import org.xcom.shcema.infra.core.service.SystemCompanyDomainService;

/**
 * 系统公司;(system_company)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
public class SystemCompanyDomainServiceImpl implements SystemCompanyDomainService {
    @Resource
    private SystemCompanyMapper systemCompanyMapper;

}