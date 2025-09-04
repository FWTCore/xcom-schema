package org.xcom.schema.infra.starter.common.utils;

import com.fasterxml.jackson.core.JsonStreamContext;
import org.apache.commons.lang3.StringUtils;

/**
 * 字段转换器 工具类
 *
 * @author xcom
 * @date 2024/8/8
 */

public class ConverterUtil {

    /**
     * 获取 JsonStreamContext 的名称
     * @param context
     * @return
     */
    public static String getCurrentName(JsonStreamContext context) {
        String currentName = context.getCurrentName();
        if (StringUtils.isBlank(currentName)) {
            return getCurrentName(context.getParent());
        }
        return currentName;
    }

}
