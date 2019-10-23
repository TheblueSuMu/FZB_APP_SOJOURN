package com.xcy.fzb.all.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.SpellingMassAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.SpellingDataBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class All_JourneyFragment extends AllFragment {
    private RecyclerView journey_rv;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_guide_fragment_journey, null);
        journey_rv = view.findViewById(R.id.journey_rv);
        initData();
        return view;
    }


    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SpellingDataBean> spellingDataBean = fzbInterface.getSpellingDataBean(FinalContents.getRouteTimeId());
        spellingDataBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpellingDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpellingDataBean spellingDataBean) {
                        journey_rv.setVisibility(View.VISIBLE);
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        journey_rv.setLayoutManager(layoutManager);
                        SpellingMassAdapter recyclerAdapter = new SpellingMassAdapter();
                        recyclerAdapter.setPlanning(spellingDataBean.getData().getPlanning());
                        journey_rv.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("打印拼团详情数据", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
