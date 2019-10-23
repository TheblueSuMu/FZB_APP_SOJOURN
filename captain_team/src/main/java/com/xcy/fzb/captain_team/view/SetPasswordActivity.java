package com.xcy.fzb.captain_team.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.NickNameBean;
import com.xcy.fzb.captain_team.persente.OkHttpPost;
import com.xcy.fzb.captain_team.persente.StatusBar;


public class SetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView set_return;
    private TextView set_wc;
    private EditText set_j_password;
    private EditText set_x_password;
    private EditText set_x_again_password;
    private TextView set_z_password;
    private String msg;
    private String message;
    private String j;
    private String x;
    private String x_again;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        StatusBar.makeStatusBarTransparent(this);

        initView();

    }

    private void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        set_return = findViewById(R.id.set_return);
        set_wc = findViewById(R.id.set_wc);
        set_j_password = findViewById(R.id.set_j_password);//旧密码
        set_x_password = findViewById(R.id.set_x_password);//新密码
        set_x_again_password = findViewById(R.id.set_x_again_password);//再次输入的新密码
        set_z_password = findViewById(R.id.set_z_password);

        set_return.setOnClickListener(this);
        set_wc.setOnClickListener(this);
        set_z_password.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //TODO 返回上一级
            case R.id.set_return:
                intent = new Intent(SetPasswordActivity.this, PersonalInformationActivity.class);
                startActivity(intent);
                finish();
                break;
            //TODO 完成
            case R.id.set_wc:

                j = set_j_password.getText().toString();
                x = set_x_password.getText().toString();
                x_again = set_x_again_password.getText().toString();

                if (x.equals(x_again)) {

                    String url = "http://39.98.173.250:8080/fangfang/app/v1/commonUpdate/updatePassword?" + "userId=" + FinalContents.getUserID() + "&oldPassword=" + j + "&newPassword=" + x_again;

                    OkHttpPost okHttpPost = new OkHttpPost(url);
                    String data = okHttpPost.post();
                    Gson gson = new Gson();
                    NickNameBean nickNameBean = gson.fromJson(data, NickNameBean.class);
                    NickNameBean.DataBean data1 = nickNameBean.getData();
                    Toast.makeText(SetPasswordActivity.this, data1.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();

                    intent = new Intent(SetPasswordActivity.this, PersonalInformationActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(this, "新密码两次不一致,请重新输入", Toast.LENGTH_SHORT).show();
                }

                break;
            //TODO 找回密码
            case R.id.set_z_password:

                break;
        }

    }
}
