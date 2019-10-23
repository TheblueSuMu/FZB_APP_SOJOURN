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

//TODO 	重复到访-项目进度
public class RepeatVisitsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView repeat_visits_return;
    ImageView repeat_visits_img1;
    ImageView repeat_visits_img2;

    TextView repeat_visits_tv1;
    TextView repeat_visits_tv2;
    TextView repeat_visits_tv3;

    RecyclerView repeat_visits_rv;

    Button repeat_visits_bt1;
    Button repeat_visits_bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_visits);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        repeat_visits_return = findViewById(R.id.repeat_visits_return);
        repeat_visits_img1 = findViewById(R.id.repeat_visits_img1);
        repeat_visits_img2 = findViewById(R.id.repeat_visits_img2);
        repeat_visits_tv1 = findViewById(R.id.repeat_visits_tv1);
        repeat_visits_tv2 = findViewById(R.id.repeat_visits_tv2);
        repeat_visits_tv3 = findViewById(R.id.repeat_visits_tv3);

        repeat_visits_rv = findViewById(R.id.repeat_visits_rv);

        repeat_visits_bt1 = findViewById(R.id.repeat_visits_bt1);
        repeat_visits_bt2 = findViewById(R.id.repeat_visits_bt2);

        repeat_visits_return.setOnClickListener(this);
        repeat_visits_img2.setOnClickListener(this);
        repeat_visits_bt1.setOnClickListener(this);
        repeat_visits_bt2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.repeat_visits_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.repeat_visits_img2:

                break;
            //            TODO 重复到访
            case R.id.repeat_visits_bt1:

                break;
            //            TODO 申請登岛
            case R.id.repeat_visits_bt2:

                break;
        }

    }
}
