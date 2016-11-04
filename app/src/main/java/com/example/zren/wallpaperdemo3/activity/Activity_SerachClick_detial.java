package com.example.zren.wallpaperdemo3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.common.Images;
import com.example.zren.wallpaperdemo3.domain.Recommend_Images;
import com.example.zren.wallpaperdemo3.utils.NetUtils;
import com.example.zren.wallpaperdemo3.utils.Rearch_item;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Activity_SerachClick_detial extends AppCompatActivity {




    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    public String Path;
    public String JsonString;
    private Recommend_Images recommend_images;
    private static WindowManager wm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_activity__serach_click_detial);


        Intent intent = getIntent();
        String url_search = intent.getStringExtra("url_search");

        System.out.println("url_search="+url_search);
        Path="http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=search&a=search&q="+url_search+"&p=1&s=30";



        wm = Activity_SerachClick_detial.this.getWindowManager();

        InitData();



        xRefreshView= (XRefreshView) findViewById(R.id.xRefreshView);
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                InitData();
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






    }


    private void InitData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Path="+Path);
                final InputStream inputStream= NetUtils.getInputStreamByGET(Path);
                System.out.println("======1inputStream===="+inputStream);

                try {

                    JsonString=NetUtils.inputStreamToString(inputStream);

                    System.out.println("JsonSting"+JsonString);

                   runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("进入主线程");
                            Gson gson=new Gson();
                            System.out.println("123");
                            Rearch_item rearch_item = gson.fromJson(JsonString, Rearch_item.class);
                            Images.ImageList=new ArrayList<String>();
                            System.out.println(rearch_item.getData().getWallpaperListInfo().size());
                            for(int i=0;i<rearch_item.getData().getWallpaperListInfo().size();i++){
                                Images.ImageList.add(rearch_item.getData().getWallpaperListInfo().get(i).getWallPaperMiddle());
                            }

                            recyclerView= (RecyclerView)findViewById(R.id.recyclerView);
                            GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),3);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            adapter=new MyAdapter(Images.ImageList);
                            //需要注意的是:RecyclerView 必须设置数据后才会下拉刷新,否则不下拉.ListView可以在下拉时在加载数据并显示.
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
                                @Override
                                public void onItemClick(View view, String data) {
                                    Toast.makeText(getApplicationContext(), "点击了图片", Toast.LENGTH_SHORT).show();
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
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(v,(String)v.getTag());
            }
        }


        public static interface OnRecyclerViewItemClickListener {
            void onItemClick(View view, String data);
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
