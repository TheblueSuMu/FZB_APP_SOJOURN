package com.xcy.fzb.shopping_guide.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.PhotoBean;
import com.xcy.fzb.shopping_guide.persente.OkHttpPost;
import com.xcy.fzb.shopping_guide.persente.StatusBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerPhotoActivity extends AppCompatActivity {

    List<String> list;
    Banner banner;
    TextView photo_message;
    TabLayout banner_photo_tab_layout;
    ImageView banner_photo_img;
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

        banner = findViewById(R.id.mbanner);
        photo_message = findViewById(R.id.photo_message);
        banner_photo_tab_layout = findViewById(R.id.banner_photo_tab_layout);
        banner_photo_img = findViewById(R.id.banner_photo_img);

        initData();

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

    private void initData() {

        list = new ArrayList<>();
        message = new ArrayList<>();
        name = new ArrayList<>();
        ifname = new ArrayList<>();

        String url = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectPhoto?" + "userId=" + FinalContents.getUserID() + "&projectId=" + FinalContents.getProjectID();

        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();

        Log.i("MyCL", "图片数据：" + data);

        Gson gson = new Gson();
        PhotoBean photoBean = gson.fromJson(data, PhotoBean.class);
        List<PhotoBean.DataBean> data1 = photoBean.getData();

        for (int i = 0; i < data1.size(); ++i) {
            dataList = data1.get(i).getDataList();
            for (int j = 0; j < dataList.size(); ++j) {
                ifname.add(data1.get(i).getTypeName());
                sum++;
                String imgPath = dataList.get(j).getImgPath();
                Log.i("MyCL", "图片地址：" + imgPath);
                list.add("http://39.98.173.250:8080" + imgPath);
                message.add(dataList.get(j).getRemarks() + "");

            }
            String typeName = data1.get(i).getTypeName();
            name.add(typeName);
            Log.i("MyCL", "项目名称" + typeName);
        }
        for (int l = 0; l < ifname.size(); ++l) {
            Log.i("MyCL", "项目名称" + ifname.get(l));
        }
        initView();
    }
}
