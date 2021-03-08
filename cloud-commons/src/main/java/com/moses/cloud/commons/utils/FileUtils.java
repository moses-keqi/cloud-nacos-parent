package com.moses.cloud.commons.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件工具类
 * @Author HanKeQi
 * @Date 2021/2/18 下午2:58
 * @Version 1.0
 **/
public class FileUtils {

    private static final String rootPath = "/temp";

    /**
     * 格式化路径,路径中只能包含字母、数字,中横线 -,下划线 _ 几种
     * 如  /a/b/c,a/b/c/,\a/b/c 等统一格式化成 /a/b/c
     * @param path
     * @return
     */
    public static String formatPath(String path){
        if(path == null || path.trim().equals("")){
            return null;
        }
        String res = path.replaceAll("[\\\\/]*([a-zA-Z0-9_\\-]+)[\\\\/]*", "/$1");
        return res;
    }

    public static void main(String[] args) {
        System.out.println(formatPath("a\\acebceeee/c/d/"));
    }


    /**
     * 将byte写入文件中
     *
     * @param fileByte
     * @param filePath
     * @throws IOException
     */
    public static void byteToFile(byte[] fileByte, String filePath)
            throws IOException {
        File file = getFile(filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try(OutputStream os = new FileOutputStream(file);) {
            os.write(fileByte);
            os.flush();
        }
    }

    /**
     * 文件转成字节数组
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] readFileToBytes(String path) throws IOException {
        byte[] b = null;
        File f = new File(path);
        try(InputStream is = new FileInputStream(f);) {
            b = new byte[(int) f.length()];
            while(is.read(b)>0){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }


    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return 如：.jpg、.gif
     */
    public static String getFileSuffix(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        int fileNameLength = fileName.length();
        return fileName.substring(lastIndexOfDot, fileNameLength);
    }

    /**
     * 获取文件后缀名不要“.”
     *
     * @param fileName
     * @return 如jpg、gif
     */
    public static String getSuffix(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        int fileNameLength = fileName.length();
        return fileName.substring(lastIndexOfDot+1, fileNameLength);
    }

    /**
     * 文件名称重新命名
     * @param oldFileName
     * @return
     */
    public static String generateFileName(String oldFileName){
        String suffix = getSuffix(oldFileName);
        return UUID.randomUUID().toString().replace("-", "")+"."+suffix;
    }

    public static String generateDatePath(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return "/"+sdf.format(new Date());
    }

    /**
     * 根据路径返回文件
     * @param filepath
     * @return
     */
    public static File getFile(String filepath) {
        File file = new File(pathHandle(rootPath + filepath + ".txt"));
        return file;
    }

    public static String pathHandle(String path) {
        String filePath = path.replace("/temp", "").replace(".txt", "");
        return filePath;
    }

    /**
     * 删除文件夹及所有子文件
     */
    public static void deleteDirectory(String path){
        File dirFile = new File(path+"/");
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.isDirectory()) {
            return;
        }
        File[] listFiles = dirFile.listFiles();
        try {
            org.apache.commons.io.FileUtils.forceDeleteOnExit(new File(path));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
