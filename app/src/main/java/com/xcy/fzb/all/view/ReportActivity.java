package com.xcy.fzb.all.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tuacy.azlist.LettersComparator;
import com.tuacy.fuzzysearchlibrary.PinyinUtil;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.FuzzySearchAdapter;
import com.xcy.fzb.all.adapter.ReportItemAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.ChangePhoneBean;
import com.xcy.fzb.all.modle.ClientBean;
import com.xcy.fzb.all.modle.IdNumberBean;
import com.xcy.fzb.all.modle.ItemEntity;
import com.xcy.fzb.all.modle.ReportBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

    private ImageView report_client_name_img;
    private EditText report_client_name_et;
    private TextView project_name;

    private String areaSection = "";
    private String ffProjectTrait = "";
    private String procuctType = "";
    private String fitmentState = "";

    private String url = FinalContents.getImageUrl() + "/fangfang/app/v1/commonSelect/projectList?"+ "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID();
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

    int year1 = 0;
    int month1 = 0;
    int dayOfMonth1 = 0;

    int ifnum1 = 0;
    private LinearLayout report_linear;
    boolean blean = false;
    private EditText report_client_phone;
    private FuzzySearchAdapter fuzzySearchAdapter;
    private RecyclerView report_associating_inputing_rv;
    private RelativeLayout report_relative;
    private List<ClientBean.DataBean> list;
    private String customerID = "";
    private LinearLayout report_nosearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initDataReport();

            initData();
            initView();
            initDate();

        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            Toast.makeText(this, "当前无网络，请检查网络后再进行登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(){

        StatusBar.makeStatusBarTransparent(this);
        report_linear = findViewById(R.id.report_linear);

        report_nosearch = findViewById(R.id.report_nosearch);
        report_associating_inputing_rv = findViewById(R.id.report_associating_inputing_rv);
        report_relative = findViewById(R.id.report_relative);

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
        report_client_name_img = findViewById(R.id.report_client_name_img);
        report_client_name_et = findViewById(R.id.report_client_name_et);
        report_client_phone = findViewById(R.id.report_client_phone);
        project_name = findViewById(R.id.report_project_name);
        report_client_name_img.setOnClickListener(this);
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


        report_client_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override

            public void afterTextChanged(Editable editable) {
                //长度发生变化，监听到输入的长度为 editText.getText().length()
                if (report_client_name_et.getText().toString().length() > 0) {
                    report_associating_inputing_rv.setVisibility(View.VISIBLE);
                }else {
                    report_associating_inputing_rv.setVisibility(View.GONE);
                }
            }
        });

        report_client_name_et.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {

                    // 获得焦点

                } else {

                    // 失去焦点
                    report_associating_inputing_rv.setVisibility(View.GONE);
                }

            }


        });

        report_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_associating_inputing_rv.setVisibility(View.GONE);
            }
        });

        report_nosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_associating_inputing_rv.setVisibility(View.GONE);
            }
        });

        report_client_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_associating_inputing_rv.setVisibility(View.GONE);
            }
        });

        if (FinalContents.isChecked()) {
            report_client_name_et.setText(FinalContents.getClientName());
            report_client_phone.setText(FinalContents.getClientPhone());
        } else {
            project_name.setText("");
        }
        if (FinalContents.isChecked2()) {
            project_name.setText(FinalContents.getProjectName());
        } else {
            project_name.setText("");
        }

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

        initAssociatingInputing();
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
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH)+1;
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        String string1 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth + 1);

        report_start.setText("<"+string);
        report_end.setText("-"+string1+" >");
        dateTimePickerView.setStartDate(Calendar.getInstance());
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));
        dateTimePickerView.setEndDate(new GregorianCalendar(year+3, month, dayOfMonth));


        report_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.GONE);
                Log.d("判断", "判断: " + blean);
                if (blean) {
                    initDate2();
                }
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
                dateTimePickerView.setStartDate(Calendar.getInstance());
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                         year1 = date.get(Calendar.YEAR);
                         month1 = date.get(Calendar.MONTH);
                         dayOfMonth1 = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year1, month1 + 1, dayOfMonth1);
                        timeStart = dateString;
                        report_start.setText("<"+dateString);
                    }
                });
                blean = true;
            }
        });

    }

    private void initDate2(){

        report_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.VISIBLE);
                Log.d("判断", "n 判断: " + blean);
                dateTimePickerView.setStartDate(new GregorianCalendar(year1, month1, dayOfMonth1));
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
                blean = false;
                Log.d("判断", "new 判断: " + blean);
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

                        for (int i = 0;i < projectLabelListBeans.size();i++){
                            traitMap.put(i,"");
                        }

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
                                    Log.i("楼盘特色","来来来："+traitMap.get(postion)+"名字:"+projectLabelListBeans.get(postion).getLable());
                                }else {
                                    traitMap.put(postion,"");
                                }
                                Log.i("楼盘特色","数据："+traitMap.get(postion));
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

    private void initIdNumber(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<IdNumberBean> userMessage = fzbInterface.getIdNumber(FinalContents.getProjectID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IdNumberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IdNumberBean idNumberBean) {
                        if (idNumberBean.getData().getIsPapers() == 0) {
                            report_linear.setVisibility(View.GONE);
                        }else if (idNumberBean.getData().getIsPapers() == 1){
                            report_linear.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wsw","报备的数据"+e.getMessage());
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
            case R.id.report_client_name_img:
                Intent clientIntent = new Intent(ReportActivity.this,MyClientActivity.class);
                FinalContents.setNUM("1");
                startActivity(clientIntent);
                FinalContents.setChecked(true);
                break;
            case R.id.report_project_name:
                Intent projectIntent = new Intent(ReportActivity.this,SearchInterfaceActivity.class);
                FinalContents.setProject("1");
                startActivity(projectIntent);
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

                if (report_linear.getVisibility() == View.VISIBLE) {
                    if (IDcard.getText().length() == 18) {
                        initReport();
                    } else if (IDcard.getText().length() == 0){
                        Toast.makeText(ReportActivity.this, "请输入身份证号码", Toast.LENGTH_SHORT).show();
                    } else if (IDcard.getText().length() < 18) {
                        Toast.makeText(ReportActivity.this, "请输入正确的身份证号码", Toast.LENGTH_SHORT).show();
                    }
                }else if (report_linear.getVisibility() == View.GONE){
                    initReport();
                }

                areaSection = "";
                ffProjectTrait = "";
                procuctType = "";
                fitmentState = "";

                break;
        }
    }

    private void initAssociatingInputing(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientBean> client = fzbInterface.getClient("", FinalContents.getUserID(), "1000");
        client.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientBean clientBean) {
                        list = clientBean.getData();
                        if (list.size() != 0) {
                            initSearch();
                        }else {
                            report_associating_inputing_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "客户列表错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initSearch(){
        List<ItemEntity> dateList = fillData(list);
        Collections.sort(dateList, new LettersComparator<ItemEntity>());
        report_associating_inputing_rv.setLayoutManager(new LinearLayoutManager(ReportActivity.this));
        fuzzySearchAdapter = new FuzzySearchAdapter(dateList);
        report_associating_inputing_rv.setAdapter(fuzzySearchAdapter);
        fuzzySearchAdapter.notifyDataSetChanged();

        report_client_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fuzzySearchAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fuzzySearchAdapter.setItemOnClick(new FuzzySearchAdapter.ItemOnClick() {
            @Override
            public void itemClick(int position, String phone) {
                Log.i("显示","模糊："+phone);
                for (int i = 0;i < list.size();i++){
                    if (phone.equals(list.get(i).getContactsPhone1())){
                        report_associating_inputing_rv.setVisibility(View.GONE);
                        FinalContents.setClientPhone(list.get(i).getContactsPhone1());
                        FinalContents.setClientName(list.get(i).getCustomerName());
                        FinalContents.setCustomerID(list.get(i).getId());
                        customerID = list.get(i).getId();
                        report_client_name_et.setText(FinalContents.getClientName());
                        report_client_phone.setText(FinalContents.getClientPhone());
                        break;
                    }
                }
                report_associating_inputing_rv.setVisibility(View.GONE);
            }
        });
    }

    private void initReport(){
        
        if (report_client_name_et.getText().toString().equals("")) {
            Toast.makeText(this, "请选择客户", Toast.LENGTH_SHORT).show();
            return;
        }

        if (project_name.getText().toString().equals("")) {
            Toast.makeText(this, "请选择项目", Toast.LENGTH_SHORT).show();
            return;
        }

        if (FinalContents.getClientPhone().equals(report_client_phone.getText().toString()) && FinalContents.getClientName().equals(report_client_name_et.getText().toString())) {
            customerID = FinalContents.getCustomerID();
        }else {
            customerID = "";
        }
        FinalContents.setCustomerID(customerID);
        FinalContents.setClientPhone(report_client_phone.getText().toString());
        FinalContents.setClientName(report_client_name_et.getText().toString());

        Log.i("报备","客户"+FinalContents.getCustomerID());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ChangePhoneBean> userMessage = fzbInterface.getReportPostBean(FinalContents.getCustomerID(),procuctType,areaSection,fitmentState,ffProjectTrait,price_start.getText().toString(),price_end.getText().toString(),FinalContents.getProjectID(),"",FinalContents.getGuideRuleId(),isIsland,IDcard.getText().toString(),report_remarks.getText().toString(),timeStart,timeEnd,FinalContents.getUserID(),FinalContents.getClientName(),FinalContents.getClientPhone());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangePhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangePhoneBean changePhoneBean) {
                        Log.i("报备","客户"+FinalContents.getCustomerID());
                        Log.i("报备",""+FinalContents.getProjectID());
                        Toast.makeText(ReportActivity.this, changePhoneBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                        FinalContents.setClientName("");
                        FinalContents.setClientPhone("");
                        FinalContents.setCustomerID("");
                        FinalContents.setProjectName("");
                        FinalContents.setProjectSearchID("");
                        FinalContents.setGuideRuleId("");
                        FinalContents.setProjectID("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ReportActivity.this, "请选择房源", Toast.LENGTH_SHORT).show();
                        Log.i("wsw","返回的数据"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<ItemEntity> fillData(List<ClientBean.DataBean> list) {
        List<ItemEntity> sortList = new ArrayList<>();
        for (ClientBean.DataBean item : list) {
            String letter;
            //汉字转换成拼音
            List<String> pinyinList = PinyinUtil.getPinYinList(item.getCustomerName()+"@"+item.getContactsPhone1());
            if (pinyinList != null && !pinyinList.isEmpty()) {
                // A-Z导航
                String letters = pinyinList.get(0).substring(0, 1).toUpperCase();
                // 正则表达式，判断首字母是否是英文字母
                if (letters.matches("[A-Z]")) {
                    letter = letters.toUpperCase();
                } else {
                    letter = "#";
                }
            } else {
                letter = "#";
            }
            sortList.add(new ItemEntity(item.getCustomerName()+"@"+item.getContactsPhone1(), letter, pinyinList));
        }
        return sortList;

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

    @Override
    protected void onRestart() {
        super.onRestart();
        if (FinalContents.isChecked()) {
            report_client_name_et.setText(FinalContents.getClientName());
            report_client_phone.setText(FinalContents.getClientPhone());
        } else {
            project_name.setText("");
        }
        if (FinalContents.isChecked2()) {
            project_name.setText(FinalContents.getProjectName());
        } else {
            project_name.setText("");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (FinalContents.getProjectName().equals("")) {

        }else {
            initIdNumber();
        }
    }
}
