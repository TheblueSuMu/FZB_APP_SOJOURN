package com.xcy.fzb.captain_market.view;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.database.DailyTurnoverBean;
import com.xcy.fzb.all.database.DataStatisticsBean;
import com.xcy.fzb.all.database.MyTeamBean;
import com.xcy.fzb.all.database.TeamCommissionsBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.captain_team.adapter.DailyTurnoverAdapter;
import com.xcy.fzb.captain_team.adapter.MyTeamAdapter;
import com.xcy.fzb.captain_team.view.Captain_Team_CommissionTheProjectEndActivity;
import com.xcy.fzb.captain_team.view.Captain_Team_MyClientActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;

//TODO 圈层4-1我的团队
public class Captain_Market_MyTeamActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout my_team_img;

    LinearLayout market_time_ll1;
    LinearLayout market_time_ll2;
    LinearLayout market_time_ll3;
    LinearLayout market_time_ll4;
    LinearLayout market_time_ll5;
    LinearLayout market_time_ll6;
    LinearLayout market_time_ll7;
    LinearLayout market_time_ll8;
    LinearLayout market_time_ll9;
    LinearLayout market_time_ll10;
    LinearLayout market_time_ll11;
    LinearLayout market_time_ll12;

    TextView market_time_tv1;
    TextView market_time_tv2;
    TextView market_time_tv3;
    TextView market_time_tv4;
    TextView market_time_tv5;
    TextView market_time_tv6;
    TextView market_time_tv7;
    TextView market_time_tv8;
    TextView market_time_tv9;
    TextView market_time_tv10;

    TextView market_time_time_tv1;
    TextView market_time_time_tv2;
    TextView market_time_time_tv3;
    TextView market_time_time_tv4;
    TextView market_time_time_tv5;
    TextView market_time_time_tv6;

    RadioGroup market_time_rg1;
    RadioGroup market_time_rg2;
    RadioGroup market_time_rg3;

    RadioButton market_time_rb1;
    RadioButton market_time_rb2;
    RadioButton market_time_rb3;
    RadioButton market_time_rb4;
    RadioButton market_time_rb5;
    RadioButton market_time_rb6;
    RadioButton market_time_rb7;
    RadioButton market_time_rb8;
    RadioButton market_time_rb9;
    RadioButton market_time_rb10;
    RadioButton market_time_rb11;
    RadioButton market_time_rb12;

    RelativeLayout market_time_rl2;

    RecyclerView market_time_rv;

    ImageView all_no_information_S;

    DateTimePickerView dateTimePickerView;
    LinearLayout report_picker;
    TextView report_cancel;
    TextView report_ensure;
    private Intent intent;

    String type1 = "";
    String startDate1 = "";
    String endDate1 = "";

    String type2 = "";
    String startDate2 = "";
    String endDate2 = "";

    String state = "3";

    String type3 = "";
    String startDate3 = "";
    String endDate3 = "";
    private PtrClassicFrameLayout ptrClassicFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_market_activity_my_team);

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
        FinalContents.setMySelf("0");
		StatusBar.makeStatusBarTransparent(this);

        ptrClassicFrameLayout = findViewById(R.id.my_team_FrameLayout);

        market_time_ll1 = findViewById(R.id.my_team_ll1);
        all_no_information_S = findViewById(R.id.all_no_information_S);
        market_time_ll2 = findViewById(R.id.my_team_ll2);
        market_time_ll3 = findViewById(R.id.my_team_ll3);
        market_time_ll4 = findViewById(R.id.my_team_ll4);
        market_time_ll5 = findViewById(R.id.my_team_ll5);
        market_time_ll6 = findViewById(R.id.my_team_ll6);
        market_time_ll7 = findViewById(R.id.my_team_ll7);
        market_time_ll8 = findViewById(R.id.my_team_ll8);
        market_time_ll9 = findViewById(R.id.my_team_ll9);
        market_time_ll10 = findViewById(R.id.my_team_ll10);
        market_time_ll11 = findViewById(R.id.my_team_ll11);
        market_time_ll12 = findViewById(R.id.my_team_ll12);

        market_time_tv1 = findViewById(R.id.my_team_tv1);
        market_time_tv2 = findViewById(R.id.my_team_tv2);
        market_time_tv3 = findViewById(R.id.my_team_tv3);
        market_time_tv4 = findViewById(R.id.my_team_tv4);
        market_time_tv5 = findViewById(R.id.my_team_tv5);
        market_time_tv6 = findViewById(R.id.my_team_tv6);
        market_time_tv7 = findViewById(R.id.my_team_tv7);
        market_time_tv8 = findViewById(R.id.my_team_tv8);
        market_time_tv9 = findViewById(R.id.my_team_tv9);
        market_time_tv10 = findViewById(R.id.my_team_tv10);

        market_time_time_tv1 = findViewById(R.id.my_team_time_tv1);
        market_time_time_tv2 = findViewById(R.id.my_team_time_tv2);
        market_time_time_tv3 = findViewById(R.id.my_team_time_tv3);
        market_time_time_tv4 = findViewById(R.id.my_team_time_tv4);
        market_time_time_tv5 = findViewById(R.id.my_team_time_tv5);
        market_time_time_tv6 = findViewById(R.id.my_team_time_tv6);

        market_time_rg1 = findViewById(R.id.my_team_rg1);
        market_time_rg2 = findViewById(R.id.my_team_rg2);
        market_time_rg3 = findViewById(R.id.my_team_rg3);

        market_time_rb1 = findViewById(R.id.my_team_rb1);
        market_time_rb2 = findViewById(R.id.my_team_rb2);
        market_time_rb3 = findViewById(R.id.my_team_rb3);
        market_time_rb4 = findViewById(R.id.my_team_rb4);
        market_time_rb5 = findViewById(R.id.my_team_rb5);
        market_time_rb6 = findViewById(R.id.my_team_rb6);
        market_time_rb7 = findViewById(R.id.my_team_rb7);
        market_time_rb8 = findViewById(R.id.my_team_rb8);
        market_time_rb9 = findViewById(R.id.my_team_rb9);
        market_time_rb10 = findViewById(R.id.my_team_rb10);
        market_time_rb11 = findViewById(R.id.my_team_rb11);
        market_time_rb12 = findViewById(R.id.my_team_rb12);

        market_time_rl2 = findViewById(R.id.my_team_rl2);

        market_time_rv = findViewById(R.id.my_team_rv);
        my_team_img = findViewById(R.id.my_team_img);


        dateTimePickerView = findViewById(R.id.my_team_pickerView);
        report_picker = findViewById(R.id.my_team_picker);
        report_cancel = findViewById(R.id.my_team_cancel);
        report_ensure = findViewById(R.id.my_team_ensure);


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        market_time_time_tv1.setText(string);
        market_time_time_tv2.setText(string);
        market_time_time_tv3.setText(string);
        market_time_time_tv4.setText(string);
        market_time_time_tv5.setText(string);
        market_time_time_tv6.setText(string);

        dateTimePickerView.setStartDate(new GregorianCalendar(year, month, dayOfMonth-15));
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));
        dateTimePickerView.setEndDate(new GregorianCalendar(year, month, dayOfMonth+15));
        my_team_img.setOnClickListener(this);
        market_time_rl2.setOnClickListener(this);
        market_time_ll1.setOnClickListener(this);
        market_time_ll2.setOnClickListener(this);
        market_time_ll3.setOnClickListener(this);
        market_time_ll4.setOnClickListener(this);
        market_time_ll5.setOnClickListener(this);
        market_time_ll6.setOnClickListener(this);
        market_time_ll7.setOnClickListener(this);
        market_time_ll8.setOnClickListener(this);
        market_time_ll9.setOnClickListener(this);
        market_time_time_tv1.setOnClickListener(this);
        market_time_time_tv2.setOnClickListener(this);
        market_time_time_tv3.setOnClickListener(this);
        market_time_time_tv4.setOnClickListener(this);
        market_time_time_tv5.setOnClickListener(this);
        market_time_time_tv6.setOnClickListener(this);

        market_time_rb1.setOnClickListener(this);
        market_time_rb2.setOnClickListener(this);
        market_time_rb3.setOnClickListener(this);
        market_time_rb4.setOnClickListener(this);
        market_time_rb5.setOnClickListener(this);
        market_time_rb6.setOnClickListener(this);
        market_time_rb7.setOnClickListener(this);
        market_time_rb8.setOnClickListener(this);
        market_time_rb9.setOnClickListener(this);
        market_time_rb10.setOnClickListener(this);
        market_time_rb11.setOnClickListener(this);
        market_time_rb12.setOnClickListener(this);

        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        initMyTeamData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
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
        initMyTeamData();
        initDailyTurnover();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.my_team_img:
                finish();
                break;
