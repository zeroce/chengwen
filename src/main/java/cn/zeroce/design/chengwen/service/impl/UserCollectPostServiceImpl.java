package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.entity.UserCollectPost;
import cn.zeroce.design.chengwen.entity.UserCollectPostExample;
import cn.zeroce.design.chengwen.mapper.UserCollectPostMapper;
import cn.zeroce.design.chengwen.service.UserCollectPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.5.7 16:08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserCollectPostServiceImpl implements UserCollectPostService {
    @Resource
    private UserCollectPostMapper userCollectPostMapper;

    @Override
    public List<UserCollectPost> getList(Integer collectorId, Integer collectedPostId) {

        UserCollectPostExample example = new UserCollectPostExample();
        UserCollectPostExample.Criteria criteria = example.createCriteria();
        if (null != collectorId && !collectorId.equals("")) {
            criteria.andCollectorIdEqualTo(collectorId);
        }
        if (null != collectedPostId && !collectedPostId.equals("")) {
            criteria.andCollectedPostIdEqualTo(collectedPostId);
        }
        return this.userCollectPostMapper.selectByExample(example);
    }
}
