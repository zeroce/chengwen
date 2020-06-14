package cn.zeroce.design.chengwen.wx.dto.respData;

import cn.zeroce.design.chengwen.wx.entity.Users;

/**
 * @author: zeroce
 * @date 20.4.18 21:20
 */
public class RegisterRespDTO {
    private String sessionId;
    private String userId;
    private String photo;
    private Boolean isAdmin;
    private Boolean hasRegist;

    private Users userInfo;

    public RegisterRespDTO() { }

    public RegisterRespDTO(String sessionId, String userId, String photo, Boolean isAdmin, Boolean hasRegist, Users userInfo) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.photo = photo;
        this.isAdmin = isAdmin;
        this.hasRegist = hasRegist;
        this.userInfo = userInfo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getHasRegist() {
        return hasRegist;
    }

    public void setHasRegist(Boolean hasRegist) {
        this.hasRegist = hasRegist;
    }

    public Users getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Users userInfo) {
        this.userInfo = userInfo;
    }
}
