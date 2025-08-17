package org.xcom.schema.infra.core.service;


import org.xcom.schema.infra.core.model.LoginAccountModel;

import java.util.List;

/**
 * 系统菜单;(system_menu)表 领域
 * @author :xcom
 * @date : 2025-8-17
 */
public interface SystemMenuDomainService{
    /**
     * 获取所有菜单
     *
     * @param hasForced
     * @return
     */
    List<LoginAccountModel.LoginUserAuthRespBO> listAllMenu(Boolean hasForced);
}
