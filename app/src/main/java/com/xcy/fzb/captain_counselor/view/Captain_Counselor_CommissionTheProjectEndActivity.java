package com.xcy.fzb.captain_counselor.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.database.CommissionListBean;
import com.xcy.fzb.all.persente.MyLinearLayoutManager;
import com.xcy.fzb.all.persente.SingleClick;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.KeyUtils;
import com.xcy.fzb.all.utils.ToastUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.captain_counselor.adapter.TheProjectEndCommissionAdapter;

import java.util.ArrayList;
import java.util.List;

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

public class Captain_Counselor_CommissionTheProjectEndActivity extends AllActivity implements View.OnClickListener {


    RelativeLayout commission_the_project_end_return;
    EditText commission_the_project_end_et;

    LinearLayout commission_the_project_end_l1;
    LinearLayout commission_the_project_end_l2;
    LinearLayout commission_the_project_end_l3;
    LinearLayout commission_the_project_end_l4;
    LinearLayout commission_the_project_end_l5;
    LinearLayout commission_the_project_end_l6;

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
    private PtrClassicFrameLayout captain_counselor_commission_the_project_end_ptrclass;
    private TextView commission_the_project_end_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_counselor_activity_commission_the_project_end);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
            initDataBean();
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
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        commission_the_project_end_return = findViewById(R.id.commission_the_project_end_return);
        commission_the_project_end_et = findViewById(R.id.commission_the_project_end_et);
        commission_the_project_end_l1 = findViewById(R.id.commission_the_project_end_l1);
        commission_the_project_end_l2 = findViewById(R.id.commission_the_project_end_l2);
        commission_the_project_end_l3 = findViewById(R.id.commission_the_project_end_l3);
        commission_the_project_end_l4 = findViewById(R.id.commission_the_project_end_l4);
        commission_the_project_end_l5 = findViewById(R.id.commission_the_project_end_l5);
        commission_the_project_end_l6 = findViewById(R.id.commission_the_project_end_l6);
        commission_the_project_end_rl = findViewById(R.id.commission_the_project_end_rl);
        commission_the_project_end_rv = findViewById(R.id.commission_the_project_end_rv);
        commission_the_project_end_select = findViewById(R.id.commission_the_project_end_select);

        initDataBean();

        captain_counselor_commission_the_project_end_ptrclass = findViewById(R.id.captain_counselor_commission_the_project_end_ptrclass);

        commission_the_project_end_return.setOnClickListener(this);
        commission_the_project_end_l1.setOnClickListener(this);
        commission_the_project_end_l3.setOnClickListener(this);
        commission_the_project_end_l5.setOnClickListener(this);
        commission_the_project_end_select.setOnClickListener(this);

        captain_counselor_commission_the_project_end_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        captain_counselor_commission_the_project_end_ptrclass.refreshComplete();
                        captain_counselor_commission_the_project_end_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        commission_the_project_end_et.setText("");
                        search = commission_the_project_end_et.getText().toString();
                        initDataBean();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

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

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.commission_the_project_end_return:
                finish();
                break;
//                TODO 城市房产
            case R.id.commission_the_project_end_l5:
                commission_the_project_end_l2.setVisibility(View.GONE);
                commission_the_project_end_l4.setVisibility(View.GONE);
                commission_the_project_end_l6.setVisibility(View.VISIBLE);
                projectType = "1";
                initDataBean();
                break;
//                TODO 旅居房产
            case R.id.commission_the_project_end_l1:

                commission_the_project_end_l2.setVisibility(View.VISIBLE);
                commission_the_project_end_l4.setVisibility(View.GONE);
                commission_the_project_end_l6.setVisibility(View.GONE);
                projectType = "3";
                initDataBean();

                break;
//                TODO 海外房产
            case R.id.commission_the_project_end_l3:

                commission_the_project_end_l2.setVisibility(View.GONE);
                commission_the_project_end_l6.setVisibility(View.GONE);
                commission_the_project_end_l4.setVisibility(View.VISIBLE);
                projectType = "2";
                initDataBean();
                break;
//            TODO 筛选条件
            case R.id.commission_the_project_end_select:
                final List<String> list = new ArrayList<>();
                list.add("全部");
                list.add("只看未结清");
                list.add("只看已结清");
                list.add("只看调单");
                list.add("只看退单");

                //      监听选中
                OptionsPickerView pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //               返回的分别是三个级别的选中位置
                        //              展示选中数据
                        if (list.get(options1).equals("全部")) {
                            commission_the_project_end_select.setText("全部");
                            status = "";
                            initDataBean();
                        } else if (list.get(options1).equals("只看未结清")) {
                            commission_the_project_end_select.setText("只看未结清");
                            status = "1";
                            initDataBean();
                        } else if (list.get(options1).equals("只看已结清")) {
                            commission_the_project_end_select.setText("只看已结清");
                            status = "2";
                            initDataBean();
                        } else if (list.get(options1).equals("只看调单")) {
                            commission_the_project_end_select.setText("只看调单");
                            status = "4";
                            initDataBean();
                        } else if (list.get(options1).equals("只看退单")) {
                            commission_the_project_end_select.setText("只看退单");
                            status = "5";
                            initDataBean();
                        }
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
        }

    }

    //TODO 我的佣金下半部分数据填充
    private void initDataBean() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CommissionListBean> userMessage = fzbInterface.getCommissionList(FinalContents.getUserID(),projectType,search,status,"1000", NewlyIncreased.getYJType(),NewlyIncreased.getYJstartDate(),NewlyIncreased.getYJendDate());
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
                                if (rowsBeanList.size() != 0) {
                                    commission_the_project_end_rl.setVisibility(View.GONE);
                                    //在此处修改布局排列方向
                                    commission_the_project_end_rv.setVisibility(View.VISIBLE);
                                    MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(Captain_Counselor_CommissionTheProjectEndActivity.this);
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
                        hideInput();
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
