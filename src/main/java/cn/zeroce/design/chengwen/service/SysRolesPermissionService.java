package cn.zeroce.design.chengwen.service;

import java.util.Set;

/**
 * @author: zeroce
 * @date 20.5.5 23:18
 */
public interface SysRolesPermissionService {
    /**
     * 按 roleId 进行授权
     * @param roleId
     * @param permissions
     */
    void grantPerms(Integer roleId, Set<Integer> permissions);
}
