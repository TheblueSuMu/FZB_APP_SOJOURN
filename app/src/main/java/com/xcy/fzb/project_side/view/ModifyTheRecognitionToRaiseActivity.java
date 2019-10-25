package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.view.AllActivity;

//TODO 修改认筹信息
public class ModifyTheRecognitionToRaiseActivity extends AllActivity {

    RelativeLayout modify_the_recognition_to_raise_return;

    RadioGroup modify_the_recognition_to_raise_rg1;

    RadioButton modify_the_recognition_to_raise_rb1;
    RadioButton modify_the_recognition_to_raise_rb2;

    EditText modify_the_recognition_to_raise_tv1;
    EditText modify_the_recognition_to_raise_tv2;
    EditText modify_the_recognition_to_raise_tv3;
    EditText modify_the_recognition_to_raise_tv4;
    EditText modify_the_recognition_to_raise_tv5;
    EditText modify_the_recognition_to_raise_tv6;
    EditText modify_the_recognition_to_raise_tv7;
    EditText modify_the_recognition_to_raise_tv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_modify_the_recognition_to_raise);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        modify_the_recognition_to_raise_return = findViewById(R.id.modify_the_recognition_to_raise_return);
        modify_the_recognition_to_raise_rg1 = findViewById(R.id.modify_the_recognition_to_raise_rg1);
        modify_the_recognition_to_raise_rb1 = findViewById(R.id.modify_the_recognition_to_raise_rb1);
        modify_the_recognition_to_raise_rb2 = findViewById(R.id.modify_the_recognition_to_raise_rb2);
        modify_the_recognition_to_raise_tv1 = findViewById(R.id.modify_the_recognition_to_raise_tv1);
        modify_the_recognition_to_raise_tv2 = findViewById(R.id.modify_the_recognition_to_raise_tv2);
        modify_the_recognition_to_raise_tv3 = findViewById(R.id.modify_the_recognition_to_raise_tv3);
        modify_the_recognition_to_raise_tv2.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        modify_the_recognition_to_raise_tv3.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        modify_the_recognition_to_raise_tv4 = findViewById(R.id.modify_the_recognition_to_raise_tv4);
        modify_the_recognition_to_raise_tv5 = findViewById(R.id.modify_the_recognition_to_raise_tv5);
        modify_the_recognition_to_raise_tv6 = findViewById(R.id.modify_the_recognition_to_raise_tv6);
        modify_the_recognition_to_raise_tv7 = findViewById(R.id.modify_the_recognition_to_raise_tv7);
        modify_the_recognition_to_raise_tv8 = findViewById(R.id.modify_the_recognition_to_raise_tv8);


        modify_the_recognition_to_raise_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initData();

    }

    private void initData() {



    }
}