//            TODO 顾问
            case R.id.my_team_rl2:
                intent = new Intent(Captain_Market_MyTeamActivity.this,TeamMemberActivity.class);
                startActivity(intent);
                break;
//            TODO 报备
            case R.id.my_team_ll1:
                intent = new Intent(Captain_Market_MyTeamActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client","1");
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 到访
            case R.id.my_team_ll2:
                intent = new Intent(Captain_Market_MyTeamActivity.this,Captain_Team_MyClientActivity.class);
                intent.putExtra("client","2");
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 登岛
            case R.id.my_team_ll3:
                intent = new Intent(Captain_Market_MyTeamActivity.this,Captain_Team_MyClientActivity.class);
                intent.putExtra("client","3");
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 认筹
            case R.id.my_team_ll4:
                intent = new Intent(Captain_Market_MyTeamActivity.this,Captain_Team_MyClientActivity.class);
                intent.putExtra("client","4");
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 成交
            case R.id.my_team_ll5:
                intent = new Intent(Captain_Market_MyTeamActivity.this,Captain_Team_MyClientActivity.class);
                intent.putExtra("client","5");
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 失效
            case R.id.my_team_ll6:
                intent = new Intent(Captain_Market_MyTeamActivity.this,Captain_Team_MyClientActivity.class);
                intent.putExtra("client","6");
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 总佣金
            case R.id.my_team_ll7:
                intent = new Intent(Captain_Market_MyTeamActivity.this, Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 已结
            case R.id.my_team_ll8:
                intent = new Intent(Captain_Market_MyTeamActivity.this,Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 未结
            case R.id.my_team_ll9:
                intent = new Intent(Captain_Market_MyTeamActivity.this,Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 时间选择1
            case R.id.my_team_time_tv1:

                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv1.setText(dateString);
                        startDate1 = dateString;
                        NewlyIncreased.setStartDate(dateString);
                    }
                });

                break;
//            TODO 时间选择2
            case R.id.my_team_time_tv2:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv2.setText(dateString);
                        endDate1 = dateString;
                        NewlyIncreased.setEndDate(dateString);
                    }
                });
                initDataStatistics();
                break;
