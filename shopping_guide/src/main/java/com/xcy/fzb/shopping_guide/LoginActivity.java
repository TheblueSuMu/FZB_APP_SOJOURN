package com.xcy.fzb.shopping_guide;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.ExemplaryUserBean;
import com.xcy.fzb.shopping_guide.service.MyService;
import com.xcy.fzb.shopping_guide.view.AllActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AllActivity {
    private TextView ensure;
    private TextView cancle;
    private EditText baseUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                initData();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setBaseUrl("http://39.98.173.250:8080/fangfang/app/v1/");
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                initData();
            }
        });
    }

    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ExemplaryUserBean> userMessage = fzbInterface.getExemplaryLogin("dg2","123456","1");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ExemplaryUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExemplaryUserBean exemplaryUserBean) {
                        ExemplaryUserBean userBean = exemplaryUserBean;

                        FinalContents.setUserID(userBean.getData().getId());
                        FinalContents.setCityID(userBean.getData().getCityId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "您输入的账号或密码有误", Toast.LENGTH_SHORT).show();
                        Log.i("wsw","返回的数据"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
