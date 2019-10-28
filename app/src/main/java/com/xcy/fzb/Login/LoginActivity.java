package com.xcy.fzb.Login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.PopAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.CaptainBean;
import com.xcy.fzb.all.database.ExemplaryUserBean;
import com.xcy.fzb.all.modle.CodeBean;
import com.xcy.fzb.all.modle.LoginUserBean;
import com.xcy.fzb.all.modle.UserIdentity;
import com.xcy.fzb.all.modle.UserSaveBean;
import com.xcy.fzb.all.persente.AdministrationAuthority;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.CountDownTimerUtils;
import com.xcy.fzb.all.utils.MatcherUtils;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.all.view.ForgetActivity;
import com.xcy.fzb.broker.view.Broker_MainActivity;
import com.xcy.fzb.captain_assistant.view.Captain_Assistant_MainActivity;
import com.xcy.fzb.captain_counselor.view.Captain_Counselor_MainActivity;
import com.xcy.fzb.captain_market.view.Captain_Market_MainActivity;
import com.xcy.fzb.captain_team.view.Captain_Team_MainActivity;
import com.xcy.fzb.project_attache.view.Project_Attache_MainActivity;
import com.xcy.fzb.project_side.view.Project_Side_MainActivity;
import com.xcy.fzb.shopping_guide.view.Shopping_Guide_MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AllActivity implements View.OnClickListener, PlatformActionListener {
    private TextView login_tv_username;
    private TextView login_tv_password;
    private TextView login_tv_get_code;
    private TextView login_tv_select_type;
    private TextView login_tv_login;
    private TextView login_tv_forget_password;
    private LinearLayout login_tv_wechat;
    private EditText login_et_username;
    private EditText login_et_password;
    private CheckBox login_save_user;
    private ImageView login_iv_user_state;
    private ImageView login_iv_login_type;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    private List<UserSaveBean> list = new ArrayList<>();

    private String LoginUrl = FinalContents.getBaseUrl() + "commonSelect/login?";
    private String sendUrl = FinalContents.getBaseUrl() + "commonSelect/sendCode?";

    private String type = "";

    private CheckBox checkBoxed;

    private String userName;
    private String passWord;

    public static LoginUserBean loginUserBean;//登陆接口数据
    private PopupWindow p;
    private String string;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {

        } else {
            Toast.makeText(this, "当前无网络，请检查网络后再进行登录", Toast.LENGTH_SHORT).show();
        }

        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
//            initMap();
//            Toast.makeText(LoginActivity.this, "已开启定位权限", Toast.LENGTH_LONG).show();
        }

        AdministrationAuthority administrationAuthority = new AdministrationAuthority();
        administrationAuthority.CameraPermissions(LoginActivity.this);

        FinalContents.setQuanceng("");
        FinalContents.setZhuanyuan("");
        FinalContents.setZhuanAn("1");
        FinalContents.setCityName("长春市");

        editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        pref = getSharedPreferences("data", MODE_PRIVATE);
        initfvb();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
