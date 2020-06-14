package cn.zeroce.design.chengwen.wx.web;

import cn.zeroce.design.chengwen.wx.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.wx.dto.reqBody.WxPostCreateReqBody;
import cn.zeroce.design.chengwen.wx.dto.respData.WxCommentsDTO;
import cn.zeroce.design.chengwen.wx.dto.respData.WxGetPostDetailsDTO;
import cn.zeroce.design.chengwen.wx.dto.respData.WxPostRespDTO;
import cn.zeroce.design.chengwen.wx.entity.Post;
import cn.zeroce.design.chengwen.wx.entity.Users;
import cn.zeroce.design.chengwen.wx.entity.WxUser;
import cn.zeroce.design.chengwen.wx.service.*;
import cn.zeroce.design.chengwen.wx.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.22 15:45
 */
@RestController
@RequestMapping("/wx/posts")
public class WxPostsController {
    @Resource
    private WxPostsService wxPostsService;
    @Resource
    private WxAccountService wxAccountService;
    @Resource
    private WxUserService wxUserService;
    @Resource
    private WxPostImageUrlsService wxPostImageUrlsService;
    @Resource
    private WxCommentsService wxCommentsService;
    @Resource
    private WxUserPostService wxUserPostService;

    @PostMapping("/deletewxarticle")
    public ResultUtil deletewxarticle(@RequestParam(name = "id") Integer postId,
                                      @RequestParam(name = "hotqinsessionid") String sessionId) {
        List<Users> usersList = this.wxAccountService.checkSessionId(sessionId);
        if (null == usersList || usersList.isEmpty()) {
            return ResultGenerator.genFailedResult("删除失败！登录已过期，请重新登录");
        }
        this.wxPostsService.deleteByPostId(postId);
        return ResultGenerator.genOkResult();
    }

    /**
     * 按 postId 更新文章
     * @param postId
     * @param reqBody
     * @param request
     * @return
     */
    @PostMapping("/update/article/{id}")
    public ResultUtil updatearticle(@PathVariable(name = "id") Integer postId,
                                    @RequestBody WxPostCreateReqBody reqBody,
                                    HttpServletRequest request) {
        System.out.println("uodate post id: " + postId + "---------------");
        this.wxPostsService.updateByPostId(postId, reqBody);
        WxGetPostDetailsDTO wxGetPostDetailsDTO = this.wxPostsService.findByPostId(postId);
        return ResultGenerator.genOkResult(wxGetPostDetailsDTO);
    }

    /**
     * 按 postId 查找文章主要内容
     * @param sessionId
     * @param postId
     * @return
     */
    @GetMapping("/editwxarticle/{id}")
    public ResultUtil editwxarticle(@RequestParam(name = "hotqinsessionid") String sessionId,
                                    @PathVariable(name = "id") Integer postId) {
        WxPostCreateReqBody respBody = this.wxPostsService.getByPostId(postId);
        respBody.setHotqinsessionid(sessionId);
        return ResultGenerator.genOkResult(respBody);
    }
    /**
     * 按 postId 查找文章全部信息
     *
     * @param sessionId
     * @param postId
     * @param request
     * @return
     */
    @GetMapping("/getwxarticle/{id}")
    public ResultUtil getwxarticle(@RequestParam(name = "hotqinsessionid") String sessionId,
                                   @PathVariable(name = "id") Integer postId,
                                   HttpServletRequest request) {
        // post postDetails author
        WxGetPostDetailsDTO responseDTO = this.wxPostsService.findByPostId(postId);
        // comments
        List<WxCommentsDTO> commentsList = this.wxCommentsService.selectByPostId(postId);
        responseDTO.setComments(commentsList);
        // viewerIsAuthor
        List<Users> usersList = this.wxAccountService.findByOpenidAndSessionId(null, sessionId);
        if (null == usersList || usersList.isEmpty()) {
            responseDTO.setViewerIsAuthor(false);
        } else {
            Users users = usersList.get(0);
            if (users.getUserOpenid().equals(responseDTO.getPost().getUserOpenid())) {
                responseDTO.setViewerIsAuthor(true);
            }
            // authorHasCollect
            responseDTO.setAuthorHasCollect(this.wxUserPostService.checkByUserIdPostId(users.getId(), postId));
        }
        // viewerIsAdmin
        responseDTO.setViewerIsAdmin(false);


        return ResultGenerator.genOkResult(responseDTO);
    }

