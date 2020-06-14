package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.dto.PermsDTO;

import java.util.List;
import java.util.Set;

/**
 * @author: zeroce
 * @date 20.3.22 23:31
 */

public interface SysPermissionService {
    /**
     * 按 roleId 查找权限 id
     * @param roleId
     * @return
     */
    Set<Integer> findByRoleId(Integer roleId);

    /**
     * 查找所有权限并分类
     * @return
     */
    List<PermsDTO> findAll();
}
