package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

//TODO 失效详情-项目进度
public class FailureActivity extends AppCompatActivity implements View.OnClickListener {
//failure

    ImageView failure_return;
    ImageView failure_img1;
    ImageView failure_img2;

    TextView failure_tv1;
    TextView failure_tv2;
    TextView failure_tv3;
    TextView failure_tv4;
    TextView failure_tv5;
    TextView failure_tv6;
    TextView failure_tv7;

    RecyclerView failure_rv;

    MakeABargainAdapter adapter;

    private MyClientFragmentBean.DataBean.InfoDataBean infoData;
    private List<MyClientFragmentBean.DataBean.ProcessDataBean> processData;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        failure_return = findViewById(R.id.failure_return);
        failure_img1 = findViewById(R.id.failure_img1);
        failure_img2 = findViewById(R.id.failure_img2);
        failure_tv1 = findViewById(R.id.failure_tv1);
        failure_tv2 = findViewById(R.id.failure_tv2);
        failure_tv3 = findViewById(R.id.failure_tv3);
        failure_tv4 = findViewById(R.id.failure_tv4);
        failure_tv5 = findViewById(R.id.failure_tv5);
        failure_tv6 = findViewById(R.id.failure_tv6);
        failure_tv7 = findViewById(R.id.failure_tv7);
        failure_rv = findViewById(R.id.failure_rv);

        failure_return.setOnClickListener(this);
        failure_img2.setOnClickListener(this);
        failure_tv6.setOnClickListener(this);

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
                        Glide.with(FailureActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(failure_img1);
                        failure_tv1.setText(infoData.getCustomerName());
                        processData = myClientFragmentBean.getData().getProcessData();
                        failure_tv2.setText("名字"+infoData.getProjectName());
                        failure_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");

                        FinalContents.setPreparationId(processData.get(0).getPreparationId());
                        if (processData.get(0).getJsonDatas().size() == 0) {
                            failure_tv4.setText("");
                            failure_tv5.setText("");
                            failure_tv6.setText("");
                            failure_tv7.setText("");
                        } else {
                            failure_tv4.setText(processData.get(0).getJsonDatas().get(0).getValue());
                            failure_tv5.setText(processData.get(0).getJsonDatas().get(1).getValue());
                            failure_tv6.setText(processData.get(0).getJsonDatas().get(2).getValue());
                            failure_tv7.setText(processData.get(0).getJsonDatas().get(3).getValue());
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
        failure_rv.setLayoutManager(manager);
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
        failure_rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //            TODO 返回上一层
            case R.id.failure_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.make_a_bargain_img2:
                if (infoData.getCustomerPhone().equals("")) {
                    Toast.makeText(this, "负责人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + infoData.getCustomerPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            //            TODO 经纪人电话
            case R.id.make_a_bargain_tv6:
                if (processData.get(0).getJsonDatas().get(2).getValue().equals("")) {
                    Toast.makeText(this, "经纪人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + processData.get(0).getJsonDatas().get(2).getValue()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
        }

    }
}
