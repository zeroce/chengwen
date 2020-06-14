package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.response.ResultCode;
import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.dto.reqBody.ArticleDTO;
import cn.zeroce.design.chengwen.dto.respBody.PostDetailRespBody;
import cn.zeroce.design.chengwen.entity.Post;
import cn.zeroce.design.chengwen.entity.Users;
import cn.zeroce.design.chengwen.service.PostService;
import cn.zeroce.design.chengwen.service.UsersService;
import cn.zeroce.design.chengwen.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users/post")
public class PostController {
    @Resource
    private UsersService usersService;
    @Autowired
    private PostService postService;
    /**
     * posts查询
     * @param page
     * @param limit
     * @return
     */
    @PreAuthorize("hasAnyAuthority('user:post:list', 'user:post:search')")
    @GetMapping("/list")
    public ResultUtil list(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "limit", required = true, defaultValue = "0") Integer limit,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "sort", defaultValue = "create_time", required = false) String sort,
            @RequestParam(name = "order", defaultValue = "desc", required = false) String order,
            @RequestParam(name = "authorId", required = false) Integer authorId) {
        PageHelper.startPage(page, limit);
        List<Post> postList = this.postService.findByTitleUsername(title, authorId);
        PageHelper.clearPage();
        PageInfo<Post> postPageInfo = new PageInfo<>(postList);
        return ResultGenerator.genOkResult(postPageInfo);
    }

    /**
     * post 信息
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('user:post:detail')")
    @GetMapping("/detail")
    public ResultUtil detail(@RequestParam(name = "id", required = true) Integer id) {
        Post resultPost = postService.findById(id);
        System.out.println("////////   " + resultPost.getPostTitle());
        Users resultUsers = this.usersService.getById(resultPost.getUserId());
        System.out.println("////////   " + resultUsers.getUserNickname());
        PostDetailRespBody result = new PostDetailRespBody(resultPost, resultUsers);
        return ResultGenerator.genOkResult(result);
    }

    /**
     * 删除 post
     * @param post
     * @return
     */
    @PreAuthorize("hasAuthority('user:post:delete')")
    @DeleteMapping("/delete")
    public ResultUtil delete(@RequestBody Post post) {
        Post resultById = this.postService.findById(post.getId());
        if (null == resultById) {
            return ResultGenerator.genFailedResult(ResultCode.DATA_IS_NOT_EXIST);
        }
        List<Post> resultByTitle = this.postService.findByTitleUsername(post.getPostTitle(), null);
        if (null == resultByTitle || resultByTitle.isEmpty()) {
            return ResultGenerator.genFailedResult(ResultCode.DATA_IS_NOT_EXIST);
        }
        this.postService.deleteByIdTitle(post);
        return ResultGenerator.genOkResult();
    }

    /**
     * 保存新 post
     * @param article
     * @return
     */
    @PreAuthorize("hasAuthority('user:post:create')")
    @PostMapping("/create")
    public ResultUtil create(@RequestBody ArticleDTO article, Principal principal) {
        if (null != article.getId() && article.getId() > 0) {
            return ResultGenerator.genFailedResult(ResultCode.DATA_IS_EXIST.getCode(), "此文章已发布");
        }
        System.out.println("content_short:  " + article.getContentShort() + "  -------------");

        Post target = new Post();
        target.setPostTitle(article.getTitle());
        target.setPostContent(article.getContent());
        target.setPostContentShort(article.getContentShort());
        target.setStatus(article.getStatus());
        target.setCommentDisabled(article.getCommentDisabled());
        target.setImportance(article.getImportance());
        target.setUserId(0);
        target.setUserOpenid(principal.getName());

        this.postService.save(target);
        return ResultGenerator.genOkResult();
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    @PreAuthorize("hasAuthority('user:post:update')")
    @PostMapping("/update")
    public ResultUtil update(@RequestBody ArticleDTO article) {
        Post target = this.postService.findById(article.getId());
        if (null == target) {
            return ResultGenerator.genFailedResult("该帖子已经不存在了");
        }
        System.out.println("content_short:  " + article.getContentShort() + "  -------------");
        target.setPostTitle(article.getTitle());
        target.setPostContent(article.getContent());
        target.setPostContentShort(article.getContentShort());
        target.setStatus(article.getStatus());
        target.setCommentDisabled(article.getCommentDisabled());
        target.setImportance(article.getImportance());

        this.postService.update(target);
        return ResultGenerator.genOkResult();
    }
}
