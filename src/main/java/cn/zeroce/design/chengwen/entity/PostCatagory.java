package cn.zeroce.design.chengwen.entity;

public class PostCatagory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_catagory.id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_catagory.catagory_name
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private String catagoryName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_catagory.post_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    private Integer postId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_catagory.id
     *
     * @return the value of post_catagory.id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_catagory.id
     *
     * @param id the value for post_catagory.id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_catagory.catagory_name
     *
     * @return the value of post_catagory.catagory_name
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public String getCatagoryName() {
        return catagoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_catagory.catagory_name
     *
     * @param catagoryName the value for post_catagory.catagory_name
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName == null ? null : catagoryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_catagory.post_id
     *
     * @return the value of post_catagory.post_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_catagory.post_id
     *
     * @param postId the value for post_catagory.post_id
     *
     * @mbg.generated Fri Mar 13 17:47:14 CST 2020
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}