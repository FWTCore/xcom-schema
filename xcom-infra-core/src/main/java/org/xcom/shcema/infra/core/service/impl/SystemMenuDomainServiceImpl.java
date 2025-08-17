package org.xcom.shcema.infra.core.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemMenuMapper;
import org.xcom.shcema.infra.core.service.SystemMenuDomainService;

/**
 * 系统菜单;(system_menu)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
public class SystemMenuDomainServiceImpl implements SystemMenuDomainService {
    @Resource
    private SystemMenuMapper systemMenuMapper;

}