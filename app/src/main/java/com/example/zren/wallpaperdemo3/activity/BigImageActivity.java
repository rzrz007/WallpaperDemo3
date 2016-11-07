package com.example.zren.wallpaperdemo3.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.common.Images;
import com.example.zren.wallpaperdemo3.domain.ImagePath;
import com.example.zren.wallpaperdemo3.domain.Recommend_Images;
import com.example.zren.wallpaperdemo3.utils.DownLoadUtils;
import com.example.zren.wallpaperdemo3.utils.NetUtils;
import com.google.gson.Gson;
import com.j256.ormlite.stmt.query.In;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static android.R.attr.data;
import static android.R.attr.id;

public class BigImageActivity extends AppCompatActivity implements View.OnClickListener {

    private Snackbar snackbar;
    private PopupWindow popupWindow;
    private FrameLayout frameLayout_bigimg;
    private ViewPager viewPager_bigimg;
    private ImageButton imageButton_bigimg_popup_back, imageView_bigimg_popup_share;
    private TextView textView_bigimg_popup;
    private Button button_bigimg_snackbar_collection, button_bigimg_snackbar_setting, button_bigimg_snackbar_download;

    private List<String> data;
    private List<View> content;
    private BigImageViewPagerAdapter adapter;

    private String jsonString;
    private Recommend_Images recommend_images;
    private int id;

    private int local_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_big_image);
        this.initView();
        Intent intent = getIntent();
        ImagePath imagePath = (ImagePath) getIntent().getSerializableExtra("path");
        data = imagePath.getImagepath();

        id = intent.getIntExtra("data", 0);
        System.out.println("id=" + id + ",list=" + this.data.size());

        adapter = new BigImageViewPagerAdapter();
        viewPager_bigimg.setAdapter(adapter);
        viewPager_bigimg.setCurrentItem(id);
        this.setListener();
    }

    private void setListener() {
        this.frameLayout_bigimg.setOnClickListener(this);
        this.imageButton_bigimg_popup_back.setOnClickListener(this);
        this.imageView_bigimg_popup_share.setOnClickListener(this);
        this.button_bigimg_snackbar_collection.setOnClickListener(this);
        this.button_bigimg_snackbar_setting.setOnClickListener(this);
        this.button_bigimg_snackbar_download.setOnClickListener(this);
    }


    private void initView() {
        this.frameLayout_bigimg = (FrameLayout) findViewById(R.id.frameLayout_bigimg);
        this.viewPager_bigimg = (ViewPager) findViewById(R.id.viewPager_bigimg);
        this.textView_bigimg_popup = (TextView) findViewById(R.id.textView_bigimg_popup);

        //实例化snackbar并指定其父布局和参数
        this.snackbar = Snackbar.make(this.frameLayout_bigimg, null, Snackbar.LENGTH_INDEFINITE);
        //指定popupWindow中填充的布局
        View popupView = getLayoutInflater().inflate(R.layout.popupwindow_big_img, null);
        this.imageButton_bigimg_popup_back = (ImageButton) popupView.findViewById(R.id.imageButton_bigimg_popup_back);
        this.imageView_bigimg_popup_share = (ImageButton) popupView.findViewById(R.id.imageView_bigimg_popup_share);
        //实例化popupWindow并指定其父布局和参数
        this.popupWindow = new PopupWindow(popupView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, true);
        //给snackbar填充布局
        View snackbarview = snackbar.getView();//获取snackbar的View(其实就是SnackbarLayout)
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarview;//将获取的View转换成SnackbarLayout
        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(R.layout.snackbar_big_img, null);//加载布局文件新建View
        this.button_bigimg_snackbar_collection = (Button) add_view.findViewById(R.id.button_bigimg_snackbar_collection);
        this.button_bigimg_snackbar_setting = (Button) add_view.findViewById(R.id.button_bigimg_snackbar_setting);
        this.button_bigimg_snackbar_download = (Button) add_view.findViewById(R.id.button_bigimg_snackbar_download);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);//设置新建布局参数
        param.gravity = Gravity.CENTER_VERTICAL;//设置新建布局在Snackbar内垂直居中显示
        snackbarLayout.addView(add_view, 1, param);//将新建布局添加进snackbarLayout相应位置

        //给popupWindow设置参数
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.anim_menu_topbar);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            //大框架
            case R.id.frameLayout_bigimg:
                if (!snackbar.isShown()) {
                    snackbar.show();
                    popupWindow.showAtLocation(v, Gravity.TOP, 0, 0);
                } else if (snackbar.isShown()) {
                    snackbar.dismiss();
                    popupWindow.dismiss();
                }
                break;
            //返回
            case R.id.imageButton_bigimg_popup_back:
                this.finish();
                break;
            //分享
            case R.id.imageView_bigimg_popup_share:
                showShare();
                break;
            //收藏
            case R.id.button_bigimg_snackbar_collection:

                break;
            //设置为壁纸
            case R.id.button_bigimg_snackbar_setting:
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String path_son = data.get(local_id-1);
                            InputStream inputStream = NetUtils.getInputStreamByGET(path_son);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            try {
                                setWallpaper(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //下载
            case R.id.button_bigimg_snackbar_download:
                final String path_son = data.get(local_id-1);
                String single_pic_name = path_son.substring(path_son.lastIndexOf("/"));
                String single_pic_file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + single_pic_name;
                File file = new File(single_pic_file);
                if (!file.exists()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DownLoadUtils.downLoadPic(path_son);
                        }
                    }).start();
                    Toast.makeText(this, "图片:" + single_pic_name + "下载成功", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(this).setTitle("提示：").setMessage("图片已存在！").setNegativeButton("查看", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final String path_son = data.get(local_id-1);
                            String single_pic_name = path_son.substring(path_son.lastIndexOf("/"));
                            String single_pic_file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + single_pic_name;
                            Intent intent = new Intent(BigImageActivity.this,Download_sonPic_Activity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("picFile",single_pic_file);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }).setPositiveButton("取消", null).show();
                }
                break;
        }
    }

    private class BigImageViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //container.addView(content.get(position));
            local_id = position;
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(getApplicationContext()).load(data.get(position)).placeholder(R.drawable.load_big).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!snackbar.isShown()) {
                        snackbar.show();
                        popupWindow.showAtLocation(v, Gravity.TOP, 0, 0);
                    } else if (snackbar.isShown()) {
                        snackbar.dismiss();
                        popupWindow.dismiss();
                    }
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object instanceof View) {
                View view = (View) object;
                container.removeView(view);
            }
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
