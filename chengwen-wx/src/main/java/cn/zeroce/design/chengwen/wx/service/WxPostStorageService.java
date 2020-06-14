package cn.zeroce.design.chengwen.wx.service;

import cn.zeroce.design.chengwen.wx.entity.PostStorage;

import java.util.List;

public interface WxPostStorageService {
    /**
     * 创建存储记录
     *
     * @param postStorageInfo
     */
    void create(PostStorage postStorageInfo);

    /**
     * 按 pKey 查询存储记录
     *
     * @param pkey
     * @return
     */
    PostStorage selectByKey(String pkey);

    /**
     * 按 name 查询存储记录
     *
     * @param originalFilename
     * @return
     */
    List<PostStorage> findByName(String originalFilename);

    /**
     * 按 pKey 删除存储记录
     *
     * @param pKey
     */
    void deleteByPKey(String pKey);

    /**
     * 按 key | name 查询存储记录
     *
     * @param pKey
     * @param pName
     * @return
     */
    List<PostStorage> findByKeyName(String pKey, String pName);

    /**
     * 按 pKey 查询存储记录
     * @param pKey
     * @return
     */
    PostStorage findByPKey(String pKey);

    /**
     * 按 pKey 更新存储记录的文件名
     *
     * @param resource
     */
    void updateName(PostStorage resource);
}
