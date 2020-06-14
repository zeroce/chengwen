package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.entity.UserCollectPost;
import cn.zeroce.design.chengwen.service.UserCollectPostService;
import cn.zeroce.design.chengwen.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.5.7 15:49
 */
@RestController
@RequestMapping("/sys/users/collect")
public class UserCollectPostController {
    @Resource
    private UserCollectPostService userCollectPostService;

    @PreAuthorize("hasAuthority('user:collect:list')")
    @GetMapping("/getList")
    public ResultUtil getList(@RequestParam(name = "page", required = true) Integer page,
                              @RequestParam(name = "limit", required = true) Integer limit,
                              @RequestParam(name = "collectorId", required = false) Integer collectorId,
                              @RequestParam(name = "collectedPostId", required = false) Integer collectedPostId,
                              @RequestParam(name = "sort", required = false) String sort,
                              @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {
        PageHelper.startPage(page, limit);
        List<UserCollectPost> resultList = this.userCollectPostService.getList(collectorId, collectedPostId);
        PageHelper.clearPage();
        PageInfo<UserCollectPost> result = new PageInfo<>(resultList);
        return ResultGenerator.genOkResult(result);
    }

}
