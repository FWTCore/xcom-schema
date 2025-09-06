package org.xcom.schema.infra.core.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xcom.schema.infra.core.model.response.LoginAccountModel;
import org.xcom.schema.infra.core.dal.entity.SystemRoleMenuDO;

import java.util.List;

/**
 * 系统角色菜单;(system_role_menu)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemRoleMenuMapper extends BaseMapper<SystemRoleMenuDO>{

    /**
     * 获取角色菜单
     *
     * @param roleIds
     * @return
     */
    List<LoginAccountModel.LoginUserAuthRespBO> listSystemUserMenuByRoleIds(@Param("roleIds") List<Long> roleIds);
}