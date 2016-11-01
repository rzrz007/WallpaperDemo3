package com.example.zren.wallpaperdemo3.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 84168 on 2016/11/1.
 */
public class NetWorkState {
    private ConnectivityManager connectivityManager;
    String str=null;
    public String getNetWorkState(Context context){

        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);//获取当前网络的连接服务
        NetworkInfo info = connectivityManager.getActiveNetworkInfo(); //获取活动的网络连接信息
        if (info==null){
            return  str="网络不给力啊";

        }
        return str="网络连接成功";
    }
}
