package com.moses.cloud.commons.utils;


import com.moses.cloud.commons.exception.BusinessException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * @Author HanKeQi
 * @Date 2021/1/6 下午1:37
 * @Version 1.0
 **/
public class Md5EncoderUtils {

    private final static String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

    private static final String ALGORITHM = "MD5";

    public static String encodeBit32WithNoSalt(byte[] sourceString){
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(sourceString);
            byte messageDigest[] = digest.digest();
            return byteArrayToHexString(messageDigest);
        }catch (Exception e){
            throw new BusinessException("", "");
        }
    }


    public static String encodeBit32WithNoSalt(String sourceStr) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(sourceStr.getBytes());
            byte[] messageDigest = digest.digest();
            return byteArrayToHexString(messageDigest);
        }catch (Exception e){
            throw new BusinessException("", "");
        }
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    public static String fileMd5(String inputFile){
        try {
            return fileInputStreamMd5(new FileInputStream(inputFile));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String fileMd5(byte[] bytes){
        try {
            return fileInputStreamMd5(new ByteArrayInputStream(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String fileInputStreamMd5(InputStream stream){
        // 缓冲区大小（这个可以抽出一个参数）
        int bufferSize = 256 * 1024;
        DigestInputStream digestInputStream = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            // 使用DigestInputStream
            digestInputStream = new DigestInputStream(stream,digest);
            // read的过程中进行MD5处理，直到读完文件
            byte[] buffer =new byte[bufferSize];
            while (digestInputStream.read(buffer) > 0) {
                //
            }
            // 获取最终的MessageDigest
            digest= digestInputStream.getMessageDigest();
            // 拿到结果，也是字节数组，包含16个元素
            byte[] resultByteArray = digest.digest();
            // 同样，把字节数组转换成字符串
            return byteArrayToHexString(resultByteArray);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                digestInputStream.close();
            } catch (Exception e) {
            }
            try {
                stream.close();
            } catch (Exception e) {
            }
        }
        return null;
    }
//
//
////    public static void main(String[] args) throws NoSuchAlgorithmException {
////        System.out.println(encodeBit32WithNoSalt("123".getBytes()));
////    }
//
    public static void main(String[] args) {
        System.out.println(fileMd5("D:/1111/mobile.zip"));
//        System.out.println(encodeBit32WithNoSalt("/Users/hankeqi/Documents/wjxk-saas.zip"));
    }
}


