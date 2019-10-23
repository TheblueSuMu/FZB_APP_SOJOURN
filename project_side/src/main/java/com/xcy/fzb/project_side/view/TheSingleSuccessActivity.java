package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.presente.StatusBar;

//TODO 调单成功-项目进度
public class TheSingleSuccessActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView the_single_success_return;
    ImageView the_single_success_img1;
    ImageView the_single_success_img2;

    TextView the_single_success_tv1;
    TextView the_single_success_tv2;
    TextView the_single_success_tv3;
    TextView the_single_success_tv4;
    TextView the_single_success_tv5;
    TextView the_single_success_tv6;
    TextView the_single_success_tv7;

    RecyclerView the_single_success_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_single_success);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        the_single_success_return = findViewById(R.id.the_single_success_return);
        the_single_success_img1 = findViewById(R.id.the_single_success_img1);
        the_single_success_img2 = findViewById(R.id.the_single_success_img2);
        the_single_success_tv1 = findViewById(R.id.the_single_success_tv1);
        the_single_success_tv2 = findViewById(R.id.the_single_success_tv2);
        the_single_success_tv3 = findViewById(R.id.the_single_success_tv3);
        the_single_success_tv4 = findViewById(R.id.the_single_success_tv4);
        the_single_success_tv5 = findViewById(R.id.the_single_success_tv5);
        the_single_success_tv6 = findViewById(R.id.the_single_success_tv6);
        the_single_success_tv7 = findViewById(R.id.the_single_success_tv7);
        the_single_success_rv = findViewById(R.id.the_single_success_rv);

        the_single_success_return.setOnClickListener(this);
        the_single_success_img2.setOnClickListener(this);
        the_single_success_tv6.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            //            TODO 返回上一层
            case R.id.the_single_success_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.the_single_success_img2:

                break;
            //            TODO 经纪人电话
            case R.id.the_single_success_tv6:

                break;

        }

    }
}
