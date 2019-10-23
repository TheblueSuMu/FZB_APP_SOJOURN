package com.xcy.fzb.all.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.PhotoBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
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

public class BannerPhotoActivity extends AllActivity {

    List<String> list;
    Banner banner;
    TextView photo_message;
    TabLayout banner_photo_tab_layout;
    RelativeLayout banner_photo_img;
    private List<PhotoBean.DataBean.DataListBean> dataList;
    private List<String> message;
    private List<String> name;
    private List<String> ifname;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_photo);
        StatusBar.makeStatusBarTransparent(this);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        banner = findViewById(R.id.mbanner);
        photo_message = findViewById(R.id.photo_message);
        banner_photo_tab_layout = findViewById(R.id.banner_photo_tab_layout);
        banner_photo_img = findViewById(R.id.banner_photo_img);

        initData();

    }
    private void initData() {

        list = new ArrayList<>();
        message = new ArrayList<>();
        name = new ArrayList<>();
        ifname = new ArrayList<>();


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<PhotoBean> photoBean = fzbInterface.getPhotoBean(FinalContents.getUserID(), FinalContents.getProjectID());
        photoBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhotoBean photoBean) {
                        List<PhotoBean.DataBean> data1 = photoBean.getData();
                        for (int i = 0; i < data1.size(); ++i) {
                            dataList = data1.get(i).getDataList();
                            for (int j = 0; j < dataList.size(); ++j) {
                                ifname.add(data1.get(i).getTypeName());
                                sum++;
                                String imgPath = dataList.get(j).getImgPath();
//                                Log.i("MyCL", "图片地址：" + imgPath);
                                list.add("http://39.98.173.250:8080" + imgPath);
                                message.add(dataList.get(j).getRemarks() + "");

                            }
                            String typeName = data1.get(i).getTypeName();
                            name.add(typeName);
//                            Log.i("MyCL", "项目名称" + typeName);
                        }
//                        for (int l = 0; l < ifname.size(); ++l) {
//                            Log.i("MyCL", "项目名称" + ifname.get(l));
//                        }
                        initView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "BannerPhotoActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {



        banner_photo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for (int j = 0; j < name.size(); ++j) {
            banner_photo_tab_layout.addTab(banner_photo_tab_layout.newTab().setText(name.get(j)));
        }

        for (int i = 0; i < list.size(); ++i) {
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
        }
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.isAutoPlay(false);
        banner.setImages(list);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                finish();
            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < name.size(); ++i) {
                    if (name.get(i).equals(ifname.get(position))) {
                        banner_photo_tab_layout.getTabAt(i).select();
                    }
                }
                photo_message.setText((position + 1) + "/" + sum + "    " + message.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        banner.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }


}
