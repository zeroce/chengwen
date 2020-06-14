package cn.zeroce.design.chengwen.wx.service;

import cn.zeroce.design.chengwen.wx.dto.reqBody.RegisterReqDTO;
import cn.zeroce.design.chengwen.wx.entity.Users;
import cn.zeroce.design.chengwen.wx.entity.WxUser;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface WxAccountService {

    /**
     * 按 NickName 验证是否存在
     * @param NickName
     * @return
     */
    boolean checkExistByNickname(String NickName);

    /**
     * 注册新微信账户
     * @param wxUserInfo
     * @return
     */
    WxUser create(Map<String, String> wxUserInfo, RegisterReqDTO registerDetails);

    /**
     * 按 openid & sessionId 查询 users
     * @param openid
     * @return
     */
    List<Users> findByOpenidAndSessionId(String openid, String sessionId);

    /**
     * 按 sessionId 查询 users
     * @param sessionId
     * @return
     */
    List<Users> checkSessionId(String sessionId);

    /**
     * 更新微信用户 users 的 sessionId
     * @param id
     * @param sessionId
     */
    void updateSessionId(Integer id, String sessionId, String sessionKey);

    /**
     * 根据 openid 验证 password
     * @param openid
     * @param password
     * @return
     */
    boolean vertifyPassword(String openid, String password);

    /**
     * 根据 openid 更新 password
     * @param openid
     * @param newPass
     */
    void updatePassword(String openid, String newPass);

    /**
     * 按 user_id 查询 users
     * @param userId
     * @return
     */
    Users findByUsersId(Integer userId);
}