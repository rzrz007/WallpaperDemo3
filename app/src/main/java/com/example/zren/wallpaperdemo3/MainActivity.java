package com.example.zren.wallpaperdemo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("这是没有忽略任何文件的");

        System.out.println("three test");
        System.out.println("第二次测试");
    }
}
