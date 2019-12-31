package com.xcy.fzb.project_attache.fragment;

import android.content.Context;
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
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
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
import com.xcy.fzb.all.persente.SingleClick;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.MyViewPager;
import com.xcy.fzb.project_attache.view.BrokersListActivity;
import com.xcy.fzb.project_attache.view.CommissionActivity;
import com.xcy.fzb.project_attache.view.StoreListActivity;
import com.xcy.fzb.project_side.view.MyClientActivity;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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

    LinearLayout project_attache_fragment_ll4;
    LinearLayout project_attache_fragment_ll3;
    LinearLayout project_attache_fragment_ll2;
    LinearLayout project_attache_fragment_ll1;

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

    private SwipeRefreshLayout ptrClassicFrameLayout;

    private MyViewPager vpager_one;
    private ArrayList<Fragment> aList;
    private MyFragmentPagerAdapter mAdapter;
    private View view;
    private Context context;
    private MyFragment1 myFragment1;
    private MyFragment2 myFragment2;
    private MyFragment3 myFragment3;
    private int year;
    private int month;
    private int dayOfMonth;
    private String string1;
    private String string2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());
        FinalContents.setZhuanyuan("1");
        view = inflater.inflate(R.layout.project_attache_modulebroker_fragment_economics, container, false);
        context = container.getContext();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {
        ptrClassicFrameLayout = view.findViewById(R.id.PtrClassic_modulebroke);

        aList = new ArrayList<>();
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment2();
        myFragment3 = new MyFragment3();

        fragment_ll_3 = view.findViewById(R.id.fragment_ll_3);
        fragment_ll_2 = view.findViewById(R.id.fragment_ll_2);
        fragment_ll_1 = view.findViewById(R.id.fragment_ll_1);
        vpager_one = view.findViewById(R.id.vpager_one);
        vpager_one.setOnSingleTouchListener(this);

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

        ll1_modulebroker = view.findViewById(R.id.ll1_modulebroke);
        ll2_modulebroker = view.findViewById(R.id.ll2_modulebroke);
        ll3_modulebroker = view.findViewById(R.id.ll3_modulebroke);
        ll4_modulebroker = view.findViewById(R.id.ll4_modulebroke);
        ll5_modulebroker = view.findViewById(R.id.ll5_modulebroke);
        ll6_modulebroker = view.findViewById(R.id.ll6_modulebroke);
        ll7_modulebroker = view.findViewById(R.id.ll7_modulebroke);

        project_attache_fragment_ll4 = view.findViewById(R.id.project_attache_fragment_ll4);
        project_attache_fragment_ll3 = view.findViewById(R.id.project_attache_fragment_ll3);
        project_attache_fragment_ll2 = view.findViewById(R.id.project_attache_fragment_ll2);
        project_attache_fragment_ll1 = view.findViewById(R.id.project_attache_fragment_ll1);

        rb1_modulebroker = view.findViewById(R.id.rb1_modulebroke);
        rb2_modulebroker = view.findViewById(R.id.rb2_modulebroke);
        rb3_modulebroker = view.findViewById(R.id.rb3_modulebroke);
        rb4_modulebroker = view.findViewById(R.id.rb4_modulebroke);
        rb5_modulebroker = view.findViewById(R.id.rb5_modulebroke);
        rb6_modulebroker = view.findViewById(R.id.rb6_modulebroke);

//        tv1_modulebroker = getActivity().findViewById(R.id.tv1_modulebroke);
//        tv2_modulebroker = getActivity().findViewById(R.id.tv2_modulebroke);
//        tv3_modulebroker = getActivity().findViewById(R.id.tv3_modulebroke);
        modulebroke_tv_type = view.findViewById(R.id.modulebroke_tv_type);

        tv4_modulebroker = view.findViewById(R.id.tv4_modulebroke);
        tv5_modulebroker = view.findViewById(R.id.tv5_modulebroke);
        tv6_modulebroker = view.findViewById(R.id.tv6_modulebroke);
        tv7_modulebroker = view.findViewById(R.id.tv7_modulebroke);
        tv8_modulebroker = view.findViewById(R.id.tv8_modulebroke);
        tv9_modulebroker = view.findViewById(R.id.tv9_modulebroke);

        time1_modulebroker = view.findViewById(R.id.time1_modulebroke);
        time2_modulebroker = view.findViewById(R.id.time2_modulebroke);

//        rl1_modulebroke = getActivity().findViewById(R.id.rl1_modulebroke);
//        rl2_modulebroke = getActivity().findViewById(R.id.rl2_modulebroke);
//        rl3_modulebroke = getActivity().findViewById(R.id.rl3_modulebroke);

        modulebroke_rg1 = view.findViewById(R.id.modulebroke_rg1);
        modulebroke_rg2 = view.findViewById(R.id.modulebroke_rg2);

        details_chart = view.findViewById(R.id.lc_modulebroke);

        ptrClassicFrameLayout.setOnRefreshListener(this);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        string1 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        string2 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth+1);
        time1_modulebroker.setText(string1);
        time2_modulebroker.setText(string2);

        modulebroke_rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb1_modulebroke) {
                    ll1_modulebroker.setVisibility(View.GONE);
                    NewlyIncreased.setTag("0");
                    if (project_attache_fragment_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("0", "", "", "1");
                    } else if (project_attache_fragment_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("0", "", "", "2");
                    }
                } else if (i == R.id.rb2_modulebroke) {
                    if (project_attache_fragment_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("1", "", "", "1");
                    } else if (project_attache_fragment_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("1", "", "", "2");
                    }

                    NewlyIncreased.setTag("1");
                    ll1_modulebroker.setVisibility(View.GONE);
                } else if (i == R.id.rb3_modulebroke) {
                    if (project_attache_fragment_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("2", "", "", "1");
                    } else if (project_attache_fragment_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("2", "", "", "2");
                    }
                    NewlyIncreased.setTag("2");
                    ll1_modulebroker.setVisibility(View.GONE);
                } else if (i == R.id.rb4_modulebroke) {
                    time1_modulebroker.setText(string1);
                    time2_modulebroker.setText(string2);
                    String s = time1_modulebroker.getText().toString();
                    String s1 = time2_modulebroker.getText().toString();
                    NewlyIncreased.setStartDate(s);
                    NewlyIncreased.setEndDate(s1);
                    NewlyIncreased.setTag("3");
                    if (project_attache_fragment_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("3", s, s1, "1");
                    } else if (project_attache_fragment_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("3", s, s1, "2");
                    }
                    ll1_modulebroker.setVisibility(View.VISIBLE);
                }
            }
        });

        time1_modulebroker.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTimePickerView1();
            }
        });
        time2_modulebroker.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTimePickerView2();
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
        project_attache_fragment_ll3.setOnClickListener(this);
        project_attache_fragment_ll1.setOnClickListener(this);

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

    private void initDataNum(String type, String startTime, String endTime, String tag) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Log.i("专员", "FinalContents.getUserID():" + FinalContents.getUserID());
        Log.i("专员", "type:" + type);
        Log.i("专员", "startTime:" + startTime);
        Log.i("专员", "endTime:" + endTime);
        Observable<DataNumBean> dataNum = myService.getDataNum(FinalContents.getUserID(), "", "", tag, type, startTime, endTime);
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

                        Log.i("广播数据", "数据：" + dBean.getData().getStoreCount());
                        Log.i("广播数据", "数据：" + dBean.getData().getPeopleCount());

                        EventBus.getDefault().post(new Fragnemt_SS(dBean.getData().getStoreCount() + "", dBean.getData().getPeopleCount() + "", "", "", ""));

                        NewlyIncreased.setStoreCount(dBean.getData().getStoreCount() + "");
                        NewlyIncreased.setPeopleCount(dBean.getData().getPeopleCount() + "");

                        if (FinalContents.getFragmentSS().equals("0")) {
                            mAdapter = new MyFragmentPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
                            FinalContents.setFragmentSS("1");
                            aList.add(myFragment2);
                            aList.add(myFragment1);
                            aList.add(myFragment3);

                            mAdapter.setListfragment(aList);
                            vpager_one.setAdapter(mAdapter);
                            vpager_one.setCurrentItem(0);
                        }
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

    //   TODO 数据统计 时间选择 开始时间
    private void initTimePickerView1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year-3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                time1_modulebroker.setText(getTime2(date));
                NewlyIncreased.setStartDate(getTime2(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //   TODO 数据统计 时间选择 结束时间
    private void initTimePickerView2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year-3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                time2_modulebroker.setText(getTime2(date));
                NewlyIncreased.setEndDate(getTime2(date));
                if (project_attache_fragment_ll2.getVisibility() == View.VISIBLE) {
                    initDataNum("3", time1_modulebroker.getText().toString(), time2_modulebroker.getText().toString(), "1");
                } else if (project_attache_fragment_ll4.getVisibility() == View.VISIBLE) {
                    initDataNum("3", time1_modulebroker.getText().toString(), time2_modulebroker.getText().toString(), "2");
                }
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    @SingleClick(1000)
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
                FinalContents.setAgentId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 到访
            case R.id.ll3_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "2");
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 登岛
            case R.id.ll4_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "3");
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 认筹
            case R.id.ll5_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "4");
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 成交
            case R.id.ll6_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "5");
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            //            TODO 失效
            case R.id.ll7_modulebroke:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "6");
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                FinalContents.setMyClientType("1");
                startActivity(intent);
                break;
            case R.id.modulebroke_tv_type:

                initPopWindow();

                break;
            case R.id.project_attache_fragment_ll1://实时
                project_attache_fragment_ll2.setVisibility(View.VISIBLE);
                project_attache_fragment_ll4.setVisibility(View.INVISIBLE);
                ll2_modulebroker.setClickable(true);
                ll3_modulebroker.setClickable(true);
                ll4_modulebroker.setClickable(true);
                ll5_modulebroker.setClickable(true);
                ll6_modulebroker.setClickable(true);
                ll7_modulebroker.setClickable(true);

