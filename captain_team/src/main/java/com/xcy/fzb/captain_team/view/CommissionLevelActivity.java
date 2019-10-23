package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.CommissionLevelAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.CommissionLevelAdapterAddBean;
import com.xcy.fzb.captain_team.database.CommissionLevelDeleteBean;
import com.xcy.fzb.captain_team.database.CommissionLevelSelectBean;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 圈层5-5佣金级别设置
public class CommissionLevelActivity extends AppCompatActivity implements View.OnClickListener, CommissionLevelAdapter.onBtnDelete {

    ImageView commission_level_img;
    RecyclerView commission_level_rv;
    CommissionLevelAdapter adapter;
    RelativeLayout commission_level_rl;

    EditText commission_level_et1;
    EditText commission_level_et2;

    CheckBox commission_level_rb1;

    Button commission_level_btn;

    private List<CommissionLevelSelectBean.DataBean> data;
    private String s = "";
    private String s1 = "";

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_level);

        initView();

    }

    @SuppressLint("WrongConstant")
    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        commission_level_img = findViewById(R.id.commission_level_img);
        commission_level_rv = findViewById(R.id.commission_level_rv);
        commission_level_rl = findViewById(R.id.commission_level_rl);
        commission_level_et1 = findViewById(R.id.commission_level_et1);
        commission_level_et2 = findViewById(R.id.commission_level_et2);
        commission_level_rb1 = findViewById(R.id.commission_level_rb1);
        commission_level_btn = findViewById(R.id.commission_level_btn);

        commission_level_img.setOnClickListener(this);
        commission_level_btn.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        commission_level_rv.setLayoutManager(manager);
        adapter = new CommissionLevelAdapter();
        initData();

        commission_level_rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (commission_level_rb1.isChecked()) {
                    commission_level_rl.setVisibility(View.VISIBLE);
                } else {
                    commission_level_rl.setVisibility(View.GONE);
                }
            }
        });

        commission_level_et2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    if (flag == 0) {
                        s = commission_level_et1.getText().toString();
                        s1 = commission_level_et2.getText().toString();
                        if (s.equals("") || s1.equals("")) {
                            Toast.makeText(CommissionLevelActivity.this, "级别和百分比不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            initDataAdd();
                            flag = 1;
                            return true;
                        }
                    }

                }

                return false;
            }
        });
    }

    private void initDataAdd() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL", "s：" + s);
        Log.i("MyCL", "s1：" + s1);
        Observable<CommissionLevelAdapterAddBean> commissionLevelAdd = fzbInterface.getCommissionLevelAdd("", FinalContents.getUserID(), s, s1, "", FinalContents.getUserID());
        commissionLevelAdd.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionLevelAdapterAddBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommissionLevelAdapterAddBean commissionLevelAdapterAddBean) {
                        if (commissionLevelAdapterAddBean.getMsg().equals("成功")) {
                            Toast.makeText(CommissionLevelActivity.this, commissionLevelAdapterAddBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            commission_level_rl.setVisibility(View.GONE);
//                            data.clear();
                            initData();
                            commission_level_et1.setText("");
                            commission_level_et2.setText("");
                            flag = 0;
                        } else {
                            Toast.makeText(CommissionLevelActivity.this, commissionLevelAdapterAddBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "添加佣金级别错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.commission_level_img:
                finish();
                break;
            case R.id.commission_level_btn:
                Toast.makeText(CommissionLevelActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }

    }

    private void initData() {
        adapter.setOnBtnDelete(this);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL", "FinalContents.getUserID()：" + FinalContents.getUserID());
        Observable<CommissionLevelSelectBean> commissionLevelSelece = fzbInterface.getCommissionLevelSelece(FinalContents.getUserID(), FinalContents.getUserID());
        commissionLevelSelece.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionLevelSelectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommissionLevelSelectBean commissionLevelSelectBean) {
                        data = commissionLevelSelectBean.getData();
                        adapter.setList(data);
                        commission_level_rv.setAdapter(adapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "查询佣金级别错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void onDelete(String position) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CommissionLevelDeleteBean> commissionLevelDelete = fzbInterface.getCommissionLevelDelete(FinalContents.getUserID(), position);
        commissionLevelDelete.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionLevelDeleteBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommissionLevelDeleteBean commissionLevelDeleteBean) {
                        if (commissionLevelDeleteBean.getMsg().equals("成功")) {
                            Toast.makeText(CommissionLevelActivity.this, commissionLevelDeleteBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
//                            data.clear();
                            initData();
                        } else {
                            Toast.makeText(CommissionLevelActivity.this, commissionLevelDeleteBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "佣金级别删除错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
