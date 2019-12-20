package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
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

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.RecyclerAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.HotBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerViewActivity extends AllActivity {
    private TextView title;
    private LinearLayout back;
    private RecyclerView recyclerView;
    private List<HotBean.DataBean.RowsBean> hotlist = new ArrayList<>();
    private String nation = "";
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            nation = getIntent().getStringExtra("nation");
            initfvb();
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

    private void initfvb() {

        StatusBar.makeStatusBarTransparent(this);
        all_no_information = findViewById(R.id.all_no_information);
        title = findViewById(R.id.recycler_title);
        back = findViewById(R.id.recycler_back);
        recyclerView = findViewById(R.id.recycler_rv);
        title.setText(nation + "项目");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void initView() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> hotList = fzbInterface.getHotBean(FinalContents.getUserID(), FinalContents.getCityID(), nation,"1000");
        hotList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        HotBean.DataBean hotBeanData = hotBean.getData();
                        hotlist = hotBeanData.getRows();

                        if (hotlist.size() == 0) {
                            recyclerView.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        } else {
                            //在此处修改布局排列方向
                            all_no_information.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(RecyclerViewActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);
                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(hotlist);
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        recyclerView.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("MyCL","RecyclerViewActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
