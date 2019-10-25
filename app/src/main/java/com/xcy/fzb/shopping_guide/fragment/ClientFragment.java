package com.xcy.fzb.shopping_guide.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.AllFragment;
import com.xcy.fzb.all.modle.CustomerListBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.KeyUtils;
import com.xcy.fzb.shopping_guide.adapter.ClientAdapter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientFragment extends AllFragment {
    private View view;
    private RecyclerView client_rv;
    private EditText client_search;
    private String search;
    int isnum = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_guide_fragment_client, null);
        client_rv = view.findViewById(R.id.client_rv);
        client_search = view.findViewById(R.id.client_search);
        initData();
        client_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {

                    KeyUtils.hideKeyboard(client_search);
                    search = client_search.getText().toString();
                    if (isnum == 0) {
                        initData();
                        isnum = 1;
                    }

                    return true;
                }
                return false;
            }
        });
        return view;
    }

    private void initData() {
        Log.i("导购","数据：用户ID："+FinalContents.getUserID());
        Log.i("导购","数据：行程路线ID："+FinalContents.getRouteTimeId());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CustomerListBean> userMessage = fzbInterface.getcustomerList(FinalContents.getUserID(), FinalContents.getRouteTimeId(), search,"1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(CustomerListBean customerListBean) {
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(view.getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        client_rv.setLayoutManager(layoutManager);
                        ClientAdapter recyclerAdapter = new ClientAdapter(customerListBean.getData().getRows());
                        client_rv.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
                        isnum = 0;
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

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
