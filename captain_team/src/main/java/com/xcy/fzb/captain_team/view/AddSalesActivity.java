package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.BrokerSaveBean;
import com.xcy.fzb.captain_team.database.RatioByOwnerIdBean;
import com.xcy.fzb.captain_team.database.TeamLeaderLevelBean;
import com.xcy.fzb.captain_team.persente.SlideSwitch;
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

//TODO 圈层5-2添加销售
public class AddSalesActivity extends AppCompatActivity implements View.OnClickListener, SlideSwitch.SlideListener {

    ImageView add_sales_img;

    TextView add_sales_tv1;
    TextView add_sales_tv2;

    EditText add_sales_et1;
    EditText add_sales_et2;
    EditText add_sales_et3;
    EditText add_sales_et4;
    EditText add_sales_et5;

    Button add_sales_btn;

    RelativeLayout add_sales_rl1;
    RelativeLayout add_sales_rl2;
    private String industry = "";
    private String name = "";
    private String phone = "";
    private String loginName = "";
    private String password = "";
    private String loginFlag = "1";
    private String manageFlag = "";
    private String type = "2";

    //TODO 新增
    SlideSwitch slide;
    TextView txt;
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        initView();
    }

    private void initView() {
		
		StatusBar.makeStatusBarTransparent(this);
		
        add_sales_img = findViewById(R.id.add_sales_img1);

        add_sales_tv1 = findViewById(R.id.add_sales_tv1);
        add_sales_tv2 = findViewById(R.id.add_sales_tv2);

        add_sales_et1 = findViewById(R.id.add_sales_et1);
        add_sales_et2 = findViewById(R.id.add_sales_et2);
        add_sales_et3 = findViewById(R.id.add_sales_et3);
        add_sales_et4 = findViewById(R.id.add_sales_et4);
        add_sales_et5 = findViewById(R.id.add_sales_et5);

        add_sales_btn = findViewById(R.id.add_sales_btn);

        add_sales_rl1 = findViewById(R.id.add_sales_rl1);
        add_sales_rl2 = findViewById(R.id.add_sales_rl2);

        add_sales_img.setOnClickListener(this);
        add_sales_rl1.setOnClickListener(this);
        add_sales_rl2.setOnClickListener(this);
        add_sales_btn.setOnClickListener(this);

//        TODO 新增s
        slide = (SlideSwitch) findViewById(R.id.sswit);

        slide.setState(false);
        txt = (TextView) findViewById(R.id.stxt);
        txt1 = (TextView) findViewById(R.id.stxt1);
        slide.setSlideListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.add_sales_img1:
                finish();
                break;
//            TODO 选择团队长
            case R.id.add_sales_rl1:
                initTeamLeaderLevel();
                break;
//                TODO 级别
            case R.id.add_sales_rl2:
                initRatioByOwnerId();
                break;
//                TODO 确定
            case R.id.add_sales_btn:
                initData();
                break;
        }
    }

    private void initTeamLeaderLevel(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamLeaderLevelBean> userMessage = fzbInterface.getTeamLeaderLevel(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamLeaderLevelBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final TeamLeaderLevelBean teamLeaderLevelBean) {
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < teamLeaderLevelBean.getData().size(); i++) {
                            list.add(teamLeaderLevelBean.getData().get(i).getName());
                        }
                        //      监听选中
                        OptionsPickerView pvOptions = new OptionsPickerBuilder(AddSalesActivity.this, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //               返回的分别是三个级别的选中位置
                                //              展示选中数据
                                add_sales_tv1.setText(teamLeaderLevelBean.getData().get(options1).getName()+"");
                                FinalContents.setLevelId(teamLeaderLevelBean.getData().get(options1).getId());
                            }
                        })
                                .setSelectOptions(0)//设置选择第一个
                                .setOutSideCancelable(false)//点击背的地方不消失
                                .build();//创建
                        //      把数据绑定到控件上面
                        pvOptions.setPicker(list);
                        //      展示
                        pvOptions.show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initRatioByOwnerId(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<RatioByOwnerIdBean> userMessage = fzbInterface.getRatioByOwnerId(FinalContents.getUserID(),FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RatioByOwnerIdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final RatioByOwnerIdBean ratioByOwnerIdBean) {
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < ratioByOwnerIdBean.getData().size(); i++) {
                            list.add(ratioByOwnerIdBean.getData().get(i).getName());
                        }
                        //      监听选中
                        OptionsPickerView pvOptions = new OptionsPickerBuilder(AddSalesActivity.this, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //               返回的分别是三个级别的选中位置
                                //              展示选中数据
                                add_sales_tv2.setText(ratioByOwnerIdBean.getData().get(options1).getName()+"");
                                FinalContents.setRatioId(ratioByOwnerIdBean.getData().get(options1).getId());
                            }
                        })
                                .setSelectOptions(0)//设置选择第一个
                                .setOutSideCancelable(false)//点击背的地方不消失
                                .build();//创建
                        //      把数据绑定到控件上面
                        pvOptions.setPicker(list);
                        //      展示
                        pvOptions.show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData(){
        name = add_sales_et1.getText().toString();
        industry = add_sales_et2.getText().toString();
        phone = add_sales_et3.getText().toString();
        loginName = add_sales_et4.getText().toString();
        password = add_sales_et5.getText().toString();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BrokerSaveBean> userMessage = fzbInterface.getBrokerSave("", industry, name, phone, loginName, password, loginFlag, manageFlag,FinalContents.getUserID(),FinalContents.getRatioId(),FinalContents.getUserID(), type,FinalContents.getLevelId());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokerSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(BrokerSaveBean brokerSaveBean) {
                        Toast.makeText(AddSalesActivity.this, brokerSaveBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("添加销售获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //    TODO 新增
    @Override
    public void open() {
        txt.setVisibility(View.VISIBLE);
        txt1.setVisibility(View.GONE);
    }

    @Override
    public void close() {
        txt.setVisibility(View.GONE);
        txt1.setVisibility(View.VISIBLE);
    }
}
