package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.presente.StatusBar;

//TODO 修改成交信息
public class ModifyTheClinchADealActivity extends AppCompatActivity {

    ImageView modify_the_clinch_a_deal_return;

    RadioGroup modify_the_clinch_a_deal_rg1;

    RadioButton modify_the_clinch_a_deal_rb1;
    RadioButton modify_the_clinch_a_deal_rb2;

    EditText modify_the_clinch_a_deal_tv1;
    EditText modify_the_clinch_a_deal_tv2;
    EditText modify_the_clinch_a_deal_tv3;
    EditText modify_the_clinch_a_deal_tv4;
    EditText modify_the_clinch_a_deal_tv5;
    EditText modify_the_clinch_a_deal_tv6;
    EditText modify_the_clinch_a_deal_tv7;
    EditText modify_the_clinch_a_deal_tv8;
    EditText modify_the_clinch_a_deal_tv9;
    EditText modify_the_clinch_a_deal_tv10;
    EditText modify_the_clinch_a_deal_tv11;
    EditText modify_the_clinch_a_deal_tv12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_the_clinch_adeal);
        StatusBar.makeStatusBarTransparent(this);
        initView();

    }

    private void initView() {

        modify_the_clinch_a_deal_return = findViewById(R.id.modify_the_clinch_a_deal_return);
        modify_the_clinch_a_deal_rg1 = findViewById(R.id.modify_the_clinch_a_deal_rg1);
        modify_the_clinch_a_deal_rb1 = findViewById(R.id.modify_the_clinch_a_deal_rb1);
        modify_the_clinch_a_deal_rb2 = findViewById(R.id.modify_the_clinch_a_deal_rb2);
        modify_the_clinch_a_deal_tv1 = findViewById(R.id.modify_the_clinch_a_deal_tv1);
        modify_the_clinch_a_deal_tv2 = findViewById(R.id.modify_the_clinch_a_deal_tv2);
        modify_the_clinch_a_deal_tv3 = findViewById(R.id.modify_the_clinch_a_deal_tv3);
        modify_the_clinch_a_deal_tv4 = findViewById(R.id.modify_the_clinch_a_deal_tv4);
        modify_the_clinch_a_deal_tv5 = findViewById(R.id.modify_the_clinch_a_deal_tv5);
        modify_the_clinch_a_deal_tv6 = findViewById(R.id.modify_the_clinch_a_deal_tv6);
        modify_the_clinch_a_deal_tv7 = findViewById(R.id.modify_the_clinch_a_deal_tv7);
        modify_the_clinch_a_deal_tv8 = findViewById(R.id.modify_the_clinch_a_deal_tv8);
        modify_the_clinch_a_deal_tv9 = findViewById(R.id.modify_the_clinch_a_deal_tv9);
        modify_the_clinch_a_deal_tv10 = findViewById(R.id.modify_the_clinch_a_deal_tv10);
        modify_the_clinch_a_deal_tv11 = findViewById(R.id.modify_the_clinch_a_deal_tv11);
        modify_the_clinch_a_deal_tv12 = findViewById(R.id.modify_the_clinch_a_deal_tv12);

        initData();

        modify_the_clinch_a_deal_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void initData() {


    }
}
