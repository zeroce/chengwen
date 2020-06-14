package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.entity.SysAdminRoles;

import java.util.List;

public interface SysAdminRolesService {
    /**
     * 按 ID 查询
     *
     * @param id
     * @return
     */
    SysAdminRoles selectById(Integer id);

    /**
     * 更新用户角色
     *
     * @param record 用户角色
     */
    void updateSysAdminRolesById(SysAdminRoles record);

    /**
     * 按 role_id 查询
     * @param roleId
     * @return 当前role_id所属的管理员列表列表
     */
    List<SysAdminRoles> selectByRolesId(Integer roleId);
}
