package org.xcom.schema.infra.core.service;

import org.xcom.schema.infra.core.model.SystemDictBO;
import org.xcom.schema.infra.core.model.SystemDictItemBO;

import java.util.List;

/**
 * 系统字典;(system_dict)表 领域
 * @author :xcom
 * @date : 2025-8-17
 */
public interface SystemDictDomainService {

    /**
     * 获取全部系统字典
     * @return
     */
    List<SystemDictBO> listAllSystemDict();

    /**
     * 获取全部系统字典项
     * @return
     */
    List<SystemDictItemBO> listAllSystemDictItem();

}