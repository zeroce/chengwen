package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.entity.SysAdminRolesExample;
import cn.zeroce.design.chengwen.entity.SysRoles;
import cn.zeroce.design.chengwen.entity.SysRolesExample;
import cn.zeroce.design.chengwen.mapper.SysAdminRolesMapper;
import cn.zeroce.design.chengwen.mapper.SysRolesMapper;
import cn.zeroce.design.chengwen.service.SysRolesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zeroce
 * @date 20.3.22 23:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRolesServiceImpl implements SysRolesService {
    @Resource
    private SysRolesMapper sysRolesMapper;
    @Resource
    private SysAdminRolesMapper sysAdminRolesMapper;

    @Override
    public List<SysRoles> listAll(String name) {
        Map<String, Object> map = new HashMap<>(1);
        if (null != name && !name.equals("")) {
            map.put("name", name);
            return this.sysRolesMapper.selectAllBy(map);
        } else {
            return this.sysRolesMapper.selectAllBy(map);
        }
    }

    @Override
    public void save(SysRoles sysRole) {
        this.sysRolesMapper.insert(sysRole);
    }

    @Override
    public boolean checkExist(SysRoles sysRoles) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("id", sysRoles.getId());
        map.put("name", sysRoles.getName());
        List<SysRoles> sysRolesList = this.sysRolesMapper.selectAllBy(map);
        if (sysRolesList != null && !sysRolesList.isEmpty()) return true;
        return false;
    }

    @Override
    public Integer countAll() {
        return (int)this.sysRolesMapper.countByExample(new SysRolesExample());
    }

    @Override
    public void deleteById(Integer id) {
        this.sysRolesMapper.deleteByPrimaryKey(id);
        SysAdminRolesExample example = new SysAdminRolesExample();
        example.createCriteria().andSysRolesIdEqualTo(id);
        this.sysAdminRolesMapper.deleteByExample(example);
    }

    @Override
    public void updateById(SysRoles sysRoles) {
        SysRoles record = new SysRoles();
        record.setName(sysRoles.getName());
        SysRolesExample example = new SysRolesExample();
        example.createCriteria().andIdEqualTo(sysRoles.getId());
        this.sysRolesMapper.updateByExampleSelective(record, example);
    }

}