package com.xcy.fzb.captain_team.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.MyClientFragment2;
import com.xcy.fzb.all.fragment.MyClientFragment3;
import com.xcy.fzb.all.fragment.MyClientFragment4;
import com.xcy.fzb.all.fragment.MyClientFragment5;
import com.xcy.fzb.all.fragment.MyClientFragment6;
import com.xcy.fzb.all.fragment.MyClientFragment7;
import com.xcy.fzb.all.persente.MyClientName;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.all.view.MyClientAddActivity;
import com.xcy.fzb.captain_team.fragment.Captain_Team_MyClientFragment1;

import org.greenrobot.eventbus.EventBus;

public class Captain_Team_MyClientActivity extends AllActivity implements View.OnClickListener {

    LinearLayout my_client_return;
    ImageView client_add;

    EditText my_client_ss;

    TextView my_client_tv;

    LinearLayout my_client_ll_1;
    LinearLayout my_client_11_2;
    LinearLayout my_client_11_3;
    LinearLayout my_client_11_4;
    LinearLayout my_client_11_5;
    LinearLayout my_client_11_6;
    LinearLayout my_client_11_7;
    LinearLayout my_client_11_8;
    LinearLayout my_client_11_9;
    LinearLayout my_client_11_10;
    LinearLayout my_client_11_11;
    LinearLayout my_client_11_12;
    LinearLayout my_client_11_13;
    LinearLayout my_client_11_14;

    FragmentManager manager;
    FragmentTransaction transaction;

