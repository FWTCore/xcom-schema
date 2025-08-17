package org.xcom.shcema.infra.starter.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.xcom.shcema.core.constant.RequestConstant;

/**
 * 用户token提取工具
 *
 * @author xcom
 * @date 2024/8/11
 */

public class TokenUtil {
    /**
     * 从请求中提取token
     *
     * @param request 请求
     * @return token
     */
    public static String extractToken(HttpServletRequest request) {
        String headerToken = extractFromHeader(request);
        if (StringUtils.isNotBlank(headerToken)) {
            return headerToken;
        }
        String bearerToken = extractFromBasicAuthHeader(request);
        if (StringUtils.isNotBlank(bearerToken)) {
            return bearerToken;
        }
        return null;
    }

    /**
     * 从自定义header中提取
     */
    private static String extractFromHeader(HttpServletRequest request) {
        return request.getHeader(RequestConstant.HEADER_ACCESS_TOKEN);
    }

    /**
     * 从Basic Auth协议header中提取
     */
    private static String extractFromBasicAuthHeader(HttpServletRequest request) {
        String authToken = request.getHeader(RequestConstant.AUTHORIZATION);
        if (StringUtils.isNotBlank(authToken)) {
            authToken = StringUtils.substringAfter(authToken, "Bearer ");
        }
        return authToken;
    }

}
