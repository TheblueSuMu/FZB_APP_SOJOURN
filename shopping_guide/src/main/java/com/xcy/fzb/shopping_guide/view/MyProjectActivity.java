package com.xcy.fzb.shopping_guide.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.adapter.HomeRecyclerAdapter;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.HomeListBean;
import com.xcy.fzb.shopping_guide.persente.MyLinearLayoutManager;
import com.xcy.fzb.shopping_guide.persente.StatusBar;
import com.xcy.fzb.shopping_guide.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyProjectActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView my_project_img;
    LinearLayout my_project_l1;
    LinearLayout my_project_l2;
    LinearLayout my_project_ll1;
    LinearLayout my_project_ll2;

    RecyclerView my_project_recyler;
    String projectType = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);
        StatusBar.makeStatusBarTransparent(this);
        initView();

    }

    private void initView() {

        my_project_img = findViewById(R.id.my_project_img);
        my_project_l1 = findViewById(R.id.my_project_l1);
        my_project_l2 = findViewById(R.id.my_project_l2);
        my_project_ll1 = findViewById(R.id.my_project_ll1);
        my_project_ll2 = findViewById(R.id.my_project_ll2);
        my_project_recyler = findViewById(R.id.my_project_recyler);

        my_project_img.setOnClickListener(this);
        my_project_l1.setOnClickListener(this);
        my_project_l2.setOnClickListener(this);

        initData();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_project_img:
                finish();
                break;
            case R.id.my_project_l1:
                my_project_ll1.setVisibility(View.VISIBLE);
                my_project_ll2.setVisibility(View.GONE);
                //TODO 海外
                projectType = "2";
                initData();
                break;
            case R.id.my_project_l2:
                my_project_ll1.setVisibility(View.GONE);
                my_project_ll2.setVisibility(View.VISIBLE);
                //TODO 旅居
                projectType = "3";
                initData();
                break;
        }
    }


    @SuppressLint("WrongConstant")
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HomeListBean> userMessage = fzbInterface.getHomeBeanList(FinalContents.getUserID(),projectType);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(HomeListBean homeListBean) {
                        if (homeListBean.getCode().equals("1")) {
                            HomeListBean.DataBean homeListBeanData = homeListBean.getData();
                            List<HomeListBean.DataBean.RowsBean> homeListBeanDataRows = homeListBeanData.getRows();
                            //在此处修改布局排列方向
                            my_project_recyler.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(MyProjectActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            my_project_recyler.setLayoutManager(layoutManager);
                            HomeRecyclerAdapter recyclerAdapter = new HomeRecyclerAdapter(homeListBeanDataRows);
                            my_project_recyler.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }else {
                            my_project_recyler.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
