package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.CodeBean;
import com.xcy.fzb.all.modle.WechatBindingBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.CountDownTimerUtils;

import org.json.JSONObject;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ToLoginActivity extends AllActivity implements View.OnClickListener{

    private RelativeLayout to_return;
    RelativeLayout to_login_rl;
    ImageView to_login_img_1;
    ImageView to_login_img_2;
    ImageView to_login_img_3;
    ImageView to_login_img_4;
    private String s;
    private boolean login = false;
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_login);
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

        StatusBar.makeStatusBarTransparent(ToLoginActivity.this);

        to_return = findViewById(R.id.to_return);
        to_login_rl = findViewById(R.id.to_login_rl);
        to_login_img_1 = findViewById(R.id.to_login_img_1);
        to_login_img_2 = findViewById(R.id.to_login_img_2);
        to_login_img_3 = findViewById(R.id.to_login_img_3);
        to_login_img_4 = findViewById(R.id.to_login_img_4);

        to_return.setOnClickListener(this);
        to_login_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (to_login_img_1.getVisibility() == View.GONE) {
//TODO 绑定
                    Platform plat = ShareSDK.getPlatform(Wechat.NAME);
                    plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
                    plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
                    plat.setPlatformActionListener(new PlatformActionListener() {
                        @Override
                        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                            login = true;
                            Log.i("json","授权成功");
                            json = new JSONObject(hashMap);
                            Log.i("json","授权成功"+ json.getClass());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //此时已在主线程中，更新UI
                                    onResume();
                                }
                            });
                        }

                        @Override
                        public void onError(Platform platform, int i, Throwable throwable) {
                            Toast.makeText(ToLoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
                            Log.i("json","授权失败"+throwable.getMessage());
                        }

                        @Override
                        public void onCancel(Platform platform, int i) {
                            Log.i("json","授权取消");
                            Toast.makeText(ToLoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(ToLoginActivity.this);
                            View inflate = LayoutInflater.from(ToLoginActivity.this).inflate(R.layout.binding_popout, null, false);
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
                    });//授权回调监听，监听oncomplete，onerror，oncancel三种状态


                    if (plat.isClientValid()) {
                        //判断是否存在授权凭条的客户端，true是有客户端，false是无
                    }
                    if (plat.isAuthValid()) {
                        //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
                        Toast.makeText(ToLoginActivity.this, "已经授权过了", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(ToLoginActivity.this, "正在授权", Toast.LENGTH_SHORT).show();
                    }
                    ShareSDK.setActivity(ToLoginActivity.this);//抖音登录适配安卓9.0
                    plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
                } else {
//TODO 解除绑定
                    AlertDialog.Builder builder = new AlertDialog.Builder(ToLoginActivity.this);
                    View inflate = LayoutInflater.from(ToLoginActivity.this).inflate(R.layout.binding_popout, null, false);
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
            }
        });
    }

    @SuppressLint("InflateParams")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.to_return:
                finish();
                break;
        }

    }

    private void initL(){
        Log.i("json","加载运行");
//        AlertDialog.Builder builder = new AlertDialog.Builder(ToLoginActivity.this);
//        builder.setTitle("退出完成");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Log.i("json","授权成功获取到数据");
//            }
//        });
//        builder.show();
        Log.i("json","成功获取到数据");
//                            AlertDialog.Builder builder = new AlertDialog.Builder(ToLoginActivity.this);
//                            View inflate = LayoutInflater.from(ToLoginActivity.this).inflate(R.layout.binding_succeed, null, false);
//                            builder.setView(inflate);
//                            Button button = inflate.findViewById(R.id.item_binding_btn);
//                            final AlertDialog show = builder.show();
//                            button.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    to_login_img_1.setVisibility(View.VISIBLE);
//                                    to_login_img_2.setVisibility(View.GONE);
//                                    to_login_img_3.setVisibility(View.VISIBLE);
//                                    to_login_img_4.setVisibility(View.GONE);
//                                    show.dismiss();
//                                }
//                            });
        AlertDialog.Builder builder = new AlertDialog.Builder(ToLoginActivity.this);

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
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);
                    Observable<CodeBean> userMessage = fzbInterface.getSendCode(s);
                    userMessage.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<CodeBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(CodeBean codeBean) {
                                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(item_binding_outpot_fs_tbn, 60000, 1000);
                                    mCountDownTimerUtils.start();
                                    Toast.makeText(ToLoginActivity.this, codeBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(ToLoginActivity.this, "您输入的手机号有误", Toast.LENGTH_SHORT).show();
                                    Log.i("wsw", "返回的数据" + e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }
        });
        item_binding_outpot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s2 = et1.getText().toString();
                String s1 = et2.getText().toString();
                initData(s2, s1,"1");
                show.dismiss();
            }
        });

        login = false;
    }

    private void initData(String s, String s1, String s2) {
        Retrofit.Builder builder1 = new Retrofit.Builder();
        builder1.baseUrl(FinalContents.getBaseUrl());
        builder1.addConverterFactory(GsonConverterFactory.create());
        builder1.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder1.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<WechatBindingBean> userMessage = fzbInterface.getWechatBinding(s+"",s1+"",s2+"",json.toString());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WechatBindingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WechatBindingBean wechatBindingBean) {
                        if (wechatBindingBean.getData().getMessage().equals("绑定成功")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ToLoginActivity.this);
                            View inflate = LayoutInflater.from(ToLoginActivity.this).inflate(R.layout.binding_succeed, null, false);
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

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ToLoginActivity.this, "您输入的手机号或验证码有误", Toast.LENGTH_SHORT).show();
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


//        AlertDialog.Builder builder = new AlertDialog.Builder(ToLoginActivity.this);
//        View inflate = LayoutInflater.from(this).inflate(R.layout.binding_succeed, null, false);
//        builder.setView(inflate);
//        Button button = inflate.findViewById(R.id.item_binding_btn);
//        final AlertDialog show = builder.show();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                to_login_img_1.setVisibility(View.VISIBLE);
//                to_login_img_2.setVisibility(View.GONE);
//                to_login_img_3.setVisibility(View.VISIBLE);
//                to_login_img_4.setVisibility(View.GONE);
//                show.dismiss();
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (login) {
            Log.i("json","进入数据加载");
            initL();
        }else {
            Log.i("json","不进入数据加载");
        }
    }
}
