package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.presente.StatusBar;

//TODO 退筹申请详情-项目进度
public class BackToRaiseDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back_to_raise_details_return;
    ImageView back_to_raise_details_img1;
    ImageView back_to_raise_details_img2;

    TextView back_to_raise_details_tv1;
    TextView back_to_raise_details_tv2;
    TextView back_to_raise_details_tv3;
    TextView back_to_raise_details_tv4;
    TextView back_to_raise_details_tv5;
    TextView back_to_raise_details_tv6;
    TextView back_to_raise_details_tv7;

    RecyclerView back_to_raise_details_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_to_raise_details);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        back_to_raise_details_return = findViewById(R.id.back_to_raise_details_return);
        back_to_raise_details_img1 = findViewById(R.id.back_to_raise_details_img1);
        back_to_raise_details_img2 = findViewById(R.id.back_to_raise_details_img2);
        back_to_raise_details_tv1 = findViewById(R.id.back_to_raise_details_tv1);
        back_to_raise_details_tv2 = findViewById(R.id.back_to_raise_details_tv2);
        back_to_raise_details_tv3 = findViewById(R.id.back_to_raise_details_tv3);
        back_to_raise_details_tv4 = findViewById(R.id.back_to_raise_details_tv4);
        back_to_raise_details_tv5 = findViewById(R.id.back_to_raise_details_tv5);
        back_to_raise_details_tv6 = findViewById(R.id.back_to_raise_details_tv6);
        back_to_raise_details_tv7 = findViewById(R.id.back_to_raise_details_tv7);
        back_to_raise_details_rv = findViewById(R.id.back_to_raise_details_rv);

        back_to_raise_details_return.setOnClickListener(this);
        back_to_raise_details_img2.setOnClickListener(this);
        back_to_raise_details_tv6.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //            TODO 返回上一层
            case R.id.back_to_raise_details_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.back_to_raise_details_img2:

                break;
            //            TODO 经纪人电话
            case R.id.back_to_raise_details_tv6:

                break;
        }

    }
}
