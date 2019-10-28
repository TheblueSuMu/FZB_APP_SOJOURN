package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
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

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.DynamicLineChartManager;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.BusinessBean;
import com.xcy.fzb.all.modle.DetailsBean;
import com.xcy.fzb.all.modle.FinanceBean;
import com.xcy.fzb.all.modle.OperationBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.all.view.MapActivity;
import com.xcy.fzb.all.view.ProjectDetails;

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

//TODO 项目详情
public class DetailsTheProjectEndActivity extends AllActivity implements View.OnClickListener {

    ImageView details_the_project_end_img;
    RelativeLayout details_the_project_end_return;

    TextView details_the_project_end_tv1;
    TextView details_the_project_end_tv2;
    TextView details_the_project_end_tv3;

    TextView details_the_project_end_tv4;
    TextView details_the_project_end_tv5;
    TextView details_the_project_end_tv6;

    TextView details_the_project_end_tv8;
    TextView details_the_project_end_tv9;
    TextView details_the_project_end_tv10;
    TextView details_the_project_end_tv11;

    TextView details_the_project_end_tv12;
    TextView details_the_project_end_tv13;
    TextView details_the_project_end_tv14;
    TextView details_the_project_end_tv15;

    RelativeLayout details_the_project_end_rl1;
    RelativeLayout details_the_project_end_rl2;

    LinearLayout details_the_project_end_ll1;
    LinearLayout details_the_project_end_ll2;
    LinearLayout details_the_project_end_ll3;
    LinearLayout details_the_project_end_ll5;
    LinearLayout details_the_project_end_ll6;
    LinearLayout details_the_project_end_ll7;
    LinearLayout details_the_project_end_ll8;
    LinearLayout details_the_project_end_ll9;
    LinearLayout details_the_project_end_ll10;
    LinearLayout details_the_project_end_ll11;
    LinearLayout details_the_project_end_ll12;

    LinearLayout details_the_project_end_time_ll1;
    LinearLayout details_the_project_end_time_ll2;
    LinearLayout details_the_project_end_time_ll3;

    TextView details_the_project_end_time1;
    TextView details_the_project_end_time2;
    TextView details_the_project_end_time3;
    TextView details_the_project_end_time4;
    TextView details_the_project_end_time5;
    TextView details_the_project_end_time6;

    RadioGroup details_the_project_end_rg1;
    RadioGroup details_the_project_end_rg2;
    RadioGroup details_the_project_end_rg3;
    RadioGroup details_the_project_end_rg4;

    RadioButton details_the_project_end_rb1;
    RadioButton details_the_project_end_rb2;
    RadioButton details_the_project_end_rb3;
    RadioButton details_the_project_end_rb4;
    RadioButton details_the_project_end_rb5;
    RadioButton details_the_project_end_rb6;
    RadioButton details_the_project_end_rb7;
    RadioButton details_the_project_end_rb8;
    RadioButton details_the_project_end_rb9;
    RadioButton details_the_project_end_rb10;
    RadioButton details_the_project_end_rb11;
    RadioButton details_the_project_end_rb13;
    RadioButton details_the_project_end_rb14;
    RadioButton details_the_project_end_rb15;

    Intent intent;

    private String type1 = "";
    private String beforeDate1 = "";
    private String afterDate1 = "";

    private String type2 = "";
    private String beforeDate2 = "";
    private String afterDate2 = "";

    private String type3 = "1";
    private String beforeDate3 = "";
    private String afterDate3 = "";

    private String status = "10";
    private LineChart details_chart;

    DateTimePickerView dateTimePickerView;
    RelativeLayout particulars_picker;
    TextView particulars_picker_cancel;
    TextView particulars_picker_ensure;

