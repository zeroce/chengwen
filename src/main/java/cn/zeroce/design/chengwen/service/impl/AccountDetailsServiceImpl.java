package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.dto.SysAdminWithRolePermissionDTO;
import cn.zeroce.design.chengwen.dto.SysAdminWithRolesDTO;
import cn.zeroce.design.chengwen.service.AccountService;
import cn.zeroce.design.chengwen.service.PermissionService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zeroce
 * @date 20.3.19 11:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountDetailsServiceImpl implements UserDetailsService {
    @Resource
    private AccountService accountService;

    @Resource
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysAdminWithRolesDTO rolesDetaiByName = this.accountService.findRolesDetaiByName(name);
        List<String> roles = rolesDetaiByName.getRoles();
        String role = roles.contains("admin") ? "admin" : roles.get(0);
        List<String> permissionCodeList = this.permissionService.findPerListByUserName(name);
        // final SysAdminWithRolePermissionDTO adminWithRolePermissionDTO = this.accountService.findPerDetailByName(name);
        // 权限
        final List<SimpleGrantedAuthority> authorities = permissionCodeList.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        // 角色
        authorities.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(
                rolesDetaiByName.getUsername(), "", authorities);
    }
}
