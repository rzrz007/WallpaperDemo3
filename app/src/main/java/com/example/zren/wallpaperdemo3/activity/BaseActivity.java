package com.example.zren.wallpaperdemo3.activity;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.zren.wallpaperdemo3.receiver.JudgeNetIsConnectedReceiver;

/**
 * Created by ysy on 2016/11/1.
 */
public class BaseActivity extends Activity{

    /**
     * 声明判断网络是否连接成功的接受者对象
     */
    private JudgeNetIsConnectedReceiver judgeNetIsConnectedReceiver;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.judgeNetIsConnectedReceiver=new JudgeNetIsConnectedReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(judgeNetIsConnectedReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(this.judgeNetIsConnectedReceiver!=null){
            this.unregisterReceiver(judgeNetIsConnectedReceiver);
        }
    }
}
