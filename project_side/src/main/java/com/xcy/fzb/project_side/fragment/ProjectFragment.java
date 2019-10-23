package com.xcy.fzb.project_side.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stx.xmarqueeview.XMarqueeView;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.RecyclerAdapter;
import com.xcy.fzb.project_side.adapter.TextBannerAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.application.DemoApplication;
import com.xcy.fzb.project_side.modle.Bean;
import com.xcy.fzb.project_side.modle.CityBean;
import com.xcy.fzb.project_side.modle.HotBean;
import com.xcy.fzb.project_side.modle.ImgData;
import com.xcy.fzb.project_side.modle.MessageBean2;
import com.xcy.fzb.project_side.presente.MyLinearLayoutManager;
import com.xcy.fzb.project_side.presente.SharItOff;
import com.xcy.fzb.project_side.presente.StatusBar;
import com.xcy.fzb.project_side.service.MyService;
import com.xcy.fzb.project_side.view.MyClientActivity;
import com.xcy.fzb.project_side.view.MyProjectActivity;
import com.xcy.fzb.project_side.view.OverSeaActivity;
import com.xcy.fzb.project_side.view.SearchInterfaceActivity;
import com.xcy.fzb.project_side.view.WebViewActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;


@SuppressLint("NewApi")
public class ProjectFragment extends AllFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, SensorEventListener {
    private View view;
    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    private RecyclerView recyclerView;
    private TextView city;
    //文字轮播数据
    List<Bean> messagelist2 = new ArrayList<>();

    private XMarqueeView tvBanner2;
    private SwipeRefreshLayout layout;
    private LinearLayout textView1;
    private LinearLayout textView2;
    private LinearLayout textView3;
    private LinearLayout textView4;
    //搜索
    private EditText search;

    private DateTimePickerView dateTimePickerView;

    private String newsListUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/newsList?";
    private String cityUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/findCityForParentId";
    private String messageUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/messageList?";
    private String hotUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectList?";

    private String projectType = "1";//房产类型
    private String arrposid = "1"; //新闻类型
    private List<ImgData.DataBean> imglist = new ArrayList<>();
    private List<MessageBean2.DataBean.RowsBean> messagelist = new ArrayList<>();
    private List<CityBean.DataBean> citylist = new ArrayList<>();
    private List<HotBean.DataBean.RowsBean> hotlist = new ArrayList<>();
    private PopupWindow p;
    private SwipeRefreshLayout.OnRefreshListener listener;

