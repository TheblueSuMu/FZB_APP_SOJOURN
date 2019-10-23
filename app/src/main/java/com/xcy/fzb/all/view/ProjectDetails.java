package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.CommissionRecycler;
import com.xcy.fzb.all.adapter.FamilyRecycler;
import com.xcy.fzb.all.adapter.ReportAdapter;
import com.xcy.fzb.all.adapter.TalktoolRecycler;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.CollectBean;
import com.xcy.fzb.all.modle.HouseBean;
import com.xcy.fzb.all.modle.ProjectDetailsBean;
import com.xcy.fzb.all.modle.RemindBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.persente.SharItOff;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;

import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectDetails extends AllActivity implements View.OnClickListener {
    private ImageView backimg;
    private LinearLayout back;
    private ImageView building;

    private TextView number;
    private TextView attention;
    private TextView transmit;
    private TextView amount;
    private TextView salestatus;
    private TagContainerLayout productfeature;
    private TextView name;
    private TextView location;
    private TextView toatl;
    private TextView unit;
    private TextView tary;
    private TextView areainterval;
    private TextView more;
    private TextView award;
    private TextView house_title;
    private TextView house_time;
    private TextView transmit_house;

    private TextView message;

    private TextView report;
    private CheckBox collect;
    private TextView call;

    private TabLayout tabLayout;


    private TextView repast;
    private TagContainerLayout repast_content;
    private TextView recuperate;
    private TextView recuperate_content;
    private TextView business;
    private TextView business_content;

    private RecyclerView report_rv;

    private RecyclerView commissionRv;
    private RecyclerView talktool;
    private RecyclerView family;

    private LinearLayout talktool_layout;
    private LinearLayout award_layout;
    private LinearLayout linear0;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private LinearLayout linear3;
    private LinearLayout linear4;
    private LinearLayout linear5;
    private LinearLayout linear6;
    private LinearLayout linear7;

    private TextView group_booking;


    private LinearLayout guowai1;
    private LinearLayout guowai3;
    private View guowai2;
    private String imagePath = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3055880154,1625749017&fm=26&gp=0.jpg";

    private TextView share;
    private TextView project_details_group_booking;

    private ProjectDetailsBean.DataBean projectDetailsBeanData;

    private TextView project_details_ding;
    private TextView project_details_kai;


    private String projectDetailsUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectDetails?";
    private String houseTypeUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectPropertyHouseList?";
    private String collectUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonUpdate/projectCollectSave?";
    private CollectBean.DataBean dataBean = new CollectBean.DataBean();
    private LinearLayout project_details_all;
    private TextView project_details_share_all;
    private CheckBox project_details_collect_all;
    private TextView project_details_qt_call;
    private LinearLayout project_details_qt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details);
        StatusBar.makeStatusBarTransparent(this);
        initfvb();
        init();

    }

    //命名区域
    private void initfvb() {
        project_details_kai = findViewById(R.id.project_details_kai);
        project_details_ding = findViewById(R.id.project_details_ding);
        project_details_qt_call = findViewById(R.id.project_details_qt_call);

        project_details_kai.setOnClickListener(this);
        project_details_ding.setOnClickListener(this);


        backimg = findViewById(R.id.project_details_backimg);
        back = findViewById(R.id.project_details_back);
        building = findViewById(R.id.project_details_building);

        number = findViewById(R.id.project_details_number);
        attention = findViewById(R.id.project_details_attention_num);
        transmit = findViewById(R.id.project_details_transmit_num);
        amount = findViewById(R.id.project_details_amount_num);
        salestatus = findViewById(R.id.project_details_salestatus);
        productfeature = findViewById(R.id.project_details_productfeature);
        name = findViewById(R.id.project_details_name);
        location = findViewById(R.id.project_details_location);
        toatl = findViewById(R.id.project_details_toatl);
        unit = findViewById(R.id.project_details_unit);
        tary = findViewById(R.id.project_details_tary);
        areainterval = findViewById(R.id.project_details_areainterval);
        more = findViewById(R.id.project_details_more);
        award = findViewById(R.id.project_details_award);
        house_title = findViewById(R.id.project_details_house_title);
        house_time = findViewById(R.id.project_details_house_time);
        transmit_house = findViewById(R.id.project_details_transmit_house);
        project_details_qt = findViewById(R.id.project_details_qt);

        project_details_group_booking = findViewById(R.id.project_details_group_booking);

        guowai1 = findViewById(R.id.guowai1);
        guowai3 = findViewById(R.id.guowai3);
        guowai2 = findViewById(R.id.guowai2);
        call = findViewById(R.id.project_details_call);
        report = findViewById(R.id.project_details_report);
        collect = findViewById(R.id.project_details_collect);

        commissionRv = findViewById(R.id.project_details_commission);
        talktool = findViewById(R.id.project_details_talktool);
        family = findViewById(R.id.project_details_family);

        report_rv = findViewById(R.id.project_details_report_rv);

        share = findViewById(R.id.project_details_share);

        tabLayout = findViewById(R.id.project_details_tab);
        message = findViewById(R.id.project_details_message);

        repast = findViewById(R.id.project_details_repast);
        repast_content = findViewById(R.id.project_details_repast_content);
        recuperate = findViewById(R.id.project_details_recuperate);
        recuperate_content = findViewById(R.id.project_details_recuperate_content);
        business = findViewById(R.id.project_details_business);
        business_content = findViewById(R.id.project_details_business_content);



        project_details_all = findViewById(R.id.project_details_all);
        project_details_share_all = findViewById(R.id.project_details_share_all);
        project_details_collect_all = findViewById(R.id.project_details_collect_all);



        linear0 = findViewById(R.id.linear0);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear5 = findViewById(R.id.linear5);
        linear6 = findViewById(R.id.linear6);
        linear7 = findViewById(R.id.linear7);

        group_booking = findViewById(R.id.project_details_group_booking);

        talktool_layout = findViewById(R.id.project_details_talktool_layout);
        award_layout = findViewById(R.id.project_details_award_layout);

        back.setOnClickListener(this);
        backimg.setOnClickListener(this);
        more.setOnClickListener(this);
        transmit_house.setOnClickListener(this);
        building.setOnClickListener(this);

        location.setOnClickListener(this);
        project_details_group_booking.setOnClickListener(this);
        project_details_qt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + projectDetailsBeanData.getProjectListVo().getFfAttacheList().get(0).getPhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });



        if (FinalContents.getZhuanAn().equals("1")) {
            project_details_qt.setVisibility(View.VISIBLE);
            project_details_all.setVisibility(View.GONE);
        }

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder2 = new Retrofit.Builder();
                builder2.baseUrl(FinalContents.getBaseUrl());
                builder2.addConverterFactory(GsonConverterFactory.create());
                builder2.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build2 = builder2.build();
                MyService fzbInterface2 = build2.create(MyService.class);
                Observable<CollectBean> collectBean1 = fzbInterface2.getCollectBean(FinalContents.getProjectID(), FinalContents.getUserID(), "1");
                collectBean1.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<CollectBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(CollectBean collectBean) {
                                dataBean = collectBean.getData();
                                if (dataBean.getStatus().equals("1")) {
                                    collect.setChecked(true);
                                    Toast.makeText(ProjectDetails.this, dataBean.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    collect.setChecked(false);
                                    Toast.makeText(ProjectDetails.this, dataBean.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        });


        project_details_collect_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder2 = new Retrofit.Builder();
                builder2.baseUrl(FinalContents.getBaseUrl());
                builder2.addConverterFactory(GsonConverterFactory.create());
                builder2.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build2 = builder2.build();
                MyService fzbInterface2 = build2.create(MyService.class);
                Observable<CollectBean> collectBean1 = fzbInterface2.getCollectBean(FinalContents.getProjectID(), FinalContents.getUserID(), "1");
                collectBean1.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<CollectBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(CollectBean collectBean) {
                                dataBean = collectBean.getData();
                                if (dataBean.getStatus().equals("1")) {
                                    collect.setChecked(true);
                                    Toast.makeText(ProjectDetails.this, dataBean.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    collect.setChecked(false);
                                    Toast.makeText(ProjectDetails.this, dataBean.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + projectDetailsBeanData.getProjectListVo().getFfAttacheList().get(0).getPhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });
        tabLayout.addTab(tabLayout.newTab().setText("交通出行"), true);
        tabLayout.addTab(tabLayout.newTab().setText("教育教学"));
        tabLayout.addTab(tabLayout.newTab().setText("医疗健康"));
        tabLayout.addTab(tabLayout.newTab().setText("商场购物"));
        tabLayout.addTab(tabLayout.newTab().setText("生活娱乐"));
        tabLayout.addTab(tabLayout.newTab().setText("著名景点"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //                添加选中Tab的逻辑
                if (tab.getText().equals("交通出行")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getTraffic());
                } else if (tab.getText().equals("教育教学")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEducation());
                } else if (tab.getText().equals("医疗健康")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getMedical());
                } else if (tab.getText().equals("商场购物")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getShopping());
                } else if (tab.getText().equals("生活娱乐")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEntertainment());
                } else if (tab.getText().equals("著名景点")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getFamousScene());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //                添加未选中Tab的逻辑
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //                再次选中tab的逻辑
                if (tab.getText().equals("交通出行")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getTraffic());
                } else if (tab.getText().equals("教育健康")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEducation());
                } else if (tab.getText().equals("医疗健康")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getMedical());
                } else if (tab.getText().equals("商场购物")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getShopping());
                } else if (tab.getText().equals("生活娱乐")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEntertainment());
                } else if (tab.getText().equals("著名景点")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getFamousScene());
                }
            }
        });
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.project_details_back:
                finish();
                break;
            case R.id.project_details_backimg:
                Intent photointent = new Intent(this, PhotoTileActivity.class);
                startActivity(photointent);
                break;
            case R.id.project_details_more:
                if (projectDetailsBeanData.getProjectListVo().getFfAttacheList().size() != 0) {
                    FinalContents.setIPhone(projectDetailsBeanData.getProjectListVo().getFfAttacheList().get(0).getPhone());
                }
                FinalContents.setProjectName(projectDetailsBeanData.getProjectListVo().getProjectName());
                FinalContents.setProjectSearchID(projectDetailsBeanData.getProjectListVo().getProjectId());
                FinalContents.setGuideRuleId(projectDetailsBeanData.getProjectListVo().getGuideRuleId());
                FinalContents.setProjectID(projectDetailsBeanData.getProjectListVo().getProjectId());
                Intent moreInformationintent = new Intent(this, MoreInformationActivity.class);
                startActivity(moreInformationintent);
                break;
            case R.id.project_details_transmit_house:
                Intent transmitintent = new Intent(this, BuildingDynamicActivity.class);
                startActivity(transmitintent);
                break;
            case R.id.project_details_building:
                Intent buildingInformationintent = new Intent(this, BuildingInformationActivity.class);
                buildingInformationintent.putExtra("pic", "http://39.98.173.250:8080" + projectDetailsBeanData.getProjectListVo().getProjectImg());
                startActivity(buildingInformationintent);
                break;
            case R.id.project_details_location:
                Intent mapintent = new Intent(this, MapActivity.class);
                mapintent.putExtra("office", "1");
                startActivity(mapintent);
                break;
            case R.id.project_details_group_booking:
                Intent intents = new Intent(ProjectDetails.this, SpellingMassTimeActivity.class);
                startActivity(intents);
                break;
            case R.id.project_details_ding:
                initRemind(1);
                break;
            case R.id.project_details_kai:
                initRemind(2);
                break;
        }
    }

    // 定价通知
    private void initRemind(final int i) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        final Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<RemindBean> remindBean = fzbInterface.getRemindBean(FinalContents.getProjectID(), i + "", FinalContents.getUserID());
        remindBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RemindBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final RemindBean remindBean) {

                        if(remindBean.getMsg().equals("成功")){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(ProjectDetails.this);
                            if(i == 1){
                                builder1.setTitle("变价提醒");
                            }else {
                                builder1.setTitle("开盘提醒");
                            }
                            builder1.setMessage(remindBean.getData().getMessage());
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onClick(DialogInterface dialogInterface, int posotion) {
                                    if(i == 1){
                                        if (remindBean.getData().getMessage().equals("取消订阅")) {
                                            project_details_ding.setTextColor(R.color.colorAppBar);
                                        } else if (remindBean.getData().getMessage().equals("订阅成功")){
                                            project_details_ding.setTextColor(R.color.textcolor3);
                                        }
                                    }else {
                                        if (remindBean.getData().getMessage().equals("取消订阅")) {
                                            project_details_kai.setTextColor(R.color.colorAppBar);
                                        } else if (remindBean.getData().getMessage().equals("订阅成功")){
                                            project_details_kai.setTextColor(R.color.textcolor3);
                                        }
                                    }
                                }
                            });
                            builder1.show();
                        }else {
                            Toast.makeText(ProjectDetails.this,remindBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL","提醒通知错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @SuppressLint({"CheckResult", "WrongConstant"})
    private void init() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ProjectDetailsBean> projectDetailsBean = fzbInterface.getProjectDetailsBean(FinalContents.getUserID(), FinalContents.getProjectID());
        projectDetailsBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onNext(final ProjectDetailsBean projectDetailsBean) {
                        projectDetailsBeanData = projectDetailsBean.getData();
                        Log.i("wsm", "fdsafd:" + projectDetailsBeanData.getProjectListVo().getProjectImg());
                        Glide.with(ProjectDetails.this).load("http://39.98.173.250:8080" + projectDetailsBean.getData().getProjectListVo().getProjectImg()).into(backimg);
                        if (projectDetailsBeanData.getProjectListVo().getReferenceToatlPrice().equals("")) {
                            guowai1.setVisibility(View.GONE);
                            guowai2.setVisibility(View.GONE);
                            guowai3.setGravity(Gravity.CENTER);
                        } else {
                            guowai1.setVisibility(View.VISIBLE);
                            guowai2.setVisibility(View.VISIBLE);
                            guowai3.setGravity(Gravity.RIGHT);
                        }

                        if (FinalContents.getZyHome().equals("1")) {
                            group_booking.setVisibility(View.GONE);
                        } else {
                            if (projectDetailsBeanData.getProjectListVo().getIsgroup().equals("1")) {
                                group_booking.setVisibility(View.VISIBLE);
                            } else {
                                group_booking.setVisibility(View.GONE);
                            }
                        }

                        message.setText(projectDetailsBeanData.getMatingInformation().getTraffic());

                        report.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FinalContents.setChecked2(true);
                                FinalContents.setGuideRuleId(projectDetailsBeanData.getProjectListVo().getGuideRuleId());
                                FinalContents.setProjectName(projectDetailsBeanData.getProjectListVo().getProjectName());
                                FinalContents.setProjectID(projectDetailsBeanData.getProjectListVo().getProjectId());
                                Intent intent = new Intent(ProjectDetails.this, ReportActivity.class);
                                startActivity(intent);
                            }
                        });

                        if (projectDetailsBean.getData().getProjectListVo().getIsCollection().equals("0")) {
                            collect.setChecked(false);
                            project_details_collect_all.setChecked(false);
                        } else if (projectDetailsBean.getData().getProjectListVo().getIsCollection().equals("1")) {
                            collect.setChecked(true);
                            project_details_collect_all.setChecked(true);
                        }

                        if (projectDetailsBean.getData().getProjectListVo().getRemindstatus1().equals("1")) {
                            project_details_ding.setTextColor(R.color.textcolor3);
                        } else if (projectDetailsBean.getData().getProjectListVo().getRemindstatus1().equals("0")) {
                            project_details_ding.setTextColor(R.color.colorAppBar);
                        }

                        if (projectDetailsBean.getData().getProjectListVo().getRemindstatus2().equals("1")) {
                            project_details_kai.setTextColor(R.color.textcolor3);
                        } else if (projectDetailsBean.getData().getProjectListVo().getRemindstatus2().equals("0")) {
                            project_details_kai.setTextColor(R.color.colorAppBar);
                        }

                        number.setText(projectDetailsBeanData.getHouseImgCountNum() + "张");
                        attention.setText(projectDetailsBeanData.getProjectListVo().getBrowseNum());
                        transmit.setText(projectDetailsBeanData.getProjectListVo().getForwardingAmount());
                        amount.setText(projectDetailsBeanData.getProjectListVo().getReportAmount());


                        //分享
                        share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FinalContents.showShare(projectDetailsBean.getData().getProjectListVo().getProjectName(),"http://test.fangzuobiao.com:88/?userId="+FinalContents.getUserID()+"&id="+FinalContents.getProjectID(),projectDetailsBean.getData().getProjectListVo().getProductFeature(),"http://39.98.173.250:8080"+projectDetailsBean.getData().getProjectListVo().getProjectImg(),"http://test.fangzuobiao.com:88/?userId="+FinalContents.getUserID()+"&id="+FinalContents.getProjectID(),ProjectDetails.this);
                            }
                        });

                        project_details_share_all.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FinalContents.showShare(projectDetailsBean.getData().getProjectListVo().getProjectName(),"http://test.fangzuobiao.com:88/?userId="+FinalContents.getUserID()+"&id="+FinalContents.getProjectID(),projectDetailsBean.getData().getProjectListVo().getProductFeature(),"http://39.98.173.250:8080"+projectDetailsBean.getData().getProjectListVo().getProjectImg(),"http://test.fangzuobiao.com:88/?userId="+FinalContents.getUserID()+"&id="+FinalContents.getProjectID(),ProjectDetails.this);
                            }
                        });


                        //地图
                        String ids = projectDetailsBeanData.getProjectListVo().getLocation();//从pd里取出字符串
                        List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list
                        double d = Double.parseDouble(tags.get(0).toString());
                        double o = Double.parseDouble(tags.get(1).toString());
                        if (!projectDetailsBeanData.getProjectListVo().getSalesOfficeLocation().equals("")) {
                            String ids1 = projectDetailsBeanData.getProjectListVo().getSalesOfficeLocation();//从pd里取出字符串
                            List tags1 = Arrays.asList(ids1.split(","));//根据逗号分隔转化为list
                            double d1 = Double.parseDouble(tags1.get(0).toString());
                            double o1 = Double.parseDouble(tags1.get(1).toString());
                            FinalContents.setO1(o1);
                            FinalContents.setD1(d1);
                            Log.i("经纬度","查看经纬度"+FinalContents.getO1()+"---"+FinalContents.getD1());
                        }
                        FinalContents.setO(o);
                        FinalContents.setD(d);
                        Log.i("经纬度","查看项目详情经纬度"+FinalContents.getO()+"---"+FinalContents.getD());

                        //报备规则
                        if (projectDetailsBeanData.getGuideRules().size() == 0) {
                            linear1.setVisibility(View.GONE);
                        } else {
                            linear1.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layout = new MyLinearLayoutManager(ProjectDetails.this);
                            layout.setOrientation(LinearLayoutManager.VERTICAL);
                            layout.setScrollEnabled(false);
                            report_rv.setLayoutManager(layout);
                            ReportAdapter reportAdapter = new ReportAdapter(projectDetailsBeanData);
                            report_rv.setAdapter(reportAdapter);
                        }

                        String ids2 = projectDetailsBeanData.getProjectListVo().getProductFeature();//从pd里取出字符串
                        List tags2 = Arrays.asList(ids2.split(","));//根据逗号分隔转化为list
                        productfeature.setTheme(ColorFactory.NONE);
                        productfeature.setTags(tags2);
                        name.setText(projectDetailsBeanData.getProjectListVo().getProjectName());
                        location.setText(projectDetailsBeanData.getProjectListVo().getDetailAddress());
                        toatl.setText(projectDetailsBeanData.getProjectListVo().getProductTotalPrice());

                        unit.setText(projectDetailsBeanData.getProjectListVo().getProductUnitPrice());

                        tary.setText(projectDetailsBeanData.getProjectListVo().getMonetaryUnit());
                        areainterval.setText(projectDetailsBeanData.getProjectListVo().getAreaInterval());

                        if (FinalContents.getIdentity().equals("63")) {
                            linear2.setVisibility(View.GONE);
                        }else {
                            //佣金规则
                            if (projectDetailsBeanData.getAmountIncentiveList().size() == 0) {
                                linear2.setVisibility(View.GONE);
                            } else {
                                linear2.setVisibility(View.VISIBLE);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(ProjectDetails.this);
                                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                commissionRv.setLayoutManager(layoutManager);
                                CommissionRecycler commissionRecycler = new CommissionRecycler(projectDetailsBeanData);
                                commissionRv.setAdapter(commissionRecycler);
                            }
                        }

                        if (SharItOff.getShar().equals("显")) {
                            linear2.setVisibility(View.VISIBLE);
                            linear0.setVisibility(View.VISIBLE);

                        } else if (SharItOff.getShar().equals("隐")) {
                            linear2.setVisibility(View.GONE);
                            linear0.setVisibility(View.GONE);

                        }


                        if (FinalContents.getIdentity().equals("63")) {
                            linear3.setVisibility(View.GONE);
                        }else {
                            if (projectDetailsBeanData.getHousesDynamics().size() == 0) {
                                linear3.setVisibility(View.GONE);
                            } else {
                                linear3.setVisibility(View.VISIBLE);
                                house_title.setText(projectDetailsBeanData.getHousesDynamics().get(0).getContent());
                                house_time.setText(projectDetailsBeanData.getHousesDynamics().get(0).getCreateDate());
                            }
                        }

                        //户型信息
                        if (projectDetailsBeanData.getFamilyInfomations().size() == 0) {
                            linear4.setVisibility(View.GONE);
                        } else {
                            linear4.setVisibility(View.VISIBLE);
                            LinearLayoutManager layoutManager_family = new LinearLayoutManager(ProjectDetails.this);
                            layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                            family.setLayoutManager(layoutManager_family);
                            FamilyRecycler recyclerAdapter = new FamilyRecycler(projectDetailsBeanData.getFamilyInfomations());
                            switch (projectDetailsBeanData.getProjectListVo().getSaleStatus()) {
                                case "1":
                                    salestatus.setText("待售");
                                    recyclerAdapter.setSales("待售");
                                    break;
                                case "2":
                                    salestatus.setText("认筹");
                                    recyclerAdapter.setSales("认筹");
                                    break;
                                case "3":
                                    salestatus.setText("在售");
                                    recyclerAdapter.setSales("在售");
                                    break;
                                case "4":
                                    salestatus.setText("尾盘");
                                    recyclerAdapter.setSales("尾盘");
                                    break;
                                case "5":
                                    salestatus.setText("特房");
                                    recyclerAdapter.setSales("特房");
                                    break;
                            }
                            recyclerAdapter.setPrice("" + projectDetailsBeanData.getProjectListVo().getProductTotalPrice());
                            family.setAdapter(recyclerAdapter);
                        }


                        if (projectDetailsBeanData.getProjectListVo().getAwardRules().equals("")) {
                            award_layout.setVisibility(View.GONE);
                        } else {
                            award_layout.setVisibility(View.VISIBLE);
                            award.setText(projectDetailsBeanData.getProjectListVo().getAwardRules());
                        }

                        //拓客工具
                        if (projectDetailsBeanData.getIsTalkTool().equals("0")) {
                            talktool_layout.setVisibility(View.GONE);
                        } else {
                            talktool_layout.setVisibility(View.VISIBLE);
                            LinearLayoutManager layoutManager_talktool = new LinearLayoutManager(ProjectDetails.this);
                            layoutManager_talktool.setOrientation(LinearLayoutManager.VERTICAL);
                            talktool.setLayoutManager(layoutManager_talktool);
                            TalktoolRecycler talktoolRecycler = new TalktoolRecycler(projectDetailsBeanData.getTalkToolData());
                            talktool.setAdapter(talktoolRecycler);
                        }


                        if (projectDetailsBeanData.getBuildingImg().equals("")) {
                            linear5.setVisibility(View.GONE);
                        } else {
                            linear5.setVisibility(View.VISIBLE);
                            Glide.with(ProjectDetails.this).load("http://39.98.173.250:8080" + projectDetailsBeanData.getBuildingImg()).into(building);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("error","项目详情内数据错误信息："+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Retrofit.Builder builder1 = new Retrofit.Builder();
        builder1.baseUrl(FinalContents.getBaseUrl());
        builder1.addConverterFactory(GsonConverterFactory.create());
        builder1.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build1 = builder1.build();
        MyService fzbInterface1 = build1.create(MyService.class);
        Observable<HouseBean> houseBean = fzbInterface1.getHouseBean(FinalContents.getUserID(), FinalContents.getProjectID(), "0","1000");
        houseBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HouseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HouseBean houseBean) {
                        final HouseBean.DataBean houseDataData = houseBean.getData();
//
                        if (houseDataData.getPropertyHouseList().size() == 0) {
                            linear6.setVisibility(View.GONE);
                        } else {
                            linear6.setVisibility(View.VISIBLE);
                            String ids = houseDataData.getPropertyHouseList().get(0).getContent();//从pd里取出字符串
                            List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list

                            repast_content.setTheme(ColorFactory.NONE);
                            repast_content.setTags(tags);
                            if (houseDataData.getPropertyHouseList().size() == 1) {
                                repast.setText(houseDataData.getPropertyHouseList().get(0).getType());
                                repast.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                        intent.putExtra("title", houseDataData.getPropertyHouseList().get(0).getType());
                                        intent.putExtra("time", houseDataData.getPropertyHouseList().get(0).getCreateDate());
                                        intent.putExtra("content", houseDataData.getPropertyHouseList().get(0).getContent());
                                        startActivity(intent);
                                    }
                                });
                            } else if (houseDataData.getPropertyHouseList().size() == 2) {
                                repast.setText(houseDataData.getPropertyHouseList().get(0).getType());
                                repast.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                        intent.putExtra("title", houseDataData.getPropertyHouseList().get(0).getType());
                                        intent.putExtra("time", houseDataData.getPropertyHouseList().get(0).getCreateDate());
                                        intent.putExtra("content", houseDataData.getPropertyHouseList().get(0).getContent());
                                        startActivity(intent);
                                    }
                                });

                                recuperate.setText(houseDataData.getPropertyHouseList().get(1).getType());
                                recuperate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                        intent.putExtra("title", houseDataData.getPropertyHouseList().get(1).getType());
                                        intent.putExtra("time", houseDataData.getPropertyHouseList().get(1).getCreateDate());
                                        intent.putExtra("content", houseDataData.getPropertyHouseList().get(1).getContent());
                                        startActivity(intent);
                                    }
                                });
                                recuperate_content.setText(houseDataData.getPropertyHouseList().get(1).getContent());
                            } else if (houseDataData.getPropertyHouseList().size() == 3) {
                                repast.setText(houseDataData.getPropertyHouseList().get(0).getType());
                                repast.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                        intent.putExtra("title", houseDataData.getPropertyHouseList().get(0).getType());
                                        intent.putExtra("time", houseDataData.getPropertyHouseList().get(0).getCreateDate());
                                        intent.putExtra("content", houseDataData.getPropertyHouseList().get(0).getContent());
                                        startActivity(intent);
                                    }
                                });

                                recuperate.setText(houseDataData.getPropertyHouseList().get(1).getType());
                                recuperate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                        intent.putExtra("title", houseDataData.getPropertyHouseList().get(1).getType());
                                        intent.putExtra("time", houseDataData.getPropertyHouseList().get(1).getCreateDate());
                                        intent.putExtra("content", houseDataData.getPropertyHouseList().get(1).getContent());
                                        startActivity(intent);
                                    }
                                });
                                recuperate_content.setText(houseDataData.getPropertyHouseList().get(1).getContent());
                                business.setText(houseDataData.getPropertyHouseList().get(2).getType());
                                business.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                        intent.putExtra("title", houseDataData.getPropertyHouseList().get(2).getType());
                                        intent.putExtra("time", houseDataData.getPropertyHouseList().get(2).getCreateDate());
                                        intent.putExtra("content", houseDataData.getPropertyHouseList().get(2).getContent());
                                        startActivity(intent);
                                    }
                                });
                                business_content.setText(houseDataData.getPropertyHouseList().get(2).getContent());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
