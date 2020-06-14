package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.core.storage.PublicStorageService;
import cn.zeroce.design.chengwen.entity.SysStorage;
import cn.zeroce.design.chengwen.service.SysStorageService;
import cn.zeroce.design.chengwen.utils.ResultUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.5.25 11:49
 */
@RestController
@RequestMapping("/sys/post/upload")
public class UploadController {
    @Resource
    private SysStorageService sysStorageService;
    @Resource
    private PublicStorageService publicStorageService;

    /**
     * 上传图片
     * @param file
     * @param principal
     * @return
     * @throws IOException
     */
    @PreAuthorize("hasAuthority('user:post:create')")
    @PostMapping("/image")
    public ResultUtil image(@RequestParam(name = "file") MultipartFile file,
                            Principal principal) throws IOException {
        System.out.println("upload image -------------");
        String originalFilename = file.getOriginalFilename();
        List<SysStorage> storageList = this.sysStorageService.findByName(originalFilename);
        if (null != storageList && !storageList.isEmpty()) {
            SysStorage existSysStorage = storageList.get(0);
            if (!existSysStorage.getDeleted()) {
                return ResultGenerator.genOkResult(existSysStorage);
            } else {
                this.sysStorageService.deleteById(existSysStorage.getId());
            }
        }
        SysStorage sysStorage = this.publicStorageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
        return ResultGenerator.genOkResult(sysStorage);
    }

}
