package com.xcy.fzb.all.view;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;


//TODO 医疗中心
public class CentreActivity extends AllActivity implements View.OnClickListener {


    private RelativeLayout center_img;
    private TextView center_headline;
    private TextView center_title;
    private TextView center_time;
    private TextView center_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);


        initView();
    }

    public void initView() {

        StatusBar.makeStatusBarTransparent(this);

        center_img = findViewById(R.id.center_img);
        center_headline = findViewById(R.id.center_headline);
        center_title = findViewById(R.id.center_title);
        center_time = findViewById(R.id.center_time);
        center_content = findViewById(R.id.center_content);

        center_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.center_img) {
            finish();
        }

    }


}
