package cn.zeroce.design.chengwen.wx.mapper;

import cn.zeroce.design.chengwen.wx.entity.PostImageUrls;
import cn.zeroce.design.chengwen.wx.entity.PostImageUrlsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PostImageUrlsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    long countByExample(PostImageUrlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    int deleteByExample(PostImageUrlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    int insert(PostImageUrls record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    int insertSelective(PostImageUrls record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    List<PostImageUrls> selectByExampleWithRowbounds(PostImageUrlsExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    List<PostImageUrls> selectByExample(PostImageUrlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    PostImageUrls selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    int updateByExampleSelective(@Param("record") PostImageUrls record, @Param("example") PostImageUrlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    int updateByExample(@Param("record") PostImageUrls record, @Param("example") PostImageUrlsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    int updateByPrimaryKeySelective(PostImageUrls record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_image_urls
     *
     * @mbg.generated Fri Apr 24 11:09:11 CST 2020
     */
    int updateByPrimaryKey(PostImageUrls record);
}