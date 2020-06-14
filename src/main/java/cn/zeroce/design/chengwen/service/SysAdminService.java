package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.entity.SysAdmin;

import java.util.List;

public interface SysAdminService {
    /**
     * 按 username 查询管理员信息
     * @param username
     * @return 管理员信息
     */
    List<SysAdmin> selectByUsername(String username);

    /**
     * 验证该 username 是否已存在
     * @param username
     * @return
     */
    boolean isExist(String username);
}
