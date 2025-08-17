package org.xcom.shcema.infra.core.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemDictMapper;
import org.xcom.shcema.infra.core.service.SystemDictDomainService;

/**
 * 系统字典;(system_dict)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
public class SystemDictDomainServiceImpl implements SystemDictDomainService {
    @Resource
    private SystemDictMapper systemDictMapper;

}