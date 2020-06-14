package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.entity.SysRolesPermission;
import cn.zeroce.design.chengwen.entity.SysRolesPermissionExample;
import cn.zeroce.design.chengwen.mapper.SysRolesPermissionMapper;
import cn.zeroce.design.chengwen.service.SysRolesPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author: zeroce
 * @date 20.5.5 23:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRolesPermissionServiceImpl implements SysRolesPermissionService {
    @Resource
    private SysRolesPermissionMapper sysRolesPermissionMapper;
    
    
    @Override
    public void grantPerms(Integer roleId, Set<Integer> permissions) {
        SysRolesPermissionExample example = new SysRolesPermissionExample();
        example.createCriteria().andSysRolesIdEqualTo(roleId);
        this.sysRolesPermissionMapper.deleteByExample(example);

        for (Integer permId :
                permissions) {
            SysRolesPermission record = new SysRolesPermission();
            record.setSysRolesId(roleId);
            record.setSysPermissionId(permId);
            this.sysRolesPermissionMapper.insertSelective(record);
        }
    }
}