//            TODO 时间选择3
            case R.id.my_team_time_tv3:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv3.setText(dateString);
                        startDate2 = dateString;
                    }
                });
                break;
//            TODO 时间选择4
            case R.id.my_team_time_tv4:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv4.setText(dateString);
                        endDate2 = dateString;
                    }
                });
                initTeamCommissions();
                break;
//            TODO 时间选择5
            case R.id.my_team_time_tv5:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv5.setText(dateString);
                        startDate3 = dateString;
                    }
                });
                break;
//            TODO 时间选择6
            case R.id.my_team_time_tv6:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv6.setText(dateString);
                        endDate3 = dateString;
                    }
                });
                initDailyTurnover();
                break;
            //            TODO 数据统计 时间选择 全部
            case R.id.my_team_rb1:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "0";
                startDate1 = "";
                endDate1 = "";
                NewlyIncreased.setTag("0");
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 昨天
            case R.id.my_team_rb2:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "1";
                startDate1 = "";
                endDate1 = "";
                NewlyIncreased.setTag("1");
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 七天
            case R.id.my_team_rb3:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "2";
                startDate1 = "";
                endDate1 = "";
                NewlyIncreased.setTag("2");
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 自定义
            case R.id.my_team_rb4:
                market_time_ll10.setVisibility(View.VISIBLE);
                type1 = "3";
                NewlyIncreased.setTag("3");
                break;

            //            TODO 财务数据 时间选择 全部
            case R.id.my_team_rb5:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "0";
                startDate2 = "";
                endDate2 = "";
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 昨天
            case R.id.my_team_rb6:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "1";
                startDate2 = "";
                endDate2 = "";
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 七天
            case R.id.my_team_rb7:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "2";
                startDate2 = "";
                endDate2 = "";
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 自定义
            case R.id.my_team_rb8:
                market_time_ll11.setVisibility(View.VISIBLE);
                type2 = "3";
                break;

            //            TODO 成交TOP5 时间选择 全部
            case R.id.my_team_rb9:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "0";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 昨天
            case R.id.my_team_rb10:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "1";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 七天
            case R.id.my_team_rb11:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "2";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 自定义
            case R.id.my_team_rb12:
                market_time_ll12.setVisibility(View.VISIBLE);
                type3 = "3";
                break;

        }

    }


    // TODO 数据统计
    private void initDataStatistics(){

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DataStatisticsBean> clientCommissions = fzbInterface.getDataStatistics(FinalContents.getUserID(),FinalContents.getUserID(),type1,startDate1,endDate1);
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataStatisticsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataStatisticsBean dataStatisticsBean) {

                        market_time_tv2.setText(dataStatisticsBean.getData().getReportNumber()+"");
                        market_time_tv3.setText(dataStatisticsBean.getData().getAccessingNumber()+"");
                        market_time_tv4.setText(dataStatisticsBean.getData().getIsIslandNumber()+"");
                        market_time_tv5.setText(dataStatisticsBean.getData().getEarnestMoneyNumber()+"");
                        market_time_tv6.setText(dataStatisticsBean.getData().getTradeNumber()+"");
                        market_time_tv7.setText(dataStatisticsBean.getData().getInvalidNum()+"");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金、用户数量:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // TODO 成交TOP5单
    private void initDailyTurnover(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DailyTurnoverBean> clientCommissions = fzbInterface.getDailyTurnover(FinalContents.getUserID(),FinalContents.getUserID(),state,type3,startDate3,endDate3);
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyTurnoverBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(DailyTurnoverBean dailyTurnoverBean) {
                        if(dailyTurnoverBean.getData().size() == 0){
                            all_no_information_S.setVisibility(View.VISIBLE);
                        }else {
                            all_no_information_S.setVisibility(View.GONE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(Captain_Market_MyTeamActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            layoutManager.setScrollEnabled(false);
                            market_time_rv.setLayoutManager(layoutManager);
                            DailyTurnoverAdapter adapter = new DailyTurnoverAdapter(dailyTurnoverBean.getData());
                            market_time_rv.setNestedScrollingEnabled(false);
                            market_time_rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金、用户数量:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // TODO 团队佣金
    private void initTeamCommissions(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamCommissionsBean> clientCommissions = fzbInterface.getTeamCommissions(FinalContents.getUserID(),FinalContents.getUserID(),type2,startDate2,endDate2);
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamCommissionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamCommissionsBean teamCommissionsBean) {
                        market_time_tv8.setText(teamCommissionsBean.getData().getTotalAmount()+"");
                        market_time_tv9.setText(teamCommissionsBean.getData().getAlreadyAmount()+"");
                        market_time_tv10.setText(teamCommissionsBean.getData().getNotAmount()+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金、用户数量:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    // TODO 我的团队显示数据
    private void initMyTeamData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MyTeamBean> clientCommissions = fzbInterface.getMyTeam(FinalContents.getUserID());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTeamBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MyTeamBean myTeamBean) {
                        market_time_tv1.setText(myTeamBean.getData().getCounselorNum()+"");

                        market_time_tv2.setText(myTeamBean.getData().getDataStatistics().getReportNumber()+"");
                        market_time_tv3.setText(myTeamBean.getData().getDataStatistics().getAccessingNumber()+"");
                        market_time_tv4.setText(myTeamBean.getData().getDataStatistics().getIsIslandNumber()+"");
                        market_time_tv5.setText(myTeamBean.getData().getDataStatistics().getEarnestMoneyNumber()+"");
                        market_time_tv6.setText(myTeamBean.getData().getDataStatistics().getTradeNumber()+"");
                        market_time_tv7.setText(myTeamBean.getData().getDataStatistics().getInvalidNum()+"");

                        market_time_tv8.setText(myTeamBean.getData().getMoneyData().getTotalAmount()+"");
                        market_time_tv9.setText(myTeamBean.getData().getMoneyData().getAlreadyAmount()+"");
                        market_time_tv10.setText(myTeamBean.getData().getMoneyData().getNotAmount()+"");

                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(Captain_Market_MyTeamActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        market_time_rv.setLayoutManager(layoutManager);
                        MyTeamAdapter adapter = new MyTeamAdapter(myTeamBean.getData().getDailyTurnover());
                        market_time_rv.setNestedScrollingEnabled(false);
                        market_time_rv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金、用户数量:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NewlyIncreased.setTag("");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
    }
}
