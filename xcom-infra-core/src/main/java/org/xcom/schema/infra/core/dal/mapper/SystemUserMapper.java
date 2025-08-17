package org.xcom.schema.infra.core.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xcom.schema.infra.core.model.LoginAccountModel;
import org.xcom.schema.infra.core.dal.entity.SystemUserDO;

/**
 * 系统用户;(system_user)表数据库访问层
 * @author :xcom
 * @date : 2025-8-17
 */
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUserDO>{
    /**
     * 通过用户id查询登录账户信息
     *
     * @param userId
     * @return
     */
    LoginAccountModel.LoginUserRespBO getLoginUserByUserId(@Param("userId") Long userId);

}
