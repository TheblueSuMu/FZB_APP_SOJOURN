package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.CommissionLevelAdapterAddBean;
import com.xcy.fzb.all.database.CommissionLevelDeleteBean;
import com.xcy.fzb.all.database.CommissionLevelSelectBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.captain_team.adapter.Captain_Team_CommissionLevelAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 圈层5-5佣金级别设置
public class Captain_Team_CommissionLevelActivity extends AllActivity implements View.OnClickListener, Captain_Team_CommissionLevelAdapter.onBtnDelete {

    RelativeLayout commission_level_img;
    RecyclerView commission_level_rv;
    Captain_Team_CommissionLevelAdapter adapter;


    private List<CommissionLevelSelectBean.DataBean> data;
    private String searchID;
    private String searchIf;

    int flag = 0;
    private TextView commission_level_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_commission_level);

        initView();

    }

    @SuppressLint("WrongConstant")
    private void initView() {

        StatusBar.makeStatusBarTransparent(this);
        commission_level_title = findViewById(R.id.commission_level_title);
        commission_level_img = findViewById(R.id.commission_level_img);
        commission_level_rv = findViewById(R.id.commission_level_rv);

        commission_level_img.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        commission_level_rv.setLayoutManager(manager);
        adapter = new Captain_Team_CommissionLevelAdapter();

        if (FinalContents.getIdentity().equals("60")) {
            commission_level_title.setText("销售佣金设置");
            initData();
        } else if (FinalContents.getIdentity().equals("61")) {
            commission_level_title.setText("顾问佣金设置");
            initData();
        } else if (FinalContents.getIdentity().equals("63")) {
            /**
             *修改
             */
            searchIf = getIntent().getStringExtra("SearchIf");
            searchID = getIntent().getStringExtra("SearchID");
            if (searchIf.equals("销售")) {
                commission_level_title.setText("销售佣金设置");
            } else if (searchIf.equals("顾问")) {
                commission_level_title.setText("顾问佣金设置");
            }
            initDatas();
        }

    }

    /**
     * 修改
     */
    private void initDatas() {
        adapter.setOnBtnDelete(this);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL", "FinalContents.getUserID()：" + FinalContents.getUserID());
        Observable<CommissionLevelSelectBean> commissionLevelSelece = fzbInterface.getCommissionLevelSelece(FinalContents.getUserID(), searchID);
        commissionLevelSelece.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionLevelSelectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final CommissionLevelSelectBean commissionLevelSelectBean) {
                        data = commissionLevelSelectBean.getData();
                        adapter.setList(data);
                        commission_level_rv.setAdapter(adapter);
                        adapter.setOnItemClickListener(new Captain_Team_CommissionLevelAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(final int postion) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Captain_Team_CommissionLevelActivity.this);
                                builder.setTitle(data.get(postion).getName());    //设置对话框标题
                                final EditText edit = new EditText(Captain_Team_CommissionLevelActivity.this);
                                edit.setHint("请输入要修改的佣金比例");
                                builder.setView(edit);
                                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if (edit.getText().toString().equals("")) {
                                            Toast.makeText(Captain_Team_CommissionLevelActivity.this, "数值不能为空", Toast.LENGTH_SHORT).show();
                                        } else {
                                            initDataAdd(commissionLevelSelectBean.getData().get(postion).getId(), edit.getText().toString());
                                        }

                                    }
                                });
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(Captain_Team_CommissionLevelActivity.this, "您已取消修改佣金比例", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
                                AlertDialog dialog = builder.create();  //创建对话框
                                dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
                                dialog.show();
                            }
                        });
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

    private void initDataAdd(String ID, String YongJin) {
        Log.i("123", "次数:");
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CommissionLevelAdapterAddBean> commissionLevelAdd = fzbInterface.getCommissionLevelAdd(ID, "", "", YongJin, "", FinalContents.getUserID() + "");
        commissionLevelAdd.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionLevelAdapterAddBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommissionLevelAdapterAddBean commissionLevelAdapterAddBean) {
                        if (commissionLevelAdapterAddBean.getMsg().equals("成功")) {
                            Toast.makeText(Captain_Team_CommissionLevelActivity.this, commissionLevelAdapterAddBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            if (FinalContents.getIdentity().equals("63")) {
                                initDatas();
                            } else {
                                initData();
                            }
                            flag = 0;
                        } else {
                            Toast.makeText(Captain_Team_CommissionLevelActivity.this, commissionLevelAdapterAddBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
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
                    public void onNext(final CommissionLevelSelectBean commissionLevelSelectBean) {
                        data = commissionLevelSelectBean.getData();
                        adapter.setList(data);
                        commission_level_rv.setAdapter(adapter);
                        adapter.setOnItemClickListener(new Captain_Team_CommissionLevelAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(final int postion) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Captain_Team_CommissionLevelActivity.this);
                                builder.setTitle(data.get(postion).getName());    //设置对话框标题
                                final EditText edit = new EditText(Captain_Team_CommissionLevelActivity.this);
                                edit.setHint("请输入要修改的佣金比例");
                                builder.setView(edit);
                                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (edit.getText().toString().equals("")) {
                                            Toast.makeText(Captain_Team_CommissionLevelActivity.this, "数值不能为空", Toast.LENGTH_SHORT).show();
                                        } else {
                                            initDataAdd(commissionLevelSelectBean.getData().get(postion).getId(), edit.getText().toString());
                                        }
                                    }
                                });
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(Captain_Team_CommissionLevelActivity.this, "您已取消修改佣金比例", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
                                AlertDialog dialog = builder.create();  //创建对话框
                                dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
                                dialog.show();
                            }
                        });
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
                            Toast.makeText(Captain_Team_CommissionLevelActivity.this, commissionLevelDeleteBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
//                            data.clear();
                            initData();
                        } else {
                            Toast.makeText(Captain_Team_CommissionLevelActivity.this, commissionLevelDeleteBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
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
