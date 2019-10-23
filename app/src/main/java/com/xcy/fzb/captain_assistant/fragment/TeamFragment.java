package com.xcy.fzb.captain_assistant.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.DailyTurnoverBean;
import com.xcy.fzb.all.database.DataStatisticsBean;
import com.xcy.fzb.all.database.TeamCommissionsBean;
import com.xcy.fzb.all.modle.MyTeam2Bean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.captain_assistant.adapter.MyTeam2Adapter;
import com.xcy.fzb.captain_assistant.view.Assistant_Teams_Activity;
import com.xcy.fzb.captain_team.adapter.DailyTurnoverAdapter;
import com.xcy.fzb.captain_team.view.Captain_Team_CommissionTheProjectEndActivity;
import com.xcy.fzb.captain_team.view.Captain_Team_MyClientActivity;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment implements View.OnClickListener {

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
    LinearLayout market_time_ll13;
    LinearLayout market_time_ll14;
    LinearLayout market_time_ll15;
    LinearLayout market_time_ll16;

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
    TextView market_time_tv11;
    TextView market_time_tv12;
    TextView market_time_tv13;
    TextView market_time_tv14;

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

    RelativeLayout market_time_rl1;
    RelativeLayout market_time_rl2;
    RelativeLayout market_time_rl3;

    RecyclerView market_time_rv;

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

    String state = "2";

    String type3 = "";
    String startDate3 = "";
    String endDate3 = "";

    public TeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }
    //  TODO 声明
    private void initView() {

        StatusBar.makeStatusBarTransparent(getActivity());

        market_time_ll1 = getActivity().findViewById(R.id.team_ll1);
        market_time_ll2 = getActivity().findViewById(R.id.team_ll2);
        market_time_ll3 = getActivity().findViewById(R.id.team_ll3);
        market_time_ll4 = getActivity().findViewById(R.id.team_ll4);
        market_time_ll5 = getActivity().findViewById(R.id.team_ll5);
        market_time_ll6 = getActivity().findViewById(R.id.team_ll6);
        market_time_ll7 = getActivity().findViewById(R.id.team_ll7);
        market_time_ll8 = getActivity().findViewById(R.id.team_ll8);
        market_time_ll9 = getActivity().findViewById(R.id.team_ll9);
        market_time_ll10 = getActivity().findViewById(R.id.team_ll10);
        market_time_ll11 = getActivity().findViewById(R.id.team_ll11);
        market_time_ll12 = getActivity().findViewById(R.id.team_ll12);
        market_time_ll13 = getActivity().findViewById(R.id.team_ll13);
        market_time_ll14 = getActivity().findViewById(R.id.team_ll14);
        market_time_ll15 = getActivity().findViewById(R.id.team_ll15);
        market_time_ll16 = getActivity().findViewById(R.id.team_ll16);

        market_time_tv1 = getActivity().findViewById(R.id.team_tv1);
        market_time_tv2 = getActivity().findViewById(R.id.team_tv2);
        market_time_tv3 = getActivity().findViewById(R.id.team_tv3);
        market_time_tv4 = getActivity().findViewById(R.id.team_tv4);
        market_time_tv5 = getActivity().findViewById(R.id.team_tv5);
        market_time_tv6 = getActivity().findViewById(R.id.team_tv6);
        market_time_tv7 = getActivity().findViewById(R.id.team_tv7);
        market_time_tv8 = getActivity().findViewById(R.id.team_tv8);
        market_time_tv9 = getActivity().findViewById(R.id.team_tv9);
        market_time_tv10 = getActivity().findViewById(R.id.team_tv10);
        market_time_tv11 = getActivity().findViewById(R.id.team_tv11);
        market_time_tv12 = getActivity().findViewById(R.id.team_tv12);
        market_time_tv13 = getActivity().findViewById(R.id.team_tv13);
        market_time_tv14 = getActivity().findViewById(R.id.team_tv14);

        market_time_time_tv1 = getActivity().findViewById(R.id.team_time_tv1);
        market_time_time_tv2 = getActivity().findViewById(R.id.team_time_tv2);
        market_time_time_tv3 = getActivity().findViewById(R.id.team_time_tv3);
        market_time_time_tv4 = getActivity().findViewById(R.id.team_time_tv4);
        market_time_time_tv5 = getActivity().findViewById(R.id.team_time_tv5);
        market_time_time_tv6 = getActivity().findViewById(R.id.team_time_tv6);

        market_time_rg1 = getActivity().findViewById(R.id.team_rg1);
        market_time_rg2 = getActivity().findViewById(R.id.team_rg2);
        market_time_rg3 = getActivity().findViewById(R.id.team_rg3);

        market_time_rb1 = getActivity().findViewById(R.id.team_rb1);
        market_time_rb2 = getActivity().findViewById(R.id.team_rb2);
        market_time_rb3 = getActivity().findViewById(R.id.team_rb3);
        market_time_rb4 = getActivity().findViewById(R.id.team_rb4);
        market_time_rb5 = getActivity().findViewById(R.id.team_rb5);
        market_time_rb6 = getActivity().findViewById(R.id.team_rb6);
        market_time_rb7 = getActivity().findViewById(R.id.team_rb7);
        market_time_rb8 = getActivity().findViewById(R.id.team_rb8);
        market_time_rb9 = getActivity().findViewById(R.id.team_rb9);
        market_time_rb10 = getActivity().findViewById(R.id.team_rb10);
        market_time_rb11 = getActivity().findViewById(R.id.team_rb11);
        market_time_rb12 = getActivity().findViewById(R.id.team_rb12);

        market_time_rl1 = getActivity().findViewById(R.id.team_rl1);
        market_time_rl2 = getActivity().findViewById(R.id.team_rl2);
        market_time_rl3 = getActivity().findViewById(R.id.team_rl3);

        market_time_rv = getActivity().findViewById(R.id.team_rv);


        dateTimePickerView = getActivity().findViewById(R.id.team_pickerView);
        report_picker = getActivity().findViewById(R.id.team_picker);
        report_cancel = getActivity().findViewById(R.id.team_cancel);
        report_ensure = getActivity().findViewById(R.id.team_ensure);

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

        dateTimePickerView.setStartDate(Calendar.getInstance());
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));

        market_time_rl1.setOnClickListener(this);
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
        market_time_ll13.setOnClickListener(this);
        market_time_ll15.setOnClickListener(this);
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
        market_time_rl3.setOnClickListener(this);



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
//            TODO 团队长（上）
            case R.id.team_rl3:
                intent = new Intent(getContext(), Assistant_Teams_Activity.class);
                intent.putExtra("Iftz","1");
                startActivity(intent);
                break;
