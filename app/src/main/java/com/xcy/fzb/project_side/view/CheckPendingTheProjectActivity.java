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
import com.xcy.fzb.all.modle.CheckPendingBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_side.adapter.CheckPendingTheProjectEndAdapter;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckPendingTheProjectActivity extends AllActivity implements View.OnClickListener{

    PtrClassicFrameLayout mPtrClassicFrameLayout;

    RelativeLayout the_project_end_refuse_return;
    RecyclerView the_project_end_refuse_rv;

    LinearLayout the_project_end_refuse_ll1;
    LinearLayout the_project_end_refuse_ll2;
    LinearLayout the_project_end_refuse_ll3;
    LinearLayout the_project_end_refuse_ll4;
    LinearLayout the_project_end_refuse_ll5;
    LinearLayout the_project_end_refuse_ll6;
    LinearLayout the_project_end_refuse_ll7;
    LinearLayout the_project_end_refuse_ll8;

    CheckPendingTheProjectEndAdapter adapter;
    private List<CheckPendingBean.DataBean.RowsBean> rows;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pending_the_project);
        FinalContents.setJuJue("拒绝记录");
        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        mPtrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.store_house_ptr_frame_2);

        the_project_end_refuse_return = findViewById(R.id.the_project_end_return);
        the_project_end_refuse_rv = findViewById(R.id.the_project_end_rv);

        the_project_end_refuse_ll1 = findViewById(R.id.the_project_end_ll1);
        the_project_end_refuse_ll2 = findViewById(R.id.the_project_end_ll2);
        the_project_end_refuse_ll3 = findViewById(R.id.the_project_end_ll3);
        the_project_end_refuse_ll4 = findViewById(R.id.the_project_end_ll4);
        the_project_end_refuse_ll5 = findViewById(R.id.the_project_end_ll5);
        the_project_end_refuse_ll6 = findViewById(R.id.the_project_end_ll6);
        the_project_end_refuse_ll7 = findViewById(R.id.the_project_end_ll7);
        the_project_end_refuse_ll8 = findViewById(R.id.the_project_end_ll8);

        the_project_end_refuse_return.setOnClickListener(this);
        the_project_end_refuse_ll1.setOnClickListener(this);
        the_project_end_refuse_ll3.setOnClickListener(this);
        the_project_end_refuse_ll5.setOnClickListener(this);
        the_project_end_refuse_ll7.setOnClickListener(this);

        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrClassicFrameLayout.refreshComplete();
                        mPtrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");

                        if(the_project_end_refuse_ll2.getVisibility() == View.VISIBLE){
                            initData(1);
                        }else if(the_project_end_refuse_ll4.getVisibility() == View.VISIBLE){
                            initData(2);
                        }else if(the_project_end_refuse_ll6.getVisibility() == View.VISIBLE){
                            initData(3);
                        }else if(the_project_end_refuse_ll8.getVisibility() == View.VISIBLE){
                            initData(4);
                        }

                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        initData(1);

    }

    @SuppressLint("WrongConstant")
    private void initData(int position) {

        String ProjectID = "";

        if (FinalContents.getDetails().equals("项目详情")) {
            ProjectID = FinalContents.getProjectID();
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        the_project_end_refuse_rv.setLayoutManager(manager);
        adapter = new CheckPendingTheProjectEndAdapter();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CheckPendingBean> clientFragmentBean = fzbInterface.getToAuditList(FinalContents.getUserID(),ProjectID,"2",position,"1000");
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckPendingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CheckPendingBean checkPendingBean) {
                        rows = checkPendingBean.getData().getRows();
                        adapter.setRows(rows);
                        adapter.setOnItemClick(new CheckPendingTheProjectEndAdapter.OnItemClick() {
                            @Override
                            public void itemItem(int position) {
                                intent = new Intent(CheckPendingTheProjectActivity.this, CheckPendingActivity.class);

                                if (rows.get(position).getRelatedData().equals("认筹") || rows.get(position).getRelatedData().equals("成功")) {
                                    intent.putExtra("Mycheck", "2");
                                } else {
                                    intent.putExtra("Mycheck", "1");
                                }
                                if (the_project_end_refuse_ll2.getVisibility() == View.VISIBLE) {
                                    intent.putExtra("name", "报备");
                                    FinalContents.setNumS(1);
                                } else if (the_project_end_refuse_ll4.getVisibility() == View.VISIBLE) {
                                    FinalContents.setNumS(3);
                                    intent.putExtra("name", "到访");
                                } else if (the_project_end_refuse_ll6.getVisibility() == View.VISIBLE) {
                                    FinalContents.setNumS(3);
                                    intent.putExtra("name", "认筹");
                                } else if (the_project_end_refuse_ll8.getVisibility() == View.VISIBLE) {
                                    FinalContents.setNumS(4);
                                    intent.putExtra("name", "失效");
                                }

                                FinalContents.setPreparationId(rows.get(position).getPreparationId());
                                FinalContents.setCustomerID(rows.get(position).getCustomerId());
                                FinalContents.setStatus(rows.get(position).getStatus());
                                Log.i("MyCL", "getPreparationId：" + rows.get(position).getPreparationId());
                                Log.i("MyCL", "getCustomerId：" + rows.get(position).getCustomerId());
                                startActivity(intent);
                            }
                        });
                        the_project_end_refuse_rv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "待我审核拒绝记录的错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.the_project_end_return:

                finish();
                break;
            case R.id.the_project_end_ll1:
                the_project_end_refuse_ll2.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);
                the_project_end_refuse_ll8.setVisibility(View.GONE);
                initData(1);
                break;
            case R.id.the_project_end_ll3:
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);
                the_project_end_refuse_ll8.setVisibility(View.GONE);
                initData(2);
                break;
            case R.id.the_project_end_ll5:
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll8.setVisibility(View.GONE);
                initData(3);
                break;
            case R.id.the_project_end_ll7:
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);
                the_project_end_refuse_ll8.setVisibility(View.VISIBLE);
                initData(4);
                break;

        }

    }
}
