package com.xcy.fzb.shopping_guide.view;

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
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.shopping_guide.fragment.MeFragment;
import com.xcy.fzb.shopping_guide.fragment.MessageFragment;
import com.xcy.fzb.shopping_guide.fragment.ProjectFragment;
import com.xcy.fzb.shopping_guide.fragment.TaskFragment;

public class Shopping_Guide_MainActivity extends AllActivity implements View.OnClickListener, ProjectFragment.FragmentInteraction {
    private RadioButton task;
    private RadioButton project;
    private RadioButton message;
    private RadioButton me;
    private ProgressLayout progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_guide_activity_main);

        StatusBar.makeStatusBarTransparent(this);

        initfvb();
    }

    // 3.2 +实现接口，实现回调
    @Override
    public void process(String str) {
        if (str != null) {
            if (str.equals("660")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                TaskFragment taskFragment = new TaskFragment();
                transaction.replace(R.id.main_framelayout,taskFragment);
                transaction.commit();
                task.setChecked(true);
            } else if (str.equals("0")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("2");
                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("2")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("3");
                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("5")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("4");
                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.commit();
                message.setChecked(true);
            }
        }
    }

    private void initfvb(){
        task = findViewById(R.id.main_task);
        project = findViewById(R.id.main_project);
        message = findViewById(R.id.main_message);
        me = findViewById(R.id.main_me);

        task.setOnClickListener(this);
        project.setOnClickListener(this);
        message.setOnClickListener(this);
        me.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ProjectFragment projectFragment = new ProjectFragment();
        transaction.add(R.id.main_framelayout,projectFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.main_task:
                initProgressLayout();
                TaskFragment taskFragment = new TaskFragment();
                transaction.replace(R.id.main_framelayout,taskFragment);
                break;
            case R.id.main_project:
                initProgressLayout();
                ProjectFragment projectFragment = new ProjectFragment();
                transaction.replace(R.id.main_framelayout,projectFragment);
                break;
            case R.id.main_message:
                initProgressLayout();
                MessageFragment messageFragment = new MessageFragment();
                transaction.replace(R.id.main_framelayout,messageFragment);
                break;
            case R.id.main_me:
                initProgressLayout();
                MeFragment meFragment = new MeFragment();
                transaction.replace(R.id.main_framelayout,meFragment);
                break;
        }
        transaction.commit();
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
