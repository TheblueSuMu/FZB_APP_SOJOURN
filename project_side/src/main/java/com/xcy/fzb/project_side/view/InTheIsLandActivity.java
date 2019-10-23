package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.presente.StatusBar;

//TODO 登岛中-项目进度
public class InTheIsLandActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView in_the_is_land_return;
    ImageView in_the_is_land_img1;
    ImageView in_the_is_land_img2;

    TextView in_the_is_land_tv1;
    TextView in_the_is_land_tv2;
    TextView in_the_is_land_tv3;
    TextView in_the_is_land_tv4;
    TextView in_the_is_land_tv5;
    TextView in_the_is_land_tv6;
    TextView in_the_is_land_tv7;

    RecyclerView in_the_is_land_rv;

    Button in_the_is_land_bt1;
    Button in_the_is_land_bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_the_is_land);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        in_the_is_land_return = findViewById(R.id.in_the_is_land_return);
        in_the_is_land_img1 = findViewById(R.id.in_the_is_land_img1);
        in_the_is_land_img2 = findViewById(R.id.in_the_is_land_img2);
        in_the_is_land_tv1 = findViewById(R.id.in_the_is_land_tv1);
        in_the_is_land_tv2 = findViewById(R.id.in_the_is_land_tv2);
        in_the_is_land_tv3 = findViewById(R.id.in_the_is_land_tv3);
        in_the_is_land_tv4 = findViewById(R.id.in_the_is_land_tv4);
        in_the_is_land_tv5 = findViewById(R.id.in_the_is_land_tv5);
        in_the_is_land_tv6 = findViewById(R.id.in_the_is_land_tv6);
        in_the_is_land_tv7 = findViewById(R.id.in_the_is_land_tv7);
        in_the_is_land_rv = findViewById(R.id.in_the_is_land_rv);

        in_the_is_land_bt1 = findViewById(R.id.in_the_is_land_bt1);
        in_the_is_land_bt2 = findViewById(R.id.in_the_is_land_bt2);

        in_the_is_land_return.setOnClickListener(this);
        in_the_is_land_img2.setOnClickListener(this);
        in_the_is_land_tv6.setOnClickListener(this);
        in_the_is_land_bt1.setOnClickListener(this);
        in_the_is_land_bt2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.in_the_is_land_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.in_the_is_land_img2:

                break;
            //            TODO 经纪人电话
            case R.id.in_the_is_land_tv6:

                break;
            //            TODO 认筹
            case R.id.in_the_is_land_bt1:

                break;
            //            TODO 成交
            case R.id.in_the_is_land_bt2:

                break;
        }

    }
}
