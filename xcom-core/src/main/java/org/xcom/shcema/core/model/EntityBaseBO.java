package org.xcom.shcema.core.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基础模型 BO
 *
 * @author xcom
 * @date 2024/8/9
 */

@Data
public class EntityBaseBO implements Serializable {


    private static final long serialVersionUID = -7059884802225152123L;


    /**
     * 自动增长主键
     */
    private Long id;
    /**
     * 逻辑删除
     */
    private Boolean deleteFlag;


    // === 创建信息

    /**
     * 创建者ID
     */
    private Long createdById;
    /**
     * 创建者名称
     */
    private String createdByName;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    // === 更新信息

    /**
     * 最后更新者ID
     */
    private Long updatedById;

    /**
     * 最后更新者名称
     */
    private String updatedByName;
    /**
     * 最后更新时间
     */
    private LocalDateTime updatedTime;
}
