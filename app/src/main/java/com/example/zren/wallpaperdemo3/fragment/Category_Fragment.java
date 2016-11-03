package com.example.zren.wallpaperdemo3.fragment;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.activity.CategoryDetailActivity;
import com.example.zren.wallpaperdemo3.domain.Category_Images;
import com.example.zren.wallpaperdemo3.utils.NetUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.R.attr.start;

public class Category_Fragment extends Fragment {

    //声明ListView
    private ListView listView_category;
    private ImageView imageView_load;
    //声明数据源
    private List<Category_Images.DataEntity> datas = new ArrayList<>();
    //声明适配器对象
    private MyAdapter adapter;
    //声明第一次加载标志位
    private boolean isFirst=true;
    private Handler handler;
    private AnimationDrawable animationDrawable;
    public Category_Fragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        Category_Fragment kind_fragment = new Category_Fragment();
        return kind_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        this.listView_category = (ListView) view.findViewById(R.id.category_lv);
        this.imageView_load= (ImageView) view.findViewById(R.id.imageView_load);
        this.imageView_load.setVisibility(View.VISIBLE);
        animationDrawable= (AnimationDrawable) imageView_load.getDrawable();
        animationDrawable.start();
        this.initData("http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=category");

        return view;
    }

    private void initView() {
        if (isFirst){
            this.adapter = new MyAdapter();
            this.imageView_load.setVisibility(View.GONE);
            this.listView_category.setAdapter(adapter);
            isFirst=false;
        }else {
            adapter.notifyDataSetChanged();
        }

        this.listView_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), CategoryDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",datas.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initData(final String path) {

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        initView();
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = NetUtils.getInputStreamByGET(path);
                    System.out.println("category.inputStream=" + inputStream);
                    if (inputStream != null) {
                        String json = NetUtils.inputStreamToString(inputStream);
                        Category_Images category_images = NetUtils.getT(json, Category_Images.class);
                        datas = category_images.getData();
                        System.out.println(datas);
                        handler.sendEmptyMessage(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 自定义适配器
     */
    private final class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.category_item, null);
                viewHolder = new ViewHolder();
                viewHolder.imageView_categoryPic = (ImageView) convertView.findViewById(R.id.categoryPic);
                viewHolder.textView_picCategoryName = (TextView) convertView.findViewById(R.id.picCategoryName);
                viewHolder.textView_descWords = (TextView) convertView.findViewById(R.id.descWords);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Category_Images.DataEntity data = datas.get(position);
            viewHolder.textView_picCategoryName.setText(data.getPicCategoryName());
            viewHolder.textView_descWords.setText(data.getDescWords());
            Picasso.with(getContext()).load(data.getCategoryPic()).resize(90,60).into(viewHolder.imageView_categoryPic);
            return convertView;
        }
    }

    private class ViewHolder {
        ImageView imageView_categoryPic;
        TextView textView_picCategoryName;
        TextView textView_descWords;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.isFirst = true;
    }


}
