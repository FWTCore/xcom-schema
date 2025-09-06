package org.xcom.schema.infra.core.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xcom.schema.infra.core.dal.entity.SystemDictDO;
import org.xcom.schema.infra.core.dal.entity.SystemDictItemDO;
import org.xcom.schema.infra.core.model.SystemDictBO;
import org.xcom.schema.infra.core.model.SystemDictItemBO;
import org.xcom.schema.infra.core.model.response.SystemDictModel;

import java.util.List;

/**
 * SystemDict Mapping
 *
 * @author xcom
 * @date 2025/9/6
 */

@Mapper
public interface SystemDictMapping {
    SystemDictMapping INSTANCE = Mappers.getMapper(SystemDictMapping.class);

    /**
     * SystemDictDO to SystemDictBO
     * @param systemDictDO
     * @return
     */
    SystemDictBO toSystemDictDO(SystemDictDO systemDictDO);

    /**
     * SystemDictDO to SystemDictBO
     * @param systemDictDO
     * @return
     */
    List<SystemDictBO> toSystemDictDO(List<SystemDictDO> systemDictDO);

    /**
     *SystemDictItemDO to SystemDictItemBO
     * @param systemDictItemDO
     * @return
     */
    SystemDictItemBO toSystemDictItemBO(SystemDictItemDO systemDictItemDO);

    /**
     *SystemDictItemDO to SystemDictItemBO
     * @param systemDictItemDO
     * @return
     */
    List<SystemDictItemBO> toSystemDictItemBO(List<SystemDictItemDO> systemDictItemDO);

    /**
     * SystemDictItemBO to SystemDictRespBO
     * @param dictBO
     * @return
     */
    SystemDictModel.SystemDictRespBO toSystemDictRespBO(SystemDictBO dictBO);

    /**
     * SystemDictItemBO to SystemDictModel.SystemDictItemRespBO
     * @param itemBO
     * @return
     */
    SystemDictModel.SystemDictItemRespBO toSystemDictItemRespBO(SystemDictItemBO itemBO);

    /**
     * SystemDictItemBO to SystemDictModel.SystemDictItemRespBO
     * @param itemBO
     * @return
     */
    List<SystemDictModel.SystemDictItemRespBO> toSystemDictItemRespBO(List<SystemDictItemBO> itemBO);
}
