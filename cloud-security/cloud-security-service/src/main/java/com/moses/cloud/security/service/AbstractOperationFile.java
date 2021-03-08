package com.moses.cloud.security.service;

/**
 * @author ：double.jia
 * @since : 2021/2/23
 */
public interface AbstractOperationFile {

    /**
     * 上传file, 返回文件的url
     * @param path 文件路径
     * @param isPublic 是否公开
     * @param bytes 文件bytes
     * @return
     */
    String uploadFile(String path, Boolean isPublic, byte[] bytes);

    /**
     * 获取文件的outputStream
     */
    byte[] getFile(String path, String bucketName);
}
