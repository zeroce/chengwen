package cn.zeroce.design.chengwen.entity;

import java.util.Date;

public class Comments {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.create_time
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.comment_user_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Integer commentUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.comment_post_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Integer commentPostId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.comment_superior_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Integer commentSuperiorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comments.comment_content_text
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private String commentContentText;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.id
     *
     * @return the value of comments.id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.id
     *
     * @param id the value for comments.id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.create_time
     *
     * @return the value of comments.create_time
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.create_time
     *
     * @param createTime the value for comments.create_time
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.comment_user_id
     *
     * @return the value of comments.comment_user_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Integer getCommentUserId() {
        return commentUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.comment_user_id
     *
     * @param commentUserId the value for comments.comment_user_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.comment_post_id
     *
     * @return the value of comments.comment_post_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Integer getCommentPostId() {
        return commentPostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.comment_post_id
     *
     * @param commentPostId the value for comments.comment_post_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setCommentPostId(Integer commentPostId) {
        this.commentPostId = commentPostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.comment_superior_id
     *
     * @return the value of comments.comment_superior_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Integer getCommentSuperiorId() {
        return commentSuperiorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.comment_superior_id
     *
     * @param commentSuperiorId the value for comments.comment_superior_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setCommentSuperiorId(Integer commentSuperiorId) {
        this.commentSuperiorId = commentSuperiorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comments.comment_content_text
     *
     * @return the value of comments.comment_content_text
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public String getCommentContentText() {
        return commentContentText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comments.comment_content_text
     *
     * @param commentContentText the value for comments.comment_content_text
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setCommentContentText(String commentContentText) {
        this.commentContentText = commentContentText == null ? null : commentContentText.trim();
    }
}