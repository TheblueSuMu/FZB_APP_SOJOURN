package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.GetLandLineBean;
import com.xcy.fzb.project_side.modle.GetLandLineTimeBean;
import com.xcy.fzb.project_side.modle.LandSaveBean;
import com.xcy.fzb.project_side.presente.StatusBar;
import com.xcy.fzb.project_side.service.MyService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 申请登岛2
public class ToApplyForAnlsland2Activity extends AppCompatActivity implements View.OnClickListener {

    ImageView to_apply_for_an_island2_return;
    ImageView to_apply_for_an_island2_img1;
    ImageView to_apply_for_an_island2_img2;
    ImageView to_apply_for_an_island2_img3;

    RelativeLayout to_apply_for_an_island2_rl1;
    RelativeLayout to_apply_for_an_island2_rl2;
    RelativeLayout to_apply_for_an_island2_rl3;

    TextView to_apply_for_an_island2_tv1;
    TextView to_apply_for_an_island2_tv2;
    TextView to_apply_for_an_island2_tv3;
    TextView to_apply_for_an_island2_tv4;

    Button to_apply_for_an_island2_btn;
    private TextView getlandLine;
    private TextView getlandLinetime;
    private int index = 0;
    private String routeid = "";
    private String routeid2 = "";
    private String customerName = "";
    private String gender = "";
    private String idNumber = "";
    private String age = "";
    private String passportNumber = "";
    private String isColleague = "0";
    private int colleagueUserNum = 0;
    private String passportImg = "";
    private String route = "";
    private String isLandTime = "";
    private String isPay = "0";
    private String sumCost = "";
    private String landingImg = "";
    private String occupation = "";
    private String focus = "";
    private String jsonStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_apply_for_anlsland2);

        initView();

    }

    private void initView() {

        initGetIntent();
        StatusBar.makeStatusBarTransparent(this);

        to_apply_for_an_island2_return = findViewById(R.id.to_apply_for_an_island2_return);
        to_apply_for_an_island2_img1 = findViewById(R.id.to_apply_for_an_island2_img1);
        to_apply_for_an_island2_img2 = findViewById(R.id.to_apply_for_an_island2_img2);
        to_apply_for_an_island2_img3 = findViewById(R.id.to_apply_for_an_island2_img3);
        to_apply_for_an_island2_rl1 = findViewById(R.id.to_apply_for_an_island2_rl1);
        to_apply_for_an_island2_rl2 = findViewById(R.id.to_apply_for_an_island2_rl2);
        to_apply_for_an_island2_rl3 = findViewById(R.id.to_apply_for_an_island2_rl3);
        to_apply_for_an_island2_tv1 = findViewById(R.id.to_apply_for_an_island2_tv1);
        to_apply_for_an_island2_tv2 = findViewById(R.id.to_apply_for_an_island2_tv2);
        to_apply_for_an_island2_tv3 = findViewById(R.id.to_apply_for_an_island2_tv3);
        to_apply_for_an_island2_tv4 = findViewById(R.id.to_apply_for_an_island2_tv4);
        to_apply_for_an_island2_btn = findViewById(R.id.to_apply_for_an_island2_btn);
        getlandLine = findViewById(R.id.getlandLine);
        getlandLinetime = findViewById(R.id.getlandLinetime);

        to_apply_for_an_island2_return.setOnClickListener(this);
        to_apply_for_an_island2_img1.setOnClickListener(this);
        to_apply_for_an_island2_img2.setOnClickListener(this);
        to_apply_for_an_island2_rl1.setOnClickListener(this);
        to_apply_for_an_island2_rl2.setOnClickListener(this);
        to_apply_for_an_island2_rl3.setOnClickListener(this);
        to_apply_for_an_island2_btn.setOnClickListener(this);

    }


    private void initGetIntent(){
        customerName = getIntent().getStringExtra("customerName");
        gender = getIntent().getStringExtra("gender");
        idNumber = getIntent().getStringExtra("idNumber");
        age = getIntent().getStringExtra("age");
        if (FinalContents.getProjectType().equals("2")) {
            passportNumber = getIntent().getStringExtra("passportNumber");
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.to_apply_for_an_island2_return:

                break;
            //            TODO 编辑
            case R.id.to_apply_for_an_island2_img1:

                break;
            //            TODO 添加同行人员
            case R.id.to_apply_for_an_island2_img2:
                int expenses = Integer.parseInt(to_apply_for_an_island2_tv1.getText().toString());
                if (isColleague.equals("0")) {
                    sumCost = expenses+"";
                    to_apply_for_an_island2_tv3.setText(sumCost);
                }else {
                    sumCost = ""+expenses*colleagueUserNum;
                    to_apply_for_an_island2_tv3.setText(sumCost);
                }
                break;
            //            TODO 选择路线
            case R.id.to_apply_for_an_island2_rl1:
                initGetLandLine();
                break;
            //            TODO 登岛时间
            case R.id.to_apply_for_an_island2_rl2:
                initGetLandLineTime();
                break;
            //            TODO 添加图片
            case R.id.to_apply_for_an_island2_rl3:

                break;
            //            TODO 完成
            case R.id.to_apply_for_an_island2_btn:
                if (!to_apply_for_an_island2_tv1.getText().toString().equals("")) {
                    initPostReport();
                }else {
                    Toast.makeText(ToApplyForAnlsland2Activity.this, "请选择登岛路线时间，如果没有请及时联系管理员", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    //            TODO 查询登岛路线
    private void initGetLandLine(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<GetLandLineBean> clientFragment = fzbInterface.getlandLine(FinalContents.getUserID(), FinalContents.getProjectID());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetLandLineBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetLandLineBean getLandLineBean) {
                        List<GetLandLineBean.DataBean> list = getLandLineBean.getData();

                        initSelect(list,getlandLine);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的客户（失效）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //            TODO 登岛路线时间
    private void initGetLandLineTime(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<GetLandLineTimeBean> clientFragment = fzbInterface.getlandLinetime(routeid2);
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetLandLineTimeBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetLandLineTimeBean getLandLineTimeBean) {
                        List<GetLandLineTimeBean.DataBean> list = getLandLineTimeBean.getData();
                        initSelect2(list,getlandLinetime);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的客户（失效）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //            TODO 请求登岛报备
    private void initPostReport(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LandSaveBean> userMessage = fzbInterface.getIslandSave(FinalContents.getPreparationId(),routeid,FinalContents.getProjectType(), isColleague, colleagueUserNum,idNumber,age,gender,passportNumber, passportImg, route, isLandTime, isPay, sumCost, landingImg, occupation, focus,FinalContents.getUserID(), jsonStr,customerName);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LandSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(LandSaveBean landSaveBean) {
                        Toast.makeText(ToApplyForAnlsland2Activity.this, landSaveBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ToApplyForAnlsland2Activity.this, "请确定您的输入信息是否完整", Toast.LENGTH_SHORT).show();
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //查询登岛路线选择器
    private void initSelect(final List<GetLandLineBean.DataBean> list, final TextView textView){
        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(ToApplyForAnlsland2Activity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                textView.setText(list.get(options1).getRouteName());
                routeid2 = list.get(options1).getId();
                route = list.get(options1).getRouteName();
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建

        List<String> stringList = new ArrayList<>();
        for (int i = 0;i < list.size();i++){
            stringList.add(list.get(i).getRouteName());
        }

        //      把数据绑定到控件上面
        pvOptions.setPicker(stringList);
        //      展示
        pvOptions.show();
    }


    //登岛路线时间选择器
    private void initSelect2(final List<GetLandLineTimeBean.DataBean> list, final TextView textView){
        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(ToApplyForAnlsland2Activity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                textView.setText(list.get(options1).getStartEndTime());
                routeid = list.get(options1).getId();
                isLandTime = list.get(options1).getStartEndTime();
                to_apply_for_an_island2_tv1.setText(list.get(options1).getExpenses());
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        List<String> stringList = new ArrayList<>();
        for (int i = 0;i < list.size();i++){
            stringList.add(list.get(i).getStartEndTime());
        }
        pvOptions.setPicker(stringList);
        //      展示
        pvOptions.show();
    }
}
