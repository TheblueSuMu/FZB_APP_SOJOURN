package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.MakeABargainAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.CBean;
import com.xcy.fzb.project_side.modle.CheckBean;
import com.xcy.fzb.project_side.modle.MyClientFragmentBean;
import com.xcy.fzb.project_side.presente.OkHttpPost;
import com.xcy.fzb.project_side.presente.StatusBar;
import com.xcy.fzb.project_side.service.MyService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckPendingActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView check_pending_return;
    ImageView check_pending_img1;
    ImageView check_pending_img2;

    TextView check_pending_tv1;
    TextView check_pending_tv2;
    TextView check_pending_tv3;
    TextView check_pending_tv4;
    TextView check_pending_tv5;
    TextView check_pending_tv6;
    TextView check_pending_tv7;

    RecyclerView check_pending_rv;

    Button check_pending_bt1;
    Button check_pending_bt2;

    MakeABargainAdapter adapter;


    private MyClientFragmentBean.DataBean.InfoDataBean infoData;
    private List<MyClientFragmentBean.DataBean.ProcessDataBean> processData;
    private List<String> list;
    private Intent intent;
    private String mycheck;
    private String name;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pending);

        initView();

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
        check_pending_tv4 = findViewById(R.id.check_pending_tv4);
        check_pending_tv5 = findViewById(R.id.check_pending_tv5);
        check_pending_tv6 = findViewById(R.id.check_pending_tv6);
        check_pending_tv7 = findViewById(R.id.check_pending_tv7);
        check_pending_rv = findViewById(R.id.check_pending_rv);
        check_pending_bt1 = findViewById(R.id.check_pending_bt1);
        check_pending_bt2 = findViewById(R.id.check_pending_bt2);


        check_pending_return.setOnClickListener(this);
        check_pending_bt1.setOnClickListener(this);
        check_pending_bt2.setOnClickListener(this);
        check_pending_img2.setOnClickListener(this);
        check_pending_tv6.setOnClickListener(this);

        initData();
    }

    private void initData() {

        if (mycheck.equals("1")) {
            check_pending_bt2.setText("拒绝");
        } else if (mycheck.equals("2")) {
            check_pending_bt2.setText("修改认筹信息");
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
        Observable<MyClientFragmentBean> clientFragmentBean = fzbInterface.getClientFragmentBean(FinalContents.getUserID(), FinalContents.getPreparationId());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyClientFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyClientFragmentBean myClientFragmentBean) {
                        infoData = myClientFragmentBean.getData().getInfoData();
                        Glide.with(CheckPendingActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(check_pending_img1);
                        check_pending_tv1.setText(infoData.getCustomerName());

                        processData = myClientFragmentBean.getData().getProcessData();
                        check_pending_tv2.setText(infoData.getProjectName());
                        check_pending_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");

                        if (processData.get(0).getJsonDatas().size() == 0) {
                            check_pending_tv4.setText("");
                            check_pending_tv5.setText("");
                            check_pending_tv6.setText("");
                            check_pending_tv7.setText("");
                        } else {
                            check_pending_tv4.setText(processData.get(0).getJsonDatas().get(0).getValue());
                            check_pending_tv5.setText(processData.get(0).getJsonDatas().get(1).getValue());
                            check_pending_tv6.setText(processData.get(0).getJsonDatas().get(2).getValue());
                            check_pending_tv7.setText(processData.get(0).getJsonDatas().get(3).getValue());
                        }

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
        adapter = new MakeABargainAdapter();
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
                }
                startActivity(intent);
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
            //            TODO 经纪人电话
            case R.id.check_pending_tv6:
                if (processData.get(0).getJsonDatas().get(2).getValue().equals("")) {
                    Toast.makeText(this, "经纪人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + processData.get(0).getJsonDatas().get(2).getValue()));//跳转到拨号界面，同时传递电话号码
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
            url = FinalContents.getBaseUrl() + "specialUpdate/reportAndVisitAudit?preparationId=" + FinalContents.getPreparationId() + "&maxStatus=" + FinalContents.getStatus() + "&minStatus=1&userId=" + FinalContents.getUserID();
            OkHttpPost okHttpPost = new OkHttpPost(url);
            String data = okHttpPost.post();
            Gson gson = new Gson();
            CBean cBean = gson.fromJson(data, CBean.class);
            if (cBean.getMsg().equals("成功")) {
                Toast.makeText(CheckPendingActivity.this, cBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(CheckPendingActivity.this, cBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (name.equals("认筹")) {
            url = FinalContents.getBaseUrl() + "specialUpdate/earnestMoneyAudit?userId=" + FinalContents.getUserID() + "&preparationId=" + FinalContents.getPreparationId() + "&isUpdate=0";
            OkHttpPost okHttpPost = new OkHttpPost(url);
            String data = okHttpPost.post();
            Gson gson = new Gson();
            CheckBean checkBean = gson.fromJson(data, CheckBean.class);
            if (checkBean.getMsg().equals("成功")) {
                Toast.makeText(CheckPendingActivity.this, checkBean.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(CheckPendingActivity.this, checkBean.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (name.equals("成功")) {
            url = FinalContents.getBaseUrl() + "specialUpdate/tradeAudit?userId=" + FinalContents.getUserID() + "&preparationId=" + FinalContents.getPreparationId() + "&isUpdate=0";
            OkHttpPost okHttpPost = new OkHttpPost(url);
            String data = okHttpPost.post();
            Gson gson = new Gson();
            CheckBean checkBean = gson.fromJson(data, CheckBean.class);
            if (checkBean.getMsg().equals("成功")) {
                Toast.makeText(CheckPendingActivity.this, checkBean.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(CheckPendingActivity.this, checkBean.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }
}
