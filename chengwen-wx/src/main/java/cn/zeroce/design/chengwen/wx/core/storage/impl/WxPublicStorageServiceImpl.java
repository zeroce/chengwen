package cn.zeroce.design.chengwen.wx.core.storage.impl;

import cn.zeroce.design.chengwen.wx.core.storage.WxPublicStorageService;
import cn.zeroce.design.chengwen.wx.core.storage.WxStorage;
import cn.zeroce.design.chengwen.wx.entity.PostStorage;
import cn.zeroce.design.chengwen.wx.service.WxPostStorageService;
import cn.zeroce.design.chengwen.wx.utils.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 对外提供存储服务类，所有存储服务均由该类对外提供
 *
 * @author: zeroce
 * @date 20.4.11 16:50
 */
@Component
public class WxPublicStorageServiceImpl implements WxPublicStorageService {
    @Autowired
    private WxStorage wxStorage;
    @Autowired
    private WxPostStorageService wxPostStorageService;

    @Override
    public PostStorage store(InputStream inputStream, long contentLength, String contentType, String fileName) {
        String key = generateKey(fileName);
        String filePath = this.wxStorage.store(inputStream, contentLength, contentType, key);

        // String url = generateUrl(key);
        String url = filePath;
        PostStorage sysStorageInfo = new PostStorage();
        sysStorageInfo.setpName(fileName);
        sysStorageInfo.setSize((int) contentLength);
        sysStorageInfo.setType(contentType);
        sysStorageInfo.setpKey(key);
        sysStorageInfo.setUrl(url);
        this.wxPostStorageService.create(sysStorageInfo);
        return sysStorageInfo;
    }

    @Override
    public String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;
        PostStorage sysStorageInfo = null;

        do {
            key = CharUtils.getRandomString(20) + suffix;
            sysStorageInfo = this.wxPostStorageService.selectByKey(key);
        } while (null != sysStorageInfo);

        return key;
    }

    @Override
    public Stream<Path> loadAll() {
        return this.wxStorage.loadAll();
    }

    @Override
    public Path load(String keyName) {
        return this.wxStorage.load(keyName);
    }

    @Override
    public Resource loadAsResource(String keyName) {
        return this.wxStorage.loadAsResource(keyName);
    }

    @Override
    public void delete(String keyName) {
        this.wxStorage.delete(keyName);
    }

    @Override
    public String generateUrl(String keyName) {
        return this.wxStorage.generateUrl(keyName);
    }
}
