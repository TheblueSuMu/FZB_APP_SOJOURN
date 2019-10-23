package com.xcy.fzb.project_side.view;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.BrokerageAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.BorkerageDownBean;
import com.xcy.fzb.project_side.modle.BorkerageUpBean;
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


//TODO 我的佣金
public class MyBrokerageActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout myBrokerageImg;
    TextView myBrokerageTotalText;
    TextView myBrokerageMbText;
    TextView myBrokerageCaText;
    TextView myBrokerageWjText;
    LinearLayout myBrokerageLjText;
    LinearLayout myBrokerageHwText;
    LinearLayout myBrokerageCsText;
    LinearLayout myBrokerageLj;
    LinearLayout myBrokerageHw;
    LinearLayout myBrokerageCs;

    RecyclerView my_brokerage_rv;
    BrokerageAdapter adapter;

    RelativeLayout myBrokerage_rl;

    EditText my_brokerage_et;

//    FragmentManager fragmentManager;
//    FragmentTransaction transaction;

//    OverseasFragment overseasFragment = new OverseasFragment();
//    SojournFragment sojournFragment = new SojournFragment();
//    BrokerageCityFragment brokerageCityFragment = new BrokerageCityFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_brokerage);
        StatusBar.makeStatusBarTransparent(this);

        initView();

    }

    public void initView() {

        //TODO 设置导航栏、标题栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        myBrokerageImg = findViewById(R.id.my_brokerage_img);
        myBrokerageTotalText = findViewById(R.id.my_brokerage_total_text);
        myBrokerageMbText = findViewById(R.id.my_brokerage_mb_text);
        myBrokerageCaText = findViewById(R.id.my_brokerage_ca_text);
        myBrokerageWjText = findViewById(R.id.my_brokerage_wj_text);
        myBrokerageLjText = findViewById(R.id.my_brokerage_lj_text);
        myBrokerageHwText = findViewById(R.id.my_brokerage_hw_text);
        myBrokerageCsText = findViewById(R.id.my_brokerage_cs_text);
        myBrokerageCs = findViewById(R.id.my_brokerage_cs);
        myBrokerageLj = findViewById(R.id.my_brokerage_lj);
        myBrokerageHw = findViewById(R.id.my_brokerage_hw);

        my_brokerage_et = findViewById(R.id.my_brokerage_et);
        my_brokerage_rv = findViewById(R.id.my_brokerage_rv);
        myBrokerage_rl = findViewById(R.id.myBrokerage_rl);

//        fragmentManager = getSupportFragmentManager();
//        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.my_brokerage_fl, brokerageCityFragment);
//        transaction.commit();

        myBrokerageImg.setOnClickListener(this);
        myBrokerageCsText.setOnClickListener(this);
        myBrokerageLjText.setOnClickListener(this);
        myBrokerageHwText.setOnClickListener(this);

        my_brokerage_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    String s = my_brokerage_et.getText().toString();

                    if (myBrokerageLj.getVisibility() == View.VISIBLE) {
                        initDataDown("1", s);
                    } else if (myBrokerageHw.getVisibility() == View.VISIBLE) {
                        initDataDown("2", s);
                    } else if (myBrokerageCs.getVisibility() == View.VISIBLE) {
                        initDataDown("3", s);
                    }
                    return true;
                }
                return false;
            }

        });

        initDataUp();
        String s = my_brokerage_et.getText().toString();
        initDataDown("1", s);

    }

    private void initDataUp() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BorkerageUpBean> test = fzbInterface.getBrokerageUp(FinalContents.getUserID());
        test.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BorkerageUpBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BorkerageUpBean borkerageUpBean) {
                        BorkerageUpBean.DataBean data = borkerageUpBean.getData();
                        myBrokerageTotalText.setText(data.getTotalAmount() + "");
                        myBrokerageMbText.setText(data.getSumCount() + "");
                        myBrokerageCaText.setText(data.getAlreadyAmount() + "");
                        myBrokerageWjText.setText(data.getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金上部" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        String s = my_brokerage_et.getText().toString();
        if (id == R.id.my_brokerage_img) {//返回上一级
            finish();
        } else if (id == R.id.my_brokerage_cs_text) {//旅居房产
            myBrokerageLj.setVisibility(View.GONE);
            myBrokerageHw.setVisibility(View.GONE);
            myBrokerageCs.setVisibility(View.VISIBLE);
            initDataDown("1", s);
//            fragmentManager = getSupportFragmentManager();
//            transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.my_brokerage_fl, brokerageCityFragment);
//            transaction.commit();
        } else if (id == R.id.my_brokerage_lj_text) {//旅居房产
            myBrokerageLj.setVisibility(View.VISIBLE);
            myBrokerageHw.setVisibility(View.GONE);
            myBrokerageCs.setVisibility(View.GONE);
            initDataDown("2", s);
//
//            fragmentManager = getSupportFragmentManager();
//            transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.my_brokerage_fl, sojournFragment);
//            transaction.commit();
        } else if (id == R.id.my_brokerage_hw_text) {//海外房产

            myBrokerageHw.setVisibility(View.VISIBLE);
            myBrokerageLj.setVisibility(View.GONE);
            myBrokerageCs.setVisibility(View.GONE);
            initDataDown("3", s);
//            fragmentManager = getSupportFragmentManager();
//            transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.my_brokerage_fl, overseasFragment);
//            transaction.commit();
        }

    }


    private void initDataDown(String projectType, String str) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BorkerageDownBean> test = fzbInterface.getBrokerageDown("43dea5335a1b4cb6bf15782a3be87c6a", projectType, str);
        test.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BorkerageDownBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BorkerageDownBean borkerageDownBean) {
                        List<BorkerageDownBean.DataBean.RowsBean> rows = borkerageDownBean.getData().getRows();


                        int total = borkerageDownBean.getData().getTotal();
                        if (total == 0) {
                            myBrokerage_rl.setVisibility(View.VISIBLE);
                        } else {
                            adapter = new BrokerageAdapter();
                            adapter.setRows(rows);
                            my_brokerage_rv.setAdapter(adapter);
                            myBrokerage_rl.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金下半部（海外）" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
