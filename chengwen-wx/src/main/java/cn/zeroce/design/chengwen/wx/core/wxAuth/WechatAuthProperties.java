package cn.zeroce.design.chengwen.wx.core.wxAuth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: zeroce
 * @date 20.4.16 18:04
 */
@Component
@ConfigurationProperties(prefix = "auth.wechat")
public class WechatAuthProperties {
    private String sessionHost;
    private String appId;
    private String secret;
    private String grantType;

    public WechatAuthProperties() {
    }

    public WechatAuthProperties(String sessionHost, String appId, String secret, String grantType) {
        this.sessionHost = sessionHost;
        this.appId = appId;
        this.secret = secret;
        this.grantType = grantType;
    }

    public String getSessionHost() {
        return sessionHost;
    }

    public void setSessionHost(String sessionHost) {
        this.sessionHost = sessionHost;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
