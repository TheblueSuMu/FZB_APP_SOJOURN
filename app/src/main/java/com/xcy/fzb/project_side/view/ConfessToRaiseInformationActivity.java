package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.ProjectProgressApi;
import com.xcy.fzb.all.modle.ConfessBean;
import com.xcy.fzb.all.persente.SingleClick;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.MatcherUtils;
import com.xcy.fzb.all.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 填写认筹信息
public class ConfessToRaiseInformationActivity extends AppCompatActivity implements View.OnClickListener {

    TextView confess_to_raise_information_tv1;
    TextView confess_to_raise_information_tv2;
    TextView confess_to_raise_information_tv3;
    TextView confess_to_raise_information_tv4;
    TextView confess_to_raise_information_tv5;
    TextView confess_to_raise_information_tv6;

    EditText confess_to_raise_information_et1;
    EditText confess_to_raise_information_et2;
    EditText confess_to_raise_information_et3;
    EditText confess_to_raise_information_et4;
    EditText confess_to_raise_information_et5;

    RadioGroup confess_to_raise_information_rg;

    RadioButton confess_to_raise_information_rb1;
    RadioButton confess_to_raise_information_rb2;

    RelativeLayout confess_to_raise_information_rl1;
    RelativeLayout confess_to_raise_information_rl2;
    RelativeLayout confess_to_raise_information_rl3;

    Button confess_to_raise_information_btn;

    RelativeLayout confess_to_raise_information_return;
    private String projectName;
    private String name;
    private String phone;
    private List<String> list;
    private OptionsPickerView pvOptions;
    private String sex = "";
    private String s;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private String s7;
    private String url;
    private String s8;

    private String beforeDate;

    int ifnum1 = 0;
    int ifnum2 = 0;
    int ifnum3 = 0;

