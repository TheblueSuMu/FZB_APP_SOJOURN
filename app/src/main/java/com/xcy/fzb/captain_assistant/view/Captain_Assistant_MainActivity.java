package com.xcy.fzb.captain_assistant.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RadioButton;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.androidprogresslayout.ProgressLayout;
import com.xcy.fzb.R;
import com.xcy.fzb.all.fragment.MessageFragment;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.captain_assistant.fragment.AssistantHomeFragment;
import com.xcy.fzb.captain_assistant.fragment.AssistantMeFragment;
import com.xcy.fzb.captain_assistant.fragment.TeamFragment;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.internal.CustomAdapt;

public class Captain_Assistant_MainActivity extends AllActivity implements View.OnClickListener, CustomAdapt,AssistantHomeFragment.FragmentInteraction,AssistantMeFragment.FragmentInteraction{

    private RadioButton home;
    private RadioButton project;
    private RadioButton message;
    private RadioButton me;
    private ProgressLayout progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoSizeConfig.getInstance()
                .getExternalAdaptManager()
                .addCancelAdaptOfActivity(Captain_Assistant_MainActivity.class);
        setContentView(R.layout.activity_captain__assistant__main);

        initfvb();

    }

    private void initfvb() {

        StatusBar.makeStatusBarTransparent(this);

        home = findViewById(R.id.team_main_home);
        project = findViewById(R.id.team_main_project);
        message = findViewById(R.id.team_main_message);
        me = findViewById(R.id.team_main_me);

        home.setOnClickListener(this);
        project.setOnClickListener(this);
        message.setOnClickListener(this);
        me.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        AssistantHomeFragment projectFragment = new AssistantHomeFragment();
        transaction.add(R.id.team_main_framelayout,projectFragment);
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
                transaction.replace(R.id.team_main_framelayout,messageFragment);
                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("2")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("3");
                transaction.replace(R.id.team_main_framelayout,messageFragment);
                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("5")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("4");
                transaction.replace(R.id.team_main_framelayout,messageFragment);
                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("63")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                TeamFragment teamFragment = new TeamFragment();
                transaction.replace(R.id.team_main_framelayout, teamFragment);
                transaction.commit();
                project.setChecked(true);
            }
        }
    }

    @Override
    public void onClick(View view) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.team_main_home:
                initProgressLayout();
                AssistantHomeFragment projectFragment = new AssistantHomeFragment();
                transaction.replace(R.id.team_main_framelayout, projectFragment);
                break;
            case R.id.team_main_project:
                initProgressLayout();
                TeamFragment teamFragment = new TeamFragment();
                transaction.replace(R.id.team_main_framelayout, teamFragment);
                break;
            case R.id.team_main_message:
                initProgressLayout();
                MessageFragment messageFragment = new MessageFragment();
                transaction.replace(R.id.team_main_framelayout, messageFragment);
                break;
            case R.id.team_main_me:
                initProgressLayout();
                AssistantMeFragment meFragment = new AssistantMeFragment();
                transaction.replace(R.id.team_main_framelayout, meFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 667;
    }

    private void initProgressLayout(){
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