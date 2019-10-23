package com.xcy.fzb.project_side.fragment;


import android.annotation.SuppressLint;
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
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.GoodNewsAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.GoodNewsBean;
import com.xcy.fzb.project_side.presente.StatusBar;
import com.xcy.fzb.project_side.service.MyService;

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
//TODO 消息喜报
public class GoodNewsFragment extends Fragment {

    RecyclerView good_news_rv;
    String url;
    GoodNewsAdapter adapter;

    public GoodNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_good_news, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        good_news_rv = getActivity().findViewById(R.id.good_news_rv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        good_news_rv.setLayoutManager(manager);

        initData();

    }

    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<GoodNewsBean> userMessage = fzbInterface.getGoodNewsBeanList(FinalContents.getUserID(),FinalContents.getCityID(),"5");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(GoodNewsBean goodNewsBean) {
                        GoodNewsBean.DataBean data1 = goodNewsBean.getData();
                        List<GoodNewsBean.DataBean.RowsBean> rows = data1.getRows();
                        adapter = new GoodNewsAdapter();
                        adapter.setRows(rows);
                        good_news_rv.setAdapter(adapter);

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
