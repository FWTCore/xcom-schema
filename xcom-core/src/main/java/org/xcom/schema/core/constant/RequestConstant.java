package org.xcom.schema.core.constant;

/**
 * 请求常量
 *
 * @author xcom
 * @date 2024/8/9
 */

public class RequestConstant {

    /**
     * 自定义token名字
     */
    public static final String HEADER_ACCESS_TOKEN = "X-Access-Token";
    /**
     * Basic Auth协议header中提取
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * header 中提取 是否测试
     */
    public static final String HEADER_TEST = "X-Test";
    /**
     * header 中提取 是否测试 是测试值
     */
    public static final String HEADER_TEST_VALUE = "1_Y";
    /**
     * header 中提取 随机字符串
     */
    public static final String HEADER_NONCE_STR = "X-NonceStr";
    /**
     * header 中提取 时间戳
     */
    public static final String HEADER_TIMESTAMP = "X-Timestamp";
    /**
     * header 中提取 签名
     */
    public static final String HEADER_SIGNATURE = "X-Signature";
    /**
     * header 中提取 appid
     */
    public static final String HEADER_APPID = "X-AppId";

    // region ip解析
    /**
     * header 中解析出正式ip
     */
    public static final String HEADER_REAL_IP = "X-Real-IP";
    /**
     * header 中解析出正式ip
     */
    public static final String HEADER_FORWARDED_IP = "X-Forwarded-For";
    /**
     * header 中解析出正式ip
     */
    public static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
    /**
     * header 中解析出正式ip
     */
    public static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    /**
     * request 中解析出正式ip
     */
    public static final String REQUEST_REMOTE_ADDR_IP = "request.getRemoteAddr";

    //endregion



    /**
     * 日志链路请求id
     */
    public static final String HEADER_TRACE_LOG_KEY = "t-id";


}