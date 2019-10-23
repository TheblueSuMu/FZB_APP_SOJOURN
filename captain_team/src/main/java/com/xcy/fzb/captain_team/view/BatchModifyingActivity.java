package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.BatchModifyingAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.CommissionLevelSelectBean;
import com.xcy.fzb.captain_team.database.LevelBean;
import com.xcy.fzb.captain_team.database.TeamMemberBean;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 圈层5-1团队人员-批量修改级别psd
public class BatchModifyingActivity extends AppCompatActivity {

    ImageView batch_modifying_img;
    RecyclerView batch_modifying_rv;
    BatchModifyingAdapter adapter;

    Button batch_modifying_btn;
    String IDS = "";
    private OptionsPickerView pvOptions;
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_modifying);

        initView();

    }

    private void initView() {
        if (FinalContents.getIdentity().equals("60")) {
            type = "2";
        }else if (FinalContents.getIdentity().equals("61")){
            type = "3";
        }
		StatusBar.makeStatusBarTransparent(this);
		
        batch_modifying_img = findViewById(R.id.batch_modifying_img);

        batch_modifying_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        batch_modifying_rv = findViewById(R.id.batch_modifying_rv);

        batch_modifying_btn = findViewById(R.id.batch_modifying_btn);

        batch_modifying_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDataView();
            }
        });
        initData();
    }

    private void initDataView(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<CommissionLevelSelectBean> commissionLevelSelece = fzbInterface.getCommissionLevelSelece(FinalContents.getUserID(), FinalContents.getUserID());
        commissionLevelSelece.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionLevelSelectBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final CommissionLevelSelectBean commissionLevelSelectBean) {

                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < commissionLevelSelectBean.getData().size(); i++) {
                            list.add(commissionLevelSelectBean.getData().get(i).getName());
                        }
                        //      监听选中
                        //创建
                        pvOptions = new OptionsPickerBuilder(BatchModifyingActivity.this, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //               返回的分别是三个级别的选中位置
                                //              展示选中数据
                                batch_modifying_btn.setText(list.get(options1));
                                FinalContents.setRatioId(commissionLevelSelectBean.getData().get(options1).getId());
                                initViewData();
                            }
                        })
                                .setSelectOptions(0)//设置选择第一个
                                .setOutSideCancelable(false)//点击背的地方不消失
                                .build();
                        //      把数据绑定到控件上面
                        pvOptions.setPicker(list);
                        //      展示
                        pvOptions.show();

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

    @SuppressLint("WrongConstant")
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamMemberBean> teamMemberBeane = fzbInterface.getTeamMemberBeane("", type, "", FinalContents.getUserID(), FinalContents.getUserID());
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamMemberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final TeamMemberBean teamMemberBean) {

                        LinearLayoutManager manager = new LinearLayoutManager(BatchModifyingActivity.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        batch_modifying_rv.setLayoutManager(manager);
                        adapter = new BatchModifyingAdapter(teamMemberBean.getData().getRows());
                        batch_modifying_rv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(new BatchModifyingAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {
                                if (IDS.equals("")) {
                                    IDS = teamMemberBean.getData().getRows().get(postion).getId();
                                }else {
                                    IDS = IDS +","+ teamMemberBean.getData().getRows().get(postion).getId();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "批量修改错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initViewData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LevelBean> teamMemberBeane = fzbInterface.getLevelBean(FinalContents.getUserID(), FinalContents.getRatioId(),IDS);
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LevelBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final LevelBean levelBean) {
                        Toast.makeText(BatchModifyingActivity.this, levelBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "批量修改错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
