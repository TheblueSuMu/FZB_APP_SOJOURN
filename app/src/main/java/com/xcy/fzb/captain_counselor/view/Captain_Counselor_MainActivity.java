package com.xcy.fzb.captain_counselor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.MessageFragment;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.all.view.ReportActivity;
import com.xcy.fzb.captain_counselor.fragment.MeFragment;
import com.xcy.fzb.captain_team.fragment.Captain_Team_MyClientFragment;
import com.xcy.fzb.captain_team.fragment.ProjectFragment;

public class Captain_Counselor_MainActivity extends AllActivity implements View.OnClickListener,ProjectFragment.FragmentInteraction {
    private RadioButton button_home;
    private RadioButton button_message;
    private RadioButton button_backup;
    private RadioButton button_economics;
    private RadioButton button_me;
    private ImageView img_backup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.captain_counselor_activity_main);
        FinalContents.setDengLu("顾问");
        StatusBar.makeStatusBarTransparent(this);
        FinalContents.setZhuanAn("0");
        FinalContents.setQuanceng("1");
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
                button_message.setChecked(true);
            } else if (str.equals("2")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("3");
                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("5")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("4");
                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.commit();
                button_message.setChecked(true);
            }
        }
    }

    private void initfvb(){
        button_home = findViewById(R.id.button_home);
        button_message = findViewById(R.id.button_message);
        button_backup = findViewById(R.id.button_backup);
        button_economics = findViewById(R.id.button_economics);
        button_me = findViewById(R.id.button_me);
        img_backup = findViewById(R.id.img_backup);
        img_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Captain_Counselor_MainActivity.this, ReportActivity.class);
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
            case R.id.button_economics:
                FinalContents.setMySelf("1");
                FinalContents.setQuanceng("1");
                FinalContents.setAgentId(FinalContents.getUserID());
                Captain_Team_MyClientFragment myClientFragment = new Captain_Team_MyClientFragment();
                transaction.replace(R.id.main_framelayout,myClientFragment);
                break;
            case R.id.button_home:
                ProjectFragment projectFragment = new ProjectFragment();
                transaction.replace(R.id.main_framelayout,projectFragment);
                break;
            case R.id.button_message:
                MessageFragment messageFragment = new MessageFragment();
                transaction.replace(R.id.main_framelayout,messageFragment);
                break;
            case R.id.button_me:
                MeFragment meFragment = new MeFragment();
                transaction.replace(R.id.main_framelayout,meFragment);
                break;
        }
        transaction.commit();
    }
}
