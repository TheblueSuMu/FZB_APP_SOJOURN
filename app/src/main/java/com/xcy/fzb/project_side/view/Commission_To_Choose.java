package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.CityContents;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.BrokerBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.ToastUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_side.adapter.TimeRangeAdapter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Commission_To_Choose extends AllActivity {

    private RecyclerView commission_to_choose_recycler;
    private RelativeLayout commission_to_choose_return;
    private String currentDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commission_to_choose);
        currentDate = getIntent().getStringExtra("time");
        initfvb();
    }

    private void initfvb(){
        commission_to_choose_recycler = findViewById(R.id.commission_to_choose_recycler);
        commission_to_choose_return = findViewById(R.id.commission_to_choose_return);

        commission_to_choose_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initTimeData();
    }

    private void initTimeData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BrokerBean> userMessage = fzbInterface.getTimeRange(currentDate, FinalContents.getProjectID(), FinalContents.getJJrID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final BrokerBean brokerBean) {
                        if (brokerBean.getData().size() != 0) {
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(Commission_To_Choose.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            layoutManager.setScrollEnabled(false);
                            commission_to_choose_recycler.setLayoutManager(layoutManager);
                            TimeRangeAdapter timeRangeAdapter = new TimeRangeAdapter(brokerBean.getData());
                            commission_to_choose_recycler.setNestedScrollingEnabled(false);
                            commission_to_choose_recycler.setAdapter(timeRangeAdapter);
                            timeRangeAdapter.setOnItemClickListener(new TimeRangeAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    CityContents.setCommissionFormat(brokerBean.getData().get(postion).getCommissionFormat());
//                                    project_brokerage.setText(brokerBean.getData().get(postion).getCommissionFormat());
                                    FinalContents.setCommissionId(brokerBean.getData().get(postion).getId());
                                    finish();
                                }
                            });
                            timeRangeAdapter.notifyDataSetChanged();
                        }else {
                            ToastUtil.showLongToast(Commission_To_Choose.this,"该成交时间下没有可用的佣金内容");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(Commission_To_Choose.this,"佣金列表数据获取错误");
                        Log.i("佣金列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
