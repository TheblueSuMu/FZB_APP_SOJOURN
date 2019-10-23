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

//TODO 调单失败-项目进度
public class TheSingleFailureActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView the_single_failure_return;
    ImageView the_single_failure_img1;
    ImageView the_single_failure_img2;

    TextView the_single_failure_tv1;
    TextView the_single_failure_tv2;
    TextView the_single_failure_tv3;
    TextView the_single_failure_tv4;
    TextView the_single_failure_tv5;
    TextView the_single_failure_tv6;
    TextView the_single_failure_tv7;

    RecyclerView the_single_failure_rv;

    Button the_single_failure_bt1;
    Button the_single_failure_bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_single_failure);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        the_single_failure_return = findViewById(R.id.the_single_failure_return);
        the_single_failure_img1 = findViewById(R.id.the_single_failure_img1);
        the_single_failure_img2 = findViewById(R.id.the_single_failure_img2);
        the_single_failure_tv1 = findViewById(R.id.the_single_failure_tv1);
        the_single_failure_tv2 = findViewById(R.id.the_single_failure_tv2);
        the_single_failure_tv3 = findViewById(R.id.the_single_failure_tv3);
        the_single_failure_tv4 = findViewById(R.id.the_single_failure_tv4);
        the_single_failure_tv5 = findViewById(R.id.the_single_failure_tv5);
        the_single_failure_tv6 = findViewById(R.id.the_single_failure_tv6);
        the_single_failure_tv7 = findViewById(R.id.the_single_failure_tv7);
        the_single_failure_rv = findViewById(R.id.the_single_failure_rv);
        the_single_failure_bt1 = findViewById(R.id.the_single_failure_bt1);
        the_single_failure_bt2 = findViewById(R.id.the_single_failure_bt2);

        the_single_failure_return.setOnClickListener(this);
        the_single_failure_bt1.setOnClickListener(this);
        the_single_failure_bt2.setOnClickListener(this);
        the_single_failure_img2.setOnClickListener(this);
        the_single_failure_tv6.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //            TODO 返回上一层
            case R.id.the_single_failure_return:
                finish();
                break;
            //            TODO 调单
            case R.id.the_single_failure_bt1:

                break;
            //            TODO 退单
            case R.id.the_single_failure_bt2:

                break;
            //            TODO 项目负责人电话
            case R.id.the_single_failure_img2:

                break;
            //            TODO 经纪人电话
            case R.id.the_single_failure_tv6:

                break;
        }


    }
}
