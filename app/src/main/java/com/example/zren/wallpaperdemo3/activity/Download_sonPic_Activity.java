package com.example.zren.wallpaperdemo3.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zren.wallpaperdemo3.R;

import java.io.IOException;

public class Download_sonPic_Activity extends AppCompatActivity {

    private ImageView imageView;
    private Button button_back;
    private Button button_detail;
    private Button button_setBackground;
    private String imgPath;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_download_son_pic);

        imageView = (ImageView) findViewById(R.id.imageView);
        button_back = (Button) findViewById(R.id.button_back);
        button_detail = (Button) findViewById(R.id.button_detail);
        button_setBackground = (Button) findViewById(R.id.button_setBackground);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        imgPath = bundle.getString("imgPath");

        bitmap = BitmapFactory.decodeFile(imgPath);
        imageView.setImageBitmap(bitmap);

        //返回则销毁当前Activity
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Download_sonPic_Activity.this).setTitle("图片详情").setMessage("路径：" + imgPath).setNegativeButton("确定", null).show();
            }
        });

        button_setBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setWallpaper(bitmap);
                    Toast.makeText(Download_sonPic_Activity.this, "设置成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
