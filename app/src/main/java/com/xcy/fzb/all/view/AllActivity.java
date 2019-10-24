package com.xcy.fzb.all.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;

public class AllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏


        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

    }
}
