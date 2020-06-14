package cn.zeroce.design.chengwen.core.config;

import cn.zeroce.design.chengwen.core.qiniu.QiniuConfigurationProperties;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author: zeroce
 * @date 20.4.10 22:14
 */
@Configuration
public class QiniuConfig {
    @Resource
    private QiniuConfigurationProperties qiniuProperties;

    /**
     * 配置存储空间的存储区域
     * @return
     */
    @Bean
    public com.qiniu.storage.Configuration getConfiguration() {
        return new com.qiniu.storage.Configuration(Zone.autoZone());
    }

    /**
     * qiniu 上传工具实例
     * @return
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(this.getConfiguration());
    }

    /**
     * 认证信息
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(this.qiniuProperties.getAccessKey(), this.qiniuProperties.getSecretKey());
    }

    /**
     * qiniu 存储空间管理实例
     * @return
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(this.auth(), this.getConfiguration());
    }

}
