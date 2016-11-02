package com.example.zren.wallpaperdemo3.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.fragment.Category_Fragment;
import com.example.zren.wallpaperdemo3.fragment.Recommend_Fragment;
import com.example.zren.wallpaperdemo3.fragment.Search_Fragment;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {
    private long preTime;

    private LinearLayout layout_recommand, layout_category, layout_search;

    private ImageView imageView_recommand,imageView_category,imageView_search;

    private TextView textView_recommand,textView_category,textView_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_index);

        initView();


    }

    public void initView() {
        layout_recommand = (LinearLayout) this.findViewById(R.id.layout_recommand);
        layout_category = (LinearLayout) this.findViewById(R.id.layout_category);
        layout_search = (LinearLayout) this.findViewById(R.id.layout_search);

        layout_recommand.setOnClickListener(this);
        layout_category.setOnClickListener(this);
        layout_search.setOnClickListener(this);

        imageView_recommand = (ImageView) this.findViewById(R.id.imageView_recommand);
        imageView_category = (ImageView) this.findViewById(R.id.imageView_category);
        imageView_search = (ImageView) this.findViewById(R.id.imageView_search);

        textView_recommand = (TextView) this.findViewById(R.id.textView_recommand);
        textView_category = (TextView) this.findViewById(R.id.textView_category);
        textView_search = (TextView) this.findViewById(R.id.textView_search);


        //默认显示第一个碎片：“推荐”部分
        showFirstFragment();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_recommand:
                imageView_recommand.setImageResource(R.mipmap.bottom_recommand_selected);
                textView_recommand.setTextColor(getResources().getColor(R.color.textColor));
                imageView_category.setImageResource(R.mipmap.bottom_category);
                textView_category.setTextColor(Color.GRAY);
                imageView_search.setImageResource(R.mipmap.bottom_search);
                textView_search.setTextColor(Color.GRAY);
                showFirstFragment();
                break;
            case R.id.layout_category:
                imageView_recommand.setImageResource(R.mipmap.bottom_recommand);
                textView_recommand.setTextColor(Color.GRAY);
                imageView_category.setImageResource(R.mipmap.bottom_category_selected);
                textView_category.setTextColor(getResources().getColor(R.color.textColor));
                imageView_search.setImageResource(R.mipmap.bottom_search);
                textView_search.setTextColor(Color.GRAY);
                showSecondFragment();
                break;
            case R.id.layout_search:
                imageView_recommand.setImageResource(R.mipmap.bottom_recommand);
                textView_recommand.setTextColor(Color.GRAY);
                imageView_category.setImageResource(R.mipmap.bottom_category);
                textView_category.setTextColor(Color.GRAY);
                imageView_search.setImageResource(R.mipmap.bottom_search_selected);
                textView_search.setTextColor(getResources().getColor(R.color.textColor));
                showThirdFragment();
                break;
        }
    }

    //显示 “推荐” 碎片
    private void showFirstFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.linearLayout_for_fragment, Recommend_Fragment.getInstance());
        fragmentTransaction.commit();

    }

    //显示 “分类” 碎片
    private void showSecondFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.linearLayout_for_fragment, Category_Fragment.getInstance());
        fragmentTransaction.commit();
    }

    //显示 “搜索” 碎片
    private void showThirdFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.linearLayout_for_fragment, Search_Fragment.getInstance());
        fragmentTransaction.commit();
    }


//----------------------------------------重写返回键对应的方法------------------------------------------

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - preTime > 2000) {
                Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
            } else {
                this.finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            this.preTime = System.currentTimeMillis();
        }
        return super.onKeyUp(keyCode, event);
    }


}
