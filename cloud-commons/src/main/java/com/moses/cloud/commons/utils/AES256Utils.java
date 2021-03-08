package com.moses.cloud.commons.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

/**
 * @Author HanKeQi
 * @Date 2021/1/5 下午3:19
 * @Version 1.0
 **/
@Slf4j
public class AES256Utils {

    /**
     * 加密
     * param content 需要加密的内容
     * param password  加密密码
     * return
     */
    private static String Key="GcA*23jKJf0df09Osf09834ljlJF0920";

    public static String encode(String stringToEncode) throws NullPointerException {

        try {
            SecretKeySpec skeySpec = getKey(Key);
            byte[] clearText = stringToEncode.getBytes("UTF8");
            final byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0x00);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
//            String encrypedValue = new sun.misc.BASE64Encoder().encode(cipher.doFinal(clearText));
            String encodeValue = Base64.getEncoder().encodeToString(cipher.doFinal(clearText));
            return encodeValue;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static SecretKeySpec getKey(String password) throws UnsupportedEncodingException {
        int keyLength = 256;
        byte[] keyBytes = new byte[keyLength / 8];
        Arrays.fill(keyBytes, (byte) 0x0);
        byte[] passwordBytes = password.getBytes("UTF-8");
        int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
        System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        return key;
    }


    public static String decoder(String encodeStr) {
        try {
            encodeStr = encodeStr.replaceAll(" ", "+");
            SecretKeySpec skeySpec = getKey(Key);
            final byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0x00);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
//            byte[] bytes = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(encodeStr));
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encodeStr));
            return new String(bytes);
        }catch (Exception e){
            log.error("decoder error encodeStr = {}", encodeStr);
//            throw new BusinessException("", "");
            return encodeStr;
        }
    }


//    public static void main(String[] args) {
//        String encode = encode("123");
//        System.out.println(encode);
//        System.out.println(decoder(encode));
//    }

}
