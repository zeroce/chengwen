package cn.zeroce.design.chengwen.mapper;

import cn.zeroce.design.chengwen.dto.PermsDTO;
import cn.zeroce.design.chengwen.entity.SysPermission;

import java.util.List;

/**
 * @author: zeroce
 * @date 20.5.5 8:52
 */
public interface ExtraSysPermissionMapper {
    /**
     * 查询所有权限类别分类
     * @return
     */
    List<String> selectDistTitle();

    /**
     * 按 roleId 查询权限 code
     * @param roleId
     * @return
     */
    List<String> selectPermsByRoleId(Integer roleId);

    /**
     * 按 title 查询权限 code
     * @param title
     * @return
     */
    List<String> selectPermsByTitle(String title);

    /**
     * 按 title 查询树形权限 code
     * @param title
     * @return
     */
    List<PermsDTO> selectPermsCodeByTitle(String title);

    /**
     * 按 roleId 查询权限 id && pid
     * @param roleId
     * @return
     */
    List<SysPermission> selectPermsIdByRoleId(Integer roleId);

    /**
     * 按 pid 查询权限 resources
     * @param pid
     * @return
     */
    List<PermsDTO> selectPermsCodeByPid(Integer pid);

    /**
     * 所有权限管理目录
     * @return
     */
    List<SysPermission> selectAllPermsTitle();
}
