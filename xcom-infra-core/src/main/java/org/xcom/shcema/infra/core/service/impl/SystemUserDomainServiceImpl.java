package org.xcom.shcema.infra.core.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.infra.core.dal.mapper.SystemUserMapper;
import org.xcom.shcema.infra.core.model.LoginAccountModel;
import org.xcom.shcema.infra.core.service.SystemUserDomainService;

/**
 * 系统用户;(system_user)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemUserDomainServiceImpl implements SystemUserDomainService {
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public LoginAccountModel.LoginUserRespBO getLoginUserByUserId(Long userId) {
        if (ObjectUtil.isNull(userId)) {
            return null;
        }
        return systemUserMapper.getLoginUserByUserId(userId);
    }
}