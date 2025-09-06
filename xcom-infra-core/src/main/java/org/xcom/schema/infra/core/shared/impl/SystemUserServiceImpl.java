package org.xcom.schema.infra.core.shared.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.schema.infra.core.service.SystemUserDomainService;
import org.xcom.schema.infra.core.shared.SystemUserService;

/**
 * 系统用户 service
 *
 * @author xcom
 * @date 2025/9/6
 */
@Slf4j
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    private SystemUserDomainService systemUserDomainService;


}
