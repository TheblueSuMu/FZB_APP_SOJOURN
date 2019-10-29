package com.xcy.fzb.shopping_guide.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.androidprogresslayout.ProgressLayout;
import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.utils.CommonUtil;
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

    ProjectFragment projectFragment = new ProjectFragment();
    TaskFragment taskFragment = new TaskFragment();
    MessageFragment messageFragment = new MessageFragment();
    MeFragment meFragment = new MeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_guide_activity_main);

        StatusBar.makeStatusBarTransparent(this);
        initfvb();

    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {

        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            Toast.makeText(this, "当前无网络，请检查网络后再进行登录", Toast.LENGTH_SHORT).show();
        }
    }

    // 3.2 +实现接口，实现回调
    @Override
    public void process(String str) {
        if (str != null) {
            if (str.equals("660")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                TaskFragment taskFragment = new TaskFragment();
//                transaction.replace(R.id.main_framelayout,taskFragment);

                transaction.hide(projectFragment);
                transaction.show(taskFragment);
                transaction.hide(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                task.setChecked(true);
            } else if (str.equals("0")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("2");
//                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.hide(projectFragment);
                transaction.hide(taskFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("2")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("3");
//                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.hide(projectFragment);
                transaction.hide(taskFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("5")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("4");
//                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.hide(projectFragment);
                transaction.hide(taskFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                message.setChecked(true);
            }
        }
    }

    private void initfvb(){
        init_No_Network();
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

        transaction.add(R.id.main_framelayout,projectFragment);
        transaction.add(R.id.main_framelayout,taskFragment);
        transaction.add(R.id.main_framelayout,messageFragment);
        transaction.add(R.id.main_framelayout,meFragment);

        transaction.show(projectFragment);
        transaction.hide(taskFragment);
        transaction.hide(messageFragment);
        transaction.hide(meFragment);

        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.main_task:
                init_No_Network();

//                transaction.replace(R.id.main_framelayout,taskFragment);
                transaction.hide(projectFragment);
                transaction.show(taskFragment);
                transaction.hide(messageFragment);
                transaction.hide(meFragment);
                break;
            case R.id.main_project:
                init_No_Network();
                transaction.show(projectFragment);
                transaction.hide(taskFragment);
                transaction.hide(messageFragment);
                transaction.hide(meFragment);
//                transaction.replace(R.id.main_framelayout,projectFragment);
                break;
            case R.id.main_message:
                init_No_Network();
                transaction.hide(projectFragment);
                transaction.hide(taskFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);
//                transaction.replace(R.id.main_framelayout,messageFragment);
                break;
            case R.id.main_me:
                init_No_Network();
                transaction.hide(projectFragment);
                transaction.hide(taskFragment);
                transaction.hide(messageFragment);
                transaction.show(meFragment);
//                transaction.replace(R.id.main_framelayout,meFragment);
                break;
        }
        transaction.commit();
    }


}
