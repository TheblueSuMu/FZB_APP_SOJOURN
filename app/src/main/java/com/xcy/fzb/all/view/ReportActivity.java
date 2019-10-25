package com.xcy.fzb.all.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.ReportItemAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.ChangePhoneBean;
import com.xcy.fzb.all.modle.ReportBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;

public class ReportActivity extends AllActivity implements View.OnClickListener {

    Button report_btn_s;
    Button report_btn_f;
    LinearLayout report_tv_timer;
    TextView report_tv_sq;
    TextView report_start;
    TextView report_end;
    TextView report_cancel;
    TextView report_ensure;
    EditText IDcard;
    EditText price_start;
    EditText price_end;
    EditText report_remarks;

    RelativeLayout back;

    LinearLayout report_picker;
    LinearLayout report_ll_start_sq;

    DateTimePickerView dateTimePickerView;

    private CheckBox area1;
    private CheckBox area2;
    private CheckBox area3;
    private CheckBox area4;
    private CheckBox area5;
    private CheckBox area6;
    private CheckBox area7;
    private CheckBox area8;


    private CheckBox type1;
    private CheckBox type2;
    private CheckBox type3;
    private CheckBox type4;
    private CheckBox type5;

    private CheckBox goal1;
    private CheckBox goal2;
    private CheckBox goal3;
    private CheckBox goal4;
    private CheckBox goal5;
    private CheckBox goal6;
    private CheckBox goal7;
    private CheckBox goal8;

    private TextView ensure;

    private TextView client_name;
    private TextView project_name;

    private String areaSection = "";
    private String ffProjectTrait = "";
    private String procuctType = "";
    private String fitmentState = "";

    private String url = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectList?"+ "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID();
    private String reportSaveUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonUpdate/reportSave?";
    private String eventUrl;

    private Map<Integer,String> areaMap = new HashMap<>();
    private Map<Integer,String> traitMap = new HashMap<>();
    private Map<Integer,String> typeMap = new HashMap<>();
    private Map<Integer,String> goalMap = new HashMap<>();
    private List<ReportBean.DataBean.AreaSectionListBean> areaSectionListBean = new ArrayList<>();
    private List<ReportBean.DataBean.ProductTypeListBean> productTypeListBeans = new ArrayList<>();
    private List<ReportBean.DataBean.ProjectLabelListBean> projectLabelListBeans = new ArrayList<>();
    private List<ReportBean.DataBean.PurposeListBean> purposeListBeans = new ArrayList<>();
    private String isIsland = "0";
    private String timeStart = "";
    private String timeEnd = "";
    private boolean type_1 = true;
    private boolean type_2 = true;
    private boolean type_3 = true;
    private boolean type_4 = true;
    private RecyclerView report_rv;
    private LinearLayout report_ll_start_sq1;
    private LinearLayout report_ll_start_sq2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        initDataReport();

