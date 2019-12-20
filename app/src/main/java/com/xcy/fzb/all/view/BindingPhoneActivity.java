package com.xcy.fzb.all.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.Connector;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.modle.ChangePhoneBean;
import com.xcy.fzb.all.modle.GWDataBean;
import com.xcy.fzb.all.modle.UserBean;
import com.xcy.fzb.all.modle.UserMessageBean;
import com.xcy.fzb.all.modle.VerificationBean;
import com.xcy.fzb.all.modle.ZYDataBean;
import com.xcy.fzb.all.modle.ZhangBingDataBean;
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

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(BindingPhoneActivity.this);
                        inflate = View.inflate(BindingPhoneActivity.this, R.layout.binding_succeed, null);
                        builder1.setView(inflate);
                        item_binding_btn = inflate.findViewById(R.id.item_binding_btn);
                        if (FinalContents.getIdentity().equals("1") || FinalContents.getIdentity().equals("2") || FinalContents.getIdentity().equals("3")) {
                            UserMessageBean userMessageBean = Connector.getUserMessageBean();
                            UserMessageBean.DataBean dataBean = userMessageBean.getData();
                            dataBean.setPhone(binding_phone.getText().toString());
                            userMessageBean.setData(dataBean);
                            Connector.setUserMessageBean(userMessageBean);
                            NewlyIncreased.setUserMessage("123");
                        } else if (FinalContents.getIdentity().equals("4")  || FinalContents.getIdentity().equals("7")) {
                            UserBean userMessageBean = Connector.getUserBean();
                            UserBean.DataBean dataBean = userMessageBean.getData();
                            dataBean.setPhone(binding_phone.getText().toString());
                            userMessageBean.setData(dataBean);
                            Connector.setUserBean(userMessageBean);
                            NewlyIncreased.setUserMessage("7");
                        } else if (FinalContents.getIdentity().equals("5")) {
                            ZYDataBean userMessageBean = Connector.getZyDataBean();
                            ZYDataBean.DataBean dataBean = userMessageBean.getData();
                            dataBean.setPhone(binding_phone.getText().toString());
                            userMessageBean.setData(dataBean);
                            Connector.setZyDataBean(userMessageBean);
                            NewlyIncreased.setUserMessage("5");
                        } else if (FinalContents.getIdentity().equals("60")) {
                            ZhangBingDataBean userMessageBean = Connector.getZhangBingDataBean();
                            ZhangBingDataBean.DataBean dataBean = userMessageBean.getData();
                            ZhangBingDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                            sysUserBean.setPhone(binding_phone.getText().toString());
                            dataBean.setSysUser(sysUserBean);
                            userMessageBean.setData(dataBean);
                            Connector.setZhangBingDataBean(userMessageBean);
                            NewlyIncreased.setUserMessage("60");
                        } else if (FinalContents.getIdentity().equals("61")) {
                            GWDataBean userMessageBean = Connector.getGwDataBean();
                            GWDataBean.DataBean dataBean = userMessageBean.getData();
                            GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                            sysUserBean.setPhone(binding_phone.getText().toString());
                            dataBean.setSysUser(sysUserBean);
                            userMessageBean.setData(dataBean);
                            Connector.setGwDataBean(userMessageBean);
                            NewlyIncreased.setUserMessage("61");
                        } else if (FinalContents.getIdentity().equals("62")) {
                            GWDataBean userMessageBean = Connector.getGwDataBean();
                            GWDataBean.DataBean dataBean = userMessageBean.getData();
                            GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                            sysUserBean.setPhone(binding_phone.getText().toString());
                            dataBean.setSysUser(sysUserBean);
                            userMessageBean.setData(dataBean);
                            Connector.setGwDataBean(userMessageBean);
                            NewlyIncreased.setUserMessage("61");
                        } else if (FinalContents.getIdentity().equals("63")) {
                            UserBean userMessageBean = Connector.getUserBean();
                            UserBean.DataBean dataBean = userMessageBean.getData();
                            dataBean.setPhone(binding_phone.getText().toString());
                            userMessageBean.setData(dataBean);
                            Connector.setUserBean(userMessageBean);
                            NewlyIncreased.setUserMessage("63");
                        }
//                        mHeadPopupclly = new PopupWindow(inflate, AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT, true);
//                        mHeadPopupclly.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//                        mHeadPopupclly.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//                        mHeadPopupclly.setBackgroundDrawable(new BitmapDrawable());
//                        mHeadPopupclly.setOutsideTouchable(true);
//                        mHeadPopupclly.showAsDropDown(binding_btn_2, 0, 0);
                        final AlertDialog alertDialog = builder1.create();
                        alertDialog.show();
                        item_binding_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                                alertDialog.dismiss();
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
