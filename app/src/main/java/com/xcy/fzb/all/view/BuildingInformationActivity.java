package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.BuildingInformationAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.BuildingBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildingInformationActivity extends AllActivity {

    private LinearLayout building_img;
    private String buildingUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectBuildingInfo?";
    private TextView name;
    private TextView state;
    private TextView ladder;
    private TextView tier;
    private TextView cell;
    private TextView open;
    private TextView occupancy;
    private TextView buildingCase;
    private TextView standard;
    private RecyclerView recyclerView;
    private ImageView pic;
    private TabLayout tabLayout;
    private BuildingInformationAdapter buildingInformationAdapter;
    private String picUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_information);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            picUrl = getIntent().getStringExtra("pic");
            initView();
            initData();
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

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        name = findViewById(R.id.building_name);
        state = findViewById(R.id.building_state);
        ladder = findViewById(R.id.building_ladder);
        tier = findViewById(R.id.building_tier);
        cell = findViewById(R.id.building_cell);
        open = findViewById(R.id.building_open);
        occupancy = findViewById(R.id.building_occupancy);
        buildingCase = findViewById(R.id.building_case);
        standard = findViewById(R.id.building_standard);
        recyclerView = findViewById(R.id.building_house_type);
        pic = findViewById(R.id.building_pic);
        building_img = findViewById(R.id.building_img);
        tabLayout = findViewById(R.id.building_tab_layout);
        Glide.with(this).load(picUrl).into(pic);

        building_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void initData() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BuildingBean> buildingBean = fzbInterface.getBuildingBean(FinalContents.getUserID(), FinalContents.getProjectID());
        buildingBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuildingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BuildingBean buildingBean) {
                        final List<BuildingBean.DataBean> list = buildingBean.getData();
                        if (list.size() != 0) {
                            tabLayout.setSelectedTabIndicatorColor(R.color.colorIndicator);
                            tabLayout.setSelectedTabIndicator(R.drawable.tab_indicator);
                            name.setText(list.get(0).getProject().getProjectName());
                            state.setText(list.get(0).getSaleStatus());
                            if (list.get(0).getElevator().equals("") || list.get(0).getFamily().equals("")) {
                                ladder.setText("未知");
                            }else {
                                ladder.setText(list.get(0).getElevator() + "梯" + list.get(0).getFamily() + "户");
                            }
                            if (list.get(0).getElementNumber().equals("")) {
                                cell.setText("未知");
                            }else {
                                cell.setText(list.get(0).getElementNumber() + "");
                            }

                            tier.setText(list.get(0).getPliesNumber() + "层");
                            open.setText(list.get(0).getBuildTime());
                            occupancy.setText(list.get(0).getCheckInTime());
                            buildingCase.setText(list.get(0).getFitmentState());
                            standard.setText(list.get(0).getFitmentStandardStr());
                            buildingInformationAdapter = new BuildingInformationAdapter(list.get(0).getHouseInfoList());
                            recyclerView.setAdapter(buildingInformationAdapter);
                            buildingInformationAdapter.notifyDataSetChanged();
                            for (int i = 0; i < list.size(); i++) {
                                if (i == 0) {
                                    tabLayout.addTab(tabLayout.newTab().setText(list.get(i).getBuildingName()), true);
                                } else {
                                    tabLayout.addTab(tabLayout.newTab().setText(list.get(i).getBuildingName()));
                                }
                            }
                            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    //                添加选中Tab的逻辑
                                    for (int j = 0; j < list.size(); j++) {
                                        if (tab.getText().toString().equals(list.get(j).getBuildingName())) {
                                            name.setText(list.get(j).getProject().getProjectName());
                                            state.setText(list.get(j).getSaleStatus());
                                            if (list.get(j).getElevator().equals("") || list.get(j).getFamily().equals("")) {
                                                ladder.setText("未知");
                                            }else {
                                                ladder.setText(list.get(j).getElevator() + "梯" + list.get(j).getFamily() + "户");
                                            }
                                            if (list.get(j).getElementNumber().equals("")) {
                                                cell.setText("未知");
                                            }else {
                                                cell.setText(list.get(j).getElementNumber() + "");
                                            }
                                            tier.setText(list.get(j).getPliesNumber() + "层");
                                            open.setText(list.get(j).getBuildTime());
                                            occupancy.setText(list.get(j).getCheckInTime());
                                            buildingCase.setText(list.get(j).getFitmentState());
                                            standard.setText(list.get(j).getFitmentStandardStr());
                                            buildingInformationAdapter = new BuildingInformationAdapter(list.get(j).getHouseInfoList());
                                            recyclerView.setAdapter(buildingInformationAdapter);
                                            buildingInformationAdapter.notifyDataSetChanged();
                                        }
                                    }

                                    if (tab.getText().equals("交通出行")) {
                                    } else if (tab.getText().equals("教育教学")) {
                                    } else if (tab.getText().equals("医疗健康")) {
                                    } else if (tab.getText().equals("商场购物")) {
                                    } else if (tab.getText().equals("生活娱乐")) {
                                    } else if (tab.getText().equals("著名景点")) {
                                    }

                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {
                                    //                添加未选中Tab的逻辑
                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {
                                    //                再次选中tab的逻辑
                                    for (int j = 0; j < list.size(); j++) {
                                        if (tab.getText().toString().equals(list.get(j).getBuildingName())) {
                                            name.setText(list.get(j).getProject().getProjectName());
                                            state.setText(list.get(j).getSaleStatus());
                                            if (list.get(j).getElevator().equals("") || list.get(j).getFamily().equals("")) {
                                                ladder.setText("未知");
                                            }else {
                                                ladder.setText(list.get(j).getElevator() + "梯" + list.get(j).getFamily() + "户");
                                            }
                                            if (list.get(j).getElementNumber().equals("")) {
                                                cell.setText("未知");
                                            }else {
                                                cell.setText(list.get(j).getElementNumber() + "");
                                            }
                                            tier.setText(list.get(j).getPliesNumber() + "层");
                                            open.setText(list.get(j).getBuildTime());
                                            occupancy.setText(list.get(j).getCheckInTime());
                                            buildingCase.setText(list.get(j).getFitmentState());
                                            standard.setText(list.get(j).getFitmentStandardStr());
                                            buildingInformationAdapter = new BuildingInformationAdapter(list.get(j).getHouseInfoList());
                                            recyclerView.setAdapter(buildingInformationAdapter);
                                            buildingInformationAdapter.notifyDataSetChanged();
                                        }
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "BuildingInformationActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
