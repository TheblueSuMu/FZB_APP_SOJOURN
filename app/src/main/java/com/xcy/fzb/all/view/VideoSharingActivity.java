package com.xcy.fzb.all.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;


//TODO 视频分享
public class VideoSharingActivity extends AllActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_sharing);


        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

    }


    @Override
    public void onClick(View view) {

    }

}
