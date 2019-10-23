package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.presente.StatusBar;

//TODO 同行人员
public class FieldActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView field_return;
    ImageView field_img;

    Button field_btn;

    TextView field_tv1;
    TextView field_tv2;

    EditText field_et1;
    EditText field_et2;
    EditText field_et3;
    EditText field_et4;
    EditText field_et5;

    RelativeLayout field_rll;
    RelativeLayout field_rl2;
    private String et1;
    private String et2;
    private String et3;
    private String et4;
    private String et5;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        field_return = findViewById(R.id.field_return);

        field_img = findViewById(R.id.field_img);

        field_btn = findViewById(R.id.field_btn);

        field_tv1 = findViewById(R.id.field_tv1);
        field_tv2 = findViewById(R.id.field_tv2);

        field_et1 = findViewById(R.id.field_et1);
        field_et2 = findViewById(R.id.field_et2);
        field_et3 = findViewById(R.id.field_et3);
        field_et4 = findViewById(R.id.field_et4);
        field_et5 = findViewById(R.id.field_et5);

        field_rll = findViewById(R.id.field_rl1);
        field_rll = findViewById(R.id.field_rl2);

        field_return.setOnClickListener(this);
        field_tv1.setOnClickListener(this);
        field_rll.setOnClickListener(this);
        field_rl2.setOnClickListener(this);
        field_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一層
            case R.id.field_return:

                break;
            //            TODO 选择性别
            case R.id.field_tv1:

                break;
            //            TODO 添加图片
            case R.id.field_rl1:

                break;
            //            TODO 客户基本信息描摹
            case R.id.field_rl2:

                break;
            //            TODO 完成
            case R.id.field_btn:

                et1 = field_et1.getText().toString();
                et2 = field_et2.getText().toString();
                et3 = field_et3.getText().toString();
                et4 = field_et4.getText().toString();
                et5 = field_et5.getText().toString();

                sex = field_tv1.getText().toString();

                break;


        }

    }
}
