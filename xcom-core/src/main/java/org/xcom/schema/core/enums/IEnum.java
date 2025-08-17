package org.xcom.schema.core.enums;

import java.io.Serializable;

/**
 * 枚举接口
 *
 * @author xcom
 * @date 2024/11/11
 */

public interface IEnum extends Serializable {

    /**
     * 获取枚举名称
     *
     * @return code
     */
    String name();

    /**
     * 获取枚举编码
     *
     * @return desc
     */
    Integer getCode();

    /**
     * 获取枚举信息
     *
     * @return desc
     */
    String getDesc();


}