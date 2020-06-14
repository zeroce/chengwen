package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.entity.SysAdminRoles;
import cn.zeroce.design.chengwen.service.SysAdminRolesService;
import cn.zeroce.design.chengwen.utils.ResultUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @author: zeroce
 * @date 20.3.22 22:23
 */
@RestController
@RequestMapping("/sys/admin/roles")
public class SysAdminRolesController {
    @Resource
    private SysAdminRolesService sysAdminRolesService;

    @PreAuthorize("hasAuthority('role:update')")
    @PutMapping
    public ResultUtil updateSysAdminRoles(
            @RequestBody final SysAdminRoles sysAdminRoles, final Principal principal) {
        final SysAdminRoles target = sysAdminRolesService.selectById(sysAdminRoles.getId());
        sysAdminRolesService.updateSysAdminRolesById(target);
        return ResultGenerator.genOkResult();
    }
}
