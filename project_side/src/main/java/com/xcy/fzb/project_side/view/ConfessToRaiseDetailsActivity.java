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
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.MakeABargainAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.MyClientFragmentBean;
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

//TODO 认筹详情-项目进度
public class ConfessToRaiseDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView confess_to_raise_details_return;
    ImageView confess_to_raise_details_img1;
    ImageView confess_to_raise_details_img2;

    TextView confess_to_raise_details_tv1;
    TextView confess_to_raise_details_tv2;
    TextView confess_to_raise_details_tv3;
    TextView confess_to_raise_details_tv4;
    TextView confess_to_raise_details_tv5;
    TextView confess_to_raise_details_tv6;
    TextView confess_to_raise_details_tv7;

    RecyclerView confess_to_raise_details_rv;

    Button confess_to_raise_details_bt1;
    Button confess_to_raise_details_bt2;
    private Intent intent;

    MakeABargainAdapter adapter;

    private MyClientFragmentBean.DataBean.InfoDataBean infoData;
    private List<MyClientFragmentBean.DataBean.ProcessDataBean> processData;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confess_to_raise_details);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        confess_to_raise_details_return = findViewById(R.id.confess_to_raise_details_return);
        confess_to_raise_details_img1 = findViewById(R.id.confess_to_raise_details_img1);
        confess_to_raise_details_img2 = findViewById(R.id.confess_to_raise_details_img2);
        confess_to_raise_details_tv1 = findViewById(R.id.confess_to_raise_details_tv1);
        confess_to_raise_details_tv2 = findViewById(R.id.confess_to_raise_details_tv2);
        confess_to_raise_details_tv3 = findViewById(R.id.confess_to_raise_details_tv3);
        confess_to_raise_details_tv4 = findViewById(R.id.confess_to_raise_details_tv4);
        confess_to_raise_details_tv5 = findViewById(R.id.confess_to_raise_details_tv5);
        confess_to_raise_details_tv6 = findViewById(R.id.confess_to_raise_details_tv6);
        confess_to_raise_details_tv7 = findViewById(R.id.confess_to_raise_details_tv7);
        confess_to_raise_details_rv = findViewById(R.id.confess_to_raise_details_rv);

        confess_to_raise_details_bt1 = findViewById(R.id.confess_to_raise_success_bt1);
        confess_to_raise_details_bt2 = findViewById(R.id.confess_to_raise_success_bt2);

        confess_to_raise_details_return.setOnClickListener(this);
        confess_to_raise_details_img2.setOnClickListener(this);
        confess_to_raise_details_tv6.setOnClickListener(this);
        confess_to_raise_details_bt1.setOnClickListener(this);
        confess_to_raise_details_bt2.setOnClickListener(this);


        initData();

    }

    private void initData() {

        Log.i("MyCL", "getCustomerId：" + FinalContents.getCustomerID());
        Log.i("MyCL", "userID：" + FinalContents.getUserID());

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
                        Glide.with(ConfessToRaiseDetailsActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(confess_to_raise_details_img1);
                        confess_to_raise_details_tv1.setText(infoData.getCustomerName());
                        processData = myClientFragmentBean.getData().getProcessData();

                        confess_to_raise_details_tv2.setText(infoData.getProjectName());
                        confess_to_raise_details_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");

                        FinalContents.setPreparationId(processData.get(0).getPreparationId());
                        if (processData.get(0).getJsonDatas().size() == 0) {
                            confess_to_raise_details_tv4.setText("");
                            confess_to_raise_details_tv5.setText("");
                            confess_to_raise_details_tv6.setText("");
                            confess_to_raise_details_tv7.setText("");
                        } else {
                            confess_to_raise_details_tv4.setText(processData.get(0).getJsonDatas().get(0).getValue());
                            confess_to_raise_details_tv5.setText(processData.get(0).getJsonDatas().get(1).getValue());
                            confess_to_raise_details_tv6.setText(processData.get(0).getJsonDatas().get(2).getValue());
                            confess_to_raise_details_tv7.setText(processData.get(0).getJsonDatas().get(3).getValue());
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
        confess_to_raise_details_rv.setLayoutManager(manager);
        adapter = new MakeABargainAdapter();
        adapter.setListData(processData);


//        for (int i = 0; i < listData.size(); ++i) {
//            List<FailureBean.DataBean.ListDataBean.ListMapBean> listMap = listData.get(i).getListMap();
//            Log.i("MyCL", listData.get(i).getStatusName());
//            list.add(listData.get(i).getStatusName());
////            for (int j = 0; j < listMap.size(); ++j){
////                Log.i("MyCL",listMap.get(j).getValue());
////            }
//
//        }
//        adapter.setList(list);
        confess_to_raise_details_rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.confess_to_raise_details_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.confess_to_raise_details_img2:
                if (infoData.getCustomerPhone().equals("")) {
                    Toast.makeText(this, "负责人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + infoData.getCustomerPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            //            TODO 经纪人电话
            case R.id.confess_to_raise_details_tv6:

                if (processData.get(0).getJsonDatas().get(2).getValue().equals("")) {
                    Toast.makeText(this, "经纪人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + processData.get(0).getJsonDatas().get(2).getValue()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            //            TODO 成交
            case R.id.confess_to_raise_success_bt1:
                intent = new Intent(ConfessToRaiseDetailsActivity.this, FillInTransactionInformationActivity.class);
                intent.putExtra("name","填写成交信息");
                startActivity(intent);
                break;
            //            TODO 退筹
            case R.id.confess_to_raise_success_bt2:
                intent = new Intent(ConfessToRaiseDetailsActivity.this, BackToRaiseThatActivity.class);
                startActivity(intent);
                break;
        }
    }

}