    Captain_Team_MyClientFragment1 myClientFragment1 = new Captain_Team_MyClientFragment1();
    MyClientFragment2 myClientFragment2 = new MyClientFragment2();
    MyClientFragment3 myClientFragment3 = new MyClientFragment3();
    MyClientFragment4 myClientFragment4 = new MyClientFragment4();
    MyClientFragment5 myClientFragment5 = new MyClientFragment5();
    MyClientFragment6 myClientFragment6 = new MyClientFragment6();
    MyClientFragment7 myClientFragment7 = new MyClientFragment7();
    private String client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_client);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
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

    private void initView() {
        client = getIntent().getStringExtra("client");
        StatusBar.makeStatusBarTransparent(this);



        my_client_return = findViewById(R.id.my_client_return);
        client_add = findViewById(R.id.client_add);
        my_client_ss = findViewById(R.id.my_client_ss);
        my_client_tv = findViewById(R.id.my_client_tv);
        my_client_ll_1 = findViewById(R.id.my_client_ll_1);
        my_client_11_2 = findViewById(R.id.my_client_ll_2);
        my_client_11_3 = findViewById(R.id.my_client_ll_3);
        my_client_11_4 = findViewById(R.id.my_client_ll_4);
        my_client_11_5 = findViewById(R.id.my_client_ll_5);
        my_client_11_6 = findViewById(R.id.my_client_ll_6);
        my_client_11_7 = findViewById(R.id.my_client_ll_7);
        my_client_11_8 = findViewById(R.id.my_client_ll_8);
        my_client_11_9 = findViewById(R.id.my_client_ll_9);
        my_client_11_10 = findViewById(R.id.my_client_ll_10);
        my_client_11_11 = findViewById(R.id.my_client_ll_11);
        my_client_11_12 = findViewById(R.id.my_client_ll_12);
        my_client_11_13 = findViewById(R.id.my_client_ll_13);
        my_client_11_14 = findViewById(R.id.my_client_ll_14);

        if (FinalContents.getIdentity().equals("63")) {
            my_client_ll_1.setVisibility(View.GONE);
            client_add.setVisibility(View.GONE);
        }

        my_client_return.setOnClickListener(this);
        client_add.setOnClickListener(this);
        my_client_ll_1.setOnClickListener(this);
        my_client_11_2.setOnClickListener(this);
        my_client_11_3.setOnClickListener(this);
        my_client_11_4.setOnClickListener(this);
        my_client_11_5.setOnClickListener(this);
        my_client_11_6.setOnClickListener(this);
        my_client_11_7.setOnClickListener(this);

        initClient();

        my_client_ss.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    String s = my_client_ss.getText().toString();
                    EventBus.getDefault().post(new MyClientName(s));
                    return true;
                }
                return false;
            }

        });

    }

    private void initClient(){
        if (client.equals("0")) {
            my_client_11_8.setVisibility(View.VISIBLE);
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            myClientFragment1 = new Captain_Team_MyClientFragment1();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment1);
            transaction.commit();
        } else if (client.equals("1")) {
            my_client_11_8.setVisibility(View.INVISIBLE);
            my_client_11_9.setVisibility(View.VISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment2);
            transaction.commit();
        }else if (client.equals("2")) {
            my_client_11_8.setVisibility(View.INVISIBLE);
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.VISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            myClientFragment3 = new MyClientFragment3();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment3);
            transaction.commit();
        }else if (client.equals("3")) {
            my_client_11_8.setVisibility(View.INVISIBLE);
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.VISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            myClientFragment4 = new MyClientFragment4();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment4);
            transaction.commit();
        }else if (client.equals("4")) {
            my_client_11_8.setVisibility(View.INVISIBLE);
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.VISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            myClientFragment5 = new MyClientFragment5();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment5);
            transaction.commit();
        }else if (client.equals("5")) {
            my_client_11_8.setVisibility(View.INVISIBLE);
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.VISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            myClientFragment6 = new MyClientFragment6();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment6);
            transaction.commit();
        }else if (client.equals("6")) {
            my_client_11_8.setVisibility(View.INVISIBLE);
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.VISIBLE);
            myClientFragment7 = new MyClientFragment7();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment7);
            transaction.commit();
        }
    }


    @Override
    public void onClick(View view) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.my_client_return:
                finish();
                break;
            case R.id.client_add:
                Intent intent = new Intent(Captain_Team_MyClientActivity.this, MyClientAddActivity.class);
                startActivity(intent);
                break;
            case R.id.my_client_ll_1:
                my_client_tv.setVisibility(View.GONE);
                my_client_ss.setVisibility(View.VISIBLE);

                my_client_11_8.setVisibility(View.VISIBLE);
                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment1);
                break;
            case R.id.my_client_ll_2:
                my_client_tv.setVisibility(View.VISIBLE);
                my_client_ss.setVisibility(View.GONE);

                my_client_11_8.setVisibility(View.INVISIBLE);
                my_client_11_9.setVisibility(View.VISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment2);
                break;
            case R.id.my_client_ll_3:
                my_client_tv.setVisibility(View.VISIBLE);
                my_client_ss.setVisibility(View.GONE);

                my_client_11_8.setVisibility(View.INVISIBLE);
                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.VISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment3);
                break;
            case R.id.my_client_ll_4:
                my_client_tv.setVisibility(View.VISIBLE);
                my_client_ss.setVisibility(View.GONE);

                my_client_11_8.setVisibility(View.INVISIBLE);
                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.VISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment4);
                break;
            case R.id.my_client_ll_5:
                my_client_tv.setVisibility(View.VISIBLE);
                my_client_ss.setVisibility(View.GONE);

                my_client_11_8.setVisibility(View.INVISIBLE);
                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.VISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment5);
                break;
            case R.id.my_client_ll_6:
                my_client_tv.setVisibility(View.VISIBLE);
                my_client_ss.setVisibility(View.GONE);

                my_client_11_8.setVisibility(View.INVISIBLE);
                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.VISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment6);
                break;
            case R.id.my_client_ll_7:
                my_client_tv.setVisibility(View.VISIBLE);
                my_client_ss.setVisibility(View.GONE);

                my_client_11_8.setVisibility(View.INVISIBLE);
                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.VISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment7);
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("返回的信息","数据："+FinalContents.getChengJao());
        Log.i("返回的信息","数据2："+FinalContents.getTiaozhuang());

        if (FinalContents.getTiaozhuang().equals("登岛成功")) {
            myClientFragment4 = new MyClientFragment4();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment4);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.VISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("到访成功")) {
            myClientFragment3 = new MyClientFragment3();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment3);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.VISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("认筹成功")) {
            myClientFragment5 = new MyClientFragment5();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment5);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.VISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("失效成功")) {
            myClientFragment7 = new MyClientFragment7();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment7);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.VISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("成交")){
            Log.i("成交123","跳转到成交页");
            myClientFragment6 = new MyClientFragment6();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment6);
            transaction.commit();
            Log.i("成交123","跳转到成交");

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.VISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("调单")){
            myClientFragment6 = new MyClientFragment6();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment6);
            transaction.commit();
            Log.i("成交123","跳转到调单");

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.VISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
