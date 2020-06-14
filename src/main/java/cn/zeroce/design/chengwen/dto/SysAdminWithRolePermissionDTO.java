package cn.zeroce.design.chengwen.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author: zeroce
 * @date 20.3.21 12:00
 */
@Data
public class SysAdminWithRolePermissionDTO{
    /**
     * 用户基本信息和角色
     */
    private SysRolesDTO sysRolesDTO;
    /**
     * 用户的角色对应的权限 code
     */
    private List<String> permissionCodeList;
}
