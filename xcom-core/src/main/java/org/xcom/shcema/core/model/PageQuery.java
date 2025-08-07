package org.xcom.shcema.core.model;

import lombok.Data;
import org.xcom.shcema.core.annotation.EnumValid;
import org.xcom.shcema.core.enums.DBSortEnum;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 分页请求模型
 *
 * @author xcom
 * @date 2024/7/28
 */

@Data
public class PageQuery<T> implements Serializable {

    private static final long serialVersionUID = 2081104351106889132L;

    /**
     * 默认当前页数
     */
    private static final long PAGE_NUM = 1;

    /**
     * 默认每页数量
     */
    private static final long PAGE_SIZE = 10;

    /**
     * 默认排序类型：升序
     */
    private static final int ORDER_BY = DBSortEnum.ASC.getCode();

    /**
     * 当前页数
     */
    private long pageNum = PAGE_NUM;
    /**
     * 每页数量
     */
    private long pageSize = PAGE_SIZE;

    /**
     * 排序
     */
    @Size(max = 5, message = "最多可以输入5个排序字段")
    private @Valid List<QuerySort> sort;

    /**
     * 请求参数对象
     */
    private @Valid T param;


    /**
     * 排序模型
     */
    @Data
    public static class QuerySort implements Serializable {

        private static final long serialVersionUID = 2081104351106889132L;

        /**
         * 排序字段
         */
        private String sortField;

        /**
         * 排序方式
         */
        @EnumValid(enumClass = DBSortEnum.class, message = "输入排序方式不合法")
        private Integer orderBy = ORDER_BY;
    }


}
