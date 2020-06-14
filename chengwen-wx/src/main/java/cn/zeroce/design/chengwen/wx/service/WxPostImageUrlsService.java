package cn.zeroce.design.chengwen.wx.service;

import java.util.List;

public interface WxPostImageUrlsService {
    /**
     * 批量保存 URL
     * @param images
     * @param id
     */
    void saveImageUrlList(List<String> images, Integer id);

    /**
     * 按 post_id 查询 url list
     * @param postId
     * @return
     */
    List<String> findUrlsByPostId(Integer postId);
}
