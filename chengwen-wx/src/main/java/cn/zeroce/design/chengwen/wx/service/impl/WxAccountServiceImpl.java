package cn.zeroce.design.chengwen.wx.service.impl;

import cn.zeroce.design.chengwen.wx.dto.reqBody.RegisterReqDTO;
import cn.zeroce.design.chengwen.wx.entity.Users;
import cn.zeroce.design.chengwen.wx.entity.UsersExample;
import cn.zeroce.design.chengwen.wx.entity.WxUser;
import cn.zeroce.design.chengwen.wx.entity.WxUserExample;
import cn.zeroce.design.chengwen.wx.mapper.UsersMapper;
import cn.zeroce.design.chengwen.wx.mapper.WxUserMapper;
import cn.zeroce.design.chengwen.wx.service.WxAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: zeroce
 * @date 20.4.19 0:04
 */
@Service
@Transactional
public class WxAccountServiceImpl implements WxAccountService {
    @Resource
    private UsersMapper usersMapper;

    @Resource
    private WxUserMapper wxUserMapper;

    @Override
    public boolean checkExistByNickname(String nickName) {
        WxUserExample example = new WxUserExample();
        example.createCriteria().andNicknameEqualTo(nickName);
        List<WxUser> wxUserList = this.wxUserMapper.selectByExample(example);
        if (null != wxUserList && !wxUserList.isEmpty()) return true;
        return false;
    }

    @Override
    public WxUser create(Map<String, String> wxUserInfo, RegisterReqDTO registerDetails) {
        WxUser record = new WxUser();
        record.setOpenid(wxUserInfo.get("openid"));
        record.setSessionKey(wxUserInfo.get("session_key"));
        record.setNickname(registerDetails.getUname());
        record.setPassword(registerDetails.getPassword());
        this.wxUserMapper.insert(record);

        Users users = new Users();
        users.setLanguage("zh_CN");
        users.setUserNickname(registerDetails.getNickName());
        users.setUserAvatarurl(registerDetails.getAvatarUrl());
        users.setUserGender(registerDetails.getGender().byteValue());
        users.setCreateTime(new Date());
        users.setModifiedTime(new Date());
        users.setUserOpenid(record.getOpenid());
        users.setSessionKey(record.getSessionKey());
        users.setIsadmin(false);
        users.setSessionId(registerDetails.getSessionId());

        this.usersMapper.insert(users);

        return record;
    }

    @Override
    public List<Users> findByOpenidAndSessionId(String openid, String sessionId) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if (null != openid && !openid.isEmpty()) {
            criteria.andUserOpenidEqualTo(openid);
        }
        if (null != sessionId && !sessionId.isEmpty()) {
            criteria.andSessionIdEqualTo(sessionId);
        }
        return this.usersMapper.selectByExample(example);
    }

    @Override
    public List<Users> checkSessionId(String sessionId) {
        UsersExample example = new UsersExample();
        example.createCriteria().andSessionIdEqualTo(sessionId);
        return this.usersMapper.selectByExample(example);
    }

    @Override
    public void updateSessionId(Integer id, String sessionId, String sessionKey) {
        UsersExample example = new UsersExample();
        example.createCriteria().andIdEqualTo(id);
        Users record = new Users();
        record.setSessionId(sessionId);
        record.setModifiedTime(new Date());
        record.setSessionKey(sessionKey);
        this.usersMapper.updateByExampleSelective(record, example);
    }

    @Override
    public boolean vertifyPassword(String openid, String password) {
        WxUser wxUser = this.wxUserMapper.selectByPrimaryKey(openid);
        if (wxUser.getPassword().equals(password)) return true;
        return false;
    }

    @Override
    public void updatePassword(String openid, String newPass) {
        WxUserExample example = new WxUserExample();
        example.createCriteria().andOpenidEqualTo(openid);
        WxUser record = new WxUser();
        record.setPassword(newPass);
        this.wxUserMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Users findByUsersId(Integer userId) {
        return this.usersMapper.selectByPrimaryKey(userId);
    }
}