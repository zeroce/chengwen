package cn.zeroce.design.chengwen.controller;

import cn.zeroce.design.chengwen.core.response.ResultCode;
import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import cn.zeroce.design.chengwen.core.storage.PublicStorageService;
import cn.zeroce.design.chengwen.core.storage.Storage;
import cn.zeroce.design.chengwen.dto.SysStorageDTO;
import cn.zeroce.design.chengwen.entity.SysStorage;
import cn.zeroce.design.chengwen.service.SysStorageService;
import cn.zeroce.design.chengwen.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.11 10:03
 */
@RestController
@RequestMapping("/sys/storage")
public class StorageController {
    @Resource
    private PublicStorageService publicStorageService;
    @Resource
    private SysStorageService sysStorageService;
    @Resource
    private Storage storage;

    /**
     * 创建新存储记录
     * @param file
     * @return
     * @throws IOException
     */
    @PreAuthorize("hasAnyAuthority('storage:create', 'storage:add')")
    @PostMapping("/create")
    public ResultUtil create(@RequestParam(name = "file") MultipartFile file) throws IOException {
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
        SysStorage target = this.publicStorageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
        return ResultGenerator.genOkResult(target);
    }

    /**
     * 管理员记录
     * @param page
     * @param limit
     * @param key
     * @param name
     * @param sort
     * @param order
     * @return
     */
    @PreAuthorize("hasAnyAuthority('storage:list', 'storage:search')")
    @GetMapping("/list")
    public ResultUtil list(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "0") Integer limit,
            @RequestParam(name = "key", required = false) String key,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "sort", defaultValue = "create_time", required = false) String sort,
            @RequestParam(name = "order", defaultValue = "desc", required = false) String order
    ) {
        PageHelper.startPage(page, limit);
        List<SysStorage> resultList = this.sysStorageService.findByKeyName(key, name);
        PageHelper.clearPage();
        PageInfo<SysStorage> pageInfo = new PageInfo<>(resultList);
        return ResultGenerator.genOkResult(pageInfo);
    }


    @PreAuthorize("hasAuthority('storage:update')")
    @PostMapping("/update")
    public ResultUtil update(@RequestBody SysStorageDTO sysStorageDTO) {
        SysStorage target = this.sysStorageService.findById(sysStorageDTO.getId());
        if (null == target) {
            return ResultGenerator.genFailedResult(ResultCode.DATA_IS_NOT_EXIST);
        }
        List<SysStorage> sysStorageListByName = this.sysStorageService.findByKeyName(null, sysStorageDTO.getName());
        if (null != sysStorageListByName && !sysStorageListByName.isEmpty()) {
            return ResultGenerator.genFailedResult(ResultCode.UPDATE_FAILED.getCode(), ResultCode.UPDATE_FAILED.getMessage() + "该文件名已存在");
        }
        SysStorage resource = new SysStorage();
        resource.setId(sysStorageDTO.getId());
        resource.setsName(sysStorageDTO.getName());
        this.sysStorageService.updateName(resource);
        return ResultGenerator.genOkResult();
    }

    @PreAuthorize("hasAuthority('storage:delete')")
    @DeleteMapping("/delete")
    public ResultUtil delete(@RequestBody SysStorage sysStorage) {
        if (StringUtils.isEmpty(sysStorage.getsKey())) {
            return ResultGenerator.genFailedResult(ResultCode.DELETE_FAILED.getCode(), ResultCode.DELETE_FAILED.getMessage() + "文件key不存在");
        }
        List<SysStorage> sysStorageListByKeyName = this.sysStorageService.findByKeyName(sysStorage.getsKey(), null);
        if (null == sysStorageListByKeyName || sysStorageListByKeyName.isEmpty()) {
            return ResultGenerator.genFailedResult(ResultCode.DELETE_FAILED.getCode(), ResultCode.DELETE_FAILED.getMessage() + "文件已删除或并未上传");
        }
        this.sysStorageService.deleteById(sysStorage.getId());
        this.storage.delete(sysStorage.getsKey());
        return ResultGenerator.genOkResult();
    }
}
