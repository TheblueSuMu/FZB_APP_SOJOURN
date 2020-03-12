package com.xcy.fzb.all.fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.ClientFragmentAdapter;
import com.xcy.fzb.all.adapter.ReportProcessAdapter;
import com.xcy.fzb.all.api.CityContents;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.modle.ClientFragmentBean;
import com.xcy.fzb.all.modle.ReportProcessBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.ReviewTheSuccessActivity;

import org.greenrobot.eventbus.EventBus;

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

/**
 * A simple {@link Fragment} subclass.
 */
//TODO 成交
public class MyClientFragment6 extends Fragment implements ClientFragmentAdapter.Click {

    RecyclerView client_6_rv;
    ClientFragmentAdapter clientFragmentAdapter;
    private List<ClientFragmentBean.DataBean.RowsBean> rows;
    private ReportProcessAdapter reportProcessAdapter;
    private List<ReportProcessBean.DataBean.RowsBean> rowsList;
    private ProgressDialog progressDialog;
    private PtrClassicFrameLayout mPtrClassicFrameLayout;
    private ImageView all_no_information;

    public MyClientFragment6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_my_client_fragment6, container, false);
    }
    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        client_6_rv = getActivity().findViewById(R.id.client_6_rv);
        all_no_information = getActivity().findViewById(R.id.all_no_information);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        client_6_rv.setLayoutManager(manager);

//        if (FinalContents.getQuanceng().equals("1")) {
//            initData2();
//        } else{
//            initData();
//        }

        mPtrClassicFrameLayout = (PtrClassicFrameLayout) getActivity().findViewById(R.id.store_house_ptr_frame_7);
        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrClassicFrameLayout.refreshComplete();
                        mPtrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");

                        if (FinalContents.getZhuanyuan().equals("1")) {
                            initData2();
                        }else if (FinalContents.getQuanceng().equals("1")) {
                            initData2();
                        }else {
                            initData();
                        }
                        EventBus.getDefault().post("修改");
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }
    private void initData() {
        clientFragmentAdapter = new ClientFragmentAdapter();
        clientFragmentAdapter.setClick(this);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientFragmentBean> clientFragment = fzbInterface.getClientFragment(FinalContents.getUserID() + "",  "", "", "60","1000", NewlyIncreased.getTag(), NewlyIncreased.getStartDate(), NewlyIncreased.getEndDate());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientFragmentBean clientFragmentBean) {
                        rows = clientFragmentBean.getData().getRows();

                        clientFragmentAdapter.setRows(rows);

                        client_6_rv.setAdapter(clientFragmentAdapter);
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            client_6_rv.setVisibility(View.VISIBLE);
                            clientFragmentAdapter.setRows(rows);
                            client_6_rv.setAdapter(clientFragmentAdapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            client_6_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        client_6_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "我的客户（成交）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initData2() {
        reportProcessAdapter = new ReportProcessAdapter();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("SZ","FinalContents.getAgentId()：" + FinalContents.getAgentId());
        Log.i("SZ","FinalContents.getUserID()：" + FinalContents.getUserID());
        Log.i("SZ","FinalContents.getMySelf()：" + FinalContents.getMySelf());
        Observable<ReportProcessBean> clientFragment = fzbInterface.getReportProcess(FinalContents.getAgentId(), "60", "",FinalContents.getUserID(),"1000",FinalContents.getMySelf(), NewlyIncreased.getTag(), NewlyIncreased.getStartDate(), NewlyIncreased.getEndDate());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReportProcessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReportProcessBean reportProcessBean) {
                        rowsList = reportProcessBean.getData().getRows();
                        if (rowsList.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            client_6_rv.setVisibility(View.VISIBLE);
                            reportProcessAdapter.setRows(rowsList);
                            client_6_rv.setAdapter(reportProcessAdapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            client_6_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        client_6_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "我的客户（报备）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void ItemOnClick(int position) {
        CityContents.setReadRecordStatus("60");
        FinalContents.setPreparationId(rows.get(position).getPreparationId());
        Intent intent = new Intent(getContext(), ReviewTheSuccessActivity.class);
        FinalContents.setCustomerID(rows.get(position).getCustomerId());
        FinalContents.setPreparationId(rows.get(position).getPreparationId());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (FinalContents.getZhuanyuan().equals("1")) {
            initData2();
        }else if (FinalContents.getQuanceng().equals("1")) {
            initData2();
        }else {
            initData();
        }

    }
}
