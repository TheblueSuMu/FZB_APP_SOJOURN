package com.xcy.fzb.all.view;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;

public class AllActivity extends AppCompatActivity {

    public static boolean exit = false;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //检测是否有写的权限
        int permission = ActivityCompat.checkSelfPermission(AllActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {// 没有写的权限，去申请写的权限，
            ActivityCompat.requestPermissions(AllActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }

        if (Build.VERSION.SDK_INT >= 23) {
            //权限不够
            if (ContextCompat.checkSelfPermission(AllActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AllActivity.this, new String[]{Manifest.permission.CAMERA}, 3);
            } else {
                // 已经申请到相机权限

            }
        }
        initView();
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