        initData();
        initView();
        initDate();


    }

    private void initView(){

        StatusBar.makeStatusBarTransparent(this);

        report_btn_s = findViewById(R.id.report_btn_yes);
        report_btn_f = findViewById(R.id.report_btn_no);
        report_tv_timer = findViewById(R.id.report_tv_timer);
        report_tv_sq = findViewById(R.id.report_tv_sq);

        report_ll_start_sq1 = findViewById(R.id.report_ll_start_sq1);

        report_ll_start_sq2 = findViewById(R.id.report_ll_start_sq2);

        report_ll_start_sq = findViewById(R.id.report_ll_start_sq);
        dateTimePickerView = findViewById(R.id.report_pickerView);
        report_start = findViewById(R.id.report_start);
        report_end = findViewById(R.id.report_end);
        IDcard = findViewById(R.id.report_IDcard);
        price_start = findViewById(R.id.report_price_start);
        price_end = findViewById(R.id.report_price_end);
        report_remarks = findViewById(R.id.report_remarks);
        client_name = findViewById(R.id.report_client_name);
        project_name = findViewById(R.id.report_project_name);
        client_name.setOnClickListener(this);
        project_name.setOnClickListener(this);

        back = findViewById(R.id.report_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setChecked(false);
                FinalContents.setChecked2(false);
                finish();
            }
        });

        report_cancel = findViewById(R.id.report_picker_cancel);
        report_ensure = findViewById(R.id.report_picker_ensure);

        report_picker = findViewById(R.id.report_picker);

        report_btn_s.setOnClickListener(this);
        report_btn_f.setOnClickListener(this);
        report_tv_sq.setOnClickListener(this);

        report_rv = findViewById(R.id.report_rv);


        area1 = findViewById(R.id.report_area_1);
        area2 = findViewById(R.id.report_area_2);
        area3 = findViewById(R.id.report_area_3);
        area4 = findViewById(R.id.report_area_4);
        area5 = findViewById(R.id.report_area_5);
        area6 = findViewById(R.id.report_area_6);
        area7 = findViewById(R.id.report_area_7);
        area8 = findViewById(R.id.report_area_8);

        type1 = findViewById(R.id.report_type_1);
        type2 = findViewById(R.id.report_type_2);
        type3 = findViewById(R.id.report_type_3);
        type4 = findViewById(R.id.report_type_4);
        type5 = findViewById(R.id.report_type_5);

        goal1 = findViewById(R.id.report_goal_1);
        goal2 = findViewById(R.id.report_goal_2);
        goal3 = findViewById(R.id.report_goal_3);
        goal4 = findViewById(R.id.report_goal_4);
        goal5 = findViewById(R.id.report_goal_5);
        goal6 = findViewById(R.id.report_goal_6);
        goal7 = findViewById(R.id.report_goal_7);
        goal8 = findViewById(R.id.report_goal_8);

        ensure = findViewById(R.id.report_ensure);

        if (FinalContents.isChecked()) {
            client_name.setText(FinalContents.getClientName());
        } else {
            project_name.setText("");
        }
        if (FinalContents.isChecked2()) {
            project_name.setText(FinalContents.getProjectName());
        } else {
            project_name.setText("");
        }

        Log.i("报备跳转","数据zhon："+FinalContents.getClientName());
        Log.i("报备跳转","数据zhon："+FinalContents.getCustomerID());

        area1.setOnClickListener(this);
        area2.setOnClickListener(this);
        area3.setOnClickListener(this);
        area4.setOnClickListener(this);
        area5.setOnClickListener(this);
        area6.setOnClickListener(this);
        area7.setOnClickListener(this);
        area8.setOnClickListener(this);
        type1.setOnClickListener(this);
        type2.setOnClickListener(this);
        type3.setOnClickListener(this);
        type4.setOnClickListener(this);
        type5.setOnClickListener(this);
        goal1.setOnClickListener(this);
        goal2.setOnClickListener(this);
        goal3.setOnClickListener(this);
        goal4.setOnClickListener(this);
        goal5.setOnClickListener(this);
        goal6.setOnClickListener(this);
        goal7.setOnClickListener(this);
        goal8.setOnClickListener(this);
        ensure.setOnClickListener(this);


    }

    private void initData(){
        areaMap.put(0,"");
        areaMap.put(1,"");
        areaMap.put(2,"");
        areaMap.put(3,"");
        areaMap.put(4,"");
        areaMap.put(5,"");
        areaMap.put(6,"");
        areaMap.put(7,"");

        traitMap.put(0,"");
        traitMap.put(1,"");
        traitMap.put(2,"");
        traitMap.put(3,"");
        traitMap.put(4,"");
        traitMap.put(5,"");
        traitMap.put(6,"");
        traitMap.put(7,"");

        typeMap.put(0,"");
        typeMap.put(1,"");
        typeMap.put(2,"");
        typeMap.put(3,"");
        typeMap.put(4,"");

        goalMap.put(0,"");
        goalMap.put(1,"");
        goalMap.put(2,"");
        goalMap.put(3,"");
        goalMap.put(4,"");
        goalMap.put(5,"");
        goalMap.put(6,"");
        goalMap.put(7,"");

    }

    private void initDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        report_start.setText("<"+string);
        report_end.setText("-"+string+" >");
        dateTimePickerView.setStartDate(Calendar.getInstance());
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));
        dateTimePickerView.setEndDate(new GregorianCalendar(year+3, month, dayOfMonth));


        report_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.GONE);
            }
        });

        report_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.GONE);
            }
        });

        report_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        timeStart = dateString;
                        report_start.setText("<"+dateString);
                    }
                });
            }
        });
        report_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        timeEnd = dateString;
                        report_end.setText("-"+dateString+" >");
                        Log.d("wsw", "new date: " + dateString);
                    }
                });
            }
        });
    }

    private void initDataReport(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReportBean> userMessage = fzbInterface.getReportBean(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReportBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReportBean reportBean) {
                        areaSectionListBean = reportBean.getData().getAreaSectionList();
                        productTypeListBeans = reportBean.getData().getProductTypeList();
                        projectLabelListBeans = reportBean.getData().getProjectLabelList();
                        purposeListBeans = reportBean.getData().getPurposeList();
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(ReportActivity.this,4);
                        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        report_rv.setLayoutManager(gridLayoutManager);
                        ReportItemAdapter reportItemAdapter = new ReportItemAdapter(projectLabelListBeans);
                        report_rv.setAdapter(reportItemAdapter);
                        reportItemAdapter.notifyDataSetChanged();
                        reportItemAdapter.setOnItemClickListener(new ReportItemAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(CheckBox checkBox, int postion) {
                                if (checkBox.isChecked()) {
                                    traitMap.put(postion,","+ projectLabelListBeans.get(postion).getId());
                                    Log.i("楼盘特色","来来来："+projectLabelListBeans.get(postion).getId()+"名字:"+projectLabelListBeans.get(postion).getLable());
                                }else {
                                    traitMap.put(postion,"");
                                }

                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wsw","返回的数据"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.report_btn_yes:
                isIsland = "1";
                report_tv_timer.setVisibility(View.VISIBLE);
                break;
            case R.id.report_btn_no:
                isIsland = "0";
                report_tv_timer.setVisibility(View.GONE);
                break;
            case R.id.report_tv_sq:
                if(report_ll_start_sq.getVisibility() == View.VISIBLE){
                    report_tv_sq.setText("展开");
                    report_ll_start_sq.setVisibility(View.GONE);
                    report_ll_start_sq1.setVisibility(View.GONE);
                    report_ll_start_sq2.setVisibility(View.GONE);
                }else if (report_ll_start_sq.getVisibility() == View.GONE){
                    report_tv_sq.setText("收起");
                    report_ll_start_sq.setVisibility(View.VISIBLE);
                    report_ll_start_sq1.setVisibility(View.VISIBLE);
                    report_ll_start_sq2.setVisibility(View.VISIBLE);
                }
                break;
            //面积
            case R.id.report_area_1:
                if (area1.isChecked()) {
                    areaMap.put(0,",-50");
                }else {
                    areaMap.put(0,"");
                }
                break;
            case R.id.report_area_2:
                if (area2.isChecked()) {
                    areaMap.put(1,",50-70");
                }else {
                    areaMap.put(1,"");
                }
                break;
            case R.id.report_area_3:
                if (area3.isChecked()) {
                    areaMap.put(2,",70-90");
                }else {
                    areaMap.put(2,"");
                }
                break;
            case R.id.report_area_4:
                if (area4.isChecked()) {
                    areaMap.put(3,",90-110");
                }else {
                    areaMap.put(3,"");
                }
                break;
            case R.id.report_area_5:
                if (area5.isChecked()) {
                    areaMap.put(4,",110-130");
                }else {
                    areaMap.put(4,"");
                }
                break;
            case R.id.report_area_6:
                if (area6.isChecked()) {
                    areaMap.put(5,",130-150");
                }else {
                    areaMap.put(5,"");
                }
                break;
            case R.id.report_area_7:
                if (area7.isChecked()) {
                    areaMap.put(6,",150-200");
                }else {
                    areaMap.put(6,"");
                }
                break;
            case R.id.report_area_8:
                if (area8.isChecked()) {
                    areaMap.put(7,",200-");
                }else {
                    areaMap.put(7,"");
                }
                break;
            //类型
            case R.id.report_type_1:
                if (type1.isChecked()) {
                    typeMap.put(0,",1");
                }else {
                    typeMap.put(0,"");
                }

                break;
            case R.id.report_type_2:
                if (type2.isChecked()) {
                    typeMap.put(1,",2");
                }else {
                    typeMap.put(1,"");
                }

                break;
            case R.id.report_type_3:
                if (type3.isChecked()) {
                    typeMap.put(2,",3");
                }else {
                    typeMap.put(2,"");
                }

                break;
            case R.id.report_type_4:
                if (type4.isChecked()) {
                    typeMap.put(3,",4");
                }else {
                    typeMap.put(3,"");
                }

                break;
            case R.id.report_type_5:
                if (type5.isChecked()) {
                    typeMap.put(4,",5");
                }else {
                    typeMap.put(4,"");
                }

                break;
            //目的
            case R.id.report_goal_1:
                if (goal1.isChecked()) {
                    goalMap.put(0,",0");
                }else {
                    goalMap.put(0,"");
                }

                break;
            case R.id.report_goal_2:
                if (goal2.isChecked()) {
                    goalMap.put(1,",1");
                }else {
                    goalMap.put(1,"");
                }

                break;
            case R.id.report_goal_3:
                if (goal3.isChecked()) {
                    goalMap.put(2,",2");
                }else {
                    goalMap.put(2,"");
                }

                break;
            case R.id.report_goal_4:
                if (goal4.isChecked()) {
                    goalMap.put(3,",3");
                }else {
                    goalMap.put(3,"");
                }

                break;
            case R.id.report_goal_5:
                if (goal5.isChecked()) {
                    goalMap.put(4,",4");
                }else {
                    goalMap.put(4,"");
                }

                break;
            case R.id.report_goal_6:
                if (goal6.isChecked()) {
                    goalMap.put(5,",5");
                }else {
                    goalMap.put(5,"");
                }

                break;
            case R.id.report_goal_7:
                if (goal7.isChecked()) {
                    goalMap.put(6,",6");
                }else {
                    goalMap.put(6,"");
                }

                break;
            case R.id.report_goal_8:
                if (goal8.isChecked()) {
                    goalMap.put(7,",7");
                }else {
                    goalMap.put(7,"");
                }
                break;
            case R.id.report_client_name:
                Intent clientIntent = new Intent(ReportActivity.this,MyClientActivity.class);
                FinalContents.setNUM("1");
                startActivity(clientIntent);
                finish();
                FinalContents.setChecked(true);
                break;
            case R.id.report_project_name:
                Intent projectIntent = new Intent(ReportActivity.this,SearchInterfaceActivity.class);
                FinalContents.setProject("1");
                startActivity(projectIntent);
                finish();
                FinalContents.setChecked2(true);
                break;
            case R.id.report_ensure:
                for (int i = 0;i < areaMap.size();i++){
                    areaSection = areaSection + areaMap.get(i);
                }
                for (int i = 0;i < traitMap.size();i++){
                    ffProjectTrait = ffProjectTrait + traitMap.get(i);
                }
                for (int i = 0;i < typeMap.size();i++){
                    procuctType = procuctType + typeMap.get(i);
                }
                for (int i = 0;i < goalMap.size();i++){
                    fitmentState = fitmentState + goalMap.get(i);
                }

                if (areaSection.equals("") || ffProjectTrait.equals("") || procuctType.equals("") || fitmentState.equals("")) {

                } else {
                    areaSection = areaSection.substring(1,areaSection.length());
                    ffProjectTrait = ffProjectTrait.substring(1,ffProjectTrait.length());
                    procuctType = procuctType.substring(1,procuctType.length());
                    fitmentState = fitmentState.substring(1,fitmentState.length());
                }

                eventUrl = url+ "&areaSection="+areaSection+"&ffProjectTrait="+ffProjectTrait+"&procuctType="+procuctType+"&fitmentState="+fitmentState;

                Log.i("楼盘特色","数据："+ffProjectTrait);

                if (IDcard.getText().length() == 18) {
                    initReport();
                } else if (IDcard.getText().length() == 0){
                    Toast.makeText(ReportActivity.this, "请输入身份证号码", Toast.LENGTH_SHORT).show();
                } else if (IDcard.getText().length() < 18) {
                    Toast.makeText(ReportActivity.this, "请输入正确的身份证号码", Toast.LENGTH_SHORT).show();
                }

                areaSection = "";
                ffProjectTrait = "";
                procuctType = "";
                fitmentState = "";

                break;
        }
    }

    private void initReport(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ChangePhoneBean> userMessage = fzbInterface.getReportPostBean(FinalContents.getCustomerID(),procuctType,areaSection,fitmentState,ffProjectTrait,price_start.getText().toString(),price_end.getText().toString(),FinalContents.getProjectID(),"",FinalContents.getGuideRuleId(),isIsland,IDcard.getText().toString(),report_remarks.getText().toString(),timeStart,timeEnd,FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangePhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangePhoneBean changePhoneBean) {
                        Log.i("报备",""+FinalContents.getProjectID());
                        Toast.makeText(ReportActivity.this, changePhoneBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                        FinalContents.setClientName("");
                        FinalContents.setCustomerID("");
                        FinalContents.setProjectName("");
                        FinalContents.setProjectSearchID("");
                        FinalContents.setGuideRuleId("");
                        FinalContents.setProjectID("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ReportActivity.this, "您输入的信息有误", Toast.LENGTH_SHORT).show();
                        Log.i("wsw","返回的数据"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);

            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
