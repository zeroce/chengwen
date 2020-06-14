package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.dto.SysAdminDTO;
import cn.zeroce.design.chengwen.dto.SysAdminWithRolePermissionDTO;
import cn.zeroce.design.chengwen.dto.SysAdminWithRolesDTO;
import cn.zeroce.design.chengwen.entity.SysAdmin;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;

public interface AccountService {
    /**
     * 保存用户
     *
     * @param accountDTO
     */
    void save(SysAdminDTO accountDTO);

    /**
     * 按 ID 查询用户
     *
     * @param id
     * @return
     */
    SysAdmin getById(Integer id);

    /**
     * 根据 ID 删除用户
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 验证用户密码
     *
     * @param rawPassword 原密码
     * @param encodedPassword 加密后的密码
     * @return
     */
    boolean verifyPassword(String rawPassword, String encodedPassword);

    /**
     * 根据 ID 更新用户资料
     * @param account
     */
    void update(SysAdminDTO account);

    /**
     * 按 username 查询用户信息
     *
     * @param username
     * @return
     */
    SysAdmin getSysAdminByName(String username);

    /**
     * 按用户名查询用户权限信息
     *
     * @param name 用户名
     * @return 用户角色拥有的权限
     * @throws UsernameNotFoundException 用户名找不到
     */
    SysAdminWithRolePermissionDTO findPerDetailByName(String name) throws UsernameNotFoundException;

    /**
     * 按用户名查询用户角色信息
     *
     * @param username
     * @return 用户的角色
     * @throws UsernameNotFoundException 用户名找不到
     */
    SysAdminWithRolesDTO findRolesDetaiByName(String username) throws UsernameNotFoundException;

    /**
     * 按条件查询用户信息
     *
     * @param column 列名
     * @param params 参数
     * @return 用户
     */
    SysAdminWithRolePermissionDTO findDetailBy(String column, Object params);

    /**
     * 获取所有用户以及对应角色
     *
     * @return 用户列表
     */
    List<SysAdminWithRolesDTO> listAllWithRolesId(String username);

    /**
     * 按条件查询用户
     *
     * @param params
     * @return
     */
    List<SysAdminWithRolesDTO> findWithRoleBy(Map<String, Object> params);

    /**
     * 按用户名更新最后一次登录时间
     *
     * @param name 用户名
     */
    void updateLoginTimeByName(String name);
}
