package com.xcy.fzb.captain_team.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.ClientFragmentAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.ClientFragmentBean;
import com.xcy.fzb.captain_team.persente.MyClientData;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;
import com.xcy.fzb.captain_team.view.VisitingScheduleActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

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

    RecyclerView client_3_rv;
    ClientFragmentAdapter clientFragmentAdapter;
    private List<ClientFragmentBean.DataBean.RowsBean> rows;

    public MyClientFragment3() {
        // Required empty public constructor
    }


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

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        client_3_rv.setLayoutManager(manager);

        initData("");

    }

    private void initData(String name) {

        clientFragmentAdapter = new ClientFragmentAdapter();
        clientFragmentAdapter.setClick(this);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientFragmentBean> clientFragment = fzbInterface.getClientFragment(FinalContents.getUserID() + "",  "", name, "20");
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

                        client_3_rv.setAdapter(clientFragmentAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的客户（到访）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void ItemOnClick(int position) {


        Intent intent = new Intent(getContext(), VisitingScheduleActivity.class);
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
        if(judge.equals("到访")){
            Log.i("MyCL", "廣播");
            initData(name);
        }
    }
}
