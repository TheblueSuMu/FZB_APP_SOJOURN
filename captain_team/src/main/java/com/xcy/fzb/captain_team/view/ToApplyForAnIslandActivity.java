package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.persente.StatusBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//TODO 申请登岛
public class ToApplyForAnIslandActivity extends AppCompatActivity implements View.OnClickListener {

    TextView to_apply_for_an_island_tv1;
    TextView to_apply_for_an_island_tv2;
    TextView to_apply_for_an_island_tv3;
    TextView to_apply_for_an_island_tv4;

    ImageView to_apply_for_an_island_return;
    ImageView to_apply_for_an_island_img;

    EditText to_apply_for_an_island_et1;
    EditText to_apply_for_an_island_et2;

    RelativeLayout to_apply_for_an_island_rl1;
    RelativeLayout to_apply_for_an_island_rl2;

    Button to_apply_for_an_island_btn;
    private String name = "";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_apply_for_an_island);
        name = getIntent().getStringExtra("name");
        initView();

    }

    @SuppressLint("WrongViewCast")
    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        to_apply_for_an_island_tv1 = findViewById(R.id.to_apply_for_an_island_tv1);
        to_apply_for_an_island_tv2 = findViewById(R.id.to_apply_for_an_island_tv2);
        to_apply_for_an_island_tv3 = findViewById(R.id.to_apply_for_an_island_tv3);
        to_apply_for_an_island_tv4 = findViewById(R.id.to_apply_for_an_island_tv4);

        to_apply_for_an_island_return = findViewById(R.id.to_apply_for_an_island_return);
        to_apply_for_an_island_img = findViewById(R.id.to_apply_for_an_island_img);

        to_apply_for_an_island_et1 = findViewById(R.id.to_apply_for_an_island_et1);
        to_apply_for_an_island_et2 = findViewById(R.id.to_apply_for_an_island_et2);

        to_apply_for_an_island_rl1 = findViewById(R.id.to_apply_for_an_island_rl1);
        to_apply_for_an_island_rl2 = findViewById(R.id.to_apply_for_an_island_rl2);

        to_apply_for_an_island_btn = findViewById(R.id.to_apply_for_an_island_btn);

        to_apply_for_an_island_return.setOnClickListener(this);
        to_apply_for_an_island_rl1.setOnClickListener(this);
        to_apply_for_an_island_rl2.setOnClickListener(this);
        to_apply_for_an_island_btn.setOnClickListener(this);

        to_apply_for_an_island_tv1.setText(name);
        to_apply_for_an_island_et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 18) {
                    int age = getAge(to_apply_for_an_island_et1.getText().toString());
                    to_apply_for_an_island_tv3.setText(age+"");
                }else {
                    to_apply_for_an_island_tv3.setText("");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
// TODO 返回上一層
            case R.id.to_apply_for_an_island_return:
                finish();
                break;
// TODO 請選擇性別
            case R.id.to_apply_for_an_island_rl1:
                List<String> list = new ArrayList<>();
                list.add("男");
                list.add("女");
                initSelect(list,to_apply_for_an_island_tv2);
                break;
// TODO 客戶基本信息描摹
            case R.id.to_apply_for_an_island_rl2:
                intent = new Intent(ToApplyForAnIslandActivity.this, EssentialInformationActivity.class);
                startActivity(intent);

                break;
//TODO 下一步
            case R.id.to_apply_for_an_island_btn:
                if (!to_apply_for_an_island_et1.getText().toString().equals("")) {
                    if (!to_apply_for_an_island_et2.getText().toString().equals("")) {
                        if (to_apply_for_an_island_tv2.getText().toString().equals("男") || to_apply_for_an_island_tv2.getText().toString().equals("女")) {
                            if (!to_apply_for_an_island_tv3.getText().toString().equals("")) {
                                intent = new Intent(ToApplyForAnIslandActivity.this, ToApplyForAnlsland2Activity.class);
                                intent.putExtra("customerName",to_apply_for_an_island_tv1.getText().toString());
                                intent.putExtra("gender",to_apply_for_an_island_tv2.getText().toString());
                                intent.putExtra("idNumber",to_apply_for_an_island_et1.getText().toString());
                                intent.putExtra("age",to_apply_for_an_island_tv3.getText().toString());
                                intent.putExtra("passportNumber",to_apply_for_an_island_et2.getText().toString());
                                startActivity(intent);
                            }else {
                                Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "请输入护照编号", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "请输入身份证", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    //由出生日期获得年龄
    public static  int getAge(String number) {
        String num = number.substring(6, 14);
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(format.parse(num));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }

    //选择器
    private void initSelect(final List<String> list, final TextView textView){
        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(ToApplyForAnIslandActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                textView.setText(list.get(options1));
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();
    }
}
