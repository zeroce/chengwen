package cn.zeroce.design.chengwen.core.qiniu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: zeroce
 * @date 20.4.10 19:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "upload.qiniu")
public class QiniuConfigurationProperties {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String path;
}
