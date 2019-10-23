package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.presente.StatusBar;

//TODO 退单成功-项目进度
public class BackASingleSuccessActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back_a_single_success_return;
    ImageView back_a_single_success_img1;
    ImageView back_a_single_success_img2;

    TextView back_a_single_success_tv1;
    TextView back_a_single_success_tv2;
    TextView back_a_single_success_tv3;
    TextView back_a_single_success_tv4;
    TextView back_a_single_success_tv5;
    TextView back_a_single_success_tv6;
    TextView back_a_single_success_tv7;

    RecyclerView back_a_single_success_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_asingle_success);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        back_a_single_success_return = findViewById(R.id.back_a_single_success_return);
        back_a_single_success_img1 = findViewById(R.id.back_a_single_success_img1);
        back_a_single_success_img2 = findViewById(R.id.back_a_single_success_img2);
        back_a_single_success_tv1 = findViewById(R.id.back_a_single_success_tv1);
        back_a_single_success_tv2 = findViewById(R.id.back_a_single_success_tv2);
        back_a_single_success_tv3 = findViewById(R.id.back_a_single_success_tv3);
        back_a_single_success_tv4 = findViewById(R.id.back_a_single_success_tv4);
        back_a_single_success_tv5 = findViewById(R.id.back_a_single_success_tv5);
        back_a_single_success_tv6 = findViewById(R.id.back_a_single_success_tv6);
        back_a_single_success_tv7 = findViewById(R.id.back_a_single_success_tv7);
        back_a_single_success_rv = findViewById(R.id.back_a_single_success_rv);

        back_a_single_success_return.setOnClickListener(this);
        back_a_single_success_img2.setOnClickListener(this);
        back_a_single_success_tv6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            //            TODO 返回上一层
            case R.id.back_a_single_success_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.back_a_single_success_img2:

                break;
            //            TODO 经纪人电话
            case R.id.back_a_single_success_tv6:

                break;

        }

    }
}
