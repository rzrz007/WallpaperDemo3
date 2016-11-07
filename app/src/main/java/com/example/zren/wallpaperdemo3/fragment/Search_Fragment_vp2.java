package com.example.zren.wallpaperdemo3.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.activity.Activity_Search_10sort_detial;
import com.example.zren.wallpaperdemo3.utils.XCRoundRectImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_Fragment_vp2 extends Fragment implements View.OnClickListener {

    private XCRoundRectImageView view1,view2;
    public Search_Fragment_vp2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search__fragment_vp2, container, false);

        view1= (XCRoundRectImageView) view.findViewById(R.id.view1);
        view2= (XCRoundRectImageView) view.findViewById(R.id.view2);

        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view1:
                Intent intent = new Intent(getContext(), Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","58");
                startActivity(intent);
                break;
            case R.id.view2:
                intent = new Intent(getContext(), Activity_Search_10sort_detial.class);
                intent.putExtra("point_detial","61");
                startActivity(intent);
                break;
        }
    }
}
