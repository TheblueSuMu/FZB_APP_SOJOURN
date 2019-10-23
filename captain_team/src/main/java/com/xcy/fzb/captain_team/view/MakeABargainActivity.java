package com.xcy.fzb.captain_team.view;

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
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.MakeABargainAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.MyClientFragmentBean;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 成交详情—项目进度
public class MakeABargainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView make_a_bargain_return;
    ImageView make_a_bargain_img1;
    ImageView make_a_bargain_img2;

    TextView make_a_bargain_tv1;
    TextView make_a_bargain_tv2;
    TextView make_a_bargain_tv3;
    TextView make_a_bargain_tv4;
    TextView make_a_bargain_tv5;
    TextView make_a_bargain_tv6;
    TextView make_a_bargain_tv7;

    RecyclerView make_a_bargain_rv;

    Button make_a_bargain_bt1;
    Button make_a_bargain_bt2;

    MakeABargainAdapter adapter;

    private MyClientFragmentBean.DataBean.InfoDataBean infoData;
    private List<MyClientFragmentBean.DataBean.ProcessDataBean> processData;
    private List<String> list;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_abargain);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        make_a_bargain_return = findViewById(R.id.make_a_bargain_return);
        make_a_bargain_img1 = findViewById(R.id.make_a_bargain_img1);
        make_a_bargain_img2 = findViewById(R.id.make_a_bargain_img2);
        make_a_bargain_tv1 = findViewById(R.id.make_a_bargain_tv1);
        make_a_bargain_tv2 = findViewById(R.id.make_a_bargain_tv2);
        make_a_bargain_tv3 = findViewById(R.id.make_a_bargain_tv3);
        make_a_bargain_tv4 = findViewById(R.id.make_a_bargain_tv4);
        make_a_bargain_tv5 = findViewById(R.id.make_a_bargain_tv5);
        make_a_bargain_tv6 = findViewById(R.id.make_a_bargain_tv6);
        make_a_bargain_tv7 = findViewById(R.id.make_a_bargain_tv7);
        make_a_bargain_rv = findViewById(R.id.make_a_bargain_rv);
        make_a_bargain_bt1 = findViewById(R.id.make_a_bargain_bt1);
        make_a_bargain_bt2 = findViewById(R.id.make_a_bargain_bt2);


        make_a_bargain_return.setOnClickListener(this);
        make_a_bargain_bt1.setOnClickListener(this);
        make_a_bargain_bt2.setOnClickListener(this);
        make_a_bargain_img2.setOnClickListener(this);
        make_a_bargain_tv6.setOnClickListener(this);

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
                        Glide.with(MakeABargainActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(make_a_bargain_img1);
                        make_a_bargain_tv1.setText(infoData.getCustomerName());
                        processData = myClientFragmentBean.getData().getProcessData();

                        make_a_bargain_tv2.setText(infoData.getProjectName());
                        make_a_bargain_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");

                        FinalContents.setPreparationId(processData.get(0).getPreparationId());
                        if (processData.get(0).getJsonDatas().size() == 0) {
                            make_a_bargain_tv4.setText("");
                            make_a_bargain_tv5.setText("");
                            make_a_bargain_tv6.setText("");
                            make_a_bargain_tv7.setText("");
                        } else {
                            make_a_bargain_tv4.setText(processData.get(0).getJsonDatas().get(0).getValue());
                            make_a_bargain_tv5.setText(processData.get(0).getJsonDatas().get(1).getValue());
                            make_a_bargain_tv6.setText(processData.get(0).getJsonDatas().get(2).getValue());
                            make_a_bargain_tv7.setText(processData.get(0).getJsonDatas().get(3).getValue());
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
        make_a_bargain_rv.setLayoutManager(manager);
        adapter = new MakeABargainAdapter();
        adapter.setListData(processData);

        make_a_bargain_rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //            TODO 返回上一层
            case R.id.make_a_bargain_return:
                finish();
                break;
            //            TODO 调单
            case R.id.make_a_bargain_bt1:
                intent = new Intent(MakeABargainActivity.this,FillInTransactionInformationActivity.class);
                intent.putExtra("name","填写成交信息");
                startActivity(intent);
                break;
            //            TODO 退单
            case R.id.make_a_bargain_bt2:
                intent = new Intent(MakeABargainActivity.this,ListOfBackActivity.class);
                startActivity(intent);
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
