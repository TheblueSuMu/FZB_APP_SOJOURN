package com.xcy.fzb.project_attache.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.MyFragmentPagerAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.database.DataNumBean;
import com.xcy.fzb.all.fragment.MyFragment1;
import com.xcy.fzb.all.fragment.MyFragment2;
import com.xcy.fzb.all.fragment.MyFragment3;
import com.xcy.fzb.all.modle.DBean;
import com.xcy.fzb.all.modle.TendentcyBean;
import com.xcy.fzb.all.persente.Fragnemt_SS;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.MyViewPager;
import com.xcy.fzb.project_attache.view.BrokersListActivity;
import com.xcy.fzb.project_attache.view.CommissionActivity;
import com.xcy.fzb.project_attache.view.StoreListActivity;
import com.xcy.fzb.project_side.view.MyClientActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;


public class DFragment extends Fragment implements View.OnClickListener, MyViewPager.OnSingleTouchListener, SwipeRefreshLayout.OnRefreshListener {

    LinearLayout ll1_modulebroker;
    LinearLayout ll2_modulebroker;
    LinearLayout ll3_modulebroker;
    LinearLayout ll4_modulebroker;
    LinearLayout ll5_modulebroker;
    LinearLayout ll6_modulebroker;
    LinearLayout ll7_modulebroker;
    LinearLayout fragment_ll_1;
    LinearLayout fragment_ll_2;
    LinearLayout fragment_ll_3;

    RadioButton rb1_modulebroker;
    RadioButton rb2_modulebroker;
    RadioButton rb3_modulebroker;
    RadioButton rb4_modulebroker;
    RadioButton rb5_modulebroker;
    RadioButton rb6_modulebroker;

    TextView tv1_modulebroker;
    TextView tv2_modulebroker;
    TextView tv3_modulebroker;

    TextView tv4_modulebroker;
    TextView tv5_modulebroker;
    TextView tv6_modulebroker;
    TextView tv7_modulebroker;
    TextView tv8_modulebroker;
    TextView tv9_modulebroker;
    TextView modulebroke_tv_type;

    TextView time1_modulebroker;
    TextView time2_modulebroker;

    RelativeLayout rl1_modulebroke;
    RelativeLayout rl2_modulebroke;
    RelativeLayout rl3_modulebroke;
    RelativeLayout fragment_ss_1;
    private Intent intent;

    private LineChart details_chart;

    RadioGroup modulebroke_rg1;
    RadioGroup modulebroke_rg2;
    private DBean.DataBean.DataMapBean dataMap;
    private DataNumBean.DataBean data;
    private List<String> indexList;
    private List<Integer> integers;
    private PopupWindow popupWindow;
    private View inflate;

    DateTimePickerView dateTimePickerView;
    LinearLayout report_picker;
    TextView report_cancel;
    TextView report_ensure;
    private SwipeRefreshLayout ptrClassicFrameLayout;

