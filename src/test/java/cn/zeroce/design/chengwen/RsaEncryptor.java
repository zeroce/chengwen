package cn.zeroce.design.chengwen;

import cn.zeroce.design.chengwen.utils.IpUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author: zeroce
 * @date 20.3.23 16:05
 */
public class RsaEncryptor {
    private final IpUtils.RsaUtils rsaUtil = new IpUtils.RsaUtils();

    /** 加载公私钥pem格式文件测试 */
    @Test
    public void test1() throws Exception {
        final PublicKey publicKey =
                this.rsaUtil.loadPublicKey(
                        Base64.decodeBase64(
                                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJEnS8G7XkVXw7J/ql7Qe+vtwR7Ky5PaQ0yRF97EI0uVzc4CgBYc6rHIXdLAjKDPkwlVxzgsWYnBg5ruR0ZzEmUCAwEAAQ=="));
        final PrivateKey privateKey =
                this.rsaUtil.loadPrivateKey(
                        Base64.decodeBase64(
                                "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAkSdLwbteRVfDsn+qXtB76+3BHsrLk9pDTJEX3sQjS5XNzgKAFhzqschd0sCMoM+TCVXHOCxZicGDmu5HRnMSZQIDAQABAkAko9W0pnZKbLgCc8Vuv5O4X0CTZD91J/A7AJkU60+5gUllIBpX0M+iJ4U0VEedjmo3pACuofCoOiGoc1GJo741AiEA4pa8x6+1FiomxrZPDA07LLFSyDHKaXRH+I3gf4HE6x8CIQCj/pCceB8zLKMKE231mYGOERpH4tPIGCe6xC7pgQkV+wIhAKJfyuB+NZKwmiEEPBky0hjw6ZW0uIxhW8HuSdaVs/uZAiBSazjwemprxK43/SXRPhHffXbw0+3N7V3iPoC5N/GCCwIgVGOQ1e9FDKBmPK8sdFbm7KsplycwPBvbZlRp2CSvKyM="));
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        final String data = "chengwen";
        // 公钥加密
        final byte[] encrypted = this.rsaUtil.encrypt(data.getBytes());
        System.out.println("加密后：" + Base64.encodeBase64String(encrypted));

        // 私钥解密
        final byte[] decrypted = this.rsaUtil.decrypt(encrypted);
        System.out.println("解密后：" + new String(decrypted));
    }

    /** 生成RSA密钥对并进行加解密测试 */
    @Test
    public void test2() throws Exception {
        final String data = "chengwen";
        final KeyPair keyPair = this.rsaUtil.genKeyPair(1024);

        // 获取公钥，并以base64格式打印出来
        final PublicKey publicKey = keyPair.getPublic();
        System.out.println("公钥：" + new String(Base64.encodeBase64(publicKey.getEncoded())));

        // 获取私钥，并以base64格式打印出来
        final PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("私钥：" + new String(Base64.encodeBase64(privateKey.getEncoded())));

        // 公钥加密
        final byte[] encrypted = this.rsaUtil.encrypt(data.getBytes(), publicKey);
        System.out.println("加密后：" + new String(Base64.encodeBase64(encrypted)));

        // 私钥解密
        final byte[] decrypted = this.rsaUtil.decrypt(encrypted, privateKey);
        System.out.println("解密后：" + new String(decrypted));
    }
}
