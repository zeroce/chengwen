package cn.zeroce.design.chengwen.core.config;

import cn.zeroce.design.chengwen.utils.IpUtils;
import org.apache.commons.codec.binary.Base64;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author: zeroce
 * @date 20.3.18 21:19
 */
@Configuration
public class JasyptConfig {
    @Value("${jasypt.encryptor.password}")
    private String passwordEncryptedByBase64AndRSA;

    @Resource
    private IpUtils.RsaUtils rsaUtils;

    @Bean
    public StringEncryptor myStringEncryptor() throws Exception {
        // Base64 + RSA 加密的密码
        //final byte[] passwordEncryptedByRSA = Base64Utils.decodeFromString(this.passwordEncryptedByBase64AndRSA);
        final byte[] passwordEncryptedByRSA = Base64.decodeBase64(this.passwordEncryptedByBase64AndRSA);
        final String password = new String(this.rsaUtils.decrypt(passwordEncryptedByRSA));
        // 配置
        final SimpleStringPBEConfig config =
                new SimpleStringPBEConfig() {
                    {
                        this.setPassword(password);
                        // 加密算法
                        this.setAlgorithm("PBEWithMD5AndDES");
                        this.setKeyObtentionIterations("1000");
                        this.setPoolSize("1");
                        this.setProviderName("SunJCE");
                        this.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
                        this.setStringOutputType("base64");
                    }
                };
        final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(config);
        return encryptor;
    }
}
