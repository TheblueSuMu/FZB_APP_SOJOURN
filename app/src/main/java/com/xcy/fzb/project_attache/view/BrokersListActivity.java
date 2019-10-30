package com.xcy.fzb.project_attache.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.BrokersListBean;
import com.xcy.fzb.all.database.BrokersListData;
import com.xcy.fzb.all.persente.ContactModel;
import com.xcy.fzb.all.persente.LetterComparator;
import com.xcy.fzb.all.persente.PinnedHeaderDecoration;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_attache.adapter.ContactsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrokersListActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout brokers_list_return;
    ImageView brokers_list_add;
    EditText brokers_list_et;
    RecyclerView brokers_list_rv;

    RelativeLayout brokers_tv_rl;

    TextView brokers_tv;

    private ContactsAdapter mAdapter;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    private Context context;
    private List<BrokersListData> listData;
    private List<BrokersListBean.DataBean.RowsBean> rows;

    private PopupWindow popupWindow;
    private View inflate;
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_brokers_list);
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
        all_no_information = findViewById(R.id.all_no_information_j);
        brokers_list_return = findViewById(R.id.brokers_list_return);
        brokers_list_add = findViewById(R.id.brokers_list_add);
        brokers_list_et = findViewById(R.id.brokers_list_et);
        brokers_list_rv = findViewById(R.id.brokers_list_rv);
        brokers_tv = findViewById(R.id.brokers_tv);
        brokers_tv_rl = findViewById(R.id.brokers_tv_rl);
        context = BrokersListActivity.this;
        mContactModels = new ArrayList<>();
        listData = new ArrayList<>();
        brokers_list_rv.setLayoutManager(new LinearLayoutManager(this));
        decoration = new PinnedHeaderDecoration();

        brokers_list_return.setOnClickListener(this);
        brokers_list_add.setOnClickListener(this);
        brokers_tv_rl.setOnClickListener(this);

        brokers_list_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    String s = brokers_list_et.getText().toString();
                    String s1 = brokers_tv.getText().toString();
                    Log.i("MyCL", "s");
                    if (s1.equals("全部")) {
                        initData(FinalContents.getStoreId(), s, "");
                        Log.i("MyCL", "s1");
                        return true;
                    } else if (s1.equals("只看异常门店")) {
                        Log.i("MyCL", "s2");
                        initData(FinalContents.getStoreId(), s, "3");
                        return true;
                    } else {
                        Log.i("MyCL", "s3");
                        initData(FinalContents.getStoreId(), s, "");
                        return true;
                    }
                }
                return false;
            }
        });

    }

    private void initData(String storeId, String search, String status) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BrokersListBean> brokersListBean = fzbInterface.getBrokersListBean(FinalContents.getCompanyId(),storeId, search, status, FinalContents.getUserID(),"1000");
        brokersListBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokersListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BrokersListBean brokersListBean) {
                        listData.clear();
                        mContactModels.clear();
                        rows = brokersListBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            brokers_list_rv.setVisibility(View.VISIBLE);
                            for (int i = 0; i < rows.size(); ++i) {
                                ContactModel contactModel = new ContactModel(rows.get(i).getAgentName() + "@" + rows.get(i).getAgentId());
                                BrokersListData brokersListData = new BrokersListData();
                                brokersListData.setAgentPhone(rows.get(i).getAgentPhone());
                                brokersListData.setAgentName(rows.get(i).getAgentName());
                                brokersListData.setCompanyName(rows.get(i).getCompanyName());
                                brokersListData.setStoreName(rows.get(i).getStoreName());
                                brokersListData.setStatus(rows.get(i).getStatus());
                                brokersListData.setAgentId(rows.get(i).getAgentId());
                                mContactModels.add(contactModel);
                                listData.add(brokersListData);

                            }
                            initDatas();
                        }else {

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("经纪人列表","错误信息:"+e.getMessage());
                        brokers_list_rv.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initDatas() {

        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        brokers_list_rv.addItemDecoration(decoration);
        mAdapter = new ContactsAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setListData(listData);
        brokers_list_rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(FinalContents.getStoreId(), "", "");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.brokers_list_return:
                finish();
                break;
            case R.id.brokers_list_add:
                Intent intent = new Intent(BrokersListActivity.this, AddBrokerActivity.class);
                startActivity(intent);
                break;
            case R.id.brokers_tv_rl:
                popupWindow = new PopupWindow(BrokersListActivity.this);
                inflate = LayoutInflater.from(BrokersListActivity.this).inflate(R.layout.project_attache_item_popwindows, null);
                popupWindow.setContentView(inflate);

                popupWindow.setWidth(brokers_tv_rl.getMeasuredWidth());
                popupWindow.setHeight(brokers_tv_rl.getMeasuredHeight() * 2 + 20);

                final TextView item_popwindoe_1 = inflate.findViewById(R.id.item_popwindoe_1s);
                final TextView item_popwindoe_2 = inflate.findViewById(R.id.item_popwindoe_2s);

                item_popwindoe_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String string = brokers_list_et.getText().toString();
                        brokers_tv.setText("全部");
                        initData(FinalContents.getStoreId(), string, "");
                        popupWindow.dismiss();
                    }
                });
                item_popwindoe_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String string = brokers_list_et.getText().toString();
                        brokers_tv.setText("只看异常门店");
                        initData(FinalContents.getStoreId(), string, "3");
                        popupWindow.dismiss();
                    }
                });


                popupWindow.setFocusable(true); //设置PopupWindow可获得焦点
                popupWindow.setTouchable(true); //设置PopupWindow可触摸
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(brokers_tv_rl, 0, 0);
                break;

        }

    }
}
