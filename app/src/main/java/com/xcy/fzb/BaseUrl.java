package com.xcy.fzb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.Login.LoginActivity;

public class BaseUrl extends AppCompatActivity {
    private TextView ensure;
    private TextView cancle;
    private EditText baseUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseurl);
        init();
    }

    private void init(){
        ensure = findViewById(R.id.login_ensure);
        cancle = findViewById(R.id.login_cancle);
        baseUrl = findViewById(R.id.baseUrl);
        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setBaseUrl(baseUrl.getText().toString()+"/fangfang/app/v1/");
                Intent intent = new Intent(BaseUrl.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setBaseUrl("http://39.98.173.250:8080/fangfang/app/v1/");
                Intent intent = new Intent(BaseUrl.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
