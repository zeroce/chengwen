package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.dto.SysRolesWithPers;

import java.util.List;

public interface PermissionService {
    /**
     * 按用户名查询权限列表
     *
     * @param username 用户名
     * @return 用户的权限列表
     */
    List<String> findPerListByUserName(String username);
}