    private MyViewPager vpager_one;
    private ArrayList<Fragment> aList = new ArrayList<>();
    private MyFragmentPagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());
        FinalContents.setZhuanyuan("1");

        return inflater.inflate(R.layout.project_attache_modulebroker_fragment_economics, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView() {
        ptrClassicFrameLayout = getActivity().findViewById(R.id.PtrClassic_modulebroke);
        fragment_ll_3 = getActivity().findViewById(R.id.fragment_ll_3);
        fragment_ll_2 = getActivity().findViewById(R.id.fragment_ll_2);
        fragment_ll_1 = getActivity().findViewById(R.id.fragment_ll_1);
        vpager_one = getActivity().findViewById(R.id.vpager_one);
        vpager_one.setOnSingleTouchListener(this);
        if (FinalContents.getFragmentSS().equals("0")) {
            mAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
            FinalContents.setFragmentSS("1");
            aList.add(new MyFragment1());
            aList.add(new MyFragment2());
            aList.add(new MyFragment3());

            mAdapter.setListfragment(aList);
            vpager_one.setAdapter(mAdapter);
            vpager_one.setCurrentItem(0);
        }

        vpager_one.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
//                    fragment_ll_1.setBackgroundColor(Color.parseColor("#334485"));
//                    fragment_ll_2.setBackgroundColor(Color.parseColor("#EEEEEE"));
//                    fragment_ll_3.setBackgroundColor(Color.parseColor("#EEEEEE"));

                    fragment_ll_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                    fragment_ll_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                    fragment_ll_3.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);

                } else if (position == 1) {
//                    fragment_ll_2.setBackgroundColor(Color.parseColor("#334485"));
//                    fragment_ll_1.setBackgroundColor(Color.parseColor("#EEEEEE"));
//                    fragment_ll_3.setBackgroundColor(Color.parseColor("#EEEEEE"));

                    fragment_ll_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                    fragment_ll_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                    fragment_ll_3.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                } else if (position == 2) {
//                    fragment_ll_3.setBackgroundColor(Color.parseColor("#334485"));
//                    fragment_ll_2.setBackgroundColor(Color.parseColor("#EEEEEE"));
//                    fragment_ll_1.setBackgroundColor(Color.parseColor("#EEEEEE"));

                    fragment_ll_3.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                    fragment_ll_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                    fragment_ll_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        vpager_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("广播","点击：");
//                int currentItem = vpager_one.getCurrentItem();
//                Log.i("广播","广播currentItem：" + currentItem);
//                if (vpager_one.getCurrentItem() == 0) {
//                    intent = new Intent(getContext(), StoreListActivity.class);
//                    FinalContents.setCompanyId("");
//                    FinalContents.setMyAddType("");
//                    startActivity(intent);
//                } else if (vpager_one.getCurrentItem() == 1) {
//                    FinalContents.setStoreId("");
//                    FinalContents.setCompanyId("");
//                    intent = new Intent(getContext(), BrokersListActivity.class);
//                    startActivity(intent);
//                } else if (vpager_one.getCurrentItem() == 2) {
//                    intent = new Intent(getContext(), CommissionActivity.class);
//                    FinalContents.setCompanyId("");
//                    FinalContents.setStoreId("");
//                    FinalContents.setAgentId("");
//                    startActivity(intent);
//                }
//            }
//        });

        ll1_modulebroker = getActivity().findViewById(R.id.ll1_modulebroke);
        ll2_modulebroker = getActivity().findViewById(R.id.ll2_modulebroke);
        ll3_modulebroker = getActivity().findViewById(R.id.ll3_modulebroke);
        ll4_modulebroker = getActivity().findViewById(R.id.ll4_modulebroke);
        ll5_modulebroker = getActivity().findViewById(R.id.ll5_modulebroke);
        ll6_modulebroker = getActivity().findViewById(R.id.ll6_modulebroke);
        ll7_modulebroker = getActivity().findViewById(R.id.ll7_modulebroke);

        rb1_modulebroker = getActivity().findViewById(R.id.rb1_modulebroke);
        rb2_modulebroker = getActivity().findViewById(R.id.rb2_modulebroke);
        rb3_modulebroker = getActivity().findViewById(R.id.rb3_modulebroke);
        rb4_modulebroker = getActivity().findViewById(R.id.rb4_modulebroke);
        rb5_modulebroker = getActivity().findViewById(R.id.rb5_modulebroke);
        rb6_modulebroker = getActivity().findViewById(R.id.rb6_modulebroke);

//        tv1_modulebroker = getActivity().findViewById(R.id.tv1_modulebroke);
//        tv2_modulebroker = getActivity().findViewById(R.id.tv2_modulebroke);
//        tv3_modulebroker = getActivity().findViewById(R.id.tv3_modulebroke);
        modulebroke_tv_type = getActivity().findViewById(R.id.modulebroke_tv_type);

        tv4_modulebroker = getActivity().findViewById(R.id.tv4_modulebroke);
        tv5_modulebroker = getActivity().findViewById(R.id.tv5_modulebroke);
        tv6_modulebroker = getActivity().findViewById(R.id.tv6_modulebroke);
        tv7_modulebroker = getActivity().findViewById(R.id.tv7_modulebroke);
        tv8_modulebroker = getActivity().findViewById(R.id.tv8_modulebroke);
        tv9_modulebroker = getActivity().findViewById(R.id.tv9_modulebroke);

        time1_modulebroker = getActivity().findViewById(R.id.time1_modulebroke);
        time2_modulebroker = getActivity().findViewById(R.id.time2_modulebroke);

//        rl1_modulebroke = getActivity().findViewById(R.id.rl1_modulebroke);
//        rl2_modulebroke = getActivity().findViewById(R.id.rl2_modulebroke);
//        rl3_modulebroke = getActivity().findViewById(R.id.rl3_modulebroke);

        modulebroke_rg1 = getActivity().findViewById(R.id.modulebroke_rg1);
        modulebroke_rg2 = getActivity().findViewById(R.id.modulebroke_rg2);

        dateTimePickerView = getActivity().findViewById(R.id.fragment_report_pickerView);
        report_picker = getActivity().findViewById(R.id.fragment_report_picker);
        report_cancel = getActivity().findViewById(R.id.fragment_report_picker_cancel);
        report_ensure = getActivity().findViewById(R.id.fragment_report_picker_ensure);
        details_chart = getActivity().findViewById(R.id.lc_modulebroke);

        ptrClassicFrameLayout.setOnRefreshListener(this);

//        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                frame.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        ptrClassicFrameLayout.refreshComplete();
//                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
//                        initData();
//                        rb1_modulebroker.setChecked(true);
//                    }
//                }, 1000);
//            }
//
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//            }
//        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        time1_modulebroker.setText(string);
        time2_modulebroker.setText(string);

        dateTimePickerView.setStartDate(new GregorianCalendar(year, month, dayOfMonth-15));
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));

        dateTimePickerView.setEndDate(new GregorianCalendar(year, month, dayOfMonth+15));

        modulebroke_rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb1_modulebroke) {
                    ll1_modulebroker.setVisibility(View.GONE);
                    NewlyIncreased.setTag("0");
                    initDataNum("0", "", "");
                } else if (i == R.id.rb2_modulebroke) {
                    initDataNum("1", "", "");
                    NewlyIncreased.setTag("1");
                    ll1_modulebroker.setVisibility(View.GONE);
                } else if (i == R.id.rb3_modulebroke) {
                    initDataNum("2", "", "");
                    NewlyIncreased.setTag("2");
                    ll1_modulebroker.setVisibility(View.GONE);
                } else if (i == R.id.rb4_modulebroke) {
                    String s = time1_modulebroker.getText().toString();
                    String s1 = time2_modulebroker.getText().toString();
                    NewlyIncreased.setTag("3");
                    initDataNum("3", s, s1);
                    ll1_modulebroker.setVisibility(View.VISIBLE);
                }
            }
        });

        time1_modulebroker.setOnClickListener(new View.OnClickListener() {
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
                        time1_modulebroker.setText(dateString);
                        String s = time1_modulebroker.getText().toString();
                        String s1 = time2_modulebroker.getText().toString();
                        NewlyIncreased.setStartDate(dateString);
                        initDataNum("3", s, s1);

                    }
                });
            }
        });
        time2_modulebroker.setOnClickListener(new View.OnClickListener() {
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
                        time2_modulebroker.setText(dateString);
                        String s = time1_modulebroker.getText().toString();
                        String s1 = time2_modulebroker.getText().toString();
                        NewlyIncreased.setEndDate(dateString);
                        initDataNum("3", s, s1);
                    }
                });
            }
        });

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

        modulebroke_rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb5_modulebroke) {
                    String s = modulebroke_tv_type.getText().toString();
                    if (s.equals("近七天的活动度   ")) {
                        initDatatTendency("0", "0");
                    } else if (s.equals("近七天的活动度   ")) {
                        initDatatTendency("1", "0");
                    } else if (s.equals("近七天的活动度   ")) {
                        initDatatTendency("2", "0");
                    }

                } else if (i == R.id.rb6_modulebroke) {
                    String s = modulebroke_tv_type.getText().toString();
                    if (s.equals("近七天的活动度   ")) {
                        initDatatTendency("0", "1");
                    } else if (s.equals("近七天的新增量   ")) {
                        initDatatTendency("1", "1");
                    } else if (s.equals("近七天的递减量   ")) {
                        initDatatTendency("2", "1");
                    }
                }
            }
        });
        ll2_modulebroker.setOnClickListener(this);
        ll3_modulebroker.setOnClickListener(this);
        ll4_modulebroker.setOnClickListener(this);
        ll5_modulebroker.setOnClickListener(this);
        ll6_modulebroker.setOnClickListener(this);
        ll7_modulebroker.setOnClickListener(this);
