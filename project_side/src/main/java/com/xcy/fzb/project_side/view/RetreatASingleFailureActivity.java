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

//TODO 退單失敗-項目進度
public class RetreatASingleFailureActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView retreat_a_single_failure_return;
    ImageView retreat_a_single_failure_img1;
    ImageView retreat_a_single_failure_img2;

    TextView retreat_a_single_failure_tv1;
    TextView retreat_a_single_failure_tv2;
    TextView retreat_a_single_failure_tv3;
    TextView retreat_a_single_failure_tv4;
    TextView retreat_a_single_failure_tv5;
    TextView retreat_a_single_failure_tv6;
    TextView retreat_a_single_failure_tv7;

    RecyclerView retreat_a_single_failure_rv;

    Button retreat_a_single_failure_bt1;
    Button retreat_a_single_failure_bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreat_asingle_failure);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        retreat_a_single_failure_return = findViewById(R.id.retreat_a_single_failure_return);
        retreat_a_single_failure_img1 = findViewById(R.id.retreat_a_single_failure_img1);
        retreat_a_single_failure_img2 = findViewById(R.id.retreat_a_single_failure_img2);
        retreat_a_single_failure_tv1 = findViewById(R.id.retreat_a_single_failure_tv1);
        retreat_a_single_failure_tv2 = findViewById(R.id.retreat_a_single_failure_tv2);
        retreat_a_single_failure_tv3 = findViewById(R.id.retreat_a_single_failure_tv3);
        retreat_a_single_failure_tv4 = findViewById(R.id.retreat_a_single_failure_tv4);
        retreat_a_single_failure_tv5 = findViewById(R.id.retreat_a_single_failure_tv5);
        retreat_a_single_failure_tv6 = findViewById(R.id.retreat_a_single_failure_tv6);
        retreat_a_single_failure_tv7 = findViewById(R.id.retreat_a_single_failure_tv7);
        retreat_a_single_failure_rv = findViewById(R.id.retreat_a_single_failure_rv);
        retreat_a_single_failure_bt1 = findViewById(R.id.retreat_a_single_failure_bt1);
        retreat_a_single_failure_bt2 = findViewById(R.id.retreat_a_single_failure_bt2);


        retreat_a_single_failure_return.setOnClickListener(this);
        retreat_a_single_failure_bt1.setOnClickListener(this);
        retreat_a_single_failure_bt2.setOnClickListener(this);
        retreat_a_single_failure_img2.setOnClickListener(this);
        retreat_a_single_failure_tv6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //            TODO 返回上一层
            case R.id.retreat_a_single_failure_return:
                finish();
                break;
            //            TODO 调单
            case R.id.retreat_a_single_failure_bt1:

                break;
            //            TODO 退单
            case R.id.retreat_a_single_failure_bt2:

                break;
            //            TODO 项目负责人电话
            case R.id.retreat_a_single_failure_img2:

                break;
            //            TODO 经纪人电话
            case R.id.retreat_a_single_failure_tv6:

                break;
        }

    }
}
