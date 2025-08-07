package org.xcom.shcema.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回分页数据模型
 *
 * @author xcom
 * @date 2024/7/28
 */

@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 2081104351106889132L;

    /**
     * 页面大小
     */
    private long pageNum;

    /**
     * 页面大小
     */
    private long pageSize;

    /**
     * 返回总数
     */
    private long total;

    /**
     * 分页数量
     */
    private long pages;

    /**
     * 数据列表
     */
    private List<T> records;


}
