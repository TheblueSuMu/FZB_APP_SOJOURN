package com.xcy.fzb.broker.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.androidprogresslayout.ProgressLayout;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.HomeFragment;
import com.xcy.fzb.all.fragment.MessageFragment;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.utils.CommonUtil;
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

    FragmentManager manager;
    FragmentTransaction transaction;
    private HomeFragment home_fragment = new HomeFragment();
    private MessageFragment message_fragment = new MessageFragment();
    private DFragment dFragment = new DFragment();
    private EFragment eFragment = new EFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broker_activity_main);
        StatusBar.makeStatusBarTransparent(this);
        FinalContents.setZhuanAn("0");
        init();
        initfvb();

    }

    private void init_No_Network() {
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

    private void init() {
        VirturlUtil.assistActivity(findViewById(android.R.id.content));
        init_No_Network();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.main_framelayout, home_fragment);
        transaction.add(R.id.main_framelayout, message_fragment);
        transaction.add(R.id.main_framelayout, dFragment);
        transaction.add(R.id.main_framelayout, eFragment);
        transaction.show(home_fragment);
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
                transaction.add(R.id.main_framelayout, messageFragment);
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
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.button_home:
                init_No_Network();
                transaction.show(home_fragment);
                transaction.hide(message_fragment);
                transaction.hide(dFragment);
                transaction.hide(eFragment);
                break;
            case R.id.button_message:
                init_No_Network();
                transaction.show(message_fragment);
                transaction.hide(home_fragment);
                transaction.hide(dFragment);
                transaction.hide(eFragment);
                break;
            case R.id.button_backup:
                Intent intent = new Intent(Broker_MainActivity.this, ReportActivity.class);
                startActivity(intent);
                break;
            case R.id.button_economics:
                init_No_Network();
                transaction.show(dFragment);
                transaction.hide(message_fragment);
                transaction.hide(home_fragment);
                transaction.hide(eFragment);
                break;
            case R.id.button_me:
                init_No_Network();
                transaction.show(eFragment);
                transaction.hide(message_fragment);
                transaction.hide(dFragment);
                transaction.hide(home_fragment);
                break;
        }
        transaction.commit();
    }


}
