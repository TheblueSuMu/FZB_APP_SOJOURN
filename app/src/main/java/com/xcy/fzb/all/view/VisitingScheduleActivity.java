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

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 到访项目进度
public class VisitingScheduleActivity extends AllActivity implements View.OnClickListener {

    ImageView visiting_schedule_return;
    ImageView visiting_schedule_img1;
    ImageView visiting_schedule_img2;

    TextView visiting_schedule_tv1;
    TextView visiting_schedule_tv2;
    TextView visiting_schedule_tv3;
    TextView visiting_schedule_tv4;
    TextView visiting_schedule_tv5;
    TextView visiting_schedule_tv6;
    TextView visiting_schedule_tv7;

    RecyclerView visiting_schedule_rv;

    Button visiting_schedule_bt1;
    Button visiting_schedule_bt2;
    Button visiting_schedule_bt3;
    Button visiting_schedule_bt4;

    private Intent intent;

    MakeABargainAdapter adapter;

    private List<MakeABargainBean.DataBean.ListDataBean> listData;
    private MakeABargainBean.DataBean.InfoDataBean infoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiting_schedule);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        visiting_schedule_return = findViewById(R.id.visiting_schedule_return);
        visiting_schedule_img1 = findViewById(R.id.visiting_schedule_img1);
        visiting_schedule_img2 = findViewById(R.id.visiting_schedule_img2);
        visiting_schedule_tv1 = findViewById(R.id.visiting_schedule_tv1);
        visiting_schedule_tv2 = findViewById(R.id.visiting_schedule_tv2);
        visiting_schedule_tv3 = findViewById(R.id.visiting_schedule_tv3);
        visiting_schedule_tv4 = findViewById(R.id.visiting_schedule_tv4);
        visiting_schedule_tv5 = findViewById(R.id.visiting_schedule_tv5);
        visiting_schedule_tv6 = findViewById(R.id.visiting_schedule_tv6);
        visiting_schedule_tv7 = findViewById(R.id.visiting_schedule_tv7);
        visiting_schedule_rv = findViewById(R.id.visiting_schedule_rv);
        visiting_schedule_bt1 = findViewById(R.id.visiting_schedule_bt1);
        visiting_schedule_bt2 = findViewById(R.id.visiting_schedule_bt2);
        visiting_schedule_bt3 = findViewById(R.id.visiting_schedule_bt3);
        visiting_schedule_bt4 = findViewById(R.id.visiting_schedule_bt4);


        visiting_schedule_return.setOnClickListener(this);
        visiting_schedule_bt1.setOnClickListener(this);
        visiting_schedule_bt2.setOnClickListener(this);
        visiting_schedule_bt3.setOnClickListener(this);
        visiting_schedule_bt4.setOnClickListener(this);
        visiting_schedule_img2.setOnClickListener(this);
        visiting_schedule_tv6.setOnClickListener(this);

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
        Observable<MakeABargainBean> mBargainBean = fzbInterface.getMBargainBean(FinalContents.getUserID(), FinalContents.getCustomerID());
        mBargainBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MakeABargainBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MakeABargainBean makeABargainBean) {
                        infoData = makeABargainBean.getData().getInfoData();
//
                        Glide.with(VisitingScheduleActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(visiting_schedule_img1);
                        visiting_schedule_tv1.setText(infoData.getCustomerName());
                        listData = makeABargainBean.getData().getListData();
                        visiting_schedule_tv2.setText(listData.get(0).getProjectName());
                        visiting_schedule_tv3.setText(listData.get(0).getAttacheList().get(0).getName() + "[" + listData.get(0).getAttacheList().get(0).getPhone() + "]");

                        if (listData.size() == 1) {
                            visiting_schedule_tv4.setText("");
                            visiting_schedule_tv5.setText("");
                            visiting_schedule_tv6.setText("");
                            visiting_schedule_tv7.setText("");
                        } else {
                            //        under_review_tv4.setText();
                            visiting_schedule_tv5.setText(listData.get(1).getAttacheList().get(0).getName());
                            visiting_schedule_tv6.setText(listData.get(1).getAttacheList().get(0).getPhone());
//        under_review_tv7.setText("");
                        }

                        initRV();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "VisitingScheduleActivity错误信息：" + e.getMessage());
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
//        Glide.with(this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(visiting_schedule_img1);
//        visiting_schedule_tv1.setText(infoData.getCustomerName());
//        listData = makeABargainBean.getData().getListData();
//        visiting_schedule_tv2.setText(listData.get(0).getProjectName());
//        visiting_schedule_tv3.setText(listData.get(0).getAttacheList().get(0).getName() + "[" + listData.get(0).getAttacheList().get(0).getPhone() + "]");
//
//        if (listData.size() == 1) {
//            visiting_schedule_tv4.setText("");
//            visiting_schedule_tv5.setText("");
//            visiting_schedule_tv6.setText("");
//            visiting_schedule_tv7.setText("");
//        } else {
//            //        under_review_tv4.setText();
//            visiting_schedule_tv5.setText(listData.get(1).getAttacheList().get(0).getName());
//            visiting_schedule_tv6.setText(listData.get(1).getAttacheList().get(0).getPhone());
////        under_review_tv7.setText("");
//        }
//
//        initRV();

    }

    @SuppressLint("WrongConstant")
    private void initRV() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        visiting_schedule_rv.setLayoutManager(manager);
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
        visiting_schedule_rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //            TODO 返回上一层
            case R.id.visiting_schedule_return:
                finish();
                break;
//            //            TODO 重复到访
//            case R.id.visiting_schedule_bt1:
//                intent = new Intent(VisitingScheduleActivity.this, FillInTransactionInformationActivity.class);
//                intent.putExtra("name","填写到访信息");
//                startActivity(intent);
//                break;
            //            TODO 申请登岛
            case R.id.visiting_schedule_bt2:
                intent = new Intent(VisitingScheduleActivity.this, ToApplyForAnIslandActivity.class);
                startActivity(intent);
                break;
//            //            TODO 认筹
//            case R.id.visiting_schedule_bt3:
//                FinalContents.setPreparationId(listData.get(0).getPreparationId());
//                FinalContents.setProjectID(listData.get(0).getProjectId());
//                FinalContents.setCustomerID(FinalContents.getCustomerID());
//                intent.putExtra("projectName", listData.get(0).getProjectName());
//                intent.putExtra("name", listData.get(0).getAttacheList().get(0).getName());
//                intent.putExtra("phone", listData.get(0).getAttacheList().get(0).getPhone());
//                intent = new Intent(VisitingScheduleActivity.this, ConfessToRaiseInformationActivity.class);
//                startActivity(intent);
//                break;
//            //            TODO 成交
//            case R.id.visiting_schedule_bt4:
//                intent = new Intent(VisitingScheduleActivity.this, FillInTransactionInformationActivity.class);
//                intent.putExtra("name","填写成交信息");
//                startActivity(intent);
//                break;
            //            TODO 项目负责人电话
            case R.id.visiting_schedule_img2:
                if (listData.get(0).getAttacheList().get(0).getPhone().equals("")) {
                    Toast.makeText(this, "负责人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + listData.get(0).getAttacheList().get(0).getPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            //            TODO 经纪人电话
            case R.id.visiting_schedule_tv6:
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
