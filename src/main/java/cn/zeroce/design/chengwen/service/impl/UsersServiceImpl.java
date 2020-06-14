package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.entity.Users;
import cn.zeroce.design.chengwen.entity.UsersExample;
import cn.zeroce.design.chengwen.mapper.UsersMapper;
import cn.zeroce.design.chengwen.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> getList(String username, String mobile) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if (null != username && !username.equals("")) {
            criteria.andUserNicknameEqualTo(username);
        }
        if (null != mobile && !mobile.equals("")) {
            criteria.andUserTelnumEqualTo(mobile);
        }
        return this.usersMapper.selectByExample(example);
    }

    @Override
    public List<Users> getByUsername(String username) {
        UsersExample example = new UsersExample();
        if (null != username && username.length() > 0) {
            example.createCriteria().andUserNicknameEqualTo(username);
        }
        return this.usersMapper.selectByExample(example);
    }

    @Override
    public Users getById(Integer userId) {
        if (null != userId && userId != 0) {
            Users users = this.usersMapper.selectByPrimaryKey(userId);
            return users;
        } else {
            Users admin = new Users();
            admin.setId(0);
            admin.setUserNickname("管理员");
            admin.setLanguage("CN");
            admin.setIsadmin(true);

            return admin;
        }
    }
}
