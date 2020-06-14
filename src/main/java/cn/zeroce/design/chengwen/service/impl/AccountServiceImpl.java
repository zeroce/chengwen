package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.core.exception.ServiceException;
import cn.zeroce.design.chengwen.dto.SysAdminDTO;
import cn.zeroce.design.chengwen.dto.SysAdminWithRolePermissionDTO;
import cn.zeroce.design.chengwen.dto.SysAdminWithRolesDTO;
import cn.zeroce.design.chengwen.entity.*;
import cn.zeroce.design.chengwen.mapper.SysAdminMapper;
import cn.zeroce.design.chengwen.mapper.SysAdminRolesMapper;
import cn.zeroce.design.chengwen.mapper.SysPermissionMapper;
import cn.zeroce.design.chengwen.service.AccountService;
import cn.zeroce.design.chengwen.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: zeroce
 * @date 20.3.20 13:58
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {
    @Resource
    private SysAdminMapper sysAdminMapper;
    @Resource
    private SysAdminRolesMapper sysAdminRolesMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    // 普通用户角色Id
    private final Integer defaultRoleId = 3;


    @Override
    public void save(SysAdminDTO accountDTO) {
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(accountDTO.getUsername());
        List<SysAdmin> sysAdmins = sysAdminMapper.selectByExample(example);
        if (null == sysAdmins || sysAdmins.isEmpty()) {
            accountDTO.setPassword(passwordEncoder.encode((accountDTO.getPassword().trim())));
            SysAdmin target = new SysAdmin();
            BeanUtils.copyProperties(accountDTO, target);
            target.setToken(target.getUsername() + "-token");
            this.sysAdminMapper.insert(target);

            SysAdminExample targetExample = new SysAdminExample();
            targetExample.createCriteria().andUsernameEqualTo(target.getUsername());
            List<SysAdmin> sysAdminList = this.sysAdminMapper.selectByExample(targetExample);
            SysAdminRoles source = new SysAdminRoles();
            source.setSysAdminId(sysAdminList.get(0).getId());
            source.setSysAdminUsername(sysAdminList.get(0).getUsername());
            this.saveAdminRoles(source, accountDTO.getRoleIds());
        } else {
            throw new ServiceException("用户名已存在");
        }
    }

    private void saveAdminRoles(SysAdminRoles target, List<Integer> roleIds) {
        if (null == roleIds || roleIds.isEmpty()) {
            roleIds.add(this.defaultRoleId);
        }
        for (Integer roleId : roleIds) {
            target.setSysRolesId(roleId);
            sysAdminRolesMapper.insert(target);
        }
    }

    @Override
    public SysAdmin getById(Integer id) {
        return sysAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        this.sysAdminMapper.deleteByPrimaryKey(id);
        SysAdminRolesExample example = new SysAdminRolesExample();
        example.createCriteria().andSysAdminIdEqualTo(id);
        this.sysAdminRolesMapper.deleteByExample(example);
    }

    @Override
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public void update(SysAdminDTO account) {
        SysAdmin target = new SysAdmin();
        BeanUtils.copyProperties(account, target);
        this.sysAdminMapper.updateByPrimaryKeySelective(target);
    }

    @Override
    public SysAdmin getSysAdminByName(String username) throws UsernameNotFoundException {
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysAdmin> sysAdmins = this.sysAdminMapper.selectByExample(example);
        if (null == sysAdmins || sysAdmins.isEmpty()) {
            //throw new UsernameNotFoundException("没有此用户");
            return null;
        }
        return sysAdmins.get(0);
    }

    @Override
    public SysAdminWithRolePermissionDTO findPerDetailByName(String username) throws UsernameNotFoundException {
        SysAdminWithRolePermissionDTO account = this.findDetailBy("username", username);
        if (null == account) {
            throw new UsernameNotFoundException("没有找到该用户名");
        }
        if ("超级管理员".equals(account.getSysRolesDTO().getRoleName())) {
            account.setPermissionCodeList(this.sysPermissionMapper.selectByExampleForCode(new SysPermission()));
        }
        return account;
    }

    @Override
    public SysAdminWithRolesDTO findRolesDetaiByName(String username) throws UsernameNotFoundException {
        Map<String, Object> map = new HashMap<>(1);
        map.put("username", username);
        SysAdminWithRolesDTO account = sysAdminMapper.findRolesDetailBy(map);
        if (null == account) {
            throw new UsernameNotFoundException("没有找到该用户名");
        }
        List<SysAdmin> publicPros = sysAdminMapper.findPublicPros(map);
        SysAdmin target = publicPros.get(0);
        System.out.println("target: " + target.getUsername());
        account.setId(target.getId());
        account.setUsername(target.getUsername());
        account.setAvatar(target.getAvatar());
        account.setName(target.getName());
        account.setIntroduction(target.getIntroduction());
        return account;
    }

    @Override
    public SysAdminWithRolePermissionDTO findDetailBy(String column, Object params) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(column, params);
        return this.sysAdminMapper.findPerDetailBy(map);
    }

    @Override
    public List<SysAdminWithRolesDTO> listAllWithRolesId(String username) {
        Map<String, Object> map = new HashMap<>(1);
        if (null != username && !username.equals("")) {
            map.put("username", username);
        }
        List<SysAdmin> publicSysAdmins = sysAdminMapper.findPublicPros(map);
        List<SysAdminWithRolesDTO> resultList = new ArrayList<>();
        for (SysAdmin account : publicSysAdmins) {
            map.clear();
            map.put("username", account.getUsername());
            SysAdminWithRolesDTO rolesDetailBy = sysAdminMapper.findRolesDetailBy(map);
            resultList.add(rolesDetailBy);
        }
        return resultList;
    }

    @Override
    public List<SysAdminWithRolesDTO> findWithRoleBy(Map<String, Object> params) {
        return sysAdminMapper.findWithRolesBy(params);
    }

    @Override
    public void updateLoginTimeByName(String name) {
        Date DBdate = DateUtil.DateFormattoDBdate(new Date());
        System.out.println(DBdate.toString());
        SysAdmin record = new SysAdmin();
        record.setModifiedTime(DBdate);
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(name);
        sysAdminMapper.updateByExampleSelective(record, example);
    }


}
