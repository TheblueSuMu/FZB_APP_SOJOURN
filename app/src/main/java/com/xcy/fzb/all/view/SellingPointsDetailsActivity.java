package com.xcy.fzb.all.view;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;


//TODO 卖点详情
public class SellingPointsDetailsActivity extends AllActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling_points_details);
        StatusBar.makeStatusBarTransparent(this);

        initView();

    }

    private void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }


    @Override
    public void onClick(View view) {

    }

}
