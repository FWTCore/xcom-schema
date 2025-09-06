package org.xcom.schema.infra.core.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.schema.core.enums.DateStatusEnum;
import org.xcom.schema.infra.core.convert.SystemDictMapping;
import org.xcom.schema.infra.core.dal.dao.SystemDictItemMapper;
import org.xcom.schema.infra.core.dal.dao.SystemDictMapper;
import org.xcom.schema.infra.core.dal.entity.SystemDictDO;
import org.xcom.schema.infra.core.dal.entity.SystemDictItemDO;
import org.xcom.schema.infra.core.model.SystemDictBO;
import org.xcom.schema.infra.core.model.SystemDictItemBO;
import org.xcom.schema.infra.core.service.SystemDictDomainService;

import java.util.List;

/**
 * 系统字典;(system_dict)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemDictDomainServiceImpl implements SystemDictDomainService {
    @Resource
    private SystemDictMapper systemDictMapper;
    @Resource
    private SystemDictItemMapper systemDictItemMapper;

    @Override
    public List<SystemDictBO> listAllSystemDict() {
        LambdaQueryWrapper<SystemDictDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemDictDO::getDeleteFlag, Boolean.FALSE);
        lambdaQueryWrapper.eq(SystemDictDO::getDataStatus, DateStatusEnum.EnableEnum.ENABLE.getCode());
        List<SystemDictDO> systemDictDOList = systemDictMapper.selectList(lambdaQueryWrapper);
        return SystemDictMapping.INSTANCE.toSystemDictDO(systemDictDOList);
    }

    @Override
    public List<SystemDictItemBO> listAllSystemDictItem() {
        LambdaQueryWrapper<SystemDictItemDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemDictItemDO::getDeleteFlag, Boolean.FALSE);
        lambdaQueryWrapper.eq(SystemDictItemDO::getDataStatus, DateStatusEnum.EnableEnum.ENABLE.getCode());
        List<SystemDictItemDO> systemDictItemDOList = systemDictItemMapper.selectList(lambdaQueryWrapper);
        return SystemDictMapping.INSTANCE.toSystemDictItemBO(systemDictItemDOList);
    }
}