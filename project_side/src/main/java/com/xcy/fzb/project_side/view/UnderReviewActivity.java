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

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 登岛
public class UnderReviewActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView under_review_return;
    ImageView under_review_img1;
    ImageView under_review_img2;

    TextView under_review_tv1;
    TextView under_review_tv2;
    TextView under_review_tv3;
    TextView under_review_tv4;
    TextView under_review_tv5;
    TextView under_review_tv6;
    TextView under_review_tv7;

    RecyclerView under_review_rv;

    Button under_review_bt1;

    private Intent intent;

    MakeABargainAdapter adapter;

    private MyClientFragmentBean.DataBean.InfoDataBean infoData;
    private List<MyClientFragmentBean.DataBean.ProcessDataBean> processData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_review);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        under_review_return = findViewById(R.id.under_review_return);
        under_review_img1 = findViewById(R.id.under_review_img1);
        under_review_img2 = findViewById(R.id.under_review_img2);
        under_review_tv1 = findViewById(R.id.under_review_tv1);
        under_review_tv2 = findViewById(R.id.under_review_tv2);
        under_review_tv3 = findViewById(R.id.under_review_tv3);
        under_review_tv4 = findViewById(R.id.under_review_tv4);
        under_review_tv5 = findViewById(R.id.under_review_tv5);
        under_review_tv6 = findViewById(R.id.under_review_tv6);
        under_review_tv7 = findViewById(R.id.under_review_tv7);
        under_review_rv = findViewById(R.id.under_review_rv);

        under_review_bt1 = findViewById(R.id.under_review_bt1);

        under_review_return.setOnClickListener(this);
        under_review_img2.setOnClickListener(this);
        under_review_tv6.setOnClickListener(this);
        under_review_bt1.setOnClickListener(this);

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
                        Glide.with(UnderReviewActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(under_review_img1);
                        under_review_tv1.setText(infoData.getCustomerName());
                        processData = myClientFragmentBean.getData().getProcessData();

                        under_review_tv2.setText(infoData.getProjectName());
                        under_review_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");

                        FinalContents.setPreparationId(processData.get(0).getPreparationId());
                        if (processData.get(0).getJsonDatas().size() == 0) {
                            under_review_tv4.setText("");
                            under_review_tv5.setText("");
                            under_review_tv6.setText("");
                            under_review_tv7.setText("");
                        } else {
                            under_review_tv4.setText(processData.get(0).getJsonDatas().get(0).getValue());
                            under_review_tv5.setText(processData.get(0).getJsonDatas().get(1).getValue());
                            under_review_tv6.setText(processData.get(0).getJsonDatas().get(2).getValue());
                            under_review_tv7.setText(processData.get(0).getJsonDatas().get(3).getValue());
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

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        under_review_rv.setLayoutManager(manager);
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
        under_review_rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.under_review_return:
                finish();
                break;
            //            TODO 项目负责人电话
            case R.id.under_review_img2:
                if (infoData.getCustomerPhone().equals("")) {
                    Toast.makeText(this, "负责人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + infoData.getCustomerPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            //            TODO 经纪人电话
            case R.id.under_review_tv6:
                if (processData.get(0).getJsonDatas().get(2).getValue().equals("")) {
                    Toast.makeText(this, "经纪人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + processData.get(0).getJsonDatas().get(2).getValue()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            //            TODO 认筹
            case R.id.under_review_bt1:
                intent = new Intent(UnderReviewActivity.this,ConfessToRaiseInformationActivity.class);
                startActivity(intent);
                break;
        }

    }
}
