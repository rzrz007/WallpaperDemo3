package com.example.zren.wallpaperdemo3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.domain.Category_Images;

public class CategoryDetailActivity extends AppCompatActivity {

    private Category_Images.DataEntity data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.fragment_recommend);

        this.data= (Category_Images.DataEntity) this.getIntent().getSerializableExtra("data");
        Toast.makeText(this, "id="+data.getID()+",name="+data.getPicCategoryName(), Toast.LENGTH_SHORT).show();
    }
}
