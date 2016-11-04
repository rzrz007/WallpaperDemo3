package com.example.zren.wallpaperdemo3.activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.common.Images;
import com.example.zren.wallpaperdemo3.utils.NetUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BigImageActivity extends AppCompatActivity implements View.OnClickListener{

    private Snackbar snackbar;
    private PopupWindow popupWindow;
    private FrameLayout frameLayout_bigimg;
    private ViewPager viewPager_bigimg;
    private ImageButton imageButton_bigimg_popup_back,imageView_bigimg_popup_share;
    private TextView textView_bigimg_popup;
    private Button button_bigimg_snackbar_collection,button_bigimg_snackbar_setting,button_bigimg_snackbar_download;

    private String[] data;
    private List<View> content;
    private BigImageViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        this.initData();
        this.initView();
        this.setListener();
    }

    private void initData() {
        this.data= Images.imageUrls;
        final int len=data.length;
        new Thread(new Runnable() {
            @Override
            public void run() {
                content=getBitmap(data,len);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter=new BigImageViewPagerAdapter();
                        viewPager_bigimg.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    private List<View> getBitmap(String[] imageUrls,int len) {
        this.content=new ArrayList<>();
        for (int i=0;i<len;i++){
            InputStream inputStream= NetUtils.getInputStreamByGET(imageUrls[i]);
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            ImageView imageView=new ImageView(BigImageActivity.this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            imageView.setImageBitmap(bitmap);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!snackbar.isShown()) {
                        snackbar.show();
                        popupWindow.showAtLocation(v,Gravity.TOP,0,0);
                    } else if (snackbar.isShown()) {
                        snackbar.dismiss();
                        popupWindow.dismiss();
                    }
                }
            });
            content.add(imageView);
        }
        return content;
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
        this.frameLayout_bigimg= (FrameLayout) findViewById(R.id.frameLayout_bigimg);
        this.viewPager_bigimg= (ViewPager) findViewById(R.id.viewPager_bigimg);
        this.textView_bigimg_popup= (TextView) findViewById(R.id.textView_bigimg_popup);

        //实例化snackbar并指定其父布局和参数
        this.snackbar=Snackbar.make(this.frameLayout_bigimg,null,Snackbar.LENGTH_INDEFINITE);
        //指定popupWindow中填充的布局
        View popupView = getLayoutInflater().inflate(R.layout.popupwindow_big_img, null);
        this.imageButton_bigimg_popup_back= (ImageButton) popupView.findViewById(R.id.imageButton_bigimg_popup_back);
        this.imageView_bigimg_popup_share= (ImageButton) popupView.findViewById(R.id.imageView_bigimg_popup_share);
        //实例化popupWindow并指定其父布局和参数
        this.popupWindow = new PopupWindow(popupView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, true);
        //给snackbar填充布局
        View snackbarview = snackbar.getView();//获取snackbar的View(其实就是SnackbarLayout)
        Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackbarview;//将获取的View转换成SnackbarLayout
        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(R.layout.snackbar_big_img,null);//加载布局文件新建View
        this.button_bigimg_snackbar_collection= (Button) add_view.findViewById(R.id.button_bigimg_snackbar_collection);
        this.button_bigimg_snackbar_setting= (Button) add_view.findViewById(R.id.button_bigimg_snackbar_setting);
        this.button_bigimg_snackbar_download= (Button) add_view.findViewById(R.id.button_bigimg_snackbar_download);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);//设置新建布局参数
        param.gravity= Gravity.CENTER_VERTICAL;//设置新建布局在Snackbar内垂直居中显示
        snackbarLayout.addView(add_view,1,param);//将新建布局添加进snackbarLayout相应位置

        //给popupWindow设置参数
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.anim_menu_topbar);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            //大框架
            case R.id.frameLayout_bigimg:
                if (!snackbar.isShown()) {
                    snackbar.show();
                    popupWindow.showAtLocation(v,Gravity.TOP,0,0);
                } else if (snackbar.isShown()) {
                    snackbar.dismiss();
                    popupWindow.dismiss();
                }
                break;
            //返回
            case R.id.imageButton_bigimg_popup_back:

                break;
            //分享
            case R.id.imageView_bigimg_popup_share:

                break;
            //收藏
            case R.id.button_bigimg_snackbar_collection:

                break;
            //设置为壁纸
            case R.id.button_bigimg_snackbar_setting:

                break;
            //下载
            case R.id.button_bigimg_snackbar_download:

                break;
        }
    }

    private class BigImageViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return content.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(content.get(position));
            return content.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(content.get(position));
        }
    }
}
