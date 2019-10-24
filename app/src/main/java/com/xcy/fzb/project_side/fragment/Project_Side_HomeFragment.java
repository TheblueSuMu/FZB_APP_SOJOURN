package com.xcy.fzb.project_side.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.AllFragment;
import com.xcy.fzb.all.modle.HomeBean;
import com.xcy.fzb.all.modle.SideHomeBean;
import com.xcy.fzb.all.modle.UserBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.project_side.adapter.Project_Side_HomeRecyclerAdapter;
import com.xcy.fzb.project_side.view.CheckPendingTheProjectEndActivity;
import com.xcy.fzb.project_side.view.CommissionTheProjectEndActivity;
import com.xcy.fzb.project_side.view.InitiatedTheReviewActivity;
import com.xcy.fzb.project_side.view.MyClientActivity;
import com.xcy.fzb.project_side.view.MyProjectActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
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
public class Project_Side_HomeFragment extends AllFragment implements View.OnClickListener {

    PtrClassicFrameLayout mPtrClassicFrameLayout;

    RecyclerView rv_home_the_project_end;

    ImageView img_home_the_project_end;
    TextView name_home_the_project_end;
    TextView city_home_the_project_end;
    TextView position_home_the_project_end;
    TextView shop_home_the_project_end;

    RadioGroup rg_home_the_project_end;
    RadioButton rb1_home_the_project_end;
    RadioButton rb2_home_the_project_end;
    RadioButton rb3_home_the_project_end;
    RadioButton rb4_home_the_project_end;

    TextView time1_home_the_project_end;
    TextView time2_home_the_project_end;

    TextView tv1_home_the_project_end;
    TextView tv2_home_the_project_end;
    TextView tv3_home_the_project_end;
    TextView tv5_home_the_project_end;
    TextView tv6_home_the_project_end;
    TextView tv7_home_the_project_end;
    TextView tv8_home_the_project_end;
    TextView tv9_home_the_project_end;
    TextView tv10_home_the_project_end;
    TextView tv11_home_the_project_end;
    TextView tv12_home_the_project_end;
    TextView tv13_home_the_project_end;
    TextView tv14_home_the_project_end;

    TextView home_picker_cancel;
    TextView home_picker_ensure;


    LinearLayout time1_ll1_home_the_project_end;
    LinearLayout home_picker;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    LinearLayout layout7;
    LinearLayout layout8;
    LinearLayout layout9;
    LinearLayout layout10;
    LinearLayout AuditList;
    LinearLayout myproject;
    LinearLayout mypost;


    DateTimePickerView dateTimePickerView;

    private Intent intent;
    private View view;

