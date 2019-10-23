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

//TODO  再次到访失败-项目进度
public class RevisitFailureActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView revisit_failure_return;
    ImageView revisit_failure_img1;
    ImageView revisit_failure_img2;

    TextView revisit_failure_tv1;
    TextView revisit_failure_tv2;
    TextView revisit_failure_tv3;
    TextView revisit_failure_tv4;
    TextView revisit_failure_tv5;
    TextView revisit_failure_tv6;
    TextView revisit_failure_tv7;

    RecyclerView revisit_failure_rv;

    Button revisit_failure_bt1;
    Button revisit_failure_bt2;
    Button revisit_failure_bt3;
    Button revisit_failure_bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisit_failure);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        revisit_failure_return = findViewById(R.id.revisit_failure_return);
        revisit_failure_img1 = findViewById(R.id.revisit_failure_img1);
        revisit_failure_img2 = findViewById(R.id.revisit_failure_img2);
        revisit_failure_tv1 = findViewById(R.id.revisit_failure_tv1);
        revisit_failure_tv2 = findViewById(R.id.revisit_failure_tv2);
        revisit_failure_tv3 = findViewById(R.id.revisit_failure_tv3);
        revisit_failure_tv4 = findViewById(R.id.revisit_failure_tv4);
        revisit_failure_tv5 = findViewById(R.id.revisit_failure_tv5);
        revisit_failure_tv6 = findViewById(R.id.revisit_failure_tv6);
        revisit_failure_tv7 = findViewById(R.id.revisit_failure_tv7);
        revisit_failure_rv = findViewById(R.id.revisit_failure_rv);

        revisit_failure_bt1 = findViewById(R.id.revisit_failure_bt1);
        revisit_failure_bt2 = findViewById(R.id.revisit_failure_bt2);
        revisit_failure_bt3 = findViewById(R.id.revisit_failure_bt3);
        revisit_failure_bt4 = findViewById(R.id.revisit_failure_bt4);

        revisit_failure_return.setOnClickListener(this);
        revisit_failure_img2.setOnClickListener(this);
        revisit_failure_tv6.setOnClickListener(this);
        revisit_failure_bt1.setOnClickListener(this);
        revisit_failure_bt2.setOnClickListener(this);
        revisit_failure_bt3.setOnClickListener(this);
        revisit_failure_bt4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.revisit_failure_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.revisit_failure_img2:

                break;
            //            TODO 经纪人电话
            case R.id.revisit_failure_tv6:

                break;
            //            TODO 重复到访
            case R.id.revisit_failure_bt1:

                break;
            //            TODO 申请登岛
            case R.id.revisit_failure_bt2:

                break;
            //            TODO 认筹
            case R.id.revisit_failure_bt3:

                break;
            //            TODO 成交
            case R.id.revisit_failure_bt4:

                break;
        }

    }
}
