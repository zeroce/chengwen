package cn.zeroce.design.chengwen.dto;

import cn.zeroce.design.chengwen.entity.SysAdmin;
import lombok.Data;

/**
 * @author: zeroce
 * @date 20.3.30 15:59
 */
@Data
public class SysRolesDTO {
    /**
     * 用户基本信息
     */
    private SysAdmin sysAdmin;
    /**
     * 用户角色ID
     */
    private Integer roleId;
    /**
     * 用户角色名
     */
    private String roleName;
}
