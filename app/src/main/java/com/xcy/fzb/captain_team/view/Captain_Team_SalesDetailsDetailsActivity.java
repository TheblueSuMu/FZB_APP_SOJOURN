package com.xcy.fzb.captain_team.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.database.AgentDetailsBean;
import com.xcy.fzb.all.database.DataStatisticsBean;
import com.xcy.fzb.all.modle.TeamLeaderAmountBean;
import com.xcy.fzb.all.persente.SingleClick;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.ToastUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.captain_assistant.view.Assistant_Addteam_Activity;
import com.xcy.fzb.captain_team.adapter.Captain_Team_YongAdapter;

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

public class Captain_Team_SalesDetailsDetailsActivity extends AllActivity implements View.OnClickListener {
    private LinearLayout sales_details_details_ll1;
    private LinearLayout sales_details_details_ll2;
    private LinearLayout sales_details_details_ll3;
    private LinearLayout sales_details_details_ll4;
    private LinearLayout sales_details_details_ll5;
    private LinearLayout sales_details_details_ll6;
    private LinearLayout sales_details_details_ll7;

    private TextView sales_details_details_tv1;
    private TextView sales_details_details_tv2;
    private TextView sales_details_details_tv3;
    private TextView sales_details_details_tv4;
    private TextView sales_details_details_tv5;
    private TextView sales_details_details_tv6;
    private TextView sales_details_details_tv7;
    private TextView sales_details_details_tv8;
    private TextView sales_details_details_tv9;
    private TextView sales_details_details_type;
    private TextView sales_details_details_time1;
    private TextView sales_details_details_time2;
    private TextView sales_details_details_name;
    private TextView sales_details_details_tv10;
    private TextView sales_details_details_tv11;

    private RadioButton sales_details_details_rb1;
    private RadioButton sales_details_details_rb2;
    private RadioButton sales_details_details_rb3;
    private RadioButton sales_details_details_rb4;

    private ImageView sales_details_details_img;

    private CombinedChart combinedChart;
    private List<String> names = new ArrayList<>(); //折线名字集合
    private List<Integer> colour = new ArrayList<>();//折线颜色集合
    private List<String> indexList;


    private RelativeLayout sales_details_details_return;
    private RelativeLayout sales_details_details_amend;
    private Intent intent;
    private String startTime = "";
    private String endTime = "";
    private String type = "";
    private ImageView sales_details_details_xiugai;
    private AgentDetailsBean agentDetails;
    private PopupWindow p;
    private RecyclerView sales_details_details_rv;
    private LinearLayout sales_details_details_linear1;
    private ImageView sales_details_details_access;

    LinearLayout project_attache_ll1;
    LinearLayout project_attache_ll2;
    LinearLayout project_attache_ll3;
    LinearLayout project_attache_ll4;

