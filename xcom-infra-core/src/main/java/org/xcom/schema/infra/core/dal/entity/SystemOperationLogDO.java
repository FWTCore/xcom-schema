package org.xcom.schema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.schema.core.model.AbstractEntityBaseDO;

/**
 * 系统操作日志;system_operation_log数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_operation_log")
public class SystemOperationLogDO extends AbstractEntityBaseDO {

    /**
     * 系统业务,;
     */
    private String business;

    /**
     * 操作内容,;
     */
    private String operationContent;
}