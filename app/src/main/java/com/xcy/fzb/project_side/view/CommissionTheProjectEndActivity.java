package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.MoneyCountBean;
import com.xcy.fzb.all.modle.ReceivableBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.KeyUtils;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_side.adapter.TheProjectEndCommissionAdapter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;

public class CommissionTheProjectEndActivity extends AllActivity implements View.OnClickListener {


    RelativeLayout commission_the_project_end_return;
    ImageView commission_the_project_end_screen;
    EditText commission_the_project_end_et;

    TextView commission_the_project_end_tv1;
    TextView commission_the_project_end_tv2;
    TextView commission_the_project_end_tv3;
    TextView commission_the_project_end_tv4;

    LinearLayout commission_the_project_end_l1;
    LinearLayout commission_the_project_end_l2;
    LinearLayout commission_the_project_end_l3;
    LinearLayout commission_the_project_end_l4;

    RelativeLayout commission_the_project_end_rl;
    RecyclerView commission_the_project_end_rv;

    private TheProjectEndCommissionAdapter adapter;

    String search = "";
    String status = "";
    String projectType = "3";
    String projectId = "";
    String startTime = "";
    String endTime = "";

    private LinearLayout mRlRight;
    private LinearLayout drawer_linear;
    private LinearLayout drawer_picker;
    private DrawerLayout mDlMain;

    RadioButton drawer_time_all;
    RadioButton drawer_time_custom;
    RadioButton drawer_status_all;
    RadioButton drawer_status_custom;
    RadioButton drawer_status_back;
    RadioButton drawer_status_settle;

    TextView drawer_start_time;
    TextView drawer_end_time;
    TextView drawer_reset;
    TextView drawer_ensure;

    TextView drawer_picker_cancel;
    TextView drawer_picker_ensure;

    DateTimePickerView drawer_pickerView;
    private PtrClassicFrameLayout project_side_commission_the_project_end_ptrclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_commission_the_project_end);
        init_No_Network();

    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
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

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        project_side_commission_the_project_end_ptrclass = findViewById(R.id.project_side_commission_the_project_end_ptrclass);

        commission_the_project_end_return = findViewById(R.id.commission_the_project_end_return);
        commission_the_project_end_screen = findViewById(R.id.commission_the_project_end_screen);
        commission_the_project_end_et = findViewById(R.id.commission_the_project_end_et);
        commission_the_project_end_tv1 = findViewById(R.id.commission_the_project_end_tv1);
        commission_the_project_end_tv2 = findViewById(R.id.commission_the_project_end_tv2);
        commission_the_project_end_tv3 = findViewById(R.id.commission_the_project_end_tv3);
        commission_the_project_end_tv4 = findViewById(R.id.commission_the_project_end_tv4);
        commission_the_project_end_l1 = findViewById(R.id.commission_the_project_end_l1);
        commission_the_project_end_l2 = findViewById(R.id.commission_the_project_end_l2);
        commission_the_project_end_l3 = findViewById(R.id.commission_the_project_end_l3);
        commission_the_project_end_l4 = findViewById(R.id.commission_the_project_end_l4);
        commission_the_project_end_rl = findViewById(R.id.commission_the_project_end_rl);
        commission_the_project_end_rv = findViewById(R.id.commission_the_project_end_rv);

        project_side_commission_the_project_end_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        project_side_commission_the_project_end_ptrclass.refreshComplete();
                        project_side_commission_the_project_end_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        commission_the_project_end_et.setText("");
                        search = commission_the_project_end_et.getText().toString();
                        initData();
                        initDataBean();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });


        // TODO 侧滑菜单
        mDlMain = findViewById(R.id.mDlMain);
        mRlRight = findViewById(R.id.rl_right);
        drawer_linear = findViewById(R.id.drawer_linear);

        drawer_time_all = findViewById(R.id.drawer_time_all);
        drawer_time_custom = findViewById(R.id.drawer_time_custom);
        drawer_status_all = findViewById(R.id.drawer_status_all);
        drawer_status_custom = findViewById(R.id.drawer_status_custom);
        drawer_status_back = findViewById(R.id.drawer_status_back);
        drawer_status_settle = findViewById(R.id.drawer_status_settle);

        drawer_start_time = findViewById(R.id.drawer_start_time);
        drawer_end_time = findViewById(R.id.drawer_end_time);
        drawer_reset = findViewById(R.id.drawer_reset);
        drawer_ensure = findViewById(R.id.drawer_ensure);

        drawer_picker = findViewById(R.id.drawer_picker);
        drawer_picker_cancel = findViewById(R.id.drawer_picker_cancel);
        drawer_picker_ensure = findViewById(R.id.drawer_picker_ensure);
        drawer_pickerView = findViewById(R.id.drawer_pickerView);

        initData();
        initDataBean();

        drawer_time_all.setChecked(true);
        drawer_time_all.setOnClickListener(this);
        drawer_time_custom.setOnClickListener(this);
        drawer_status_all.setOnClickListener(this);
        drawer_status_custom.setOnClickListener(this);
        drawer_status_back.setOnClickListener(this);
        drawer_status_settle.setOnClickListener(this);

        drawer_start_time.setOnClickListener(this);
        drawer_end_time.setOnClickListener(this);
        drawer_reset.setOnClickListener(this);
        drawer_ensure.setOnClickListener(this);

        commission_the_project_end_return.setOnClickListener(this);
        commission_the_project_end_screen.setOnClickListener(this);
        commission_the_project_end_l1.setOnClickListener(this);
        commission_the_project_end_l3.setOnClickListener(this);

        commission_the_project_end_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //如果actionId是搜索的id，则进行下一步的操作
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    // 当按了搜索之后关闭软键盘
                    KeyUtils.hideKeyboard(commission_the_project_end_et);
                    search = commission_the_project_end_et.getText().toString();
                    initDataBean();
                }
                return false;    
            }
        });
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        drawer_start_time.setText("<"+string);
        drawer_end_time.setText("-"+string+" >");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.commission_the_project_end_return:
                finish();
                break;