//        rl1_modulebroke.setOnClickListener(this);
//        rl2_modulebroke.setOnClickListener(this);
//        rl3_modulebroke.setOnClickListener(this);
        modulebroke_tv_type.setOnClickListener(this);

        initData();
    }

    private void initDatatTendency(String status, String type) {


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<TendentcyBean> tendentcy = myService.getTendentcy(status, type, FinalContents.getUserID());
        tendentcy.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TendentcyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TendentcyBean tendentcyBean) {
                        integers = tendentcyBean.getData().getSeries().get(0).getData();
                        indexList = tendentcyBean.getData().getXAxis().getData();
                        details_chart.setVisibility(View.VISIBLE);
                        init(integers);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("专员", "错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initDataNum(String type, String startTime, String endTime) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<DataNumBean> dataNum = myService.getDataNum(FinalContents.getUserID(), "", "", type, startTime, endTime);
        dataNum.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataNumBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataNumBean dataNumBean) {
                        data = dataNumBean.getData();
                        tv4_modulebroker.setText(data.getReportNumber() + "");
                        tv5_modulebroker.setText(data.getAccessingNumber() + "");
                        tv6_modulebroker.setText(data.getIsIslandNumber() + "");
                        tv7_modulebroker.setText(data.getEarnestMoneyNumber() + "");
                        tv8_modulebroker.setText(data.getTradeNumber() + "");
                        tv9_modulebroker.setText(data.getInvalidNum() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("专员", "错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<DBean> dBean = myService.getDBean(FinalContents.getUserID());
        dBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DBean dBean) {
                        dataMap = dBean.getData().getDataMap();

                        EventBus.getDefault().post(new Fragnemt_SS(dBean.getData().getStoreCount() + "", dBean.getData().getPeopleCount() + "", "", "", ""));

//                        tv1_modulebroker.setText(dBean.getData().getStoreCount() + "");
//                        tv2_modulebroker.setText(dBean.getData().getPeopleCount() + "");
                        tv4_modulebroker.setText(dataMap.getReportNumber() + "");
                        tv5_modulebroker.setText(dataMap.getAccessingNumber() + "");
                        tv6_modulebroker.setText(dataMap.getIsIslandNumber() + "");
                        tv7_modulebroker.setText(dataMap.getEarnestMoneyNumber() + "");
                        tv8_modulebroker.setText(dataMap.getTradeNumber() + "");
                        tv9_modulebroker.setText(dataMap.getInvalidNum() + "");

                        integers = dBean.getData().getGsonOption().getSeries().get(0).getData();
                        indexList = dBean.getData().getGsonOption().getXAxis().getData();
                        details_chart.setVisibility(View.VISIBLE);
                        init(integers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("专员", "专员门店列表错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 门店
//            case R.id.rl1_modulebroke:
//                intent = new Intent(getContext(), StoreListActivity.class);
//                FinalContents.setCompanyId("");
//                FinalContents.setMyAddType("");
//                startActivity(intent);
//                break;
            //            TODO 经纪人
//            case R.id.rl2_modulebroke:
//                FinalContents.setStoreId("");
//                FinalContents.setCompanyId("");
//                intent = new Intent(getContext(), BrokersListActivity.class);
//                startActivity(intent);
//                break;
            //            TODO 佣金跟进
//            case R.id.rl3_modulebroke:
//                intent = new Intent(getContext(), CommissionActivity.class);
//                FinalContents.setCompanyId("");
//                FinalContents.setStoreId("");
//                FinalContents.setAgentId("");
//                startActivity(intent);
//                break;
            //            TODO 报备
            case R.id.ll2_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "1");
                FinalContents.setStoreId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 到访
            case R.id.ll3_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "2");
                FinalContents.setStoreId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 登岛
            case R.id.ll4_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "3");
                FinalContents.setStoreId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 认筹
            case R.id.ll5_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "4");
                FinalContents.setStoreId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 成交
            case R.id.ll6_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "5");
                FinalContents.setStoreId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 失效
            case R.id.ll7_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "6");
                FinalContents.setStoreId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            case R.id.modulebroke_tv_type:

                initPopWindow();

