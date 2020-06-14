package cn.zeroce.design.chengwen.wx.dto.reqBody;

/**
 * @author: zeroce
 * @date 20.4.26 11:04
 */
public class WxCommentsCreateReqDTO {
    private String hotqinsessionid;
    private String content;
    private String username;

    private String avatarUrl;
    private Integer appVersion;

    public WxCommentsCreateReqDTO() { }

    public WxCommentsCreateReqDTO(String hotqinsessionid, String content, String username, String avatarUrl, Integer appVersion) {
        this.hotqinsessionid = hotqinsessionid;
        this.content = content;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.appVersion = appVersion;
    }

    public String getHotqinsessionid() {
        return hotqinsessionid;
    }

    public void setHotqinsessionid(String hotqinsessionid) {
        this.hotqinsessionid = hotqinsessionid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }
}
