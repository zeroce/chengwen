package cn.zeroce.design.chengwen.wx.core.storage.impl;

import cn.zeroce.design.chengwen.wx.core.qiniu.QiniuConfigurationProperties;
import cn.zeroce.design.chengwen.wx.core.storage.WxStorage;
import cn.zeroce.design.chengwen.wx.entity.PostStorage;
import cn.zeroce.design.chengwen.wx.mapper.PostStorageMapper;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author: zeroce
 * @date 20.4.11 14:42
 */
@Service
public class QiniuWxStorageImpl implements WxStorage {
    private final Log logger = LogFactory.getLog(QiniuWxStorageImpl.class);
    @Autowired
    private QiniuConfigurationProperties qiniuProperties;
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private Auth auth;
    @Autowired
    private BucketManager bucketManager;
    @Autowired
    private PostStorageMapper postStorageMapper;


    @Override
    public String store(InputStream inputStream, long contentLength, String contentType, String keyName) {
        try {
            String uploadToken = this.auth.uploadToken(this.qiniuProperties.getBucket());
            Response response = this.uploadManager.put(inputStream, keyName, uploadToken, null, contentType);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String returnPath = this.qiniuProperties.getPath() + "/" + putRet.key;
            return returnPath;
        } catch (QiniuException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String keyName) {
        return null;
    }

    @Override
    public Resource loadAsResource(String keyName) {
        try {
            URL url = new URL(this.generateUrl(keyName));
            Resource resource = new UrlResource(url);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            logger.error("===> MalformedURLException: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void delete(String keyName) {
        try {
            this.bucketManager.delete(this.qiniuProperties.getBucket(), keyName);
        } catch (QiniuException e) {
            logger.error("===> QiniuException: " + e.getMessage(), e);
        }
    }

    @Override
    public String generateUrl(String keyName) {
        return null;
    }

    public void generateDownloadUrl() {
        PostStorage sysStorage = this.postStorageMapper.selectByPrimaryKey("116513156315664");
        String publicUrl = this.qiniuProperties.getPath() + "/" + sysStorage.getpKey();
        System.out.println("///////////////////////////////////////////////////////////////////");
        System.out.println("===> public url: " + publicUrl);
        System.out.println("///////////////////////////////////////////////////////////////////");

        long expireInSeconds = 3600;
        String finalUrl = this.auth.privateDownloadUrl(publicUrl, expireInSeconds);
        System.out.println("///////////////////////////////////////////////////////////////////");
        System.out.println("===> final url: " + finalUrl);
        System.out.println("///////////////////////////////////////////////////////////////////");

    }
}
