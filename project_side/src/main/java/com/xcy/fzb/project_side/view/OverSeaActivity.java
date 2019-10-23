package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lzj.gallery.library.views.BannerViewPager;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.IssueAdapter;
import com.xcy.fzb.project_side.adapter.OverseaCityAdapter;
import com.xcy.fzb.project_side.adapter.RecyclerAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.application.DemoApplication;
import com.xcy.fzb.project_side.fragment.ComprehensiveFragment;
import com.xcy.fzb.project_side.fragment.CountryFragment;
import com.xcy.fzb.project_side.fragment.HouseTypeFragment;
import com.xcy.fzb.project_side.fragment.PriceFragment;
import com.xcy.fzb.project_side.fragment.ScreeningFragment;
import com.xcy.fzb.project_side.modle.HotBean;
import com.xcy.fzb.project_side.modle.ImgData;
import com.xcy.fzb.project_side.modle.NationBean;
import com.xcy.fzb.project_side.presente.MyLinearLayoutManager;
import com.xcy.fzb.project_side.presente.OkHttpPost;
import com.xcy.fzb.project_side.presente.StatusBar;
import com.xcy.fzb.project_side.service.MyService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OverSeaActivity extends AppCompatActivity implements View.OnClickListener {
    private BannerViewPager banner;
    private List<String> list_img;
    private String newsListUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/newsList?";
    private String nacationUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectCityNationlist?";
    private String projectListUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectList?"+ "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID();
    private String eventUrl = "";

    private String arrposid = "2"; //新闻类型
    private String arrpos = "4"; //专场新闻类型
    private RecyclerView nationRv;
    private RecyclerView issueRv;
    private RecyclerView hotRv;
    private TextView sort;
    private TextView screen;
    private TextView state;
    private TextView price;
    private TextView house_type;
    private TextView title;
    private RadioButton oversea_rb_1;
    private RadioButton oversea_rb_2s;
    private RadioButton oversea_rb_3;
    private RadioButton oversea_rb_4;
    private RadioButton oversea_rb_5;
    private RadioGroup oversea_rg;
    private LinearLayout oversea_ll;
    private LinearLayout oversea_lll;
    private ScrollView scrollView;
    private EditText search;
    private LinearLayout back;
    FrameLayout oversea_fl;
    private String projectLabel;

    private TagContainerLayout tagView;
    private LinearLayout oversea_linear;

    ComprehensiveFragment comprehensiveFragment;
    CountryFragment countryFragment;
    PriceFragment priceFragment;
    HouseTypeFragment houseTypeFragment;
    ScreeningFragment screeningFragment;

    FragmentManager manager;
    FragmentTransaction transaction;
    private List<NationBean.DataBean> nationlist;
    private List<ImgData.DataBean> imglist;
    private List<ImgData.DataBean> imagelist;
    private List<HotBean.DataBean.RowsBean> hotlist;
    private DemoApplication application;
    private RecyclerAdapter recyclerAdapter;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_sea);

        title = findViewById(R.id.oversea_title);
        StatusBar.makeStatusBarTransparent(this);


        initfvb();
        initView();
        initissue();
        init();
        EventBus.getDefault().register(this);
    }

    private void init(){
        if (FinalContents.getProjectType().equals("2")) {
            title.setText("海外房产");
            arrposid = "2";
            arrpos = "4";
            oversea_linear.setVisibility(View.GONE);
            nationRv.setVisibility(View.VISIBLE);
            initcity();
            inithot();
        }else if (FinalContents.getProjectType().equals("3")){
            title.setText("旅居房产");
            arrposid = "5";
            arrpos = "6";
            oversea_linear.setVisibility(View.VISIBLE);
            nationRv.setVisibility(View.GONE);
            initcity();
            inithot();
        }
    }

    private void initvoid(){
        FinalContents.setComprehensiveSorting("");
        FinalContents.setProjectLabel("");
        FinalContents.setNation("");
        FinalContents.setProjectPriceStart("");
        FinalContents.setProjectPriceEnd("");
        FinalContents.setApartment("");
        FinalContents.setAreaSection("");
        FinalContents.setFfProjectTrait("");
        FinalContents.setProcuctType("");
        FinalContents.setFitmentState("");
    }

    private void initfvb() {
        initvoid();

        application = (DemoApplication) getApplication();
        comprehensiveFragment = application.getComprehensiveFragment();
        countryFragment = application.getCountryFragment();
        priceFragment = application.getPriceFragment();
        houseTypeFragment = application.getHouseTypeFragment();
        screeningFragment = application.getScreeningFragment();

        nationlist = application.getNationlist();
        imagelist = application.getImagelist();
        imglist = application.getImglist();
        hotlist = application.getHotlist();

        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);
        checkBox5 = findViewById(R.id.checkbox5);
        checkBox6 = findViewById(R.id.checkbox6);
        checkBox7 = findViewById(R.id.checkbox7);

        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBox5.setOnClickListener(this);
        checkBox6.setOnClickListener(this);
        checkBox7.setOnClickListener(this);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        banner =findViewById(R.id.banner_2);

        nationRv = findViewById(R.id.overseas_nation);
        issueRv = findViewById(R.id.overseas_issue);
        hotRv = findViewById(R.id.overseas_hot);

        oversea_linear = findViewById(R.id.oversea_linear);

        tagView = findViewById(R.id.oversea_tagview);

        sort = findViewById(R.id.overseas_sort);
        screen = findViewById(R.id.overseas_screen);
        state = findViewById(R.id.overseas_state);
        price = findViewById(R.id.overseas_price);
        house_type = findViewById(R.id.overseas_house_type);

        oversea_fl = findViewById(R.id.oversea_fl);
        oversea_rb_1 = findViewById(R.id.oversea_rb_1);
        oversea_rb_2s = findViewById(R.id.oversea_rb_2s);
        oversea_rb_3 = findViewById(R.id.oversea_rb_3);
        oversea_rb_4 = findViewById(R.id.oversea_rb_4);
        oversea_rb_5 = findViewById(R.id.oversea_rb_5);
        oversea_rg = findViewById(R.id.oversea_rg);
        oversea_ll = findViewById(R.id.oversea_ll);
        oversea_lll = findViewById(R.id.oversea_lll);

        search = findViewById(R.id.oversea_search);
        search.setFocusable(false);
        back = findViewById(R.id.oversea_back);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverSeaActivity.this,SearchInterfaceActivity.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        oversea_fl.setOnClickListener(null);

        scrollView = findViewById(R.id.oversea_scroll);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.oversea_fl, comprehensiveFragment);
        transaction.commit();

        oversea_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oversea_ll.setVisibility(View.GONE);
            }
        });

        oversea_lll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oversea_ll.setVisibility(View.GONE);
            }
        });

        oversea_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                if (oversea_rb_1.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, comprehensiveFragment);
                } else if (oversea_rb_2s.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, countryFragment);
                } else if (oversea_rb_3.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, priceFragment);
                } else if (oversea_rb_4.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, houseTypeFragment);
                } else if (oversea_rb_5.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, screeningFragment);
                }
                transaction.commit();
            }
        });


        sort.setOnClickListener(this);
        screen.setOnClickListener(this);
        state.setOnClickListener(this);
        price.setOnClickListener(this);
        house_type.setOnClickListener(this);
    }


    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.overseas_sort:
                oversea_rb_1.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
            case R.id.overseas_screen:
                oversea_rb_5.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
            case R.id.overseas_state:
                oversea_rb_2s.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
            case R.id.overseas_price:
                oversea_rb_3.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
            case R.id.overseas_house_type:
                oversea_rb_4.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
            case R.id.checkbox1:
                if (checkBox1.isChecked()) {
                    projectLabel = "0";
                    FinalContents.setProjectLabel(projectLabel);
                    inithot();
                }else {
                    FinalContents.setProjectLabel("");
                    inithot();
                }
                break;
            case R.id.checkbox2:
                if (checkBox2.isChecked()) {
                    projectLabel = "1";
                    FinalContents.setProjectLabel(projectLabel);
                    inithot();
                }else {
                    FinalContents.setProjectLabel("");
                    inithot();
                }
                break;
            case R.id.checkbox3:
                if (checkBox3.isChecked()) {
                    projectLabel = "2";
                    FinalContents.setProjectLabel(projectLabel);
                    inithot();
                }else {
                    FinalContents.setProjectLabel("");
                    inithot();
                }
                break;
            case R.id.checkbox4:
                if (checkBox4.isChecked()) {
                    projectLabel = "3";
                    FinalContents.setProjectLabel(projectLabel);
                    inithot();
                }else {
                    FinalContents.setProjectLabel("");
                    inithot();
                }
                break;
            case R.id.checkbox5:
                if (checkBox5.isChecked()) {
                    projectLabel = "4";
                    FinalContents.setProjectLabel(projectLabel);
                    inithot();
                }else {
                    FinalContents.setProjectLabel("");
                    inithot();
                }
                break;
            case R.id.checkbox6:
                if (checkBox6.isChecked()) {
                    projectLabel = "5";
                    FinalContents.setProjectLabel(projectLabel);
                    inithot();
                }else {
                    FinalContents.setProjectLabel("");
                    inithot();
                }
                break;
            case R.id.checkbox7:
                if (checkBox7.isChecked()) {
                    projectLabel = "6";
                    FinalContents.setProjectLabel(projectLabel);
                    inithot();
                }else {
                    FinalContents.setProjectLabel("");
                    inithot();
                }
                break;
        }
    }


    private void initcity() {

        if (nationlist.size() == 0) {
            String url = nacationUrl + "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID() + "&projectType="+FinalContents.getProjectType();

            OkHttpPost okHttpPost = new OkHttpPost(url);
            String data = okHttpPost.post();
            Log.i("FZB", "城市新闻接收值:" + data);

            String substring = data.substring(9, 10);

            if (substring.equals("1")) {
                NationBean nationBean = new Gson().fromJson(data, NationBean.class);
                nationlist = nationBean.getData();
                if (FinalContents.getProjectType().equals("2")) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    nationRv.setLayoutManager(layoutManager);
                    OverseaCityAdapter recyclerAdapter = new OverseaCityAdapter(nationlist);
                    nationRv.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                }else if (FinalContents.getProjectType().equals("3")){
                    final List<String> cityName = new ArrayList<>();
                    for (int i = 0;i < nationlist.size();i++){
                        cityName.add(nationlist.get(i).getNationName());
                    }
                    tagView.setTheme(ColorFactory.RANDOM);
                    tagView.setTags(cityName);
                    tagView.setOnTagClickListener(new TagView.OnTagClickListener() {
                        @Override
                        public void onTagClick(int position, String text) {
                            // ...点击事件
                            Intent intent = new Intent(OverSeaActivity.this,RecyclerViewActivity.class);
                            intent.putExtra("nation",cityName.get(position));
                            startActivity(intent);
                        }
                        @Override
                        public void onTagLongClick(final int position, String text) {
                            // ...长按事件
                        }
                    });
                }
            } else {
                Toast.makeText(this, "没有获取到数据", Toast.LENGTH_SHORT).show();
            }
        }else {
            if (FinalContents.getProjectType().equals("2")) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                nationRv.setLayoutManager(layoutManager);
                OverseaCityAdapter recyclerAdapter = new OverseaCityAdapter(nationlist);
                nationRv.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }else if (FinalContents.getProjectType().equals("3")){
                final List<String> cityName = new ArrayList<>();
                for (int i = 0;i < nationlist.size();i++){
                    cityName.add(nationlist.get(i).getNationName());
                }
                tagView.setTheme(ColorFactory.RANDOM);
                tagView.setTags(cityName);
                tagView.setOnTagClickListener(new TagView.OnTagClickListener() {
                    @Override
                    public void onTagClick(int position, String text) {
                        // ...点击事件
                        Intent intent = new Intent(OverSeaActivity.this,RecyclerViewActivity.class);
                        intent.putExtra("nation",cityName.get(position));
                        startActivity(intent);
                    }
                    @Override
                    public void onTagLongClick(final int position, String text) {
                        // ...长按事件
                    }
                });
            }
        }


    }


    //首页轮播图
    private void initView() {
        list_img = new ArrayList<>();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ImgData> userMessage = fzbInterface.getBannerList(FinalContents.getUserID(),FinalContents.getCityID(),FinalContents.getProjectType(),arrposid);
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

                        for (int i = 0; i < imglist.size(); i++) {
                            list_img.add("http://39.98.173.250:8080" + imglist.get(i).getCoverImg());
                        }

                        banner.initBanner(list_img, false)//关闭3D画廊效果
                                .addPageMargin(10, 50)//参数1page之间的间距,参数2中间item距离边界的间距
                                .addPoint(6)//添加指示器
                                .addStartTimer(8)//自动轮播5秒间隔
                                .addPointBottom(7)
                                .addRoundCorners(12)//圆角
                                .finishConfig()//这句必须加
                                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
                                    @Override
                                    public void onBannerClick(int position) {
                                        //点击item
                                        Intent intent = new Intent(OverSeaActivity.this, WebViewActivity.class);
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

    private void initissue() {

        list_img = new ArrayList<>();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ImgData> userMessage = fzbInterface.getBannerList(FinalContents.getUserID(),FinalContents.getCityID(),FinalContents.getProjectType(),arrpos);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ImgData imgData) {
                        imagelist = imgData.getData();

                        LinearLayoutManager layoutManager = new LinearLayoutManager(OverSeaActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                        issueRv.setLayoutManager(layoutManager);
                        IssueAdapter recyclerAdapter = new IssueAdapter(imagelist);
                        issueRv.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
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

    @SuppressLint("WrongConstant")
    private void inithot() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> userMessage = fzbInterface.getList(FinalContents.getUserID(),FinalContents.getCityID(),FinalContents.getComprehensiveSorting(),FinalContents.getProjectLabel(),FinalContents.getProjectType(),FinalContents.getNation(),FinalContents.getProjectPriceStart(),FinalContents.getProjectPriceEnd(),FinalContents.getApartment(),FinalContents.getAreaSection(),FinalContents.getFfProjectTrait(),FinalContents.getProcuctType(),FinalContents.getFitmentState());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(HotBean hotBean) {
                        HotBean.DataBean hotBeanData = hotBean.getData();
                        hotlist = hotBeanData.getRows();

                        //在此处修改布局排列方向
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(OverSeaActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        hotRv.setLayoutManager(layoutManager);
                        recyclerAdapter = new RecyclerAdapter(hotlist);
                        hotRv.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
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


    @Subscribe(threadMode = ThreadMode.MAIN,priority = 100,sticky = false) //在ui线程执行，优先级为100
    public void onEvent(String url){
        Log.i("event","打印："+url);
        inithot();
        oversea_ll.setVisibility(View.GONE);
    }

}
