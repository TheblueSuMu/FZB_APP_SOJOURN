package com.xcy.fzb.all.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.makeramen.roundedimageview.RoundedImageView;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.StoreListBean;
import com.xcy.fzb.all.modle.CityBean;
import com.xcy.fzb.all.modle.HotBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.project_attache.adapter.ClusterItem;
import com.xcy.fzb.project_attache.adapter.ClusterManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapHouseActivity extends AppCompatActivity implements View.OnClickListener, BaiduMap.OnMapLoadedCallback {

    LinearLayout map_house_return;
    EditText map_house_search;
    TextView map_house_check;

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private MapStatus mMapStatus;
    private ClusterManager<MyItem> mClusterManager;
    private List<MyItem> items;
    private LayoutInflater mLayoutIn;
    private View view;
    private PopupWindow popupWindow;
    private List<StoreListBean.DataBean.RowsBean> rows;
    private List<String> strings = new ArrayList<>();
    private StringBuffer sb;
    private TextView pop_name;
    private TextView pop_title;
    private LinearLayout layout;
    private double vs1;
    private double vs2;

    // 定位相关
    LocationClient mLocClient;
    //定位监听
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位
    BDLocation mlocation;
    private LatLng ll;
    private MyItem item1;
    private LinearLayout pop_ll_1;

    int ifMG = 0;
    private List<StoreListBean.DataBean.RowsBean> rows1;
    private double ssv;
    private double ssvs;

    int ifKeyListener = 0;
    private List<CityBean.DataBean> data;
    private List<HotBean.DataBean.RowsBean> rows2;

    //城市弹窗数据
    RoundedImageView imageAvatar;
    TextView nameText;
    TagContainerLayout tagView;
    TextView chick;
    TextView attention;
    TextView collect;
    TextView transmit;
    TextView price;
    TextView price_money;
    TextView square;
    TextView commission;
    TextView second;
    LinearLayout modulebroke_ll;
    TextView group_booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_house);

        StatusBar.makeStatusBarTransparent(this);

        mMapView = findViewById(R.id.map_house_mapView);

        if (ContextCompat.checkSelfPermission(MapHouseActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(MapHouseActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            if (FinalContents.getIfCity().equals("")) {
                initView();
            } else {
                initCityData();//城市名字 + ID
            }
//            Toast.makeText(TestMapActivity.this, "已开启定位权限", Toast.LENGTH_LONG).show();
        }

    }

    //城市名字 + ID
    private void initCityData() {

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
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(CityBean cityBean) {
                        Log.i("MyCL", "cityBean:" + cityBean.getData().size());
                        data = cityBean.getData();
                        initView();
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView() {

        map_house_return = findViewById(R.id.map_house_return);
        map_house_search = findViewById(R.id.map_house_search_S);
        map_house_check = findViewById(R.id.map_house_check);
        //输入框回车事件监听
        map_house_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
                    if (ifKeyListener == 0) {
                        String s = map_house_search.getText().toString();
                        if (s.equals("")) {
                            //提示用户输入内容再搜索
                            Toast.makeText(MapHouseActivity.this, "输入框为空，请输入内容再进行查找", Toast.LENGTH_SHORT).show();
                        } else {
                            if (FinalContents.getIfCity().equals("")) {
                                if (ifMG == 0) {//门店
                                    for (int p = 0; p < rows.size(); ++p) {
                                        if (s.equals(rows.get(p).getStoreName())) {
                                            StringBuffer stringBuffer = new StringBuffer();
                                            StringBuffer append = stringBuffer.append(rows.get(p).getLocation());
                                            for (int j = 0; j < append.length(); ++j) {
                                                if (append.substring(j, j + 1).equals(",")) {
                                                    //lo 后
                                                    ssv = Double.parseDouble(append.substring(0, j));
                                                    //la 前
                                                    ssvs = Double.parseDouble(append.substring(j + 1));
                                                    ifKeyListener = 1;
                                                }
                                            }
                                        }
                                    }
                                } else if (ifMG == 1) {//公司
                                    for (int p = 0; p < rows1.size(); ++p) {
                                        if (s.equals(rows1.get(p).getCompanyName())) {
                                            StringBuffer stringBuffer = new StringBuffer();
                                            StringBuffer append = stringBuffer.append(rows1.get(p).getComLocation());
                                            for (int j = 0; j < append.length(); ++j) {
                                                if (append.substring(j, j + 1).equals(",")) {
                                                    ssv = Double.parseDouble(append.substring(0, j));//lo 后

                                                    ssvs = Double.parseDouble(append.substring(j + 1));//la 前

                                                    ifKeyListener = 1;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                for (int p = 0; p < rows2.size(); ++p) {
                                    if (s.equals(rows2.get(p).getProjectName())) {
                                        StringBuffer stringBuffer = new StringBuffer();
                                        StringBuffer append = stringBuffer.append(rows2.get(p).getLocation());
                                        for (int j = 0; j < append.length(); ++j) {
                                            if (append.substring(j, j + 1).equals(",")) {
                                                ssv = Double.parseDouble(append.substring(0, j));//lo 后

                                                ssvs = Double.parseDouble(append.substring(j + 1));//la 前

                                                ifKeyListener = 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        map_house_return.setOnClickListener(this);
        map_house_check.setOnClickListener(this);

        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new

                LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        option.setAddrType("all");
        mLocClient.setLocOption(option);
        mLocClient.start();

        if (FinalContents.getIfCity().

                equals("")) {
            if (ifMG == 0) {
                initMapData();//门店数据
            } else if (ifMG == 1) {
                initMapDataS();//公司数据
            }
        } else {
            if (data.size() == 0) {

            } else {
                for (int i = 0; i < data.size(); ++i) {
                    if (FinalContents.getCityID().equals(data.get(i).getId())) {
                        map_house_check.setText(data.get(i).getCity());
                    }
                }
            }
            initMapDataProject();//城市公司项目列表数据
        }

//       initMy();

    }

    //城市公司项目列表数据
    private void initMapDataProject() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> userMessage = fzbInterface.getList(FinalContents.getUserID(), FinalContents.getIfCity(), "", "", "3", "", "", "", "", "", "", "", "", "", "", "1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        rows2 = hotBean.getData().getRows();
                        Log.i("MyCL", "城市项目列表长度：" + rows2.size());
                        initMap();
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

//    private void initMy() {
//
//
//
//    }

    //公司数据
    private void initMapDataS() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<StoreListBean> storeList = fzbInterface.getCompanList("", "", "", FinalContents.getUserID(), "1000");
        storeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreListBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(StoreListBean storeListBean) {
                        rows1 = storeListBean.getData().getRows();
                        Log.i("MyCL", "公司数据：" + rows1.size());
                        initMap();
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //门店数据
    private void initMapData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<StoreListBean> storeList = fzbInterface.getStoreList("", "", "", FinalContents.getUserID(), "1000", "", "", "");
        storeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreListBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(StoreListBean storeListBean) {
                        rows = storeListBean.getData().getRows();
                        Log.i("MyCL", "门店数据：" + rows.size());
                        initMap();
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initMap() {

        mLayoutIn = LayoutInflater.from(MapHouseActivity.this);
        mMapStatus = new MapStatus.Builder().target(new LatLng(39.914935, 116.403119)).zoom(8).build();

        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));
        // 定义点聚合管理类ClusterManager
        mClusterManager = new ClusterManager<MyItem>(this, mBaiduMap);
        // 添加Marker点
        addMarkers();
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        // 设置maker点击时的响应
        mBaiduMap.setOnMarkerClickListener(mClusterManager);

//        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
//            @Override
//            public boolean onClusterClick(Cluster<MyItem> cluster) {
//                Toast.makeText(MainActivity.this, "有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
        //每个item点击事件
        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {

            private TextView pop_address;

            @Override
            public boolean onClusterItemClick(MyItem item) {

                item1 = item;

                if (FinalContents.getIfCity().equals("")) {
                    layout = (LinearLayout) getLayoutInflater().inflate(R.layout.pop_five_activity_bottom_layout, null);
                    pop_title = layout.findViewById(R.id.pop_title);
                    pop_address = layout.findViewById(R.id.pop_address);
                    pop_name = layout.findViewById(R.id.pop_name);
                    pop_ll_1 = layout.findViewById(R.id.pop_ll_1);
                    if (ifMG == 0) {
                        for (int i = 0; i < rows.size(); ++i) {
                            StringBuffer stringBuffer = new StringBuffer();
                            StringBuffer append = stringBuffer.append(rows.get(i).getLocation());
                            for (int j = 0; j < append.length(); ++j) {
                                if (append.substring(j, j + 1).equals(",")) {
                                    vs1 = Double.parseDouble(append.substring(0, j));
                                    vs2 = Double.parseDouble(append.substring(j + 1));
                                    if (item.getPosition().longitude == vs1) {
                                        pop_title.setText(rows.get(i).getStoreName());
                                        pop_address.setText(rows.get(i).getAddress());
                                        if (rows.get(i).getShopownerName().equals("")) {
                                            pop_ll_1.setVisibility(View.GONE);
                                        } else {
                                            pop_ll_1.setVisibility(View.VISIBLE);
                                            pop_name.setText(rows.get(i).getShopownerName());
                                        }
                                    }

                                }
                            }
                        }
                    } else if (ifMG == 1) {
                        for (int i = 0; i < rows1.size(); ++i) {
                            StringBuffer stringBuffer = new StringBuffer();
                            StringBuffer append = stringBuffer.append(rows1.get(i).getComLocation());
                            for (int j = 0; j < append.length(); ++j) {
                                if (append.substring(j, j + 1).equals(",")) {
                                    vs1 = Double.parseDouble(append.substring(0, j));
                                    vs2 = Double.parseDouble(append.substring(j + 1));
                                    if (item.getPosition().longitude == vs1) {
                                        pop_title.setText(rows1.get(i).getCompanyName());
                                        pop_address.setText(rows1.get(i).getCompanyAddress());
                                        if (rows1.get(i).getShopownerName().equals("")) {
                                            pop_ll_1.setVisibility(View.GONE);
                                        } else {
                                            pop_ll_1.setVisibility(View.VISIBLE);
                                            pop_name.setText(rows1.get(i).getShopownerName());
                                        }
                                    }

                                }
                            }
                        }
                    }
                } else {
                    layout = (LinearLayout) getLayoutInflater().inflate(R.layout.modulebroker_fragment_recycler_item, null);
                    imageAvatar = layout.findViewById(R.id.ImageView);
                    nameText = (TextView) layout.findViewById(R.id.TextViewName);
                    tagView = layout.findViewById(R.id.tagView);
                    chick = (TextView) layout.findViewById(R.id.chick);
                    attention = (TextView) layout.findViewById(R.id.attention);
                    collect = (TextView) layout.findViewById(R.id.collect);
                    transmit = (TextView) layout.findViewById(R.id.transmit);
                    price_money = (TextView) layout.findViewById(R.id.price_money);
                    price = (TextView) layout.findViewById(R.id.price);
                    square = (TextView) layout.findViewById(R.id.square);
                    commission = (TextView) layout.findViewById(R.id.commission);
                    second = (TextView) layout.findViewById(R.id.second);
                    modulebroke_ll = (LinearLayout) layout.findViewById(R.id.modulebroke_ll);
                    group_booking = layout.findViewById(R.id.group_booking_item);
                    modulebroke_ll.setVisibility(View.GONE);
                    for (int i = 0; i < rows2.size(); ++i) {
                        StringBuffer stringBuffer = new StringBuffer();
                        StringBuffer append = stringBuffer.append(rows2.get(i).getLocation());
                        for (int j = 0; j < append.length(); ++j) {
                            if (append.substring(j, j + 1).equals(",")) {
                                vs1 = Double.parseDouble(append.substring(0, j));
                                vs2 = Double.parseDouble(append.substring(j + 1));
                                if (item.getPosition().longitude == vs1) {
                                    Glide.with(MapHouseActivity.this).load(FinalContents.getImageUrl() + rows2.get(i).getProjectImg()).into(imageAvatar);
                                    nameText.setText("[" + rows2.get(i).getArea() + "]" + rows2.get(i).getProjectName());

                                    String ids = rows2.get(i).getProductFeature();//从pd里取出字符串
                                    List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list

                                    if (rows2.get(i).getProductFeature().equals("")) {
                                        tagView.setVisibility(View.GONE);
                                    } else {
                                        tagView.setVisibility(View.VISIBLE);
                                        tagView.setTheme(ColorFactory.NONE);
                                        tagView.setTags(tags);
                                    }

//        if(FinalContents.getZyHome().equals("1")){
//            holder.group_booking.setVisibility(View.GONE);
//        }else {
                                    if (rows2.get(i).getIsgroup().equals("1")) {
                                        group_booking.setVisibility(View.VISIBLE);
                                        group_booking.setText(rows2.get(i).getGroupNum() + "个团火热报名中...");
                                    } else {
                                        group_booking.setVisibility(View.GONE);
                                    }
//        }

                                    chick.setText(Html.fromHtml("报备(" + "<font color='#A52A2A'>" + rows2.get(i).getReportAmount() + "</font>" + ")"));
                                    attention.setText(Html.fromHtml("关注(" + "<font color='#A52A2A'>" + rows2.get(i).getBrowseNum() + "</font>" + ")"));
                                    collect.setText(Html.fromHtml("收藏(" + "<font color='#A52A2A'>" + rows2.get(i).getCollectionNum() + "</font>" + ")"));
                                    transmit.setText(Html.fromHtml("转发(" + "<font color='#A52A2A'>" + rows2.get(i).getForwardingAmount() + "</font>" + ")"));


//                                    if(rows2.get(i).getProjectType().equals("2")){
//                                        price.setText(rows2.get(i).getReferenceToatlPrice());
//                                        price_money.setText(rows2.get(i).getReferenceToatlUnit());
//                                    }else if(rows2.get(i).getProjectType().equals("3")){
                                    price.setText(rows2.get(i).getProductUnitPrice());
                                    price_money.setText(rows2.get(i).getMonetaryUnit());
//                                    }else if(rows2.get(i).getProjectType().equals("1")){
//                                       price.setText(rows2.get(i).getProductUnitPrice());
//                                       price_money.setText(rows2.get(i).getMonetaryUnit());
//                                    }

                                    square.setText(rows2.get(i).getAreaInterval());
//                                    commission.setText("佣金："+rows2.get(i).getCommission());
//                                    second.setText("秒结："+rows2.get(i).getSecondPay());
//                                    FinalContents.setProjectID(rows2.get(i).getProjectId());

                                }
                            }
                        }
                    }

                }


                popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //点击空白处时，隐藏掉pop窗口
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                //添加弹出、弹入的动画
//                        popupWindow.setAnimationStyle(R.style.Popupwindow);
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.BOTTOM, 0, -location[1]);
                //添加按键事件监听
                if (FinalContents.getIfCity().equals("")) {
                    setButtonListeners(layout);
                }
                //添加pop窗口关闭事件，主要是实现关闭时改变背景的透明度
                //        popupWindow.setOnDismissListener(new poponDismissListener());
                //        backgroundAlpha(1f);

                return false;
            }
        });

    }


    /**
     * 向地图添加Marker点
     */
    public void addMarkers() {
        // 添加Marker点
        items = new ArrayList<MyItem>();
        items.clear();
        strings.clear();
        mClusterManager.clearItems();
//        for (int p = 0; p < items.size(); ++p){
//            mClusterManager.removeItem(items.get(p));
//        }
        if (FinalContents.getIfCity().equals("")) {
            if (ifMG == 0) {
                Log.i("MyCL", "添加门店数据");
                for (int i = 0; i < rows.size(); ++i) {
                    StringBuffer stringBuffer = new StringBuffer();
                    StringBuffer append = stringBuffer.append(rows.get(i).getLocation());
                    for (int j = 0; j < append.length(); ++j) {
                        if (append.substring(j, j + 1).equals(",")) {
                            double v = Double.parseDouble(append.substring(0, j));
                            double v1 = Double.parseDouble(append.substring(j + 1));
                            strings.add(rows.get(i).getStoreName());
                            items.add(new MyItem(new LatLng(v1, v)));

                        }
                    }
                }
            } else if (ifMG == 1) {
                Log.i("MyCL", "添加公司数据");
                for (int i = 0; i < rows1.size(); ++i) {
                    StringBuffer stringBuffer = new StringBuffer();
                    StringBuffer append = stringBuffer.append(rows1.get(i).getComLocation());
                    for (int j = 0; j < append.length(); ++j) {
                        if (append.substring(j, j + 1).equals(",")) {
                            double v = Double.parseDouble(append.substring(0, j));
                            double v1 = Double.parseDouble(append.substring(j + 1));
                            strings.add(rows1.get(i).getCompanyName());
                            items.add(new MyItem(new LatLng(v1, v)));
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < rows2.size(); ++i) {
                StringBuffer stringBuffer = new StringBuffer();
                StringBuffer append = stringBuffer.append(rows2.get(i).getLocation());
                for (int j = 0; j < append.length(); ++j) {
                    if (append.substring(j, j + 1).equals(",")) {
                        double v = Double.parseDouble(append.substring(0, j));
                        double v1 = Double.parseDouble(append.substring(j + 1));
                        strings.add(rows2.get(i).getProjectName());
                        items.add(new MyItem(new LatLng(v1, v)));
                    }
                }
            }
        }
        Log.i("MyCL", "items数据长度：" + items.size());
        mClusterManager.addItems(items);
    }

    /**
     * 每个Marker点，包含Marker点坐标以及图标
     */
    public class MyItem implements ClusterItem {
        private final LatLng mPosition;

        private MyItem(LatLng latLng) {
            mPosition = latLng;
            Log.i("MyCL", "mPosition数据：" + mPosition);
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {

            Bitmap bitmap = BitmapFactory.decodeResource(MapHouseActivity.this.getResources(), R.mipmap.mapb);
            if (FinalContents.getIfCity().equals("")) {
                if (ifMG == 0) {
                    Log.i("MyCL", "rows数据长度：" + rows.size());
                    for (int i = 0; i < rows.size(); ++i) {
                        StringBuffer stringBuffer = new StringBuffer();
                        StringBuffer append = stringBuffer.append(rows.get(i).getLocation());
                        for (int j = 0; j < append.length(); ++j) {
                            if (append.substring(j, j + 1).equals(",")) {
                                double v = Double.parseDouble(append.substring(0, j));
                                double v1 = Double.parseDouble(append.substring(j + 1));
                                if (mPosition.longitude == v) {
                                    if (rows.get(i).getStatus().equals("0")) {
                                        sb = new StringBuffer(rows.get(i).getStoreName() + "(未合作)");
                                    } else if (rows.get(i).getStatus().equals("1")) {
                                        if (rows.get(i).getState().equals("1")) {
                                            sb = new StringBuffer(rows.get(i).getStoreName() + "(签约)");
                                        } else if (rows.get(i).getState().equals("2")) {
                                            sb = new StringBuffer(rows.get(i).getStoreName() + "(装机)");
                                        } else if (rows.get(i).getState().equals("3")) {
                                            sb = new StringBuffer(rows.get(i).getStoreName() + "(培训)");
                                        } else if (rows.get(i).getState().equals("4")) {
                                            sb = new StringBuffer(rows.get(i).getStoreName() + "(维护)");
                                        } else {
                                            sb = new StringBuffer(rows.get(i).getStoreName());
                                        }
                                    } else if (rows.get(i).getStatus().equals("2")) {
                                        sb = new StringBuffer(rows.get(i).getStoreName() + "(取消合作)");
                                    } else if (rows.get(i).getStatus().equals("3")) {
                                        sb = new StringBuffer(rows.get(i).getStoreName() + "(倒闭)");
                                    }
//                                    sb = new StringBuffer(rows.get(i).getStoreName());
                                }
                            }
                        }
                    }
                } else if (ifMG == 1) {
                    Log.i("MyCL", "rows1数据长度：" + rows1.size());
                    for (int i = 0; i < rows1.size(); ++i) {
                        StringBuffer stringBuffer = new StringBuffer();
                        StringBuffer append = stringBuffer.append(rows1.get(i).getComLocation());
                        for (int j = 0; j < append.length(); ++j) {
                            if (append.substring(j, j + 1).equals(",")) {
                                double v = Double.parseDouble(append.substring(0, j));
                                double v1 = Double.parseDouble(append.substring(j + 1));
                                if (mPosition.longitude == v) {
                                    if (rows1.get(i).getStatus().equals("0")) {
                                        sb = new StringBuffer(rows1.get(i).getCompanyName() + "(未合作)");
                                    } else if (rows1.get(i).getStatus().equals("1")) {
                                        if (rows1.get(i).getState().equals("1")) {
                                            sb = new StringBuffer(rows1.get(i).getCompanyName() + "(签约)");
                                        } else if (rows1.get(i).getState().equals("2")) {
                                            sb = new StringBuffer(rows1.get(i).getCompanyName() + "(装机)");
                                        } else if (rows1.get(i).getState().equals("3")) {
                                            sb = new StringBuffer(rows1.get(i).getCompanyName() + "(培训)");
                                        } else if (rows1.get(i).getState().equals("4")) {
                                            sb = new StringBuffer(rows1.get(i).getCompanyName() + "(维护)");
                                        } else {
                                            sb = new StringBuffer(rows1.get(i).getCompanyName());
                                        }
                                    } else if (rows1.get(i).getStatus().equals("2")) {
                                        sb = new StringBuffer(rows1.get(i).getCompanyName() + "(取消合作)");
                                    } else if (rows1.get(i).getStatus().equals("3")) {
                                        sb = new StringBuffer(rows1.get(i).getCompanyName() + "(倒闭)");
                                    }
//                                    sb = new StringBuffer(rows1.get(i).getCompanyName());
                                }
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < rows2.size(); ++i) {
                    StringBuffer stringBuffer = new StringBuffer();
                    StringBuffer append = stringBuffer.append(rows2.get(i).getLocation());
                    for (int j = 0; j < append.length(); ++j) {
                        if (append.substring(j, j + 1).equals(",")) {
                            double v = Double.parseDouble(append.substring(0, j));
                            double v1 = Double.parseDouble(append.substring(j + 1));
                            if (mPosition.longitude == v) {
                                sb = new StringBuffer(rows2.get(i).getProjectName() + rows2.get(i).getProductUnitPrice() + rows2.get(i).getMonetaryUnit());
                            }
                        }
                    }
                }
            }

            return BitmapDescriptorFactory.fromView(getView(bitmap, sb));
        }
    }

    //跳转第三方地图 路线规划
    private void setButtonListeners(LinearLayout layout) {

        Button pop_btn = layout.findViewById(R.id.pop_btn);
        pop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MapHouseActivity.this, "路线", Toast.LENGTH_SHORT).show();
                //定义起终点坐标（天安门和百度大厦）
                LatLng startPoint = new LatLng(ll.latitude, ll.longitude);
                LatLng endPoint = new LatLng(item1.getPosition().latitude, item1.getPosition().longitude);

//构建RouteParaOption参数以及策略
//也可以通过startName和endName来构造
                RouteParaOption paraOption = new RouteParaOption()
                        .startPoint(startPoint)
                        .endPoint(endPoint)
                        .busStrategyType(RouteParaOption.EBusStrategyType.bus_recommend_way);
//调起百度地图
                try {
                    BaiduMapRoutePlan.openBaiduMapTransitRoute(paraOption, MapHouseActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }//调起结束时及时调用finish方法以释放相关资源
                BaiduMapRoutePlan.finish(MapHouseActivity.this);
                popupWindow.dismiss();
            }
        });
    }


    @Override
    public void onMapLoaded() {
        // TODO Auto-generated method stub
        // TODO 自动生成方法存根
        mMapStatus = new MapStatus.Builder().zoom(9).build();
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));
    }

    private static class ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
    }

    private View getView(Bitmap resId, StringBuffer str) {
        ViewHolder mViewHolder;
        if (view == null) {
            //获取布局
            view = mLayoutIn.inflate(R.layout.marker_item, null);
            mViewHolder = new ViewHolder();
//            mViewHolder.mImageView = view.findViewById(R.id.iv_marker_icon);//图标
            mViewHolder.mTextView = view.findViewById(R.id.tv_marker_text);//文本
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
//        mViewHolder.mImageView.setImageBitmap(resId);//设置图片
        mViewHolder.mTextView.setText(str);//设置文字
        return view;//返回

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.map_house_return:
                //返回上一层
                finish();
                break;
            case R.id.map_house_check:
                //门店/公司/经纪人选择
                initCheck();
                break;
        }

    }

    private void initCheck() {

        final List<String> list = new ArrayList<>();

        if (FinalContents.getIfCity().equals("")) {
            list.add("公司");
            list.add("门店");
        } else {
            for (int i = 0; i < data.size(); ++i) {
                list.add(data.get(i).getCity());
            }
        }


//        list.add("经纪人");


        OptionsPickerView pvOptions = new OptionsPickerBuilder(MapHouseActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //              展示选中数据
                map_house_check.setText(list.get(options1));
                if (FinalContents.getIfCity().equals("")) {
                    if (options1 == 1) {//门店
                        ifMG = 0;
//                    initMy();
                        mBaiduMap.clear();
                        initView();
                    } else if (options1 == 0) {//公司
                        ifMG = 1;
//                    initMy();
                        mBaiduMap.clear();
                        initView();
                    }
                } else {
                    for (int i = 0; i < data.size(); ++i) {
                        if (list.get(options1).equals(data.get(i).getCity())) {
                            FinalContents.setIfCity(data.get(i).getId());
                            mBaiduMap.clear();
                            initView();
                        }
                    }
                }


            }
        })
                .setSelectOptions(1)
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();

    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mlocation = location;

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(mlocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(mlocation.getLatitude())
                    .longitude(mlocation.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();

            }
            if (ifKeyListener == 1) {
                ifKeyListener = 0;
                LatLng ll1 = new LatLng(ssvs,
                        ssv);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll1).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
        // 在activity执行onResume时必须调用mMapView. onResume ()
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
        // 在activity执行onPause时必须调用mMapView. onPause ()
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
        // 在activity执行onDestroy时必须调用mMapView.onDestroy()
    }

    //    TODO 动态打开gps
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                    initView();//开始定位
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(MapHouseActivity.this, "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                    initView();
                }
                break;
            default:
                break;
        }
    }
}
