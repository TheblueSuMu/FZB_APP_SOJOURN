package com.xcy.fzb.broker.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.androidprogresslayout.ProgressLayout;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.HomeFragment;
import com.xcy.fzb.all.fragment.MessageFragment;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.utils.VirturlUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.all.view.ReportActivity;
import com.xcy.fzb.broker.fragment.DFragment;
import com.xcy.fzb.broker.fragment.EFragment;

public class Broker_MainActivity extends AllActivity implements View.OnClickListener, HomeFragment.FragmentInteraction {

    private RadioButton button_home;
    private RadioButton button_message;
    private RadioButton button_backup;
    private RadioButton button_economics;
    private RadioButton button_me;
    private ImageView img_backup;
    private ProgressLayout progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broker_activity_main);
        StatusBar.makeStatusBarTransparent(this);
        FinalContents.setZhuanAn("0");
        init();
        initfvb();
    }

    private void init() {
        VirturlUtil.assistActivity(findViewById(android.R.id.content));

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HomeFragment home_fragment = new HomeFragment();

        MessageFragment message_fragment = new MessageFragment();
        DFragment dFragment = new DFragment();
        EFragment eFragment = new EFragment();

        transaction.replace(R.id.main_framelayout, home_fragment);


        transaction.commit();
    }

    // 3.2 +实现接口，实现回调
    @Override
    public void process(String str) {
        if (str != null) {
            if (str.equals("0")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("2");
                transaction.replace(R.id.main_framelayout, messageFragment);
                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("2")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("3");
                transaction.replace(R.id.main_framelayout, messageFragment);
                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("5")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("4");
                transaction.replace(R.id.main_framelayout, messageFragment);
                transaction.commit();
                button_message.setChecked(true);
            }
        }
    }

    private void initfvb() {
        button_home = findViewById(R.id.button_home);
        button_message = findViewById(R.id.button_message);
        button_backup = findViewById(R.id.button_backup);
        button_economics = findViewById(R.id.button_economics);
        button_me = findViewById(R.id.button_me);
        img_backup = findViewById(R.id.img_backup);
        img_backup.setImageResource(R.mipmap.z13);
        img_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Broker_MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        click();
    }

    public void click() {
        button_home.setOnClickListener(this);
        button_message.setOnClickListener(this);
        button_backup.setOnClickListener(this);
        button_economics.setOnClickListener(this);
        button_me.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.button_home:
                initProgressLayout();
                HomeFragment home_fragment = new HomeFragment();
                transaction.replace(R.id.main_framelayout, home_fragment);
                break;
            case R.id.button_message:
                initProgressLayout();
                MessageFragment message_fragment = new MessageFragment();
                transaction.replace(R.id.main_framelayout, message_fragment);


                break;
            case R.id.button_backup:
                Intent intent = new Intent(Broker_MainActivity.this, ReportActivity.class);
                startActivity(intent);
                break;
            case R.id.button_economics:
                initProgressLayout();
                DFragment dFragment = new DFragment();
                transaction.replace(R.id.main_framelayout, dFragment);


                break;
            case R.id.button_me:
                initProgressLayout();
                EFragment eFragment = new EFragment();
                transaction.replace(R.id.main_framelayout, eFragment);


                break;
        }
        initSeleep();
        transaction.commit();
    }

    private void initSeleep() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initProgressLayout() {
        progressLayout = findViewById(R.id.progress_layout);

        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 切换回正常显示页面
                progressLayout.showContent();
            }
        };

        // 开始加载... 假设从这里开始一个耗时的操作将开始启动，在此启动过程中，开发者希望用户稍事休息，等待。。。
        progressLayout.showProgress();

        // 假设有一个耗时的加载业务逻辑，需要5秒完成。
        handler.sendEmptyMessageDelayed(0, 300);

    }
}
