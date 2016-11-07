package com.example.zren.wallpaperdemo3.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadUtils {

    /**
     * 使用该方法判断的时候需要重写ononRequestPermissionsResult（）保证第一次授权的时候能执行操作，否则第一次授权操作不会被执行
     * @Override
       public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
           super.onRequestPermissionsResult(requestCode, permissions, grantResults);
               if (requestCode == 1) {
               for (int i = 0; i < permissions.length; i++) {
               String permission = permissions[i];
               int grantResult = grantResults[i];

               if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
               if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    //授权后执行的操作

               } else {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
               }
               }
               }
           }
       }
     * @param activity
     * @return
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
            return false;
        }
        return true;
    }

    /**
     * Android6.0版本使用该方法前需调用isGrantExternalRW(Activity)进行权限请求
     *
     * @param picPath
     */
    public static void downLoadPic(String picPath,String place) {

        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String picName = picPath.substring(picPath.lastIndexOf("/"));
        try {
            fileOutputStream = new FileOutputStream(Environment.getExternalStoragePublicDirectory(place) + File.separator + picName);
            URL url = new URL(picPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(3000);
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    int len = 0;
                    byte[] arr = new byte[1024];
                    while ((len = inputStream.read(arr)) != -1) {
                        fileOutputStream.write(arr, 0, len);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}

