package com.example.zren.wallpaperdemo3.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.activity.Activity_Serach_10sort;
import com.example.zren.wallpaperdemo3.activity.Activity_Serach_base;
import com.example.zren.wallpaperdemo3.activity.Activity_SreachClick;
import com.example.zren.wallpaperdemo3.utils.XCRoundRectImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_Fragment extends Fragment implements View.OnClickListener{

    private LinearLayout searchArea;
    private ViewPager search_vp;
    private MyAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();

    private XCRoundRectImageView xc_car,xc_cartoon,xc_Scenery,xc_Animal;
    private LinearLayout ll_cute,ll_animal,ll_plant,ll_character;
    private TextView tv_point;

    public static Fragment getInstance() {
        Search_Fragment search_fragment = new Search_Fragment();
        return search_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initView(view);

        searchArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Activity_SreachClick.class);
                startActivity(intent);
            }
        });



        getData();


        adapter = new MyAdapter(getFragmentManager());
        search_vp.setAdapter(adapter);


        xc_car.setOnClickListener(this);
        xc_cartoon.setOnClickListener(this);
        xc_Scenery.setOnClickListener(this);
        xc_Animal.setOnClickListener(this);
        ll_cute.setOnClickListener(this);
        ll_animal.setOnClickListener(this);
        ll_plant.setOnClickListener(this);
        ll_character.setOnClickListener(this);
        tv_point.setOnClickListener(this);


        return view;
    }

    /**
     * 初始化UI
     * @param view
     */
    private void initView(View view) {
        search_vp = (ViewPager) view.findViewById(R.id.search_vp);
        searchArea= (LinearLayout) view.findViewById(R.id.searchArea);
        xc_car= (XCRoundRectImageView) view.findViewById(R.id.xc_car);
        xc_cartoon= (XCRoundRectImageView) view.findViewById(R.id.xc_cartoon);
        xc_Scenery= (XCRoundRectImageView) view.findViewById(R.id.xc_Scenery);
        xc_Animal= (XCRoundRectImageView) view.findViewById(R.id.xc_Animal);
        ll_cute= (LinearLayout) view.findViewById(R.id.ll_cute);
        ll_animal= (LinearLayout) view.findViewById(R.id.ll_animal);
        ll_plant= (LinearLayout) view.findViewById(R.id.ll_plant);
        ll_character= (LinearLayout) view.findViewById(R.id.ll_character);
        tv_point= (TextView) view.findViewById(R.id.tv_point);
    }


    /**
     * 初始化数据源
     */
    private void getData() {
        Search_Fragment_vp1 search_fragment_vp = new Search_Fragment_vp1();
        Search_Fragment_vp2 search_fragment_vp2 = new Search_Fragment_vp2();
        Search_Fragment_vp3 search_fragment_vp3 = new Search_Fragment_vp3();
        Search_Fragment_vp4 search_fragment_vp4 = new Search_Fragment_vp4();
        Search_Fragment_vp5 search_fragment_vp5 = new Search_Fragment_vp5();

        fragments.add(search_fragment_vp);
        fragments.add(search_fragment_vp2);
        fragments.add(search_fragment_vp3);
        fragments.add(search_fragment_vp4);
        fragments.add(search_fragment_vp5);
    }



    //点击事件处理
    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.xc_car:

               Intent intent = new Intent(getContext(), Activity_Serach_base.class);
               intent.putExtra("serach_title","汽车");
               startActivity(intent);
               break;
           case R.id.xc_Animal:

               intent = new Intent(getContext(), Activity_Serach_base.class);
               intent.putExtra("serach_title","动物");
               startActivity(intent);
               break;
           case R.id.xc_cartoon:

              intent = new Intent(getContext(), Activity_Serach_base.class);
               intent.putExtra("serach_title","卡通");
               startActivity(intent);
               break;
           case R.id.xc_Scenery:

              intent = new Intent(getContext(), Activity_Serach_base.class);
               intent.putExtra("serach_title","风景");
               startActivity(intent);
               break;
           case R.id.ll_animal:

                intent = new Intent(getContext(), Activity_Serach_base.class);
               intent.putExtra("serach_title","动物");
               startActivity(intent);
               break;
           case R.id.ll_character:

               intent = new Intent(getContext(), Activity_Serach_base.class);
               intent.putExtra("serach_title","文字");
               startActivity(intent);
               break;
           case R.id.ll_cute:

                intent = new Intent(getContext(), Activity_Serach_base.class);
               intent.putExtra("serach_title","可爱");
               startActivity(intent);
               break;
           case R.id.ll_plant:

               intent = new Intent(getContext(), Activity_Serach_base.class);
               intent.putExtra("serach_title","植物");
               startActivity(intent);
               break;
           case R.id.tv_point:
               intent = new Intent(getContext(), Activity_Serach_10sort.class);
               startActivity(intent);

       }

    }


    /**
     * 适配器
     */
    public class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
