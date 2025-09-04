package org.xcom.schema.core.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * 基础配置
 *
 * @author xcom
 * @date 2024/8/11
 */
@Data
public class InfraProperties {

    /**
     * 匿名访问
     * 不签名，不授权
     */
    @Value("${xcom.infra.anonymous-urls: }")
    private String anonymousUrls;

    public List<String> getAnonymousUrlList() {
        return splitUrlConfig(anonymousUrls);
    }

    // region 签名

    /**
     * 检测是否启用，默认启用
     */
    @Value("${xcom.infra.signature.enable:0}")
    private Boolean signatureEnable;

    /**
     * 签名时效性，默认1分
     */
    @Value("${xcom.infra.signature.timeliness:60}")
    private long    signatureTimeliness;

    /**
     * 签名密钥
     */
    @Value("${xcom.infra.signature.secret:633a23e5b6b0cc5bcde8a83ce71b30ad}")
    private String  apiSecret;

    /**
     * 不验证签名的url
     */
    @Value("${xcom.infra.signature.skip-urls: }")
    private String  noSignUrls;

    public List<String> getNoSignUrlList() {
        return splitUrlConfig(noSignUrls);
    }

    // endregion

    // region 认证

    /**
     * 检测是否启用，默认启用
     */
    @Value("${xcom.infra.login.enable:1}")
    private Boolean loginEnable;
    /**
     * 不登录，不鉴权 url
     */
    @Value("${xcom.infra.no-login-urls: }")
    private String  noLoginUrls;

    /**
     * 检测是否启用，默认启用
     */
    @Value("${xcom.infra.auth.enable:1}")
    private Boolean authEnable;

    /**
     * 仅登录，不鉴权 url
     */
    @Value("${xcom.infra.no-auth-urls: }")
    private String  noAuthUrls;

    public List<String> getNoLoginUrlList() {
        return splitUrlConfig(noLoginUrls);
    }

    public List<String> getNoAuthUrlList() {
        return splitUrlConfig(noAuthUrls);
    }

    // endregion

    /**
     * 拆分url
     *
     * @param splitUrl
     * @return
     */
    private List<String> splitUrlConfig(String splitUrl) {
        if (isEmpty(splitUrl)) {
            return new ArrayList<>();
        }
        String[] urls = splitUrl.split(",");
        List<String> urlList = new ArrayList<>();
        for (String url : urls) {
            if (isEmpty(url)) {
                continue;
            }
            urlList.add(url.trim());
        }
        return urlList;
    }
}