//            TODO 销售（上）
            case R.id.team_rl1:
                intent = new Intent(getContext(), Assistant_Teams_Activity.class);
                intent.putExtra("Iftz","2");
                startActivity(intent);
                break;
//            TODO 顾问（上）
            case R.id.team_rl2:
                intent = new Intent(getContext(),Assistant_Teams_Activity.class);
                intent.putExtra("Iftz","3");
                startActivity(intent);
                break;
//            TODO 报备
            case R.id.team_ll1:
                intent = new Intent(getContext(), Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client","1");
                startActivity(intent);
                break;
//            TODO 到访
            case R.id.team_ll2:
                intent = new Intent(getContext(),Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client","2");
                startActivity(intent);
                break;
//            TODO 登岛
            case R.id.team_ll3:
                intent = new Intent(getContext(),Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client","3");
                startActivity(intent);
                break;
//            TODO 认筹
            case R.id.team_ll4:
                intent = new Intent(getContext(),Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client","4");
                startActivity(intent);
                break;
//            TODO 成交
            case R.id.team_ll5:
                intent = new Intent(getContext(),Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client","5");
                startActivity(intent);
                break;
//            TODO 失效
            case R.id.team_ll6:
                intent = new Intent(getContext(),Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client","6");
                startActivity(intent);
                break;
//            TODO 总佣金
            case R.id.team_ll7:
                intent = new Intent(getContext(), Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 已结
            case R.id.team_ll8:
                intent = new Intent(getContext(),Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 未结
            case R.id.team_ll9:
                intent = new Intent(getContext(),Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 销售（下）
            case R.id.team_ll13:
                market_time_ll14.setVisibility(View.VISIBLE);
                market_time_ll16.setVisibility(View.GONE);
                market_time_tv12.setTextColor(Color.parseColor("#334485"));
                market_time_tv13.setTextColor(Color.parseColor("#333333"));
                state = "2";
                initDailyTurnover();
                break;
//            TODO 顾问（下）
            case R.id.team_ll15:
                market_time_ll14.setVisibility(View.GONE);
                market_time_ll16.setVisibility(View.VISIBLE);
                market_time_tv13.setTextColor(Color.parseColor("#334485"));
                market_time_tv12.setTextColor(Color.parseColor("#333333"));
                state = "3";
                initDailyTurnover();
                break;
//            TODO 时间选择1
            case R.id.team_time_tv1:

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
                    }
                });

                break;
//            TODO 时间选择2
            case R.id.team_time_tv2:
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
                    }
                });
                initDataStatistics();
                break;
//            TODO 时间选择3
            case R.id.team_time_tv3:
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
            case R.id.team_time_tv4:
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
            case R.id.team_time_tv5:
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
            case R.id.team_time_tv6:
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
            case R.id.team_rb1:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "0";
                startDate1 = "";
                endDate1 = "";
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 昨天
            case R.id.team_rb2:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "1";
                startDate1 = "";
                endDate1 = "";
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 七天
            case R.id.team_rb3:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "2";
                startDate1 = "";
                endDate1 = "";
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 自定义
            case R.id.team_rb4:
                market_time_ll10.setVisibility(View.VISIBLE);
                type1 = "3";
                break;

            //            TODO 财务数据 时间选择 全部
            case R.id.team_rb5:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "0";
                startDate2 = "";
                endDate2 = "";
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 昨天
            case R.id.team_rb6:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "1";
                startDate2 = "";
                endDate2 = "";
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 七天
            case R.id.team_rb7:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "2";
                startDate2 = "";
                endDate2 = "";
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 自定义
            case R.id.team_rb8:
                market_time_ll11.setVisibility(View.VISIBLE);
                type2 = "3";
                break;

            //            TODO 成交TOP5 时间选择 全部
            case R.id.team_rb9:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "0";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 昨天
            case R.id.team_rb10:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "1";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 七天
            case R.id.team_rb11:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "2";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 自定义
            case R.id.team_rb12:
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
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        market_time_rv.setLayoutManager(layoutManager);
                        DailyTurnoverAdapter adapter = new DailyTurnoverAdapter(dailyTurnoverBean.getData());
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
        Observable<MyTeam2Bean> clientCommissions = fzbInterface.getMyTeam2(FinalContents.getUserID());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTeam2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MyTeam2Bean myTeamBean) {
                        market_time_tv14.setText(myTeamBean.getData().getLeaderNum()+"");

                        market_time_tv11.setText(myTeamBean.getData().getSalesNum()+"");
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

                        FinalContents.setAgentId(FinalContents.getUserID());

                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        market_time_rv.setLayoutManager(layoutManager);
                        MyTeam2Adapter adapter = new MyTeam2Adapter(myTeamBean.getData().getDailyTurnover());
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
}
