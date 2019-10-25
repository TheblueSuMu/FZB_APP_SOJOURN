package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.ProjectProgressApi;
import com.xcy.fzb.all.modle.ConfessBean;
import com.xcy.fzb.all.persente.OkHttpPost;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.utils.MatcherUtils;
import com.xcy.fzb.all.view.AllActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import top.defaults.view.DateTimePickerView;

//TODO 填写认筹信息
public class ConfessToRaiseInformationActivity extends AllActivity implements View.OnClickListener {

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

    RelativeLayout confess_to_raise_information_return;
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
    private TextView picker_cancel;
    private TextView picker_ensure;
    private DateTimePickerView pickerView;
    private LinearLayout picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_confess_to_raise_information);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

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

        picker_cancel = findViewById(R.id.picker_cancel);
        picker_ensure = findViewById(R.id.picker_ensure);
        pickerView = findViewById(R.id.pickerView);
        picker = findViewById(R.id.picker);

        confess_to_raise_information_rg = findViewById(R.id.confess_to_raise_information_rg);

        confess_to_raise_information_rb1 = findViewById(R.id.confess_to_raise_information_rb1);
        confess_to_raise_information_rb2 = findViewById(R.id.confess_to_raise_information_rb2);

        confess_to_raise_information_rl1 = findViewById(R.id.confess_to_raise_information_rl1);
        confess_to_raise_information_rl2 = findViewById(R.id.confess_to_raise_information_rl2);
        confess_to_raise_information_rl3 = findViewById(R.id.confess_to_raise_information_rl3);

        confess_to_raise_information_btn = findViewById(R.id.confess_to_raise_information_btn);

        confess_to_raise_information_return = findViewById(R.id.confess_to_raise_information_return);

        confess_to_raise_information_tv1.setText(ProjectProgressApi.getCustomerName());
        confess_to_raise_information_tv2.setText(ProjectProgressApi.getCustomerPhone());
        confess_to_raise_information_tv3.setText(ProjectProgressApi.getProjectName());


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
                initDate();
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

                if (!MatcherUtils.isMobile(confess_to_raise_information_et2.getText().toString())) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                }

                if(s6.equals("一室")){
                    s8 = "1";
                }else if(s6.equals("二室")){
                    s8 = "2";
                }else if(s6.equals("三室")){
                    s8 = "3";
                }else if(s6.equals("四室")){
                    s8 = "4";
                }else if(s6.equals("五室")){
                    s8 = "5";
                }

                if (confess_to_raise_information_rb1.isChecked()) {
                    sex = "男";
                } else if (confess_to_raise_information_rb2.isChecked()) {
                    sex = "女";

                }
//                TODO 解析bean类
                url = FinalContents.getBaseUrl()+"nodeUpdate/earnestMoneySave?preparationId=" + FinalContents.getPreparationId() + "&customerId=" + FinalContents.getCustomerID() + "&projectId=" + FinalContents.getProjectID() + "&fullName=" + s + "&phone=" + s1 + "&idNumber=" + s2 + "&intentionPier=" + s3 + "&apartment=" + s8 + "&intentionalArea=" + s4 + "&recognizeTime=" + s7 + "&relation=" + s5 + "&userId=" + FinalContents.getUserID();
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
                            FinalContents.setTiaozhuang("认筹成功");
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


    //TODO 认筹时间赋值
    private void initDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month, dayOfMonth);
        confess_to_raise_information_tv6.setText(dateString);
        pickerView.setStartDate(new GregorianCalendar(year-2, 01, 01));
        // 注意：月份是从0开始计数的
        pickerView.setSelectedDate(new GregorianCalendar(2019, 01, 01));
        pickerView.setEndDate(new GregorianCalendar(year, month-1, dayOfMonth));

        picker_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setVisibility(View.GONE);
            }
        });

        picker_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setVisibility(View.GONE);
            }
        });

        //            TODO 时间
        confess_to_raise_information_tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setVisibility(View.VISIBLE);
                pickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month + 1, dayOfMonth);
                        confess_to_raise_information_tv6.setText(dateString);
                    }
                });
            }
        });

    }
}
