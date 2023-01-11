package com.iot.common.util.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class RsaKeyLoad {

    private RsaKeyLoad() {
    }

    static final String JWT_KEY_PATH = "jwt/jwt.jks";
    private static final String JKS_PASSWORD = "123456";
    private static volatile RSAKey rsaKey = null;

    /**
     * 加载jks文件
     * @return
     */
    static RSAKey getRsaKey() {
        //从classpath下获取RSA秘钥对，密码为生成jks文件时设置的密码
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(JWT_KEY_PATH), JKS_PASSWORD.toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("JKS", JKS_PASSWORD.toCharArray());
        //获取RSA公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //获取RSA私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    }

    /**
     * 获取公钥
     * @return
     */
    public static String getPublicKey() {
        RSAKey rsaJWK = getRsaKey();
        PublicKey publicKey;
        try {
            publicKey = rsaJWK.toPublicKey();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}
