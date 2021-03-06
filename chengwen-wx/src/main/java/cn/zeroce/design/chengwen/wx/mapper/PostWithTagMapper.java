package cn.zeroce.design.chengwen.wx.mapper;

import cn.zeroce.design.chengwen.wx.entity.PostWithTag;
import cn.zeroce.design.chengwen.wx.entity.PostWithTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PostWithTagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    long countByExample(PostWithTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    int deleteByExample(PostWithTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    int insert(PostWithTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    int insertSelective(PostWithTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    List<PostWithTag> selectByExampleWithRowbounds(PostWithTagExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    List<PostWithTag> selectByExample(PostWithTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    PostWithTag selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    int updateByExampleSelective(@Param("record") PostWithTag record, @Param("example") PostWithTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    int updateByExample(@Param("record") PostWithTag record, @Param("example") PostWithTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    int updateByPrimaryKeySelective(PostWithTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_with_tag
     *
     * @mbg.generated Fri Apr 17 18:10:52 CST 2020
     */
    int updateByPrimaryKey(PostWithTag record);
}