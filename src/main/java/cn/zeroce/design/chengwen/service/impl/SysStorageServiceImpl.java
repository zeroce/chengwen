package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.dto.SysStorageDTO;
import cn.zeroce.design.chengwen.entity.SysStorage;
import cn.zeroce.design.chengwen.entity.SysStorageExample;
import cn.zeroce.design.chengwen.mapper.SysStorageMapper;
import cn.zeroce.design.chengwen.service.SysStorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 对外提供存储服务类，所有存储服务均由该类对外提供
 *
 * @author: zeroce
 * @date 20.4.11 15:48
 */
@Service
public class SysStorageServiceImpl implements SysStorageService {
    @Resource
    private SysStorageMapper sysStorageMapper;

    @Override
    public void create(SysStorage sysStorageInfo) {
        Date createTime = new Date();
        sysStorageInfo.setCreateTime(createTime);
        sysStorageInfo.setModifiedTime(createTime);
        this.sysStorageMapper.insertSelective(sysStorageInfo);
    }

    @Override
    public SysStorage selectByKey(String key) {
        SysStorageExample example = new SysStorageExample();
        example.createCriteria().andSKeyEqualTo(key);
        List<SysStorage> sysStorageList = this.sysStorageMapper.selectByExample(example);
        if (null != sysStorageList && !sysStorageList.isEmpty()) {
            return sysStorageList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<SysStorage> findByName(String originalFilename) {
        SysStorageExample example = new SysStorageExample();
        example.createCriteria().andSNameEqualTo(originalFilename);
        return this.sysStorageMapper.selectByExample(example);
    }

    @Override
    public void deleteById(Integer id) {
        this.sysStorageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysStorage> findByKeyName(String sKey, String sName) {
        SysStorageExample example = new SysStorageExample();
        SysStorageExample.Criteria criteria = example.createCriteria();
        if (null != sKey && !sKey.equals("")) {
            criteria.andSKeyEqualTo(sKey);
        }
        if (null != sName && !sName.equals("")) {
            criteria.andSNameEqualTo(sName);
        }
        return this.sysStorageMapper.selectByExample(example);
    }

    @Override
    public SysStorage findById(Integer id) {
        return this.sysStorageMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateName(SysStorage resource) {
        this.sysStorageMapper.updateByPrimaryKeySelective(resource);
    }
}
