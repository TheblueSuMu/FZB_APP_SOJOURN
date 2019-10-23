package com.xcy.fzb.captain_assistant.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzb.R;
import com.xcy.fzb.all.fragment.MessageFragment;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.captain_assistant.fragment.AssistantHomeFragment;
import com.xcy.fzb.captain_assistant.fragment.AssistantMeFragment;
import com.xcy.fzb.captain_assistant.fragment.TeamFragment;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.internal.CustomAdapt;

public class Captain_Assistant_MainActivity extends AllActivity implements View.OnClickListener, CustomAdapt,AssistantHomeFragment.FragmentInteraction{

    private RadioButton home;
    private RadioButton project;
    private RadioButton message;
    private RadioButton me;

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
            }
        }
    }

    @Override
    public void onClick(View view) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.team_main_home:
                AssistantHomeFragment projectFragment = new AssistantHomeFragment();
                transaction.replace(R.id.team_main_framelayout, projectFragment);
                break;
            case R.id.team_main_project:
                TeamFragment teamFragment = new TeamFragment();
                transaction.replace(R.id.team_main_framelayout, teamFragment);
                break;
            case R.id.team_main_message:
                MessageFragment messageFragment = new MessageFragment();
                transaction.replace(R.id.team_main_framelayout, messageFragment);
                break;
            case R.id.team_main_me:
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
}
//fragment_assistant__home_