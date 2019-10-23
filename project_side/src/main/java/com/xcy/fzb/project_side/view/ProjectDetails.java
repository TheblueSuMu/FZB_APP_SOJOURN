package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.CommissionRecycler;
import com.xcy.fzb.project_side.adapter.FamilyRecycler;
import com.xcy.fzb.project_side.adapter.ReportAdapter;
import com.xcy.fzb.project_side.adapter.TalktoolRecycler;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.CollectBean;
import com.xcy.fzb.project_side.modle.HouseBean;
import com.xcy.fzb.project_side.modle.ProjectDetailsBean;
import com.xcy.fzb.project_side.presente.MyLinearLayoutManager;
import com.xcy.fzb.project_side.presente.OkHttpPost;
import com.xcy.fzb.project_side.presente.SharItOff;
import com.xcy.fzb.project_side.presente.StatusBar;

import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

public class ProjectDetails extends AllActivity implements View.OnClickListener {
    private ImageView backimg;
    private LinearLayout back;
    private ImageView building;

    private TextView number;
    private TextView attention;
    private TextView transmit;
    private TextView amount;
    private TextView salestatus;
    private TagContainerLayout productfeature;
    private TextView name;
    private TextView location;
    private TextView toatl;
    private TextView unit;
    private TextView tary;
    private TextView areainterval;
    private TextView more;
    private TextView award;
    private TextView house_title;
    private TextView house_time;
    private TextView transmit_house;

    private TextView message;

    private TextView report;
    private CheckBox collect;
    private TextView call;

    private TabLayout tabLayout;


    private TextView repast;
    private TagContainerLayout repast_content;
    private TextView recuperate;
    private TextView recuperate_content;
    private TextView business;
    private TextView business_content;

    private RecyclerView report_rv;

    private RecyclerView commissionRv;
    private RecyclerView talktool;
    private RecyclerView family;

    private LinearLayout talktool_layout;
    private LinearLayout award_layout;
    private LinearLayout linear0;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private LinearLayout linear3;
    private LinearLayout linear4;
    private LinearLayout linear5;
    private LinearLayout linear6;
    private LinearLayout linear7;

    private TextView group_booking;

    private LinearLayout guowai1;
    private LinearLayout guowai3;
    private View guowai2;
    private String imagePath = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3055880154,1625749017&fm=26&gp=0.jpg";

    private TextView share;

    private ProjectDetailsBean.DataBean projectDetailsBeanData;


