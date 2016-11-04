package com.example.zren.wallpaperdemo3.utils;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by wjj on 2016/11/4.
 */
public class FileReadUtils {

    // 用于遍历sdcard卡上所有文件的类
    public static ArrayList<String> DirAll(File dirFile) throws Exception {
        ArrayList<String> dirAllStrArr = new ArrayList<>();
        if (dirFile.exists()) {
            File files[] = dirFile.listFiles();
            for (File file : files) {
                String fileName = file.getName();

                if (file.isDirectory()) {

                    // 除sdcard上Android这个文件夹以外。
                    if (!fileName.endsWith("Android")) {
                        // 如果遇到文件夹则递归调用。
                        DirAll(file);
                    }
                } else {
                    // 如果是图片文件压入数组

                    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")
                            || fileName.endsWith(".bmp")
                            || fileName.endsWith(".gif")
                            || fileName.endsWith(".png")) {

                        // 如果遇到文件则放入数组
                        if (dirFile.getPath().endsWith(File.separator)) {
                            dirAllStrArr.add(dirFile.getPath() + file.getName());
                        } else {
                            dirAllStrArr.add(dirFile.getPath() + File.separator + file.getName());
                        }
                    }
                }
            }
        }
        return dirAllStrArr;
    }

    // 图片加载的缓存工具类，安卓自带的方法
    public static BitmapFactory.Options getHeapOpts(File file) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 数字越大读出的图片占用的heap必须越小，不然总是溢出
        if (file.length() < 20480) { // 0-20k
            opts.inSampleSize = 1;// 这里意为缩放的大小 ，数字越多缩放得越厉害
        } else if (file.length() < 51200) { // 20-50k
            opts.inSampleSize = 2;
        } else if (file.length() < 307200) { // 50-300k
            opts.inSampleSize = 4;
        } else if (file.length() < 819200) { // 300-800k
            opts.inSampleSize = 6;
        } else if (file.length() < 1048576) { // 800-1024k
            opts.inSampleSize = 8;
        } else {
            opts.inSampleSize = 10;
        }
        return opts;
    }


}