    private DynamicLineChartManager dynamicLineChartManager;
    private List<String> names = new ArrayList<>(); //折线名字集合
    private List<Integer> colour = new ArrayList<>();//折线颜色集合
    private List<String> indexList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_details_the_project_end);
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

    //TODO 声明
    private void initView() {
        FinalContents.setDetails("项目详情");
        StatusBar.makeStatusBarTransparent(this);

        details_the_project_end_img = findViewById(R.id.details_the_project_end_img);
        details_the_project_end_return = findViewById(R.id.details_the_project_end_return);

        details_the_project_end_tv1 = findViewById(R.id.details_the_project_end_tv1);
        details_the_project_end_tv2 = findViewById(R.id.details_the_project_end_tv2);
        details_the_project_end_tv3 = findViewById(R.id.details_the_project_end_tv3);

        details_the_project_end_tv4 = findViewById(R.id.details_the_project_end_tv4);
        details_the_project_end_tv5 = findViewById(R.id.details_the_project_end_tv5);
        details_the_project_end_tv6 = findViewById(R.id.details_the_project_end_tv6);

        details_the_project_end_tv8 = findViewById(R.id.details_the_project_end_tv8);
        details_the_project_end_tv9 = findViewById(R.id.details_the_project_end_tv9);
        details_the_project_end_tv10 = findViewById(R.id.details_the_project_end_tv10);
        details_the_project_end_tv11 = findViewById(R.id.details_the_project_end_tv11);

        details_the_project_end_tv12 = findViewById(R.id.details_the_project_end_tv12);
        details_the_project_end_tv13 = findViewById(R.id.details_the_project_end_tv13);
        details_the_project_end_tv14 = findViewById(R.id.details_the_project_end_tv14);
        details_the_project_end_tv15 = findViewById(R.id.details_the_project_end_tv15);

        details_the_project_end_rl1 = findViewById(R.id.details_the_project_end_rl1);
        details_the_project_end_rl2 = findViewById(R.id.details_the_project_end_rl2);

        details_the_project_end_ll1 = findViewById(R.id.details_the_project_end_ll1);
        details_the_project_end_ll2 = findViewById(R.id.details_the_project_end_ll2);
        details_the_project_end_ll3 = findViewById(R.id.details_the_project_end_ll3);
        details_the_project_end_ll5 = findViewById(R.id.details_the_project_end_ll5);
        details_the_project_end_ll6 = findViewById(R.id.details_the_project_end_ll6);
        details_the_project_end_ll7 = findViewById(R.id.details_the_project_end_ll7);
        details_the_project_end_ll8 = findViewById(R.id.details_the_project_end_ll8);
        details_the_project_end_ll9 = findViewById(R.id.details_the_project_end_ll9);
        details_the_project_end_ll10 = findViewById(R.id.details_the_project_end_ll10);
        details_the_project_end_ll11 = findViewById(R.id.details_the_project_end_ll11);
        details_the_project_end_ll12 = findViewById(R.id.details_the_project_end_ll12);

        details_the_project_end_time1 = findViewById(R.id.details_the_project_end_time1);
        details_the_project_end_time2 = findViewById(R.id.details_the_project_end_time2);
        details_the_project_end_time3 = findViewById(R.id.details_the_project_end_time3);
        details_the_project_end_time4 = findViewById(R.id.details_the_project_end_time4);
        details_the_project_end_time5 = findViewById(R.id.details_the_project_end_time5);
        details_the_project_end_time6 = findViewById(R.id.details_the_project_end_time6);

        details_the_project_end_rg1 = findViewById(R.id.details_the_project_end_rg1);
        details_the_project_end_rg2 = findViewById(R.id.details_the_project_end_rg2);
        details_the_project_end_rg3 = findViewById(R.id.details_the_project_end_rg3);
        details_the_project_end_rg4 = findViewById(R.id.details_the_project_end_rg4);

        details_the_project_end_rb1 = findViewById(R.id.details_the_project_end_rb1);
        details_the_project_end_rb2 = findViewById(R.id.details_the_project_end_rb2);
        details_the_project_end_rb3 = findViewById(R.id.details_the_project_end_rb3);
        details_the_project_end_rb4 = findViewById(R.id.details_the_project_end_rb4);
        details_the_project_end_rb5 = findViewById(R.id.details_the_project_end_rb5);
        details_the_project_end_rb6 = findViewById(R.id.details_the_project_end_rb6);
        details_the_project_end_rb7 = findViewById(R.id.details_the_project_end_rb7);
        details_the_project_end_rb8 = findViewById(R.id.details_the_project_end_rb8);
        details_the_project_end_rb9 = findViewById(R.id.details_the_project_end_rb9);
        details_the_project_end_rb10 = findViewById(R.id.details_the_project_end_rb10);
        details_the_project_end_rb11 = findViewById(R.id.details_the_project_end_rb11);
        details_the_project_end_rb13 = findViewById(R.id.details_the_project_end_rb13);
        details_the_project_end_rb14 = findViewById(R.id.details_the_project_end_rb14);
        details_the_project_end_rb15 = findViewById(R.id.details_the_project_end_rb15);

        details_the_project_end_time_ll1 = findViewById(R.id.details_the_project_end_time_ll1);
        details_the_project_end_time_ll2 = findViewById(R.id.details_the_project_end_time_ll2);
        details_the_project_end_time_ll3 = findViewById(R.id.details_the_project_end_time_ll3);
        particulars_picker = findViewById(R.id.particulars_picker);
        particulars_picker_cancel = findViewById(R.id.particulars_picker_cancel);
        particulars_picker_ensure = findViewById(R.id.particulars_picker_ensure);
        dateTimePickerView = findViewById(R.id.particulars_pickerView);

        details_chart = findViewById(R.id.details_chart);

        details_the_project_end_return.setOnClickListener(this);
        details_the_project_end_rl1.setOnClickListener(this);
        details_the_project_end_rl2.setOnClickListener(this);

        details_the_project_end_time1.setOnClickListener(this);
        details_the_project_end_time2.setOnClickListener(this);
        details_the_project_end_time3.setOnClickListener(this);
        details_the_project_end_time4.setOnClickListener(this);
        details_the_project_end_time5.setOnClickListener(this);
        details_the_project_end_time6.setOnClickListener(this);

        details_the_project_end_tv4.setOnClickListener(this);
        details_the_project_end_tv5.setOnClickListener(this);
        details_the_project_end_tv6.setOnClickListener(this);
        details_the_project_end_tv8.setOnClickListener(this);
        details_the_project_end_tv9.setOnClickListener(this);
        details_the_project_end_tv10.setOnClickListener(this);
        details_the_project_end_tv11.setOnClickListener(this);
        details_the_project_end_tv12.setOnClickListener(this);
        details_the_project_end_tv13.setOnClickListener(this);
        details_the_project_end_tv14.setOnClickListener(this);
        details_the_project_end_tv15.setOnClickListener(this);

        details_the_project_end_rb1.setOnClickListener(this);
        details_the_project_end_rb2.setOnClickListener(this);
        details_the_project_end_rb3.setOnClickListener(this);
        details_the_project_end_rb4.setOnClickListener(this);
        details_the_project_end_rb5.setOnClickListener(this);
        details_the_project_end_rb6.setOnClickListener(this);
        details_the_project_end_rb7.setOnClickListener(this);
        details_the_project_end_rb8.setOnClickListener(this);
        details_the_project_end_rb9.setOnClickListener(this);
        details_the_project_end_rb10.setOnClickListener(this);
        details_the_project_end_rb11.setOnClickListener(this);
        details_the_project_end_rb13.setOnClickListener(this);
        details_the_project_end_rb14.setOnClickListener(this);
        details_the_project_end_rb15.setOnClickListener(this);

        details_the_project_end_ll1.setOnClickListener(this);
        details_the_project_end_ll2.setOnClickListener(this);
        details_the_project_end_ll3.setOnClickListener(this);
        details_the_project_end_ll5.setOnClickListener(this);
        details_the_project_end_ll6.setOnClickListener(this);
        details_the_project_end_ll7.setOnClickListener(this);
        details_the_project_end_ll8.setOnClickListener(this);
        details_the_project_end_ll9.setOnClickListener(this);
        details_the_project_end_ll10.setOnClickListener(this);
        details_the_project_end_ll11.setOnClickListener(this);
        details_the_project_end_ll12.setOnClickListener(this);


        initDate();
        initData();
        initViewData1();
        initViewData2();
    }

    //TODO 详情页时间赋值
    private void initDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        details_the_project_end_time1.setText("<"+string);
        details_the_project_end_time2.setText("-"+string+" >");
        details_the_project_end_time3.setText("<"+string);
        details_the_project_end_time4.setText("-"+string+" >");
        details_the_project_end_time5.setText("<"+string);
        details_the_project_end_time6.setText("-"+string+" >");

        dateTimePickerView.setStartDate(new GregorianCalendar(year-2, month, dayOfMonth));
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));

        dateTimePickerView.setEndDate(new GregorianCalendar(year+1, month, dayOfMonth));

        particulars_picker_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particulars_picker.setVisibility(View.GONE);
            }
        });

        particulars_picker_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particulars_picker.setVisibility(View.GONE);
            }
        });

        particulars_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particulars_picker.setVisibility(View.GONE);
            }
        });

        //            TODO 开始时间
        details_the_project_end_time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particulars_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        beforeDate1 = dateString;
                        details_the_project_end_time1.setText("<"+dateString);
                    }
                });
            }
        });
        //                TODO 结束时间
        details_the_project_end_time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particulars_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        afterDate1 = dateString;
                        details_the_project_end_time2.setText("-"+dateString+" >");
                        Log.d("wsw", "new date: " + dateString);
                        initViewData1();
                    }
                });
            }
        });

        //            TODO 开始时间
        details_the_project_end_time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particulars_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        beforeDate2 = dateString;
                        details_the_project_end_time3.setText("<"+dateString);
                    }
                });
            }
        });
        //                TODO 结束时间
        details_the_project_end_time4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particulars_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        afterDate2 = dateString;
                        details_the_project_end_time4.setText("-"+dateString+" >");
                        Log.d("wsw", "new date: " + dateString);
                        initViewData2();
                    }
                });
            }
        });

        //            TODO 开始时间
        details_the_project_end_time5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particulars_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        beforeDate3 = dateString;
                        details_the_project_end_time5.setText("<"+dateString);
                    }
                });
            }
        });

        //                TODO 结束时间
        details_the_project_end_time6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particulars_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        afterDate3 = dateString;
                        details_the_project_end_time6.setText("-"+dateString+" >");
                        initViewData3();
                    }
                });
            }
        });
    }

    //TODO 详情页财务数据赋值
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DetailsBean> userMessage = fzbInterface.getDetailsBeanList(FinalContents.getUserID(),FinalContents.getProjectID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        FinalContents.setProjectID(detailsBean.getData().getProject().getId());
                        Glide.with(DetailsTheProjectEndActivity.this).load("http://39.98.173.250:8080" +detailsBean.getData().getProject().getProjectImg()).into(details_the_project_end_img);
                        details_the_project_end_tv1.setText("["+detailsBean.getData().getProject().getCityName()+"]"+detailsBean.getData().getProject().getProjectName());
                        details_the_project_end_tv2.setText("项目地址："+detailsBean.getData().getProject().getAddress());
                        details_the_project_end_tv3.setText(Html.fromHtml("报备(" + "<font color='#A52A2A'>" + detailsBean.getData().getProject().getReportAmount() + "</font>"+")  "+"关注(" + "<font color='#A52A2A'>" + detailsBean.getData().getProject().getBrowseNum() + "</font>"+")  "+"收藏(" + "<font color='#A52A2A'>" + detailsBean.getData().getProject().getCollectionNum() + "</font>"+")  "+"转发(" + "<font color='#A52A2A'>" + detailsBean.getData().getProject().getForwardingAmount() + "</font>"+")  "));
                        List<Integer> integers = detailsBean.getData().getGsonOption().getSeries().get(0).getData();
                        indexList = detailsBean.getData().getGsonOption().getXAxis().getData();
                        init(integers);
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

    //TODO 详情页财务数据赋值
    private void initViewData1(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<OperationBean> userMessage = fzbInterface.getOperationList(FinalContents.getUserID(),FinalContents.getProjectID(),beforeDate2,afterDate2,type2);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OperationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(OperationBean operationBean) {
                        details_the_project_end_tv12.setText(""+operationBean.getData().getReceivableMoney());
                        details_the_project_end_tv13.setText(""+operationBean.getData().getBackMoney());
                        details_the_project_end_tv14.setText(""+operationBean.getData().getInvoiceMoney());
                        details_the_project_end_tv15.setText(""+operationBean.getData().getSurplusMoney());
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

    //TODO 详情页运营数据赋值
    private void initViewData2(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<FinanceBean> userMessage = fzbInterface.getFinanceList(FinalContents.getUserID(),FinalContents.getProjectID(),beforeDate1,afterDate1,type1);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FinanceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(FinanceBean financeBean) {
                        details_the_project_end_tv4.setText(""+financeBean.getData().getReportNumber());
                        details_the_project_end_tv5.setText(""+financeBean.getData().getReportOk());
                        details_the_project_end_tv6.setText(""+financeBean.getData().getAccessingNumber());
                        details_the_project_end_tv8.setText(""+financeBean.getData().getIsIslandNumber());
                        details_the_project_end_tv9.setText(""+financeBean.getData().getEarnestMoneyNumber());
                        details_the_project_end_tv10.setText(""+financeBean.getData().getTradeNumber());
                        details_the_project_end_tv11.setText(""+financeBean.getData().getInvalidNum());

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

    //TODO 详情页趋势数据赋值
    private void initViewData3(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BusinessBean> userMessage = fzbInterface.getBusinesseList(FinalContents.getUserID(),FinalContents.getProjectID(),beforeDate3,afterDate3,type3,status);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BusinessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(BusinessBean businessBean) {
                        List<Integer> integers = businessBean.getData().getSeries().get(0).getData();
                        indexList = businessBean.getData().getXAxis().getData();
                        init(integers);
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

    //TODO 详情页趋势图绘制
    private void init(final List<Integer> list){

        //显示边界
        details_chart.setDrawBorders(false);
        //无数据时显示的文字
        details_chart.setNoDataText("暂无数据");
        //折线图不显示数值
//        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = details_chart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(list.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(list.size());
        Log.i("length","长度："+list.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IndexAxisValueFormatter(indexList));
        //得到Y轴
        YAxis yAxis = details_chart.getAxisLeft();
        YAxis rightYAxis = details_chart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        // yAxis.setEnabled(false);
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(1);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(7, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //图例：得到Lengend
        Legend legend = details_chart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        details_chart.setDescription(description);
        //图标刷新
        details_chart.invalidate();
        details_chart.animateXY(2000, 2000);
        setData(list);

        // don't forget to refresh the drawing
        details_chart.invalidate();
    }

    //TODO 详情页趋势图数据填充
    private void setData(final List<Integer> list) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            values.add(new Entry(i, list.get(i)));
        }

        LineDataSet set1;

        if (details_chart.getData() != null &&
                details_chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) details_chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            details_chart.getData().notifyDataChanged();
            details_chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(true);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setValueTextSize(9f);
            set1.setHighlightEnabled(!set1.isHighlightEnabled());
            set1.setCircleColor(Color.GRAY);
            set1.setHighLightColor(Color.BLACK);
            set1.setColor(R.color.line);
            set1.setFillColor(R.color.mian);
            set1.setDrawValues(!set1.isDrawValuesEnabled());
            set1.setFillAlpha(50);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return details_chart.getAxisLeft().getAxisMinimum();
                }
            });


            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            details_chart.setData(data);
            // 设置放大限制
            details_chart.getViewPortHandler().setMaximumScaleX(1.0f); // 限制X轴放大限制
            details_chart.getViewPortHandler().setMaximumScaleY(1.0f); // 限制Y轴放大限制
        }
        details_chart.animateXY(2000, 2000);

    }

    //TODO 点击事件
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//                TODO 返回上一层
            case R.id.details_the_project_end_return:
                finish();
                break;
//                TODO 头部
            case R.id.details_the_project_end_rl1:
                intent = new Intent(DetailsTheProjectEndActivity.this, ProjectDetails.class);
                startActivity(intent);
                break;
//                TODO 项目定位
            case R.id.details_the_project_end_rl2:
                intent = new Intent(DetailsTheProjectEndActivity.this, MapActivity.class);
                startActivity(intent);
                break;
            case R.id.details_the_project_end_rb1:
                details_the_project_end_time_ll1.setVisibility(View.GONE);
                beforeDate1 = "";
                afterDate1 = "";
                type1 = "";
                initViewData2();
                break;
            case R.id.details_the_project_end_rb2:
                details_the_project_end_time_ll1.setVisibility(View.GONE);
                beforeDate1 = "";
                afterDate1 = "";
                type1 = "1";
                initViewData2();
                break;
            case R.id.details_the_project_end_rb3:
                details_the_project_end_time_ll1.setVisibility(View.GONE);
                beforeDate1 = "";
                afterDate1 = "";
                type1 = "2";
                initViewData2();
                break;
            case R.id.details_the_project_end_rb4:
                details_the_project_end_time_ll1.setVisibility(View.VISIBLE);
                type1 = "3";
                initDate();
                break;
            case R.id.details_the_project_end_rb5:
                details_the_project_end_time_ll2.setVisibility(View.GONE);
                beforeDate2 = "";
                afterDate2 = "";
                type2 = "";
                initViewData1();
                break;
            case R.id.details_the_project_end_rb6:
                details_the_project_end_time_ll2.setVisibility(View.GONE);
                beforeDate2 = "";
                afterDate2 = "";
                type2 = "1";
                initViewData1();
                break;
            case R.id.details_the_project_end_rb7:
                details_the_project_end_time_ll2.setVisibility(View.GONE);
                beforeDate2 = "";
                afterDate2 = "";
                type2 = "2";
                initViewData1();
                break;
            case R.id.details_the_project_end_rb8:
                details_the_project_end_time_ll2.setVisibility(View.VISIBLE);
                type2 = "3";
                initDate();
                break;
//                    TODO 报备
            case R.id.details_the_project_end_rb9:
                status = "10";
                initViewData3();
                break;
//                        TODO 认筹
            case R.id.details_the_project_end_rb10:
                status = "50";
                initViewData3();
                break;
//                        TODO 成交
            case R.id.details_the_project_end_rb11:
                status = "60";
                initViewData3();
                break;
//                        TODO 昨日
            case R.id.details_the_project_end_rb13:
                details_the_project_end_time_ll3.setVisibility(View.GONE);
                beforeDate3 = "";
                afterDate3 = "";
                type3 = "1";
                initViewData3();
                break;
//                        TODO 近七日
            case R.id.details_the_project_end_rb14:
                details_the_project_end_time_ll3.setVisibility(View.GONE);
                beforeDate3 = "";
                afterDate3 = "";
                type3 = "2";
                initViewData3();
                break;
//                        TODO 自定义
            case R.id.details_the_project_end_rb15:
                details_the_project_end_time_ll3.setVisibility(View.VISIBLE);
                type3 = "3";
                initDate();
                initViewData3();
                break;
//                TODO  报备审核
            case R.id.details_the_project_end_ll1:
                intent = new Intent(DetailsTheProjectEndActivity.this, CheckPendingTheProjectEndActivity.class);
                intent.putExtra("client","1");
                startActivity(intent);
                break;
//                TODO  报备
            case R.id.details_the_project_end_ll2:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client","1");
                startActivity(intent);
                break;
//                TODO  到访
            case R.id.details_the_project_end_ll3:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client","2");
                startActivity(intent);
                break;
//               TODO  登岛
            case R.id.details_the_project_end_ll5:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client","3");
                startActivity(intent);
                break;
//                TODO  认筹
            case R.id.details_the_project_end_ll6:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client","4");
                startActivity(intent);
                break;
//                TODO  成交
            case R.id.details_the_project_end_ll7:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client","5");
                startActivity(intent);
                break;
//                TODO  失效
            case R.id.details_the_project_end_ll8:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client","6");
                startActivity(intent);
                break;
//                TODO  应收
            case R.id.details_the_project_end_ll9:
                intent = new Intent(DetailsTheProjectEndActivity.this, CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  已结
            case R.id.details_the_project_end_ll10:
                intent = new Intent(DetailsTheProjectEndActivity.this, CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  待结
            case R.id.details_the_project_end_ll11:
                intent = new Intent(DetailsTheProjectEndActivity.this, CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  未结
            case R.id.details_the_project_end_ll12:
                intent = new Intent(DetailsTheProjectEndActivity.this, CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinalContents.setProjectID("");
        FinalContents.setDetails("");
    }
}