    int isnum1 = 0;
    private int year;
    private int month;
    private int dayOfMonth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_confess_to_raise_information);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
        //设置透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.parseColor("#ff546da6"));
        init_No_Network();
    }

    private void init_No_Network() {
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
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {

        confess_to_raise_information_tv1 = findViewById(R.id.confess_to_raise_information_tv1);
        confess_to_raise_information_tv2 = findViewById(R.id.confess_to_raise_information_tv2);
        confess_to_raise_information_tv3 = findViewById(R.id.confess_to_raise_information_tv3);
        confess_to_raise_information_tv4 = findViewById(R.id.confess_to_raise_information_tv4);
        confess_to_raise_information_tv5 = findViewById(R.id.confess_to_raise_information_tv5);
        confess_to_raise_information_tv6 = findViewById(R.id.confess_to_raise_information_tv6);

        confess_to_raise_information_et1 = findViewById(R.id.confess_to_raise_information_et1);
        confess_to_raise_information_et2 = findViewById(R.id.confess_to_raise_information_et2);
        confess_to_raise_information_et3 = findViewById(R.id.confess_to_raise_information_et3);
        confess_to_raise_information_et4 = findViewById(R.id.confess_to_raise_information_et4);
        confess_to_raise_information_et5 = findViewById(R.id.confess_to_raise_information_et5);

        confess_to_raise_information_rg = findViewById(R.id.confess_to_raise_information_rg);

        confess_to_raise_information_rb1 = findViewById(R.id.confess_to_raise_information_rb1);
        confess_to_raise_information_rb2 = findViewById(R.id.confess_to_raise_information_rb2);

        confess_to_raise_information_rl1 = findViewById(R.id.confess_to_raise_information_rl1);
        confess_to_raise_information_rl2 = findViewById(R.id.confess_to_raise_information_rl2);
        confess_to_raise_information_rl3 = findViewById(R.id.confess_to_raise_information_rl3);

        confess_to_raise_information_btn = findViewById(R.id.confess_to_raise_information_btn);

        confess_to_raise_information_return = findViewById(R.id.confess_to_raise_information_return);

        confess_to_raise_information_tv1.setText(ProjectProgressApi.getCustomerName());
        confess_to_raise_information_tv2.setText(ProjectProgressApi.getCustomerPhone());
        confess_to_raise_information_tv3.setText(ProjectProgressApi.getProjectName());


        confess_to_raise_information_return.setOnClickListener(this);
        confess_to_raise_information_btn.setOnClickListener(this);
        confess_to_raise_information_rl1.setOnClickListener(this);
        confess_to_raise_information_rl2.setOnClickListener(this);
        confess_to_raise_information_rl3.setOnClickListener(this);

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.confess_to_raise_information_return:
                finish();
                break;
            //            TODO 报备客户与认筹客户关系
            case R.id.confess_to_raise_information_rl1:
                hideInput();
                if (ifnum1 == 0) {
                    ifnum1 = 1;
                    list = new ArrayList<>();
                    list.add("本人");
                    list.add("父母");
                    list.add("子女");
                    list.add("配偶");
                    list.add("其他");
                    pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            //               返回的分别是三个级别的选中位置
                            //              展示选中数据
                            confess_to_raise_information_tv4.setText(list.get(options1));
                        }
                    })
                            .setSelectOptions(0)//设置选择第一个
                            .setOutSideCancelable(false)//点击背的地方不消失
                            .build();//创建
                    //      把数据绑定到控件上面
                    pvOptions.setPicker(list);
                    //      展示
                    pvOptions.show();
                    ifnum1 = 0;
                }

                break;
            //            TODO 意向户型
            case R.id.confess_to_raise_information_rl2:
                hideInput();
                if (ifnum2 == 0) {
                    ifnum2 = 1;
                    list = new ArrayList<>();
                    list.add("一室");
                    list.add("二室");
                    list.add("三室");
                    list.add("四室");
                    list.add("五室");
                    //创建
                    pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            //               返回的分别是三个级别的选中位置
                            //              展示选中数据
                            confess_to_raise_information_tv5.setText(list.get(options1));
                        }
                    })
                            .setSelectOptions(0)//设置选择第一个
                            .setOutSideCancelable(false)//点击背的地方不消失
                            .build();
                    //      把数据绑定到控件上面
                    pvOptions.setPicker(list);
                    //      展示
                    pvOptions.show();
                    ifnum2 = 0;
                }


                break;
            //            TODO 认筹时间
            case R.id.confess_to_raise_information_rl3:
                hideInput();
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month + 1, dayOfMonth);
                Calendar selectedDate = Calendar.getInstance();//系统当前时间
                Calendar startDate = Calendar.getInstance();
                startDate.set(year-2, month, dayOfMonth);
                final Calendar endDate = Calendar.getInstance();
                endDate.set(year, month, dayOfMonth);
                TimePickerView pvTime = new TimePickerBuilder(ConfessToRaiseInformationActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        confess_to_raise_information_tv6.setText(getTime1(date));
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                        .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                        .isCenterLabel(false)
                        .setDate(selectedDate)
                        .setLineSpacingMultiplier(1.5f)
                        .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                        .setRangDate(startDate, endDate)
                        .build();
                pvTime.show();
                break;
            //            TODO 提交
            case R.id.confess_to_raise_information_btn:

                s = confess_to_raise_information_et1.getText().toString();
                s1 = confess_to_raise_information_et2.getText().toString();
                s2 = confess_to_raise_information_et3.getText().toString();
                s3 = confess_to_raise_information_et4.getText().toString();
                s4 = confess_to_raise_information_et5.getText().toString();

                s5 = confess_to_raise_information_tv4.getText().toString();
                s6 = confess_to_raise_information_tv5.getText().toString();
                s7 = confess_to_raise_information_tv6.getText().toString();

                if (!MatcherUtils.isMobile(confess_to_raise_information_et2.getText().toString())) {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                    return;
                }

                if (s6.equals("一室")) {
                    s8 = "1";
                } else if (s6.equals("二室")) {
                    s8 = "2";
                } else if (s6.equals("三室")) {
                    s8 = "3";
                } else if (s6.equals("四室")) {
                    s8 = "4";
                } else if (s6.equals("五室")) {
                    s8 = "5";
                }

                if (confess_to_raise_information_rb1.isChecked()) {
                    sex = "男";
                } else if (confess_to_raise_information_rb2.isChecked()) {
                    sex = "女";
                }

                if (confess_to_raise_information_et1.getText().toString().equals("")) {
                    ToastUtil.showToast(this, "请输入认筹客户姓名");
                    return;
                }

                if (sex.equals("")) {
                    ToastUtil.showToast(this, "请选择性别");
                    return;
                }

                if (confess_to_raise_information_et2.getText().toString().equals("")) {
                    ToastUtil.showToast(this, "请输入认筹客户电话");
                    return;
                }

                if (confess_to_raise_information_et3.getText().toString().equals("")) {
                    ToastUtil.showToast(this, "请输入认筹客户身份证");
                    return;
                }

                if (confess_to_raise_information_tv4.getText().toString().equals("")) {
                    ToastUtil.showToast(this, "请选择报备客户与认筹客户关系");
                    return;
                }

                if (confess_to_raise_information_et4.getText().toString().equals("")) {
                    ToastUtil.showToast(this, "请输入意向楼栋");
                    return;
                }

                if (confess_to_raise_information_tv5.getText().toString().equals("")) {
                    ToastUtil.showToast(this, "请选择意向户型");
                    return;
                }

                if (confess_to_raise_information_et5.getText().toString().equals("")) {
                    ToastUtil.showToast(this, "请输入意向面积");
                    return;
                }

                if (confess_to_raise_information_tv6.getText().toString().equals("")) {
                    ToastUtil.showToast(this, "请选择认筹时间");
                    return;
                }
                if (isnum1 == 0) {
                    isnum1 = 1;
                    init();
                }

                break;
        }

    }

    private void init() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ConfessBean> userMessage = fzbInterface.getEarnestMoneySave("", FinalContents.getPreparationId(), FinalContents.getCustomerID(), FinalContents.getProjectID(), s, s1, s2, s3, s8, s4, s7+"", s5, sex, FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConfessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ConfessBean confessBean) {
                        if (confessBean.getMsg().equals("成功")) {
                            ToastUtil.showToast(ConfessToRaiseInformationActivity.this, confessBean.getData().getMessage());
                            FinalContents.setTiaozhuang("认筹成功");
                            finish();
                            isnum1 = 0;
                        } else {
                            ToastUtil.showToast(ConfessToRaiseInformationActivity.this, confessBean.getData().getMessage());
                            isnum1 = 0;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("认筹信息", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private boolean hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            return im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return false;
    }

    public String getTime1(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }
}
