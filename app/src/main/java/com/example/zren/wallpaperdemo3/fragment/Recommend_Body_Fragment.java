package com.example.zren.wallpaperdemo3.fragment;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.example.zren.wallpaperdemo3.R;

import com.example.zren.wallpaperdemo3.common.Images;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class Recommend_Body_Fragment extends Fragment {

    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    public Recommend_Body_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_recommend__body_, container, false);
        this.xRefreshView= (XRefreshView) view.findViewById(R.id.xRefreshView);
        this.recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);

        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.adapter=new MyAdapter(Images.imageUrls);
        //需要注意的是:RecyclerView 必须设置数据后才会下拉刷新,否则不下拉.ListView可以在下拉时在加载数据并显示.
        this.recyclerView.setAdapter(this.adapter);
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                xRefreshView.stopRefresh();
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onLoadMore() {
                System.out.println("===onLoadMore=====");
            }

            @Override
            public void onRelease(float direction) {
                System.out.println("===onRelease(float direction="+direction+")=====");
            }
        });
        return view;
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
            WindowManager wm = getActivity().getWindowManager();

            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();

            int img_width=width/3;
            int img_height=img_width*2;

            Picasso.with(getContext()).load(imageUrls[position]).resize(img_width,img_height-30).into(holder.imageView_img);
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
