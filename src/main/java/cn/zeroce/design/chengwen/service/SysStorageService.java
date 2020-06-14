package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.entity.SysStorage;

import java.util.List;

/**
 * 对外存储服务接口
 * @author: zeroce
 * @date 20.4.10 23:50
 */
public interface SysStorageService {
    /**
     * 创建存储记录
     * @param sysStorageInfo
     */
    void create(SysStorage sysStorageInfo);

    /**
     * 按 sKey查询存储记录
     * @param skey
     * @return
     */
    SysStorage selectByKey(String skey);

    /**
     * 按 name 查询存储记录
     * @param originalFilename
     * @return
     */
    List<SysStorage> findByName(String originalFilename);

    /**
     * 按 id 删除存储记录
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 按 key | name 查询存储记录
     * @param sKey
     * @param sName
     * @return
     */
    List<SysStorage> findByKeyName(String sKey, String sName);

    /**
     *
     * @param id
     * @return
     */
    SysStorage findById(Integer id);

    /**
     * 按 id 更新存储记录的文件名
     * @param resource
     */
    void updateName(SysStorage resource);
}
