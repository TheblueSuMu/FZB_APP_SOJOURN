package com.xcy.fzb.project_side.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.fragment.MyClientFragment2;
import com.xcy.fzb.project_side.fragment.MyClientFragment3;
import com.xcy.fzb.project_side.fragment.MyClientFragment4;
import com.xcy.fzb.project_side.fragment.MyClientFragment5;
import com.xcy.fzb.project_side.fragment.MyClientFragment6;
import com.xcy.fzb.project_side.fragment.MyClientFragment7;
import com.xcy.fzb.project_side.presente.MyClientData;
import com.xcy.fzb.project_side.presente.StatusBar;

import org.greenrobot.eventbus.EventBus;

public class MyClientActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout my_client_return;
    ImageView client_add;

    EditText my_client_ss;

    TextView my_client_tv;

    LinearLayout my_client_11_2;
    LinearLayout my_client_11_3;
    LinearLayout my_client_11_4;
    LinearLayout my_client_11_5;
    LinearLayout my_client_11_6;
    LinearLayout my_client_11_7;
    LinearLayout my_client_11_9;
    LinearLayout my_client_11_10;
    LinearLayout my_client_11_11;
    LinearLayout my_client_11_12;
    LinearLayout my_client_11_13;
    LinearLayout my_client_11_14;

    FragmentManager manager;
    FragmentTransaction transaction;

    MyClientFragment2 myClientFragment2;
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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        client = getIntent().getStringExtra("client");

        my_client_return = findViewById(R.id.my_client_return);
        client_add = findViewById(R.id.client_add);
        my_client_ss = findViewById(R.id.my_client_ss);
        my_client_tv = findViewById(R.id.my_client_tv);
        my_client_11_2 = findViewById(R.id.my_client_ll_2);
        my_client_11_3 = findViewById(R.id.my_client_ll_3);
        my_client_11_4 = findViewById(R.id.my_client_ll_4);
        my_client_11_5 = findViewById(R.id.my_client_ll_5);
        my_client_11_6 = findViewById(R.id.my_client_ll_6);
        my_client_11_7 = findViewById(R.id.my_client_ll_7);
        my_client_11_9 = findViewById(R.id.my_client_ll_9);
        my_client_11_10 = findViewById(R.id.my_client_ll_10);
        my_client_11_11 = findViewById(R.id.my_client_ll_11);
        my_client_11_12 = findViewById(R.id.my_client_ll_12);
        my_client_11_13 = findViewById(R.id.my_client_ll_13);
        my_client_11_14 = findViewById(R.id.my_client_ll_14);

        myClientFragment2 = new MyClientFragment2();
       initClient();

        my_client_return.setOnClickListener(this);
        client_add.setOnClickListener(this);
        my_client_11_2.setOnClickListener(this);
        my_client_11_3.setOnClickListener(this);
        my_client_11_4.setOnClickListener(this);
        my_client_11_5.setOnClickListener(this);
        my_client_11_6.setOnClickListener(this);
        my_client_11_7.setOnClickListener(this);

        my_client_ss.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    String s = my_client_ss.getText().toString();
                    if(my_client_11_9.getVisibility() == View.VISIBLE){
                        Log.i("MyCL","回车点击事件1");
                        EventBus.getDefault().post(new MyClientData(s,"报备"));
                    }else if(my_client_11_10.getVisibility() == View.VISIBLE){
                        Log.i("MyCL","回车点击事件2");
                        EventBus.getDefault().post(new MyClientData(s,"到访"));
                    }else if(my_client_11_11.getVisibility() == View.VISIBLE){
                        Log.i("MyCL","回车点击事件3");
                        EventBus.getDefault().post(new MyClientData(s,"登岛"));
                    }else if(my_client_11_12.getVisibility() == View.VISIBLE){
                        Log.i("MyCL","回车点击事件4");
                        EventBus.getDefault().post(new MyClientData(s,"认筹"));
                    }else if(my_client_11_13.getVisibility() == View.VISIBLE){
                        Log.i("MyCL","回车点击事件5");
                        EventBus.getDefault().post(new MyClientData(s,"成交"));
                    }else if(my_client_11_14.getVisibility() == View.VISIBLE){
                        Log.i("MyCL","回车点击事件6");
                        EventBus.getDefault().post(new MyClientData(s,"失效"));
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private void initClient(){
        if (client.equals("1")) {
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
            case R.id.my_client_ll_2:

                my_client_11_9.setVisibility(View.VISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment2);
                break;
            case R.id.my_client_ll_3:

                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.VISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment3);
                break;
            case R.id.my_client_ll_4:

                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.VISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment4);
                break;
            case R.id.my_client_ll_5:

                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.VISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment5);
                break;
            case R.id.my_client_ll_6:

                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.VISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment6);
                break;
            case R.id.my_client_ll_7:

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
