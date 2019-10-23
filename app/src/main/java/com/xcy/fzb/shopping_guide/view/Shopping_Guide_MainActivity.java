package com.xcy.fzb.shopping_guide.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
                TaskFragment taskFragment = new TaskFragment();
                transaction.replace(R.id.main_framelayout,taskFragment);
                break;
            case R.id.main_project:
                ProjectFragment projectFragment = new ProjectFragment();
                transaction.replace(R.id.main_framelayout,projectFragment);
                break;
            case R.id.main_message:
                MessageFragment messageFragment = new MessageFragment();
                transaction.replace(R.id.main_framelayout,messageFragment);
                break;
            case R.id.main_me:
                MeFragment meFragment = new MeFragment();
                transaction.replace(R.id.main_framelayout,meFragment);
                break;
        }
        transaction.commit();
    }
}
