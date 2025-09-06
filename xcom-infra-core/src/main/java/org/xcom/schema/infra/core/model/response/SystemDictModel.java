package org.xcom.schema.infra.core.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 系统字典
 *
 * @author xcom
 * @date 2024/12/1
 */

public class SystemDictModel implements Serializable {

    /**
     * 登录账户模型
     */
    @Data
    public static class SystemDictRespBO implements Serializable {

        private static final long          serialVersionUID = -7059884802225152123L;

        /**
         * 自动增长主键
         */
        private Long                       id;

        /**
         * 字典名称,;
         */
        private String                     dictName;

        /**
         * 字典编码,;
         */
        private String                     dictCode;

        /**
         * 描述,;
         */
        private String                     description;
        /**
         * 字典项列
         */
        private List<SystemDictItemRespBO> systemDictItemList;

    }

    @Data
    public static class SystemDictItemRespBO implements Serializable {

        private static final long serialVersionUID = -7059884802225152123L;

        /**
         * 自动增长主键
         */
        private Long              id;

        /**
         * 字典编码,;
         */
        private String            dictCode;

        /**
         * 字典项编码,;
         */
        private String            itemCode;

        /**
         * 字典文本,;
         */
        private String            itemText;

        /**
         * 描述,;
         */
        private String            description;

        /**
         * 排序,;
         */
        private Short             sortIndex;
    }

}
