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

//TODO 登岛成功-项目进度
public class TheIslandSuccessfulActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView the_island_successful_return;
    ImageView the_island_successful_img1;
    ImageView the_island_successful_img2;

    TextView the_island_successful_tv1;
    TextView the_island_successful_tv2;
    TextView the_island_successful_tv3;
    TextView the_island_successful_tv4;
    TextView the_island_successful_tv5;
    TextView the_island_successful_tv6;
    TextView the_island_successful_tv7;

    RecyclerView the_island_successful_rv;

    Button the_island_successful_bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_island_successful);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        the_island_successful_return = findViewById(R.id.the_island_successful_return);
        the_island_successful_img1 = findViewById(R.id.the_island_successful_img1);
        the_island_successful_img2 = findViewById(R.id.the_island_successful_img2);
        the_island_successful_tv1 = findViewById(R.id.the_island_successful_tv1);
        the_island_successful_tv2 = findViewById(R.id.the_island_successful_tv2);
        the_island_successful_tv3 = findViewById(R.id.the_island_successful_tv3);
        the_island_successful_tv4 = findViewById(R.id.the_island_successful_tv4);
        the_island_successful_tv5 = findViewById(R.id.the_island_successful_tv5);
        the_island_successful_tv6 = findViewById(R.id.the_island_successful_tv6);
        the_island_successful_tv7 = findViewById(R.id.the_island_successful_tv7);
        the_island_successful_rv = findViewById(R.id.the_island_successful_rv);

        the_island_successful_bt1 = findViewById(R.id.the_island_successful_bt1);

        the_island_successful_return.setOnClickListener(this);
        the_island_successful_img2.setOnClickListener(this);
        the_island_successful_tv6.setOnClickListener(this);
        the_island_successful_bt1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.the_island_successful_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.the_island_successful_img2:

                break;
            //            TODO 经纪人电话
            case R.id.the_island_successful_tv6:

                break;
            //            TODO 补全信息
            case R.id.the_island_successful_bt1:

                break;
        }

    }
}
