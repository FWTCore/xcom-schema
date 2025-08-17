package org.xcom.schema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.enums.DateStatusEnum;
import org.xcom.schema.core.model.EntityBaseDO;

/**
 * 系统字典;system_dict数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_dict")
public class SystemDictDO extends EntityBaseDO {

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