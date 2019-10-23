package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.ClientTheProjectEndAdapter;
import com.xcy.fzb.project_side.presente.StatusBar;

public class ClientTheProjectEndActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView client_the_project_end_return;
    ImageView client_the_project_end_xs;

    LinearLayout client_the_project_end_ll_2;
    LinearLayout client_the_project_end_ll_3;
    LinearLayout client_the_project_end_ll_4;
    LinearLayout client_the_project_end_ll_5;
    LinearLayout client_the_project_end_ll_6;
    LinearLayout client_the_project_end_ll_7;
    LinearLayout client_the_project_end_ll_9;
    LinearLayout client_the_project_end_ll_10;
    LinearLayout client_the_project_end_ll_11;
    LinearLayout client_the_project_end_ll_12;
    LinearLayout client_the_project_end_ll_13;
    LinearLayout client_the_project_end_ll_14;

    RecyclerView client_the_project_end_rv;

    ClientTheProjectEndAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_the_project_end);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        client_the_project_end_return = findViewById(R.id.client_the_project_end_return);
        client_the_project_end_xs = findViewById(R.id.client_the_project_end_xs);
        client_the_project_end_ll_2 = findViewById(R.id.client_the_project_end_ll_2);
        client_the_project_end_ll_3 = findViewById(R.id.client_the_project_end_ll_3);
        client_the_project_end_ll_4 = findViewById(R.id.client_the_project_end_ll_4);
        client_the_project_end_ll_5 = findViewById(R.id.client_the_project_end_ll_5);
        client_the_project_end_ll_6 = findViewById(R.id.client_the_project_end_ll_6);
        client_the_project_end_ll_7 = findViewById(R.id.client_the_project_end_7);
        client_the_project_end_ll_9 = findViewById(R.id.client_the_project_end_ll_9);
        client_the_project_end_ll_10 = findViewById(R.id.client_the_project_end_ll_10);
        client_the_project_end_ll_11 = findViewById(R.id.client_the_project_end_ll_11);
        client_the_project_end_ll_12 = findViewById(R.id.client_the_project_end_ll_12);
        client_the_project_end_ll_13 = findViewById(R.id.client_the_project_end_ll_13);
        client_the_project_end_ll_14 = findViewById(R.id.client_the_project_end_ll_14);
        client_the_project_end_rv = findViewById(R.id.client_the_project_end_rv);

        client_the_project_end_return.setOnClickListener(this);
        client_the_project_end_xs.setOnClickListener(this);
        client_the_project_end_ll_2.setOnClickListener(this);
        client_the_project_end_ll_3.setOnClickListener(this);
        client_the_project_end_ll_4.setOnClickListener(this);
        client_the_project_end_ll_5.setOnClickListener(this);
        client_the_project_end_ll_6.setOnClickListener(this);
        client_the_project_end_ll_7.setOnClickListener(this);

        initData();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.client_the_project_end_return:
                finish();
                break;
            case R.id.client_the_project_end_xs:

                break;
            case R.id.client_the_project_end_ll_2:

                client_the_project_end_ll_9.setVisibility(View.VISIBLE);
                client_the_project_end_ll_10.setVisibility(View.GONE);
                client_the_project_end_ll_11.setVisibility(View.GONE);
                client_the_project_end_ll_12.setVisibility(View.GONE);
                client_the_project_end_ll_13.setVisibility(View.GONE);
                client_the_project_end_ll_14.setVisibility(View.GONE);
                initData();
                break;
            case R.id.client_the_project_end_ll_3:

                client_the_project_end_ll_9.setVisibility(View.GONE);
                client_the_project_end_ll_10.setVisibility(View.VISIBLE);
                client_the_project_end_ll_11.setVisibility(View.GONE);
                client_the_project_end_ll_12.setVisibility(View.GONE);
                client_the_project_end_ll_13.setVisibility(View.GONE);
                client_the_project_end_ll_14.setVisibility(View.GONE);
                initData();
                break;
            case R.id.client_the_project_end_ll_4:

                client_the_project_end_ll_9.setVisibility(View.GONE);
                client_the_project_end_ll_10.setVisibility(View.GONE);
                client_the_project_end_ll_11.setVisibility(View.VISIBLE);
                client_the_project_end_ll_12.setVisibility(View.GONE);
                client_the_project_end_ll_13.setVisibility(View.GONE);
                client_the_project_end_ll_14.setVisibility(View.GONE);
                initData();
                break;
            case R.id.client_the_project_end_ll_5:

                client_the_project_end_ll_9.setVisibility(View.GONE);
                client_the_project_end_ll_10.setVisibility(View.GONE);
                client_the_project_end_ll_11.setVisibility(View.GONE);
                client_the_project_end_ll_12.setVisibility(View.VISIBLE);
                client_the_project_end_ll_13.setVisibility(View.GONE);
                client_the_project_end_ll_14.setVisibility(View.GONE);
                initData();
                break;
            case R.id.client_the_project_end_ll_6:

                client_the_project_end_ll_9.setVisibility(View.GONE);
                client_the_project_end_ll_10.setVisibility(View.GONE);
                client_the_project_end_ll_11.setVisibility(View.GONE);
                client_the_project_end_ll_12.setVisibility(View.GONE);
                client_the_project_end_ll_13.setVisibility(View.VISIBLE);
                client_the_project_end_ll_14.setVisibility(View.GONE);
                initData();
                break;
            case R.id.client_the_project_end_7:

                client_the_project_end_ll_9.setVisibility(View.GONE);
                client_the_project_end_ll_10.setVisibility(View.GONE);
                client_the_project_end_ll_11.setVisibility(View.GONE);
                client_the_project_end_ll_12.setVisibility(View.GONE);
                client_the_project_end_ll_13.setVisibility(View.GONE);
                client_the_project_end_ll_14.setVisibility(View.VISIBLE);
                initData();
                break;


        }

    }

    @SuppressLint("WrongConstant")
    private void initData() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new ClientTheProjectEndAdapter();
        client_the_project_end_rv.setLayoutManager(manager);



        client_the_project_end_rv.setAdapter(adapter);

    }
}
