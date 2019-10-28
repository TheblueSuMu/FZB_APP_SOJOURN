package com.xcy.fzb.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.ChangePhoneBean;
import com.xcy.fzb.all.modle.VerificationBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.CountDownTimerUtils;
import com.xcy.fzb.all.utils.MatcherUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ChangePhoneActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout change_phone_return;
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
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            Toast.makeText(this, "当前无网络，请检查网络后再进行登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

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
                if (!MatcherUtils.isMobile(change_phone_et.getText().toString())) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    initData2();
                }
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
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(change_phone_yanzhengma_1, 60000, 1000);
                        mCountDownTimerUtils.start();
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
        if (yanzhengma.equals("")) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
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
                        if (data.getMessage().equals("修改成功")) {
                            finish();
                        }
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
