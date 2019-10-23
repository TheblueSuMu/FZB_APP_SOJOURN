package com.xcy.fzb.captain_team.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.persente.StatusBar;

//TODO 用戶基本信息描摹
public class EssentialInformationActivity extends AppCompatActivity {

    ImageView essential_information_return;

    EditText essential_information_et1;
    EditText essential_information_et2;
    EditText essential_information_et3;
    EditText essential_information_et4;
    EditText essential_information_et5;
    EditText essential_information_et6;
    EditText essential_information_et7;
    EditText essential_information_et8;

    Button essential_information_tbn;

    RadioButton essential_information_rb1;
    RadioButton essential_information_rb2;

    RadioGroup essential_information_rg;
    private String et1;
    private String et3;
    private String et4;
    private String et5;
    private String et6;
    private String et2;
    private String et7;
    private String et8;
    private String authority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essential_information);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        essential_information_return = findViewById(R.id.essential_information_return);

        essential_information_et1 = findViewById(R.id.essential_information_et1);
        essential_information_et2 = findViewById(R.id.essential_information_et2);
        essential_information_et3 = findViewById(R.id.essential_information_et3);
        essential_information_et4 = findViewById(R.id.essential_information_et4);
        essential_information_et5 = findViewById(R.id.essential_information_et5);
        essential_information_et6 = findViewById(R.id.essential_information_et6);
        essential_information_et7 = findViewById(R.id.essential_information_et7);
        essential_information_et8 = findViewById(R.id.essential_information_et8);

        essential_information_tbn = findViewById(R.id.essential_information_btn);

        essential_information_rb1 = findViewById(R.id.essential_information_rb1);
        essential_information_rb2 = findViewById(R.id.essential_information_rb2);

        essential_information_rg = findViewById(R.id.essential_information_rg);

        essential_information_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        essential_information_tbn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                et1 = essential_information_et1.getText().toString();
                et2 = essential_information_et2.getText().toString();
                et3 = essential_information_et3.getText().toString();
                et4 = essential_information_et4.getText().toString();
                et5 = essential_information_et5.getText().toString();
                et6 = essential_information_et6.getText().toString();
                et7 = essential_information_et7.getText().toString();
                et8 = essential_information_et8.getText().toString();

                if(essential_information_rb1.isChecked() == true){
                    authority = "是";
                }else if(essential_information_rb2.isChecked() == true){
                    authority = "否";
                }
                Toast.makeText(EssentialInformationActivity.this, "用户消息提交完成", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
