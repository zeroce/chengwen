package cn.zeroce.design.chengwen.wx.dto.respData;

import cn.zeroce.design.chengwen.wx.entity.Comments;
import cn.zeroce.design.chengwen.wx.entity.Post;
import cn.zeroce.design.chengwen.wx.entity.PostDetails;

import java.util.List;

/**
 * post detials response body
 * @author: zeroce
 * @date 20.4.25 13:54
 */
public class WxGetPostDetailsDTO {
    // 文章内容
    private Post post;
    // 文章相关
    private PostDetails postDetails;
    // 文章内容图片链接
    private List<String> imageUrls;

    private String author;
    private Boolean viewerIsAdmin;
    private Boolean viewerIsAuthor;
    private Boolean authorHasCollect;

    // 评论集合
    private List<WxCommentsDTO> comments;

    public WxGetPostDetailsDTO() { }

    public WxGetPostDetailsDTO(Post post, PostDetails postDetails, List<String> imageUrls, String author, Boolean viewerIsAdmin, Boolean viewerIsAuthor, Boolean authorHasCollect, List<WxCommentsDTO> comments) {
        this.post = post;
        this.postDetails = postDetails;
        this.imageUrls = imageUrls;
        this.author = author;
        this.viewerIsAdmin = viewerIsAdmin;
        this.viewerIsAuthor = viewerIsAuthor;
        this.authorHasCollect = authorHasCollect;
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostDetails getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(PostDetails postDetails) {
        this.postDetails = postDetails;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getViewerIsAdmin() {
        return viewerIsAdmin;
    }

    public void setViewerIsAdmin(Boolean viewerIsAdmin) {
        this.viewerIsAdmin = viewerIsAdmin;
    }

    public Boolean getViewerIsAuthor() {
        return viewerIsAuthor;
    }

    public void setViewerIsAuthor(Boolean viewerIsAuthor) {
        this.viewerIsAuthor = viewerIsAuthor;
    }

    public Boolean getAuthorHasCollect() {
        return authorHasCollect;
    }

    public void setAuthorHasCollect(Boolean authorHasCollect) {
        this.authorHasCollect = authorHasCollect;
    }

    public List<WxCommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List<WxCommentsDTO> comments) {
        this.comments = comments;
    }
}
