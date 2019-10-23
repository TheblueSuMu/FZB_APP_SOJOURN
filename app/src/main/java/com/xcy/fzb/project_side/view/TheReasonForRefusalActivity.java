package com.xcy.fzb.project_side.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.CBean;
import com.xcy.fzb.all.persente.OkHttpPost;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.view.AllActivity;

//TODO 拒绝原因
public class TheReasonForRefusalActivity extends AllActivity {

    RelativeLayout the_reason_for_refusal_return;

    EditText the_reason_for_refusal_et;

    Button the_reason_for_refusal_btn;
    private String message;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_the_reason_for_refusal);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        the_reason_for_refusal_return = findViewById(R.id.the_reason_for_refusal_return);
        the_reason_for_refusal_et = findViewById(R.id.the_reason_for_refusal_et);
        the_reason_for_refusal_btn = findViewById(R.id.the_reason_for_refusal_btn);

        the_reason_for_refusal_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        the_reason_for_refusal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message = the_reason_for_refusal_et.getText().toString();
                url = FinalContents.getBaseUrl() + "specialUpdate/reportAndVisitAudit?preparationId=" + FinalContents.getPreparationId() + "&maxStatus=" + FinalContents.getStatus() + "&minStatus=2&userId=" + FinalContents.getUserID() + "&reason=" + message;
                OkHttpPost okHttpPost = new OkHttpPost(url);
                String data = okHttpPost.post();
                Gson gson = new Gson();
                CBean cBean = gson.fromJson(data, CBean.class);
                if (cBean.getMsg().equals("成功")) {
                    Toast.makeText(TheReasonForRefusalActivity.this, cBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TheReasonForRefusalActivity.this, CheckPendingTheProjectActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(TheReasonForRefusalActivity.this, cBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }
}
