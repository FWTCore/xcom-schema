package org.xcom.schema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.enums.DateStatusEnum;
import org.xcom.schema.core.model.AbstractEntityBaseDO;

/**
 * 系统字典项;system_dict_item数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_dict_item")
public class SystemDictItemDO extends AbstractEntityBaseDO {

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