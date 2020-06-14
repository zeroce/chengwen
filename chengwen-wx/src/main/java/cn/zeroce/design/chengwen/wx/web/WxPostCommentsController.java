package cn.zeroce.design.chengwen.wx.web;

import cn.zeroce.design.chengwen.wx.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.wx.dto.reqBody.WxCommentsCreateReqDTO;
import cn.zeroce.design.chengwen.wx.entity.Comments;
import cn.zeroce.design.chengwen.wx.entity.Users;
import cn.zeroce.design.chengwen.wx.service.WxAccountService;
import cn.zeroce.design.chengwen.wx.service.WxCommentsService;
import cn.zeroce.design.chengwen.wx.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.26 10:51
 */
@RestController
@RequestMapping("/wx/comments")
public class WxPostCommentsController {
    @Resource
    private WxCommentsService wxCommentsService;
    @Resource
    private WxAccountService wxAccountService;

    /**
     * 添加评论
     * @param postId
     * @param requestDTO
     * @param request
     * @return
     */
    @PostMapping("/addwxrelease/{id}")
    public ResultUtil addwxrelease(@PathVariable(name = "id") Integer postId,
                                   @RequestBody WxCommentsCreateReqDTO requestDTO,
                                   HttpServletRequest request) {
        List<Users> usersList = this.wxAccountService.findByOpenidAndSessionId(null, requestDTO.getHotqinsessionid());
        Users users = usersList.get(0);
        Comments comments = this.wxCommentsService.save(postId, requestDTO, users);

        return ResultGenerator.genOkResult(comments);
    }

    /**
     * 删除评论
     * @param id
     * @param request
     * @return
     */
    @PostMapping("/deletewxrelease/{id}")
    public ResultUtil deletewxrelease(@PathVariable(name = "id") Integer id,

                                      HttpServletRequest request) {

        return ResultGenerator.genOkResult();
    }
}
