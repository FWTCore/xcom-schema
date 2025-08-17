package org.xcom.shcema.infra.core.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.xcom.shcema.infra.core.dal.entity.SystemMenuDO;
import org.xcom.shcema.infra.core.model.LoginAccountModel;

import java.util.List;

/**
 * 系统菜单 mapping
 *
 * @author xcom
 * @date 2025/8/17
 */

@Mapper
public interface SystemMenuMapping {
    SystemMenuMapping INSTANCE = Mappers.getMapper(SystemMenuMapping.class);

    /**
     * SystemMenuDO to LoginUserAuthRespBO
     *
     * @param doList
     * @return
     */
    @Mappings({ @Mapping(target = "menuId", source = "id") })
    List<LoginAccountModel.LoginUserAuthRespBO> toLoginUserMenuInfraRespDOList(List<SystemMenuDO> doList);
}
