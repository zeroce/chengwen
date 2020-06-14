package cn.zeroce.design.chengwen.wx.dto.respData;

import java.util.Date;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.24 11:12
 */
public class WxPostRespDTO {
    private Integer id;

    private String postTitle;

    private Date createTime;

    private Date modifiedTime;

    private Integer userId;

    private String postReviewer;

    private String postContentShort;

    private Float forecast;

    private Integer importance;

    private String type;

    private String status;

    private Boolean commentDisabled;

    private Integer pageviews;

    private String platforms;

    private String userOpenid;

    private String userNickname;

    private String postContent;

    private List<String> imageUrls;

    public WxPostRespDTO() { }

    public WxPostRespDTO(Integer id, String postTitle, Date createTime, Date modifiedTime, Integer userId, String postReviewer, String postContentShort, Float forecast, Integer importance, String type, String status, Boolean commentDisabled, Integer pageviews, String platforms, String userOpenid, String userNickname, String postContent, List<String> imageUrls) {
        this.id = id;
        this.postTitle = postTitle;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.userId = userId;
        this.postReviewer = postReviewer;
        this.postContentShort = postContentShort;
        this.forecast = forecast;
        this.importance = importance;
        this.type = type;
        this.status = status;
        this.commentDisabled = commentDisabled;
        this.pageviews = pageviews;
        this.platforms = platforms;
        this.userOpenid = userOpenid;
        this.userNickname = userNickname;
        this.postContent = postContent;
        this.imageUrls = imageUrls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostReviewer() {
        return postReviewer;
    }

    public void setPostReviewer(String postReviewer) {
        this.postReviewer = postReviewer;
    }

    public String getPostContentShort() {
        return postContentShort;
    }

    public void setPostContentShort(String postContentShort) {
        this.postContentShort = postContentShort;
    }

    public Float getForecast() {
        return forecast;
    }

    public void setForecast(Float forecast) {
        this.forecast = forecast;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCommentDisabled() {
        return commentDisabled;
    }

    public void setCommentDisabled(Boolean commentDisabled) {
        this.commentDisabled = commentDisabled;
    }

    public Integer getPageviews() {
        return pageviews;
    }

    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
