package cn.zeroce.design.chengwen.dto;

import cn.zeroce.design.chengwen.entity.SysAdmin;
import lombok.*;

import java.util.List;

/**
 * @author: zeroce
 * @date 20.3.21 13:56
 */
@Getter
@Setter
@ToString
public class SysAdminWithRolesDTO{
    private Integer id;
    private String username;
    private String avatar;
    private String name;
    private String introduction;
    private List<String> roles;
    private List<Integer> roleIds;
    private List<String> permissionCodeList;
}
