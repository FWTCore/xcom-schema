package org.xcom.schema.infra.core.shared.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.xcom.schema.core.constant.RedisConstant;
import org.xcom.schema.core.redis.ActionRedisUtil;
import org.xcom.schema.core.redis.KeyRedisUtil;
import org.xcom.schema.infra.core.convert.SystemDictMapping;
import org.xcom.schema.infra.core.model.SystemDictBO;
import org.xcom.schema.infra.core.model.SystemDictItemBO;
import org.xcom.schema.infra.core.model.response.SystemDictModel;
import org.xcom.schema.infra.core.service.SystemDictDomainService;
import org.xcom.schema.infra.core.shared.DictionaryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典服务
 *
 * @author xcom
 * @date 2025/9/6
 */

@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private SystemDictDomainService systemDictDomainService;

    @Override
    public Map<String, SystemDictModel.SystemDictRespBO> loadAllDictionary(Boolean hasForced) {
        Map<String, SystemDictModel.SystemDictRespBO> resultData = new HashMap<>();
        Map<Object, Object> redisData = new HashMap<>();

        // 非强制重获取，优先走缓存
        String redisKey = KeyRedisUtil.getGlobalRedisKey(RedisConstant.REDIS_INFRA_DICT_PREFIX);
        if (BooleanUtil.isFalse(hasForced)) {
            redisData = ActionRedisUtil.getRedisMapData(redisKey);
            if (ObjectUtils.isNotEmpty(redisData) && redisData.size() > 0) {
                for (Map.Entry<Object, Object> item : redisData.entrySet()) {
                    resultData.put(item.getKey().toString(), (SystemDictModel.SystemDictRespBO) item.getValue());
                }
                return resultData;
            }
            resultData = new HashMap<>();
        }
        List<SystemDictBO> dictRespData = systemDictDomainService.listAllSystemDict();
        if (CollectionUtil.isEmpty(dictRespData)) {
            ActionRedisUtil.removeRedisData(redisKey);
            return new HashMap<>(0);
        }
        List<SystemDictItemBO> systemDictItemS = systemDictDomainService.listAllSystemDictItem();
        if (CollectionUtil.isEmpty(systemDictItemS)) {
            ActionRedisUtil.removeRedisData(redisKey);
            return new HashMap<>(0);
        }
        Map<String, SystemDictModel.SystemDictRespBO> finalResultData = resultData;
        Map<Object, Object> finalRedisData = redisData;
        dictRespData.forEach(dict -> {
            SystemDictModel.SystemDictRespBO tempDict = SystemDictMapping.INSTANCE.toSystemDictRespBO(dict);
            if (systemDictItemS.stream().anyMatch(e -> e.getDictCode().equals(dict.getDictCode()))) {
                List<SystemDictItemBO> dictItems = systemDictItemS.stream()
                    .filter(e -> e.getDictCode().equals(dict.getDictCode())).collect(Collectors.toList());
                tempDict.setSystemDictItemList(SystemDictMapping.INSTANCE.toSystemDictItemRespBO(dictItems));
            }
            finalResultData.put(tempDict.getDictCode(), tempDict);
            finalRedisData.put(tempDict.getDictCode(), tempDict);
        });
        // 添加缓存
        if (ObjectUtils.isNotEmpty(finalRedisData) && finalRedisData.size() > 0) {
            ActionRedisUtil.cacheRedisMapData(redisKey, finalRedisData);
        }
        return finalResultData;
    }
}
