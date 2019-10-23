package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.database.CodeBean;
import com.xcy.fzb.captain_team.persente.OkHttpPost;
import com.xcy.fzb.captain_team.persente.StatusBar;


public class ToLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView to_return;
    RelativeLayout to_login_rl;
    ImageView to_login_img_1;
    ImageView to_login_img_2;
    ImageView to_login_img_3;
    ImageView to_login_img_4;
    private AlertDialog.Builder builder;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_login);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(ToLoginActivity.this);

        to_return = findViewById(R.id.to_return);
        to_login_rl = findViewById(R.id.to_login_rl);
        to_login_img_1 = findViewById(R.id.to_login_img_1);
        to_login_img_2 = findViewById(R.id.to_login_img_2);
        to_login_img_3 = findViewById(R.id.to_login_img_3);
        to_login_img_4 = findViewById(R.id.to_login_img_4);

//        if(){
//            to_login_img_1.setVisibility(View.VISIBLE);
//            to_login_img_2.setVisibility(View.GONE);
//            to_login_img_3.setVisibility(View.VISIBLE);
//            to_login_img_4.setVisibility(View.GONE);
//        }else {
//            to_login_img_1.setVisibility(View.GONE);
//            to_login_img_2.setVisibility(View.VISIBLE);
//            to_login_img_3.setVisibility(View.GONE);
//            to_login_img_4.setVisibility(View.VISIBLE);
//        }

        to_return.setOnClickListener(this);
        to_login_rl.setOnClickListener(this);

    }

    @SuppressLint("InflateParams")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.to_return:
                finish();
                break;
            case R.id.to_login_rl:

                if (to_login_img_1.getVisibility() == View.GONE) {
//TODO 绑定
                    builder = new AlertDialog.Builder(ToLoginActivity.this);

                    View inflate = LayoutInflater.from(ToLoginActivity.this).inflate(R.layout.binding_output, null, false);
                    builder.setView(inflate);
                    final AlertDialog show = builder.show();
                    final EditText et1 = inflate.findViewById(R.id.item_binding_outpot_et1);
                    final EditText et2 = inflate.findViewById(R.id.item_binding_outpot_et2);
                    final Button item_binding_outpot_fs_tbn = inflate.findViewById(R.id.item_binding_outpot_fs_tbn);
                    Button item_binding_outpot_btn = inflate.findViewById(R.id.item_binding_outpot_btn);

                    item_binding_outpot_fs_tbn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            s = et1.getText().toString();
                            if (s.equals("")) {
                                Toast.makeText(ToLoginActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                            } else {
                                item_binding_outpot_fs_tbn.setBackgroundColor(Color.parseColor("#EEEEEE"));
                                item_binding_outpot_fs_tbn.setTextColor(Color.parseColor("#FF615454"));
                                item_binding_outpot_fs_tbn.setText("发送中");

                                String url = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/sendCode?phone=" + s;
                                OkHttpPost okHttpPost = new OkHttpPost(url);
                                String data = okHttpPost.post();
                                String substring = data.substring(9, 10);
                                if (substring.equals("1")) {
                                    CodeBean codeBean = new Gson().fromJson(data, CodeBean.class);
                                    Toast.makeText(ToLoginActivity.this, codeBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ToLoginActivity.this, "您输入的手机号有误", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                    item_binding_outpot_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String s2 = et1.getText().toString();
                            String s1 = et2.getText().toString();
                            initData(s2, s1);
                            show.dismiss();
                        }
                    });


                } else {
//TODO 解除绑定
                    builder = new AlertDialog.Builder(ToLoginActivity.this);
                    View inflate = LayoutInflater.from(this).inflate(R.layout.binding_popout, null, false);
                    builder.setView(inflate);
                    Button button = inflate.findViewById(R.id.btn);
                    final AlertDialog show = builder.show();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i("MyCL", "确定");
                            to_login_img_1.setVisibility(View.GONE);
                            to_login_img_2.setVisibility(View.VISIBLE);
                            to_login_img_3.setVisibility(View.GONE);
                            to_login_img_4.setVisibility(View.VISIBLE);
                            show.dismiss();
                        }
                    });
                }
                break;
        }

    }

    private void initData(String s, String s1) {

        String url = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/wechatBinding?" + "userPhone=" + s + "&captcha=" + s1;

        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();

        Gson gson = new Gson();

        builder = new AlertDialog.Builder(ToLoginActivity.this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.binding_succeed, null, false);
        builder.setView(inflate);
        Button button = inflate.findViewById(R.id.item_binding_btn);
        final AlertDialog show = builder.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_login_img_1.setVisibility(View.VISIBLE);
                to_login_img_2.setVisibility(View.GONE);
                to_login_img_3.setVisibility(View.VISIBLE);
                to_login_img_4.setVisibility(View.GONE);
                show.dismiss();
            }
        });

    }

}
