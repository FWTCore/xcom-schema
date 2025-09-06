package org.xcom.schema.infra.core.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xcom.schema.infra.core.dal.entity.SystemUserDO;
import org.xcom.schema.infra.core.model.SystemUserBO;

/**
 * SystemUser Mapping
 *
 * @author xcom
 * @date 2025/9/6
 */

@Mapper
public interface SystemUserMapping {
    SystemUserMapping INSTANCE = Mappers.getMapper(SystemUserMapping.class);

    /**
     * SystemUserDO to SystemUserBO
     * @param systemUserDO
     * @return
     */

    SystemUserBO toSystemUserServiceBO(SystemUserDO systemUserDO);
}
