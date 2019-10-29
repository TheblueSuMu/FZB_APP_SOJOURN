package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.CBean;
import com.xcy.fzb.all.modle.CheckBean;
import com.xcy.fzb.all.modle.ReportProcessDetailsBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_side.adapter.Project_Side_MakeABargainAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckPendingActivity extends AllActivity implements View.OnClickListener {


    RelativeLayout check_pending_return;
    ImageView check_pending_img1;
    ImageView check_pending_img2;

    TextView check_pending_tv1;
    TextView check_pending_tv2;
    TextView check_pending_tv3;
    RecyclerView check_pending_rv;

    Button check_pending_bt1;
    Button check_pending_bt2;

    Project_Side_MakeABargainAdapter adapter;


    private ReportProcessDetailsBean.DataBean.InfoDataBean infoData;
    private List<ReportProcessDetailsBean.DataBean.ProcessDataBean> processData;
    private List<String> list;
    private Intent intent;
    private String mycheck;
    private String name;
    private String url;
    private LinearLayout check_pending_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_check_pending);
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
        StatusBar.makeStatusBarTransparent(this);

        name = getIntent().getStringExtra("name");

        mycheck = getIntent().getStringExtra("Mycheck");
        check_pending_return = findViewById(R.id.check_pending_return);
        check_pending_img1 = findViewById(R.id.check_pending_img1);
        check_pending_img2 = findViewById(R.id.check_pending_img2);
        check_pending_tv1 = findViewById(R.id.check_pending_tv1);
        check_pending_tv2 = findViewById(R.id.check_pending_tv2);
        check_pending_tv3 = findViewById(R.id.check_pending_tv3);
        check_pending_rv = findViewById(R.id.check_pending_rv);
        check_pending_bt1 = findViewById(R.id.check_pending_bt1);
        check_pending_bt2 = findViewById(R.id.check_pending_bt2);
        check_pending_ll = findViewById(R.id.check_pending_ll);

        if (FinalContents.getJuJue().equals("拒绝记录")) {
            check_pending_ll.setVisibility(View.GONE);
        } else if (FinalContents.getJuJue().equals("待我审核")){
            check_pending_ll.setVisibility(View.VISIBLE);
        }


        check_pending_return.setOnClickListener(this);
        check_pending_bt1.setOnClickListener(this);
        check_pending_bt2.setOnClickListener(this);
        check_pending_img2.setOnClickListener(this);

        initData();
    }

    private void initData() {

        if (mycheck.equals("1")) {
            check_pending_bt2.setText("拒绝");
        } else if (mycheck.equals("2")) {
            check_pending_bt2.setText("修改认筹信息");
        } else if (mycheck.equals("3")) {
            check_pending_bt2.setText("修改成交信息");
        }

        Log.i("MyCL", "getCustomerId：" + FinalContents.getCustomerID());
        Log.i("数据", "userID：" + FinalContents.getUserID());
        Log.i("数据", "报备：" + FinalContents.getPreparationId());


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReportProcessDetailsBean> clientFragmentBean = fzbInterface.getReportProcessDetails(FinalContents.getUserID(), FinalContents.getPreparationId());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReportProcessDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReportProcessDetailsBean reportProcessDetailsBean) {
                        infoData = reportProcessDetailsBean.getData().getInfoData();
                        Glide.with(CheckPendingActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(check_pending_img1);
                        check_pending_tv1.setText(infoData.getCustomerName());

                        processData = reportProcessDetailsBean.getData().getProcessData();
                        check_pending_tv2.setText(infoData.getProjectName());
                        check_pending_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");
                        FinalContents.setJJrID(infoData.getAgentId());
                        initRV();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("WrongConstant")
    private void initRV() {

        list = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        check_pending_rv.setLayoutManager(manager);
        adapter = new Project_Side_MakeABargainAdapter();
        adapter.setListData(processData);

        check_pending_rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //            TODO 返回上一层
            case R.id.check_pending_return:
                finish();
                break;
            //            TODO 通过
            case R.id.check_pending_bt1:
                initBTN();
                break;
            //            TODO 拒绝
            case R.id.check_pending_bt2:
                String s = check_pending_bt2.getText().toString();
                if (s.equals("拒绝")) {
                    intent = new Intent(CheckPendingActivity.this, TheReasonForRefusalActivity.class);
                } else if (s.equals("修改认筹信息")) {
                    intent = new Intent(CheckPendingActivity.this, ModifyTheRecognitionToRaiseActivity.class);
                } else if (s.equals("修改成交信息")) {
                    intent = new Intent(CheckPendingActivity.this, FillInTransactionInformationActivity.class);
                    FinalContents.setTiaodan("调单");
                }
                startActivity(intent);
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.check_pending_img2:
                if (infoData.getCustomerPhone().equals("")) {
                    Toast.makeText(this, "负责人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + infoData.getCustomerPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initBTN() {
        Log.i("MyCL", "getUserID：" + FinalContents.getUserID());
        Log.i("MyCL", "getPreparationId：" + FinalContents.getPreparationId());
        Log.i("MyCL","Name：" + name);
        if (name.equals("报备") || name.equals("到访")) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<CBean> clientFragmentBean = fzbInterface.getReportAndVisitAudit(FinalContents.getUserID(), FinalContents.getPreparationId(),FinalContents.getStatus(),"1");
            clientFragmentBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CBean cBean) {
                            if (cBean.getMsg().equals("成功")) {
                                Toast.makeText(CheckPendingActivity.this, cBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                FinalContents.setEndStart("成功");
                                finish();
                            } else {
                                Toast.makeText(CheckPendingActivity.this, cBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else if (name.equals("认筹")) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<CheckBean> clientFragmentBean = fzbInterface.getEarnestMoneyAudit(FinalContents.getUserID(), FinalContents.getPreparationId(),"0");
            clientFragmentBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CheckBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CheckBean checkBean) {
                            if (checkBean.getMsg().equals("成功")) {
                                Toast.makeText(CheckPendingActivity.this, checkBean.getMsg(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(CheckPendingActivity.this, checkBean.getMsg(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else if (name.equals("成交")) {
            Log.i("成交","用户名："+FinalContents.getUserID());
            Log.i("成交","报备："+FinalContents.getPreparationId());

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<CheckBean> clientFragmentBean = fzbInterface.getTradeAudit(FinalContents.getUserID(), FinalContents.getPreparationId(),"0");
            clientFragmentBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CheckBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CheckBean checkBean) {
                            if (checkBean.getMsg().equals("成功")) {
                                Toast.makeText(CheckPendingActivity.this, checkBean.getMsg(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(CheckPendingActivity.this, checkBean.getMsg(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }
}
