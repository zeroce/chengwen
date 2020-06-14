package cn.zeroce.design.chengwen.wx.service.impl;

import cn.zeroce.design.chengwen.wx.entity.PostStorage;
import cn.zeroce.design.chengwen.wx.entity.PostStorageExample;
import cn.zeroce.design.chengwen.wx.mapper.PostStorageMapper;
import cn.zeroce.design.chengwen.wx.service.WxPostStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 对外提供存储服务类，所有存储服务均由该类对外提供
 *
 * @author: zeroce
 * @date 20.4.22 23:08
 */
@Service
@Transactional
public class WxPostStorageServiceImpl implements WxPostStorageService {
    @Resource
    private PostStorageMapper postStorageMapper;

    @Override
    public void create(PostStorage postStorageInfo) {
        Date createTime = new Date();
        postStorageInfo.setCreateTime(createTime);
        postStorageInfo.setModifiedTime(createTime);
        this.postStorageMapper.insertSelective(postStorageInfo);
    }

    @Override
    public PostStorage selectByKey(String pkey) {
        PostStorage postStorage = this.postStorageMapper.selectByPrimaryKey(pkey);
        return postStorage;
    }

    @Override
    public List<PostStorage> findByName(String originalFilename) {
        PostStorageExample example = new PostStorageExample();
        example.createCriteria().andPNameEqualTo(originalFilename);
        return this.postStorageMapper.selectByExample(example);
    }

    @Override
    public void deleteByPKey(String pKey) {
        this.postStorageMapper.deleteByPrimaryKey(pKey);
    }

    @Override
    public List<PostStorage> findByKeyName(String pKey, String pName) {
        PostStorageExample example = new PostStorageExample();
        PostStorageExample.Criteria criteria = example.createCriteria();
        if (null != pKey && !pKey.equals("")) {
            criteria.andPKeyEqualTo(pKey);
        }
        if (null != pName && !pName.equals("")) {
            criteria.andPNameEqualTo(pName);
        }
        return this.postStorageMapper.selectByExample(example);
    }

    @Override
    public PostStorage findByPKey(String pKey) {
        return this.postStorageMapper.selectByPrimaryKey(pKey);
    }

    @Override
    public void updateName(PostStorage resource) {
        this.postStorageMapper.updateByPrimaryKeySelective(resource);
    }
}