//                TODO 右边菜单栏
            case R.id.commission_the_project_end_screen:
                mDlMain.openDrawer(Gravity.RIGHT);
                break;
//                TODO 旅居房产
            case R.id.commission_the_project_end_l1:

                commission_the_project_end_l2.setVisibility(View.VISIBLE);
                commission_the_project_end_l4.setVisibility(View.GONE);
                projectType = "3";
                initDataBean();

                break;
//                TODO 海外房产
            case R.id.commission_the_project_end_l3:

                commission_the_project_end_l2.setVisibility(View.GONE);
                commission_the_project_end_l4.setVisibility(View.VISIBLE);
                projectType = "2";
                initDataBean();

                break;
            //   TODO 时间选择  全部
            case R.id.drawer_time_all:
                drawer_linear.setVisibility(View.GONE);
                break;
            //   TODO 时间选择  自定义
            case R.id.drawer_time_custom:
                drawer_linear.setVisibility(View.VISIBLE);
                break;
            //   TODO 状态选择  全部
            case R.id.drawer_status_all:
                status = "";
                break;
            //   TODO 状态选择  未结
            case R.id.drawer_status_custom:
                status = "0";
                break;
            //   TODO 状态选择  退还
            case R.id.drawer_status_back:
                status = "2";
                break;
            //   TODO 状态选择  结清
            case R.id.drawer_status_settle:
                status = "1";
                break;
            //   TODO 时间选择  自定义 开始时间
            case R.id.drawer_start_time:
                initDate();
                break;
            //   TODO 时间选择  自定义 结束时间
            case R.id.drawer_end_time:
                initDate();
                break;
            //   TODO 重置
            case R.id.drawer_reset:
                status = "";
                startTime = "";
                endTime = "";
                drawer_status_all.setChecked(true);
                drawer_time_all.setChecked(true);
                drawer_linear.setVisibility(View.GONE);
                break;
            //   TODO 确定
            case R.id.drawer_ensure:
                mDlMain.closeDrawer(Gravity.RIGHT);
                initDataBean();
                break;
        }

    }

    //TODO 我的佣金侧滑菜单时间赋值
    private void initDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        drawer_start_time.setText("<"+string);
        drawer_end_time.setText("-"+string+" >");
        drawer_pickerView.setStartDate(Calendar.getInstance());
        startTime = drawer_start_time.getText().toString();
        endTime = drawer_end_time.getText().toString();
        // 注意：月份是从0开始计数的
        drawer_pickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));

        drawer_picker_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_picker.setVisibility(View.GONE);
            }
        });

        drawer_picker_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_picker.setVisibility(View.GONE);
            }
        });

        //            TODO 开始时间
        drawer_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_picker.setVisibility(View.VISIBLE);
                drawer_pickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        startTime = dateString;
                        drawer_start_time.setText("<"+dateString);
                    }
                });
            }
        });
        //                TODO 结束时间
        drawer_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_picker.setVisibility(View.VISIBLE);
                drawer_pickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        endTime = dateString;
                        drawer_end_time.setText("-"+dateString+" >");
                    }
                });
            }
        });
    }

    //    TODO 我的佣金上半部分数据填充
    private void initData() {

        String ProjectID = "";

        if (FinalContents.getDetails().equals("项目详情")) {
            ProjectID = FinalContents.getProjectID();
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MoneyCountBean> userMessage = fzbInterface.getMoneyCountList(FinalContents.getUserID(),ProjectID);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoneyCountBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MoneyCountBean moneyCountBean) {
                        commission_the_project_end_tv1.setText(moneyCountBean.getData().getMoneyData().getReceivableMoney());
                        commission_the_project_end_tv2.setText(moneyCountBean.getData().getMoneyData().getBackMoney());
                        commission_the_project_end_tv3.setText(moneyCountBean.getData().getMoneyData().getInvoiceMoney());
                        commission_the_project_end_tv4.setText(moneyCountBean.getData().getMoneyData().getSurplusMoney());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 我的佣金下半部分数据填充
    private void initDataBean() {
        String ProjectID = "";

        if (FinalContents.getDetails().equals("项目详情")) {
            ProjectID = FinalContents.getProjectID();
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReceivableBean> userMessage = fzbInterface.getMoneyList(FinalContents.getUserID(),search,status,projectType,ProjectID,startTime,endTime,"1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReceivableBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ReceivableBean receivableBean) {
                        if (receivableBean.getCode().equals("1")) {
                            if (receivableBean.getData() != null) {
                                ReceivableBean.DataBean receivableBeanData = receivableBean.getData();
                                List<ReceivableBean.DataBean.RowsBean> rowsBeanList = receivableBeanData.getRows();
                                if (rowsBeanList.size() != 0) {
                                    commission_the_project_end_rl.setVisibility(View.GONE);
                                    //在此处修改布局排列方向
                                    commission_the_project_end_rv.setVisibility(View.VISIBLE);
                                    MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(CommissionTheProjectEndActivity.this);
                                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                    commission_the_project_end_rv.setLayoutManager(layoutManager);
                                    TheProjectEndCommissionAdapter recyclerAdapter = new TheProjectEndCommissionAdapter(rowsBeanList);
                                    commission_the_project_end_rv.setAdapter(recyclerAdapter);
                                    recyclerAdapter.notifyDataSetChanged();
                                }else {
                                    commission_the_project_end_rl.setVisibility(View.VISIBLE);
                                    commission_the_project_end_rv.setVisibility(View.GONE);
                                }

                            }else {
                                commission_the_project_end_rv.setVisibility(View.GONE);
                                commission_the_project_end_rl.setVisibility(View.VISIBLE);
                            }
                        }else {
                            commission_the_project_end_rv.setVisibility(View.GONE);
                            commission_the_project_end_rl.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        commission_the_project_end_rv.setVisibility(View.GONE);
                        commission_the_project_end_rl.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
