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
    private String imgPath1;
    private String imgPath2;
    private Bitmap bitmap1;
    private Bitmap bitmap2;

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

        imgPath1 = bundle.getString("imgPath");

        imgPath2 = bundle.getString("picFile");

        //解析本地图片资源
        bitmap1 = BitmapFactory.decodeFile(imgPath1);

        bitmap2 = BitmapFactory.decodeFile(imgPath2);

        if (bitmap1 != null) {
            imageView.setImageBitmap(bitmap1);
        } else {
            if (bitmap2 != null) {
                imageView.setImageBitmap(bitmap2);
            }
        }

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
                if (imgPath1 == null) {
                    imgPath1 = imgPath2;
                }
                new AlertDialog.Builder(Download_sonPic_Activity.this).setTitle("图片详情").setMessage("路径：" + imgPath1).setNegativeButton("确定", null).show();

            }
        });

        button_setBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (bitmap1 != null) {
                        setWallpaper(bitmap1);
                    } else {
                        if (bitmap2 != null) {
                            setWallpaper(bitmap2);
                        }
                    }
                    Toast.makeText(Download_sonPic_Activity.this, "设置成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
