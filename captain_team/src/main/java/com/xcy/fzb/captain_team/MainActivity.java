package com.xcy.fzb.captain_team;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzb.captain_team.fragment.MeFragment;
import com.xcy.fzb.captain_team.fragment.MessageFragment;
import com.xcy.fzb.captain_team.fragment.MyClientFragment;
import com.xcy.fzb.captain_team.fragment.ProjectFragment;
import com.xcy.fzb.captain_team.view.ReportActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton button_home;
    private RadioButton button_message;
    private RadioButton button_backup;
    private RadioButton button_economics;
    private RadioButton button_me;
    private ImageView img_backup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initfvb();
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
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
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
                MyClientFragment myClientFragment = new MyClientFragment();
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
