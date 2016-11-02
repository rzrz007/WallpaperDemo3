package com.example.zren.wallpaperdemo3.fragment;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.common.Images;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Recommend_Fragment extends Fragment {
    List<Recommend_Body_Fragment> data;
    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private ViewPager viewPager_Body;
    private MyAdapter adapter;
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
}