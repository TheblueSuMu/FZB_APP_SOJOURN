package com.xcy.fzb.project_side.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.xcy.fzb.all.adapter.ProcessDataAdapter;
import com.xcy.fzb.all.api.CityContents;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.modle.ClientFragmentBean;
import com.xcy.fzb.all.modle.ProcessDataBean;
import com.xcy.fzb.all.persente.MyClientData;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.ReviewTheSuccessActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
//TODO 到访
public class MyClientFragment3 extends Fragment implements ClientFragmentAdapter.Click {

    PtrClassicFrameLayout ptrClassicFrameLayout;

    RecyclerView client_3_rv;
    ClientFragmentAdapter clientFragmentAdapter;
    private List<ClientFragmentBean.DataBean.RowsBean> rows;
    private ProcessDataAdapter processDataAdapter;
    private ImageView all_no_information;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_my_client_fragment3, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        client_3_rv = getActivity().findViewById(R.id.client_3_rv);
        all_no_information = getActivity().findViewById(R.id.all_no_information);


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        client_3_rv.setLayoutManager(manager);

        ptrClassicFrameLayout = (PtrClassicFrameLayout) getActivity().findViewById(R.id.store_house_ptr_frame_4);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        listterner.process("3"); // 3.1 执行回调
                        if (FinalContents.getZhuanyuan().equals("1")) {
                            initData2("");
                        } else {
                            initData("");
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

    private void initData(String name) {

        clientFragmentAdapter = new ClientFragmentAdapter();
        clientFragmentAdapter.setClick(this);

        String ProjectID = "";

        if (FinalContents.getDetails().equals("项目详情")) {
            ProjectID = FinalContents.getProjectID();
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientFragmentBean> clientFragment = fzbInterface.getClientFragment(FinalContents.getUserID() + "", ProjectID, name, "20","1000", NewlyIncreased.getTag(), NewlyIncreased.getStartDate(), NewlyIncreased.getEndDate());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientFragmentBean clientFragmentBean) {
                        rows = clientFragmentBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            client_3_rv.setVisibility(View.VISIBLE);
                            clientFragmentAdapter.setRows(rows);
                            client_3_rv.setAdapter(clientFragmentAdapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            client_3_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        client_3_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "我的客户（到访）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initData2(String name) {
        processDataAdapter = new ProcessDataAdapter();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ProcessDataBean> clientFragment = fzbInterface.getProcessData(FinalContents.getStoreId(), FinalContents.getAgentId(),"20", name + "", FinalContents.getUserID(),"1000", NewlyIncreased.getTag(), NewlyIncreased.getStartDate(), NewlyIncreased.getEndDate());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProcessDataBean>() {

                    private List<ProcessDataBean.DataBean.RowsBean> rowsList;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProcessDataBean processDataBean) {
                        rowsList = processDataBean.getData().getRows();
                        if (rowsList.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            client_3_rv.setVisibility(View.VISIBLE);
                            processDataAdapter.setRows(rowsList);
                            client_3_rv.setAdapter(processDataAdapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            client_3_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        client_3_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "我的客户（报备）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    @Override
    public void ItemOnClick(int position) {
        CityContents.setReadRecordStatus("20");
        Intent intent = new Intent(getContext(), ReviewTheSuccessActivity.class);
        FinalContents.setCustomerID(rows.get(position).getCustomerId());
        FinalContents.setPreparationId(rows.get(position).getPreparationId());
        startActivity(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 103, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(MyClientData myClientName) {
        String name = myClientName.getName();
        String judge = myClientName.getJudge();
        if (FinalContents.getZhuanyuan().equals("1")) {
            if (judge.equals("到访")) {
                initData2(name);
            }
        } else {
            if (judge.equals("到访")) {
                initData(name);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        if (FinalContents.getZhuanyuan().equals("1")) {
            initData2("");
        } else {
            initData("");
        }

    }

    // 2.1 定义用来与外部activity交互，获取到宿主activity
    private FragmentInteraction listterner;

    // 1 定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        void process(String str);
    }

    // 当FRagmen被加载到activity的时候会被回调
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof FragmentInteraction) {
            listterner = (FragmentInteraction)activity; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    //把传递进来的activity对象释放掉
    @Override
    public void onDetach() {
        super.onDetach();
        listterner = null;
    }
}
