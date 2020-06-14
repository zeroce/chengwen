package cn.zeroce.design.chengwen.core.storage;

import cn.zeroce.design.chengwen.entity.SysStorage;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 对外存储服务接口
 *
 * @author: zeroce
 * @date 20.4.11 16:38
 */
public interface PublicStorageService {
    /**
     * 存储一个文件对象
     *
     * @param inputStream
     * @param contentLength
     * @param contentType
     * @param fileName
     * @return
     */
    SysStorage store(InputStream inputStream, long contentLength, String contentType, String fileName);

    String generateKey(String originalFilename);

    Stream<Path> loadAll();

    Path load(String keyName);

    Resource loadAsResource(String keyName);

    void delete(String keyName);

    String generateUrl(String keyName);

}
