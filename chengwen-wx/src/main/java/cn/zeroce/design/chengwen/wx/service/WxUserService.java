package cn.zeroce.design.chengwen.wx.service;

import cn.zeroce.design.chengwen.wx.entity.WxUser;

/**
 * @author: zeroce
 * @date 20.4.24 20:29
 */
public interface WxUserService {
    /**
     * 按 openid 查询 wx_user
     * @param openid
     * @return
     */
    WxUser findByOpenid(String openid);
}
