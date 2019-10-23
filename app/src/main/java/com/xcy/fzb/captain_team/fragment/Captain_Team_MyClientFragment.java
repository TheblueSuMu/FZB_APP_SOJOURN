package com.xcy.fzb.captain_team.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzb.R;
import com.xcy.fzb.all.fragment.AllFragment;
import com.xcy.fzb.all.fragment.MyClientFragment2;
import com.xcy.fzb.all.fragment.MyClientFragment3;
import com.xcy.fzb.all.fragment.MyClientFragment4;
import com.xcy.fzb.all.fragment.MyClientFragment5;
import com.xcy.fzb.all.fragment.MyClientFragment6;
import com.xcy.fzb.all.fragment.MyClientFragment7;
import com.xcy.fzb.all.persente.MyClientName;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.view.MyClientAddActivity;

import org.greenrobot.eventbus.EventBus;

public class Captain_Team_MyClientFragment extends AllFragment implements View.OnClickListener {

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

    Captain_Team_MyClientFragment1 captain_team_myClientFragment1 = new Captain_Team_MyClientFragment1();
    MyClientFragment2 myClientFragment2 = new MyClientFragment2();
    MyClientFragment3 myClientFragment3 = new MyClientFragment3();
    MyClientFragment4 myClientFragment4 = new MyClientFragment4();
    MyClientFragment5 myClientFragment5 = new MyClientFragment5();
    MyClientFragment6 myClientFragment6 = new MyClientFragment6();
    MyClientFragment7 myClientFragment7 = new MyClientFragment7();

    private Context context;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());
        view = inflater.inflate(R.layout.activity_my_client, container, false);
        initView();
        context = container.getContext();
        return view;
    }

    private void initView() {

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        my_client_return = view.findViewById(R.id.my_client_return);
        client_add = view.findViewById(R.id.client_add);
        my_client_ss = view.findViewById(R.id.my_client_ss);
        my_client_tv = view.findViewById(R.id.my_client_tv);
        my_client_ll_1 = view.findViewById(R.id.my_client_ll_1);
        my_client_11_2 = view.findViewById(R.id.my_client_ll_2);
        my_client_11_3 = view.findViewById(R.id.my_client_ll_3);
        my_client_11_4 = view.findViewById(R.id.my_client_ll_4);
        my_client_11_5 = view.findViewById(R.id.my_client_ll_5);
        my_client_11_6 = view.findViewById(R.id.my_client_ll_6);
        my_client_11_7 = view.findViewById(R.id.my_client_ll_7);
        my_client_11_8 = view.findViewById(R.id.my_client_ll_8);
        my_client_11_9 = view.findViewById(R.id.my_client_ll_9);
        my_client_11_10 = view.findViewById(R.id.my_client_ll_10);
        my_client_11_11 = view.findViewById(R.id.my_client_ll_11);
        my_client_11_12 = view.findViewById(R.id.my_client_ll_12);
        my_client_11_13 = view.findViewById(R.id.my_client_ll_13);
        my_client_11_14 = view.findViewById(R.id.my_client_ll_14);
        captain_team_myClientFragment1 = new Captain_Team_MyClientFragment1();
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.my_client_fl, captain_team_myClientFragment1);
        transaction.commit();

        my_client_return.setOnClickListener(this);
        client_add.setOnClickListener(this);
        my_client_ll_1.setOnClickListener(this);
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
                    EventBus.getDefault().post(new MyClientName(s));
                    return true;
                }
                return false;
            }

        });

    }

    @Override
    public void onClick(View view) {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.my_client_return:
                break;
            case R.id.client_add:
                Intent intent = new Intent(context, MyClientAddActivity.class);
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
                transaction.replace(R.id.my_client_fl, captain_team_myClientFragment1);
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
