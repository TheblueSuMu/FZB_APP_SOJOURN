package com.xcy.fzb.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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


//TODO 性别
public class SexActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout sex_return;
    private TextView sex_wc;
    private ImageView sex_nan;
    private ImageView sex_nv;
    private RelativeLayout sex_rl_1;
    private RelativeLayout sex_rl_2;

    String sex = "男";
    private String nc;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);


        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        sex_return = findViewById(R.id.sex_return);
        sex_wc = findViewById(R.id.sex_wc);
        sex_nan = findViewById(R.id.sex_nan);
        sex_nv = findViewById(R.id.sex_nv);

        sex_rl_1 = findViewById(R.id.sex_rl_1);
        sex_rl_2 = findViewById(R.id.sex_rl_2);

        sex_return.setOnClickListener(this);
        sex_wc.setOnClickListener(this);
        sex_rl_1.setOnClickListener(this);
        sex_rl_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.sex_return:
                intent = new Intent(SexActivity.this, PersonalInformationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sex_wc:
                nc = getIntent().getStringExtra("nc");
                Log.i("MyCL", "接收参数：" + nc);
                String url = "http://39.98.173.250:8080/fangfang/app/v1/commonUpdate/updateUser?" + "userId=" + FinalContents.getUserID() + "&name=" + nc + "&sex=" + sex + "&photo=" + "&industry=";

                OkHttpPost okHttpPost = new OkHttpPost(url);
                String data = okHttpPost.post();
                Log.i("MyCL", "参数：" + data);
                Gson gson = new Gson();
                NickNameBean nickNameBean = gson.fromJson(data, NickNameBean.class);
                NickNameBean.DataBean data1 = nickNameBean.getData();
                Toast.makeText(SexActivity.this, data1.getMessage(), Toast.LENGTH_SHORT).show();

                intent = new Intent(SexActivity.this, PersonalInformationActivity.class);
                startActivity(intent);
                finish();

                break;
            case R.id.sex_rl_1:
                sex_nan.setVisibility(View.VISIBLE);
                sex_nv.setVisibility(View.INVISIBLE);

                sex = "男";

                break;
            case R.id.sex_rl_2:
                sex_nan.setVisibility(View.INVISIBLE);
                sex_nv.setVisibility(View.VISIBLE);

                sex = "女";

                break;

        }

    }
}
