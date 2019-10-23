package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.PopAdapter;
import com.xcy.fzb.captain_team.adapter.TheProjectEndCommissionAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.CommissionListBean;
import com.xcy.fzb.captain_team.database.TeamCommissionsBean;
import com.xcy.fzb.captain_team.persente.KeyUtils;
import com.xcy.fzb.captain_team.persente.MyLinearLayoutManager;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommissionTheProjectEndActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView commission_the_project_end_return;
    ImageView commission_the_project_end_screen;
    EditText commission_the_project_end_et;

    TextView commission_the_project_end_tv2;
    TextView commission_the_project_end_tv3;
    TextView commission_the_project_end_tv4;
    TextView commission_the_project_end_all;

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
    private PopupWindow p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_the_project_end);

        initView();
        initData();
        initDataBean();
    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        commission_the_project_end_return = findViewById(R.id.commission_the_project_end_return);
        commission_the_project_end_screen = findViewById(R.id.commission_the_project_end_screen);
        commission_the_project_end_et = findViewById(R.id.commission_the_project_end_et);
        commission_the_project_end_tv2 = findViewById(R.id.commission_the_project_end_tv2);
        commission_the_project_end_tv3 = findViewById(R.id.commission_the_project_end_tv3);
        commission_the_project_end_tv4 = findViewById(R.id.commission_the_project_end_tv4);
        commission_the_project_end_l1 = findViewById(R.id.commission_the_project_end_l1);
        commission_the_project_end_l2 = findViewById(R.id.commission_the_project_end_l2);
        commission_the_project_end_l3 = findViewById(R.id.commission_the_project_end_l3);
        commission_the_project_end_l4 = findViewById(R.id.commission_the_project_end_l4);
        commission_the_project_end_rl = findViewById(R.id.commission_the_project_end_rl);
        commission_the_project_end_rv = findViewById(R.id.commission_the_project_end_rv);
        commission_the_project_end_all = findViewById(R.id.commission_the_project_end_all);

        initData();
        initDataBean();


        commission_the_project_end_return.setOnClickListener(this);
        commission_the_project_end_screen.setOnClickListener(this);
        commission_the_project_end_l1.setOnClickListener(this);
        commission_the_project_end_l3.setOnClickListener(this);
        commission_the_project_end_all.setOnClickListener(this);

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

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.commission_the_project_end_return:
                finish();
                break;
//                TODO 佣金设置
            case R.id.commission_the_project_end_screen:
                Intent intent = new Intent(CommissionTheProjectEndActivity.this,CommissionLevelActivity.class);
                startActivity(intent);
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
//                TODO 佣金状态
            case R.id.commission_the_project_end_all:
                PopWindow();
                break;
        }

    }

    private void PopWindow(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.item_popup, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        p = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        p.setTouchable(true);
        p.setFocusable(true);
        p.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));
        p.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOff;
        int buttonWidth = commission_the_project_end_all.getWidth();
        int popupwindowWidth = p.getContentView().getMeasuredWidth();
        xOff = buttonWidth - popupwindowWidth;
        p.showAsDropDown(commission_the_project_end_all,xOff,0);

        p.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }

    // TODO 填写数据
    private void handleListView(View contentView) {

        RecyclerView recyclerView = contentView.findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("只看未结清");
        list.add("只看已结清");
        list.add("只看自己");
        list.add("只看调单");
        list.add("只看退单");

        PopAdapter popAdapter = new PopAdapter(list);
        recyclerView.setAdapter(popAdapter);

        popAdapter.setOnItemClickListener(new PopAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {
                if (postion == 0) {
                    status = "";
                } else if (postion == 1) {
                    status = "1";
                } else if (postion == 2) {
                    status = "2";
                } else if (postion == 3) {
                    status = "3";
                } else if (postion == 4) {
                    status = "4";
                } else if (postion == 5) {
                    status = "5";
                }
                commission_the_project_end_all.setText(list.get(postion));
                initDataBean();
                p.dismiss();
            }
        });

    }

    //    TODO 我的佣金上半部分数据填充
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamCommissionsBean> userMessage = fzbInterface.getTeamCommissions(FinalContents.getUserID(),FinalContents.getUserID(),"0","","");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamCommissionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(TeamCommissionsBean teamCommissionsBean) {
                        commission_the_project_end_tv2.setText(teamCommissionsBean.getData().getTotalAmount());
                        commission_the_project_end_tv3.setText(teamCommissionsBean.getData().getAlreadyAmount());
                        commission_the_project_end_tv4.setText(teamCommissionsBean.getData().getNotAmount());
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
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CommissionListBean> userMessage = fzbInterface.getCommissionList(FinalContents.getUserID(),projectType,search,status);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(CommissionListBean commissionListBean) {
                        if (commissionListBean.getCode().equals("1")) {
                            if (commissionListBean.getData() != null) {
                                CommissionListBean.DataBean commissionListBeanData = commissionListBean.getData();
                                List<CommissionListBean.DataBean.RowsBean> rowsBeanList = commissionListBeanData.getRows();
                                //在此处修改布局排列方向
                                commission_the_project_end_rv.setVisibility(View.VISIBLE);
                                MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(CommissionTheProjectEndActivity.this);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                commission_the_project_end_rv.setLayoutManager(layoutManager);
                                TheProjectEndCommissionAdapter recyclerAdapter = new TheProjectEndCommissionAdapter(rowsBeanList);
                                commission_the_project_end_rv.setAdapter(recyclerAdapter);
                                recyclerAdapter.notifyDataSetChanged();
                            }else {
                                commission_the_project_end_rv.setVisibility(View.GONE);
                            }
                        }else {
                            commission_the_project_end_rv.setVisibility(View.GONE);
                        }
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

}
