package com.xcy.fzb.shopping_guide.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.adapter.BuildingInformationAdapter;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.BuildingBean;
import com.xcy.fzb.shopping_guide.persente.OkHttpPost;
import com.xcy.fzb.shopping_guide.persente.StatusBar;

import java.util.List;

public class BuildingInformationActivity extends AppCompatActivity {

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
        StatusBar.makeStatusBarTransparent(this);

        picUrl = getIntent().getStringExtra("pic");
        initView();
        initData();
    }

    private void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
    private void initData(){
        String url = buildingUrl+"&userId="+ FinalContents.getUserID()+"&id="+FinalContents.getProjectID();
        Log.i("aaa","项目ID："+ FinalContents.getProjectID());
        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();

        String substring = data.substring(9, 10);

        if (substring.equals("1")) {
            BuildingBean bean = new Gson().fromJson(data, BuildingBean.class);
            final List<BuildingBean.DataBean> list = bean.getData();
            tabLayout.setSelectedTabIndicatorColor(R.color.colorIndicator);
            tabLayout.setSelectedTabIndicator(R.drawable.tab_indicator);
            for (int i = 0;i < list.size();i++){
                if (i == 0) {
                    tabLayout.addTab(tabLayout.newTab().setText(list.get(i).getBuildingName()),true);
                }else {
                    tabLayout.addTab(tabLayout.newTab().setText(list.get(i).getBuildingName()));
                }
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    //                添加选中Tab的逻辑
                    for (int j = 0;j < list.size();j++){
                        if (tab.getText().toString().equals(list.get(j).getBuildingName())) {
                            name.setText(list.get(j).getProject().getProjectName());
                            state.setText(list.get(j).getSaleStatus());
                            ladder.setText(list.get(j).getElevator()+"梯"+list.get(j).getFamily()+"户");
                            tier.setText(list.get(j).getPliesNumber()+"层");
                            cell.setText(list.get(j).getElementNumber()+"");
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
                    for (int j = 0;j < list.size();j++){
                        if (tab.getText().toString().equals(list.get(j).getBuildingName())) {
                            name.setText(list.get(j).getProject().getProjectName());
                            state.setText(list.get(j).getSaleStatus());
                            ladder.setText(list.get(j).getElevator()+"梯"+list.get(j).getFamily()+"户");
                            tier.setText(list.get(j).getPliesNumber()+"层");
                            cell.setText(list.get(j).getElementNumber()+"");
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





        }else {
            Toast.makeText(this, "没有获取到数据", Toast.LENGTH_SHORT).show();
        }
    }
}
