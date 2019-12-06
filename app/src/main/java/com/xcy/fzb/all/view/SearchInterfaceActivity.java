package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.RecyclerAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.HotBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchInterfaceActivity extends AllActivity implements View.OnClickListener {


    private LinearLayout search_img;
    private EditText search_edit_text;
    private LinearLayout search_l1;
    private LinearLayout search_l2;
    private LinearLayout search_l3;
    private LinearLayout search_ll3;
    private LinearLayout search_ll1;
    private LinearLayout search_ll2;
    private RelativeLayout nofound;
    private TextView textView;

    private String hotUrl = FinalContents.getBaseUrl() + "/fangfang/app/v1/commonSelect/projectList?";

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private String text = "";
    private String url = hotUrl + "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID() + "&projectType=3" + "&searchName=" + text;
    private List<HotBean.DataBean.RowsBean> hotlist = new ArrayList<>();
    private ImageView all_no_information;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_interface);

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

        search_img = findViewById(R.id.search_img);
        search_edit_text = findViewById(R.id.search_edit_text);
        search_l1 = findViewById(R.id.search_l1);
        search_l2 = findViewById(R.id.search_l2);
        search_l3 = findViewById(R.id.search_l3);
        search_ll3 = findViewById(R.id.search_ll3);
        search_ll1 = findViewById(R.id.search_ll1);
        search_ll2 = findViewById(R.id.search_ll2);
        nofound = findViewById(R.id.nofound);
        textView = findViewById(R.id.search_text);

        recyclerView = findViewById(R.id.search_recyler);

        all_no_information = findViewById(R.id.all_no_information);

        search_img.setOnClickListener(this);
        search_l1.setOnClickListener(this);
        search_l2.setOnClickListener(this);
        search_l3.setOnClickListener(this);


        search_edit_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    if(search_ll1.getVisibility() == View.VISIBLE){
                        recyclerViewData("3");
                    }else if(search_ll2.getVisibility() == View.VISIBLE){
                        recyclerViewData("2");
                    } else if (search_ll3.getVisibility() == View.VISIBLE) {
                        recyclerViewData("1");
                    }
                    return true;
                }
                return false;
            }

        });

        recyclerViewData("3");

    }

    @SuppressLint("WrongConstant")
    private void recyclerViewData(String projecttype) {
        text = search_edit_text.getText().toString();
        Log.i("bbb", "用户名ID：" + FinalContents.getUserID());
        Log.i("bbb", "城市ID：" + FinalContents.getCityID());
        Log.i("bbb", "城市公司ID：" + FinalContents.getCityID());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> hotBean = fzbInterface.getHotBean(FinalContents.getUserID(), FinalContents.getCityID(), projecttype, text,"1000");
        hotBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        HotBean.DataBean hotBeanData = hotBean.getData();
                        hotlist = hotBeanData.getRows();
                        Log.i("调接口", "接收值" + hotlist.size());
                        //在此处修改布局排列方向
                        if (hotlist.size() == 0) {
                            all_no_information.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            recyclerAdapter.notifyDataSetChanged();
                        } else {
                            all_no_information.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            nofound.setVisibility(View.GONE);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(SearchInterfaceActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerAdapter = new RecyclerAdapter(hotlist);
                            recyclerAdapter.setProject(FinalContents.getProject());
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }

                        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {
                                if (FinalContents.getProject().equals("1")) {
                                    FinalContents.setProjectName(hotlist.get(postion).getProjectName());
                                    FinalContents.setProjectSearchID(hotlist.get(postion).getProjectId());
                                    FinalContents.setGuideRuleId(hotlist.get(postion).getGuideRuleId());
                                    FinalContents.setProjectID(hotlist.get(postion).getProjectId());
                                    finish();
                                }
                            }
                        });
                        hideInput();
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Log.i("MyCL","SearchInterfaceActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        textView.setVisibility(View.GONE);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        //加载更多功能的代码
                        textView.setVisibility(View.VISIBLE);
                    }
                }else {
                    textView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dx > 0) {
                    //大于0表示正在向右滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向左滚动
                    isSlidingToLast = false;
                }
            }
        });


    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.search_img:
                finish();
                break;
            case R.id.search_l1:
                search_ll1.setVisibility(View.VISIBLE);
                search_ll2.setVisibility(View.INVISIBLE);
                search_ll3.setVisibility(View.INVISIBLE);
                recyclerViewData("3");
                break;
            case R.id.search_l2:
                search_ll2.setVisibility(View.VISIBLE);
                search_ll1.setVisibility(View.INVISIBLE);
                search_ll3.setVisibility(View.INVISIBLE);
                recyclerViewData("2");
                break;
            case R.id.search_l3:
                search_ll2.setVisibility(View.INVISIBLE);
                search_ll1.setVisibility(View.INVISIBLE);
                search_ll3.setVisibility(View.VISIBLE);
                recyclerViewData("1");
                break;
        }

    }
}