    /**
     * 分页查找文章列表
     * 按 searchKeyWord 查找相关文章
     *
     * @param page
     * @param limit
     * @param searchKeyWord
     * @param request
     * @return
     */
    @GetMapping("/all/pagelists")
    public ResultUtil pagelists(@RequestParam(name = "page") Integer page,
                                @RequestParam(name = "limit", defaultValue = "10", required = false) Integer limit,
                                @RequestParam(name = "searchKeyWord", required = false) String searchKeyWord,
                                @RequestParam(name = "userId", required = false) String userId,
                                HttpServletRequest request) {
        System.out.println("now page: " + page + ", and limit: " + limit);
        PageHelper.startPage(page, limit);
        List<Post> pageList = this.wxPostsService.findPageList(searchKeyWord, userId);
        PageHelper.clearPage();
        List<WxPostRespDTO> resultList = new ArrayList<>();
        for (Post post :
                pageList) {
            WxUser wxUserByOpenid = this.wxUserService.findByOpenid(post.getUserOpenid());
            List<String> postImageUrls = this.wxPostImageUrlsService.findUrlsByPostId(post.getId());
            WxPostRespDTO record = new WxPostRespDTO();
            record.setId(post.getId());
            record.setPostTitle(post.getPostTitle());
            record.setPostContent(post.getPostContent());
            record.setPostContentShort(post.getPostContentShort());
            record.setCreateTime(post.getCreateTime());
            record.setUserId(post.getUserId());
            record.setUserOpenid(post.getUserOpenid());
            record.setImageUrls(postImageUrls);
            record.setUserNickname(wxUserByOpenid.getNickname());

            resultList.add(record);
        }

        PageInfo<WxPostRespDTO> postPageInfo = new PageInfo<>(resultList);
        return ResultGenerator.genOkResult(postPageInfo);
    }

    /**
     * 保存新编辑的文章
     *
     * @param reqBody
     * @return
     */
    @PostMapping("/create/article")
    public ResultUtil createArticle(@RequestBody WxPostCreateReqBody reqBody) {
        String sessionId = reqBody.getHotqinsessionid();
        List<Users> usersListBySessionId = this.wxAccountService.findByOpenidAndSessionId(null, sessionId);
        if (null == usersListBySessionId || usersListBySessionId.isEmpty()) {
            return ResultGenerator.genFailedResult("登录超时！！请重新登录，并重新编辑文章");
        }
        Users wxUsers = usersListBySessionId.get(0);

        Post post = this.wxPostsService.create(reqBody, wxUsers);
        if (null != reqBody.getImages() && !reqBody.getImages().isEmpty()) {
            this.wxPostImageUrlsService.saveImageUrlList(reqBody.getImages(), post.getId());
        }

        WxPostRespDTO responseDTO = new WxPostRespDTO();
        responseDTO.setId(post.getId());
        responseDTO.setPostTitle(post.getPostTitle());
        responseDTO.setPostContent(post.getPostContent());
        responseDTO.setPostContentShort(post.getPostContentShort());
        responseDTO.setCreateTime(post.getCreateTime());
        responseDTO.setUserId(post.getUserId());
        responseDTO.setUserOpenid(post.getUserOpenid());
        responseDTO.setImageUrls(reqBody.getImages());

        return ResultGenerator.genOkResult(responseDTO);
    }
}
