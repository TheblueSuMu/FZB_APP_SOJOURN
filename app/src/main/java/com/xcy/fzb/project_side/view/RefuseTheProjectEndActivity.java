package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.InitiatedBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_side.adapter.InitiatedAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RefuseTheProjectEndActivity extends AllActivity implements View.OnClickListener, InitiatedAdapter.OnItemClick {

    RelativeLayout the_project_end_refuse_return;
    RecyclerView the_project_end_refuse_rv;

    LinearLayout the_project_end_refuse_ll1;
    LinearLayout the_project_end_refuse_ll2;
    LinearLayout the_project_end_refuse_ll3;
    LinearLayout the_project_end_refuse_ll4;
    LinearLayout the_project_end_refuse_ll5;
    LinearLayout the_project_end_refuse_ll6;

    InitiatedAdapter adapter;
    private List<InitiatedBean.DataBean.RowsBean> rows;
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_refuse_the_project_end);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);
        all_no_information = findViewById(R.id.all_no_information);
        the_project_end_refuse_return = findViewById(R.id.the_project_end_refuse_return);
        the_project_end_refuse_rv = findViewById(R.id.the_project_end_refuse_rv);

        the_project_end_refuse_ll1 = findViewById(R.id.the_project_end_refuse_ll1);
        the_project_end_refuse_ll2 = findViewById(R.id.the_project_end_refuse_ll2);
        the_project_end_refuse_ll3 = findViewById(R.id.the_project_end_refuse_ll3);
        the_project_end_refuse_ll4 = findViewById(R.id.the_project_end_refuse_ll4);
        the_project_end_refuse_ll5 = findViewById(R.id.the_project_end_refuse_ll5);
        the_project_end_refuse_ll6 = findViewById(R.id.the_project_end_refuse_ll6);

        initData(1);

        the_project_end_refuse_return.setOnClickListener(this);
        the_project_end_refuse_ll1.setOnClickListener(this);
        the_project_end_refuse_ll3.setOnClickListener(this);
        the_project_end_refuse_ll5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.the_project_end_refuse_return:
                finish();
                break;
            case R.id.the_project_end_refuse_ll1:
                the_project_end_refuse_ll2.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);

                initData(1);

                break;
            case R.id.the_project_end_refuse_ll3:
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);

                initData(2);

                break;
            case R.id.the_project_end_refuse_ll5:
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.VISIBLE);

                initData(3);

                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initData(int position) {
        adapter = new InitiatedAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        the_project_end_refuse_rv.setLayoutManager(manager);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<InitiatedBean> examinelistBean = fzbInterface.getExaminelistBean(FinalContents.getUserID(), "2", position + "","1000");
        examinelistBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InitiatedBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InitiatedBean examinelistBean) {
                        rows = examinelistBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            the_project_end_refuse_rv.setVisibility(View.VISIBLE);
                            adapter.setRows(rows);
                            adapter.setOnItemClick(RefuseTheProjectEndActivity.this);
                            the_project_end_refuse_rv.setAdapter(adapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            the_project_end_refuse_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        the_project_end_refuse_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "拒绝记录错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void Item(int position) {
        FinalContents.setPreparationId(rows.get(position).getPreparationId());
        FinalContents.setCustomerID(rows.get(position).getCustomerId());
        FinalContents.setStatus(rows.get(position).getStatus());
        Intent intent = new Intent(this, InitiatedActivity.class);
        FinalContents.setCustomerID(rows.get(position).getCustomerId());
        startActivity(intent);

    }
}