    private String type = "";
    private String beforeDate = "";
    private String afterDate = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.project_side_fragment_home_the_project_end, container, false);
        initfvb();
        return view;
    }

    //TODO 声明
    private void initfvb() {
        StatusBar.makeStatusBarTransparent(getActivity());

        mPtrClassicFrameLayout = view.findViewById(R.id.store_house_ptr_frame);

        FinalContents.setTiaozhuang("");

        img_home_the_project_end = view.findViewById(R.id.img_home_the_project_end);
        name_home_the_project_end = view.findViewById(R.id.name_home_the_project_end);
        city_home_the_project_end = view.findViewById(R.id.city_home_the_project_end);
        position_home_the_project_end = view.findViewById(R.id.position_home_the_project_end);
        shop_home_the_project_end = view.findViewById(R.id.shop_home_the_project_end);
        rg_home_the_project_end = view.findViewById(R.id.rg_home_the_project_end);
        rb1_home_the_project_end = view.findViewById(R.id.rb1_home_the_project_end);
        rb2_home_the_project_end = view.findViewById(R.id.rb2_home_the_project_end);
        rb3_home_the_project_end = view.findViewById(R.id.rb3_home_the_project_end);
        rb4_home_the_project_end = view.findViewById(R.id.rb4_home_the_project_end);

        home_picker = view.findViewById(R.id.home_picker);
        home_picker_cancel = view.findViewById(R.id.home_picker_cancel);
        home_picker_ensure = view.findViewById(R.id.home_picker_ensure);
        dateTimePickerView = view.findViewById(R.id.home_pickerView);


        myproject = view.findViewById(R.id.myproject);
        mypost = view.findViewById(R.id.mypost);

        layout1 = view.findViewById(R.id.layout1);
        layout2 = view.findViewById(R.id.layout2);
        layout3 = view.findViewById(R.id.layout3);
        layout4 = view.findViewById(R.id.layout4);
        layout5 = view.findViewById(R.id.layout5);
        layout6 = view.findViewById(R.id.layout6);
        layout7 = view.findViewById(R.id.layout7);
        layout8 = view.findViewById(R.id.layout8);
        layout9 = view.findViewById(R.id.layout9);
        layout10 = view.findViewById(R.id.layout10);


        AuditList = view.findViewById(R.id.AuditList);

        time1_home_the_project_end = view.findViewById(R.id.time1_home_the_project_end);
        time2_home_the_project_end = view.findViewById(R.id.time2_home_the_project_end);

        tv1_home_the_project_end = view.findViewById(R.id.tv1_home_the_project_end);
        tv2_home_the_project_end = view.findViewById(R.id.tv2_home_the_project_end);
        tv3_home_the_project_end = view.findViewById(R.id.tv3_home_the_project_end);
        tv5_home_the_project_end = view.findViewById(R.id.tv5_home_the_project_end);
        tv6_home_the_project_end = view.findViewById(R.id.tv6_home_the_project_end);
        tv7_home_the_project_end = view.findViewById(R.id.tv7_home_the_project_end);
        tv8_home_the_project_end = view.findViewById(R.id.tv8_home_the_project_end);
        tv9_home_the_project_end = view.findViewById(R.id.tv9_home_the_project_end);
        tv10_home_the_project_end = view.findViewById(R.id.tv10_home_the_project_end);
        tv11_home_the_project_end = view.findViewById(R.id.tv11_home_the_project_end);
        tv12_home_the_project_end = view.findViewById(R.id.tv12_home_the_project_end);
        tv13_home_the_project_end = view.findViewById(R.id.tv13_home_the_project_end);
        tv14_home_the_project_end = view.findViewById(R.id.tv14_home_the_project_end);

        time1_ll1_home_the_project_end = view.findViewById(R.id.time1_ll1_home_the_project_end);

        rv_home_the_project_end = view.findViewById(R.id.rv_home_the_project_end);

        time1_home_the_project_end.setOnClickListener(this);
        time2_home_the_project_end.setOnClickListener(this);


        rb1_home_the_project_end.setOnClickListener(this);
        rb2_home_the_project_end.setOnClickListener(this);
        rb3_home_the_project_end.setOnClickListener(this);
        rb4_home_the_project_end.setOnClickListener(this);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);
        layout6.setOnClickListener(this);
        layout7.setOnClickListener(this);
        layout8.setOnClickListener(this);
        layout9.setOnClickListener(this);
        layout10.setOnClickListener(this);
        AuditList.setOnClickListener(this);
        mypost.setOnClickListener(this);
        myproject.setOnClickListener(this);

        mPtrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrClassicFrameLayout.refreshComplete();
                        mPtrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        initData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        initData();
    }

    //TODO 赋值
    private void initData() {

        initUser();
        initViewData();
        initList();
    }

    //TODO 首页用户信息赋值
    private void initUser() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UserBean> userMessage = fzbInterface.getUserBean(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(UserBean userBean) {
                        Glide.with(getActivity()).load("http://39.98.173.250:8080" + userBean.getData().getPhoto()).placeholder(R.mipmap.logo_square).into(img_home_the_project_end);
                        name_home_the_project_end.setText(userBean.getData().getName());
                        city_home_the_project_end.setText(userBean.getData().getCity());
                        shop_home_the_project_end.setText(userBean.getData().getCityName());
                        position_home_the_project_end.setText("专案");
                        FinalContents.setCityID(userBean.getData().getCityId());
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

    //TODO 首页时间赋值
    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        time1_home_the_project_end.setText("<" + string);
        time2_home_the_project_end.setText("-" + string + " >");
        dateTimePickerView.setStartDate(new GregorianCalendar(year-2, month, dayOfMonth));
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(2019, 0, 1));

        dateTimePickerView.setEndDate(new GregorianCalendar(year, month, dayOfMonth));

        home_picker_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_picker.setVisibility(View.GONE);
            }
        });

        home_picker_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_picker.setVisibility(View.GONE);
            }
        });

        //            TODO 开始时间
        time1_home_the_project_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        beforeDate = dateString;
                        time1_home_the_project_end.setText("<" + dateString);
                    }
                });
            }
        });
        //                TODO 结束时间
        time2_home_the_project_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        afterDate = dateString;
                        time2_home_the_project_end.setText("-" + dateString + " >");
                        Log.d("wsw", "new date: " + dateString);
                        initViewData();
                    }
                });
            }
        });
    }

    //TODO 首页运营数据赋值
    private void initViewData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HomeBean> userMessage = fzbInterface.getHomeList(FinalContents.getUserID(), beforeDate, afterDate, type,"1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(HomeBean homeBean) {
                        tv1_home_the_project_end.setText("" + homeBean.getData().getProdjectCount());
                        tv2_home_the_project_end.setText("" + homeBean.getData().getReportNumber());
                        tv3_home_the_project_end.setText("" + homeBean.getData().getAccessingNumber());
                        tv5_home_the_project_end.setText("" + homeBean.getData().getIsIslandNumber());
                        tv6_home_the_project_end.setText("" + homeBean.getData().getEarnestMoneyNumber());
                        tv7_home_the_project_end.setText("" + homeBean.getData().getTradeNumber());
                        tv8_home_the_project_end.setText("" + homeBean.getData().getLoseEfficacy());
                        tv9_home_the_project_end.setText("" + homeBean.getData().getReceivableMoney());
                        tv10_home_the_project_end.setText("" + homeBean.getData().getBackMoney());
                        tv11_home_the_project_end.setText("" + homeBean.getData().getInvoiceMoney());
                        tv12_home_the_project_end.setText("" + homeBean.getData().getSurplusMoney());
                        tv13_home_the_project_end.setText("" + homeBean.getData().getAuditNumber());
                        tv14_home_the_project_end.setText("" + homeBean.getData().getApplyCount());
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

    //TODO 首页列表数据赋值
    private void initList() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SideHomeBean> userMessage = fzbInterface.getHomeBeanList(FinalContents.getUserID(), "","1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SideHomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(SideHomeBean homeListBean) {
                        if (homeListBean.getCode().equals("1")) {
                            SideHomeBean.DataBean homeListBeanData = homeListBean.getData();
                            List<SideHomeBean.DataBean.RowsBean> homeListBeanDataRows = homeListBeanData.getRows();
                            //在此处修改布局排列方向
                            rv_home_the_project_end.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(view.getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            layoutManager.setScrollEnabled(false);
                            rv_home_the_project_end.setLayoutManager(layoutManager);
                            Project_Side_HomeRecyclerAdapter recyclerAdapter = new Project_Side_HomeRecyclerAdapter(homeListBeanDataRows);
                            rv_home_the_project_end.setNestedScrollingEnabled(false);
                            rv_home_the_project_end.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        } else {
                            rv_home_the_project_end.setVisibility(View.GONE);
                        }
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

    //TODO 点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//                TODO 我的项目
            case R.id.myproject:
                intent = new Intent(getContext(), MyProjectActivity.class);
                startActivity(intent);
                break;
//                TODO  报备
            case R.id.layout1:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
//                TODO  到访
            case R.id.layout2:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "2");
                startActivity(intent);
                break;
//               TODO  登岛
            case R.id.layout3:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "3");
                startActivity(intent);
                break;
//                TODO  认筹
            case R.id.layout4:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "4");
                startActivity(intent);
                break;
