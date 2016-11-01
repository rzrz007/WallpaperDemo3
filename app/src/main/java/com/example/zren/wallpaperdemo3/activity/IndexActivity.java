package com.example.zren.wallpaperdemo3.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zren.wallpaperdemo3.R;
import com.example.zren.wallpaperdemo3.fragment.Kind_Fragment;
import com.example.zren.wallpaperdemo3.fragment.Recommend_Fragment;
import com.example.zren.wallpaperdemo3.fragment.Search_Fragment;

public class IndexActivity extends AppCompatActivity {
    private long preTime;
    private RadioGroup radioGroup;
    private RadioButton radioButton01,radioButton02,radioButton03;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_index);

        initView();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_01:
                        showFirstFragment();
                        break;
                    case R.id.rb_02:
                        showSecondFragment();
                        break;
                    case R.id.rb_03:
                        showThirdFragment();;
                        break;
                }

            }
        });

    }

    public void initView() {
        radioGroup = (RadioGroup) this.findViewById(R.id.radioGroup_IndexActivity);
        radioButton01 = (RadioButton) this.findViewById(R.id.rb_01);
        radioButton02 = (RadioButton) this.findViewById(R.id.rb_02);
        radioButton03 = (RadioButton) this.findViewById(R.id.rb_03);

        //默认显示第一个碎片：“精选”部分
        showFirstFragment();
    }

    //显示 “精选” 碎片
    private void showFirstFragment() {
        if(!(fragment instanceof Recommend_Fragment)){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.linearLayout_for_fragment,Recommend_Fragment.getInstance());
            ft.commit();
        }
    }

    //显示 “分类” 碎片
    private void showSecondFragment(){
        if(!(fragment instanceof Kind_Fragment)){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.linearLayout_for_fragment,Kind_Fragment.getInstance());
            ft.commit();
        }
    }

    //显示 “搜索” 碎片
    private void showThirdFragment(){
        if(!(fragment instanceof Search_Fragment)){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.linearLayout_for_fragment,Search_Fragment.getInstance());
            ft.commit();
        }
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
