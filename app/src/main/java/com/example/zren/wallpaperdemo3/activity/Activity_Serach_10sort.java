package com.example.zren.wallpaperdemo3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.zren.wallpaperdemo3.R;

public class Activity_Serach_10sort extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_point_1, iv_point_2, iv_point_3, iv_point_4,
            iv_point_5, iv_point_6, iv_point_7, iv_point_8, iv_point_9, iv_point_10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_activity__serach_10sort);

        initView();

        click();

    }

    /**
     * 加监听器
     */
    private void click() {
        iv_point_1.setOnClickListener(this);
        iv_point_2.setOnClickListener(this);
        iv_point_3.setOnClickListener(this);
        iv_point_4.setOnClickListener(this);
        iv_point_5.setOnClickListener(this);
        iv_point_6.setOnClickListener(this);
        iv_point_7.setOnClickListener(this);
        iv_point_8.setOnClickListener(this);
        iv_point_9.setOnClickListener(this);
        iv_point_10.setOnClickListener(this);
    }


    /**
     * UI
     */
    private void initView() {
        iv_point_1 = (ImageView) findViewById(R.id.iv_point_1);
        iv_point_2 = (ImageView) findViewById(R.id.iv_point_2);
        iv_point_3 = (ImageView) findViewById(R.id.iv_point_3);
        iv_point_4 = (ImageView) findViewById(R.id.iv_point_4);
        iv_point_5 = (ImageView) findViewById(R.id.iv_point_5);
        iv_point_6 = (ImageView) findViewById(R.id.iv_point_6);
        iv_point_7 = (ImageView) findViewById(R.id.iv_point_7);
        iv_point_8 = (ImageView) findViewById(R.id.iv_point_8);
        iv_point_9 = (ImageView) findViewById(R.id.iv_point_9);
        iv_point_10 = (ImageView) findViewById(R.id.iv_point_10);
    }

    /**
     * 点击事件处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_point_1:
                Intent intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","65");
                startActivity(intent);
                break;
            case R.id.iv_point_2:
               intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","62");
                startActivity(intent);
                break;
            case R.id.iv_point_3:
              intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","58");
                startActivity(intent);
                break;
            case R.id.iv_point_4:
              intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","61");
                startActivity(intent);
                break;
            case R.id.iv_point_5:
                intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","54");
                startActivity(intent);
                break;
            case R.id.iv_point_6:
                intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","41");
                startActivity(intent);
                break;
            case R.id.iv_point_7:
                 intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","30");
                startActivity(intent);
                break;
            case R.id.iv_point_8:
               intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","36");
                startActivity(intent);
                break;
            case R.id.iv_point_9:
                intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","37");
                startActivity(intent);
                break;
            case R.id.iv_point_10:
                intent = new Intent(this, Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","34");
                startActivity(intent);
                break;

        }
    }
}
