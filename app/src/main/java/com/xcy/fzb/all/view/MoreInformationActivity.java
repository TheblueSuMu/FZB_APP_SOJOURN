package com.xcy.fzb.all.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.MoreInformationFragment;
import com.xcy.fzb.all.fragment.MoreProjectFragment;
import com.xcy.fzb.all.fragment.MoreTypeFragment;
import com.xcy.fzb.all.modle.MoreBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.shopping_guide.adapter.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoreInformationActivity extends AllActivity implements View.OnClickListener {
    private LinearLayout more_img;

    FragmentManager manager;
    FragmentTransaction transaction;

    MoreInformationFragment moreInformationFragment = new MoreInformationFragment();
    MoreTypeFragment moreTypeFragment= new MoreTypeFragment();
    MoreProjectFragment moreProjectFragment = new MoreProjectFragment();
    private Button more_report;
    private Button more_call;
    private List<Fragment> mFragments;
    private String[] mTitles = new String[]{
            "基本信息", "产品类型", "项目规划"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);


        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);
        more_img = findViewById(R.id.more_img);
        more_report = findViewById(R.id.more_report);
        more_call = findViewById(R.id.more_call);

        initData();

        more_img.setOnClickListener(this);

        more_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + FinalContents.getIPhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });

        more_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setChecked2(true);
                Intent intent = new Intent(MoreInformationActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MoreBean> clientFragmentBean = fzbInterface.getProjectMoreInfo(FinalContents.getProjectID());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoreBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoreBean moreBean) {
                        List<MoreBean.DataBean> list = moreBean.getData();
                        if (list.size() == 0) {
                        }else {
                            moreInformationFragment = new MoreInformationFragment(list);
                            moreTypeFragment = new MoreTypeFragment(list);
                            moreProjectFragment = new MoreProjectFragment(list);
                            setupViewPager();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("更多信息", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_img:
                finish();
                break;
        }
    }


    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.more_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.more_tablayout);
        tabLayout.setSelectedTabIndicator(R.drawable.tablayout_select);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments = new ArrayList<>();
        mFragments.add(moreInformationFragment);
        mFragments.add(moreTypeFragment);
        mFragments.add(moreProjectFragment);
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(adapter);
    }
}
