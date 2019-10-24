package com.xcy.fzb.all.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;

public class VisitDetailsActivity extends AllActivity implements View.OnClickListener {

    ImageView visit_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_details);


        initView();

    }

    //TODO 视图
    private void initView() {

        StatusBar.makeStatusBarTransparent(this);


        visit_return = findViewById(R.id.visit_return);


        visit_return.setOnClickListener(this);
    }

    //TODO 点击事件
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.visit_return:
                finish();
                break;
        }

    }
}
