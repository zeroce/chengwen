package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.entity.Post;
import cn.zeroce.design.chengwen.entity.PostExample;
import cn.zeroce.design.chengwen.mapper.ExtraPostMapper;
import cn.zeroce.design.chengwen.mapper.PostMapper;
import cn.zeroce.design.chengwen.mapper.UsersMapper;
import cn.zeroce.design.chengwen.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service

public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Resource
    private ExtraPostMapper extraPostMapper;
    @Resource
    private UsersMapper usersMapper;

    @Override
    public List<Post> findByTitleUsername(String title, Integer authorId) {
        PostExample example = new PostExample();
        PostExample.Criteria criteria = example.createCriteria();
        if (null != title && !title.equals("")) {
            criteria.andPostTitleEqualTo(title);
        }
        if (null != authorId && authorId != 0) {
            criteria.andUserIdEqualTo(authorId);
        }
        return this.postMapper.selectByExample(example);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Post findById(Integer id) {
        Post post = postMapper.selectByPrimaryKey(id);
        return post;
    }

    @Override
    public void deleteByIdTitle(Post post) {
        PostExample example = new PostExample();
        example.createCriteria().andIdEqualTo(post.getId()).andPostTitleEqualTo(post.getPostTitle());
        this.postMapper.deleteByExample(example);
    }

    @Override
    public void save(Post target) {
        target.setId(this.extraPostMapper.selectMaxId() + 1);
        target.setCreateTime(new Date());
        target.setModifiedTime(new Date());
        target.setForecast(1f);
        target.setType("CN");
        this.postMapper.insertSelective(target);
    }

    @Override
    public void update(Post target) {
        target.setModifiedTime(new Date());
        PostExample example = new PostExample();
        example.createCriteria().andIdEqualTo(target.getId());
        this.postMapper.updateByExampleSelective(target, example);
    }
}
