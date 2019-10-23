package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.SpellingMassTimeAdaptewr;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.SpellingMassTimeBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
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

public class SpellingMassTimeActivity extends AllActivity {

    private RelativeLayout task_history_back;
    private RecyclerView task_history_rv;
    private List<SpellingMassTimeBean.DataBean.RowsBean> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling_mass_time);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        task_history_back = findViewById(R.id.task_history_back);
        task_history_rv = findViewById(R.id.task_history_rv);
        task_history_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();

    }

    private void initData() {
        Log.i("MyCL","FinalContents.getProjectID()" + FinalContents.getProjectID());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SpellingMassTimeBean> spellingMassTimeBean = fzbInterface.getSpellingMassTimeBean(FinalContents.getProjectID(),"1000");
        spellingMassTimeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpellingMassTimeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("MyCL","onSubscribe");
                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(SpellingMassTimeBean taskListBean) {

                        rows = taskListBean.getData().getRows();
                        Log.i("MyCL", "拼团测试：" + rows.size());
                        task_history_rv.setVisibility(View.VISIBLE);
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(SpellingMassTimeActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        task_history_rv.setLayoutManager(layoutManager);
                        SpellingMassTimeAdaptewr recyclerAdapter = new SpellingMassTimeAdaptewr();
                        recyclerAdapter.setRows(rows);
                        task_history_rv.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
