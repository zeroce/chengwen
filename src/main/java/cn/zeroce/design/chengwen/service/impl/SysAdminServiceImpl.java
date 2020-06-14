package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.entity.SysAdmin;
import cn.zeroce.design.chengwen.entity.SysAdminExample;
import cn.zeroce.design.chengwen.mapper.SysAdminMapper;
import cn.zeroce.design.chengwen.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Override
    public List<SysAdmin> selectByUsername(String username) {
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysAdmin> sysAdminList = this.sysAdminMapper.selectByExample(example);
        if (null != sysAdminList && !sysAdminList.isEmpty()) {
            return sysAdminList;
        } else {
            return null;
        }
    }

    @Override
    public boolean isExist(String username) {
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysAdmin> sysAdminList = this.sysAdminMapper.selectByExample(example);
        return (null!= sysAdminList && !sysAdminList.isEmpty());
    }
}
