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

//TODO 退筹失败-项目进度
public class BackToRaiseFailureActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back_to_raise_failure_return;
    ImageView back_to_raise_failure_img1;
    ImageView back_to_raise_failure_img2;

    TextView back_to_raise_failure_tv1;
    TextView back_to_raise_failure_tv2;
    TextView back_to_raise_failure_tv3;
    TextView back_to_raise_failure_tv4;
    TextView back_to_raise_failure_tv5;
    TextView back_to_raise_failure_tv6;
    TextView back_to_raise_failure_tv7;

    RecyclerView back_to_raise_failure_rv;

    Button back_to_raise_failure_bt1;
    Button back_to_raise_failure_bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_to_raise_failure);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        back_to_raise_failure_return = findViewById(R.id.back_to_raise_failure_return);
        back_to_raise_failure_img1 = findViewById(R.id.back_to_raise_failure_img1);
        back_to_raise_failure_img2 = findViewById(R.id.back_to_raise_failure_img2);
        back_to_raise_failure_tv1 = findViewById(R.id.back_to_raise_failure_tv1);
        back_to_raise_failure_tv2 = findViewById(R.id.back_to_raise_failure_tv2);
        back_to_raise_failure_tv3 = findViewById(R.id.back_to_raise_failure_tv3);
        back_to_raise_failure_tv4 = findViewById(R.id.back_to_raise_failure_tv4);
        back_to_raise_failure_tv5 = findViewById(R.id.back_to_raise_failure_tv5);
        back_to_raise_failure_tv6 = findViewById(R.id.back_to_raise_failure_tv6);
        back_to_raise_failure_tv7 = findViewById(R.id.back_to_raise_failure_tv7);
        back_to_raise_failure_rv = findViewById(R.id.back_to_raise_failure_rv);
        back_to_raise_failure_bt1 = findViewById(R.id.back_to_raise_failure_bt1);
        back_to_raise_failure_bt2 = findViewById(R.id.back_to_raise_failure_bt2);

        back_to_raise_failure_return.setOnClickListener(this);
        back_to_raise_failure_bt1.setOnClickListener(this);
        back_to_raise_failure_bt2.setOnClickListener(this);
        back_to_raise_failure_img2.setOnClickListener(this);
        back_to_raise_failure_tv6.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //            TODO 返回上一层
            case R.id.back_to_raise_failure_return:
                finish();
                break;
            //            TODO 成交
            case R.id.back_to_raise_failure_bt1:

                break;
            //            TODO 退筹
            case R.id.back_to_raise_failure_bt2:

                break;
            //            TODO 项目负责人电话
            case R.id.back_to_raise_failure_img2:

                break;
            //            TODO 经纪人电话
            case R.id.back_to_raise_failure_tv6:

                break;
        }

    }
}
