package org.xcom.shcema.infra.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xcom.shcema.core.constant.RedisConstant;
import org.xcom.shcema.core.enums.DateStatusEnum;
import org.xcom.shcema.core.redis.ActionRedisUtil;
import org.xcom.shcema.core.redis.KeyRedisUtil;
import org.xcom.shcema.infra.core.convert.SystemMenuMapping;
import org.xcom.shcema.infra.core.dal.entity.SystemMenuDO;
import org.xcom.shcema.infra.core.dal.mapper.SystemMenuMapper;
import org.xcom.shcema.infra.core.model.LoginAccountModel;
import org.xcom.shcema.infra.core.service.SystemMenuDomainService;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单;(system_menu)表服务接口实现类
 * @author : xcom
 * @date : 2025-8-17
 */
@Slf4j
@Service
@DS("infra")
public class SystemMenuDomainServiceImpl implements SystemMenuDomainService {
    @Resource
    private SystemMenuMapper systemMenuMapper;

    @Override
    public List<LoginAccountModel.LoginUserAuthRespBO> listAllMenu(Boolean hasForced) {
        List<LoginAccountModel.LoginUserAuthRespBO> resultData = new ArrayList<>();
        // 非强制重获取，优先走缓存
        String redisKey = KeyRedisUtil.getGlobalRedisKey(RedisConstant.REDIS_INFRA_MENU_PREFIX);
        if (BooleanUtil.isFalse(hasForced)) {
            Object redisData = ActionRedisUtil.getRedisData(redisKey);
            if (ObjectUtil.isNotNull(redisData) && redisData instanceof List<?>) {
                resultData = (List<LoginAccountModel.LoginUserAuthRespBO>) redisData;
                return resultData;
            }
        }
        LambdaQueryWrapper<SystemMenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemMenuDO::getDeleteFlag, Boolean.FALSE);
        lambdaQueryWrapper.eq(SystemMenuDO::getDataStatus, DateStatusEnum.EnableEnum.ENABLE.getCode());
        List<SystemMenuDO> menuDoList = systemMenuMapper.selectList(lambdaQueryWrapper);

        if (CollectionUtil.isNotEmpty(menuDoList)) {
            resultData = SystemMenuMapping.INSTANCE.toLoginUserMenuInfraRespDOList(menuDoList);
            ActionRedisUtil.cacheRedisData(redisKey, resultData);
        }
        return resultData;
    }
}