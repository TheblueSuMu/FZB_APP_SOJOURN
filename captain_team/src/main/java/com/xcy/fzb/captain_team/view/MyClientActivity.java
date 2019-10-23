package com.xcy.fzb.captain_team.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.fragment.MyClientFragment1;
import com.xcy.fzb.captain_team.fragment.MyClientFragment2;
import com.xcy.fzb.captain_team.fragment.MyClientFragment3;
import com.xcy.fzb.captain_team.fragment.MyClientFragment4;
import com.xcy.fzb.captain_team.fragment.MyClientFragment5;
import com.xcy.fzb.captain_team.fragment.MyClientFragment6;
import com.xcy.fzb.captain_team.fragment.MyClientFragment7;
import com.xcy.fzb.captain_team.persente.MyClientName;
import com.xcy.fzb.captain_team.persente.StatusBar;

import org.greenrobot.eventbus.EventBus;

public class MyClientActivity extends AppCompatActivity implements View.OnClickListener {

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

    MyClientFragment1 myClientFragment1 = new MyClientFragment1();
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
        StatusBar.makeStatusBarTransparent(this);
        initView();
    }

    private void initView() {
        client = getIntent().getStringExtra("client");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

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
            myClientFragment1 = new MyClientFragment1();
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
                Intent intent = new Intent(MyClientActivity.this, MyClientAddActivity.class);
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
