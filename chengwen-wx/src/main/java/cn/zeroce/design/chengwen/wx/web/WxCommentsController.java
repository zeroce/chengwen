package cn.zeroce.design.chengwen.wx.web;

import cn.zeroce.design.chengwen.wx.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.wx.service.WxCommentsService;
import cn.zeroce.design.chengwen.wx.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zeroce
 * @date 20.4.28 23:53
 */
@RestController
@RequestMapping("/wx/comments")
public class WxCommentsController {
    @Resource
    private WxCommentsService wxCommentsService;

    @GetMapping("/thumpup")
    public ResultUtil thumpup(@RequestParam(name = "id") Integer commentsId,
                              @RequestParam(name = "hotqinsessionid") String sessionId) {
        System.out.println("commentsId: " + commentsId);
        System.out.println("userId: " + sessionId);

        return ResultGenerator.genOkResult();
    }
}
