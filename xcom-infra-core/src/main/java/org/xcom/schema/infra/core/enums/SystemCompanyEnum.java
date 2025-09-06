package org.xcom.schema.infra.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.xcom.schema.core.enums.IEnum;

/**
 * 系统公司枚举 集合
 *
 * @author xcom
 * @date 2025/9/6
 */

@Getter
@RequiredArgsConstructor
public class SystemCompanyEnum {

    /**
     * 系统公司类型
     *
     * @author xcom
     * @date 2023/6/5
     */
    @Getter
    @RequiredArgsConstructor
    public enum CompanyTypeEnum implements IEnum {

        /**
         * 目录
         */
        manager(1, "管理者"),
        /**
         * 页面
         */
        Customer(2, "客户"),

        ;

        private final Integer code;
        private final String  desc;

    }

}
