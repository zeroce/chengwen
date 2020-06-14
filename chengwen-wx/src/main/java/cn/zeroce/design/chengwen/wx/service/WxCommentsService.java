package cn.zeroce.design.chengwen.wx.service;

import cn.zeroce.design.chengwen.wx.dto.reqBody.WxCommentsCreateReqDTO;
import cn.zeroce.design.chengwen.wx.dto.respData.WxCommentsDTO;
import cn.zeroce.design.chengwen.wx.entity.Comments;
import cn.zeroce.design.chengwen.wx.entity.Users;

import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.22 15:45
 */
public interface WxCommentsService {
    /**
     * 按 postId 查询 Comments list
     * @param postId
     * @return
     */
    List<WxCommentsDTO> selectByPostId(Integer postId);

    /**
     * 保存新建的 comments
     * @param postId
     * @param requestDTO
     * @return
     */
    Comments save(Integer postId, WxCommentsCreateReqDTO requestDTO, Users users);
}
