package com.xcy.fzb.all.view;

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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.MakeABargainAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.MakeABargainBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;

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
public class ConfessToRaiseDetailsActivity extends AllActivity implements View.OnClickListener {

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

    private List<MakeABargainBean.DataBean.ListDataBean> listData;
    private MakeABargainBean.DataBean.InfoDataBean infoData;
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
        Observable<MakeABargainBean> makeABargainBean = fzbInterface.getMakeABargainBean(FinalContents.getUserID(), FinalContents.getCustomerID());
        makeABargainBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MakeABargainBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MakeABargainBean makeABargainBean) {
                        infoData = makeABargainBean.getData().getInfoData();
//
                        Glide.with(ConfessToRaiseDetailsActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(confess_to_raise_details_img1);
                        confess_to_raise_details_tv1.setText(infoData.getCustomerName());
                        listData = makeABargainBean.getData().getListData();
                        confess_to_raise_details_tv2.setText(listData.get(0).getProjectName());
                        confess_to_raise_details_tv3.setText(listData.get(0).getAttacheList().get(0).getName() + "[" + listData.get(0).getAttacheList().get(0).getPhone() + "]");

                        if (listData.size() == 1) {
                            confess_to_raise_details_tv4.setText("");
                            confess_to_raise_details_tv5.setText("");
                            confess_to_raise_details_tv6.setText("");
                            confess_to_raise_details_tv7.setText("");
                        } else {
                            confess_to_raise_details_tv5.setText(listData.get(1).getAttacheList().get(0).getName());
                            confess_to_raise_details_tv6.setText(listData.get(1).getAttacheList().get(0).getPhone());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "ConfessToRaiseDetailsActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        String url = FinalContents.getBaseUrl()+"commonSelect/customerDetails?userId=" + FinalContents.getUserID() + "&customerId=" + FinalContents.getCustomerID();
//
//        OkHttpPost okHttpPost = new OkHttpPost(url);
//        String data = okHttpPost.post();
//
//        Gson gson = new Gson();
//        MakeABargainBean makeABargainBean = gson.fromJson(data, MakeABargainBean.class);
//        infoData = makeABargainBean.getData().getInfoData();
//
//        Glide.with(this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(confess_to_raise_details_img1);
//        confess_to_raise_details_tv1.setText(infoData.getCustomerName());
//        listData = makeABargainBean.getData().getListData();
//        confess_to_raise_details_tv2.setText(listData.get(0).getProjectName());
//        confess_to_raise_details_tv3.setText(listData.get(0).getAttacheList().get(0).getName() + "[" + listData.get(0).getAttacheList().get(0).getPhone() + "]");

        initRV();

    }

    @SuppressLint("WrongConstant")
    private void initRV() {

        list = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        confess_to_raise_details_rv.setLayoutManager(manager);
        adapter = new MakeABargainAdapter();
        adapter.setListData(listData);


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
                if (listData.get(0).getAttacheList().get(0).getPhone().equals("")) {
                    Toast.makeText(this, "负责人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + listData.get(0).getAttacheList().get(0).getPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            //            TODO 经纪人电话
            case R.id.confess_to_raise_details_tv6:

                if (listData.get(1).getAttacheList().get(0).getPhone().equals("")) {
                    Toast.makeText(this, "经纪人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + listData.get(1).getAttacheList().get(0).getPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
        }
    }

}
