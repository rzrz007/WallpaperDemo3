package com.example.zren.wallpaperdemo3.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.utils.DownLoadUtils;
import com.example.zren.wallpaperdemo3.utils.FileReadUtils;

import java.io.File;
import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {
    private TextView textView_showState;
    private GridView gridView_showPic;
    private Button button_back;

    private Thread readSdcard;

    private int count;

    private ArrayList<String> dirAllStrArr;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    textView_showState.setVisibility(View.GONE);
                    break;
                case 1:
                    textView_showState.setText("未找到图片资源");
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_download);

        DownLoadUtils.isGrantExternalRW(this);

        textView_showState = (TextView) findViewById(R.id.textView_showState);
        gridView_showPic = (GridView) findViewById(R.id.gridView_showPic);
        button_back = (Button) findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

		/* 遍历sdcard旗下的所有文件夹开始 */
        readSdcard = new Thread() {
            // 获取sdCard的 Pictures 文件夹路径
            private String sdpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            private File dirFile = new File(sdpath);

            public void run() {
                try {
                    dirAllStrArr = FileReadUtils.DirAll(dirFile);
                    //打印图片路径集合
                    System.out.println(dirAllStrArr + "==============");
                    count = dirAllStrArr.size();
                    if (dirAllStrArr.isEmpty()) {
                        handler.sendEmptyMessage(1);
                    }else{
                        handler.sendEmptyMessage(0);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        readSdcard.start();

        final BaseAdapter baseAdapter = new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup viewGroup) {
                //定义网状视图子元素ImageView
                ImageView imageView_son;

                if (convertView == null) {
                    imageView_son = new ImageView(DownloadActivity.this);
                    // 自动缩放为宽高比
                    imageView_son.setAdjustViewBounds(true);
                    // 设置图片保持宽高比显示
                    imageView_son.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    // 设置图片内边距
                    imageView_son.setPadding(5, 5, 5, 5);
                } else {
                    imageView_son = (ImageView) convertView;
                }
                String filePath = dirAllStrArr.get(position);
                File file = new File(filePath);

                Bitmap bm = BitmapFactory.decodeFile(filePath, FileReadUtils.getHeapOpts(file));

                imageView_son.setImageBitmap(bm);

                return imageView_son;
            }

            // 获取当前选项
            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            // 获取数量
            @Override
            public int getCount() {
                return count;
            }
        };

        gridView_showPic.setAdapter(baseAdapter);// 把适配器与网格视图链接起来

        //设置图片点击事件
        gridView_showPic.setOnItemClickListener(new AdapterView.OnItemClickListener() {// 点击网格组件的任意一张图片时候的事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DownloadActivity.this, Download_sonPic_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("imgPath", dirAllStrArr.get(position));// 传递点击的图片的id到ViewActivity
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        gridView_showPic.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("onItemLongClick(AdapterView<?> parent = " + parent + ", View view=" + view + ", int position=" + position + ", long id=" + id + ")");
                final String temp = dirAllStrArr.get(position);
                File file = new File(temp);
                if (file.exists()) {
                    new AlertDialog.Builder(DownloadActivity.this).setTitle("提示：").setMessage("是否删除？").setNegativeButton("是的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            File file1 = new File(temp);
                            if (file1.delete()) {
                                baseAdapter.notifyDataSetChanged();
                                Toast.makeText(DownloadActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(DownloadActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setPositiveButton("不了", null).show();

                } else {
                    Toast.makeText(DownloadActivity.this, "图片不存在", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
