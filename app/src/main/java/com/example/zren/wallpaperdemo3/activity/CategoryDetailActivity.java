package com.example.zren.wallpaperdemo3.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.domain.Category_Images;
import com.example.zren.wallpaperdemo3.fragment.Recommend_Body_Fragment;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailActivity extends AppCompatActivity implements View.OnClickListener{

    List<Recommend_Body_Fragment> data;
    private Category_Images.DataEntity image;
    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private ViewPager viewPager_Body;
    private ImageView imageView_line;
    private int width,height;
    private TextView textView_new,textView_hot,textView_random,textView_top;
    int x=0;
    int y=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.fragment_recommend);

        this.image= (Category_Images.DataEntity) this.getIntent().getSerializableExtra("data");
        this.xRefreshView= (XRefreshView) this.findViewById(R.id.xRefreshView);
        this.recyclerView= (RecyclerView) this.findViewById(R.id.recyclerView);
        this.viewPager_Body= (ViewPager) this.findViewById(R.id.viewPager_Body);
        this.imageView_line= (ImageView) this.findViewById(R.id.imageView_line);
        this.textView_hot= (TextView) this.findViewById(R.id.textView_hot);
        this.textView_new= (TextView) this.findViewById(R.id.textView_new);
        this.textView_random= (TextView) this.findViewById(R.id.textView_random);
        this.textView_top= (TextView) this.findViewById(R.id.textView_top);
        this.textView_top.setText(image.getPicCategoryName());
        textView_hot.setOnClickListener(this);
        textView_new.setOnClickListener(this);
        textView_random.setOnClickListener(this);

        WindowManager wm=this.getWindowManager();
        width=wm.getDefaultDisplay().getWidth();
        height=wm.getDefaultDisplay().getHeight();
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        data=new ArrayList<>();
        String news="http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&index=1&size=60&bigid="+image.getID();
        String hot="http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=hotRecent&index=1&size=60&bigid="+image.getID();
        String random="http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=random&bigid="+image.getID();
        Recommend_Body_Fragment recommend_body_fragment1=new Recommend_Body_Fragment(news);
        Recommend_Body_Fragment recommend_body_fragment2=new Recommend_Body_Fragment(hot);
        Recommend_Body_Fragment recommend_body_fragment3=new Recommend_Body_Fragment(random);

        data.add(recommend_body_fragment1);
        data.add(recommend_body_fragment2);
        data.add(recommend_body_fragment3);
        viewPager_Body.setOffscreenPageLimit(3);
        viewPager_Body.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager_Body.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        ClickNew();
                        break;
                    case 1:
                        ClickHot();
                        break;
                    case 2:
                        ClickRandom();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private final  class MyPagerAdapter extends FragmentStatePagerAdapter{


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView_new:
                viewPager_Body.setCurrentItem(0,true);
                break;
            case R.id.textView_hot:
                viewPager_Body.setCurrentItem(1,true);
                break;
            case R.id.textView_random:
                viewPager_Body.setCurrentItem(2,true);
                break;
        }
    }

    private void ClickRandom() {
        textView_new.setTextColor(Color.BLACK);
        textView_hot.setTextColor(Color.BLACK);
        textView_random.setTextColor(Color.RED);
        TranslateAnimation translateAnimation_random=new TranslateAnimation(
                Animation.ABSOLUTE,//指定平移参考点在X轴方向为绝对距离值
                x,//平移动画开始前在X轴方向的起始偏移量
                Animation.ABSOLUTE,//指定平移参考点在X轴方向为绝对距离值
                (width/3)*2,//平移动画结束后在X轴方向的结束偏移量
                Animation.ABSOLUTE,//指定平移参考点在Y轴方向为绝对距离值
                y,//平移动画开始前在Y轴方向的起始偏移量
                Animation.ABSOLUTE,//指定平移参考点在X轴方向为绝对距离值
                0//平移动画结束后在Y轴方向的结束偏移量
        );
        translateAnimation_random.setDuration(500);
        translateAnimation_random.setFillAfter(true);
        //开始动画
        this.imageView_line.startAnimation(translateAnimation_random);
        x=(width/3)*2;
    }

    private void ClickHot() {
        textView_new.setTextColor(Color.BLACK);
        textView_hot.setTextColor(Color.RED);
        textView_random.setTextColor(Color.BLACK);
        TranslateAnimation translateAnimation_hot=new TranslateAnimation(
                Animation.ABSOLUTE,//指定平移参考点在X轴方向为绝对距离值
                x,//平移动画开始前在X轴方向的起始偏移量
                Animation.ABSOLUTE,//指定平移参考点在X轴方向为绝对距离值
                width/3,//平移动画结束后在X轴方向的结束偏移量
                Animation.ABSOLUTE,//指定平移参考点在Y轴方向为绝对距离值
                y,//平移动画开始前在Y轴方向的起始偏移量
                Animation.ABSOLUTE,//指定平移参考点在X轴方向为绝对距离值
                0//平移动画结束后在Y轴方向的结束偏移量
        );
        translateAnimation_hot.setDuration(500);
        translateAnimation_hot.setFillAfter(true);
        //开始动画
        this.imageView_line.startAnimation(translateAnimation_hot);
        x=width/3;
    }

    private void ClickNew() {
        textView_new.setTextColor(Color.RED);
        textView_hot.setTextColor(Color.BLACK);
        textView_random.setTextColor(Color.BLACK);
        TranslateAnimation translateAnimation_new=new TranslateAnimation(
                Animation.ABSOLUTE,//指定平移参考点在X轴方向为绝对距离值
                x,//平移动画开始前在X轴方向的起始偏移量
                Animation.ABSOLUTE,//指定平移参考点在X轴方向为绝对距离值
                0,//平移动画结束后在X轴方向的结束偏移量
                Animation.ABSOLUTE,//指定平移参考点在Y轴方向为绝对距离值
                y,//平移动画开始前在Y轴方向的起始偏移量
                Animation.ABSOLUTE,//指定平移参考点在X轴方向为绝对距离值
                0//平移动画结束后在Y轴方向的结束偏移量
        );
        translateAnimation_new.setDuration(500);
        translateAnimation_new.setFillAfter(true);
        //开始动画
        this.imageView_line.startAnimation(translateAnimation_new);
        x=0;
        y=0;
    }
}