//                    initMap();//开始定位
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(LoginActivity.this, "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    //命名区域
    private void initfvb() {
        list.clear();
        login_tv_username = findViewById(R.id.tv_tip);
        login_tv_password = findViewById(R.id.tv_password);
        login_tv_get_code = findViewById(R.id.ctv_get_code);
        login_tv_select_type = findViewById(R.id.tv_select_login_type);
        login_tv_login = findViewById(R.id.tv_user_login);

        login_tv_forget_password = findViewById(R.id.tv_forget_password);
        login_tv_wechat = findViewById(R.id.tv_wechat);

        checkBoxed = findViewById(R.id.checkboxed);

        login_et_username = findViewById(R.id.et_user_name);
        login_et_password = findViewById(R.id.et_password);
        login_save_user = findViewById(R.id.cb_is_save_user);

        login_iv_user_state = findViewById(R.id.iv_user_state);
        login_iv_login_type = findViewById(R.id.iv_login_type);

        FinalContents.setIFSP("1");

        if (pref.getString("user_name", "").equals("")) {
        } else {
            list.add(new UserSaveBean(pref.getString("user_name", ""), pref.getString("user_password", "")));
            if (pref.getString("user_name2", "").equals("")) {
            } else {
                list.add(new UserSaveBean(pref.getString("user_name2", ""), pref.getString("user_password2", "")));
                if (pref.getString("user_name3", "").equals("")) {
                } else {
                    list.add(new UserSaveBean(pref.getString("user_name3", ""), pref.getString("user_password3", "")));
                }
            }
        }

        if (FinalContents.getDengLu().equals("")) {
            initDengLu();
        } else {

        }

        click();
    }

    private void initDengLu() {
        Log.i("登录", "数据：" + pref.getString("DengLu", ""));
        if (pref.getString("denglu", "").equals("经纪人")) {
            if (pref.getString("user_name", "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Toast.makeText(this, "用户成功登录" + pref.getString("userID", ""), Toast.LENGTH_SHORT);
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Broker_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("销售")) {
            if (pref.getString("user_name", "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Toast.makeText(this, "用户成功登录" + pref.getString("userID", ""), Toast.LENGTH_SHORT);
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Captain_Market_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("顾问")) {
            if (pref.getString("user_name", "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Toast.makeText(this, "用户成功登录" + pref.getString("userID", ""), Toast.LENGTH_SHORT);
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Captain_Counselor_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("团队长")) {
            if (pref.getString("user_name", "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Toast.makeText(this, "用户成功登录" + pref.getString("userID", ""), Toast.LENGTH_SHORT);
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Captain_Team_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("团助")) {
            if (pref.getString("user_name", "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Toast.makeText(this, "用户成功登录" + pref.getString("userID", ""), Toast.LENGTH_SHORT);
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Captain_Assistant_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("专员")) {
            if (pref.getString("user_name", "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Toast.makeText(this, "用户成功登录" + pref.getString("userID", ""), Toast.LENGTH_SHORT);
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Project_Attache_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setCityName(pref.getString("cityname", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("专案")) {
            if (pref.getString("user_name", "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Toast.makeText(this, "用户成功登录" + pref.getString("userID", ""), Toast.LENGTH_SHORT);
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Project_Side_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("导购")) {
            if (pref.getString("user_name", "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Toast.makeText(this, "用户成功登录" + pref.getString("userID", ""), Toast.LENGTH_SHORT);
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Shopping_Guide_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        }
    }

    private void click() {
        login_tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        login_tv_password.setOnClickListener(this);
        login_tv_get_code.setOnClickListener(this);
        login_tv_select_type.setOnClickListener(this);
        login_tv_login.setOnClickListener(this);
        login_tv_forget_password.setOnClickListener(this);
        login_tv_wechat.setOnClickListener(this);
        login_et_username.setOnClickListener(this);
        login_et_password.setOnClickListener(this);
        login_save_user.setOnClickListener(this);
        login_iv_user_state.setOnClickListener(this);
        login_iv_login_type.setOnClickListener(this);
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select_login_type:
                login_et_password.setText("");
                login_et_username.setText("");
                if (login_tv_select_type.getText().equals("短信登录")) {
                    iii();
                    type = "2";
                } else {
                    ccc();
                    type = "1";
                }
                Log.i("数据", "登录：" + type);
                break;
            case R.id.cb_is_save_user:

                break;
            case R.id.tv_forget_password:
                Intent intent = new Intent(this, ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_user_login:
                boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
                if (networkAvailable) {
                    if (type.equals("1")) {
                        userLoginWithAccount();
                    } else if (type.equals("2")) {
                        userLoginWithCode();
                    } else if (type.equals("")) {
                        userLoginWithAccount();
                    }
                } else {
                    Toast.makeText(this, "当前无网络，请检查网络后再进行登录", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_wechat:

                Toast.makeText(LoginActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();

//                Platform plat = ShareSDK.getPlatform(Wechat.NAME);
//                plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
//                plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
//                plat.setPlatformActionListener(this);//授权回调监听，监听oncomplete，onerror，oncancel三种状态
//                if (plat.isClientValid()) {
//                    //判断是否存在授权凭条的客户端，true是有客户端，false是无
//                }
//                if (plat.isAuthValid()) {
//                    //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
//                    Toast.makeText(this, "已经授权过了", 0).show();
//                    return;
//                }
//                ShareSDK.setActivity(this);//抖音登录适配安卓9.0
//                plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
                break;
            case R.id.iv_user_state:
                PopWindow();
                break;
            case R.id.ctv_get_code:
                //点击发送验证码按钮
                sendcode();
                break;
            case R.id.iv_login_type:
                login_et_password.setText("");
                login_et_username.setText("");
                if (login_tv_select_type.getText().equals("短信登录")) {
                    iii();
                    type = "2";
                } else {
                    ccc();
                    type = "1";
                }
                Log.i("数据", "登录：" + type);
                break;
        }
    }

    /**
     * 发送验证码
     */
    @SuppressLint("ShowToast")
    private void sendcode() {
        userName = login_et_username.getText().toString();

        if (userName.equals("")) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!MatcherUtils.isMobile(userName)) {
            Log.i("aaa", "走一下");
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Log.i("aaa", "不走");
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CodeBean> userMessage = fzbInterface.getSendCode(userName);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CodeBean codeBean) {
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(login_tv_get_code, 60000, 1000);
                        mCountDownTimerUtils.start();
                        Toast.makeText(LoginActivity.this, codeBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "您输入的手机号有误", Toast.LENGTH_SHORT).show();
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 验证码登录
     */
    private void userLoginWithCode() {
        userName = login_et_username.getText().toString();
        passWord = login_et_password.getText().toString();
        if (userName.equals("")) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passWord.equals("")) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UserIdentity> userMessage = fzbInterface.getUserIdentity(userName, passWord, "2");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserIdentity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserIdentity userIdentity) {
                        if (userIdentity.getData().getIdentity().equals("1") || userIdentity.getData().getIdentity().equals("2") || userIdentity.getData().getIdentity().equals("3")) {
                            initBroker();
                        } else if (userIdentity.getData().getIdentity().equals("4") || userIdentity.getData().getIdentity().equals("5") || userIdentity.getData().getIdentity().equals("7")) {
                            initExemplary();
                        } else if (userIdentity.getData().getIdentity().equals("60") || userIdentity.getData().getIdentity().equals("61") || userIdentity.getData().getIdentity().equals("62") || userIdentity.getData().getIdentity().equals("63")) {
                            initCaptain();
                        }
                        type = "2";
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 账号登录
     */
    private void userLoginWithAccount() {
        userName = login_et_username.getText().toString();
        passWord = login_et_password.getText().toString();
        if (userName.equals("")) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passWord.equals("")) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checkBoxed.isChecked()) {
            initlogin();
        } else {
            Toast.makeText(this, "请同意服务条款后进行登录", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 账号数据请求
     */
    private void initlogin() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UserIdentity> userMessage = fzbInterface.getUserIdentity(userName, passWord, "1");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserIdentity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserIdentity userIdentity) {
                        if (userIdentity.getData().getIdentity().equals("1") || userIdentity.getData().getIdentity().equals("2") || userIdentity.getData().getIdentity().equals("3")) {
                            initBroker();
                        } else if (userIdentity.getData().getIdentity().equals("4") || userIdentity.getData().getIdentity().equals("5") || userIdentity.getData().getIdentity().equals("7") || userIdentity.getData().getIdentity().equals("63")) {
                            initExemplary();
                        } else if (userIdentity.getData().getIdentity().equals("60") || userIdentity.getData().getIdentity().equals("61") || userIdentity.getData().getIdentity().equals("62")) {
                            initCaptain();
                        }
                        type = "1";
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "请输入正确的账户或用户名", Toast.LENGTH_SHORT).show();
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initBroker() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LoginUserBean> userMessage = fzbInterface.getLogin(userName, passWord, type);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginUserBean loginUserBean1) {
                        loginUserBean = loginUserBean1;
                        if (pref.getString("user_name", "").equals("")) {
                            editor.putString("user_name", userName);
                            editor.putString("user_password", passWord);
                            editor.commit();
                        } else if (pref.getString("user_name", "").equals(userName)) {
                        } else {
                            if (pref.getString("user_name2", "").equals("")) {
                                editor.putString("user_name2", userName);
                                editor.putString("user_password2", passWord);
                                editor.commit();
                            } else if (pref.getString("user_name2", "").equals(userName)) {
                            } else {
                                if (pref.getString("user_name3", "").equals("")) {
                                    editor.putString("user_name3", userName);
                                    editor.putString("user_password3", passWord);
                                    editor.commit();
                                } else if (pref.getString("user_name3", "").equals(userName)) {
                                } else {
                                    editor.putString("user_name", userName);
                                    editor.putString("user_password", passWord);
                                    editor.commit();
                                }
                            }
                        }
                        boolean networkAvailable = CommonUtil.isNetworkAvailable(LoginActivity.this);
                        if (networkAvailable) {
                            Intent intent = new Intent(LoginActivity.this, Broker_MainActivity.class);
                            FinalContents.setUserID(loginUserBean.getData().getId());
                            FinalContents.setCityID(loginUserBean.getData().getCityId());
                            FinalContents.setIdentity(loginUserBean.getData().getIdentity());
                            editor.putString("denglu", "经纪人");
                            editor.commit();
                            Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                            editor.putString("userID", FinalContents.getUserID());
                            editor.putString("cityID", FinalContents.getCityID());
                            editor.putString("identity", FinalContents.getIdentity());
                            editor.commit();
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "成功登陆", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "当前无网络，请检查网络后再重新登录APP", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "您输入的账号或密码有误", Toast.LENGTH_SHORT).show();
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initExemplary() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ExemplaryUserBean> userMessage = fzbInterface.getExemplaryLogin(userName, passWord, type);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ExemplaryUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExemplaryUserBean exemplaryUserBean) {
                        ExemplaryUserBean userBean = exemplaryUserBean;
                        if (pref.getString("user_name", "").equals("")) {
                            editor.putString("user_name", userName);
                            editor.putString("user_password", passWord);
                            editor.commit();
                        } else if (pref.getString("user_name", "").equals(userName)) {
                        } else {
                            if (pref.getString("user_name2", "").equals("")) {
                                editor.putString("user_name2", userName);
                                editor.putString("user_password2", passWord);
                                editor.commit();
                            } else if (pref.getString("user_name2", "").equals(userName)) {
                            } else {
                                if (pref.getString("user_name3", "").equals("")) {
                                    editor.putString("user_name3", userName);
                                    editor.putString("user_password3", passWord);
                                    editor.commit();
                                } else if (pref.getString("user_name3", "").equals(userName)) {
                                } else {
                                    editor.putString("user_name", userName);
                                    editor.putString("user_password", passWord);
                                    editor.commit();
                                }
                            }
                        }
                        boolean networkAvailable = CommonUtil.isNetworkAvailable(LoginActivity.this);
                        if (networkAvailable) {
                            if (userBean.getData().getIdentity().equals("5")) {
                                //  TODO 专员端登录
                                Intent intent = new Intent(LoginActivity.this, Project_Attache_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setCityName(userBean.getData().getCityName());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                editor.putString("denglu", "专员");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("cityname", FinalContents.getCityName());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("4")) {
                                //  TODO 专案端登录
                                Intent intent = new Intent(LoginActivity.this, Project_Side_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                editor.putString("denglu", "专案");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("7")) {
                                //  TODO 导购端登录
                                Intent intent = new Intent(LoginActivity.this, Shopping_Guide_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                editor.putString("denglu", "导购");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("63")) {
                                //  TODO 圈层端登录 团助
                                Intent intent = new Intent(LoginActivity.this, Captain_Assistant_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                FinalContents.setXSName(userBean.getData().getName());
                                editor.putString("denglu", "团助");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.commit();
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "当前无网络，请检查网络后再重新登录APP", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "您输入的账号或密码有误", Toast.LENGTH_SHORT).show();
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initCaptain() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CaptainBean> userMessage = fzbInterface.getCaptainLogin(userName, passWord, type);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CaptainBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CaptainBean captainBean) {
                        CaptainBean userBean = captainBean;
                        if (pref.getString("user_name", "").equals("")) {
                            editor.putString("user_name", userName);
                            editor.putString("user_password", passWord);
                            editor.commit();
                        } else if (pref.getString("user_name", "").equals(userName)) {
                        } else {
                            if (pref.getString("user_name2", "").equals("")) {
                                editor.putString("user_name2", userName);
                                editor.putString("user_password2", passWord);
                                editor.commit();
                            } else if (pref.getString("user_name2", "").equals(userName)) {
                            } else {
                                if (pref.getString("user_name3", "").equals("")) {
                                    editor.putString("user_name3", userName);
                                    editor.putString("user_password3", passWord);
                                    editor.commit();
                                } else if (pref.getString("user_name3", "").equals(userName)) {
                                } else {
                                    editor.putString("user_name", userName);
                                    editor.putString("user_password", passWord);
                                    editor.commit();
                                }
                            }
                        }
                        boolean networkAvailable = CommonUtil.isNetworkAvailable(LoginActivity.this);
                        if (networkAvailable) {
                            if (userBean.getData().getIdentity().equals("60")) {
                                //  TODO 圈层端登录 团队长
                                Intent intent = new Intent(LoginActivity.this, Captain_Team_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                FinalContents.setUserName(userBean.getData().getName());
                                editor.putString("denglu", "团队长");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("62")) {
                                //  TODO 圈层端登录 顾问
                                Intent intent = new Intent(LoginActivity.this, Captain_Counselor_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                editor.putString("denglu", "顾问");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("61")) {
                                //  TODO 圈层端登录 销售
                                Intent intent = new Intent(LoginActivity.this, Captain_Market_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                FinalContents.setXSName(userBean.getData().getName());
                                FinalContents.setXSTeamName(userBean.getData().getLayerTeamVo().getTeamLeader().getParent().getName());
                                FinalContents.setUserName(userBean.getData().getName());
                                FinalContents.setParentName(userBean.getData().getLayerTeamVo().getTeamLeader().getParent().getName());
                                editor.putString("denglu", "销售");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.commit();
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "当前无网络，请检查网络后再重新登录APP", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "您输入的账号或密码有误", Toast.LENGTH_SHORT).show();
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void iii() {
        login_et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        login_tv_username.setText("手机号");
        login_tv_password.setText("验证码");

        login_et_password.setHint("请输入验证码");
        login_et_username.setHint("请输入手机号");

        login_tv_select_type.setText("账号登录");
        login_tv_get_code.setVisibility(View.VISIBLE);
        login_iv_login_type.setImageResource(R.mipmap.ic_login_account);
    }

    private void ccc() {
        login_et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        login_tv_username.setText("账号");
        login_tv_password.setText("密码");

        login_et_password.setHint("请输入密码");
        login_et_username.setHint("请输入账号");

        login_tv_select_type.setText("短信登录");
        login_tv_get_code.setVisibility(View.GONE);
        login_iv_login_type.setImageResource(R.mipmap.ic_login_iphone);
    }

    private void PopWindow() {
        login_iv_user_state.setImageResource(R.mipmap.ic_login_up);
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_login_user_list, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        p = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        p.setTouchable(true);
        p.setFocusable(true);
        p.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));
        p.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOff;
        int buttonWidth = login_iv_user_state.getWidth();
        int popupwindowWidth = p.getContentView().getMeasuredWidth();
        xOff = buttonWidth - popupwindowWidth;
        p.showAsDropDown(login_iv_user_state, xOff, 0);

        p.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                login_iv_user_state.setImageResource(R.mipmap.ic_login_down);
            }
        });
        Log.i("aaa", "用户名" + pref.getString("name", ""));
    }

    //获取历史账号
    private void handleListView(View contentView) {

        RecyclerView recyclerView = contentView.findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final PopAdapter popAdapter = new PopAdapter(list);
        recyclerView.setAdapter(popAdapter);

        popAdapter.setOnItemClickListener(new PopAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {
                if (FinalContents.getDelete().equals("填充")) {
                    login_et_username.setText(list.get(postion).getUserName());
                    login_et_password.setText(list.get(postion).getUserPassword());
                    p.dismiss();
                } else if (FinalContents.getDelete().equals("删除")) {
                    if (pref.getString("user_name", "").equals(list.get(postion).getUserName())) {
                        Toast.makeText(LoginActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        editor.remove("user_name");
                        editor.remove("user_password");
                    } else {
                        if (pref.getString("user_name2", "").equals(list.get(postion).getUserName())) {
                            editor.remove("user_name2");
                            editor.remove("user_password2");
                        } else {
                            if (pref.getString("user_name3", "").equals(list.get(postion).getUserName())) {
                                editor.remove("user_name3");
                                editor.remove("user_password3");
                            } else {
                                Toast.makeText(LoginActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    list.remove(postion);
                    popAdapter.notifyItemRemoved(postion);
                    popAdapter.notifyDataSetChanged();
                    if (list.size() == 0) {
                        Toast.makeText(LoginActivity.this, "存储清零", Toast.LENGTH_SHORT).show();
                        editor.clear().commit();
                        list.clear();
                    }
                }
            }
        });

    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Gson gson = new Gson();
        String fieldbeanlist = gson.toJson(hashMap);


    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(LoginActivity.this, "您已取消微信授权登录", Toast.LENGTH_SHORT).show();
    }
}
