package cn.zeroce.design.chengwen.utils;

import cn.zeroce.design.chengwen.core.rsa.RsaConfigurationProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class IpUtils {
    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IPV4 = "127.0.0.1";
    private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    private IpUtils() {}

    public static String getIpAddress() {
        return getIpAddress(ContextUtils.getRequest());
    }

    /**
     * 获取请求中的 IP 地址
     * @param request
     * @return ip
     */
    public static String getIpAddress(final HttpServletRequest request) {
        String ip = LOCALHOST_IPV4;
        if (request != null) {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                // request.getRemoteAddr() 获取客户端的 IP 地址在大部分情况下都是有效的
                // 但是在通过了 Apache，Squid 等反向代理软件就不能获取到客户端的真实 IP 地址
                // 如果通过了多级反向代理的话 X-Forwarded-For 的值并不止一个
                // 而是一串 IP 值，例如：192.168.1.110,192.168.1.120,192.168.1.130,192.168.1.100
                // 其中第一个 192.168.1.110 才是用户真实的 IP
                if (LOCALHOST_IPV4.equals(ip) || LOCALHOST_IPV6.equals(ip)) {
                    // 根据网卡取本机配置的 IP，而不是环回地址
                    try {
                        ip = InetAddress.getLocalHost().getHostAddress();
                    } catch (final UnknownHostException ignored) {
                    }
                }
            }
            // 多个 IP 中取第一个
            final String ch = ",";
            if (!StringUtils.isEmpty(ip) && ip.contains(ch)) {
                ip = ip.substring(0, ip.indexOf(ch));
            }
        }
        return ip;
    }

    public static String getInfoByIp(final String ip) {
        try {
            final URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);

            final InputStream in = connection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            final StringBuilder buffer = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                buffer.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            final JSONObject obj = (JSONObject) JSON.parse(buffer.toString());
            final StringBuilder info = new StringBuilder();
            final int responseCode = obj.getIntValue("code");
            if (responseCode == 0) {
                final JSONObject data = obj.getJSONObject("data");
                info.append(data.getString("country")).append(" ");
                info.append(data.getString("region")).append(" ");
                info.append(data.getString("city")).append(" ");
                info.append(data.getString("isp"));
            }
            return info.toString();
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @author: zeroce
     * @date 20.3.18 21:23
     */
    @Slf4j
    @Component
    public static class RsaUtils {
        @Resource
        private RsaConfigurationProperties rsaConfigurationProperties;

        private static final String ALGORITHM = "RSA";

        private PrivateKey privateKey;

        private PublicKey publicKey;

        public RsaUtils() {
            if (this.rsaConfigurationProperties == null) {
                this.rsaConfigurationProperties = new RsaConfigurationProperties();
            }
        }

        /**
         * 生成密钥对
         *
         * @param keyLength 密钥长度(最少512位)
         * @return 密钥对 公钥 keyPair.getPublic() 私钥 keyPair.getPrivate()
         * @throws Exception e
         */
        public KeyPair genKeyPair(final int keyLength) throws Exception {
            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(keyLength);
            return keyPairGenerator.generateKeyPair();
        }

        /**
         * 公钥加密
         *
         * @param content   待加密数据
         * @param publicKey 公钥
         * @return 加密内容
         * @throws Exception e
         */
        public byte[] encrypt(final byte[] content, final PublicKey publicKey) throws Exception {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(content);
        }

        /**
         * 公钥加密
         *
         * @param content 待加密数据
         * @return 加密内容
         * @throws Exception e
         */
        public byte[] encrypt(final byte[] content) throws Exception {
            return this.encrypt(content, this.publicKey != null ? this.publicKey : this.loadPublicKey());
        }

        /**
         * 私钥解密
         *
         * @param content    加密数据
         * @param privateKey 私钥
         * @return 解密
         * @throws Exception e
         */
        public byte[] decrypt(final byte[] content, final PrivateKey privateKey) throws Exception {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] bytes = cipher.doFinal(content);
            return bytes;
        }

        /**
         * 私钥解密
         *
         * @param content 加密数据
         * @return 解密内容
         * @throws Exception e
         */
        public byte[] decrypt(final byte[] content) throws Exception {
            return this.decrypt(content, this.privateKey != null ? this.privateKey : this.loadPrivateKey());
        }

        /**
         * 加载pem格式的公钥
         *
         * @param decoded 二进制公钥
         * @return 公钥
         */
        public PublicKey loadPublicKey(final byte[] decoded) {
            try {
                final X509EncodedKeySpec spec = new X509EncodedKeySpec(new Base64().decode(decoded));
                final KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
                this.publicKey = keyFactory.generatePublic(spec);
                return this.publicKey;
            } catch (final Exception e) {
                log.error("==> RSA Utils Exception: {}", e.getMessage());
                return null;
            }
        }

        /**
         * 加载配置文件中设置的公钥
         *
         * @return 公钥
         */
        public PublicKey loadPublicKey() {
            try {
                byte[] decoded;
                if (this.rsaConfigurationProperties.isUseFile()) {
                    decoded =
                            this.replaceAndBase64Decode(
                                    this.rsaConfigurationProperties.getPublicKeyPath(),
                                    this.rsaConfigurationProperties.getPublicKeyHead(),
                                    this.rsaConfigurationProperties.getPublicKeyTail());
                } else {
                    decoded = this.rsaConfigurationProperties.getPublicKey().getBytes();
                }
                return this.loadPublicKey(decoded);
            } catch (final Exception e) {
                log.error("==> RSA Utils Exception: {}", e.getMessage());
                return null;
            }
        }

        /**
         * 加载pem格式PKCS8编码的私钥
         *
         * @param decoded 二进制私钥
         * @return 私钥
         */
        public PrivateKey loadPrivateKey(final byte[] decoded) {
            try {
                final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(new Base64().decode(decoded));
                final KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
                this.privateKey = keyFactory.generatePrivate(spec);
                return this.privateKey;
            } catch (final Exception e) {
                log.error("==> RSA Utils Exception: {}", e.getMessage());
                return null;
            }
        }

        /**
         * 加载配置文件中设置的私钥
         *
         * @return 私钥
         */
        public PrivateKey loadPrivateKey() {
            try {
                byte[] decoded;
                if (this.rsaConfigurationProperties.isUseFile()) {
                    decoded =
                            this.replaceAndBase64Decode(
                                    this.rsaConfigurationProperties.getPrivateKeyPath(),
                                    this.rsaConfigurationProperties.getPrivateKeyHead(),
                                    this.rsaConfigurationProperties.getPrivateKeyTail());
                } else {
                    decoded = this.rsaConfigurationProperties.getPrivateKey().getBytes();
                    // decoded = Base64.decodeBase64(this.rsaConfigurationProperties.getPrivateKey());
                    // decoded = new Base64().decode(this.rsaConfigurationProperties.getPrivateKey());
                    // System.out.println("Base64 解密后的密钥 decoded: " + decoded);
                }
                return this.loadPrivateKey(decoded);
            } catch (final Exception e) {
                log.error("==> RSA Utils Exception: {}", e.getMessage());
                return null;
            }
        }

        /**
         * 加载文件后替换头和尾并解密
         *
         * @return 文件字节
         */
        private byte[] replaceAndBase64Decode(
                final String filePath, final String headReplace, final String tailReplace) throws Exception {
            // 从 classpath:resources/ 中加载资源
            final ClassPathResource resource = new ClassPathResource(filePath);
            if (!resource.exists()) {
                throw new Exception("公私钥文件找不到");
            }
            final byte[] keyBytes = new byte[(int) resource.getFile().length()];
            final FileInputStream in = new FileInputStream(resource.getFile());
            in.read(keyBytes);
            in.close();

            final String keyPEM =
                    new String(keyBytes).replace(headReplace, "").trim().replace(tailReplace, "").trim();

            return Base64.decodeBase64(keyPEM);
        }
    }
}
