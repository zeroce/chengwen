package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.response.ResultCode;
import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.dto.reqBody.GrantPermsReqBody;
import cn.zeroce.design.chengwen.entity.SysAdminRoles;
import cn.zeroce.design.chengwen.entity.SysRoles;
import cn.zeroce.design.chengwen.entity.SysRolesPermission;
import cn.zeroce.design.chengwen.service.AccountService;
import cn.zeroce.design.chengwen.service.SysAdminRolesService;
import cn.zeroce.design.chengwen.service.SysRolesPermissionService;
import cn.zeroce.design.chengwen.service.SysRolesService;
import cn.zeroce.design.chengwen.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zeroce
 * @date 20.3.22 23:25
 */
@RestController
@RequestMapping("/sys/roles")
public class SysRolesController {
    @Resource
    private SysRolesService sysRolesService;
    @Resource
    private AccountService accountService;
    @Resource
    private SysAdminRolesService sysAdminRolesService;
    @Resource
    private SysRolesPermissionService sysRolesPermissionService;

    /**
     * 创建管理员角色
     *
     * @param sysRole
     * @return
     */
    @PreAuthorize("hasAuthority('roles:add')")
    @PostMapping("/create")
    public ResultUtil create(@RequestBody SysRoles sysRole) {
        if (this.sysRolesService.checkExist(sysRole)) {
            return ResultGenerator.genFailedResult(ResultCode.DATA_IS_EXIST);
        }
        this.sysRolesService.save(sysRole);
        sysRole.setId(this.sysRolesService.countAll());
        return ResultGenerator.genOkResult(sysRole);
    }

    /**
     * 删除角色
     *
     * @param data
     * @return
     */
    @PreAuthorize("hasAuthority('roles:delete')")
    @DeleteMapping("/{data}")
    public ResultUtil delete(@PathVariable(name = "data") String data) {
        SysRoles target = new SysRoles();
        target.setName(data);
        List<SysRoles> sysRoles = this.sysRolesService.listAll(data);
        if (null != sysRoles && !sysRoles.isEmpty()) {
            SysRoles resource = sysRoles.get(0);
            List<SysAdminRoles> targetList = this.sysAdminRolesService.selectByRolesId(resource.getId());
            if (null != targetList && !targetList.isEmpty()) {
                return ResultGenerator.genFailedResult(ResultCode.DELETE_FAILED.getCode(), ResultCode.DELETE_FAILED.getMessage() + "，当前角色存在管理员，不能删除");
            }
            this.sysRolesService.deleteById(resource.getId());
            return ResultGenerator.genOkResult();
        }
        return ResultGenerator.genFailedResult(ResultCode.DELETE_FAILED.getCode(), ResultCode.DELETE_FAILED.getMessage() + "，该角色信息已不存在，请刷新列表");
    }

    /**
     * 所有管理员角色信息
     *
     * @param page
     * @param limit
     * @param username
     * @param sort
     * @param order
     * @return 管理员角色列表
     */
    @PreAuthorize("hasAnyAuthority('roles:list', 'roles:search')")
    @GetMapping
    public ResultUtil list(@RequestParam(name = "page", defaultValue = "0") final Integer page,
                           @RequestParam(name = "limit", defaultValue = "0") final Integer limit,
                           @RequestParam(name = "name", required = false) String username,
                           @RequestParam(name = "sort", defaultValue = "create_time", required = false) String sort,
                           @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        PageHelper.startPage(page, limit);
        List<SysRoles> resultList = this.sysRolesService.listAll(username);
        PageHelper.clearPage();
        PageInfo<SysRoles> pageInfo = new PageInfo<>(resultList);
        return ResultGenerator.genOkResult(pageInfo);

    }

    /**
     * 可操作角色列表
     *
     * @return
     */
    @PreAuthorize("hasAuthority('roles:list')")
    @GetMapping("/options")
    public ResultUtil options() {
        List<SysRoles> resultList = sysRolesService.listAll(null);
        List<Map<String, Object>> options = new ArrayList<>(resultList.size());
        for (SysRoles role : resultList) {
            Map<String, Object> option = new HashMap<>(2);
            option.put("value", role.getId());
            option.put("label", role.getName());
            options.add(option);
        }
        return ResultGenerator.genOkResult(options);
    }

    /**
     * 修改角色信息
     *
     * @param sysRoles
     * @return
     */
    @PreAuthorize("hasAuthority('roles:update')")
    @PutMapping("/update")
    public ResultUtil update(@RequestBody SysRoles sysRoles) {
        SysRoles target = new SysRoles();
        target.setName(sysRoles.getName());
        if (this.sysRolesService.checkExist(target)) {
            return ResultGenerator.genFailedResult(ResultCode.UPDATE_FAILED.getCode(),
                    ResultCode.UPDATE_FAILED.getMessage() + "，与其他角色名重复，请重新命名");
        }
        this.sysRolesService.updateById(sysRoles);
        return ResultGenerator.genOkResult();
    }

    /**
     * 角色授权
     *
     * @param reqBody
     * @return
     */
    @PreAuthorize("hasAuthority('roles:update')")
    @PostMapping("/grantPerms")
    public ResultUtil grantPerms(@RequestBody GrantPermsReqBody reqBody) {
        System.out.println("reqBody: " + reqBody.getRoleId() + ",  + " + reqBody.getPermissions());
        this.sysRolesPermissionService.grantPerms(reqBody.getRoleId(), reqBody.getPermissions());
        return ResultGenerator.genOkResult();
    }


}
