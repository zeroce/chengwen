package cn.zeroce.design.chengwen.dto.reqBody;

import java.util.Set;

/**
 * @author: zeroce
 * @date 20.5.5 22:02
 */
public class GrantPermsReqBody {
    private Integer roleId;
    private Set<Integer> permissions;

    public GrantPermsReqBody() { }

    public GrantPermsReqBody(Integer roleId, Set<Integer> permissions) {
        this.roleId = roleId;
        this.permissions = permissions;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Set<Integer> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Integer> permissions) {
        this.permissions = permissions;
    }
}
