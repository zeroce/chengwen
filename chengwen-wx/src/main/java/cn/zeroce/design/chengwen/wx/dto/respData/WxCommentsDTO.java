package cn.zeroce.design.chengwen.wx.dto.respData;

import cn.zeroce.design.chengwen.wx.entity.Comments;
import cn.zeroce.design.chengwen.wx.entity.Users;

/**
 * @author: zeroce
 * @date 20.4.26 17:15
 */
public class WxCommentsDTO {
    private Integer id;
    // 查看者是否是拥有者
    private Boolean isMine;
    private Boolean isAdmin;
    private Comments comments;
    private Users users;

    // 点赞数
    private Integer likeCount;

    public WxCommentsDTO() { }

    public WxCommentsDTO(Integer id, Boolean isMine, Boolean isAdmin, Comments comments, Users users, Integer likeCount) {
        this.id = id;
        this.isMine = isMine;
        this.isAdmin = isAdmin;
        this.comments = comments;
        this.users = users;
        this.likeCount = likeCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getMine() {
        return isMine;
    }

    public void setMine(Boolean mine) {
        isMine = mine;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
