package com.xcy.fzb.captain_team.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.ConfessBean;
import com.xcy.fzb.captain_team.persente.OkHttpPost;
import com.xcy.fzb.captain_team.persente.StatusBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//TODO 填写认筹信息
public class ConfessToRaiseInformationActivity extends AppCompatActivity implements View.OnClickListener {

    TextView confess_to_raise_information_tv1;
    TextView confess_to_raise_information_tv2;
    TextView confess_to_raise_information_tv3;
    TextView confess_to_raise_information_tv4;
    TextView confess_to_raise_information_tv5;
    TextView confess_to_raise_information_tv6;

    EditText confess_to_raise_information_et1;
    EditText confess_to_raise_information_et2;
    EditText confess_to_raise_information_et3;
    EditText confess_to_raise_information_et4;
    EditText confess_to_raise_information_et5;

    RadioGroup confess_to_raise_information_rg;

    RadioButton confess_to_raise_information_rb1;
    RadioButton confess_to_raise_information_rb2;

    RelativeLayout confess_to_raise_information_rl1;
    RelativeLayout confess_to_raise_information_rl2;
    RelativeLayout confess_to_raise_information_rl3;

    Button confess_to_raise_information_btn;

    ImageView confess_to_raise_information_return;
    private String projectName;
    private String name;
    private String phone;
    private List<String> list;
    private OptionsPickerView pvOptions;
    private String sex;
    private String s;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private String s7;
    private String url;
    private String s8;

    private String beforeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confess_to_raise_information);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        projectName = getIntent().getStringExtra("projectName");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");

        confess_to_raise_information_tv1 = findViewById(R.id.confess_to_raise_information_tv1);
        confess_to_raise_information_tv2 = findViewById(R.id.confess_to_raise_information_tv2);
        confess_to_raise_information_tv3 = findViewById(R.id.confess_to_raise_information_tv3);
        confess_to_raise_information_tv4 = findViewById(R.id.confess_to_raise_information_tv4);
        confess_to_raise_information_tv5 = findViewById(R.id.confess_to_raise_information_tv5);
        confess_to_raise_information_tv6 = findViewById(R.id.confess_to_raise_information_tv6);

        confess_to_raise_information_et1 = findViewById(R.id.confess_to_raise_information_et1);
        confess_to_raise_information_et2 = findViewById(R.id.confess_to_raise_information_et2);
        confess_to_raise_information_et3 = findViewById(R.id.confess_to_raise_information_et3);
        confess_to_raise_information_et4 = findViewById(R.id.confess_to_raise_information_et4);
        confess_to_raise_information_et5 = findViewById(R.id.confess_to_raise_information_et5);

        confess_to_raise_information_rg = findViewById(R.id.confess_to_raise_information_rg);

        confess_to_raise_information_rb1 = findViewById(R.id.confess_to_raise_information_rb1);
        confess_to_raise_information_rb2 = findViewById(R.id.confess_to_raise_information_rb2);

        confess_to_raise_information_rl1 = findViewById(R.id.confess_to_raise_information_rl1);
        confess_to_raise_information_rl2 = findViewById(R.id.confess_to_raise_information_rl2);
        confess_to_raise_information_rl3 = findViewById(R.id.confess_to_raise_information_rl3);

        confess_to_raise_information_btn = findViewById(R.id.confess_to_raise_information_btn);

        confess_to_raise_information_return = findViewById(R.id.confess_to_raise_information_return);

        confess_to_raise_information_tv1.setText(name);
        confess_to_raise_information_tv2.setText(phone);
        confess_to_raise_information_tv3.setText(projectName);


        confess_to_raise_information_return.setOnClickListener(this);
        confess_to_raise_information_btn.setOnClickListener(this);
        confess_to_raise_information_rl1.setOnClickListener(this);
        confess_to_raise_information_rl2.setOnClickListener(this);
        confess_to_raise_information_rl3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.confess_to_raise_information_return:
                finish();
                break;
            //            TODO 报备客户与认筹客户关系
            case R.id.confess_to_raise_information_rl1:
                list = new ArrayList<>();
                list.add("本人");
                list.add("父母");
                list.add("子女");
                list.add("配偶");
                list.add("其他");
                pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //               返回的分别是三个级别的选中位置
                        //              展示选中数据
                        confess_to_raise_information_tv4.setText(list.get(options1));
                    }
                })
                        .setSelectOptions(0)//设置选择第一个
                        .setOutSideCancelable(false)//点击背的地方不消失
                        .build();//创建
                //      把数据绑定到控件上面
                pvOptions.setPicker(list);
                //      展示
                pvOptions.show();
                break;
            //            TODO 意向户型
            case R.id.confess_to_raise_information_rl2:
                list = new ArrayList<>();
                list.add("一室");
                list.add("二室");
                list.add("三室");
                list.add("四室");
                list.add("五室");
                //创建
                pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //               返回的分别是三个级别的选中位置
                        //              展示选中数据
                        confess_to_raise_information_tv5.setText(list.get(options1));
                    }
                })
                        .setSelectOptions(0)//设置选择第一个
                        .setOutSideCancelable(false)//点击背的地方不消失
                        .build();
                //      把数据绑定到控件上面
                pvOptions.setPicker(list);
                //      展示
                pvOptions.show();

                break;
            //            TODO 认筹时间
            case R.id.confess_to_raise_information_rl3:
