package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.entity.PostDetails;
import cn.zeroce.design.chengwen.entity.PostDetailsExample;
import cn.zeroce.design.chengwen.mapper.PostDetailsMapper;
import cn.zeroce.design.chengwen.service.PostDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.5.7 21:23
 */
@Service
public class PostDetailsServiceImpl implements PostDetailsService {
    @Resource
    private PostDetailsMapper postDetailsMapper;

    @Override
    public List<PostDetails> getList(Integer postId) {
        PostDetailsExample example = new PostDetailsExample();
        PostDetailsExample.Criteria criteria = example.createCriteria();
        if (null != postId && postId > 0) {
            criteria.andPostIdEqualTo(postId);
        }
        return this.postDetailsMapper.selectByExample(example);
    }
}
