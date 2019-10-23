package com.xcy.fzb.shopping_guide.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.adapter.TaskListAdapter;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.TaskListBean;
import com.xcy.fzb.shopping_guide.persente.MyLinearLayoutManager;
import com.xcy.fzb.shopping_guide.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskHistoryListActivity extends AllActivity{

    private RelativeLayout task_history_back;
    private RecyclerView task_history_rv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_history_list);
        initView();
    }

    private void initView(){
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

    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TaskListBean> userMessage = fzbInterface.getRouteTimeList(FinalContents.getUserID(),"60");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(TaskListBean taskListBean) {
                        if (taskListBean.getCode().equals("1")) {
                            TaskListBean.DataBean dataBean = taskListBean.getData();
                            List<TaskListBean.DataBean.RowsBean> rows = dataBean.getRows();
                            //在此处修改布局排列方向
                            task_history_rv.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(TaskHistoryListActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            task_history_rv.setLayoutManager(layoutManager);
                            TaskListAdapter recyclerAdapter = new TaskListAdapter(rows);
                            task_history_rv.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }else {
                            task_history_rv.setVisibility(View.GONE);
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
