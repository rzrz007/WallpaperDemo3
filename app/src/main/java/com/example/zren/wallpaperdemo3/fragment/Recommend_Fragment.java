package com.example.zren.wallpaperdemo3.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.common.Images;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Recommend_Fragment extends Fragment implements View.OnClickListener{
    List<Recommend_Body_Fragment> data;
    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private ViewPager viewPager_Body;
    private MyAdapter adapter;
    private ImageView imageView_line;
    private int width,height;
    private TextView textView_new,textView_hot,textView_random;
    int x=0;
    int y=0;
    public Recommend_Fragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        Recommend_Fragment recommend_fragment = new Recommend_Fragment();
        return recommend_fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        this.xRefreshView= (XRefreshView) view.findViewById(R.id.xRefreshView);
        this.recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        this.viewPager_Body= (ViewPager) view.findViewById(R.id.viewPager_Body);
        this.imageView_line= (ImageView) view.findViewById(R.id.imageView_line);
        this.textView_hot= (TextView) view.findViewById(R.id.textView_hot);
        this.textView_new= (TextView) view.findViewById(R.id.textView_new);
        this.textView_random= (TextView) view.findViewById(R.id.textView_random);
        textView_hot.setOnClickListener(this);
        textView_new.setOnClickListener(this);
        textView_random.setOnClickListener(this);

        //获的屏幕的宽高
        WindowManager wm = getActivity().getWindowManager();
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        this.adapter=new MyAdapter(Images.imageUrls);
        //需要注意的是:RecyclerView 必须设置数据后才会下拉刷新,否则不下拉.ListView可以在下拉时在加载数据并显示.

        data=new ArrayList<>();
        Recommend_Body_Fragment recommend_body_fragment1=new Recommend_Body_Fragment();
        Recommend_Body_Fragment recommend_body_fragment2=new Recommend_Body_Fragment();
        Recommend_Body_Fragment recommend_body_fragment3=new Recommend_Body_Fragment();
        data.add(recommend_body_fragment1);
        data.add(recommend_body_fragment2);
        data.add(recommend_body_fragment3);
        viewPager_Body.setAdapter(new MyPagerAdapter(getFragmentManager()));
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
        return view;
    }



    private final class MyPagerAdapter extends FragmentStatePagerAdapter {

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
    private void getData() {
        adapter.notifyDataSetChanged();
    }

    private final class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        private String[] imageUrls;

        public MyAdapter(String[] imageUrls) {
            this.imageUrls=imageUrls;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=View.inflate(getContext(),R.layout.item,null);
            ViewHolder viewHolder=new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Picasso.with(getContext()).load(imageUrls[position]).into(holder.imageView_img);
        }


        @Override
        public int getItemCount() {
            return imageUrls.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView_img;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView_img= (ImageView) itemView.findViewById(R.id.imageView_img);
            }
        }
    }
    public void onClick(View view){
        System.out.println("进入onClick");
        System.out.println("view.getId="+view.getId());

        switch (view.getId()){
            case R.id.textView_new:
                System.out.println("case R.id.textView_new:");
//               ClickNew();

                viewPager_Body.setCurrentItem(0,true);
                break;

            case R.id.textView_hot:
                System.out.println("case R.id.textView_hot:");
                //ClickHot();
                viewPager_Body.setCurrentItem(1,true);
                break;
            case R.id.textView_random:
                System.out.println("case R.id.textView_random:");
//                ClickRandom();
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