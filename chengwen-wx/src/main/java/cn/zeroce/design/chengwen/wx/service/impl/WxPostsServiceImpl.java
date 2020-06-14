package cn.zeroce.design.chengwen.wx.service.impl;

import cn.zeroce.design.chengwen.wx.dto.reqBody.WxPostCreateReqBody;
import cn.zeroce.design.chengwen.wx.dto.respData.WxGetPostDetailsDTO;
import cn.zeroce.design.chengwen.wx.dto.respData.WxPostRespDTO;
import cn.zeroce.design.chengwen.wx.entity.*;
import cn.zeroce.design.chengwen.wx.mapper.*;
import cn.zeroce.design.chengwen.wx.service.WxPostsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.22 15:58
 */
@Service
@Transactional
public class WxPostsServiceImpl implements WxPostsService {
    @Resource
    private PostMapper wxPostMapper;
    @Resource
    private ExtraPostMapper wxExtraPostMapper;
    @Resource
    private PostImageUrlsMapper wxPostImageUrlsMapper;
    @Resource
    private ExtraPostImageUrlsMapper wxExtraPostImageUrlsMapper;
    @Resource
    private PostDetailsMapper wxPostDetailsMapper;
    @Resource
    private WxUserMapper wxUserMapper;

    @Override
    public List<Post> findPageList(String keyWord, String userId) {
        PostExample example = new PostExample();
        PostExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(keyWord)) {
            // 内容检索
            // Todo ...
        }
        if (!StringUtils.isBlank(userId)) {
            criteria.andUserOpenidEqualTo(userId);
        }
        return this.wxPostMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public Post create(WxPostCreateReqBody reqBody, Users wxUsers) {
        Post record = new Post();
        // title 和 content contentShort
        record.setPostTitle(reqBody.getTitle());
        String content = reqBody.getContent();
        record.setPostContent(content);
        record.setPostContentShort(content.substring(0, 5));
        // time
        record.setCreateTime(new Date());
        record.setModifiedTime(new Date());
        // user_id
        record.setUserId(wxUsers.getId());
        record.setUserOpenid(wxUsers.getUserOpenid());
        // id
        record.setId(this.wxExtraPostMapper.selectMaxId() + 1);
        this.wxPostMapper.insertSelective(record);

        // 文章相关信息保存
        PostDetails postDetailsRecord = new PostDetails();
        postDetailsRecord.setPostId(record.getId());
        this.wxPostDetailsMapper.insertSelective(postDetailsRecord);

        return record;
    }

    @Override
    public Post findByTitleUserId(String title, String openid) {
        PostExample example = new PostExample();
        example.createCriteria().andPostTitleEqualTo(title).andUserOpenidEqualTo(openid);
        List<Post> posts = this.wxPostMapper.selectByExampleWithBLOBs(example);
        Post target = posts.get(0);
        return target;
    }

    @Override
    public WxGetPostDetailsDTO findByPostId(Integer postId) {
        WxGetPostDetailsDTO result = new WxGetPostDetailsDTO();

        Post post = this.wxPostMapper.selectByPrimaryKey(postId);
        result.setPost(post);

        PostDetails postDetails = this.wxPostDetailsMapper.selectByPrimaryKey(postId);
        result.setPostDetails(postDetails);

        WxUser wxUser = this.wxUserMapper.selectByPrimaryKey(post.getUserOpenid());
        result.setAuthor(wxUser.getNickname());

        result.setImageUrls(this.wxExtraPostImageUrlsMapper.selectUrlsByPostId(postId));

        return result;
    }

    @Override
    public WxPostCreateReqBody getByPostId(Integer postId) {
        Post post = this.wxPostMapper.selectByPrimaryKey(postId);
        WxPostCreateReqBody reqBody = new WxPostCreateReqBody();
        reqBody.setTitle(post.getPostTitle());
        reqBody.setContent(post.getPostContent());
        reqBody.setImages(this.wxExtraPostImageUrlsMapper.selectUrlsByPostId(postId));

        // 删除已上传的图片记录
        PostImageUrlsExample example = new PostImageUrlsExample();
        example.createCriteria().andPostIdEqualTo(postId);
        this.wxPostImageUrlsMapper.deleteByExample(example);

        return reqBody;
    }

    @Override
    public void updateByPostId(Integer postId, WxPostCreateReqBody reqBody) {
        PostExample example = new PostExample();
        example.createCriteria().andIdEqualTo(postId);
        Post record = new Post();
        record.setPostTitle(reqBody.getTitle());
        record.setPostContent(reqBody.getContent());
        record.setPostContentShort(reqBody.getContent().substring(0,30));
        Date nowTime = new Date();
        record.setModifiedTime(nowTime);
        this.wxPostMapper.updateByExampleSelective(record, example);

        for (String imageUrl :
                reqBody.getImages()) {
            PostImageUrls postImageUrls = new PostImageUrls();
            postImageUrls.setCreateTime(nowTime);
            postImageUrls.setPostId(postId);
            postImageUrls.setImageUrl(imageUrl);
            this.wxPostImageUrlsMapper.insertSelective(postImageUrls);
        }
    }

    @Override
    public void deleteByPostId(Integer postId) {
        this.wxPostMapper.deleteByPrimaryKey(postId);
    }
}