//
//                Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH)+1;
//                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//                dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));
//
//                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
//                    @Override
//                    public void onSelectedDateChanged(Calendar date) {
//                        int year = date.get(Calendar.YEAR);
//                        int month = date.get(Calendar.MONTH);
//                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
//                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
//                        beforeDate = dateString;
//                        confess_to_raise_information_tv6.setText("<"+dateString);
//                    }
//                });

                list = new ArrayList<>();
                list.add("20190812");
                list.add("20190813");
                list.add("20190814");
                list.add("20190815");
                list.add("20190816");
                //创建
                pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //               返回的分别是三个级别的选中位置
                        //              展示选中数据
                        confess_to_raise_information_tv6.setText(list.get(options1));
                    }
                })
                        .setSelectOptions(0)//设置选择第一个
                        .setOutSideCancelable(false)//点击背的地方不消失
                        .build();
                //      把数据绑定到控件上面
                pvOptions.setPicker(list);
                //      展示
                pvOptions.show();
                break;
            //            TODO 提交
            case R.id.confess_to_raise_information_btn:

                s = confess_to_raise_information_et1.getText().toString();
                s1 = confess_to_raise_information_et2.getText().toString();
                s2 = confess_to_raise_information_et3.getText().toString();
                s3 = confess_to_raise_information_et4.getText().toString();
                s4 = confess_to_raise_information_et5.getText().toString();

                s5 = confess_to_raise_information_tv4.getText().toString();
                s6 = confess_to_raise_information_tv5.getText().toString();
                s7 = confess_to_raise_information_tv6.getText().toString();

                if(s6.equals("一室")){
                    s8 = "10";
                }else if(s6.equals("二室")){
                    s8 = "20";
                }else if(s6.equals("三室")){
                    s8 = "30";
                }else if(s6.equals("四室")){
                    s8 = "40";
                }else if(s6.equals("五室")){
                    s8 = "50";
                }

                if (confess_to_raise_information_rb1.isChecked()) {
                    sex = "男";
                } else if (confess_to_raise_information_rb2.isChecked()) {
                    sex = "女";

                }
//                TODO 解析bean类
                url = "http://39.98.173.250:8080/fangfang/app/v1/nodeUpdate/earnestMoneySave?preparationId=" + FinalContents.getPreparationId() + "&customerId=" + FinalContents.getCustomerID() + "&projectId=" + FinalContents.getProjectID() + "&fullName=" + s + "&phone=" + s1 + "&idNumber=" + s2 + "&intentionPier=" + s3 + "&apartment=" + s8 + "&intentionalArea=" + s4 + "&recognizeTime=" + s7 + "&relation=" + s5 + "&userId=" + FinalContents.getUserID();
                OkHttpPost okHttpPost = new OkHttpPost(url);
                String post = okHttpPost.post();

                try {
                    JSONObject jsonObject = new JSONObject(post);

                    String msg = jsonObject.getString("msg");
                    if(msg.equals("")){
                        Toast.makeText(ConfessToRaiseInformationActivity.this, "该数据无法认筹", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Gson gson = new Gson();
                        ConfessBean confessBean1 = gson.fromJson(post, ConfessBean.class);
                        if (confessBean1.getMsg().equals("成功")) {
                            Toast.makeText(ConfessToRaiseInformationActivity.this, confessBean1.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ConfessToRaiseInformationActivity.this, confessBean1.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;

        }

    }


}
