package org.xcom.schema.infra.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.enums.DateStatusEnum;
import org.xcom.schema.core.model.AbstractEntityBaseBO;

/**
 * 系统字典项BO
 *
 * @author xcom
 * @date 2025/9/6
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SystemDictItemBO extends AbstractEntityBaseBO {

    /**
     * 字典编码,;
     */
    private String dictCode;

    /**
     * 字典项编码,;
     */
    private String itemCode;

    /**
     * 字典文本,;
     */
    private String itemText;

    /**
     * 描述,;
     */
    private String description;

    /**
     * 排序,;
     */
    private Short  sortIndex;

    /**
     * 数据状态,;
     * @see DateStatusEnum.EnableEnum
     */
    private Short  dataStatus;
}
