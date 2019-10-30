package com.xcy.fzb.project_attache.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.CommissionListBean;
import com.xcy.fzb.all.modle.CommissionUpBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_attache.adapter.CommissionListAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommissionActivity extends AllActivity implements View.OnClickListener {

    LinearLayout commission_return;

    TextView commission_tv1;
    TextView commission_tv2;
    TextView commission_tv3;

    RecyclerView commission_rv;

    LinearLayout commission_ll1;
    LinearLayout commission_ll2;
    LinearLayout commission_ll3;
    LinearLayout commission_ll4;

    CheckBox commission_cb;
    String ifCheckBox = "";

    EditText commission_et;
    private List<CommissionListBean.DataBean.RowsBean> rows;
    CommissionListAdapter adapter;
    private String s = "";
    private RelativeLayout myBrokerage_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_commission);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            Toast.makeText(this, "当前无网络，请检查网络后再进行登录", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        commission_return = findViewById(R.id.commission_return);
        commission_tv1 = findViewById(R.id.commission_tv1);
        commission_tv2 = findViewById(R.id.commission_tv2);
        commission_tv3 = findViewById(R.id.commission_tv3);
        commission_rv = findViewById(R.id.commission_rv);
        commission_ll1 = findViewById(R.id.commission_ll1);
        commission_ll2 = findViewById(R.id.commission_ll2);
        commission_ll3 = findViewById(R.id.commission_ll3);
        commission_ll4 = findViewById(R.id.commission_ll4);
        commission_et = findViewById(R.id.commission_et);
        commission_cb = findViewById(R.id.commission_cb);
        myBrokerage_rl = findViewById(R.id.myBrokerage_rl);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        commission_rv.setLayoutManager(manager);

        commission_return.setOnClickListener(this);
        commission_ll1.setOnClickListener(this);
        commission_ll3.setOnClickListener(this);

        initDataUp();

        commission_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commission_cb.isChecked()){
                    ifCheckBox = "0";
                    if(commission_ll2.getVisibility() == View.VISIBLE){
                        initData("3", s);
                    }else {
                        initData("2", s);
                    }
                }else {
                    ifCheckBox = "";
                    if(commission_ll2.getVisibility() == View.VISIBLE){
                        initData("3", s);
                    }else {
                        initData("2", s);
                    }
                }
            }
        });

    }

    private void initDataUp() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<CommissionUpBean> commissionUpBeanObservable = myService.getcommissionUpBean(FinalContents.getUserID(),FinalContents.getCompanyId(),FinalContents.getStoreId(),FinalContents.getAgentId(),"0");
        commissionUpBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionUpBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("MyCL","1");
                    }

                    @Override
                    public void onNext(CommissionUpBean commissionUpBean) {
                        Log.i("MyCL","2");
                        commission_tv1.setText(commissionUpBean.getData().getTotalAmount());
                        commission_tv2.setText(commissionUpBean.getData().getAlreadyAmount());
                        commission_tv3.setText(commissionUpBean.getData().getNotAmount());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        initData("3", s);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.commission_return:
                finish();
                break;
            case R.id.commission_ll1:
                s = commission_et.getText().toString();
                commission_ll2.setVisibility(View.VISIBLE);
                commission_ll4.setVisibility(View.INVISIBLE);

                initData("3", s);

                break;
            case R.id.commission_ll3:
                s = commission_et.getText().toString();
                commission_ll2.setVisibility(View.INVISIBLE);
                commission_ll4.setVisibility(View.VISIBLE);

                initData("2", s);

                break;


        }

    }

    private void initData(String projectType, String search) {

        adapter = new CommissionListAdapter();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<CommissionListBean> commissionListBean = myService.getCommissionListBean(FinalContents.getUserID(), projectType, search, ifCheckBox,"1000");
        commissionListBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommissionListBean commissionListBean) {
                        rows = commissionListBean.getData().getRows();
                        Log.i("长度","长度："+rows.size());
                        if (rows.size() != 0) {
                            myBrokerage_rl.setVisibility(View.GONE);
                            commission_rv.setVisibility(View.VISIBLE);
                            adapter.setRows(rows);
                            commission_rv.setAdapter(adapter);
                        }else {
                            commission_rv.setVisibility(View.GONE);
                            myBrokerage_rl.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        commission_rv.setVisibility(View.GONE);
                        myBrokerage_rl.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
