package com.xcy.fzb.project_attache.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.database.BrokerBean;
import com.xcy.fzb.all.database.DataNumBean;
import com.xcy.fzb.all.database.FinanceBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_side.view.MyClientActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

public class BrokerActivity extends AllActivity implements View.OnClickListener {

    TextView broker_tv1;
    TextView broker_tv2;
    TextView broker_tv4;
    TextView broker_tv5;
    TextView broker_tv6;
    TextView broker_tv7;
    TextView broker_tv8;
    TextView broker_tv9;
    TextView broker_tv10;
    TextView broker_tv11;
    TextView broker_tv13;
    TextView broker_tv14;
    TextView broker_tv15;
    TextView broker_tv16;
    TextView broker_tv17;
    TextView broker_tv18;
    TextView broker_call;

    LinearLayout broker_ll1;
    LinearLayout broker_ll2;
    LinearLayout broker_ll3;
    LinearLayout broker_ll4;
    LinearLayout broker_ll5;
    LinearLayout broker_ll6;
    LinearLayout broker_ll7;
    LinearLayout broker_ll9;
    LinearLayout broker_ll10;
    LinearLayout broker_ll11;
    LinearLayout broker_ll12;

    RelativeLayout broker_return;
    ImageView broker_change;

    RadioGroup broker_rg1;
    RadioGroup broker_rg2;

    RadioButton broker_rb1;
    RadioButton broker_rb2;
    RadioButton broker_rb3;
    RadioButton broker_rb4;
    RadioButton broker_rb5;
    RadioButton broker_rb6;
    RadioButton broker_rb7;
    RadioButton broker_rb8;

    RelativeLayout broker_rl;
    TextView broker_tv;

    private LineChart details_chart;
    private List<Integer> integers;
    private List<String> indexList;
    private Intent intent;

