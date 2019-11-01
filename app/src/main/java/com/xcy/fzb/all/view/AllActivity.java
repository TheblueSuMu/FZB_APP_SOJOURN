package com.xcy.fzb.all.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;

public class AllActivity extends AppCompatActivity {

    public static boolean exit = false;

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


    @Override
    protected void onRestart() {
        super.onRestart();
        if (AllActivity.exit) {
            finish();
            Log.i("王","123");
        }else {
            Log.i("王","321");
        }

    }
}
