package org.xcom.schema.infra.starter.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xcom.schema.infra.core.model.request.LoginReqBO;
import org.xcom.schema.infra.starter.system.request.LoginRequest;

/**
 * Account Mapping
 *
 * @author xcom
 * @date 2025/9/6
 */

@Mapper
public interface AccountMapping {
    AccountMapping INSTANCE = Mappers.getMapper(AccountMapping.class);

    /**
     * LoginRequest to LoginReqBO
     * @param request
     * @return
     */
    LoginReqBO toLoginReqBO(LoginRequest request);
}