    DateTimePickerView dateTimePickerView;
    LinearLayout report_picker;
    TextView report_cancel;
    TextView report_ensure;
    private BrokerBean.DataBean.AgentInfoBean agentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_broker);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            NewlyIncreased.setTag("");
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
            NewlyIncreased.setYJType("");
            NewlyIncreased.setYJstartDate("");
            NewlyIncreased.setYJendDate("");
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

        broker_rb1 = findViewById(R.id.broker_rb1);
        broker_rb2 = findViewById(R.id.broker_rb2);
        broker_rb3 = findViewById(R.id.broker_rb3);
        broker_rb4 = findViewById(R.id.broker_rb4);
        broker_rb5 = findViewById(R.id.broker_rb5);
        broker_rb6 = findViewById(R.id.broker_rb6);
        broker_rb7 = findViewById(R.id.broker_rb7);
        broker_rb8 = findViewById(R.id.broker_rb8);
        broker_rl = findViewById(R.id.broker_rl);
        broker_tv = findViewById(R.id.broker_tv);

        broker_rg1 = findViewById(R.id.broker_rg1);
        broker_rg2 = findViewById(R.id.broker_rg2);

        broker_return = findViewById(R.id.broker_return);
        broker_change = findViewById(R.id.broker_change);

        broker_ll1 = findViewById(R.id.broker_ll1);
        broker_ll2 = findViewById(R.id.broker_ll2);
        broker_ll3 = findViewById(R.id.broker_ll3);
        broker_ll4 = findViewById(R.id.broker_ll4);
        broker_ll5 = findViewById(R.id.broker_ll5);
        broker_ll6 = findViewById(R.id.broker_ll6);
        broker_ll7 = findViewById(R.id.broker_ll7);
        broker_ll9 = findViewById(R.id.broker_ll9);
        broker_ll10 = findViewById(R.id.broker_ll10);
        broker_ll11 = findViewById(R.id.broker_ll11);
        broker_ll12 = findViewById(R.id.broker_ll12);

        broker_tv1 = findViewById(R.id.broker_tv1);
        broker_tv2 = findViewById(R.id.broker_tv2);
        broker_tv4 = findViewById(R.id.broker_tv4);
        broker_tv5 = findViewById(R.id.broker_tv5);
        broker_tv6 = findViewById(R.id.broker_tv6);
        broker_tv7 = findViewById(R.id.broker_tv7);
        broker_tv8 = findViewById(R.id.broker_tv8);
        broker_tv9 = findViewById(R.id.broker_tv9);
        broker_tv10 = findViewById(R.id.broker_tv10);
        broker_tv11 = findViewById(R.id.broker_tv11);
        broker_tv13 = findViewById(R.id.broker_tv13);
        broker_tv14 = findViewById(R.id.broker_tv14);
        broker_tv15 = findViewById(R.id.broker_tv15);
        broker_tv16 = findViewById(R.id.broker_tv16);
        broker_tv17 = findViewById(R.id.broker_tv17);
        broker_tv18 = findViewById(R.id.broker_tv18);
        broker_call = findViewById(R.id.broker_call);

        details_chart = findViewById(R.id.lc_broker);

        dateTimePickerView = findViewById(R.id.broker_report_pickerView);
        report_picker = findViewById(R.id.broker_report_picker);
        report_cancel = findViewById(R.id.broker_report_picker_cancel);
        report_ensure = findViewById(R.id.broker_report_picker_ensure);

        broker_return.setOnClickListener(this);
        broker_change.setOnClickListener(this);
        broker_ll2.setOnClickListener(this);
        broker_ll3.setOnClickListener(this);
        broker_ll4.setOnClickListener(this);
        broker_ll5.setOnClickListener(this);
        broker_ll6.setOnClickListener(this);
        broker_ll7.setOnClickListener(this);
        broker_ll10.setOnClickListener(this);
        broker_ll11.setOnClickListener(this);
        broker_ll12.setOnClickListener(this);

        initData();

        broker_rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.broker_rb1) {
                    initDataNum("0", "", "");
                    NewlyIncreased.setTag("0");
                    broker_ll1.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb2) {
                    initDataNum("1", "", "");
                    NewlyIncreased.setTag("1");
                    broker_ll1.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb3) {
                    initDataNum("2", "", "");
                    NewlyIncreased.setTag("2");
                    broker_ll1.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb4) {
                    initDataNum("3", "", "");
                    NewlyIncreased.setTag("3");
                    broker_ll1.setVisibility(View.VISIBLE);
                }
            }
        });
        broker_rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.broker_rb5) {
                    initFinanceNum("0", "", "");
                    NewlyIncreased.setYJType("0");
                    broker_ll9.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb6) {
                    initFinanceNum("1", "", "");
                    NewlyIncreased.setYJType("1");
                    broker_ll9.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb7) {
                    initFinanceNum("2", "", "");
                    NewlyIncreased.setYJType("2");
                    broker_ll9.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb8) {
                    initFinanceNum("3", "", "");
                    NewlyIncreased.setYJType("3");
                    broker_ll9.setVisibility(View.VISIBLE);
                }
            }
        });


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        broker_tv4.setText(string);
        broker_tv5.setText(string);
        broker_tv13.setText(string);
        broker_tv14.setText(string);
        dateTimePickerView.setStartDate(Calendar.getInstance());
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));

        broker_tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        broker_tv4.setText(dateString);
                        String s = broker_tv4.getText().toString();
                        String s1 = broker_tv5.getText().toString();
                        initDataNum("3", s, s1);
                        NewlyIncreased.setStartDate(dateString);
                    }
                });
            }
        });
        broker_tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        broker_tv5.setText(dateString);
                        String s = broker_tv4.getText().toString();
                        String s1 = broker_tv5.getText().toString();
                        initDataNum("3", s, s1);
                        NewlyIncreased.setEndDate(dateString);
                    }
                });
            }
        });
        broker_tv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        broker_tv13.setText(dateString);
                        String s = broker_tv13.getText().toString();
                        String s1 = broker_tv14.getText().toString();
                        NewlyIncreased.setYJstartDate(dateString);
                        initDataNum("3", s, s1);

                    }
                });
            }
        });
        broker_tv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        broker_tv14.setText(dateString);
                        String s = broker_tv13.getText().toString();
                        String s1 = broker_tv14.getText().toString();
                        NewlyIncreased.setYJendDate(dateString);
                        initDataNum("3", s, s1);
                    }
                });
            }
        });
        report_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.GONE);
            }
        });

        report_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.GONE);
            }
        });

        broker_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (agentInfo.getAgentPhone().equals("")) {
                    Toast.makeText(BrokerActivity.this, "暂无电话信息，无法拨打", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + agentInfo.getAgentPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }

            }
        });


