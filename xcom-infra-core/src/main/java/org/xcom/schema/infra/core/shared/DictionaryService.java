package org.xcom.schema.infra.core.shared;

import org.xcom.schema.infra.core.model.response.SystemDictModel;

import java.util.Map;

/**
 * 字典服务
 *
 * @author xcom
 * @date 2025/9/6
 */

public interface DictionaryService {
    /**
     * 加载全量的数据字典
     *
     * @param hasForced
     * @return
     */
    Map<String, SystemDictModel.SystemDictRespBO> loadAllDictionary(Boolean hasForced);
}
