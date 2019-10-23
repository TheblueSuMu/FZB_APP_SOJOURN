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

//TODO 登岛结束-项目进度
public class EndOfTheIslandActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView end_of_the_is_return;
    ImageView end_of_the_is_img1;
    ImageView end_of_the_is_img2;

    TextView end_of_the_is_tv1;
    TextView end_of_the_is_tv2;
    TextView end_of_the_is_tv3;
    TextView end_of_the_is_tv4;
    TextView end_of_the_is_tv5;
    TextView end_of_the_is_tv6;
    TextView end_of_the_is_tv7;

    RecyclerView end_of_the_is_rv;

    Button end_of_the_is_bt1;
    Button end_of_the_is_bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_the_island);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        end_of_the_is_return = findViewById(R.id.end_of_the_is_return);
        end_of_the_is_img1 = findViewById(R.id.end_of_the_is_img1);
        end_of_the_is_img2 = findViewById(R.id.end_of_the_is_img2);
        end_of_the_is_tv1 = findViewById(R.id.end_of_the_is_tv1);
        end_of_the_is_tv2 = findViewById(R.id.end_of_the_is_tv2);
        end_of_the_is_tv3 = findViewById(R.id.end_of_the_is_tv3);
        end_of_the_is_tv4 = findViewById(R.id.end_of_the_is_tv4);
        end_of_the_is_tv5 = findViewById(R.id.end_of_the_is_tv5);
        end_of_the_is_tv6 = findViewById(R.id.end_of_the_is_tv6);
        end_of_the_is_tv7 = findViewById(R.id.end_of_the_is_tv7);
        end_of_the_is_rv = findViewById(R.id.end_of_the_is_rv);

        end_of_the_is_bt1 = findViewById(R.id.end_of_the_is_bt1);
        end_of_the_is_bt2 = findViewById(R.id.end_of_the_is_bt2);

        end_of_the_is_return.setOnClickListener(this);
        end_of_the_is_img2.setOnClickListener(this);
        end_of_the_is_tv6.setOnClickListener(this);
        end_of_the_is_bt1.setOnClickListener(this);
        end_of_the_is_bt2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.end_of_the_is_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.end_of_the_is_img2:

                break;
            //            TODO 经纪人电话
            case R.id.end_of_the_is_tv6:

                break;
            //            TODO 成交
            case R.id.end_of_the_is_bt1:

                break;
            //            TODO 退筹
            case R.id.end_of_the_is_bt2:

                break;
        }

    }
}
