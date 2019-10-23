package com.xcy.fzb.captain_team.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.ChangePhoneBean;
import com.xcy.fzb.captain_team.database.VerificationBean;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ChangePhoneActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView change_phone_return;
    EditText change_phone_et;
    EditText change_phone_yanzhengma;
    Button change_phone_yanzhengma_1;
    Button change_phone_yanzhengma_2;
    Button change_phone_yanzheng;
    private String phone;
    private String yanzhengma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        StatusBar.makeStatusBarTransparent(this);

        initView();

    }

    private void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        change_phone_return = findViewById(R.id.change_phone_return);
        change_phone_et = findViewById(R.id.change_phone_et);
        change_phone_yanzhengma = findViewById(R.id.change_phone_yanzhengma);
        change_phone_yanzhengma_1 = findViewById(R.id.change_phone_yanzhengma_1);
        change_phone_yanzhengma_2 = findViewById(R.id.change_phone_yanzhengma_2);
        change_phone_yanzheng = findViewById(R.id.change_phone_yanzheng);

        change_phone_yanzhengma_1.setVisibility(View.VISIBLE);
        change_phone_yanzhengma_2.setVisibility(View.GONE);

        change_phone_return.setOnClickListener(this);
        change_phone_yanzhengma_1.setOnClickListener(this);
        change_phone_yanzheng.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.change_phone_return:
                finish();
                break;
            case R.id.change_phone_yanzhengma_1:
                initData2();
                break;
            case R.id.change_phone_yanzheng:
                initData1();
                break;
        }

    }

    private void initData2() {
        phone = change_phone_et.getText().toString();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<VerificationBean> code = fzbInterface.getCode(phone);
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerificationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VerificationBean codeBean) {
                        change_phone_yanzhengma_1.setVisibility(View.GONE);
                        change_phone_yanzhengma_2.setVisibility(View.VISIBLE);
                        VerificationBean.DataBean data = codeBean.getData();
                        Toast.makeText(ChangePhoneActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "发送验证码错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initData1() {
        yanzhengma = change_phone_yanzhengma.getText().toString();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ChangePhoneBean> changePhone = fzbInterface.getChangePhone(yanzhengma, phone, FinalContents.getUserID());
        changePhone.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangePhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangePhoneBean changeBean) {
                        ChangePhoneBean.DataBean data = changeBean.getData();
                        Toast.makeText(ChangePhoneActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "更换手机号错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
