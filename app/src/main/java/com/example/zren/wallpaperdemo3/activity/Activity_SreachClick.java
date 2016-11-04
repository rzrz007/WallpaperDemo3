package com.example.zren.wallpaperdemo3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.zren.wallpaperdemo3.R;

import java.util.List;
import java.util.Map;

public class Activity_SreachClick extends AppCompatActivity {

    private Button btn;
    private EditText searchEt;
    private ListView lv_serach;
    private String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_activity__sreach_click);

        searchEt= (EditText) findViewById(R.id.searchEt);
        lv_serach= (ListView) findViewById(R.id.lv_serach);
        btn= (Button) findViewById(R.id.btn);






        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                input = searchEt.getText().toString();
                System.out.println("input="+input);

                Intent intent = new Intent(Activity_SreachClick.this, Activity_SerachClick_detial.class);

                intent.putExtra("url_search",input);

                startActivity(intent);
            }
        });
    }



}
