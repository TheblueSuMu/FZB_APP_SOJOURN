package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.VideoAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.SellingPointsBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//TODO 项目视频
public class ProjectVideoActivity extends AllActivity implements View.OnClickListener {

    RecyclerView video_rv;
    LinearLayout video_return;
    VideoAdapter videoAdapter;
    private List<SellingPointsBean.DataBean.RowsBean> rows;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_video);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
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

    @SuppressLint("WrongConstant")
    private void initView() {

        StatusBar.makeStatusBarTransparent(this);
        video_return = findViewById(R.id.video_return);
        video_rv = findViewById(R.id.video_rv);

        video_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        video_rv.setLayoutManager(manager);

        initData();    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();

        MyService myService = build.create(MyService.class);
        final Observable<SellingPointsBean> sellingPoints = myService.getSellingPoints("2", FinalContents.getUserID(), FinalContents.getProjectID());
        sellingPoints.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SellingPointsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SellingPointsBean sellingPointsBean) {
                        rows = sellingPointsBean.getData().getRows();
                        videoAdapter = new VideoAdapter(rows);
                        video_rv.setAdapter(videoAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "项目卖点错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    @Override
    public void onClick(View view) {

    }

}
