package cn.zeroce.design.chengwen.core.storage.impl;

import cn.zeroce.design.chengwen.core.storage.Storage;
import cn.zeroce.design.chengwen.core.storage.PublicStorageService;
import cn.zeroce.design.chengwen.entity.SysStorage;
import cn.zeroce.design.chengwen.service.SysStorageService;
import cn.zeroce.design.chengwen.utils.CharUtils;
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
public class PublicStorageServiceImpl implements PublicStorageService {
    @Autowired
    private Storage storage;
    @Autowired
    private SysStorageService sysStorageService;

    @Override
    public SysStorage store(InputStream inputStream, long contentLength, String contentType, String fileName) {
        String key = generateKey(fileName);
        String filePath = this.storage.store(inputStream, contentLength, contentType, key);

        // String url = generateUrl(key);
        String url = filePath;
        SysStorage sysStorageInfo = new SysStorage();
        sysStorageInfo.setsName(fileName);
        sysStorageInfo.setSize((int) contentLength);
        sysStorageInfo.setType(contentType);
        sysStorageInfo.setsKey(key);
        sysStorageInfo.setUrl(url);
        this.sysStorageService.create(sysStorageInfo);
        return sysStorageInfo;
    }

    @Override
    public String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;
        SysStorage sysStorageInfo = null;

        do {
            key = CharUtils.getRandomString(20) + suffix;
            sysStorageInfo = this.sysStorageService.selectByKey(key);
        } while (null != sysStorageInfo);

        return key;
    }

    @Override
    public Stream<Path> loadAll() {
        return this.storage.loadAll();
    }

    @Override
    public Path load(String keyName) {
        return this.storage.load(keyName);
    }

    @Override
    public Resource loadAsResource(String keyName) {
        return this.storage.loadAsResource(keyName);
    }

    @Override
    public void delete(String keyName) {
        this.storage.delete(keyName);
    }

    @Override
    public String generateUrl(String keyName) {
        return this.storage.generateUrl(keyName);
    }
}