//                if (ll1_modulebroker.getVisibility() == View.VISIBLE) {
                if (rb1_modulebroker.isChecked() == true) {
                    ll1_modulebroker.setVisibility(View.GONE);
                    NewlyIncreased.setTag("0");
                    initDataNum("0", "", "", "1");
                } else if (rb2_modulebroker.isChecked() == true) {
                    initDataNum("1", "", "", "1");
                    NewlyIncreased.setTag("1");
                    ll1_modulebroker.setVisibility(View.GONE);
                } else if (rb3_modulebroker.isChecked() == true) {
                    initDataNum("2", "", "", "1");
                    NewlyIncreased.setTag("2");
                    ll1_modulebroker.setVisibility(View.GONE);
                } else if (rb4_modulebroker.isChecked() == true) {
                    String s = time1_modulebroker.getText().toString();
                    String s1 = time2_modulebroker.getText().toString();
                    NewlyIncreased.setStartDate(s);
                    NewlyIncreased.setEndDate(s1);
                    NewlyIncreased.setTag("3");
                    initDataNum("3", s, s1, "1");
                    ll1_modulebroker.setVisibility(View.VISIBLE);
                }
//                } else {
//                    initDataNum("", "", "", "1");
//                }

                break;
            case R.id.project_attache_fragment_ll3://总体
                project_attache_fragment_ll2.setVisibility(View.INVISIBLE);
                project_attache_fragment_ll4.setVisibility(View.VISIBLE);
                ll2_modulebroker.setClickable(false);
                ll3_modulebroker.setClickable(false);
                ll4_modulebroker.setClickable(false);
                ll5_modulebroker.setClickable(false);
                ll6_modulebroker.setClickable(false);
                ll7_modulebroker.setClickable(false);
