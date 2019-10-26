package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.RecyclerSAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.application.DemoApplication;
import com.xcy.fzb.all.modle.HotBean;
import com.xcy.fzb.all.persente.SharItOff;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CollectActivity extends AllActivity implements View.OnClickListener , SensorEventListener {

    PtrClassicFrameLayout ptrClassicFrameLayout;

    private RelativeLayout collect_img;
    private LinearLayout collect_l1;
    private LinearLayout collect_l2;
    private LinearLayout collect_ll1;
    private LinearLayout collect_ll2;

    private String hotUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectList?";

    private RecyclerView recyclerView;
    private ImageView all_no_information;

    //TODO     摇一摇
    private SensorManager mSensorManager;
    private Vibrator vibrator;
    private DemoApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        application = (DemoApplication) getApplication();
        initView();
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);



        collect_img = findViewById(R.id.collect_img);
        collect_l1 = findViewById(R.id.collect_l1);
        collect_l2 = findViewById(R.id.collect_l2);
        collect_ll1 = findViewById(R.id.collect_ll1);
        collect_ll2 = findViewById(R.id.collect_ll2);

        recyclerView = findViewById(R.id.collect_recyler);
        all_no_information = findViewById(R.id.all_no_information);

        collect_img.setOnClickListener(this);
        collect_l1.setOnClickListener(this);
        collect_l2.setOnClickListener(this);


        ptrClassicFrameLayout = findViewById(R.id.store_house_ptr_frame_9);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        if(collect_ll1.getVisibility() == View.VISIBLE){
                            recyclerViewData("3");
                        }else if(collect_ll2.getVisibility() == View.VISIBLE){
                            recyclerViewData("2");
                        }
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        recyclerViewData("3");

    }

    @SuppressLint("WrongConstant")
    private void recyclerViewData(String projectType ) {

        Log.i("bbb", "用户名ID：" + FinalContents.getUserID());
        Log.i("bbb", "城市ID：" + FinalContents.getCityID());
        Log.i("bbb", "城市公司ID：" + FinalContents.getCityID());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> hotBean1 = fzbInterface.getMyCollection(FinalContents.getUserID(), projectType);
        hotBean1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {

                        HotBean.DataBean hotBeanData = hotBean.getData();
                        List<HotBean.DataBean.RowsBean> hotlist = hotBeanData.getRows();
                        if (hotlist.size() != 0){
                            all_no_information.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            RecyclerSAdapter recyclerAdapter = new RecyclerSAdapter(hotlist);
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Log.i("MyCL", "CollectActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collect_img:
                finish();
                break;
            case R.id.collect_l1:
                collect_ll1.setVisibility(View.VISIBLE);
                collect_ll2.setVisibility(View.INVISIBLE);
                recyclerViewData("3");
                break;
            case R.id.collect_l2:
                collect_ll1.setVisibility(View.INVISIBLE);
                collect_ll2.setVisibility(View.VISIBLE);
                recyclerViewData("2");
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(collect_ll1.getVisibility() == View.VISIBLE){
            recyclerViewData("3");
        }else if(collect_ll2.getVisibility() == View.VISIBLE){
            recyclerViewData("2");
        }
    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        if(FinalContents.getIdentity().equals("63")){

        }else {
            int sensortype = event.sensor.getType();
            float[] values = event.values;
            if (sensortype == Sensor.TYPE_ACCELEROMETER) {
                /*因为一般正常情况下，任意轴数值最大就在9.8~10之间，只有在你突然摇动手机
                 *的时候，瞬时加速度才会突然增大或减少。
                 *所以，经过实际测试，只需监听任一轴的加速度大于14的时候，改变你需要的设置
                 *就OK了~~~
                 */
                if (Math.abs(values[0]) > 20 || Math.abs(values[1]) > 20 || Math.abs(values[2]) > 20) {

                    if (SharItOff.getShar().equals("隐")) {
                        SharItOff.setShar("显");
                        Toast.makeText(application, "佣金已显示，如需隐藏请摇动", Toast.LENGTH_SHORT).show();
                    } else if (SharItOff.getShar().equals("显")) {
                        SharItOff.setShar("隐");
                        Toast.makeText(application, "佣金已隐藏，如需显示请摇动", Toast.LENGTH_SHORT).show();
                    }
                    Log.i("MyCL", "摇一摇");
                    if(collect_ll1.getVisibility() == View.VISIBLE){
                        recyclerViewData("3");
                    }else if(collect_ll2.getVisibility() == View.VISIBLE){
                        recyclerViewData("2");
                    }


                    vibrator.vibrate(100);
                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }
}
