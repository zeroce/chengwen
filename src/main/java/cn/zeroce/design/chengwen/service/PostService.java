package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.entity.Post;

import java.util.List;

public interface PostService {
    /**
     * 按 title | username 查找 post
     *
     * @param title
     * @param authorId
     * @return
     */
    List<Post> findByTitleUsername(String title, Integer authorId);

    /**
     * 按 id 查询 post
     * @param id
     * @return
     */
    Post findById(Integer id);

    /**
     * 按 id & title 删除 post
     * @param post
     */
    void deleteByIdTitle(Post post);

    /**
     * 创建新 post
     * @param target
     */
    void save(Post target);

    /**
     * 修改 post
     * @param target
     */
    void update(Post target);
}
