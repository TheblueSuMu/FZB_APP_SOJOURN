package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.CityContents;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.FamilyInfoBean;
import com.xcy.fzb.all.persente.GradationScrollView;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnalysisActivity extends AllActivity implements GradationScrollView.ScrollViewListener {

    private RelativeLayout all_activity_analysis_return;
    private ImageView all_activity_analysis_backImage;
    private TextView all_activity_analysis_productfeature;
    private TextView all_activity_analysis_salestatus;
    private TextView all_activity_analysis_house_type;
    private TextView all_activity_analysis_price;
    private TextView all_activity_analysis_acreage_area;
    private TextView all_activity_analysis_compass_area;
    private TextView all_activity_analysis_percentage_area;
    private TextView all_activity_analysis_building;
    private TextView all_activity_analysis_houses;
    private TextView all_activity_analysis_site;
    private TextView all_activity_analysis_analysis_title;
    private TextView all_activity_analysis_analysis_content;
    private TextView all_activity_analysis_monthly_installment;
    private TextView all_activity_analysis_total_price;
    private TextView all_activity_analysis_down_payment;
    private TextView all_activity_analysis_loans;
    private TextView all_activity_analysis_interest;
    private TextView all_activity_analysis_price_title;
    private PieChart mChart;

    // 饼图数据
    float quarterly1 = 35;
    float quarterly2 = 34;
    float quarterly3 = 34;
    private LinearLayout all_activity_analysis_linear;
    private LinearLayout all_activity_analysis_analysis_linear;
    double d = 0;
    double o = 0;
    private String location;
    int mAlpha = 0;
    private TextView all_activity_analysis_title;
    private RelativeLayout all_activity_analysis_toolbar;
    private String bigPhotoimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_analysis);
        initfvb();
    }

    private void initfvb() {

        all_activity_analysis_linear = findViewById(R.id.all_activity_analysis_linear);
        all_activity_analysis_analysis_linear = findViewById(R.id.all_activity_analysis_analysis_linear);

        all_activity_analysis_return = findViewById(R.id.all_activity_analysis_return);
        all_activity_analysis_backImage = findViewById(R.id.all_activity_analysis_backImage);
        all_activity_analysis_title = findViewById(R.id.all_activity_analysis_title);
        all_activity_analysis_toolbar = findViewById(R.id.all_activity_analysis_toolbar);

        all_activity_analysis_salestatus = findViewById(R.id.all_activity_analysis_salestatus);
        all_activity_analysis_productfeature = findViewById(R.id.all_activity_analysis_productfeature);
        all_activity_analysis_house_type = findViewById(R.id.all_activity_analysis_house_type);
        all_activity_analysis_price = findViewById(R.id.all_activity_analysis_price);
        all_activity_analysis_price_title = findViewById(R.id.all_activity_analysis_price_title);

        all_activity_analysis_acreage_area = findViewById(R.id.all_activity_analysis_acreage_area);
        all_activity_analysis_compass_area = findViewById(R.id.all_activity_analysis_compass_area);
        all_activity_analysis_percentage_area = findViewById(R.id.all_activity_analysis_percentage_area);

        all_activity_analysis_building = findViewById(R.id.all_activity_analysis_building);
        all_activity_analysis_houses = findViewById(R.id.all_activity_analysis_houses);
        all_activity_analysis_site = findViewById(R.id.all_activity_analysis_site);

        all_activity_analysis_analysis_title = findViewById(R.id.all_activity_analysis_analysis_title);
        all_activity_analysis_analysis_content = findViewById(R.id.all_activity_analysis_analysis_content);

        mChart = findViewById(R.id.all_activity_analysis_piechart);
        all_activity_analysis_monthly_installment = findViewById(R.id.all_activity_analysis_monthly_installment);
        all_activity_analysis_total_price = findViewById(R.id.all_activity_analysis_total_price);
        all_activity_analysis_down_payment = findViewById(R.id.all_activity_analysis_down_payment);
        all_activity_analysis_loans = findViewById(R.id.all_activity_analysis_loans);
        all_activity_analysis_interest = findViewById(R.id.all_activity_analysis_interest);


        initData();
        initClick();
    }

    private void initClick() {
        all_activity_analysis_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });         //      TODO    退出


        all_activity_analysis_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (location.equals("")) {
                    ToastUtil.showLongToast(AnalysisActivity.this,"当前无法进入地图");
                }else {
                    Intent mapintent = new Intent(AnalysisActivity.this, MapActivity.class);
                    mapintent.putExtra("office", "1");
                    startActivity(mapintent);
                }
            }
        });         //      TODO    进入地图导航页
    }

    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<FamilyInfoBean> spellingMassTimeBean = fzbInterface.getFamilyInfo(CityContents.getFamilyId());
        spellingMassTimeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FamilyInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final FamilyInfoBean familyInfoBean) {
                        Log.i("测试",familyInfoBean.getData().getSaleStatus());
                        try {
                            Glide.with(AnalysisActivity.this).load(FinalContents.getImageUrl() + familyInfoBean.getData().getFloorPlan()).into(all_activity_analysis_backImage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        all_activity_analysis_salestatus.setText(familyInfoBean.getData().getSaleStatus());
                        switch (familyInfoBean.getData().getProductType()) {
                            case "1":
                                all_activity_analysis_productfeature.setText("住宅");
                                break;
                            case "2":
                                all_activity_analysis_productfeature.setText("公寓");
                                break;
                            case "3":
                                all_activity_analysis_productfeature.setText("写字楼");
                                break;
                            case "4":
                                all_activity_analysis_productfeature.setText("商铺");
                                break;
                            case "5":
                                all_activity_analysis_productfeature.setText("别墅");
                                break;
                        }

                        if (familyInfoBean.getData().getHall().equals("") && familyInfoBean.getData().getToilet().equals("")) {
                            all_activity_analysis_house_type.setText(familyInfoBean.getData().getRoom() + "室");
                        }else if (familyInfoBean.getData().getToilet().equals("")){
                            all_activity_analysis_house_type.setText(familyInfoBean.getData().getRoom() + "室"+familyInfoBean.getData().getHall() + "厅");
                        }else if (familyInfoBean.getData().getHall().equals("")){
                            all_activity_analysis_house_type.setText(familyInfoBean.getData().getRoom() + "室"+familyInfoBean.getData().getToilet() + "卫");
                        }else {
                            all_activity_analysis_house_type.setText(familyInfoBean.getData().getRoom() + "室"+familyInfoBean.getData().getHall() + "厅"+familyInfoBean.getData().getToilet() + "卫");
                        }

                        if(familyInfoBean.getData().getAverage().equals("")){
                            all_activity_analysis_price_title.setVisibility(View.GONE);
                            all_activity_analysis_price.setVisibility(View.GONE);
                        }else {
                            all_activity_analysis_price_title.setVisibility(View.VISIBLE);
                            all_activity_analysis_price.setVisibility(View.VISIBLE);
                            all_activity_analysis_price.setText("¥" + familyInfoBean.getData().getAverage() + "/m²");
                        }

                        //地图
                        //从pd里取出字符串
                        location = familyInfoBean.getData().getProject().getLocation();
                        if (!location.equals("")) {
                            List tags = Arrays.asList(location.split(","));//根据逗号分隔转化为list
                            double d = Double.parseDouble(tags.get(0).toString());
                            double o = Double.parseDouble(tags.get(1).toString());
                            FinalContents.setO(o);
                            FinalContents.setD(d);
                        }






                        if (!familyInfoBean.getData().getFamilyArea().equals("")) {
                            all_activity_analysis_acreage_area.setText(familyInfoBean.getData().getFamilyArea() + "m²");
                        }else {
                            all_activity_analysis_percentage_area.setText("暂无");
                        }

                        if (!familyInfoBean.getData().getFamilyOrientation().equals("")) {
                            all_activity_analysis_compass_area.setText(familyInfoBean.getData().getFamilyOrientation());
                        }else {
                            all_activity_analysis_percentage_area.setText("暂无");
                        }

                        if (!familyInfoBean.getData().getGetHouseRate().equals("")) {
                            all_activity_analysis_percentage_area.setText(familyInfoBean.getData().getGetHouseRate() + "%");
                        }else {
                            all_activity_analysis_percentage_area.setText("暂无");
                        }

                        Log.i("测试","sd"+familyInfoBean.getData().getBuild());
                        all_activity_analysis_building.setText(familyInfoBean.getData().getBuild());
                        all_activity_analysis_houses.setText(familyInfoBean.getData().getProject().getProjectName());
                        all_activity_analysis_site.setText(familyInfoBean.getData().getProject().getAddress());

                        all_activity_analysis_analysis_title.setText(familyInfoBean.getData().getTitle());
                        all_activity_analysis_analysis_content.setText(familyInfoBean.getData().getText());


                        if (familyInfoBean.getData().getYears().equals("")) {
                            all_activity_analysis_monthly_installment.setText(familyInfoBean.getData().getMonthly() + "元");
                        }else {
                            all_activity_analysis_monthly_installment.setText(familyInfoBean.getData().getMonthly() + "元"+"("+familyInfoBean.getData().getYears()+"年)");
                        }


                        if (!familyInfoBean.getData().getIsText().equals("1")) {
                            all_activity_analysis_analysis_linear.setVisibility(View.GONE);
                        } else {
                            all_activity_analysis_analysis_linear.setVisibility(View.VISIBLE);
                        }

                        all_activity_analysis_total_price.setText(familyInfoBean.getData().getTotal() + "万元");
                        if (familyInfoBean.getData().getPercentage().equals("")) {
                            all_activity_analysis_down_payment.setText(familyInfoBean.getData().getDownpayment() + "万元");
                        } else {
                            all_activity_analysis_down_payment.setText(familyInfoBean.getData().getDownpayment() + "万元(" + familyInfoBean.getData().getPercentage() + "%)");
                        }

                        all_activity_analysis_loans.setText(familyInfoBean.getData().getLoan() + "万元");
                        all_activity_analysis_interest.setText(familyInfoBean.getData().getInterest() + "万元");

                        if (!familyInfoBean.getData().getIsShow().equals("1")) {
                            all_activity_analysis_linear.setVisibility(View.GONE);
                        } else {
                            all_activity_analysis_linear.setVisibility(View.VISIBLE);
                        }

                        if (familyInfoBean.getData().getDownpayment().equals("") || familyInfoBean.getData().getLoan().equals("") || familyInfoBean.getData().getInterest().equals("")) {
                            mChart.setVisibility(View.GONE);
                        } else {
                            mChart.setVisibility(View.VISIBLE);
                            // 饼图数据
                            quarterly1 = Float.parseFloat(familyInfoBean.getData().getDownpayment());
                            quarterly2 = Float.parseFloat(familyInfoBean.getData().getLoan());
                            quarterly3 = Float.parseFloat(familyInfoBean.getData().getInterest());
                            showChart(getPieData());
                        }

                        if (familyInfoBean.getData().getFamilySlideImgList().size() != 0) {
                            bigPhotoimg = familyInfoBean.getData().getFamilySlideImgList().get(0);
                            for (int i = 1;i < familyInfoBean.getData().getFamilySlideImgList().size();i++){
                                bigPhotoimg = bigPhotoimg + "|" + familyInfoBean.getData().getFamilySlideImgList().get(i);
                            }
                        }

                        all_activity_analysis_backImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(AnalysisActivity.this, BigPhotoActivity.class);
                                intent.putExtra("index", 0);
                                intent.putExtra("bigPhotoimg", bigPhotoimg);// -1  -1  -1
                                startActivity(intent);
                            }
                        });         //      TODO    进入图片轮播图

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("户型解析界面数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void showChart(PieData pieData) {
        Description description = new Description();
        description.setText("");
        mChart.setDescription(description);
        mChart.setHoleRadius(60f);  //内环半径
        mChart.setTransparentCircleRadius(60f); // 半透明圈半径
        mChart.setDrawCenterText(false);  //饼状图中间可以添加文字
        mChart.setDrawHoleEnabled(true);
        mChart.setRotationAngle(90); // 初始旋转角度
        mChart.setRotationEnabled(false); // 可以手动旋转
        mChart.setUsePercentValues(false);
        // 设置可触摸
        mChart.setTouchEnabled(false);
        // 设置数据
        mChart.setData(pieData);
        for (IDataSet<?> set : mChart.getData().getDataSets())
            set.setDrawValues(false);
        // 取消高亮显示
        mChart.highlightValues(null);
        mChart.invalidate();

        Legend mLegend = mChart.getLegend();  //设置比例图
        mLegend.setForm(Legend.LegendForm.NONE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(0);
        mLegend.setYEntrySpace(0);

        //设置动画
        mChart.animateXY(1000, 1000);
    }

    private PieData getPieData() {
        // yVals用来表示封装每个饼块的实际数据
        List<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(quarterly1, 0));
        yValues.add(new PieEntry(quarterly2, 1));
        yValues.add(new PieEntry(quarterly3, 2));

        // y轴集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.parseColor("#5484FF"));
        colors.add(Color.parseColor("#50DF6B"));
        colors.add(Color.parseColor("#FF5C0D"));

        // 设置饼图颜色
        pieDataSet.setColors(colors);

        // 设置选中态多出的长度
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px);

        // 创建饼图数据
        PieData pieData = new PieData(pieDataSet);

        return pieData;
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, final int y, int oldx, int oldy) {
        int minHeight = 50;
        int maxHeight = (int) (all_activity_analysis_backImage.getMeasuredHeight()*0.5);
        if (maxHeight == 0) {
            maxHeight = 500;
        }

        int color = Color.parseColor("#334485");
        final int red = (color & 0xff0000) >> 16;
        final int green = (color & 0x00ff00) >> 8;
        final int blue = (color & 0x0000ff);
        if (scrollView.getScrollY() <= minHeight) {
            mAlpha = 0;
        } else if (scrollView.getScrollY() > maxHeight) {
            mAlpha = 255;
        } else {
            mAlpha = (scrollView.getScrollY() - minHeight) * 255 / (maxHeight - minHeight);
        }

        if (mAlpha <= 0) {
            all_activity_analysis_title.setText("");
            all_activity_analysis_toolbar.setBackgroundColor(Color.argb((int) 0, red, green, blue));
        } else if (mAlpha >= 255) {
            all_activity_analysis_title.setText("户型解析");
            all_activity_analysis_toolbar.setBackgroundColor(Color.argb((int) 225, red, green, blue));
        } else {
            float scale = (float) y / maxHeight;
            float alpha = (255 * scale);
            all_activity_analysis_title.setText("户型解析");
            all_activity_analysis_toolbar.setBackgroundColor(Color.argb((int) alpha, red, green, blue));
        }

    }
}
