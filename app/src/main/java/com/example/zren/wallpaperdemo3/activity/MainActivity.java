package com.example.zren.wallpaperdemo3.activity;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.zren.wallpaperdemo3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //启动欢迎页面2秒后自动跳转到 IndexActivity
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        }).start();


    }

}
