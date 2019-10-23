package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.ScheduleTheProjectEndAdapter;
import com.xcy.fzb.project_side.presente.StatusBar;

//TODO 項目进度
public class ProjectScheduleActivity extends AppCompatActivity {

    ImageView project_schedule_return;

    ImageView project_schedule_img1;
    ImageView project_schedule_img2;
    ImageView project_schedule_img3;

    TextView project_schedule_tv1;
    TextView project_schedule_tv2;
    TextView project_schedule_tv3;
    TextView project_schedule_tv4;
    TextView project_schedule_tv5;
    TextView project_schedule_tv6;
    TextView project_schedule_tv7;
    TextView project_schedule_tv8;

    RecyclerView project_schedule_rv;

    ScheduleTheProjectEndAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_schedule);
        StatusBar.makeStatusBarTransparent(this);
        initView();

    }

    private void initView() {

        project_schedule_return = findViewById(R.id.project_schedule_return);

        project_schedule_img1 = findViewById(R.id.project_schedule_img1);
        project_schedule_img2 = findViewById(R.id.project_schedule_img2);
        project_schedule_img3 = findViewById(R.id.project_schedule_img3);

        project_schedule_tv1 = findViewById(R.id.project_schedule_tv1);
        project_schedule_tv2 = findViewById(R.id.project_schedule_tv2);
        project_schedule_tv3 = findViewById(R.id.project_schedule_tv3);
        project_schedule_tv4 = findViewById(R.id.project_schedule_tv4);
        project_schedule_tv5 = findViewById(R.id.project_schedule_tv5);
        project_schedule_tv6 = findViewById(R.id.project_schedule_tv6);
        project_schedule_tv7 = findViewById(R.id.project_schedule_tv7);
        project_schedule_tv8 = findViewById(R.id.project_schedule_tv8);

        project_schedule_rv = findViewById(R.id.project_schedule_rv);

        initData();

        project_schedule_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @SuppressLint("WrongConstant")
    private void initData() {
        adapter = new ScheduleTheProjectEndAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        project_schedule_rv.setLayoutManager(manager);


        project_schedule_rv.setAdapter(adapter);

    }
}
