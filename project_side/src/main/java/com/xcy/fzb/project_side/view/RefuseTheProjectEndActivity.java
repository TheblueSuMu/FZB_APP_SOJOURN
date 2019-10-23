package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.TheProjectEndRefuseAdapter;
import com.xcy.fzb.project_side.presente.StatusBar;

public class RefuseTheProjectEndActivity extends AppCompatActivity {

    ImageView the_project_end_refuse_return;
    RecyclerView the_project_end_refuse_rv;
    TheProjectEndRefuseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuse_the_project_end);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        the_project_end_refuse_return = findViewById(R.id.the_project_end_refuse_return);
        the_project_end_refuse_rv = findViewById(R.id.the_project_end_refuse_rv);

        initData();

        the_project_end_refuse_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {

        adapter = new TheProjectEndRefuseAdapter();

    }
}
