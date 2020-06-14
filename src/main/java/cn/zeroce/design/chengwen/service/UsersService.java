package cn.zeroce.design.chengwen.service;

import cn.zeroce.design.chengwen.entity.Users;

import java.util.List;

public interface UsersService {
    /**
     * 按 username | mobile 查询用户信息
     * @param username
     * @param mobile
     * @return
     */
    List<Users> getList(String username, String mobile);

    /**
     * 按 username 查找用户
     * @param username
     * @return
     */
    List<Users> getByUsername(String username);

    /**
     * 按 id 查找用户
     * @param userId
     * @return
     */
    Users getById(Integer userId);
}
