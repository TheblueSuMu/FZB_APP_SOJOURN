package com.xcy.fzb.shopping_guide.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.AllFragment;
import com.xcy.fzb.all.modle.TaskDetailsBean;
import com.xcy.fzb.all.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AttentionFragment extends AllFragment {

    private View view;
    private TextView attention_content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_guide_fragment_attention, null);
        attention_content = view.findViewById(R.id.attention_content);
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
        Observable<TaskDetailsBean> userMessage = fzbInterface.getRoutetimeDetails(FinalContents.getUserID(),FinalContents.getRouteTimeId());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(TaskDetailsBean taskDetailsBean) {
                        attention_content.setText(Html.fromHtml(taskDetailsBean.getData().getRouteTimeInfo().getMatter().getMatterComment()));
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