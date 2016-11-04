package com.example.zren.wallpaperdemo3.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.example.zren.wallpaperdemo3.R;

import com.example.zren.wallpaperdemo3.activity.BigImageActivity;
import com.example.zren.wallpaperdemo3.common.Images;
import com.example.zren.wallpaperdemo3.common.JsonUrl;
import com.example.zren.wallpaperdemo3.domain.Recommend_Images;
import com.example.zren.wallpaperdemo3.utils.NetUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Recommend_Body_Fragment extends Fragment {

    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    public String Path;
    public String JsonString;
    private Recommend_Images recommend_images;
    private static WindowManager wm;
    public Recommend_Body_Fragment() {
        // Required empty public constructor
    }

    public Recommend_Body_Fragment(String path) {
        Path = path;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_recommend__body_, container, false);
        wm = getActivity().getWindowManager();

        InitData(view);



        xRefreshView= (XRefreshView) view.findViewById(R.id.xRefreshView);
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                InitData(view);
                adapter.notifyDataSetChanged();
                xRefreshView.stopRefresh();
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

    private void InitData(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Path="+Path);
                final InputStream inputStream= NetUtils.getInputStreamByGET(Path);
                try {
                    JsonString=NetUtils.inputStreamToString(inputStream);
                    System.out.println("JsonSting"+JsonString);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("进入主线程");
                            Gson gson=new Gson();

                            recommend_images=gson.fromJson(JsonString,Recommend_Images.class);
                            Images.ImageList=new ArrayList<String>();
                            for(int i=0;i<recommend_images.getData().getWallpaperListInfo().size();i++){
                                Images.ImageList.add(recommend_images.getData().getWallpaperListInfo().get(i).getWallPaperMiddle());
                            }

                            recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
                            GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            adapter=new MyAdapter(Images.ImageList);
                            //需要注意的是:RecyclerView 必须设置数据后才会下拉刷新,否则不下拉.ListView可以在下拉时在加载数据并显示.
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
                                @Override
                                public void onItemClick(View view, String data) {
                                    Intent intent=new Intent(getContext(), BigImageActivity.class);
                                    startActivity(intent);
                                    System.out.println(view);
                                    Toast.makeText(getActivity(), "点击了图片", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private static final class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener{
        private ViewGroup viewGroup;
        private List<String> ImageList;

        @Override
        public void onClick(View v) {
            System.out.println("view="+v);
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(v,(String)v.getTag());
            }
        }


        public static interface OnRecyclerViewItemClickListener {
            void onItemClick(View view , String data);
        }
        private OnRecyclerViewItemClickListener mOnItemClickListener = null;

        public MyAdapter(List<String> imageList) {
            this.ImageList=imageList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            
            this.viewGroup=parent;
            View view=View.inflate(parent.getContext(),R.layout.item,null);
            ViewHolder viewHolder=new ViewHolder(view);
            view.setOnClickListener(this);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            

            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();

            int img_width=width/3;
            int img_height=img_width*2;
            Picasso.with(viewGroup.getContext()).load(ImageList.get(position)).resize(img_width,img_height-30).into(holder.imageView_img);
            holder.imageView_img.setTag(ImageList.get(position));
        }


        @Override
        public int getItemCount() {
            return ImageList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView_img;
            public ViewHolder(View itemView) {
                super(itemView);
                imageView_img= (ImageView) itemView.findViewById(R.id.imageView_img);
            }
        }

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }
    }

}
