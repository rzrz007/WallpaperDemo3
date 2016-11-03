package com.example.zren.wallpaperdemo3.fragment;


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

import com.andview.refreshview.XRefreshView;
import com.example.zren.wallpaperdemo3.R;

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
    private Handler mainHandler;
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


        InitData(view);

        new Handler(){
            public void handleMessage(Message msg) {
                System.out.println("进入handleMessage");
                Gson gson=new Gson();
                String str= (String) msg.obj;
                recommend_images=gson.fromJson(str,Recommend_Images.class);
                for(int i=0;i<recommend_images.getData().getWallpaperListInfo().size();i++){
                    Images.ImageList.add(recommend_images.getData().getWallpaperListInfo().get(i).getWallPaperMiddle());
                }

                recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
                recyclerView.setLayoutManager(gridLayoutManager);
                adapter=new MyAdapter(Images.imageUrls,Images.ImageList);
                //需要注意的是:RecyclerView 必须设置数据后才会下拉刷新,否则不下拉.ListView可以在下拉时在加载数据并显示.
                recyclerView.setAdapter(adapter);

            }
        };
        xRefreshView= (XRefreshView) view.findViewById(R.id.xRefreshView);
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                InitData(view);
                adapter.notifyDataSetChanged();
                xRefreshView.stopRefresh();
                /*new Thread(new Runnable() {
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
                }).start();*/
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
                    /*Message message=Message.obtain();
                    message.obj=JsonString;
                    Message msg=Message.obtain(message);
                    msg.setTarget(mainHandler);
                    msg.sendToTarget();*/
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
                            adapter=new MyAdapter(Images.imageUrls,Images.ImageList);
                            //需要注意的是:RecyclerView 必须设置数据后才会下拉刷新,否则不下拉.ListView可以在下拉时在加载数据并显示.
                            recyclerView.setAdapter(adapter);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void getData() {
        adapter.notifyDataSetChanged();
    }

    private final class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        private String[] imageUrls;
        private List<String> ImageList;

        public MyAdapter(String[] imageUrls ,List<String> imageList) {
            this.imageUrls=imageUrls;
            this.ImageList=imageList;
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
            System.out.println("ImageList.get(position)="+ImageList.get(position));
            Picasso.with(getContext()).load(ImageList.get(position)).resize(img_width,img_height-30).into(holder.imageView_img);
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
