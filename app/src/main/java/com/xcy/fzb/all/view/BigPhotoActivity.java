package com.xcy.fzb.all.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BigPhotoActivity extends AllActivity {

    ImageView big_photo_img;
    Banner banner;
    List<String> listImage;
    TextView big_tv;
    int j = 0;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_photo);

        initView();
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        listImage = new ArrayList<>();

        big_photo_img = findViewById(R.id.big_photo_img);
        banner = findViewById(R.id.big_banner);
        big_tv = findViewById(R.id.big_tv);

        String bigPhotoimg = getIntent().getStringExtra("bigPhotoimg");


        String[] a  = bigPhotoimg.split("[|]");
        for (int i = 0; i < a.length; i++){
            listImage.add("http://39.98.173.250:8080"+a[i]);
        }


        for (int s = 0; s < listImage.size(); ++s) {
            Log.i("MyCL", "图片地址：" + listImage.get(s).toString());
        }

        for (int i = 0; i < listImage.size(); ++i) {
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
        }
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.isAutoPlay(false);
        banner.setImages(listImage);
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
                big_tv.setText((position + 1) + "/" + listImage.size());
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