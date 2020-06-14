package cn.zeroce.design.chengwen.wx.web;

import cn.zeroce.design.chengwen.wx.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.wx.core.storage.WxPublicStorageService;
import cn.zeroce.design.chengwen.wx.core.storage.WxStorage;
import cn.zeroce.design.chengwen.wx.entity.PostStorage;
import cn.zeroce.design.chengwen.wx.service.WxPostStorageService;
import cn.zeroce.design.chengwen.wx.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.22 22:29
 */
@RestController
@RequestMapping("/wx/storage")
public class WxStorageController {
    @Resource
    private WxPublicStorageService wxPublicStorageService;
    @Resource
    private WxPostStorageService wxPostStorageService;
    @Resource
    private WxStorage wxStorage;

    /**
     * 创建新存储记录
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/wxpostsimg")
    public ResultUtil wxpostsimg(@RequestParam(name = "file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        List<PostStorage> postStorageList = this.wxPostStorageService.findByName(originalFilename);
        if (null != postStorageList && !postStorageList.isEmpty()) {
            PostStorage existPostStorage = postStorageList.get(0);
            if (!existPostStorage.getDeleted()) {
                return ResultGenerator.genOkResult(existPostStorage);
            } else {
                this.wxPostStorageService.deleteByPKey(existPostStorage.getpKey());
            }
        }
        PostStorage target = this.wxPublicStorageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);

        return ResultGenerator.genOkResult(target);
    }

}
