package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.TimeRangeAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.BrokerBean;
import com.xcy.fzb.captain_team.database.DictListBean;
import com.xcy.fzb.captain_team.database.TradeSaveBean;
import com.xcy.fzb.captain_team.persente.MyLinearLayoutManager;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

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
public class FillInTransactionInformationActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView fill_in_transaction_information_return;
    Button fill_in_transaction_information_btn;
    RadioGroup fill_in_transaction_information_rg;
    RadioButton fill_in_transaction_information_rb1;
    RadioButton fill_in_transaction_information_rb2;

    TextView fill_in_transaction_information_tv1;
    TextView fill_in_transaction_information_tv2;
    TextView fill_in_transaction_information_tv3;
    TextView fill_in_transaction_information_tv4;
    TextView fill_in_transaction_information_tv5;
    TextView fill_in_transaction_information_tv6;
    TextView fill_in_transaction_information_title;

    TextView project_type;
    TextView project_relation;
    TextView house_type;
    TextView payment_way;
    TextView project_time;
    TextView project_brokerage;



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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_transaction_information);
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


        fill_in_transaction_information_tv1 = findViewById(R.id.fill_in_transaction_information_tv1);
        fill_in_transaction_information_tv2 = findViewById(R.id.fill_in_transaction_information_tv2);
        fill_in_transaction_information_tv3 = findViewById(R.id.fill_in_transaction_information_tv3);
        fill_in_transaction_information_tv4 = findViewById(R.id.fill_in_transaction_information_tv4);
        fill_in_transaction_information_tv5 = findViewById(R.id.fill_in_transaction_information_tv5);
        fill_in_transaction_information_tv6 = findViewById(R.id.fill_in_transaction_information_tv6);

        fill_in_transaction_information_rl1 = findViewById(R.id.fill_in_transaction_information_rl1);
        fill_in_transaction_information_rl2 = findViewById(R.id.fill_in_transaction_information_rl2);
        fill_in_transaction_information_rl3 = findViewById(R.id.fill_in_transaction_information_rl3);
        fill_in_transaction_information_rl4 = findViewById(R.id.fill_in_transaction_information_rl4);
        fill_in_transaction_information_rl5 = findViewById(R.id.fill_in_transaction_information_rl5);
        fill_in_transaction_information_rl6 = findViewById(R.id.fill_in_transaction_information_rl6);


        fill_in_transaction_information_return.setOnClickListener(this);
        fill_in_transaction_information_btn.setOnClickListener(this);
        fill_in_transaction_information_rl1.setOnClickListener(this);
        fill_in_transaction_information_rl2.setOnClickListener(this);
        fill_in_transaction_information_rl3.setOnClickListener(this);
        fill_in_transaction_information_rl4.setOnClickListener(this);
        fill_in_transaction_information_rl5.setOnClickListener(this);
        fill_in_transaction_information_rl6.setOnClickListener(this);
    }

    // TODO 添加成交信息数据
    private void initTradeSave(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TradeSaveBean> userMessage = fzbInterface.getTradeSave("",FinalContents.getProjectID(),FinalContents.getEconomicCircleID(),FinalContents.getPreparationId(),FinalContents.getCustomerID(),"1","","1","1","10","支付宝",FinalContents.getCommissionId(),FinalContents.getProcuctType(),"","","","","",FinalContents.getUserID(),"2019年9月9日");
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
                        finish();
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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.fill_in_transaction_information_return:
                finish();
                break;
            //            TODO 提交
            case R.id.fill_in_transaction_information_btn:
                initTradeSave();
                finish();
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
        String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month + 1, dayOfMonth);
        project_time.setText(dateString);
        dateTimePickerView.setStartDate(Calendar.getInstance());
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));

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
        Observable<BrokerBean> userMessage = fzbInterface.getTimeRange(project_time.getText().toString(),FinalContents.getProjectID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(BrokerBean brokerBean) {
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(FillInTransactionInformationActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        transition_recycler.setLayoutManager(layoutManager);
                        TimeRangeAdapter timeRangeAdapter = new TimeRangeAdapter(brokerBean.getData());
                        transition_recycler.setNestedScrollingEnabled(false);
                        transition_recycler.setAdapter(timeRangeAdapter);
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