//        broker_tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (agentInfo.getAgentPhone().equals("")) {
//                    Toast.makeText(BrokerActivity.this, "暂无电话", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + agentInfo.getAgentPhone()));//跳转到拨号界面，同时传递电话号码
//                    startActivity(dialIntent);
//                }
//            }
//        });

    }

    private void initFinanceNum(String type, String startTime, String endTime) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<FinanceBean> financeBean = myService.getFinanceBean(FinalContents.getUserID(), "", "",FinalContents.getAgentId(), type, startTime, endTime);
        financeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FinanceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FinanceBean financeBean) {
                        broker_tv15.setText(financeBean.getData().getTotalAmount() + "");
                        broker_tv16.setText(financeBean.getData().getAlreadyAmount() + "");
                        broker_tv17.setText(financeBean.getData().getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initDataNum(String type, String startTime, String endTime) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<DataNumBean> dataNum = myService.getDataNum(FinalContents.getUserID(), "", FinalContents.getAgentId(), type, startTime, endTime);
        dataNum.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataNumBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataNumBean dataNumBean) {
                        broker_tv6.setText(dataNumBean.getData().getReportNumber() + "");
                        broker_tv7.setText(dataNumBean.getData().getAccessingNumber() + "");
                        broker_tv8.setText(dataNumBean.getData().getIsIslandNumber() + "");
                        broker_tv9.setText(dataNumBean.getData().getEarnestMoneyNumber() + "");
                        broker_tv10.setText(dataNumBean.getData().getTradeNumber() + "");
                        broker_tv11.setText(dataNumBean.getData().getInvalidNum() + "");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BrokerBean> clientCommissions = fzbInterface.getEAgentDetails(FinalContents.getAgentId(), FinalContents.getUserID());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BrokerBean brokerBean) {
                        //        TODO 基本信息
                        agentInfo = brokerBean.getData().getAgentInfo();
                        if (agentInfo.getStatus().equals("0") || agentInfo.getStatus().equals("1")) {
                            broker_rl.setVisibility(View.GONE);
                        } else if (agentInfo.getStatus().equals("2")) {
                            broker_rl.setVisibility(View.VISIBLE);
                            if (agentInfo.getReason().equals("")) {
                                broker_tv.setText("异常原因：公司取消合作");
                            } else {
                                broker_tv.setText("异常原因：" + agentInfo.getReason());
                            }
                        } else if (agentInfo.getStatus().equals("3")) {
                            broker_rl.setVisibility(View.VISIBLE);
                            if (agentInfo.getReason().equals("")) {
                                broker_tv.setText("异常原因：公司倒闭");
                            } else {
                                broker_tv.setText("异常原因：" + agentInfo.getReason());
                            }
                        }
                        broker_tv18.setText(agentInfo.getAgentName());
                        broker_tv1.setText(agentInfo.getAgentName());
                        broker_call.setText(agentInfo.getAgentPhone());
                        /**
                         * 修改 20191023
                         */
                        if (agentInfo.getStoreName().equals("")) {
                            broker_tv2.setText(agentInfo.getCompanyName());
                        } else {
                            broker_tv2.setText(agentInfo.getCompanyName() + "-" + agentInfo.getStoreName());
                        }

//        TODO 数据统计
                        BrokerBean.DataBean.AgentDataStatisticsBean agentDataStatistics = brokerBean.getData().getAgentDataStatistics();
                        broker_tv6.setText(agentDataStatistics.getReportNumber() + "");
                        broker_tv7.setText(agentDataStatistics.getAccessingNumber() + "");
                        broker_tv8.setText(agentDataStatistics.getIsIslandNumber() + "");
                        broker_tv9.setText(agentDataStatistics.getEarnestMoneyNumber() + "");
                        broker_tv10.setText(agentDataStatistics.getTradeNumber() + "");
                        broker_tv11.setText(agentDataStatistics.getInvalidNum() + "");
//        TODO 近七天活动度
                        BrokerBean.DataBean.GsonOptionBean gsonOption = brokerBean.getData().getGsonOption();
                        integers = brokerBean.getData().getGsonOption().getSeries().get(0).getData();
                        indexList = brokerBean.getData().getGsonOption().getXAxis().getData();
                        init(integers);
//        TODO 佣金
                        BrokerBean.DataBean.AgentMoneyDataBean agentMoneyData = brokerBean.getData().getAgentMoneyData();
                        broker_tv15.setText(agentMoneyData.getTotalAmount() + "");
                        broker_tv16.setText(agentMoneyData.getAlreadyAmount() + "");
                        broker_tv17.setText(agentMoneyData.getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "经纪人列表:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.broker_return:
                finish();
                FinalContents.setStoreId("");
                break;
            case R.id.broker_ll2:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
            case R.id.broker_ll3:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                intent.putExtra("client", "2");
                startActivity(intent);
                break;
            case R.id.broker_ll4:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                intent.putExtra("client", "3");
                startActivity(intent);
                break;
            case R.id.broker_ll5:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                intent.putExtra("client", "4");
                startActivity(intent);
                break;
            case R.id.broker_ll6:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                intent.putExtra("client", "5");
                startActivity(intent);
                break;
            case R.id.broker_ll7:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                intent.putExtra("client", "6");
                startActivity(intent);
                break;
            case R.id.broker_ll10:
                intent = new Intent(BrokerActivity.this, CommissionActivity.class);
                FinalContents.setCompanyId("");
                FinalContents.setStoreId("");
                FinalContents.setAgentId(agentInfo.getAgentId());
                startActivity(intent);
                break;
            case R.id.broker_ll11:
                intent = new Intent(BrokerActivity.this, CommissionActivity.class);
                FinalContents.setCompanyId("");
                FinalContents.setStoreId("");
                FinalContents.setAgentId(agentInfo.getAgentId());
                startActivity(intent);
                break;
            case R.id.broker_ll12:
                FinalContents.setCompanyId("");
                FinalContents.setStoreId("");
                FinalContents.setAgentId(agentInfo.getAgentId());
                intent = new Intent(BrokerActivity.this, CommissionActivity.class);
                startActivity(intent);
                break;
            case R.id.broker_change:
                finish();
                intent = new Intent(BrokerActivity.this, AddBrokerActivity.class);
                FinalContents.setAgentId(agentInfo.getAgentId());
                FinalContents.setBorkerChange("修改");
                startActivity(intent);
                break;
        }

    }

    //TODO 详情页趋势图绘制
    private void init(final List<Integer> list) {

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
        xAxis.setGranularity(0f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(indexList.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) list.size());
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
//        yAxis.setEnabled(false);
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
//        //设置Y轴坐标之间的最小间隔
//        yAxis.setGranularity(1);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(Collections.max(list) + 2, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
        yAxis.setAxisMaximum(Collections.max(list) + 1);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NewlyIncreased.setTag("0");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
        NewlyIncreased.setYJType("");
        NewlyIncreased.setYJstartDate("");
        NewlyIncreased.setYJendDate("");
    }
}
