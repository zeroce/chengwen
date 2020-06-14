package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.entity.PostDetails;
import cn.zeroce.design.chengwen.service.PostDetailsService;
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
 * @date 20.5.7 18:19
 */
@RestController
@RequestMapping("/users/post/details")
public class PostDetailsController {
    @Resource
    private PostDetailsService postDetailsService;

    @PreAuthorize("hasAuthority('user:post:list')")
    @GetMapping("/getList")
    public ResultUtil getList(@RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
                              @RequestParam(name = "limit", required = true, defaultValue = "0") Integer limit,
                              @RequestParam(name = "postId", required = false) Integer postId,
                              @RequestParam(name = "sort", defaultValue = "create_time", required = false) String sort,
                              @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        PageHelper.startPage(page, limit);
        List<PostDetails> resultList = this.postDetailsService.getList(postId);
        PageHelper.clearPage();
        PageInfo<PostDetails> result = new PageInfo<>(resultList);
        return ResultGenerator.genOkResult(result);
    }
}
