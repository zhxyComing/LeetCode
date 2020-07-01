package com.dixon.descprocessor.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 文件操作工具类
 */
public class FileUtil {

    public static void write(String dir, String fileName, String content) {
        try {
            // 创建文件夹
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                boolean createSuccess = dirFile.mkdirs();
                if (!createSuccess) {
                    return;
                }
            }
            // 创建文件
            File file = new File(dir, fileName);
            if (!file.exists()) {
                boolean createSuccess = file.createNewFile();
                if (!createSuccess) {
                    return;
                }
            }
            // 写入文件
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(content); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String read(String filePath) {
        try {
            // 创建文件
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }
            // 读取文件
            StringBuilder result = new StringBuilder();

            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
