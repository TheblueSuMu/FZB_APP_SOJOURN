package com.xcy.fzb.project_attache.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.CompanyBean;
import com.xcy.fzb.all.modle.CompanyDataBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.AllActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;

public class StoreDetailsActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout store_details_return;
    ImageView store_details_change;
    ImageView store_details_call;


    RadioGroup store_details_rg1;
    RadioGroup store_details_rg2;

    RadioButton store_details_rb1;
    RadioButton store_details_rb2;
    RadioButton store_details_rb3;
    RadioButton store_details_rb4;
    RadioButton store_details_rb5;
    RadioButton store_details_rb6;
    RadioButton store_details_rb7;
    RadioButton store_details_rb8;

    TextView store_details_tv1;
    TextView store_details_tv2;
    TextView store_details_tv3;
    TextView store_details_tv4;
    TextView store_details_tv5;
    TextView store_details_tv6;
    TextView store_details_tv7;
    TextView store_details_tv8;
    TextView store_details_tv9;
    TextView store_details_tv10;
    TextView store_details_tv11;
    TextView store_details_tv12;
    TextView store_details_tv13;

    RelativeLayout store_details_rl1;
    RelativeLayout store_details_rl2;

    LinearLayout store_details_ll1;
    LinearLayout store_details_ll2;
    LinearLayout store_details_ll3;
    LinearLayout store_details_ll4;
    LinearLayout store_details_ll5;
    private Intent intent;

    DateTimePickerView dateTimePickerView;
    LinearLayout report_picker;
    TextView report_cancel;
    TextView report_ensure;
    private CompanyBean.DataBean.CompanyDataStatisticsBean companyDataStatistics;
    private CompanyBean.DataBean.CompanyInfoBean companyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        store_details_return = findViewById(R.id.store_details_return);
        store_details_change = findViewById(R.id.store_details_change);
        store_details_tv13 = findViewById(R.id.store_details_tv13);
        store_details_call = findViewById(R.id.store_details_call);

        store_details_rg1 = findViewById(R.id.store_details_rg1);
        store_details_rg2 = findViewById(R.id.store_details_rg2);

        store_details_rb1 = findViewById(R.id.store_details_rb1);
        store_details_rb2 = findViewById(R.id.store_details_rb2);
        store_details_rb3 = findViewById(R.id.store_details_rb3);
        store_details_rb4 = findViewById(R.id.store_details_rb4);
        store_details_rb5 = findViewById(R.id.store_details_rb5);
        store_details_rb6 = findViewById(R.id.store_details_rb6);
        store_details_rb7 = findViewById(R.id.store_details_rb7);
        store_details_rb8 = findViewById(R.id.store_details_rb8);

        store_details_tv1 = findViewById(R.id.store_details_tv1);
        store_details_tv2 = findViewById(R.id.store_details_tv2);
        store_details_tv3 = findViewById(R.id.store_details_tv3);
        store_details_tv4 = findViewById(R.id.store_details_tv4);
        store_details_tv5 = findViewById(R.id.store_details_tv5);
        store_details_tv6 = findViewById(R.id.store_details_tv6);
        store_details_tv7 = findViewById(R.id.store_details_tv7);
        store_details_tv8 = findViewById(R.id.store_details_tv8);
        store_details_tv9 = findViewById(R.id.store_details_tv9);
        store_details_tv10 = findViewById(R.id.store_details_tv10);
        store_details_tv11 = findViewById(R.id.store_details_tv11);
        store_details_tv12 = findViewById(R.id.store_details_tv12);

        store_details_rl1 = findViewById(R.id.store_details_rl1);
        store_details_rl2 = findViewById(R.id.store_details_rl2);

        store_details_ll1 = findViewById(R.id.store_details_ll1);
        store_details_ll2 = findViewById(R.id.store_details_ll2);
        store_details_ll3 = findViewById(R.id.store_details_ll3);
        store_details_ll4 = findViewById(R.id.store_details_ll4);
        store_details_ll5 = findViewById(R.id.store_details_ll5);

        dateTimePickerView = findViewById(R.id.store_details_report_pickerView);
        report_picker = findViewById(R.id.store_details_report_picker);
        report_cancel = findViewById(R.id.store_details_report_picker_cancel);
        report_ensure = findViewById(R.id.store_details_report_picker_ensure);

        store_details_return.setOnClickListener(this);
        store_details_change.setOnClickListener(this);
        store_details_tv4.setOnClickListener(this);
        store_details_tv5.setOnClickListener(this);
        store_details_rl1.setOnClickListener(this);
        store_details_rl2.setOnClickListener(this);
        store_details_tv8.setOnClickListener(this);
        store_details_tv9.setOnClickListener(this);
        store_details_ll3.setOnClickListener(this);
        store_details_ll4.setOnClickListener(this);
        store_details_ll5.setOnClickListener(this);
        store_details_call.setOnClickListener(this);

        store_details_rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.company_details_rb1) {
                    initDataNum("0", "", "");
                    store_details_ll1.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb2) {
                    initDataNum("1", "", "");
                    store_details_ll1.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb3) {
                    initDataNum("2", "", "");
                    store_details_ll1.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb4) {
                    initDataNum("3", "", "");
                    store_details_ll1.setVisibility(View.VISIBLE);
                }
            }
        });
        store_details_rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.company_details_rb5) {
                    initFinanceNum("0", "", "");
                    store_details_ll2.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb6) {
                    initFinanceNum("1", "", "");
                    store_details_ll2.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb7) {
                    initFinanceNum("2", "", "");
                    store_details_ll2.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb8) {
                    initFinanceNum("3", "", "");
                    store_details_ll2.setVisibility(View.VISIBLE);
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        store_details_tv4.setText(string);
        store_details_tv5.setText(string);
        store_details_tv8.setText(string);
        store_details_tv9.setText(string);
        dateTimePickerView.setStartDate(Calendar.getInstance());
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));

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

        initData();

    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("公司詳情", FinalContents.getUserID());
        Log.i("公司詳情", "**********" + FinalContents.getCompanyId());
        final Observable<CompanyBean> companyDetails = fzbInterface.getCompanyDetails(FinalContents.getUserID(), FinalContents.getCompanyId());
        companyDetails.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompanyBean>() {

                    private CompanyBean.DataBean.CompanyMoneyDataBean companyMoneyData;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CompanyBean companyBean) {
//                        数据统计
                        companyDataStatistics = companyBean.getData().getCompanyDataStatistics();
                        store_details_tv6.setText(companyDataStatistics.getStoreNum() + "");
                        store_details_tv7.setText(companyDataStatistics.getAgentNum() + "");
//                        财务数据
                        companyMoneyData = companyBean.getData().getCompanyMoneyData();
                        store_details_tv10.setText(companyMoneyData.getTotalAmount() + "");
                        store_details_tv11.setText(companyMoneyData.getAlreadyAmount() + "");
                        store_details_tv12.setText(companyMoneyData.getNotAmount() + "");
//                        基本信息
                        companyInfo = companyBean.getData().getCompanyInfo();
                        store_details_tv1.setText(companyInfo.getCompanyName());
                        store_details_tv2.setText(companyInfo.getCompanyAddress());

                        store_details_tv3.setText("公司负责人：" + companyInfo.getShopownerName() + " " + companyInfo.getShopownerPhone());
                        store_details_tv13.setText(companyInfo.getCompanyName());

                        if (companyInfo.getShopownerPhone().equals("")) {
                            store_details_call.setVisibility(View.GONE);
                        } else {
                            store_details_call.setVisibility(View.VISIBLE);
                        }



                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "公司详情错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initFinanceNum(String type, String startTime, String endTime) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<com.xcy.fzb.all.database.FinanceBean> financeBean = myService.getFinanceBean(FinalContents.getUserID(), FinalContents.getStoreId(), "", type, startTime, endTime);
        financeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<com.xcy.fzb.all.database.FinanceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("MyCL", "4");
                    }

                    @Override
                    public void onNext(com.xcy.fzb.all.database.FinanceBean financeBean) {
                        Log.i("MyCL", "3");
                        store_details_tv10.setText(financeBean.getData().getTotalAmount() + "");
                        store_details_tv11.setText(financeBean.getData().getAlreadyAmount() + "");
                        store_details_tv12.setText(financeBean.getData().getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initDataNum(String s, String s1, String s11) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CompanyDataBean> companyData = fzbInterface.getCompanyData(FinalContents.getUserID(), FinalContents.getCompanyId(), s, s1, s11);
        companyData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompanyDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CompanyDataBean companyDataBean) {
                        CompanyDataBean.DataBean data = companyDataBean.getData();
                        store_details_tv4.setText(data.getStoreNum());
                        store_details_tv5.setText(data.getAgentNum());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "公司详情数据统计错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.store_details_return:
                FinalContents.setMyAddType("公司");
                finish();
                break;
            case R.id.store_details_call:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + companyInfo.getShopownerPhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;
            case R.id.store_details_change:
                finish();
                intent = new Intent(StoreDetailsActivity.this, AddCompanyActivity.class);
                FinalContents.setCompanyId(companyInfo.getCompanyId());
                Log.i("专员", "companyInfo.getCompanyId()：" + companyInfo.getCompanyId());
                FinalContents.setStoreChange("修改");
                startActivity(intent);
                break;
            case R.id.store_details_tv4:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        store_details_tv4.setText(dateString);
                        String s = store_details_tv4.getText().toString();
                        String s1 = store_details_tv5.getText().toString();
                        initDataNum("3", s, s1);
                    }
                });
                break;
            case R.id.store_details_tv5:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        store_details_tv5.setText(dateString);
                        Log.d("wsw", "new date: " + dateString);
                        String s = store_details_tv4.getText().toString();
                        String s1 = store_details_tv5.getText().toString();
                        initDataNum("3", s, s1);
                    }
                });
                break;
            case R.id.store_details_rl1:
                intent = new Intent(StoreDetailsActivity.this, StoreListActivity.class);
                Log.i("专员", "FinalContents.getCompanyId()：" + companyInfo.getCompanyId());
                FinalContents.setMyAddType("");
                FinalContents.setCompanyId(companyInfo.getCompanyId());
                startActivity(intent);
                break;
            case R.id.store_details_rl2:
                initDataS();
                break;
            case R.id.store_details_tv8:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        store_details_tv8.setText(dateString);
                        String s = store_details_tv8.getText().toString();
                        String s1 = store_details_tv9.getText().toString();
                        initFinanceNum("3", s, s1);

                    }
                });
                break;
            case R.id.store_details_tv9:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        store_details_tv8.setText(dateString);
                        String s = store_details_tv8.getText().toString();
                        String s1 = store_details_tv9.getText().toString();
                        initFinanceNum("3", s, s1);

                    }
                });
                break;
            case R.id.store_details_ll3:
                intent = new Intent(StoreDetailsActivity.this, CommissionActivity.class);
                FinalContents.setCompanyId(companyInfo.getCompanyId());
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                startActivity(intent);
                break;
            case R.id.store_details_ll4:
                intent = new Intent(StoreDetailsActivity.this, CommissionActivity.class);
                FinalContents.setCompanyId(companyInfo.getCompanyId());
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                startActivity(intent);
                break;
            case R.id.store_details_ll5:
                FinalContents.setCompanyId(companyInfo.getCompanyId());
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                intent = new Intent(StoreDetailsActivity.this, CommissionActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void initDataS() {
        intent = new Intent(StoreDetailsActivity.this, BrokersListActivity.class);
        FinalContents.setCompanyId(companyInfo.getCompanyId());
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinalContents.setMyAddType("");
        FinalContents.setCompanyId("");
    }
}
