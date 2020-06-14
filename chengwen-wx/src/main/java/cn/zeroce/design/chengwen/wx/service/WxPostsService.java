package cn.zeroce.design.chengwen.wx.service;

import cn.zeroce.design.chengwen.wx.dto.reqBody.WxPostCreateReqBody;
import cn.zeroce.design.chengwen.wx.dto.respData.WxGetPostDetailsDTO;
import cn.zeroce.design.chengwen.wx.entity.Post;
import cn.zeroce.design.chengwen.wx.entity.Users;

import java.util.List;

public interface WxPostsService {
    /**
     * 按 page 查询 post
     * @param keyWord
     * @return
     */
    List<Post> findPageList(String keyWord, String userId);

    /**
     * 创建新的 post
     * @param reqBody
     * @param wxUsers
     */
    Post create(WxPostCreateReqBody reqBody, Users wxUsers);

    /**
     * 按 title && user_openid 查询 post
     * @param title
     * @param openid
     * @return
     */
    Post findByTitleUserId(String title, String openid);

    /**
     * 按 postId 查询 post details
     * @param postId
     * @return
     */
    WxGetPostDetailsDTO findByPostId(Integer postId);

    /**
     * 按 postId 查询 post
     * @param postId
     * @return
     */
    WxPostCreateReqBody getByPostId(Integer postId);

    /**
     * 根据 postId 更新 post
     * @param postId
     * @param reqBody
     * @return
     */
    void updateByPostId(Integer postId, WxPostCreateReqBody reqBody);

    /**
     * 根据 postId 删除 post
     * @param postId
     */
    void deleteByPostId(Integer postId);
}
