package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_side.fragment.MeFragment;
import com.xcy.fzb.project_side.fragment.MessageFragment;
import com.xcy.fzb.project_side.fragment.ProjectFragment;
import com.xcy.fzb.project_side.fragment.Project_Side_HomeFragment;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.internal.CustomAdapt;

public class Project_Side_MainActivity extends AllActivity implements CustomAdapt,View.OnClickListener,ProjectFragment.FragmentInteraction {
    private RadioButton home;
    private RadioButton project;
    private RadioButton message;
    private RadioButton me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoSizeConfig.getInstance()
                .getExternalAdaptManager()
                .addCancelAdaptOfActivity(Project_Side_MainActivity.class);
        setContentView(R.layout.project_side_activity_main);

        FinalContents.setZhuanAn("1");
        initfvb();
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
        home = findViewById(R.id.main_home);
        project = findViewById(R.id.main_project);
        message = findViewById(R.id.main_message);
        me = findViewById(R.id.main_me);

        home.setOnClickListener(this);
        project.setOnClickListener(this);
        message.setOnClickListener(this);
        me.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Project_Side_HomeFragment homeFragment = new Project_Side_HomeFragment();
        transaction.add(R.id.main_framelayout,homeFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.main_home:
                Project_Side_HomeFragment homeFragment = new Project_Side_HomeFragment();
                transaction.replace(R.id.main_framelayout,homeFragment);
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

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 667;
    }
}
