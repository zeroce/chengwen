package cn.zeroce.design.chengwen.wx.mapper;

import java.util.List;

/**
 * Extra Post ImageUrlsMapper
 */
public interface ExtraPostImageUrlsMapper {

    List<String> selectUrlsByPostId(Integer postId);

}
