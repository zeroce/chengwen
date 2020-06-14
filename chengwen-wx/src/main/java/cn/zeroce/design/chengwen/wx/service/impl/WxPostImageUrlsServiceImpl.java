package cn.zeroce.design.chengwen.wx.service.impl;

import cn.zeroce.design.chengwen.wx.entity.PostImageUrls;
import cn.zeroce.design.chengwen.wx.mapper.ExtraPostImageUrlsMapper;
import cn.zeroce.design.chengwen.wx.mapper.PostImageUrlsMapper;
import cn.zeroce.design.chengwen.wx.service.WxPostImageUrlsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.24 11:25
 */
@Service
@Transactional
public class WxPostImageUrlsServiceImpl implements WxPostImageUrlsService {
    @Resource
    private PostImageUrlsMapper postImageUrlsMapper;
    @Resource
    private ExtraPostImageUrlsMapper extraPostImageUrlsMapper;

    @Override
    public void saveImageUrlList(List<String> images, Integer id) {
        for (String imageUrl :
                images) {
            PostImageUrls record = new PostImageUrls();
            record.setPostId(id);
            record.setImageUrl(imageUrl);
            record.setCreateTime(new Date());

            this.postImageUrlsMapper.insertSelective(record);
        }
    }

    @Override
    public List<String> findUrlsByPostId(Integer postId) {
        return this.extraPostImageUrlsMapper.selectUrlsByPostId(postId);
    }
}
