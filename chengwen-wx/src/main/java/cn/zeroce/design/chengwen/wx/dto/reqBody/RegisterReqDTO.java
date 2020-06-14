package cn.zeroce.design.chengwen.wx.dto.reqBody;

/**
 *
 * @author: zeroce
 * @date 20.4.18 21:00
 */
public class RegisterReqDTO {
    private String code;
    private String uname;
    private String password;
    private Integer appVersion;
    private String nickName;
    private String avatarUrl;
    private Integer gender;
    private String sessionId;

    public RegisterReqDTO() { }

    public RegisterReqDTO(String code, String uname, String password, Integer appVersion, String nickName, String avatarUrl, Integer gender, String sessionId) {
        this.code = code;
        this.uname = uname;
        this.password = password;
        this.appVersion = appVersion;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.sessionId = sessionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
