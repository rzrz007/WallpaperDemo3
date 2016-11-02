package com.example.zren.wallpaperdemo3.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zren.wallpaperdemo3.R;

public class Category_Fragment extends Fragment {


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
        return view;
    }

}
