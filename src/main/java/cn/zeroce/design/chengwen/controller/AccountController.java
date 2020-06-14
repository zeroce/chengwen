package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.jwt.JwtUtil;
import cn.zeroce.design.chengwen.core.response.ResultCode;
import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.dto.SysAdminDTO;
import cn.zeroce.design.chengwen.dto.SysAdminWithRolesDTO;
import cn.zeroce.design.chengwen.entity.SysAdmin;
import cn.zeroce.design.chengwen.service.AccountService;
import cn.zeroce.design.chengwen.service.PermissionService;
import cn.zeroce.design.chengwen.service.SysAdminService;
import cn.zeroce.design.chengwen.service.impl.AccountDetailsServiceImpl;
import cn.zeroce.design.chengwen.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.3.20 9:05
 */
@RestController
@RequestMapping("/sys/account")
@Validated
public class AccountController {
    @Resource
    private AccountService accountService;
    @Resource
    private SysAdminService sysAdminService;
    @Resource
    private AccountDetailsServiceImpl userDetailsService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private JwtUtil jwtUtil;

    /**
     * 注册
     * @param account
     * @param bindingResult
     * @return
     */
    @PostMapping
    public ResultUtil register(
            @RequestBody @Valid final SysAdminDTO account, final BindingResult bindingResult) {
        // {"name":"123456", "password":"123456", "email": "123456@qq.com"}
        if (bindingResult.hasErrors()) {
            //noinspection ConstantConditions
            final String msg = bindingResult.getFieldError().getDefaultMessage();
            return ResultGenerator.genFailedResult(msg);
        } else {
            this.accountService.save(account);
            return this.getToken(account.getName());
        }
    }

    /**
     * 登录账户的资料
     * @param principal
     * @return
     */
    @GetMapping("/getInfo")
    public ResultUtil detail(final Principal principal) {
        final SysAdminWithRolesDTO sysAdminWithRolesDTO = this.accountService.findRolesDetaiByName(principal.getName());
        sysAdminWithRolesDTO.setPermissionCodeList(this.permissionService.findPerListByUserName(principal.getName()));
        return ResultGenerator.genOkResult(sysAdminWithRolesDTO);
    }

    /**
     * 新增管理员账户
     * @param account
     * @return
     */
    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/create")
    public ResultUtil create(@RequestBody SysAdminDTO account) {
        boolean isExist = this.sysAdminService.isExist(account.getUsername());
        if (isExist) {
            return ResultGenerator.genFailedResult(ResultCode.DUPLICATE_NAME);
        } else {
            this.accountService.save(account);
        }
        return ResultGenerator.genOkResult(account);
    }

    /**
     * 删除管理员账户
     * @param data
     * @param request
     * @return
     */
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/{data}")
    public ResultUtil delete(@PathVariable(name = "data") String data, HttpServletRequest request) {
        SysAdmin dbAccount = this.accountService.getSysAdminByName(data);
        if (dbAccount == null) {
            return ResultGenerator.genFailedResult("用户不存在");
        } else {
            String token = this.jwtUtil.getTokenFromRequest(request);
            if (null != token && !token.equals("")) {
                String accountName = this.jwtUtil.getName(token);
                if (dbAccount.getUsername().equals(accountName)) {
                    return ResultGenerator.genFailedResult(ResultCode.DELETE_FAILED.getCode(), "该管理员正在登录，不得删除！");
                }
            }
        }
        this.accountService.deleteById(dbAccount.getId());
        return ResultGenerator.genOkResult(ResultCode.SUCCESS);
    }

    /**
     * 验证密码
     * @param account
     * @return
     */
    @PostMapping("/password")
    public ResultUtil validatePassword(@RequestBody SysAdminDTO account) {
        final SysAdmin dbAccount = this.accountService.getById(account.getId());
        final boolean isValidate =
                this.accountService.verifyPassword(account.getPassword(), dbAccount.getPassword());
        return ResultGenerator.genOkResult(isValidate);
    }

    /**
     * 更新管理员的资料
     * @param account
     * @param principal
     * @return
     */
    @PreAuthorize("hasAuthority('admin:update')")
    @PostMapping("/update")
    public ResultUtil update(@RequestBody SysAdminDTO account, final Principal principal) {
        final SysAdmin dbAccount = this.accountService.getById(account.getId());
        if (dbAccount == null) {
            return ResultGenerator.genFailedResult("用户不存在");
        }
        this.accountService.update(account);
        return ResultGenerator.genOkResult();
    }

    /**
     * 其他账户的资料
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('admin:detail')")
    @GetMapping("/{id}")
    public ResultUtil detail(@PathVariable final Long id) {
        final SysAdmin dbAccount = this.accountService.getById(id.intValue());
        return ResultGenerator.genOkResult(dbAccount);
    }

    /**
     * 管理员资料
     * @param page
     * @param limit
     * @param username
     * @param sort
     * @param order
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin:list', 'admin:search')")
    @GetMapping
    public ResultUtil list(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "0") Integer limit,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "sort", defaultValue = "create_time", required = false) String sort,
            @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        PageHelper.startPage(page, limit);
        List<SysAdminWithRolesDTO> list = this.accountService.listAllWithRolesId(username);
        PageHelper.clearPage();
        PageInfo<SysAdminWithRolesDTO> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    /***
     * 登录
     * @param account
     * @return
     */
        // {"name":"admin", "password":"admin123"}
        // {"email":"admin@qq.com", "password":"admin123"}
    @PostMapping("/login/token")
    public ResultUtil login(@RequestBody SysAdmin account) {
        if (account.getUsername() == null) {
            return ResultGenerator.genFailedResult("用户名为空");
        }
        if (account.getPassword() == null) {
            return ResultGenerator.genFailedResult("密码为空");
        }
        // 用户名登录
        SysAdmin dbAccount = null;
        if (account.getUsername() != null) {
            dbAccount = this.accountService.getSysAdminByName(account.getUsername());
            if (dbAccount == null) {
                return ResultGenerator.genFailedResult("用户名不存在");
            }
        }
        // 验证密码
        // noinspection ConstantConditions
        if (!this.accountService.verifyPassword(account.getPassword(), dbAccount.getPassword())) {
            return ResultGenerator.genFailedResult("密码错误");
        }
        // 更新登录时间
        this.accountService.updateLoginTimeByName(account.getUsername());
        return this.getToken(account.getUsername());
    }

    /**
     * 退出登录
     * @param principal
     * @return
     */
    @DeleteMapping("/logout/token")
    public ResultUtil logout(final Principal principal) {
        this.jwtUtil.invalidRedisToken(principal.getName());
        return ResultGenerator.genOkResult();
    }

    /**
     * 获得 token
     * @param username
     * @return
     */
    private ResultUtil getToken(final String username) {
        final UserDetails accountDetails = this.userDetailsService.loadUserByUsername(username);
        final String token = this.jwtUtil.sign(username, accountDetails.getAuthorities(), true);
        return ResultGenerator.genOkResult(token);
    }
}
