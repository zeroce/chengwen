package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.entity.SysRoles;

import java.util.List;

public interface SysRolesService {
    /**
     * 查找所有角色信息
     *
     * @return 角色信息列表
     */
    List<SysRoles> listAll(String name);

    /**
     * 保存新管理员角色信息
     * @param sysRole
     */
    void save(SysRoles sysRole);

    /**
     * 验证该角色信息是否已存在
     * @return
     */
    boolean checkExist(SysRoles sysRoles);

    /**
     * 计数
     * @return
     */
    Integer countAll();

    /**
     * 按 Id 删除角色，同时删除该角色拥有的权限
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新角色信息
     * @param sysRoles
     */
    void updateById(SysRoles sysRoles);
}