    String tag = "1";
    private String string;
    private int year;
    private int month;
    private int dayOfMonth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_sales_details_details);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            NewlyIncreased.setTag("");
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
            initfvb();
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


    private void initfvb() {

        StatusBar.makeStatusBarTransparent(this);

        //      TODO    城市版
        sales_details_details_rv = findViewById(R.id.sales_details_details_rv);
        sales_details_details_linear1 = findViewById(R.id.sales_details_details_linear1);
        sales_details_details_access = findViewById(R.id.sales_details_details_access);

        project_attache_ll1 = findViewById(R.id.project_attache_ll1);
        project_attache_ll2 = findViewById(R.id.project_attache_ll2);
        project_attache_ll3 = findViewById(R.id.project_attache_ll3);
        project_attache_ll4 = findViewById(R.id.project_attache_ll4);

        project_attache_ll1.setOnClickListener(this);
        project_attache_ll3.setOnClickListener(this);

        //      TODO    城市版

        sales_details_details_ll1 = findViewById(R.id.sales_details_details_ll1);
        sales_details_details_ll2 = findViewById(R.id.sales_details_details_ll2);
        sales_details_details_ll3 = findViewById(R.id.sales_details_details_ll3);
        sales_details_details_ll4 = findViewById(R.id.sales_details_details_ll4);
        sales_details_details_ll5 = findViewById(R.id.sales_details_details_ll5);
        sales_details_details_ll6 = findViewById(R.id.sales_details_details_ll6);
        sales_details_details_ll7 = findViewById(R.id.sales_details_details_ll7);
        sales_details_details_return = findViewById(R.id.sales_details_details_return);
        sales_details_details_amend = findViewById(R.id.sales_details_details_amend);

        sales_details_details_xiugai = findViewById(R.id.sales_details_details_xiugai);

        sales_details_details_tv1 = findViewById(R.id.sales_details_details_tv1);
        sales_details_details_tv2 = findViewById(R.id.sales_details_details_tv2);
        sales_details_details_tv3 = findViewById(R.id.sales_details_details_tv3);
        sales_details_details_tv4 = findViewById(R.id.sales_details_details_tv4);
        sales_details_details_tv5 = findViewById(R.id.sales_details_details_tv5);
        sales_details_details_tv6 = findViewById(R.id.sales_details_details_tv6);
        sales_details_details_tv7 = findViewById(R.id.sales_details_details_tv7);
        sales_details_details_tv8 = findViewById(R.id.sales_details_details_tv8);
        sales_details_details_tv9 = findViewById(R.id.sales_details_details_tv9);
        sales_details_details_type = findViewById(R.id.sales_details_details_type);
        sales_details_details_time1 = findViewById(R.id.sales_details_details_time1);
        sales_details_details_time2 = findViewById(R.id.sales_details_details_time2);

        sales_details_details_name = findViewById(R.id.sales_details_details_name);
        sales_details_details_tv10 = findViewById(R.id.sales_details_details_tv10);
        sales_details_details_tv11 = findViewById(R.id.sales_details_details_tv11);

        sales_details_details_rb1 = findViewById(R.id.sales_details_details_rb1);
        sales_details_details_rb2 = findViewById(R.id.sales_details_details_rb2);
        sales_details_details_rb3 = findViewById(R.id.sales_details_details_rb3);
        sales_details_details_rb4 = findViewById(R.id.sales_details_details_rb4);

        sales_details_details_img = findViewById(R.id.sales_details_details_img);

        combinedChart = findViewById(R.id.sales_details_details_lc);

        sales_details_details_ll2.setOnClickListener(this);
        sales_details_details_ll3.setOnClickListener(this);
        sales_details_details_ll4.setOnClickListener(this);
        sales_details_details_ll5.setOnClickListener(this);
        sales_details_details_ll6.setOnClickListener(this);
        sales_details_details_ll7.setOnClickListener(this);

        sales_details_details_rb1.setOnClickListener(this);
        sales_details_details_rb2.setOnClickListener(this);
        sales_details_details_rb3.setOnClickListener(this);
        sales_details_details_rb4.setOnClickListener(this);

        sales_details_details_time1.setOnClickListener(this);
        sales_details_details_time2.setOnClickListener(this);

        sales_details_details_tv10.setOnClickListener(this);
        sales_details_details_tv11.setOnClickListener(this);

        sales_details_details_return.setOnClickListener(this);
        sales_details_details_amend.setOnClickListener(this);
        sales_details_details_xiugai.setOnClickListener(this);


        if (FinalContents.getIdentity().equals("60")) {
            if (FinalContents.getManageFlag().equals("0")) {
                sales_details_details_xiugai.setVisibility(View.GONE);
            } else {
                if (FinalContents.getXiaoShou().equals("不")) {
                    sales_details_details_xiugai.setVisibility(View.GONE);
                    sales_details_details_amend.setVisibility(View.GONE);
                } else if (FinalContents.getXiaoShou().equals("是")) {
                    sales_details_details_xiugai.setVisibility(View.VISIBLE);
                    sales_details_details_amend.setVisibility(View.VISIBLE);
                }
            }
        }
        initData();



        sales_details_details_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, CommissionDetailsActivity.class);
                startActivity(intent);
            }
        });

        sales_details_details_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + agentDetails.getData().getAgentInfo().getPhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });

    }

    private void init(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamLeaderAmountBean> clientCommissions = fzbInterface.getTeamLeaderAmount(FinalContents.getAgentId(),"");
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamLeaderAmountBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamLeaderAmountBean teamLeaderAmountBean) {
                        List<TeamLeaderAmountBean.DataBean> list = teamLeaderAmountBean.getData();
                        if (list.size() != 0) {
                            sales_details_details_linear1.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Captain_Team_SalesDetailsDetailsActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            sales_details_details_rv.setLayoutManager(linearLayoutManager);
                            Captain_Team_YongAdapter captain_team_yongAdapter = new Captain_Team_YongAdapter(list);
                            sales_details_details_rv.setAdapter(captain_team_yongAdapter);
                            captain_team_yongAdapter.notifyDataSetChanged();
                        }else {
                            sales_details_details_linear1.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "销售详情页佣金数据:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData() {
        Log.i("顾问", "从详情页获取的ID：" + FinalContents.getInforId());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<AgentDetailsBean> clientCommissions = fzbInterface.getAgentDetails(FinalContents.getUserID(), FinalContents.getInforId());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AgentDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AgentDetailsBean agentDetailsBean) {
                        agentDetails = agentDetailsBean;
                        sales_details_details_tv4.setText(agentDetailsBean.getData().getAgentDataStatistics().getReportNumber() + "");
                        sales_details_details_tv5.setText(agentDetailsBean.getData().getAgentDataStatistics().getAccessingNumber() + "");
                        sales_details_details_tv6.setText(agentDetailsBean.getData().getAgentDataStatistics().getIsIslandNumber() + "");
                        sales_details_details_tv7.setText(agentDetailsBean.getData().getAgentDataStatistics().getEarnestMoneyNumber() + "");
                        sales_details_details_tv8.setText(agentDetailsBean.getData().getAgentDataStatistics().getTradeNumber() + "");
                        sales_details_details_tv9.setText(agentDetailsBean.getData().getAgentDataStatistics().getInvalidNum() + "");

                        sales_details_details_name.setText(agentDetailsBean.getData().getAgentInfo().getName());
                        sales_details_details_tv2.setText(agentDetailsBean.getData().getAgentInfo().getPhone());
                        Glide.with(Captain_Team_SalesDetailsDetailsActivity.this).load(FinalContents.getImageUrl() + agentDetailsBean.getData().getAgentInfo().getPhoto()).into(sales_details_details_img);

                        FinalContents.setAgentId(agentDetailsBean.getData().getAgentInfo().getId());
                        if (FinalContents.getIdentity().equals("60")) {
                            Log.i("测试", "第一个显示");
                            if (FinalContents.getManageFlag().equals("0")) {
                                Log.i("测试", "第二个显示");
                                sales_details_details_xiugai.setVisibility(View.GONE);
                            } else {

                                Log.i("测试", "第二个不显示");
                                if (FinalContents.getXiaoShou().equals("不")) {
                                    Log.i("测试", "第三个显示");
                                    sales_details_details_xiugai.setVisibility(View.GONE);
                                    sales_details_details_amend.setVisibility(View.GONE);
                                } else if (FinalContents.getXiaoShou().equals("是")) {
                                    if (sales_details_details_tv10.getText().toString().equals("他的销售")) {
                                        sales_details_details_xiugai.setVisibility(View.GONE);
                                        sales_details_details_amend.setVisibility(View.GONE);
                                    }else if(sales_details_details_tv10.getText().toString().equals("他的顾问")){
                                        sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                        sales_details_details_amend.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    Log.i("测试", "第三个不显示");
                                    if (agentDetailsBean.getData().getAgentInfo().getIdentity().equals("61")) {
                                        Log.i("测试", "显示");
                                        sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                        sales_details_details_amend.setVisibility(View.VISIBLE);
                                    } else {
                                        Log.i("测试", "不显示");
                                        sales_details_details_xiugai.setVisibility(View.GONE);
                                        sales_details_details_amend.setVisibility(View.GONE);
                                    }
                                }
                            }
                        }

                        else if (FinalContents.getIdentity().equals("61")) {
                            Log.i("顾问", "第一个不显示");
                            if (FinalContents.getManageFlag().equals("0")) {
                                Log.i("顾问", "第二个显示");
                                sales_details_details_xiugai.setVisibility(View.GONE);
                            } else {
                                Log.i("顾问", "第二个不显示");
                                if (agentDetailsBean.getData().getAgentInfo().getIdentity().equals("62")) {
                                    Log.i("顾问", "显示");
                                    sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                    sales_details_details_amend.setVisibility(View.VISIBLE);
                                } else {
                                    Log.i("顾问", "不显示");
                                    sales_details_details_xiugai.setVisibility(View.GONE);
                                    sales_details_details_amend.setVisibility(View.GONE);
                                }

                            }
                        }

                        /**
                         * 20191104 团助 修改
                         */

                        else if (FinalContents.getIdentity().equals("63")){
                            Log.i("顾问","第一个不显示");
                            if(FinalContents.getManageFlag().equals("0")){
                                Log.i("顾问","第二个显示");
                                sales_details_details_xiugai.setVisibility(View.VISIBLE);
                            }else {
                                Log.i("顾问","第二个不显示");
                                if (agentDetailsBean.getData().getAgentInfo().getIdentity().equals("60")) {
                                    Log.i("顾问","显示");
                                    sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                    sales_details_details_amend.setVisibility(View.VISIBLE);
                                }else {
                                    Log.i("顾问","不显示");
                                    sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                    sales_details_details_amend.setVisibility(View.VISIBLE);
                                }

                            }
                        }

                        if (agentDetailsBean.getData().getAgentInfo().getType().equals("2")) {
                            sales_details_details_tv1.setText(agentDetailsBean.getData().getAgentInfo().getName() + "(" + agentDetailsBean.getData().getAgentInfo().getRatioName() + ")");
                            if (agentDetailsBean.getData().getAgentInfo().getCounselorNum().equals("")) {
                                if (agentDetailsBean.getData().getAgentInfo().getLeaderName().equals("")) {
                                    sales_details_details_tv3.setText("团队长：暂无  顾问：0人");
                                } else {
                                    sales_details_details_tv3.setText("团队长：" + agentDetailsBean.getData().getAgentInfo().getLeaderName() + "  顾问：0人");
                                }
                            } else {
                                if (agentDetailsBean.getData().getAgentInfo().getLeaderName().equals("")) {
                                    sales_details_details_tv3.setText("团队长：暂无  顾问：" + agentDetailsBean.getData().getAgentInfo().getCounselorNum() + "人");
                                } else {
                                    sales_details_details_tv3.setText("团队长：" + agentDetailsBean.getData().getAgentInfo().getLeaderName() + "  顾问：" + agentDetailsBean.getData().getAgentInfo().getCounselorNum() + "人");
                                }
                            }
                            sales_details_details_tv10.setText("他的顾问");
                        } else if (agentDetailsBean.getData().getAgentInfo().getType().equals("3")) {
                            sales_details_details_tv1.setText(agentDetailsBean.getData().getAgentInfo().getName() + "(" + agentDetailsBean.getData().getAgentInfo().getRatioName() + ")");
                            if (agentDetailsBean.getData().getAgentInfo().getSaleName().equals("")) {
                                if (agentDetailsBean.getData().getAgentInfo().getLeaderName().equals("")) {
                                    sales_details_details_tv3.setText("团队长：暂无  暂无销售");
                                } else {
                                    sales_details_details_tv3.setText("团队长：" + agentDetailsBean.getData().getAgentInfo().getLeaderName() + "  暂无销售");
                                }
                            } else {
                                if (agentDetailsBean.getData().getAgentInfo().getLeaderName().equals("")) {
                                    sales_details_details_tv3.setText("团队长：暂无  销售：" + agentDetailsBean.getData().getAgentInfo().getSaleName());
                                } else {
                                    sales_details_details_tv3.setText("团队长：" + agentDetailsBean.getData().getAgentInfo().getLeaderName() + "  销售：" + agentDetailsBean.getData().getAgentInfo().getSaleName());
                                }
                            }
                            sales_details_details_tv10.setText("他的销售");
                        } else if (agentDetailsBean.getData().getAgentInfo().getType().equals("1")) {
                            sales_details_details_tv1.setText(agentDetailsBean.getData().getAgentInfo().getName() + "(" + agentDetailsBean.getData().getAgentInfo().getLevelName() + ")");
                            if (agentDetailsBean.getData().getAgentInfo().getCounselorNum().equals("")) {
                                if (agentDetailsBean.getData().getAgentInfo().getSaleNum().equals("")) {
                                    sales_details_details_tv3.setText("销售：0 人  顾问：0人");
                                } else {
                                    sales_details_details_tv3.setText("销售：" + agentDetailsBean.getData().getAgentInfo().getSaleNum() + "人  顾问：0人");
                                }
                            } else {
                                if (agentDetailsBean.getData().getAgentInfo().getSaleNum().equals("")) {
                                    sales_details_details_tv3.setText("销售：0 人  顾问：" + agentDetailsBean.getData().getAgentInfo().getCounselorNum() + "人");
                                } else {
                                    sales_details_details_tv3.setText("销售：" + agentDetailsBean.getData().getAgentInfo().getSaleNum() + "人  顾问：" + agentDetailsBean.getData().getAgentInfo().getCounselorNum() + "人");
                                }
                            }
                            sales_details_details_tv10.setText("他的销售");
                        }

                        if (FinalContents.getIdentity().equals("60")) {
                            if (agentDetails.getData().getAgentInfo().getIdentity().equals("61")) {
                                sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                sales_details_details_amend.setVisibility(View.VISIBLE);
                            }else {
                                sales_details_details_xiugai.setVisibility(View.GONE);
                                sales_details_details_amend.setVisibility(View.GONE);
                            }
                        }

                        if (FinalContents.getIdentity().equals("63")) {
                            if (agentDetails.getData().getAgentInfo().getIdentity().equals("60")) {
                                sales_details_details_linear1.setVisibility(View.VISIBLE);
                                init();
                            }else {
                                sales_details_details_linear1.setVisibility(View.GONE);
                            }

                        }else {
                            sales_details_details_linear1.setVisibility(View.GONE);
                        }


                        if (agentDetailsBean.getData().getGsonOption().getSeries().get(0).getData().size() != 0) {
                            List<Integer> integers = agentDetailsBean.getData().getGsonOption().getSeries().get(0).getData();
                            indexList = agentDetailsBean.getData().getGsonOption().getXAxis().getData();
                            setData(integers);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "销售详情页第一次:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        if (NewlyIncreased.getTag().equals("3")) {
            NewlyIncreased.setStartDate(startTime);
            NewlyIncreased.setEndDate(endTime);
        }else {
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
        }

        if (NewlyIncreased.getYJType().equals("3")) {
            NewlyIncreased.setYJstartDate(startTime);
            NewlyIncreased.setYJendDate(endTime);
        }else {
            NewlyIncreased.setYJstartDate("");
            NewlyIncreased.setYJendDate("");
        }
        switch (view.getId()) {
            //      TODO 进入报备
            case R.id.sales_details_details_ll2:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
            //      TODO 进入到访
            case R.id.sales_details_details_ll3:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "2");
                startActivity(intent);
                break;
            //      TODO 进入登岛
            case R.id.sales_details_details_ll4:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "3");
                startActivity(intent);
                break;
            //      TODO 进入认筹
            case R.id.sales_details_details_ll5:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "4");
                startActivity(intent);
                break;
            //      TODO 进入成交
            case R.id.sales_details_details_ll6:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "5");
                startActivity(intent);
                break;
            //      TODO 进入失效
            case R.id.sales_details_details_ll7:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "6");
                startActivity(intent);
                break;
            //      TODO 数据统计 全部
            case R.id.sales_details_details_rb1:
                sales_details_details_ll1.setVisibility(View.GONE);
                type = "0";
                startTime = "";
                endTime = "";
                NewlyIncreased.setTag("0");
                initDataStatistics();
                break;
            //      TODO 数据统计 昨天
            case R.id.sales_details_details_rb2:
                sales_details_details_ll1.setVisibility(View.GONE);
                type = "1";
                startTime = "";
                endTime = "";
                NewlyIncreased.setTag("1");
                initDataStatistics();
                break;
            //      TODO 数据统计 七天
            case R.id.sales_details_details_rb3:
                sales_details_details_ll1.setVisibility(View.GONE);
                type = "2";
                startTime = "";
                endTime = "";
                NewlyIncreased.setTag("2");
                initDataStatistics();
                break;
            //      TODO 数据统计 自定义
            case R.id.sales_details_details_rb4:
                sales_details_details_ll1.setVisibility(View.VISIBLE);
                type = "3";
                startTime = string;
                endTime = string;
                initDate();
                NewlyIncreased.setTag("3");
                initDataStatistics();
                break;
            //      TODO 选择开始时间
            case R.id.sales_details_details_time1:
                initDate();
                break;
            //      TODO 选择结束时间
            case R.id.sales_details_details_time2:
                initDate();
                break;
            //      TODO 退出
            case R.id.sales_details_details_return:
                finish();
                break;
            //      TODO 进入修改编辑界面
            case R.id.sales_details_details_amend:
                if (FinalContents.getIdentity().equals("60")) {
                    finish();
                    FinalContents.setXiuGai("修改销售");
                    FinalContents.setOwnerId("");
                    Log.i("销售啊啊啊", "修改销售：" + FinalContents.getXiuGai());
                    Intent intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
                    startActivity(intent);
                    finish();
                } else if (FinalContents.getIdentity().equals("61")) {
                    finish();
                    FinalContents.setXiuGai("修改顾问");
                    FinalContents.setOwnerId("");
                    Log.i("销售啊啊啊", "修改gw：" + FinalContents.getXiuGai());
                    Intent intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddAConsultantActivity.class);
                    startActivity(intent);
                    finish();
                } else if (FinalContents.getIdentity().equals("63")) {
                    Log.i("团助端修改", "63");
                    if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                        Log.i("团助端修改", "1");
                        PopWindow1();
//                        FinalContents.setOwnerId("");
//                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Assistant_Addteam_Activity.class);
//                        FinalContents.setXiuGai("修改团队长");
//                        startActivity(intent);
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                        Log.i("团助端修改", "2");
                        PopWindow1();
//                        FinalContents.setOwnerId("");
//                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
//                        FinalContents.setXiuGai("修改销售");
//                        startActivity(intent);
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("3")) {
                        Log.i("团助端修改", "3");
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddAConsultantActivity.class);
                        FinalContents.setXiuGai("修改顾问");
                        startActivity(intent);
                        finish();
                    }
                }
                break;
            //      TODO 他的顾问
            case R.id.sales_details_details_tv10:
                if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                    finish();
                    FinalContents.setInforId(agentDetails.getData().getAgentInfo().getId());
                    Log.i("顾问", "销售从详情页获取的顾问ID：" + FinalContents.getInforId());
                    intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_SalesDetails.class);
                    intent.putExtra("searchName", "顾问");
                    startActivity(intent);
                } else if (agentDetails.getData().getAgentInfo().getType().equals("3")) {
                    finish();
                    FinalContents.setInforId(agentDetails.getData().getAgentInfo().getParentId());
                    Log.i("顾问", "从详情页获取的销售ID：" + FinalContents.getInforId());
                    intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_SalesDetailsDetailsActivity.class);
                    startActivity(intent);
                } else if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                    finish();
                    FinalContents.setInforId(agentDetails.getData().getAgentInfo().getId());
                    Log.i("顾问", "团队长从详情页获取的销售ID：" + FinalContents.getInforId());
                    intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_SalesDetails.class);
                    intent.putExtra("searchName", "销售");
                    startActivity(intent);
                }
                break;
            //      TODO 打电话
            case R.id.sales_details_details_tv11:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定拨打：" + sales_details_details_tv2.getText().toString());
                builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + sales_details_details_tv2.getText().toString()));//跳转到拨号界面，同时传递电话号码
                        startActivity(dialIntent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                break;
            //      TODO 修改
            case R.id.sales_details_details_xiugai:
                if (FinalContents.getIdentity().equals("60")) {
                    finish();
                    FinalContents.setXiuGai("修改销售");
                    FinalContents.setOwnerId("");
                    Log.i("销售啊啊啊", "修改销售：" + FinalContents.getXiuGai());
                    Intent intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
                    startActivity(intent);
                    finish();
                } else if (FinalContents.getIdentity().equals("61")) {
                    finish();
                    FinalContents.setXiuGai("修改顾问");
                    FinalContents.setOwnerId("");
                    Log.i("销售啊啊啊", "修改gw：" + FinalContents.getXiuGai());
                    Intent intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddAConsultantActivity.class);
                    startActivity(intent);
                    finish();
                } else if (FinalContents.getIdentity().equals("63")) {
                    /**
                     * 修改
                     */
                    Log.i("团助端修改", "63");
                    if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                        Log.i("团助端修改", "1");
                        PopWindow1();
//                        FinalContents.setOwnerId("");
//                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Assistant_Addteam_Activity.class);
//                        FinalContents.setXiuGai("修改团队长");
//                        startActivity(intent);
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                        Log.i("团助端修改", "2");
                        PopWindow1();
//                        FinalContents.setOwnerId("");
//                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
//                        FinalContents.setXiuGai("修改销售");
//                        startActivity(intent);
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("3")) {
                        Log.i("团助端修改", "3");
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddAConsultantActivity.class);
                        FinalContents.setXiuGai("修改顾问");
                        startActivity(intent);
                        finish();
                    }
                } else {

                }
                break;
            case R.id.project_attache_ll1://实时
                tag = "1";
                project_attache_ll2.setVisibility(View.VISIBLE);
                project_attache_ll4.setVisibility(View.INVISIBLE);
                sales_details_details_ll2.setClickable(true);
                sales_details_details_ll3.setClickable(true);
                sales_details_details_ll4.setClickable(true);
                sales_details_details_ll5.setClickable(true);
                sales_details_details_ll6.setClickable(true);
                sales_details_details_ll7.setClickable(true);
                if (sales_details_details_rb1.isChecked() == true) {
                    type = "0";
                    startTime = "";
                    endTime = "";
                    initDataStatistics();
                    NewlyIncreased.setTag("0");
                    sales_details_details_ll1.setVisibility(View.GONE);
                } else if (sales_details_details_rb2.isChecked() == true) {
                    type = "1";
                    startTime = "";
                    endTime = "";
                    initDataStatistics();
                    NewlyIncreased.setTag("1");
                    sales_details_details_ll1.setVisibility(View.GONE);
                } else if (sales_details_details_rb3.isChecked() == true) {
                    type = "2";
                    startTime = "";
                    endTime = "";
                    initDataStatistics();
                    NewlyIncreased.setTag("2");
                    sales_details_details_ll1.setVisibility(View.GONE);
                } else if (sales_details_details_rb4.isChecked() == true) {
                    type = "3";
                    startTime = string;
                    endTime = string;
                    initDate();
                    initDataStatistics();
                    NewlyIncreased.setTag("3");
                    sales_details_details_ll1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.project_attache_ll3://总体
                tag = "2";
                project_attache_ll2.setVisibility(View.INVISIBLE);
                project_attache_ll4.setVisibility(View.VISIBLE);
                sales_details_details_ll2.setClickable(false);
                sales_details_details_ll3.setClickable(false);
                sales_details_details_ll4.setClickable(false);
                sales_details_details_ll5.setClickable(false);
                sales_details_details_ll6.setClickable(false);
                sales_details_details_ll7.setClickable(false);
                if (sales_details_details_rb1.isChecked() == true) {
                    type = "0";
                    startTime = "";
                    endTime = "";
                    initDataStatistics();
                    NewlyIncreased.setTag("0");
                    sales_details_details_ll1.setVisibility(View.GONE);
                } else if (sales_details_details_rb2.isChecked() == true) {
                    type = "1";
                    startTime = "";
                    endTime = "";
                    initDataStatistics();
                    NewlyIncreased.setTag("1");
                    sales_details_details_ll1.setVisibility(View.GONE);
                } else if (sales_details_details_rb3.isChecked() == true) {
                    type = "2";
                    startTime = "";
                    endTime = "";
                    initDataStatistics();
                    NewlyIncreased.setTag("2");
                    sales_details_details_ll1.setVisibility(View.GONE);
                } else if (sales_details_details_rb4.isChecked() == true) {
                    type = "3";
                    startTime = string;
                    endTime = string;
                    initDate();
                    initDataStatistics();
                    NewlyIncreased.setTag("3");
                    sales_details_details_ll1.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    // TODO 数据统计
    private void initDataStatistics() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DataStatisticsBean> clientCommissions = fzbInterface.getDataStatistics(FinalContents.getUserID(), FinalContents.getAgentId(), type, startTime, endTime,tag);
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataStatisticsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataStatisticsBean dataStatisticsBean) {
                        sales_details_details_tv4.setText(dataStatisticsBean.getData().getReportNumber() + "");
                        sales_details_details_tv5.setText(dataStatisticsBean.getData().getAccessingNumber() + "");
                        sales_details_details_tv6.setText(dataStatisticsBean.getData().getIsIslandNumber() + "");
                        sales_details_details_tv7.setText(dataStatisticsBean.getData().getEarnestMoneyNumber() + "");
                        sales_details_details_tv8.setText(dataStatisticsBean.getData().getTradeNumber() + "");
                        sales_details_details_tv9.setText(dataStatisticsBean.getData().getInvalidNum() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "销售详情页数据统计:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 首页时间赋值
    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        sales_details_details_time1.setText("<" + string);
        sales_details_details_time2.setText("-" + string + " >");

        startTime = string;
        endTime = string;

        //            TODO 开始时间
        sales_details_details_time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTimePickerView1();
            }
        });

        //                TODO 结束时间
        sales_details_details_time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTimePickerView2();
            }
        });
    }

    //TODO 详情页 数据统计 开始时间
    private void initTimePickerView1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(Captain_Team_SalesDetailsDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                sales_details_details_time1.setText("<" + getTime2(date));
                startTime = getTime2(date);
                NewlyIncreased.setStartDate(getTime2(date));
                initDataStatistics();
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
    }

    //TODO 详情页 数据统计 结束时间
    private void initTimePickerView2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(Captain_Team_SalesDetailsDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                sales_details_details_time2.setText("-" + getTime2(date) + " >");
                endTime = getTime2(date);
                NewlyIncreased.setEndDate(getTime2(date));
                initDataStatistics();
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
    }

    //TODO 详情页趋势图数据填充
    private void setData(final List<Integer> list) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            values.add(new Entry(i, list.get(i)));
        }

        LineDataSet set1;

        if (combinedChart.getData() != null &&
                combinedChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) combinedChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            combinedChart.getData().notifyDataChanged();
            combinedChart.notifyDataSetChanged();
        } else {
            combinedChart.setDrawBorders(false); // 显示边界
            combinedChart.getDescription().setEnabled(false);  // 不显示备注信息
            combinedChart.setPinchZoom(false); // 比例缩放
            combinedChart.animateY(1500);
            combinedChart.setTouchEnabled(true);
            combinedChart.setDragEnabled(true);
            combinedChart.getLegend().setEnabled(false);
            combinedChart.setDoubleTapToZoomEnabled(false);
            combinedChart.setHighlightPerTapEnabled(false);
            combinedChart.getAxisRight().setEnabled(false);

            int max = 0;

            for (int i = 0;i < list.size();i++){
                if (list.get(i) > max) {
                    max = list.get(i);
                }
            }

            XAxis xAxis = combinedChart.getXAxis();
            xAxis.setDrawGridLines(false);
            /*解决左右两端柱形图只显示一半的情况 只有使用CombinedChart时会出现，如果单独使用BarChart不会有这个问题*/
            xAxis.setAxisMinimum(-0.2f);
            xAxis.setAxisMaximum(values.size() - 0.5f);
            xAxis.setGranularity(1f);
            xAxis.setTextColor(Color.parseColor("#666666"));
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置X轴标签位置，BOTTOM在底部显示，TOP在顶部显示
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    if (indexList.size() != 0) {
                        return indexList.get((int) value % indexList.size());
                    }else {
                        return "";
                    }
                }
            });

            YAxis axisLeft = combinedChart.getAxisLeft(); // 获取左边Y轴操作类
            axisLeft.setAxisMinimum(0); // 设置最小值
            axisLeft.setAxisMaximum(max+5); // 设置最大值
            axisLeft.setLabelCount(5); // 设置最大值
            axisLeft.setAxisLineColor(Color.parseColor("#00000000"));
            axisLeft.setTextColor(Color.parseColor("#999999"));


            List<Entry> lineEntries = new ArrayList<>();
            List<BarEntry> barEntries = new ArrayList<>();
            for (int i = 0; i < indexList.size(); i++) {
                lineEntries.add(new Entry(i, list.get(i)));
                barEntries.add(new BarEntry(i, list.get(i)));
            }

            /**
             * 初始化柱形图的数据
             * 此处用suppliers的数量做循环，因为总共所需要的数据源数量应该和标签个数一致
             * 其中BarEntry是柱形图数据源的实体类，包装xy坐标数据
             */
            /******************BarData start********************/

            BarDataSet barDataSet = new BarDataSet(barEntries, "LAR");  // 新建一组柱形图，"LAR"为本组柱形图的Label
            barDataSet.setColor(Color.parseColor("#6596ba")); // 设置柱形图颜色
            barDataSet.setDrawValues(false);
            BarData barData = new BarData();
            barData.addDataSet(barDataSet);// 添加一组柱形图，如果有多组柱形图数据，则可以多次addDataSet来设置
            barData.setBarWidth(0.1f);

            /******************BarData end********************/

            /**
             * 初始化折线图数据
             * 说明同上
             */
            /******************LineData start********************/

            LineDataSet lineDataSet = new LineDataSet(lineEntries, "不良率");
            lineDataSet.setColor(Color.parseColor("#ce7951"));
            lineDataSet.setCircleColor(Color.parseColor("#ce7951"));
            lineDataSet.setCircleHoleColor(Color.parseColor("#FFFFFF"));
            lineDataSet.setValueTextColor(Color.parseColor("#666666")); //  设置线形图顶部文本颜色
            lineDataSet.setLineWidth(1);
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            lineDataSet.setHighlightEnabled(false);
            lineDataSet.setCubicIntensity(0.2f);
            lineDataSet.setValueTextSize(10);
            lineDataSet.setDrawValues(true);
            LineData lineData = new LineData();
            lineData.addDataSet(lineDataSet);
            /******************LineData end********************/

            CombinedData combinedData = new CombinedData(); // 创建组合图的数据
            combinedData.setData(barData);  // 添加柱形图数据源
            combinedData.setData(lineData); // 添加折线图数据源
            if (indexList.size() > 5) {
                combinedChart.setVisibleXRange(0,5);
            }
            combinedChart.setData(combinedData); // 为组合图设置数据源
        }
        combinedChart.animateXY(2000, 2000);

    }

    /**
     * 新增
     */
    //TODO 弹窗
    private void PopWindow1() {
        final List<String> list = new ArrayList<>();
        list.add("修改信息");
        list.add("修改佣金");

        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_SalesDetailsDetailsActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                if (options1 == 0) {
                    if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Assistant_Addteam_Activity.class);
                        FinalContents.setXiuGai("修改团队长");
                        startActivity(intent);
                        finish();
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
                        FinalContents.setXiuGai("修改销售");
                        startActivity(intent);
                        finish();
                    }
                } else if (options1 == 1) {
                    if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_CommissionLevelActivity.class);
                        intent.putExtra("SearchIf", "销售");
                        intent.putExtra("SearchID", agentDetails.getData().getAgentInfo().getId());
                        startActivity(intent);
                        finish();
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_CommissionLevelActivity.class);
                        intent.putExtra("SearchIf", "顾问");
                        intent.putExtra("SearchID", agentDetails.getData().getAgentInfo().getId());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        })
                .setSelectOptions(2)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NewlyIncreased.setTag("");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
    }
}
