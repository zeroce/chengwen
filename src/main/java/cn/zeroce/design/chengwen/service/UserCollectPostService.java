package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.entity.UserCollectPost;

import java.util.List;

/**
 * @author: zeroce
 * @date 20.5.7 16:08
 */
public interface UserCollectPostService {
    /**
     * 按 collectorId | collectedPostId 获取列表
     * @param collectorId
     * @param collectedPostId
     * @return
     */
    List<UserCollectPost> getList(Integer collectorId, Integer collectedPostId);
}
