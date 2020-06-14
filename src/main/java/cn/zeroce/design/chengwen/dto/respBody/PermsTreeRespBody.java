package cn.zeroce.design.chengwen.dto.respBody;

import cn.zeroce.design.chengwen.dto.PermsDTO;

import java.util.List;
import java.util.Set;

/**
 * @author: zeroce
 * @date 20.5.5 14:53
 */
public class PermsTreeRespBody {
    private List<PermsDTO> permsTree;
    private Set<Integer> rolePerms;

    public PermsTreeRespBody() { }

    public PermsTreeRespBody(List<PermsDTO> permsTree, Set<Integer> rolePerms) {
        this.permsTree = permsTree;
        this.rolePerms = rolePerms;
    }

    public List<PermsDTO> getPermsTree() {
        return permsTree;
    }

    public void setPermsTree(List<PermsDTO> permsTree) {
        this.permsTree = permsTree;
    }

    public Set<Integer> getRolePerms() {
        return rolePerms;
    }

    public void setRolePerms(Set<Integer> rolePerms) {
        this.rolePerms = rolePerms;
    }
}
