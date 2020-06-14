package cn.zeroce.design.chengwen.wx.service.impl;

import cn.zeroce.design.chengwen.wx.entity.UserCollectPost;
import cn.zeroce.design.chengwen.wx.entity.UserCollectPostExample;
import cn.zeroce.design.chengwen.wx.mapper.UserCollectPostMapper;
import cn.zeroce.design.chengwen.wx.service.WxUserPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.25 17:09
 */
@Service
public class WxUserPostServiceImpl implements WxUserPostService {
    @Resource
    private UserCollectPostMapper wxUserCollectPostMapper;

    @Override
    public Boolean checkByUserIdPostId(Integer id, Integer postId) {
        UserCollectPostExample example = new UserCollectPostExample();
        example.createCriteria().andCollectorIdEqualTo(id).andCollectedPostIdEqualTo(postId);
        List<UserCollectPost> userCollectPosts = this.wxUserCollectPostMapper.selectByExample(example);
        if (null != userCollectPosts && !userCollectPosts.isEmpty()) {
            return true;
        } else return false;

    }
}
