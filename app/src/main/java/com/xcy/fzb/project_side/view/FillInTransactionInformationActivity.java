package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.ConfessAdapter;
import com.xcy.fzb.all.adapter.ConfessAdapter2;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.BrokerBean;
import com.xcy.fzb.all.modle.DictListBean;
import com.xcy.fzb.all.modle.FindAdjustApplyBean;
import com.xcy.fzb.all.modle.FindTradeBean;
import com.xcy.fzb.all.modle.TradeSaveBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.KeyUtils;
import com.xcy.fzb.all.utils.MatcherUtils;
import com.xcy.fzb.all.view.AllActivity;
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

//TODO 填写成交信息
public class FillInTransactionInformationActivity extends AllActivity implements View.OnClickListener {

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
    private double sum = 0;
    private String projecttype;
    private RecyclerView fill_in_transaction_information_rv;
    private TextView fill_in_transaction_information_tishi;
    private boolean whethe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_fill_in_transaction_information);
        StatusBar.makeStatusBarTransparent(this);
        initView();


    }

    private void initView() {

        fill_in_transaction_information_return = findViewById(R.id.fill_in_transaction_information_return);
        fill_in_transaction_information_btn = findViewById(R.id.fill_in_transaction_information_btn);
        fill_in_transaction_information_rg = findViewById(R.id.fill_in_transaction_information_rg);
        fill_in_transaction_information_rb1 = findViewById(R.id.fill_in_transaction_information_rb1);
        fill_in_transaction_information_rb2 = findViewById(R.id.fill_in_transaction_information_rb2);

        fill_in_transaction_information_title = findViewById(R.id.fill_in_transaction_information_title);

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
        fill_in_transaction_information_et2.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        fill_in_transaction_information_et3.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        fill_in_transaction_information_et4 = findViewById(R.id.fill_in_transaction_information_et4);
        fill_in_transaction_information_et5 = findViewById(R.id.fill_in_transaction_information_et5);
        fill_in_transaction_information_et4.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        fill_in_transaction_information_et5.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        fill_in_transaction_information_et6 = findViewById(R.id.fill_in_transaction_information_et6);

        fill_in_transaction_information_tishi = findViewById(R.id.fill_in_transaction_information_tishi);

        fill_in_transaction_information_rv = findViewById(R.id.fill_in_transaction_information_rv);

        fang_hao_et3 = findViewById(R.id.fang_hao_et3);
        fang_hao_et2 = findViewById(R.id.fang_hao_et2);
        fang_hao_et1 = findViewById(R.id.fang_hao_et1);

        fang_hao_et1.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        fang_hao_et2.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        fang_hao_et3.setInputType(EditorInfo.TYPE_CLASS_PHONE);

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
                    if (s1.equals("") && s2.equals("")) {
                    }else {
                        double area = Double.parseDouble(s1);
                        double price = Double.parseDouble(s2);
                        sum = (area*price);
                        java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.00");
                        String str = myformat.format(sum);
                        Log.i("计算价格","sum:"+sum);
                        fill_in_transaction_information_et6.setText(str+"元");
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
                }else {
                    //当actionId == XX_SEND 或者 XX_DONE时都触发
                    //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                    //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                        //处理事件
                        KeyUtils.hideKeyboard(fill_in_transaction_information_et5);
                        String s1 = fill_in_transaction_information_et4.getText().toString();
                        String s2 = fill_in_transaction_information_et5.getText().toString();
                        if (s1.equals("") && s2.equals("")) {
                        }else {
                            double area = Double.parseDouble(s1);
                            double price = Double.parseDouble(s2);
                            sum = (area*price);
                            java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.00");
                            String str = myformat.format(sum);
                            Log.i("计算价格","sum:"+sum);
                            fill_in_transaction_information_et6.setText(sum+"元");
                            fill_in_transaction_information_tishi.setVisibility(View.GONE);
                            return true;
                        }
                    }
                }
                return false;
            }
        });



        if (FinalContents.getTiaodan().equals("调单")) {
            initFindAdjustApply();
            fill_in_transaction_information_et4.setOnEditorActionListener(new TextView.OnEditorActionListener() {

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
                        if (s1.equals("") && s2.equals("")) {
                        }else {
                            double area = Double.parseDouble(s1);
                            double price = Double.parseDouble(s2);
                            sum = (area*price);
                            java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.00");
                            String str = myformat.format(sum);
                            Log.i("计算价格","sum:"+sum);
                            fill_in_transaction_information_et6.setText(sum+"元");
                            fill_in_transaction_information_tishi.setVisibility(View.GONE);
                            return true;
                        }
                    }
                    return false;
                }
            });
        } else if (FinalContents.getTiaodan().equals("成交")) {
            initFindTrade();
        }

        if (fill_in_transaction_information_et6.getText().toString().equals("")) {
        }else {
            fill_in_transaction_information_tishi.setVisibility(View.GONE);
        }
    }

    private void initselect(){
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
        }else if (fill_in_transaction_information_rb2.isChecked()){
            gender = "女";
        }
        if (project_type.getText().toString().equals("住宅")) {
            projecttype = "1";
        } else if (project_type.getText().toString().equals("公寓")) {
            projecttype = "2";
        } else if (project_type.getText().toString().equals("写字楼")) {
            projecttype = "3";
        } else if (project_type.getText().toString().equals("商铺")) {
            projecttype = "4";
        } else if (project_type.getText().toString().equals("别墅")) {
            projecttype = "5";
        }
    }

    // TODO 添加成交信息数据
    private void initTradeSave(){
        initselect();
        Log.i("成交信息","项目ID："+ FinalContents.getProjectID());
        Log.i("成交信息","报备ID："+ FinalContents.getPreparationId());
        Log.i("成交信息","客户ID："+ FinalContents.getCustomerID());
        Log.i("成交信息","佣金ID："+ FinalContents.getCommissionId());
        Log.i("成交信息","用户ID："+ FinalContents.getUserID());

        Log.i("成交123","添加成交");
        if (whethe) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<TradeSaveBean> userMessage = fzbInterface.getTradeSave("",FinalContents.getProjectID(),"",FinalContents.getPreparationId(),FinalContents.getCustomerID(),fang_hao_et1.getText().toString()+"栋"+fang_hao_et2.getText().toString()+"单元"+fang_hao_et3.getText().toString()+"室", apartment,fill_in_transaction_information_et4.getText().toString(),fill_in_transaction_information_et5.getText().toString(),sum+"",payment_way.getText().toString(),FinalContents.getCommissionId(),projecttype, gender,project_relation.getText().toString(),fill_in_transaction_information_et1.getText().toString(),fill_in_transaction_information_et2.getText().toString(),fill_in_transaction_information_et3.getText().toString(),FinalContents.getUserID(),project_time.getText().toString());
            userMessage.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TradeSaveBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onNext(TradeSaveBean tradeSaveBean) {
                            if (FinalContents.getCommissionId().equals("")){
                                Toast.makeText(FillInTransactionInformationActivity.this, "请选择佣金", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(FillInTransactionInformationActivity.this, tradeSaveBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("成交信息","错误"+e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            if (FinalContents.getCommissionId().equals("")){
                Toast.makeText(FillInTransactionInformationActivity.this, "请选择佣金", Toast.LENGTH_SHORT).show();
            }else {
                FinalContents.setTiaozhuang("成交");
                Log.i("返回的数据：","成交内的："+FinalContents.getTiaozhuang());
            }
        }else {
            Toast.makeText(FillInTransactionInformationActivity.this, "请选择佣金", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO 添加调单信息数据
    private void initAdjustApplySave(){
        Log.i("调单数据","调单报备："+FinalContents.getPreparationId());
        Log.i("调单数据","调单客户："+FinalContents.getCustomerID());
        Log.i("调单数据","调单佣金："+FinalContents.getCommissionId());
        Log.i("调单数据","调单用户："+FinalContents.getUserID());
        Log.i("调单数据","调单项目："+FinalContents.getProjectID());
        Log.i("成交123","添加调单");

        Log.i("成交123","调单："+sum);

        initselect();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TradeSaveBean> userMessage = fzbInterface.getAdjustApplySave(FinalContents.getPreparationId(),FinalContents.getCustomerID(),fang_hao_et1.getText().toString()+"栋"+fang_hao_et2.getText().toString()+"单元"+fang_hao_et3.getText().toString()+"室",apartment,fill_in_transaction_information_et4.getText().toString(),fill_in_transaction_information_et5.getText().toString(),sum+"",payment_way.getText().toString(),FinalContents.getCommissionId(),projecttype,gender,project_relation.getText().toString(),fill_in_transaction_information_et1.getText().toString(),fill_in_transaction_information_et2.getText().toString(),fill_in_transaction_information_et3.getText().toString(),FinalContents.getUserID(),FinalContents.getProjectID(),FinalContents.getEconomicCircleID(),project_time.getText().toString());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TradeSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(TradeSaveBean tradeSaveBean) {
                        Toast.makeText(FillInTransactionInformationActivity.this, tradeSaveBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        if (FinalContents.getCommissionId().equals("")){

                        }else {
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        FinalContents.setTiaozhuang("调单");
        Log.i("返回的数据：","调单内的："+FinalContents.getTiaozhuang());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.fill_in_transaction_information_return:
                finish();
                break;
            //            TODO 提交
            case R.id.fill_in_transaction_information_btn:
                if (!MatcherUtils.isMobile(fill_in_transaction_information_et2.getText().toString())) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    if (project_type.getText().toString().equals("")) {
                        Toast.makeText(this, "请选择产品类型", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (fill_in_transaction_information_et1.getText().toString().equals("")) {
                        Toast.makeText(this, "请输入成交客户姓名", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (gender.equals("")) {
                        Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (fill_in_transaction_information_et2.getText().toString().equals("")) {
                        Toast.makeText(this, "请输入成交客户电话", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (fill_in_transaction_information_et3.getText().toString().equals("")) {
                        Toast.makeText(this, "请输入成交客户身份证号", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (project_relation.getText().toString().equals("")) {
                        Toast.makeText(this, "请选择成交客户与报备客户关系", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (fang_hao_et1.getText().toString().equals("") || fang_hao_et2.getText().toString().equals("") || fang_hao_et3.getText().toString().equals("")) {
                        Toast.makeText(this, "请输入成交房号", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (house_type.getText().toString().equals("")) {
                        Toast.makeText(this, "请选择成交户型", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (fill_in_transaction_information_et4.getText().toString().equals("")) {
                        Toast.makeText(this, "请输入成交面积", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (fill_in_transaction_information_et5.getText().toString().equals("")) {
                        Toast.makeText(this, "请填写成交单价", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (fill_in_transaction_information_tishi.getText().toString().equals("")) {
                        Toast.makeText(this, "请填写成交单价并确认", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (payment_way.getText().toString().equals("")) {
                        Toast.makeText(this, "请选择付款方式", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (project_time.getText().toString().equals("")) {
                        Toast.makeText(this, "请选择成交时间", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (FinalContents.getTiaodan().equals("调单")) {
                        initAdjustApplySave();
                    } else if (FinalContents.getTiaodan().equals("成交")) {
                        initTradeSave();
                    }
                }

                break;
            //            TODO 产品类型
            case R.id.fill_in_transaction_information_rl1:
                type = "procuct_type";
                initData(project_type);
                break;
            //            TODO 成交客户与报备客户关系
            case R.id.fill_in_transaction_information_rl2:
                List<String> relation = new ArrayList<>();
                relation.add("本人");
                relation.add("父母");
                relation.add("子女");
                relation.add("配偶");
                relation.add("其他");
                initSelect(relation,project_relation);
                break;
            //            TODO 成交户型
            case R.id.fill_in_transaction_information_rl3:
                type = "apartment";
                initData(house_type);
                break;
            //            TODO 付款方式
            case R.id.fill_in_transaction_information_rl4:
                List<String> payment = new ArrayList<>();
                payment.add("一次性");
                payment.add("按揭");
                payment.add("分期");
                initSelect(payment,payment_way);
                break;
            //            TODO 成交时间
            case R.id.fill_in_transaction_information_rl5:
                picker.setVisibility(View.VISIBLE);
                initDate();
                break;
            //            TODO 佣金
            case R.id.fill_in_transaction_information_rl6:
                if (whether) {
                    transition_layout.setVisibility(View.VISIBLE);
                    initTimeData();
                    whether = false;
                }else {
                    transition_layout.setVisibility(View.GONE);
                    whether = true;
                }
                break;
            //            TODO 佣金选择
            case R.id.transition_layout:
                transition_layout.setVisibility(View.GONE);
                break;

        }
    }

    //TODO 成交时间赋值
    private void initDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month, dayOfMonth);
        project_time.setText(dateString);
        dateTimePickerView.setStartDate(new GregorianCalendar(year-2, 01, 01));
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(2019, 01, 01));
        dateTimePickerView.setEndDate(new GregorianCalendar(year, month-1, dayOfMonth));

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

    private void initTimeData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BrokerBean> userMessage = fzbInterface.getTimeRange(project_time.getText().toString(),FinalContents.getProjectID(),FinalContents.getJJrID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final BrokerBean brokerBean) {
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(FillInTransactionInformationActivity.this);
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
                                Log.i("佣金","佣金ID："+brokerBean.getData().get(postion).getId());
                                FinalContents.setCommissionId(brokerBean.getData().get(postion).getId());
                                transition_layout.setVisibility(View.GONE);
                                whethe = true;
                            }
                        });
                        timeRangeAdapter.notifyDataSetChanged();
                        Log.i("获取到数据","获取到了并显示");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                        Log.i("获取到数据","获取到了不显示");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initFindTrade(){
        Log.i("返回的数据：","getPreparationId："+FinalContents.getPreparationId());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<FindTradeBean> userMessage = fzbInterface.getFindTrade(FinalContents.getPreparationId());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindTradeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(FindTradeBean findTradeBean) {
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(FillInTransactionInformationActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        fill_in_transaction_information_rv.setLayoutManager(layoutManager);
                        ConfessAdapter confessAdapter = new ConfessAdapter(findTradeBean.getData().getShowData());
                        fill_in_transaction_information_rv.setNestedScrollingEnabled(false);
                        fill_in_transaction_information_rv.setAdapter(confessAdapter);
                        Log.i("返回的数据：","getValue："+findTradeBean.getData().getShowData().get(0).getValue());
                        fill_in_transaction_information_et1.setText(findTradeBean.getData().getFfServerEarnestMoney().getFullName());
                        fill_in_transaction_information_et2.setText(findTradeBean.getData().getFfServerEarnestMoney().getPhone());
                        fill_in_transaction_information_et3.setText(findTradeBean.getData().getFfServerEarnestMoney().getIdNumber());
                        FinalContents.setCustomerID(findTradeBean.getData().getFfServerEarnestMoney().getCustomerId());
                        project_relation.setText(findTradeBean.getData().getFfServerEarnestMoney().getRelation());
                        if (findTradeBean.getData().getFfServerEarnestMoney().getGender().equals("男")) {
                            fill_in_transaction_information_rb1.setChecked(true);
                        } else if (findTradeBean.getData().getFfServerEarnestMoney().getGender().equals("女")) {
                            fill_in_transaction_information_rb2.setChecked(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initFindAdjustApply(){
        Log.i("调单信息","调单："+FinalContents.getPreparationId());
        Log.i("调单信息","调单："+FinalContents.getUserID());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<FindAdjustApplyBean> userMessage = fzbInterface.getFindAdjustApply(FinalContents.getUserID(),FinalContents.getPreparationId());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindAdjustApplyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(FindAdjustApplyBean findTradeBean) {
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(FillInTransactionInformationActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        fill_in_transaction_information_rv.setLayoutManager(layoutManager);
                        ConfessAdapter2 confessAdapter = new ConfessAdapter2(findTradeBean.getData().getShowData());
                        fill_in_transaction_information_rv.setNestedScrollingEnabled(false);
                        fill_in_transaction_information_rv.setAdapter(confessAdapter);


                        fill_in_transaction_information_et1.setText(findTradeBean.getData().getFfServerTrade().getFullName());
                        fill_in_transaction_information_et2.setText(findTradeBean.getData().getFfServerTrade().getPhone());
                        fill_in_transaction_information_et3.setText(findTradeBean.getData().getFfServerTrade().getIdNumber());
                        project_relation.setText(findTradeBean.getData().getFfServerTrade().getRelation());
                        if (findTradeBean.getData().getFfServerTrade().getGender().equals("男")) {
                            fill_in_transaction_information_rb1.setChecked(true);
                        } else if (findTradeBean.getData().getFfServerTrade().getGender().equals("女")) {
                            fill_in_transaction_information_rb2.setChecked(true);
                        }

                        if (findTradeBean.getData().getFfServerTrade().getRoomNumber().equals("")) {
                        }else {
                            String roomNumber = findTradeBean.getData().getFfServerTrade().getRoomNumber();
                            String substring1 = roomNumber.substring(0,roomNumber.indexOf("栋"));
                            String substring2 = roomNumber.substring(roomNumber.indexOf("栋")+1,roomNumber.indexOf("单"));
                            String substring3 = roomNumber.substring(roomNumber.indexOf("元")+1,roomNumber.indexOf("室"));

                            fang_hao_et1.setText(substring1);
                            fang_hao_et2.setText(substring2);
                            fang_hao_et3.setText(substring3);
                        }

                        project_relation.setText(findTradeBean.getData().getFfServerTrade().getRelation());
                        if (findTradeBean.getData().getFfServerTrade().getProcuctType().equals("1")) {
                            project_type.setText("住宅");
                        } else if (findTradeBean.getData().getFfServerTrade().getProcuctType().equals("2")) {
                            project_type.setText("公寓");
                        } else if (findTradeBean.getData().getFfServerTrade().getProcuctType().equals("3")) {
                            project_type.setText("写字间");
                        } else if (findTradeBean.getData().getFfServerTrade().getProcuctType().equals("4")) {
                            project_type.setText("商铺");
                        } else if (findTradeBean.getData().getFfServerTrade().getProcuctType().equals("5")) {
                            project_type.setText("别墅");
                        }

                        if(findTradeBean.getData().getFfServerTrade().getApartment().equals("1")){
                            house_type.setText("一室");
                        } else if(findTradeBean.getData().getFfServerTrade().getApartment().equals("2")){
                            house_type.setText("二室");
                        } else if(findTradeBean.getData().getFfServerTrade().getApartment().equals("3")){
                            house_type.setText("三室");
                        } else if(findTradeBean.getData().getFfServerTrade().getApartment().equals("4")){
                            house_type.setText("四室");
                        } else if(findTradeBean.getData().getFfServerTrade().getApartment().equals("5")){
                            house_type.setText("五室");
                        } else if(findTradeBean.getData().getFfServerTrade().getApartment().equals("6")){
                            house_type.setText("五室以上");
                        } else if(findTradeBean.getData().getFfServerTrade().getApartment().equals("0")){
                            house_type.setText("不限");
                        }

                        fill_in_transaction_information_et4.setText(findTradeBean.getData().getFfServerTrade().getArea());
                        fill_in_transaction_information_et5.setText(findTradeBean.getData().getFfServerTrade().getPrice());
                        fill_in_transaction_information_et6.setText(findTradeBean.getData().getFfServerTrade().getTotalPrice()+"元");
                        sum = Double.parseDouble(findTradeBean.getData().getFfServerTrade().getTotalPrice());
                        payment_way.setText(findTradeBean.getData().getFfServerTrade().getPaymentMethod());
                        project_time.setText(findTradeBean.getData().getFfServerTrade().getTradeDate());

                        if (findTradeBean.getData().getFfServerTrade().getTotalPrice().equals("")) {
                        }else {
                            fill_in_transaction_information_tishi.setVisibility(View.GONE);
                        }

                        project_brokerage.setText(findTradeBean.getData().getFfServerTrade().getCommission().getCommissionFormat());


                        FinalContents.setPreparationId(findTradeBean.getData().getComesBackData().getPreparationId());
                        FinalContents.setCommissionId(findTradeBean.getData().getFfServerTrade().getCommissionId());
                        FinalContents.setCustomerID(findTradeBean.getData().getComesBackData().getCustomerId());
                        FinalContents.setProjectID(findTradeBean.getData().getComesBackData().getProjectId());
                        FinalContents.setEconomicCircleID(findTradeBean.getData().getComesBackData().getEconomicId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData(final TextView textView){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DictListBean> userMessage = fzbInterface.getDictList(FinalContents.getUserID(),type);
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
                        for (int i = 0; i < dictListBeanList.size();i++ ){
                            list.add(dictListBeanList.get(i).getName());
                        }
                        initSelect(list,textView);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //选择器
    private void initSelect(final List<String> list, final TextView textView){
        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(FillInTransactionInformationActivity.this, new OnOptionsSelectListener() {
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
}