//                TODO  成交
            case R.id.layout5:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "5");
                startActivity(intent);
                break;
//                TODO  失效
            case R.id.layout6:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "6");
                startActivity(intent);
                break;
//                TODO  应收
            case R.id.layout7:
                intent = new Intent(getContext(), CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  已结
            case R.id.layout8:
                intent = new Intent(getContext(), CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  待结
            case R.id.layout9:
                intent = new Intent(getContext(), CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  未结
            case R.id.layout10:
                intent = new Intent(getContext(), CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  待我审核
            case R.id.AuditList:
                FinalContents.setNumS(1);
                intent = new Intent(getContext(), CheckPendingTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  我发起的审核
            case R.id.mypost:
                intent = new Intent(getContext(), InitiatedTheReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.rb1_home_the_project_end:
                time1_ll1_home_the_project_end.setVisibility(View.GONE);
                beforeDate = "";
                afterDate = "";
                type = "";
                initViewData();
                break;
            case R.id.rb2_home_the_project_end:
                time1_ll1_home_the_project_end.setVisibility(View.GONE);
                beforeDate = "";
                afterDate = "";
                type = "1";
                initViewData();
                break;
            case R.id.rb3_home_the_project_end:
                time1_ll1_home_the_project_end.setVisibility(View.GONE);
                beforeDate = "";
                afterDate = "";
                type = "2";
                initViewData();
                break;
            case R.id.rb4_home_the_project_end:
                time1_ll1_home_the_project_end.setVisibility(View.VISIBLE);
                type = "3";
                initDate();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
