package cn.zeroce.design.chengwen.entity;

public class PostWithTag {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_with_tag.id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_with_tag.tag_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Integer tagId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_with_tag.post_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Integer postId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_with_tag.id
     *
     * @return the value of post_with_tag.id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_with_tag.id
     *
     * @param id the value for post_with_tag.id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_with_tag.tag_id
     *
     * @return the value of post_with_tag.tag_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_with_tag.tag_id
     *
     * @param tagId the value for post_with_tag.tag_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_with_tag.post_id
     *
     * @return the value of post_with_tag.post_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_with_tag.post_id
     *
     * @param postId the value for post_with_tag.post_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}