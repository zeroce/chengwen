package cn.zeroce.design.chengwen.wx.service.impl;

import cn.zeroce.design.chengwen.wx.entity.WxUser;
import cn.zeroce.design.chengwen.wx.mapper.WxUserMapper;
import cn.zeroce.design.chengwen.wx.service.WxUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zeroce
 * @date 20.4.24 20:30
 */
@Service
public class WxUserServiceImpl implements WxUserService {
    @Resource
    private WxUserMapper wxUserMapper;

    @Override
    public WxUser findByOpenid(String openid) {
        return this.wxUserMapper.selectByPrimaryKey(openid);
    }
}
