package org.xcom.schema.infra.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.enums.DateStatusEnum;
import org.xcom.schema.core.model.AbstractEntityBaseBO;

/**
 * 系统字典BO
 *
 * @author xcom
 * @date 2025/9/6
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SystemDictBO extends AbstractEntityBaseBO {
    /**
     * 字典名称,;
     */
    private String dictName;

    /**
     * 字典编码,;
     */
    private String dictCode;

    /**
     * 描述,;
     */
    private String description;

    /**
     * 数据状态,;
     * @see DateStatusEnum.EnableEnum
     */
    private Short  dataStatus;
}