//                popupWindow = new PopupWindow(getContext());
//                inflate = LayoutInflater.from(getContext()).inflate(R.layout.project_attache_item_popwindow, null);
//                popupWindow.setContentView(inflate);
//
//                popupWindow.setWidth(modulebroke_tv_type.getMeasuredWidth());
//                popupWindow.setHeight(modulebroke_tv_type.getMeasuredHeight() * 3 + 20);
//
//                final TextView item_popwindoe_1 = inflate.findViewById(R.id.item_popwindoe_1);
//                final TextView item_popwindoe_2 = inflate.findViewById(R.id.item_popwindoe_2);
//                final TextView item_popwindoe_3 = inflate.findViewById(R.id.item_popwindoe_3);
//
//                item_popwindoe_1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        modulebroke_tv_type.setText("近七天的活动度   ");
//                        if (rb5_modulebroker.isChecked()) {
//                            initDatatTendency("0", "0");
//                        } else if (rb6_modulebroker.isChecked()) {
//                            initDatatTendency("0", "1");
//                        }
//                        popupWindow.dismiss();
//                    }
//                });
//                item_popwindoe_2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        modulebroke_tv_type.setText("近七天的新增量   ");
//                        if (rb5_modulebroker.isChecked()) {
//                            initDatatTendency("1", "0");
//                        } else if (rb6_modulebroker.isChecked()) {
//                            initDatatTendency("1", "1");
//                        }
//                        popupWindow.dismiss();
//                    }
//                });
//                item_popwindoe_3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        modulebroke_tv_type.setText("近七天的递减量   ");
//                        if (rb5_modulebroker.isChecked()) {
//                            initDatatTendency("2", "0");
//                        } else if (rb6_modulebroker.isChecked()) {
//                            initDatatTendency("2", "1");
//                        }
//                        popupWindow.dismiss();
//                    }
//                });
//
//                popupWindow.setFocusable(true); //设置PopupWindow可获得焦点
//                popupWindow.setTouchable(true); //设置PopupWindow可触摸
//                popupWindow.setOutsideTouchable(true);
//                popupWindow.showAsDropDown(modulebroke_tv_type, 0, 0);


                break;
        }

    }

    private void initPopWindow() {


        final List<String> list1 = new ArrayList<>();
        list1.add("近七天的活动度");
        list1.add("近七天的新增量");
        list1.add("近七天的递减量");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {

                modulebroke_tv_type.setText(list1.get(options1) + "   ");

                if(options1 == 0){
                    if (rb5_modulebroker.isChecked()) {
                        initDatatTendency("0", "0");
                    } else if (rb6_modulebroker.isChecked()) {
                        initDatatTendency("0", "1");
                    }
                }else if(options1 == 1){
                    if (rb5_modulebroker.isChecked()) {
                        initDatatTendency("1", "0");
                    } else if (rb6_modulebroker.isChecked()) {
                        initDatatTendency("1", "1");
                    }
                }else if(options1 == 2){
                    if (rb5_modulebroker.isChecked()) {
                        initDatatTendency("2", "0");
                    } else if (rb6_modulebroker.isChecked()) {
                        initDatatTendency("2", "1");
                    }
                }

            }
        }).setSelectOptions(0)
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list1);
        //      展示
        pvOptions.show();

    }

    //TODO 详情页趋势图绘制
    private void init(final List<Integer> list) {

        //显示边界
        details_chart.setDrawBorders(false);
        //无数据时显示的文字
        details_chart.setNoDataText("暂无数据");
        //折线图不显示数值
//        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = details_chart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(0f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(indexList.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) list.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IndexAxisValueFormatter(indexList));
        //得到Y轴
        YAxis yAxis = details_chart.getAxisLeft();
        YAxis rightYAxis = details_chart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
//        yAxis.setEnabled(false);
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
//        //设置Y轴坐标之间的最小间隔
//        yAxis.setGranularity(1);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(Collections.max(list) + 2, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
        yAxis.setAxisMaximum(Collections.max(list) + 1);
        //图例：得到Lengend
        Legend legend = details_chart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        details_chart.setDescription(description);
        //图标刷新
        details_chart.invalidate();
        details_chart.animateXY(2000, 2000);
        setData(list);

        // don't forget to refresh the drawing
        details_chart.invalidate();
    }

    //TODO 详情页趋势图数据填充
    private void setData(final List<Integer> list) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            values.add(new Entry(i, list.get(i)));
        }

        LineDataSet set1;

        if (details_chart.getData() != null &&
                details_chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) details_chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            details_chart.getData().notifyDataChanged();
            details_chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(true);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(3f);
            set1.setValueTextSize(9f);
            set1.setHighlightEnabled(!set1.isHighlightEnabled());
            set1.setCircleColor(Color.parseColor("#FFFFFF"));
            set1.setCircleHoleColor(Color.parseColor("#5484FF"));
            set1.setHighLightColor(Color.BLACK);
            set1.setColor(Color.parseColor("#5484FF"));
