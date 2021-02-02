package com.iot.common.util;


import com.iot.common.util.jwt.RsaKeyLoad;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
public class RsaKeyHelper {
    /**
     * 获取公钥
     *
     * @param filename
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws Exception
     */
    public PublicKey getPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filename);
        log.info("Resource path：" + getClass().getClassLoader().getResource(filename).getPath());

        DataInputStream dis = new DataInputStream(resourceAsStream);
        try {
            byte[] keyBytes = {48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1,
                    1, 5, 0, 3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -26, 77, -20,
                    -21, -93, 122, 15, 64, 29, 103, -79, -62, -83, 85, -116, -115, 75, -104,
                    -1, 27, 112, 111, -121, -126, 81, -117, -78, 120, 83, -54, 70, 91, 31,
                    -44, -68, 29, 26, -70, 119, -93, -19, 33, -59, -115, -56, 11, -116, 17,
                    54, 31, -30, 55, 45, 113, 90, -38, 77, 52, 55, -127, -65, 62, -58, -91, 51,
                    79, 103, 18, 125, -4, -17, -117, 18, 43, -24, 12, -78, -24, 52, 77, -96,
                    -5, -108, 123, -64, 78, -31, -31, 70, -94, 10, -54, -6, -8, 40, 19, -52,
                    -90, -67, 10, 41, -18, -93, 114, -66, -121, 103, 39, 3, 33, 13, 60, -111,
                    -66, -71, 46, 47, -14, -116, 27, -125, -56, 4, 54, 4, 12, 94, 107, 2, 3, 1, 0, 1};
            if (resourceAsStream != null &&
                    resourceAsStream.available() != 0) {
                keyBytes = new byte[resourceAsStream.available()];
                dis.readFully(keyBytes);
            }
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(spec);
        } finally {
            try {
                if (null != dis) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取密钥
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public PrivateKey getPrivateKey(String filename) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        DataInputStream dis = new DataInputStream(resourceAsStream);
        try {
            byte[] keyBytes = {48, -126, 2, 120, 2, 1, 0, 48, 13, 6, 9, 42,
                    -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 4, -126, 2, 98,
                    48, -126, 2, 94, 2, 1, 0, 2, -127, -127, 0, -26, 77,
                    -20, -21, -93, 122, 15, 64, 29, 103, -79, -62, -83,
                    85, -116, -115, 75, -104, -1, 27, 112, 111, -121,
                    -126, 81, -117, -78, 120, 83, -54, 70, 91, 31, -44,
                    -68, 29, 26, -70, 119, -93, -19, 33, -59, -115, -56,
                    11, -116, 17, 54, 31, -30, 55, 45, 113, 90, -38, 77,
                    52, 55, -127, -65, 62, -58, -91, 51, 79, 103, 18,
                    125, -4, -17, -117, 18, 43, -24, 12, -78, -24, 52,
                    77, -96, -5, -108, 123, -64, 78, -31, -31, 70, -94,
                    10, -54, -6, -8, 40, 19, -52, -90, -67, 10, 41, -18,
                    -93, 114, -66, -121, 103, 39, 3, 33, 13, 60, -111, -66,
                    -71, 46, 47, -14, -116, 27, -125, -56, 4, 54, 4, 12,
                    94, 107, 2, 3, 1, 0, 1, 2, -127, -127, 0, -98, -17,
                    -80, 77, 126, 104, 66, 22, 17, 52, -6, -84, 75, 74,
                    -6, -48, -104, 23, 98, 22, -31, -91, 86, 115, 77, 43,
                    -121, 99, 27, 16, 65, 53, -87, -27, -6, 92, 107, -111,
                    -69, -9, 89, 51, 49, 79, 3, 123, 84, -110, -100, 51,
                    -84, -66, 63, -110, 55, 12, -24, -78, -36, -117, -94,
                    28, 4, -95, -74, 98, -54, 70, 126, -41, -10, 23, 30,
                    -78, -88, 101, 36, -85, 43, 16, -28, -116, 47, 103,
                    73, 67, 58, 9, -95, 14, 80, 88, -122, 17, 59, -109,
                    -123, -60, 122, -87, -55, -40, 80, -106, 74, -60, 2,
                    -59, 36, 26, 115, 21, 88, -105, 66, -42, -40, 13,
                    -65, -68, -12, -9, 58, -49, 116, 85, 25, -95, 2, 65,
                    0, -7, 32, 87, -65, 22, 95, -60, 19, 72, 118, 5, 4,
                    -83, -76, -73, -41, 53, 3, -81, -27, 112, 109, -22,
                    -42, 11, 51, 6, -6, 91, -28, 13, -60, 109, -107, -102,
                    -43, -41, -16, -86, 14, -46, -97, -104, 19, -93, 47,
                    -14, -2, 122, -43, 86, -124, -128, 86, 11, -81, -61,
                    -101, -64, 83, -60, 90, 38, -17, 2, 65, 0, -20, -88,
                    -93, 47, -108, -95, -10, -32, -31, -37, -77, -62, 37,
                    8, 73, -7, 33, 40, 41, -39, 54, 65, -4, 39, -26, -115,
                    -81, 112, 104, 36, 83, -77, 71, 28, -25, -121, 116, 95,
                    34, -119, 95, -124, -19, 121, -50, -19, -11, -60, -16,
                    -80, -61, -115, -64, -30, 8, 104, -68, 71, 18, -68, -21,
                    62, 32, 69, 2, 65, 0, -33, -79, 24, -40, -19, -32, 35,
                    -111, 36, -7, 67, -36, 69, -107, 5, -107, -23, 118, 92,
                    20, 106, 117, 103, -19, -75, -76, -91, -55, 91, -123, 84,
                    -113, 81, -50, -119, 111, 6, 44, -124, -90, 54, -99, 50,
                    -46, 63, 47, 40, -61, 51, -52, 39, -103, 102, 26, -36,
                    -9, 127, 110, 116, -83, 66, -101, -88, 101, 2, 64, 94,
                    -12, -21, 69, 84, 38, 8, -95, 2, -2, -36, -119, -79, -62,
                    57, -84, 52, -103, -96, -16, -2, -52, 75, 24, -41, 113,
                    82, 75, -113, -3, -61, 119, -45, -72, -51, 77, -108,
                    107, -9, -73, -105, 20, 7, 21, 48, -22, 90, 10, -61,
                    104, -87, -114, 95, 46, 82, -95, 115, -86, 48, -30,
                    -116, -95, 72, -31, 2, 65, 0, -88, -18, 70, -118, -84,
                    60, 73, 109, 5, 125, -7, 16, 21, 95, 57, -3, -108, -96,
                    87, -41, -67, 60, -81, -109, 25, -98, -128, 26, 15, -113,
                    -10, -107, 121, 52, 101, -85, 95, 101, 93, -1, -106, -99,
                    -55, 119, 48, 107, 88, 16, -36, -35, 24, 82, 127, 12, 125,
                    -100, -1, -76, 105, -44, -15, 54, 2, 70};
            if (resourceAsStream != null &&
                    resourceAsStream.available() != 0) {
                keyBytes = new byte[resourceAsStream.available()];
                dis.readFully(keyBytes);
            }
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != dis) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 生存rsa公钥和密钥
     *
     * @param publicKeyFilename
     * @param privateKeyFilename
     * @param password
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void generateKey(String publicKeyFilename, String privateKeyFilename, String password) {
        KeyPairGenerator keyPairGenerator = null;
        FileOutputStream fos = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = new SecureRandom(password.getBytes(Charset.forName("UTF-8")));
            keyPairGenerator.initialize(1024, secureRandom);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            fos = new FileOutputStream(publicKeyFilename);
            fos.write(publicKeyBytes);
            fos.close();
            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            fos = new FileOutputStream(privateKeyFilename);
            fos.write(privateKeyBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 解密的方法，使用私钥进行解密
     * privateKey  私钥
     * encoData 需要解密的数据
     */
    private byte[] privateEncrypt(Key privateKey, byte[] encoData) throws Exception {
        Cipher cipher = Cipher
                .getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //设置为解密模式，用私钥解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //解密
        byte[] data = cipher.doFinal(encoData);
        return data;
    }

    /**
     * 加密的方法,使用公钥进行加密
     *
     * @param publicKey 公钥
     * @param data      需要加密的数据
     */
    private static byte[] publicEncrypt(Key publicKey, String data) throws Exception {
        Cipher cipher = Cipher
                .getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        // 设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 对数据进行加密
        return cipher.doFinal(data.getBytes());
    }

    /**
     * RSA私钥解密(用户密码密文传输 字符串解析)
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String publicKeyEncrypt(String str, String publicKey) throws Exception {
        log.info("|RSA公钥加密前的数据|str:{}|publicKey:{}",str,publicKey);
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").
                generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        log.info("公钥加密后的数据|outStr:{}",outStr);
        return outStr;
    }


    /**
     * jwk rsa解密password
     *
     */
    public static String rsaDecryptPassword(String encodePassword) {
        if (StringUtils.hasText(encodePassword)) {
            try {
                var privateKey = RsaKeyLoad.getRsaKey().toPrivateKey();
                String keyString = java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded());
                return RsaKeyHelper.decrypt(encodePassword, keyString);
            } catch (Exception e) {
            }
        }
        return "";
    }

}

