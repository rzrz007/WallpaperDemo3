package com.example.zren.wallpaperdemo3.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zren.wallpaperdemo3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_Fragment extends Fragment {


    public Search_Fragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        Search_Fragment search_fragment = new Search_Fragment();
        return search_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

}