//            set1.setFillColor(R.color.mian);
            set1.setFillAlpha(20);
            Drawable drawable = getResources().getDrawable(R.drawable.line_back);
            set1.setFillDrawable(drawable);
            set1.setDrawValues(!set1.isDrawValuesEnabled());
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return details_chart.getAxisLeft().getAxisMinimum();
                }
            });


            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            details_chart.setData(data);
            // 设置放大限制
            details_chart.getViewPortHandler().setMaximumScaleX(1.0f); // 限制X轴放大限制
            details_chart.getViewPortHandler().setMaximumScaleY(1.0f); // 限制Y轴放大限制
        }
        details_chart.animateXY(2000, 2000);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (NewlyIncreased.getTag().equals("0")) {

        } else if (NewlyIncreased.getTag().equals("1")){

        } else if (NewlyIncreased.getTag().equals("2")){

        } else if (NewlyIncreased.getTag().equals("3")){

        } else {
            initView();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //TODO now visible to user 不显示fragment
        } else {
            onResume();
            //TODO now invisible to user 显示fragment
        }
    }

    @Override
    public void onSingleTouch() {

        Log.i("广播", "点击：");
        int currentItem = vpager_one.getCurrentItem();
        Log.i("广播", "广播currentItem：" + currentItem);
        if (vpager_one.getCurrentItem() == 0) {
            intent = new Intent(getContext(), StoreListActivity.class);
            FinalContents.setCompanyId("");
            FinalContents.setMyAddType("");
            startActivity(intent);
        } else if (vpager_one.getCurrentItem() == 1) {
            FinalContents.setStoreId("");
            FinalContents.setCompanyId("");
            intent = new Intent(getContext(), BrokersListActivity.class);
            startActivity(intent);
        } else if (vpager_one.getCurrentItem() == 2) {
            intent = new Intent(getContext(), CommissionActivity.class);
            FinalContents.setCompanyId("");
            FinalContents.setStoreId("");
            FinalContents.setAgentId("");
            startActivity(intent);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FinalContents.setFragmentSS("0");
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRefresh() {

        if (ptrClassicFrameLayout.isRefreshing()) {//如果正在刷新
            initView();
            initData();
            ptrClassicFrameLayout.setRefreshing(false);//取消刷新
        }

    }
}
