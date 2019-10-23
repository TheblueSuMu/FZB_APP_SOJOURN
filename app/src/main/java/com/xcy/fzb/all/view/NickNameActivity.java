package com.xcy.fzb.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.NickNameBean;
import com.xcy.fzb.all.persente.OkHttpPost;
import com.xcy.fzb.all.persente.StatusBar;

public class NickNameActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout nick_return;
    private TextView nick_wc;
    private EditText nick_change_name;
    private String text;
    private String xb;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);
        StatusBar.makeStatusBarTransparent(this);

        initView();

    }

    private void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        xb = getIntent().getStringExtra("xb");

        nick_return = findViewById(R.id.nick_return);
        nick_wc = findViewById(R.id.nick_wc);
        nick_change_name = findViewById(R.id.nick_change_name);

        nick_return.setOnClickListener(this);
        nick_wc.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.nick_return:
                intent = new Intent(NickNameActivity.this,PersonalInformationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nick_wc:
                text = nick_change_name.getText().toString();
                String url = "http://39.98.173.250:8080/fangfang/app/v1/commonUpdate/updateUser?" + "userId=" + FinalContents.getUserID() + "&name=" + text + "&sex="+ xb + "&photo=" + "&industry=";

                OkHttpPost okHttpPost = new OkHttpPost(url);
                String data = okHttpPost.post();
                Gson gson = new Gson();
                NickNameBean nickNameBean = gson.fromJson(data, NickNameBean.class);
                NickNameBean.DataBean data1 = nickNameBean.getData();
                Toast.makeText(NickNameActivity.this, data1.getMessage(), Toast.LENGTH_SHORT).show();
                finish();

                intent = new Intent(NickNameActivity.this,PersonalInformationActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}
