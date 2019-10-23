package com.xcy.fzb.project_side.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.presente.StatusBar;

public class AboutFZBTheProjectEndActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView return_about_fzb__the_project_end;
    private ImageView fx_about_fzb__the_project_end;
    private RelativeLayout jc_about_fzb__the_project_end;
    private RelativeLayout mz_about_fzb__the_project_end;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_fzbthe_project_end);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        return_about_fzb__the_project_end = findViewById(R.id.return_about_fzb__the_project_end);
        fx_about_fzb__the_project_end = findViewById(R.id.fx_about_fzb__the_project_end);
        jc_about_fzb__the_project_end = findViewById(R.id.jc_about_fzb__the_project_end);
        mz_about_fzb__the_project_end = findViewById(R.id.mz_about_fzb__the_project_end);

        return_about_fzb__the_project_end.setOnClickListener(this);
        fx_about_fzb__the_project_end.setOnClickListener(this);
        jc_about_fzb__the_project_end.setOnClickListener(this);
        mz_about_fzb__the_project_end.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.return_about_fzb__the_project_end:
                finish();
                break;
//                TODO 分享
            case R.id.fx_about_fzb__the_project_end:
//                FinalContents.showShare("房app下载", "http://admin.fangzuobiao.com:88/fangfang/static/down/index.html", "app下载", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3055880154,1625749017&fm=26&gp=0.jpg", "http://admin.fangzuobiao.com:88/fangfang/static/down/index.html", AboutFZBTheProjectEndActivity.this);
                break;
//                TODO 检测版本
            case R.id.jc_about_fzb__the_project_end:
                Toast.makeText(AboutFZBTheProjectEndActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
//                TODO 免责声明
            case R.id.mz_about_fzb__the_project_end:
                intent = new Intent(AboutFZBTheProjectEndActivity.this, DisclaimerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
