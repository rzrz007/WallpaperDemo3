package com.example.zren.wallpaperdemo3.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ysy on 2016/11/1.
 */
public class NetUtils {

    /**
     * GET方式请求网络得到输入流
     * @param path
     * @return
     */
    public static InputStream getInputStreamByGET(String path){
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return httpURLConnection.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * POST方式请求网络得到输入流
     * @param path
     * @param param
     * @return
     */
    public static InputStream getInputStreamByPOST(String path, String param) {
        OutputStream outputStream = null;
        InputStream inputStream=null;
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            outputStream = httpURLConnection.getOutputStream();
            outputStream.write(param.getBytes());
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream=httpURLConnection.getInputStream();
                return inputStream;
            } else {
                System.out.println("网络连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断手机是否联网
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return true;
        }
        return false;
    }

    public static String inputStreamToString(InputStream inputStream) throws Exception{
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=inputStream.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toString();
    }

    public static <T> T getT(String json,Class<T> cls){
        //1.实例化Gson对象
        Gson gson=new Gson();
        //2.将指定的json字符串还原成T类型的对象
        T t=gson.fromJson(json, cls);
        return t;
    }


}