//                if (ll1_modulebroker.getVisibility() == View.VISIBLE) {
                if (rb1_modulebroker.isChecked() == true) {
                    ll1_modulebroker.setVisibility(View.GONE);
                    NewlyIncreased.setTag("0");
                    initDataNum("0", "", "", "2");
                } else if (rb2_modulebroker.isChecked() == true) {
                    initDataNum("1", "", "", "2");
                    NewlyIncreased.setTag("1");
                    ll1_modulebroker.setVisibility(View.GONE);
                } else if (rb3_modulebroker.isChecked() == true) {
                    initDataNum("2", "", "", "2");
                    NewlyIncreased.setTag("2");
                    ll1_modulebroker.setVisibility(View.GONE);
                } else if (rb4_modulebroker.isChecked() == true) {
                    String s = time1_modulebroker.getText().toString();
                    String s1 = time2_modulebroker.getText().toString();
                    NewlyIncreased.setStartDate(s);
                    NewlyIncreased.setEndDate(s1);
                    NewlyIncreased.setTag("3");
                    initDataNum("3", s, s1, "2");
                    ll1_modulebroker.setVisibility(View.VISIBLE);
                }
//                } else {
//                    initDataNum("", "", "", "2");
//                }
                break;
        }

    }

    //弹窗
    private void initPopWindow() {


        final List<String> list1 = new ArrayList<>();
        list1.add("近七天的活动度");
        list1.add("近七天的新增量");
        list1.add("近七天的递减量");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {

                modulebroke_tv_type.setText(list1.get(options1) + "   ");

                if (options1 == 0) {
                    if (rb5_modulebroker.isChecked()) {
                        initDatatTendency("0", "0");
                    } else if (rb6_modulebroker.isChecked()) {
                        initDatatTendency("0", "1");
                    }
                } else if (options1 == 1) {
                    if (rb5_modulebroker.isChecked()) {
                        initDatatTendency("1", "0");
                    } else if (rb6_modulebroker.isChecked()) {
                        initDatatTendency("1", "1");
                    }
                } else if (options1 == 2) {
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
        xAxis.setLabelRotationAngle(0);
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
    public void onSingleTouch() {

        Log.i("广播", "点击：");
        int currentItem = vpager_one.getCurrentItem();
        Log.i("广播", "广播currentItem：" + currentItem);
        if (vpager_one.getCurrentItem() == 1) {
            intent = new Intent(getContext(), StoreListActivity.class);
            FinalContents.setCompanyId("");
            FinalContents.setMyAddType("");
            startActivity(intent);
        } else if (vpager_one.getCurrentItem() == 0) {
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
            rb1_modulebroker.setChecked(true);
            rb5_modulebroker.setChecked(true);
            initView();
            initData();
            ptrClassicFrameLayout.setRefreshing(false);//取消刷新
        }

    }

    public String getTime2(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(date);
    }
}
