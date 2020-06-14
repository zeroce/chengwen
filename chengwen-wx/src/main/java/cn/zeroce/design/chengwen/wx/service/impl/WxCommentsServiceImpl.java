package cn.zeroce.design.chengwen.wx.service.impl;

import cn.zeroce.design.chengwen.wx.dto.reqBody.WxCommentsCreateReqDTO;
import cn.zeroce.design.chengwen.wx.dto.respData.WxCommentsDTO;
import cn.zeroce.design.chengwen.wx.entity.*;
import cn.zeroce.design.chengwen.wx.mapper.CommentsMapper;
import cn.zeroce.design.chengwen.wx.mapper.ExtraCommentsMapper;
import cn.zeroce.design.chengwen.wx.mapper.UserThumbupCommentMapper;
import cn.zeroce.design.chengwen.wx.service.WxAccountService;
import cn.zeroce.design.chengwen.wx.service.WxCommentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.25 16:08
 */
@Service
public class WxCommentsServiceImpl implements WxCommentsService {
    @Resource
    private CommentsMapper wxCommentsMapper;
    @Resource
    private ExtraCommentsMapper wxExtraCommentsMapper;
    @Resource
    private UserThumbupCommentMapper wxUserThumbupCommentMapper;
    @Resource
    private WxAccountService wxAccountService;

    @Override
    public List<WxCommentsDTO> selectByPostId(Integer postId) {
        List<WxCommentsDTO> resultList = new ArrayList<>();

        CommentsExample example = new CommentsExample();
        example.createCriteria().andCommentPostIdEqualTo(postId);
        List<Comments> commentsList = this.wxCommentsMapper.selectByExampleWithBLOBs(example);
        for (Comments comments :
                commentsList) {
            WxCommentsDTO wxCommentsDTO = new WxCommentsDTO();
            wxCommentsDTO.setAdmin(false);
            wxCommentsDTO.setUsers(this.wxAccountService.findByUsersId(comments.getCommentUserId()));
            wxCommentsDTO.setComments(comments);
            wxCommentsDTO.setId(comments.getId());

            UserThumbupCommentExample example1 = new UserThumbupCommentExample();
            example1.createCriteria().andCommentIdEqualTo(comments.getId());
            wxCommentsDTO.setLikeCount(this.wxUserThumbupCommentMapper.selectByExample(example1).size());

            resultList.add(wxCommentsDTO);
        }
        return resultList;
    }

    @Override
    public Comments save(Integer postId, WxCommentsCreateReqDTO requestDTO, Users users) {
        Comments record = new Comments();
        Integer maxId = this.wxExtraCommentsMapper.selectMaxId();
        if (null == maxId) {
            record.setId(1);
        } else {
            record.setId(maxId + 1);
        }
        record.setCommentPostId(postId);
        record.setCommentContentText(requestDTO.getContent());
        record.setCommentUserId(users.getId());
        record.setCommentSuperiorId(0);
        record.setCreateTime(new Date());
        this.wxCommentsMapper.insertSelective(record);
        return record;
    }
}
