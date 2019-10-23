package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.captain_team.adapter.Captain_Team_SelectBrokerAdapter;

//TODO 圈层5-1团队人员（选择经纪人）
public class Captain_Team_SelectBrokerActivity extends AllActivity {

    RelativeLayout select_broker_img;
    RecyclerView select_broker_rv;
    Captain_Team_SelectBrokerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_select_broker);

        initView();

    }

    private void initView() {
		
		StatusBar.makeStatusBarTransparent(this);
		
        select_broker_img = findViewById(R.id.select_broker_img);
        select_broker_rv = findViewById(R.id.select_broker_rv);

        select_broker_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
    }

    @SuppressLint("WrongConstant")
    private void initData() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        select_broker_rv.setLayoutManager(manager);
        adapter = new Captain_Team_SelectBrokerAdapter();



        select_broker_rv.setAdapter(adapter);

    }
}
