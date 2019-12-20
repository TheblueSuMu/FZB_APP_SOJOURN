package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.CityContents;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.BrokerBean;
import com.xcy.fzb.all.modle.DictListBean;
import com.xcy.fzb.all.modle.OneKeyTradeBean;
import com.xcy.fzb.all.modle.PhoneByUserBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.persente.SingleClick;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.GetInt;
import com.xcy.fzb.all.utils.KeyUtils;
import com.xcy.fzb.all.utils.MatcherUtils;
import com.xcy.fzb.all.utils.MoneyValueFilter;
import com.xcy.fzb.all.utils.ToastUtil;
import com.xcy.fzb.project_side.adapter.TimeRangeAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;

public class OneKeyActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout fill_in_transaction_information_return;
    Button fill_in_transaction_information_btn;
    RadioGroup fill_in_transaction_information_rg;
    RadioButton fill_in_transaction_information_rb1;
    RadioButton fill_in_transaction_information_rb2;

    TextView fill_in_transaction_information_title;

    TextView project_type;
    TextView project_relation;
    TextView house_type;
    TextView payment_way;
    TextView project_time;
    TextView project_brokerage;

    EditText fang_hao_et1;
    EditText fang_hao_et2;
    EditText fang_hao_et3;


    RelativeLayout fill_in_transaction_information_rl1;
    RelativeLayout fill_in_transaction_information_rl2;
    RelativeLayout fill_in_transaction_information_rl3;
    RelativeLayout fill_in_transaction_information_rl4;
    RelativeLayout fill_in_transaction_information_rl5;
    RelativeLayout fill_in_transaction_information_rl6;

    String type = "";
    private DateTimePickerView dateTimePickerView;

    RelativeLayout transition_layout;
    RecyclerView transition_recycler;

    LinearLayout picker;
    TextView picker_cancel;
    TextView picker_ensure;

    boolean whether = true;
    private String apartment = "";  //  TODO    户型
    private EditText fill_in_transaction_information_et1;
    private EditText fill_in_transaction_information_et2;
    private TextView fill_in_transaction_information_et6;
    private EditText fill_in_transaction_information_et5;
    private EditText fill_in_transaction_information_et4;
    private EditText fill_in_transaction_information_et3;
    private String gender = "";     //  TODO    性别
    private String sum = "";
    private String projecttype;
    private TextView fill_in_transaction_information_tishi;
    private boolean whethe = false;
    private String str = "";

    int ifnum1 = 0;
    int ifnum2 = 0;
    int ifnum3 = 0;
    int ifnum4 = 0;
    int ifnum5 = 0;

    int ifnum6 = 0;
    private RelativeLayout one_key_relative_1;
    private TextView one_key_relative_et3;
    private EditText one_key_relative_et1;
    private EditText one_key_relative_et2;
    private EditText one_key_et;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_one_key);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.parseColor("#ff546da6"));
        CityContents.setOneKey("一键成交");
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.one_key_no_network);
            Button all_no_reload = findViewById(R.id.one_key_no_reload);

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

    private void initfvb(){
        one_key_relative_1 = findViewById(R.id.one_key_relative_1);
        one_key_relative_et3 = findViewById(R.id.one_key_relative_et3);

        one_key_relative_et1 = findViewById(R.id.one_key_relative_et1);
        one_key_relative_et2 = findViewById(R.id.one_key_relative_et2);
        one_key_et = findViewById(R.id.one_key_et);

        fill_in_transaction_information_return = findViewById(R.id.one_key_return);
        fill_in_transaction_information_btn = findViewById(R.id.one_key_btn);
        fill_in_transaction_information_rg = findViewById(R.id.fill_in_transaction_information_rg);
        fill_in_transaction_information_rb1 = findViewById(R.id.fill_in_transaction_information_rb1);
        fill_in_transaction_information_rb2 = findViewById(R.id.fill_in_transaction_information_rb2);

        fill_in_transaction_information_title = findViewById(R.id.one_key_title);

        dateTimePickerView = findViewById(R.id.pickerView);
        picker = findViewById(R.id.picker);
        picker_cancel = findViewById(R.id.picker_cancel);
        picker_ensure = findViewById(R.id.picker_ensure);

        project_type = findViewById(R.id.project_type);
        project_relation = findViewById(R.id.project_relation);
        house_type = findViewById(R.id.house_type);
        payment_way = findViewById(R.id.payment_way);
        project_time = findViewById(R.id.project_time);
        project_brokerage = findViewById(R.id.project_brokerage);

        transition_layout = findViewById(R.id.transition_layout);
        transition_recycler = findViewById(R.id.transition_recycler);
        transition_layout.setOnClickListener(this);

        fill_in_transaction_information_rl1 = findViewById(R.id.fill_in_transaction_information_rl1);
        fill_in_transaction_information_rl2 = findViewById(R.id.fill_in_transaction_information_rl2);
        fill_in_transaction_information_rl3 = findViewById(R.id.fill_in_transaction_information_rl3);
        fill_in_transaction_information_rl4 = findViewById(R.id.fill_in_transaction_information_rl4);
        fill_in_transaction_information_rl5 = findViewById(R.id.fill_in_transaction_information_rl5);
        fill_in_transaction_information_rl6 = findViewById(R.id.fill_in_transaction_information_rl6);

        fill_in_transaction_information_et1 = findViewById(R.id.fill_in_transaction_information_et1);
        fill_in_transaction_information_et2 = findViewById(R.id.fill_in_transaction_information_et2);
        fill_in_transaction_information_et3 = findViewById(R.id.fill_in_transaction_information_et3);
        fill_in_transaction_information_et4 = findViewById(R.id.fill_in_transaction_information_et4);
        fill_in_transaction_information_et5 = findViewById(R.id.fill_in_transaction_information_et5);
        fill_in_transaction_information_et6 = findViewById(R.id.fill_in_transaction_information_et6);

        fill_in_transaction_information_tishi = findViewById(R.id.fill_in_transaction_information_tishi);

        fang_hao_et3 = findViewById(R.id.fang_hao_et3);
        fang_hao_et2 = findViewById(R.id.fang_hao_et2);
        fang_hao_et1 = findViewById(R.id.fang_hao_et1);

        //默认两位小数
        fill_in_transaction_information_et4.setFilters(new InputFilter[]{new MoneyValueFilter()});
        fill_in_transaction_information_et5.setFilters(new InputFilter[]{new MoneyValueFilter()});

        one_key_relative_1.setOnClickListener(this);
        one_key_relative_et3.setOnClickListener(this);
        fill_in_transaction_information_return.setOnClickListener(this);
        fill_in_transaction_information_btn.setOnClickListener(this);
        fill_in_transaction_information_rl1.setOnClickListener(this);
        fill_in_transaction_information_rl2.setOnClickListener(this);
        fill_in_transaction_information_rl3.setOnClickListener(this);
        fill_in_transaction_information_rl4.setOnClickListener(this);
        fill_in_transaction_information_rl5.setOnClickListener(this);
        fill_in_transaction_information_rl6.setOnClickListener(this);

        fill_in_transaction_information_et5.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    KeyUtils.hideKeyboard(fill_in_transaction_information_et5);
                    String s1 = fill_in_transaction_information_et4.getText().toString();
                    String s2 = fill_in_transaction_information_et5.getText().toString();
                    if (s1.equals("") || s2.equals("")) {
                    } else {
                        double area = Double.parseDouble(s1);
                        double price = Double.parseDouble(s2);
                        sum = GetInt.getInt((area * price));
                        str = sum;
                        fill_in_transaction_information_et6.setText(str + "元");
                        fill_in_transaction_information_tishi.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });


        fill_in_transaction_information_et4.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (fill_in_transaction_information_et5.getText().toString().equals("")) {
                } else {
                    //当actionId == XX_SEND 或者 XX_DONE时都触发
                    //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                    //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                        //处理事件
                        KeyUtils.hideKeyboard(fill_in_transaction_information_et5);
                        String s1 = fill_in_transaction_information_et4.getText().toString();
                        String s2 = fill_in_transaction_information_et5.getText().toString();
                        if (s1.equals("") || s2.equals("")) {
                        } else {
                            double area = Double.parseDouble(s1);
                            double price = Double.parseDouble(s2);
                            sum = GetInt.getInt((area * price));
                            str = sum;
                            fill_in_transaction_information_et6.setText(str + "元");
                            fill_in_transaction_information_tishi.setVisibility(View.GONE);
                            return true;
                        }
                    }
                }
                return false;
            }
        });



        if (fill_in_transaction_information_et6.getText().toString().equals("")) {
        } else {
            fill_in_transaction_information_tishi.setVisibility(View.GONE);
        }

        fill_in_transaction_information_et4.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 获得焦点
                } else {
                    // 失去焦点
                    hideInput();
                    String s1 = fill_in_transaction_information_et4.getText().toString();
                    String s2 = fill_in_transaction_information_et5.getText().toString();
                    if (s1.equals("") || s2.equals("")) {
                    } else {
                        double area = Double.parseDouble(s1);
                        double price = Double.parseDouble(s2);
                        sum = GetInt.getInt((area * price));
                        str = sum;
                        fill_in_transaction_information_et6.setText(str + "元");
                        fill_in_transaction_information_tishi.setVisibility(View.GONE);
                    }
                }
            }


        });

        fill_in_transaction_information_et5.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    // 获得焦点
                } else {
                    // 失去焦点
                    hideInput();
                    String s1 = fill_in_transaction_information_et4.getText().toString();
                    String s2 = fill_in_transaction_information_et5.getText().toString();
                    if (s1.equals("") || s2.equals("")) {
                    } else {
                        double area = Double.parseDouble(s1);
                        double price = Double.parseDouble(s2);
                        sum = GetInt.getInt((area * price));
                        str = sum;
                        fill_in_transaction_information_et6.setText(str + "元");
                        fill_in_transaction_information_tishi.setVisibility(View.GONE);
                    }
                }
            }


        });
    }

    //  TODO    选择变换
    private void initselect() {
        if (house_type.getText().toString().equals("不限")) {
            apartment = "0";
        } else if (house_type.getText().toString().equals("一室")) {
            apartment = "1";
        } else if (house_type.getText().toString().equals("二室")) {
            apartment = "2";
        } else if (house_type.getText().toString().equals("三室")) {
            apartment = "3";
        } else if (house_type.getText().toString().equals("四室")) {
            apartment = "4";
        } else if (house_type.getText().toString().equals("五室")) {
            apartment = "5";
        } else if (house_type.getText().toString().equals("五室以上")) {
            apartment = "6";
        }

        if (fill_in_transaction_information_rb1.isChecked()) {
            gender = "男";
        } else if (fill_in_transaction_information_rb2.isChecked()) {
            gender = "女";
        }
        if (project_type.getText().toString().equals("住宅")) {
            projecttype = "1";
        } else if (project_type.getText().toString().equals("公寓")) {
            projecttype = "2";
        } else if (project_type.getText().toString().equals("写字间")) {
            projecttype = "3";
        } else if (project_type.getText().toString().equals("商铺")) {
            projecttype = "4";
        } else if (project_type.getText().toString().equals("别墅")) {
            projecttype = "5";
        }
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.one_key_return:
                finish();
                break;
            //            TODO 提交
            case R.id.one_key_btn:
                if (one_key_relative_et1.getText().toString().equals("")){
                    ToastUtil.showToast(this, "请输入经纪人姓名");
                    return;
                }

                if (!MatcherUtils.isMobile(one_key_relative_et2.getText().toString())){
                    ToastUtil.showToast(this, "请输入正确的手机号");
                    return;
                }

                if (!MatcherUtils.isMobile(fill_in_transaction_information_et2.getText().toString())) {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                    return;
                } else {

                    if (project_type.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择产品类型");
                        return;
                    }

                    if (fill_in_transaction_information_et1.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交客户姓名");
                        return;
                    }

                    if (fill_in_transaction_information_et2.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交客户电话");
                        return;
                    }

                    if (fill_in_transaction_information_et3.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交客户身份证号");
                        return;
                    }

                    if (project_relation.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择成交客户与报备客户关系");
                        return;
                    }

                    if (fang_hao_et1.getText().toString().equals("") || fang_hao_et2.getText().toString().equals("") || fang_hao_et3.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交房号");
                        return;
                    }

                    if (house_type.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择成交户型");
                        return;
                    }

                    if (fill_in_transaction_information_et4.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交面积");
                        return;
                    }

                    if (fill_in_transaction_information_et5.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请填写成交单价");
                        return;
                    }

                    if (str.equals("")) {
                        ToastUtil.showToast(this, "请填写成交单价并确认");
                        return;
                    }

                    if (payment_way.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择付款方式");
                        return;
                    }

                    if (project_time.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择成交时间");
                        return;
                    }

                    if (ifnum6 == 0) {
                        ifnum6 = 1;
                        Log.i("判断", "shuju：" + ifnum6);
                        initTradeSave();
                    }

                }


                break;
            //            TODO 产品类型
            case R.id.fill_in_transaction_information_rl1:

                if (ifnum4 == 0) {
                    ifnum4 = 1;
                    type = "procuct_type";
                    initData(project_type);
                    ifnum4 = 0;
                }

                break;
            //            TODO 成交客户与报备客户关系
            case R.id.fill_in_transaction_information_rl2:
                if (ifnum1 == 0) {
                    ifnum1 = 1;
                    List<String> relation = new ArrayList<>();
                    relation.add("本人");
                    relation.add("父母");
                    relation.add("子女");
                    relation.add("配偶");
                    relation.add("其他");
                    initSelect(relation, project_relation);
                    ifnum1 = 0;
                }

                break;
            //            TODO 成交户型
            case R.id.fill_in_transaction_information_rl3:
                if (ifnum5 == 0) {
                    ifnum5 = 1;
                    type = "apartment";
                    initData(house_type);
                    ifnum5 = 0;
                }
                break;
            //            TODO 付款方式
            case R.id.fill_in_transaction_information_rl4:
                if (ifnum2 == 0) {
                    ifnum2 = 1;
                    List<String> payment = new ArrayList<>();
                    payment.add("一次性");
                    payment.add("按揭");
                    payment.add("分期");
                    initSelect(payment, payment_way);
                    ifnum2 = 0;
                }
                break;
            //            TODO 成交时间
            case R.id.fill_in_transaction_information_rl5:

                if (ifnum3 == 0) {
                    ifnum3 = 1;
                    hideInput();
                    picker.setVisibility(View.VISIBLE);
                    initDate();
                    ifnum3 = 0;
                }
                break;
            //            TODO 佣金
            case R.id.fill_in_transaction_information_rl6:
                if (whether) {
                    transition_layout.setVisibility(View.VISIBLE);
                    initTimeData();
                    whether = false;
                } else {
                    transition_layout.setVisibility(View.GONE);
                    whether = true;
                }
                break;
            //            TODO 佣金选择
            case R.id.transition_layout:
                transition_layout.setVisibility(View.GONE);
                break;
            case R.id.one_key_relative_1:
                initQuery();
                break;
        }
    }

    //TODO 成交时间赋值
    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month, dayOfMonth);
        project_time.setText(dateString);
        dateTimePickerView.setStartDate(new GregorianCalendar(year - 2, month - 1, dayOfMonth));
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(2019, month - 1, dayOfMonth));
        dateTimePickerView.setEndDate(new GregorianCalendar(year, month - 1, dayOfMonth));

        picker_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setVisibility(View.GONE);
            }
        });

        picker_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setVisibility(View.GONE);
            }
        });

        dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
            @Override
            public void onSelectedDateChanged(Calendar date) {
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH);
                int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month + 1, dayOfMonth);
                project_time.setText(dateString);
            }
        });

        //            TODO 时间
        project_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month + 1, dayOfMonth);
                        project_time.setText(dateString);
                    }
                });
            }
        });
    }

    private void initTimeData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BrokerBean> userMessage = fzbInterface.getTimeRange(project_time.getText().toString(), FinalContents.getProjectID(), FinalContents.getJJrID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final BrokerBean brokerBean) {
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(OneKeyActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        transition_recycler.setLayoutManager(layoutManager);
                        TimeRangeAdapter timeRangeAdapter = new TimeRangeAdapter(brokerBean.getData());
                        transition_recycler.setNestedScrollingEnabled(false);
                        transition_recycler.setAdapter(timeRangeAdapter);
                        timeRangeAdapter.setOnItemClickListener(new TimeRangeAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {
                                project_brokerage.setText(brokerBean.getData().get(postion).getCommissionFormat());
                                FinalContents.setCommissionId(brokerBean.getData().get(postion).getId());
                                transition_layout.setVisibility(View.GONE);
                                whethe = true;
                            }
                        });
                        timeRangeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("佣金列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData(final TextView textView) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DictListBean> userMessage = fzbInterface.getDictList(FinalContents.getUserID(), type);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DictListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(DictListBean dictListBean) {
                        List<String> list = new ArrayList<>();
                        List<DictListBean.DataBean> dictListBeanList = dictListBean.getData();
                        for (int i = 0; i < dictListBeanList.size(); i++) {
                            list.add(dictListBeanList.get(i).getName());
                        }
                        initSelect(list, textView);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initQuery() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL","FinalContents.getUserID():" + FinalContents.getUserID());
        Log.i("MyCL","one_key_relative_et1.getText().toString():" + one_key_relative_et1.getText().toString());
        Log.i("MyCL","one_key_relative_et2.getText().toString():" + one_key_relative_et2.getText().toString());
        Observable<PhoneByUserBean> userMessage = fzbInterface.getPhoneByUser(FinalContents.getUserID(),one_key_relative_et2.getText().toString(),one_key_relative_et1.getText().toString());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhoneByUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(PhoneByUserBean phoneByUserBean) {
                        Log.i("MyCL","phoneByUserBean:" + phoneByUserBean.getData().getMsg());
                        FinalContents.setJJrID(phoneByUserBean.getData().getData().getUserId());
                        if (phoneByUserBean.getData().getCode().equals("1")) {
                            FinalContents.setProject("1");
                            FinalContents.setChecked2(true);
                            Intent project_intent = new Intent(OneKeyActivity.this, MyProjectActivity.class);
                            startActivity(project_intent);
                        }else if (phoneByUserBean.getData().getCode().equals("0")){
                           ToastUtil.showLongToast(OneKeyActivity.this, "请确保输入的经纪人信息是否正确");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(OneKeyActivity.this, "请确保输入的经纪人信息是否正确");
                        Log.i("根据手机号查询经纪人信息数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //  TODO 选择器
    private void initSelect(final List<String> list, final TextView textView) {
        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(OneKeyActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                textView.setText(list.get(options1));
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();
    }

    // TODO 添加成交信息数据
    private void initTradeSave() {
        Log.i("判断", "getProjectID：" + FinalContents.getProjectID());
        Log.i("判断", "getPreparationId：" + FinalContents.getPreparationId());
        Log.i("判断", "getCustomerID：" + FinalContents.getCustomerID());
        Log.i("判断", "getCommissionId：" + FinalContents.getCommissionId());
        Log.i("判断", "getUserID：" + FinalContents.getUserID());
        initselect();


        if (whethe) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<OneKeyTradeBean> userMessage = fzbInterface.getOneKeyTrade(projecttype+"", fill_in_transaction_information_et1.getText().toString()+"", gender+"", fill_in_transaction_information_et2.getText().toString()+"", fill_in_transaction_information_et3.getText().toString()+"", fang_hao_et1.getText().toString() + "栋" + fang_hao_et2.getText().toString() + "单元" + fang_hao_et3.getText().toString() + "室", apartment+"", fill_in_transaction_information_et4.getText().toString()+"", fill_in_transaction_information_et5.getText().toString()+"", str+"", payment_way.getText().toString()+"", one_key_et.getText().toString()+"", FinalContents.getCommissionId()+"", FinalContents.getProjectID()+"", FinalContents.getUserID()+"", one_key_relative_et1.getText().toString()+"", one_key_relative_et2.getText().toString()+"", project_time.getText().toString()+"");
            userMessage.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<OneKeyTradeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onNext(OneKeyTradeBean oneKeyTradeBean) {
                            ToastUtil.showToast(OneKeyActivity.this, oneKeyTradeBean.getData().getMsg());
                            if (oneKeyTradeBean.getData().getStatus().equals("1")) {
                                finish();
                                Log.i("判断", "shuju2：" + ifnum6);
                            }
                            ifnum6 = 0;
                        }

                        @Override
                        public void onError(Throwable e) {
                            ifnum6 = 0;
                            ToastUtil.showToast(OneKeyActivity.this, "请查看所输入的信息是否正确");
                            Log.i("一键成交信息", "错误" + e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            if (FinalContents.getCommissionId().equals("")) {
                ToastUtil.showToast(OneKeyActivity.this, "请选择佣金");
            } else {
                FinalContents.setTiaozhuang("成交");
            }
        } else {
            ToastUtil.showToast(OneKeyActivity.this, "请选择佣金");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (FinalContents.isChecked2()) {
            one_key_relative_et3.setText(FinalContents.getProjectName());
        } else {
            one_key_relative_et3.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CityContents.setOneKey("");
        FinalContents.setProjectID("");
        FinalContents.setProjectName("");
        FinalContents.setCommissionId("");
        FinalContents.setProject("");
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
}