    private String projectDetailsUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectDetails?";
    private String houseTypeUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectPropertyHouseList?";
    private String collectUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonUpdate/projectCollectSave?";
    private CollectBean.DataBean dataBean = new CollectBean.DataBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details);
        StatusBar.makeStatusBarTransparent(this);
        initfvb();
        init();
    }

    //命名区域
    private void initfvb(){


        backimg = findViewById(R.id.project_details_backimg);
        back = findViewById(R.id.project_details_back);
        building = findViewById(R.id.project_details_building);

        number =findViewById(R.id.project_details_number);
        attention =findViewById(R.id.project_details_attention_num);
        transmit =findViewById(R.id.project_details_transmit_num);
        amount =findViewById(R.id.project_details_amount_num);
        salestatus =findViewById(R.id.project_details_salestatus);
        productfeature =findViewById(R.id.project_details_productfeature);
        name =findViewById(R.id.project_details_name);
        location =findViewById(R.id.project_details_location);
        toatl =findViewById(R.id.project_details_toatl);
        unit =findViewById(R.id.project_details_unit);
        tary =findViewById(R.id.project_details_tary);
        areainterval =findViewById(R.id.project_details_areainterval);
        more =findViewById(R.id.project_details_more);
        award =findViewById(R.id.project_details_award);
        house_title =findViewById(R.id.project_details_house_title);
        house_time =findViewById(R.id.project_details_house_time);
        transmit_house =findViewById(R.id.project_details_transmit_house);

        guowai1 = findViewById(R.id.guowai1);
        guowai3 = findViewById(R.id.guowai3);
        guowai2 = findViewById(R.id.guowai2);

        call = findViewById(R.id.project_details_call);
        report = findViewById(R.id.project_details_report);
        collect = findViewById(R.id.project_details_collect);

        commissionRv =findViewById(R.id.project_details_commission);
        talktool =findViewById(R.id.project_details_talktool);
        family =findViewById(R.id.project_details_family);

        report_rv = findViewById(R.id.project_details_report_rv);

        share = findViewById(R.id.project_details_share);

        tabLayout = findViewById(R.id.project_details_tab);
        message = findViewById(R.id.project_details_message);

        repast = findViewById(R.id.project_details_repast);
        repast_content = findViewById(R.id.project_details_repast_content);
        recuperate = findViewById(R.id.project_details_recuperate);
        recuperate_content = findViewById(R.id.project_details_recuperate_content);
        business = findViewById(R.id.project_details_business);
        business_content = findViewById(R.id.project_details_business_content);

        linear0 = findViewById(R.id.linear0);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear5 = findViewById(R.id.linear5);
        linear6 = findViewById(R.id.linear6);
        linear7 = findViewById(R.id.linear7);

        group_booking = findViewById(R.id.project_details_group_booking);

        talktool_layout = findViewById(R.id.project_details_talktool_layout);
        award_layout = findViewById(R.id.project_details_award_layout);

        back.setOnClickListener(this);
        backimg.setOnClickListener(this);
        more.setOnClickListener(this);
        transmit_house.setOnClickListener(this);
        building.setOnClickListener(this);

        location.setOnClickListener(this);

        if (SharItOff.getShar().equals("显")) {
            linear0.setVisibility(View.VISIBLE);
        } else if (SharItOff.getShar().equals("隐")) {
            linear0.setVisibility(View.GONE);
        }



        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = collectUrl + "&projectId=" + FinalContents.getProjectID() + "&userId=" +FinalContents.getUserID()+"&type=1";
                OkHttpPost okHttpPost = new OkHttpPost(url);
                String data = okHttpPost.post();

                CollectBean collectBean = new Gson().fromJson(data,CollectBean.class);
                dataBean = collectBean.getData();
                if (dataBean.getStatus().equals("1")) {
                    collect.setChecked(true);
                    Toast.makeText(ProjectDetails.this, dataBean.getMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    collect.setChecked(false);
                    Toast.makeText(ProjectDetails.this, dataBean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ProjectDetails.this,ReportActivity.class);
//                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + projectDetailsBeanData.getProjectListVo().getFfAttacheList().get(0).getPhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("交通出行"),true);
        tabLayout.addTab(tabLayout.newTab().setText("教育教学"));
        tabLayout.addTab(tabLayout.newTab().setText("医疗健康"));
        tabLayout.addTab(tabLayout.newTab().setText("商场购物"));
        tabLayout.addTab(tabLayout.newTab().setText("生活娱乐"));
        tabLayout.addTab(tabLayout.newTab().setText("著名景点"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
    //                添加选中Tab的逻辑
                if (tab.getText().equals("交通出行")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getTraffic());
                } else if (tab.getText().equals("教育教学")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEducation());
                } else if (tab.getText().equals("医疗健康")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getMedical());
                } else if (tab.getText().equals("商场购物")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getShopping());
                } else if (tab.getText().equals("生活娱乐")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEntertainment());
                } else if (tab.getText().equals("著名景点")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getFamousScene());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
    //                添加未选中Tab的逻辑
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
    //                再次选中tab的逻辑
                if (tab.getText().equals("交通出行")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getTraffic());
                } else if (tab.getText().equals("教育健康")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEducation());
                } else if (tab.getText().equals("医疗健康")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getMedical());
                } else if (tab.getText().equals("商场购物")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getShopping());
                } else if (tab.getText().equals("生活娱乐")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEntertainment());
                } else if (tab.getText().equals("著名景点")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getFamousScene());
                }
            }
        });
    }


    private void showShare() {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle("这是一个标题");
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("https://www.sojson.com/httpRequest/");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        if(imagePath != null &&imagePath.contains("/sdcard/")){
//            //imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//            oks.setImagePath(imagePath);
//        }else if(imagePath != null){
//            oks.setImageUrl(imagePath); //网络地址
//        }
//        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("https://www.sojson.com/httpRequest/");
//        // 启动分享GUI
//        oks.show(this);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.project_details_back:
                finish();
                break;
            case R.id.project_details_backimg:
                Intent photointent = new Intent(this,PhotoTileActivity.class);
                startActivity(photointent);
                break;
            case R.id.project_details_more:
                Intent moreInformationintent = new Intent(this,MoreInformationActivity.class);
                startActivity(moreInformationintent);
                break;
            case R.id.project_details_transmit_house:
                Intent transmitintent = new Intent(this,BuildingDynamicActivity.class);
                startActivity(transmitintent);
                break;
            case R.id.project_details_building:
                Intent buildingInformationintent = new Intent(this,BuildingInformationActivity.class);
                buildingInformationintent.putExtra("pic","http://39.98.173.250:8080"+projectDetailsBeanData.getProjectListVo().getProjectImg());
                startActivity(buildingInformationintent);
                break;
            case R.id.project_details_location:
                String ids = projectDetailsBeanData.getProjectListVo().getLocation();//从pd里取出字符串
                List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list
                double d = Double.parseDouble(tags.get(0).toString());
                double o = Double.parseDouble(tags.get(1).toString());
                if (!projectDetailsBeanData.getProjectListVo().getSalesOfficeLocation().equals("")) {
                    String ids1 = projectDetailsBeanData.getProjectListVo().getSalesOfficeLocation();//从pd里取出字符串
                    List tags1 = Arrays.asList(ids1.split(","));//根据逗号分隔转化为list
                    double d1 = Double.parseDouble(tags1.get(0).toString());
                    double o1 = Double.parseDouble(tags1.get(1).toString());
                    FinalContents.setO1(o1);
                    FinalContents.setD1(d1);
                }
                FinalContents.setO(o);
                FinalContents.setD(d);
//                Intent mapintent = new Intent(this,MapActivity.class);
//                mapintent.putExtra("office","1");
//                startActivity(mapintent);
                break;
        }

    }


    @SuppressLint({"CheckResult", "WrongConstant"})
    private void init(){
        String url = projectDetailsUrl+"&userId="+ FinalContents.getUserID()+"&id="+FinalContents.getProjectID();
        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();
        Log.i("aaa","jieshouzhi:"+data);

        String substring = data.substring(9, 10);

        if (substring.equals("1")) {
            ProjectDetailsBean projectDetailsBean = new Gson().fromJson(data, ProjectDetailsBean.class);
            projectDetailsBeanData = projectDetailsBean.getData();
            Log.i("wsm","fdsafd:"+projectDetailsBeanData.getProjectListVo().getProjectImg());
            Glide.with(this).load("http://39.98.173.250:8080"+projectDetailsBean.getData().getProjectListVo().getProjectImg()).into(backimg);
            if (projectDetailsBeanData.getProjectListVo().getReferenceToatlPrice().equals("")) {
                guowai1.setVisibility(View.GONE);
                guowai2.setVisibility(View.GONE);
                guowai3.setGravity(Gravity.CENTER);
            }else {
                guowai1.setVisibility(View.VISIBLE);
                guowai2.setVisibility(View.VISIBLE);
                guowai3.setGravity(Gravity.RIGHT);
            }

            if (projectDetailsBeanData.getProjectListVo().getIsgroup().equals("1")) {
                group_booking.setVisibility(View.VISIBLE);
            }else {
                group_booking.setVisibility(View.GONE);
            }
            number.setText(projectDetailsBeanData.getHouseImgCountNum()+"张");
            attention.setText(projectDetailsBeanData.getProjectListVo().getBrowseNum());
            transmit.setText(projectDetailsBeanData.getProjectListVo().getForwardingAmount());
            amount.setText(projectDetailsBeanData.getProjectListVo().getReportAmount());

            //报备规则
            if (projectDetailsBeanData.getGuideRules().size() == 0) {
                linear1.setVisibility(View.GONE);
            }else {
                linear1.setVisibility(View.VISIBLE);
                MyLinearLayoutManager layout = new MyLinearLayoutManager(ProjectDetails.this);
                layout.setOrientation(LinearLayoutManager.VERTICAL);
                layout.setScrollEnabled(false);
                report_rv.setLayoutManager(layout);
                ReportAdapter reportAdapter = new ReportAdapter(projectDetailsBeanData);
                report_rv.setAdapter(reportAdapter);
            }

            String ids = projectDetailsBeanData.getProjectListVo().getProductFeature();//从pd里取出字符串
            List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list
            productfeature.setTheme(ColorFactory.NONE);
            productfeature.setTags(tags);
            name.setText(projectDetailsBeanData.getProjectListVo().getProjectName());
            location.setText(projectDetailsBeanData.getProjectListVo().getDetailAddress());
            toatl.setText(projectDetailsBeanData.getProjectListVo().getProductTotalPrice());

            unit.setText(projectDetailsBeanData.getProjectListVo().getProductUnitPrice());

            tary.setText(projectDetailsBeanData.getProjectListVo().getMonetaryUnit());
            areainterval.setText(projectDetailsBeanData.getProjectListVo().getAreaInterval());

            //佣金规则
            if (projectDetailsBeanData.getAmountIncentiveList().size() == 0) {
                linear2.setVisibility(View.GONE);
            }else {
                linear2.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                commissionRv.setLayoutManager(layoutManager);
                CommissionRecycler commissionRecycler = new CommissionRecycler(projectDetailsBeanData);
                commissionRv.setAdapter(commissionRecycler);
            }


            if (projectDetailsBeanData.getHousesDynamics().size() == 0) {
                linear3.setVisibility(View.GONE);
            }else {
                linear3.setVisibility(View.VISIBLE);
                house_title.setText(projectDetailsBeanData.getHousesDynamics().get(0).getContent());
                house_time.setText(projectDetailsBeanData.getHousesDynamics().get(0).getCreateDate());
            }

            //户型信息
            if (projectDetailsBeanData.getFamilyInfomations().size() == 0) {
                linear4.setVisibility(View.GONE);
            }else {
                linear4.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager_family = new LinearLayoutManager(this);
                layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                family.setLayoutManager(layoutManager_family);
                FamilyRecycler recyclerAdapter = new FamilyRecycler(projectDetailsBeanData.getFamilyInfomations());
                switch (projectDetailsBeanData.getProjectListVo().getSaleStatus()){
                    case "1":
                        salestatus.setText("待售");
                        recyclerAdapter.setSales("待售");
                        break;
                    case "2":
                        salestatus.setText("认筹");
                        recyclerAdapter.setSales("认筹");
                        break;
                    case "3":
                        salestatus.setText("在售");
                        recyclerAdapter.setSales("在售");
                        break;
                    case "4":
                        salestatus.setText("尾盘");
                        recyclerAdapter.setSales("尾盘");
                        break;
                    case "5":
                        salestatus.setText("特房");
                        recyclerAdapter.setSales("特房");
                        break;
                }
                recyclerAdapter.setPrice(""+projectDetailsBeanData.getProjectListVo().getProductTotalPrice());
                family.setAdapter(recyclerAdapter);
            }




            if (projectDetailsBeanData.getProjectListVo().getAwardRules().equals("")) {
                award_layout.setVisibility(View.GONE);
            }else {
                award_layout.setVisibility(View.VISIBLE);
                award.setText(projectDetailsBeanData.getProjectListVo().getAwardRules());
            }

            //拓客工具
            if (projectDetailsBeanData.getIsTalkTool().equals("0")) {
                talktool_layout.setVisibility(View.GONE);
            }else {
                talktool_layout.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager_talktool = new LinearLayoutManager(this);
                layoutManager_talktool.setOrientation(LinearLayoutManager.VERTICAL);
                talktool.setLayoutManager(layoutManager_talktool);
                TalktoolRecycler talktoolRecycler = new TalktoolRecycler(projectDetailsBeanData.getTalkToolData());
                talktool.setAdapter(talktoolRecycler);
            }


            if (projectDetailsBeanData.getBuildingImg().equals("")) {
                linear5.setVisibility(View.GONE);
            }else {
                linear5.setVisibility(View.VISIBLE);
                Glide.with(this).load("http://39.98.173.250:8080"+projectDetailsBeanData.getBuildingImg()).into(building);
            }

        }else {
            Toast.makeText(this, "没有获取到数据", Toast.LENGTH_SHORT).show();
        }


        String houseUrl = houseTypeUrl+"&userId="+ FinalContents.getUserID()+"&projectId="+FinalContents.getProjectID()+"&status=0";
        Log.i("bbb","项目ID："+FinalContents.getProjectID());
        OkHttpPost houseOkHttpPost = new OkHttpPost(houseUrl);
        String houseData = houseOkHttpPost.post();
        String houseSubstring = data.substring(9, 10);


        if (houseSubstring.equals("1")) {
            HouseBean houseBean = new Gson().fromJson(houseData, HouseBean.class);
            final HouseBean.DataBean houseDataData = houseBean.getData();

            if (houseDataData.getPropertyHouseList().size() == 0) {
                linear6.setVisibility(View.GONE);
            }else {
                linear6.setVisibility(View.VISIBLE);
                String ids = houseDataData.getPropertyHouseList().get(0).getContent();//从pd里取出字符串
                List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list

                repast_content.setTheme(ColorFactory.NONE);
                repast_content.setTags(tags);
                repast.setText(houseDataData.getPropertyHouseList().get(0).getType());
                repast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                        intent.putExtra("title",houseDataData.getPropertyHouseList().get(0).getType());
                        intent.putExtra("time",houseDataData.getPropertyHouseList().get(0).getCreateDate());
                        intent.putExtra("content",houseDataData.getPropertyHouseList().get(0).getContent());
                        startActivity(intent);
                    }
                });
                recuperate.setText(houseDataData.getPropertyHouseList().get(1).getType());
                recuperate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                        intent.putExtra("title",houseDataData.getPropertyHouseList().get(1).getType());
                        intent.putExtra("time",houseDataData.getPropertyHouseList().get(1).getCreateDate());
                        intent.putExtra("content",houseDataData.getPropertyHouseList().get(1).getContent());
                        startActivity(intent);
                    }
                });
                recuperate_content.setText(houseDataData.getPropertyHouseList().get(1).getContent());
                business.setText(houseDataData.getPropertyHouseList().get(2).getType());
                business.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                        intent.putExtra("title",houseDataData.getPropertyHouseList().get(2).getType());
                        intent.putExtra("time",houseDataData.getPropertyHouseList().get(2).getCreateDate());
                        intent.putExtra("content",houseDataData.getPropertyHouseList().get(2).getContent());
                        startActivity(intent);
                    }
                });
                business_content.setText(houseDataData.getPropertyHouseList().get(2).getContent());
            }

        }else {
            Toast.makeText(this, "没有获取到数据", Toast.LENGTH_SHORT).show();
        }
    }
}
