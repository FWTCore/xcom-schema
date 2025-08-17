package org.xcom.schema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xcom.schema.infra.core.model.LoginAccountModel;
import org.xcom.schema.infra.core.dal.entity.SystemUserRoleDO;

import java.util.List;

/**
 * 系统用户角色;(system_user_role)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemUserRoleMapper extends BaseMapper<SystemUserRoleDO>{

    /**
     * 获取系统用户角色
     *
     * @param userId
     * @return
     */
    List<LoginAccountModel.LoginUserRoleRespBO> listLoginUserRoleByUserId(@Param("userId") Long userId);

}