    //TODO     摇一摇
    private SensorManager mSensorManager;
    private Vibrator vibrator;
    private DemoApplication application;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project, null);
        StatusBar.makeStatusBarTransparent(getActivity());

        fvbId(view);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);


        initView();

        tvBanner();

        initHotList();

        return view;
    }

    //    TODO 摇一摇start
    @Override
    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onSensorChanged(SensorEvent event) {
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
                Log.i("MyCL","摇一摇");
                initHotList();

                vibrator.vibrate(100);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
//    TODO 摇一摇end

    @Override
    public void onResume() {
        super.onResume();
        tvBanner2.startFlipping();
        //TODO 获取加速传感器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        tvBanner2.stopFlipping();
        //        TODO 摇一摇
        mSensorManager.unregisterListener(this);
    }

    //命名区域
    private void fvbId(View view){
        application = (DemoApplication) getActivity().getApplication();


        recyclerView = view.findViewById(R.id.home_recycler_vertical);

        banner = view.findViewById(R.id.home_banner);

        layout = view.findViewById(R.id.home_srl);
        tvBanner2 =  view.findViewById(R.id.tv_banner2);

        textView1 = view.findViewById(R.id.home_item_sojourn);
        textView2 = view.findViewById(R.id.home_item_overseas);
        textView3 = view.findViewById(R.id.home_item_client);
        textView4 = view.findViewById(R.id.home_item_brokerage);
        city = view.findViewById(R.id.project_city_selector);


        layout.setOnRefreshListener(this);
        search = view.findViewById(R.id.home_search);
        search.setFocusable(false);
        search.setOnClickListener(this);
        city.setOnClickListener(this);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);

        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString("搜索");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        search.setHint(new SpannedString(ss));
    }

    //点击事件
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.project_city_selector){
            showPickerView();
        } else if (view.getId() == R.id.home_search) {
            Intent intent = new Intent(view.getContext(), SearchInterfaceActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.home_item_sojourn) {
            FinalContents.setProjectType("3");
            Intent intent = new Intent(view.getContext(), OverSeaActivity.class);
            startActivity(intent);
        }else if (view.getId() == R.id.home_item_overseas) {
            FinalContents.setProjectType("2");
            Intent intent = new Intent(view.getContext(), OverSeaActivity.class);
            startActivity(intent);
        }else if (view.getId() == R.id.home_item_brokerage) {
            Intent intent = new Intent(view.getContext(), MyProjectActivity.class);
            startActivity(intent);
        }else if (view.getId() == R.id.home_item_client) {
            Intent intent = new Intent(view.getContext(), MyClientActivity.class);
            intent.putExtra("client","1");
            startActivity(intent);
        }
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView() {
//      要展示的数据
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CityBean> userMessage = fzbInterface.getCityList();
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CityBean cityBean) {
                        citylist = cityBean.getData();
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < citylist.size(); i++) {
                            list.add(citylist.get(i).getCity());
                        }
                        //      监听选中
                        OptionsPickerView pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //               返回的分别是三个级别的选中位置
                                //              展示选中数据
                                city.setText(list.get(options1));
                                FinalContents.setCityID(citylist.get(options1).getId());
                                Log.i("city",FinalContents.getCityID());
                                initHotList();
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
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    private void initHotList() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> userMessage = fzbInterface.getHotList(FinalContents.getUserID(),FinalContents.getCityID(),"1");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(HotBean hotBean) {
                        Log.i("city",hotBean.getCode());
                        if (hotBean.getCode().equals("1")) {
                            HotBean.DataBean hotBeanData = hotBean.getData();
                            hotlist = hotBeanData.getRows();
                            //在此处修改布局排列方向
                            recyclerView.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(view.getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            layoutManager.setScrollEnabled(false);
                            recyclerView.setLayoutManager(layoutManager);
                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(hotlist);
                            recyclerView.setNestedScrollingEnabled(false);
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();

                        }else {
                            recyclerView.setVisibility(View.GONE);
                        }
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

    //文字轮播
    private void tvBanner(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MessageBean2> userMessage = fzbInterface.getMessageTextList(FinalContents.getUserID(),FinalContents.getCityID(),"0");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageBean2>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MessageBean2 messageBean) {
                        MessageBean2.DataBean dataBean = messageBean.getData();
                        messagelist = dataBean.getRows();

                        for (int i = 0; i < messagelist.size(); i++){
                            if (i%2 == 1) {
                                messagelist2.add(new Bean(R.mipmap.give,messagelist.get(i).getTitle()));
                            }else {
                                messagelist2.add(new Bean(R.mipmap.lodger,messagelist.get(i).getTitle()));
                            }
                        }

                        tvBanner2.setAdapter(new TextBannerAdapter(messagelist2, view.getContext()));
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

    //首页轮播图
    private void initView() {
        list_path = new ArrayList<>();
        list_title = new ArrayList<>();
        list_path.clear();
        list_title.clear();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ImgData> userMessage = fzbInterface.getBannerList(FinalContents.getUserID(),FinalContents.getCityID(),projectType,arrposid);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ImgData imgData) {
                        imglist = imgData.getData();
                        Log.i("aaa","图片地址："+ imglist.get(0).getCoverImg());

                        Log.i("aaa","图片"+ imglist.get(0).getCoverImg());
                        for (int i = 0; i < imglist.size(); i++){
                            list_path.add("http://39.98.173.250:8080"+ imglist.get(i).getCoverImg());
                            list_title.add(imglist.get(i).getTitle());
                        }

                        //设置banner样式
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                        //设置图片加载器
                        banner.setImageLoader(new MyLoader());
                        //设置图片集合
                        banner.setImages(list_path);
                        //设置banner动画效果
                        banner.setBannerAnimation(Transformer.Default);
                        //设置标题集合（当banner样式有显示title时）
                        banner.setBannerTitles(list_title);
                        //设置自动轮播，默认为true
                        banner.isAutoPlay(true);
                        //设置轮播时间
                        banner.setDelayTime(3000);
                        //设置指示器位置（当banner模式中有指示器时）
                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();

                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                Intent intent = new Intent(view.getContext(), WebViewActivity.class);
                                intent.putExtra("title","新闻详情");
                                intent.putExtra("webview",imglist.get(position).getContent());
                                startActivity(intent);
                            }
                        });
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

    @Override
    public void onRefresh() {
        if (layout.isRefreshing()) {//如果正在刷新
            initView();
            initHotList();
            layout.setRefreshing(false);//取消刷新
        }
    }

    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }


    }

}
