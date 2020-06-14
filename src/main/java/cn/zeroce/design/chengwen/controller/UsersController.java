package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.entity.Users;
import cn.zeroce.design.chengwen.service.UsersService;
import cn.zeroce.design.chengwen.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param username
     * @param mobile
     * @param sort
     * @param order
     * @return
     */
    @PreAuthorize("hasAuthority('user:list')")
    @GetMapping("/list")
    public Object getUserList(@RequestParam(name = "page", required = true) Integer page,
                              @RequestParam(name = "limit", required = true) Integer limit,
                              @RequestParam(name = "username", required = false) String username,
                              @RequestParam(name = "mobile", required = false) String mobile,
                              @RequestParam(name = "sort", required = false) String sort,
                              @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {
        PageHelper.startPage(page, limit);
        List<Users> usersList = this.usersService.getList(username, mobile);
        PageHelper.clearPage();
        PageInfo<Users> usersPageInfo = new PageInfo<>(usersList);
        return ResultGenerator.genOkResult(usersPageInfo);
    }

    @PreAuthorize("hasAuthority('user:search')")
    @GetMapping("search")
    public ResultUtil search(@RequestParam(name = "name") String username) {
        List<Users> resultList = this.usersService.getByUsername(username);
        return ResultGenerator.genOkResult(resultList);
    }

}
