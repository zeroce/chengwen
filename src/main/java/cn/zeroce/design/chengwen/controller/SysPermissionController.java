package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.dto.PermsDTO;
import cn.zeroce.design.chengwen.dto.respBody.PermsTreeRespBody;
import cn.zeroce.design.chengwen.service.SysPermissionService;
import cn.zeroce.design.chengwen.utils.ResultUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author: zeroce
 * @date 20.3.22 23:25
 */
@RestController
@RequestMapping("/sys/perms")
public class SysPermissionController {
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 权限树
     * @param roleId
     * @return
     */
    @PreAuthorize("hasAuthority('permission:list')")
    @GetMapping("/getPerms")
    public ResultUtil getPerms(@RequestParam(name = "roleId") Integer roleId) {
        System.out.println("roleId:" + roleId);
        Set<Integer> adminRolesList = this.sysPermissionService.findByRoleId(roleId);
        List<PermsDTO> allList = this.sysPermissionService.findAll();
        PermsTreeRespBody respBody = new PermsTreeRespBody(allList, adminRolesList);
        System.out.println(respBody);
        return ResultGenerator.genOkResult(respBody);
    }

}
