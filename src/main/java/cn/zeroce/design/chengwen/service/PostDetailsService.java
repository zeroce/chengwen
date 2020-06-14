package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.entity.PostDetails;

import java.util.List;

/**
 * @author: zeroce
 * @date 20.5.7 18:20
 */
public interface PostDetailsService {
    /**
     * 按 postId 查询列表
     * @param postId
     * @return
     */
    List<PostDetails> getList(Integer postId);
}
