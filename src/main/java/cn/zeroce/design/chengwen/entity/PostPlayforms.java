package cn.zeroce.design.chengwen.entity;

import java.util.Date;

public class PostPlayforms {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_playforms.id
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_playforms.playform_key
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    private String playformKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_playforms.playform_name
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    private String playformName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_playforms.create_time
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_playforms.modified_time
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    private Date modifiedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_playforms.id
     *
     * @return the value of post_playforms.id
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_playforms.id
     *
     * @param id the value for post_playforms.id
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_playforms.playform_key
     *
     * @return the value of post_playforms.playform_key
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public String getPlayformKey() {
        return playformKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_playforms.playform_key
     *
     * @param playformKey the value for post_playforms.playform_key
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public void setPlayformKey(String playformKey) {
        this.playformKey = playformKey == null ? null : playformKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_playforms.playform_name
     *
     * @return the value of post_playforms.playform_name
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public String getPlayformName() {
        return playformName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_playforms.playform_name
     *
     * @param playformName the value for post_playforms.playform_name
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public void setPlayformName(String playformName) {
        this.playformName = playformName == null ? null : playformName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_playforms.create_time
     *
     * @return the value of post_playforms.create_time
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_playforms.create_time
     *
     * @param createTime the value for post_playforms.create_time
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_playforms.modified_time
     *
     * @return the value of post_playforms.modified_time
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_playforms.modified_time
     *
     * @param modifiedTime the value for post_playforms.modified_time
     *
     * @mbg.generated Tue Apr 14 23:29:45 CST 2020
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}