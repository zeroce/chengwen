package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.dto.PermsDTO;
import cn.zeroce.design.chengwen.entity.SysPermission;
import cn.zeroce.design.chengwen.mapper.ExtraSysPermissionMapper;
import cn.zeroce.design.chengwen.mapper.SysPermissionMapper;
import cn.zeroce.design.chengwen.mapper.SysRolesPermissionMapper;
import cn.zeroce.design.chengwen.service.SysPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: zeroce
 * @date 20.3.22 23:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    private SysRolesPermissionMapper sysRolesPermissionMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private ExtraSysPermissionMapper extraSysPermissionMapper;

    @Override
    public Set<Integer> findByRoleId(Integer roleId) {
        Set<Integer> permsByRoleId = new TreeSet<>();
        List<SysPermission> permissionList = this.extraSysPermissionMapper.selectPermsIdByRoleId(roleId);
        for (SysPermission permission :
                permissionList) {
            permsByRoleId.add(permission.getId());
        }
        return permsByRoleId;
    }

    @Override
    public List<PermsDTO> findAll() {
        List<PermsDTO> permsDTOList = new ArrayList<>();

        List<SysPermission> permissionList = this.extraSysPermissionMapper.selectAllPermsTitle();

        for (SysPermission permission :
                permissionList) {
            PermsDTO permsDTO = new PermsDTO();
            permsDTO.setId(permission.getId());
            permsDTO.setLabel(permission.getTitle());
            permsDTO.setChildren(this.extraSysPermissionMapper.selectPermsCodeByPid(permission.getId()));
            permsDTOList.add(permsDTO);
        }
        return permsDTOList;
    }
}
