package com.xcy.fzb.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.persente.StatusBar;

public class AboutFZBActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout fzb_return;
    private ImageView fzb_fx;
    private RelativeLayout fzb_jc;
    private RelativeLayout fzb_mz;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_fzb);


        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        fzb_return = findViewById(R.id.fzb_return);
        fzb_fx = findViewById(R.id.fzb_fx);
        fzb_jc = findViewById(R.id.fzb_jc);
        fzb_mz = findViewById(R.id.fzb_mz);

        fzb_return.setOnClickListener(this);
        fzb_fx.setOnClickListener(this);
        fzb_jc.setOnClickListener(this);
        fzb_mz.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.fzb_return:
                finish();
                break;
//                TODO 分享
            case R.id.fzb_fx:
                FinalContents.showShare("房app下载", "http://admin.fangzuobiao.com:88/fangfang/static/down/index.html", "app下载", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3055880154,1625749017&fm=26&gp=0.jpg", "http://admin.fangzuobiao.com:88/fangfang/static/down/index.html", AboutFZBActivity.this);
                break;
//                TODO 检测版本
            case R.id.fzb_jc:
                Toast.makeText(AboutFZBActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
//                TODO 免责声明
            case R.id.fzb_mz:
                intent = new Intent(AboutFZBActivity.this, DisclaimerActivity.class);
                startActivity(intent);
                break;
        }

    }
}
