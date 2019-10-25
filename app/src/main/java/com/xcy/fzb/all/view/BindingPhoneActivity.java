package com.xcy.fzb.all.view;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.ChangePhoneBean;
import com.xcy.fzb.all.modle.VerificationBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CountDownTimerUtils;
import com.xcy.fzb.all.utils.MatcherUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BindingPhoneActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout binding_return;
    EditText binding_phone;
    EditText binding_et;
    Button binding_btn_1;
    Button binding_btn_2;
    Button binding_btn;
    private String phone;
    private String yanzhengma;
    private View inflate;
    private Button item_binding_btn;
    private PopupWindow mHeadPopupclly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_phone);


        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);


        binding_return = findViewById(R.id.binding_return);
        binding_phone = findViewById(R.id.binding_phone);
        binding_et = findViewById(R.id.binding_et);
        binding_btn_1 = findViewById(R.id.binding_btn_1);
        binding_btn_2 = findViewById(R.id.binding_btn_2);
        binding_btn = findViewById(R.id.binding_btn);

        binding_btn_1.setVisibility(View.VISIBLE);
        binding_btn_2.setVisibility(View.GONE);

        binding_return.setOnClickListener(this);
        binding_btn_1.setOnClickListener(this);
        binding_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.binding_return:
                finish();
                break;
            case R.id.binding_btn_1:
                if (!MatcherUtils.isMobile(binding_phone.getText().toString())) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    initData1();
                }
                break;
            case R.id.binding_btn:
                initData2();
                break;

        }

    }

    private void initData2() {

        yanzhengma = binding_et.getText().toString();
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
                        inflate = View.inflate(BindingPhoneActivity.this, R.layout.binding_succeed, null);
                        item_binding_btn = inflate.findViewById(R.id.item_binding_btn);
                        mHeadPopupclly = new PopupWindow(inflate, AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT, true);
                        mHeadPopupclly.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                        mHeadPopupclly.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                        mHeadPopupclly.setBackgroundDrawable(new BitmapDrawable());
                        mHeadPopupclly.setOutsideTouchable(true);
                        mHeadPopupclly.showAsDropDown(binding_btn_2, 0, 0);
                        item_binding_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
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

    private void initData1() {

        phone = binding_phone.getText().toString();
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
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(binding_btn_1, 60000, 1000);
                        mCountDownTimerUtils.start();
                        VerificationBean.DataBean data = codeBean.getData();
                        Toast.makeText(BindingPhoneActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
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
}
