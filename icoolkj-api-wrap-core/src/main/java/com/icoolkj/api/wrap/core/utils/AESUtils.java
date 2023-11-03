package com.icoolkj.api.wrap.core.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AESUtils
{
    private static final Logger log = Logger.getLogger(AESUtils.class.getName());
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding"; //默认的加密算法
    private static final int DEFAULT_KEY_SIZE = 128; // 默认的密钥长度

    /**
     * AES 加密操作
     *
     * @param data     待加密数据
     * @param password 加密密钥
     * @return 返回Base64转码的加密数据
     */
    public static String encrypt(String data, String password) {
        return encrypt(data, password, DEFAULT_KEY_SIZE);
    }

    /**
     * AES 加密操作
     *
     * @param data     待加密数据
     * @param password 加密密钥
     * @param keySize  密钥长度
     * @return 返回Base64转码的加密数据
     */
    public static String encrypt(String data, String password, int keySize) {
        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteData = data.getBytes(StandardCharsets.UTF_8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password, keySize));
            // 加密
            byte[] encrypted = cipher.doFinal(byteData);
            // 通过Base64转码
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            // ex.printStackTrace();
            log.log(Level.SEVERE, "Error during encryption", ex);
            return null;
        }
    }

    /**
     * AES 解密操作
     *
     * @param encryptedData 待解密数据
     * @param password      解密密钥
     * @return 返回解密后数据
     */
    public static String decrypt(String encryptedData, String password) {
        return decrypt(encryptedData, password, DEFAULT_KEY_SIZE);
    }

    /**
     * AES 解密操作
     *
     * @param encryptedData 待解密数据
     * @param password      解密密钥
     * @param keySize       密钥长度
     * @return 返回解密后数据
     */
    public static String decrypt(String encryptedData, String password, int keySize) {
        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 初始化解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password, keySize));
            // 执行操作
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            // ex.printStackTrace();
            log.log(Level.SEVERE, "Error during decryption", ex);
            return null;
        }
    }

    /**
     * 生成加密秘钥
     *
     * @param password 加密密钥
     * @param keySize  密钥长度
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password, int keySize) {
        // 返回生成指定算法密钥 SecretKeySpec 对象
        try {
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            // AES 要求密钥长度为 128, 192 或 256
            kg.init(keySize, new SecureRandom(password.getBytes()));
            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            // ex.printStackTrace();
            log.log(Level.SEVERE, "Error during key generation", ex);
            return null;
        }
    }
}
