package cn.zeroce.design.chengwen.dto.reqBody;

import cn.zeroce.design.chengwen.entity.Users;

import java.util.List;

/**
 * @author: zeroce
 * @date 20.5.24 21:47
 */
public class ArticleDTO {
    private String status;
    private String title;
    private String content;
    private String contentShort;
    private List<String> imageUri;
    private Integer id;
//    private List<String> platforms;
    private Boolean commentDisabled;
    private Integer importance;
    private String authorName;
    private Users author;

    public ArticleDTO() { }

    public ArticleDTO(String status, String title, String content, String contentShort, List<String> imageUri, Integer id, Boolean commentDisabled, Integer importance, String authorName, Users author) {
        this.status = status;
        this.title = title;
        this.content = content;
        this.contentShort = contentShort;
        this.imageUri = imageUri;
        this.id = id;
        this.commentDisabled = commentDisabled;
        this.importance = importance;
        this.authorName = authorName;
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentShort() {
        return contentShort;
    }

    public void setContentShort(String contentShort) {
        this.contentShort = contentShort;
    }

    public List<String> getImageUri() {
        return imageUri;
    }

    public void setImageUri(List<String> imageUri) {
        this.imageUri = imageUri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCommentDisabled() {
        return commentDisabled;
    }

    public void setCommentDisabled(Boolean commentDisabled) {
        this.commentDisabled = commentDisabled;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", contentShort='" + contentShort + '\'' +
                ", imageUri=" + imageUri +
                ", id=" + id +
                ", commentDisabled=" + commentDisabled +
                ", importance=" + importance +
                ", authorName='" + authorName + '\'' +
                ", author=" + author +
                '}';
    }
}
