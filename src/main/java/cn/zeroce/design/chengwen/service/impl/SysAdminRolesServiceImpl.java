package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.entity.SysAdminRoles;
import cn.zeroce.design.chengwen.entity.SysAdminRolesExample;
import cn.zeroce.design.chengwen.mapper.SysAdminRolesMapper;
import cn.zeroce.design.chengwen.service.SysAdminRolesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.3.22 23:03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysAdminRolesServiceImpl implements SysAdminRolesService {
    @Resource
    private SysAdminRolesMapper sysAdminRolesMapper;

    @Override
    public SysAdminRoles selectById(Integer id) {
        return sysAdminRolesMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateSysAdminRolesById(SysAdminRoles record) {
        SysAdminRolesExample example = new SysAdminRolesExample();
        example.createCriteria().andIdEqualTo(record.getId());
        record.setSysRolesId(null);
        record.setSysAdminUsername(null);
        record.setSysAdminId(null);
        sysAdminRolesMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<SysAdminRoles> selectByRolesId(Integer roleId) {
        SysAdminRolesExample example = new SysAdminRolesExample();
        example.createCriteria().andSysRolesIdEqualTo(roleId);
        return this.sysAdminRolesMapper.selectByExample(example);
    }
}
