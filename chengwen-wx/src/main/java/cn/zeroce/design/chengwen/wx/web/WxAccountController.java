package cn.zeroce.design.chengwen.wx.web;

import cn.zeroce.design.chengwen.wx.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.wx.core.wxAuth.WechatAuthProperties;
import cn.zeroce.design.chengwen.wx.dto.reqBody.RegisterReqDTO;
import cn.zeroce.design.chengwen.wx.dto.respData.RegisterRespDTO;
import cn.zeroce.design.chengwen.wx.entity.Users;
import cn.zeroce.design.chengwen.wx.entity.WxUser;
import cn.zeroce.design.chengwen.wx.service.WxAccountService;
import cn.zeroce.design.chengwen.wx.utils.OkHttpUtil;
import cn.zeroce.design.chengwen.wx.utils.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zeroce
 * @date 20.4.17 10:35
 */
@RestController
@RequestMapping("/wx/account")
public class WxAccountController {
    @Resource
    private WechatAuthProperties authProperties;
    @Resource
    private WxAccountService wxAccountService;
    @Resource
    private OkHttpUtil httpUtil;

    /**
     * 检查服务器 session 是否还保存
     *
     * @param sessionId
     * @return
     */
    @GetMapping("/wxhassession")
    public ResultUtil wxhassession(@RequestParam(name = "hotqinsessionid", required = false) String sessionId,
                                   HttpServletRequest request) {
        System.out.println("===> wxhassession Check start ----------------------");
        List<Users> resultList = this.wxAccountService.checkSessionId(sessionId);
        System.out.println("===> wxhassession Check End ----------------------");
        if (null != resultList && !resultList.isEmpty()) {
            return ResultGenerator.genOkResult(resultList.get(0));
        }
        return ResultGenerator.genFailedResult();
    }

    /**
     * 登录
     *
     * @param code
     * @param sessionid
     * @param request
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/login")
    public ResultUtil login(@RequestParam(name = "code", required = false) String code,
                            @RequestParam(name = "hotqinsessionid", required = false) String sessionid,
                            HttpServletRequest request) throws JsonProcessingException {
        // ResponseBody
        RegisterRespDTO responseDTO = new RegisterRespDTO();

        if (StringUtils.isBlank(code) && !StringUtils.isBlank(sessionid)) {
            List<Users> usersListBySessionId = this.wxAccountService.findByOpenidAndSessionId(null, sessionid);
            if (null == usersListBySessionId || usersListBySessionId.isEmpty()) {
                responseDTO.setUserId(null);
                return ResultGenerator.genFailedResult();
            } else {
                Users users = usersListBySessionId.get(0);
                responseDTO.setUserId(users.getUserOpenid());
                responseDTO.setAdmin(users.getIsadmin());
                responseDTO.setHasRegist(true);
                responseDTO.setPhoto(users.getUserAvatarurl());
                responseDTO.setUserInfo(users);
                return ResultGenerator.genOkResult(responseDTO);
            }
        }
        String newSessionId = request.getSession().getId();
        // 获取登录用户的 userInfo
        Map<String, String> wxUserInfo = this.getWxUserInfo(code);
        // 根据 openid 查询是否注册：
        // 1. 注册：查询注册信息，返回信息和sessionid，openid
        // 2. 未注册：返回sessionid，openid
        List<Users> usersList = this.wxAccountService.findByOpenidAndSessionId(wxUserInfo.get("openid"), sessionid);
        if (null == usersList || usersList.isEmpty()) {
            responseDTO.setUserId(null);
        } else {
            Users users = usersList.get(0);
            responseDTO.setUserId(users.getUserOpenid());
            responseDTO.setAdmin(users.getIsadmin());
            responseDTO.setHasRegist(true);
            responseDTO.setPhoto(users.getUserAvatarurl());
            responseDTO.setUserInfo(users);
            // 更新 sessionId
            this.wxAccountService.updateSessionId(users.getId(), newSessionId, wxUserInfo.get("session_key"));
        }
        responseDTO.setSessionId(newSessionId);
        return ResultGenerator.genOkResult(responseDTO);
    }

    /**
     * 注册
     *
     * @param registerDetails
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResultUtil register(@RequestBody RegisterReqDTO registerDetails,
                               HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        registerDetails.setSessionId(sessionId);

        // 获取 openid, session_key
        String code = registerDetails.getCode();
        Map<String, String> wxUserInfo = this.getWxUserInfo(code);

        WxUser wxUser = this.wxAccountService.create(wxUserInfo, registerDetails);

        RegisterRespDTO response = new RegisterRespDTO();
        response.setHasRegist(true);
        response.setAdmin(false);
        response.setSessionId(sessionId);
        response.setUserId(wxUser.getOpenid());
        response.setPhoto(registerDetails.getAvatarUrl());

        System.out.println("===> End: register ----------------");
        return ResultGenerator.genOkResult(response);
    }

    /**
     * 判断账号名是否被注册
     *
     * @param loginName
     * @return
     */
    @GetMapping("/checkLoginName")
    public ResultUtil checkLoginName(@RequestParam(name = "loginName") String loginName) {
        if (this.wxAccountService.checkExistByNickname(loginName)) {
            return null;
        }
        System.out.println("===> End: checkLoginName ----------------");
        return ResultGenerator.genFailedResult("loginName 不存在，可注册");
    }

    /**
     * 修改密码
     *
     * @param sessionId
     * @param oldPass
     * @param newPass
     * @param openid
     * @return
     */
    @PostMapping("/updatewxpassword")
    public ResultUtil updatewxpassword(@RequestParam(name = "hotqinsessionid") String sessionId,
                                       @RequestParam(name = "oldpass", required = true) String oldPass,
                                       @RequestParam(name = "newpass", required = true) String newPass,
                                       @RequestParam(name = "uid", required = true) String openid) {
        List<Users> usersList = this.wxAccountService.findByOpenidAndSessionId(openid, sessionId);
        if (null != usersList && !usersList.isEmpty()) {
            if (this.wxAccountService.vertifyPassword(openid, oldPass)) {
                this.wxAccountService.updatePassword(openid, newPass);
                return ResultGenerator.genOkResult();
            } else {
                return ResultGenerator.genFailedResult("密码不正确！修改密码失败");
            }
        }
        return ResultGenerator.genFailedResult("登录过时！请重新登录");
    }

    /**
     * 根据 code 发送 http 请求
     * 获取微信用户 userInfo
     *
     * @param code
     * @return
     */
    public Map<String, String> getWxUserInfo(String code) {
        Map<String, String> map = new HashMap<>(4);
        map.put("appid", this.authProperties.getAppId());
        map.put("secret", this.authProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", this.authProperties.getGrantType());
        String response = httpUtil.get(this.authProperties.getSessionHost(), map);

        JSONObject jsonObject = JSONObject.parseObject(response);

        Map<String, String> result = new HashMap<>(2);
        result.put("openid", jsonObject.getString("openid"));
        result.put("session_key", jsonObject.getString("session_key"));
        return result;
    }
}
