package cn.zeroce.design.chengwen.wx.service;

/**
 * @author: zeroce
 * @date 20.4.25 17:08
 */
public interface WxUserPostService {
    /**
     * 按 userId && postId 验证是否存在记录
     * @param id
     * @param postId
     * @return
     */
    Boolean checkByUserIdPostId(Integer id, Integer postId);
}
