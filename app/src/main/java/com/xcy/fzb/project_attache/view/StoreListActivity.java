package com.xcy.fzb.project_attache.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.StoreListBean;
import com.xcy.fzb.all.database.StoreListData;
import com.xcy.fzb.all.persente.ContactModel;
import com.xcy.fzb.all.persente.LetterComparator;
import com.xcy.fzb.all.persente.PinnedHeaderDecoration;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_attache.adapter.StoreListAdapter;

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

public class StoreListActivity extends AllActivity implements View.OnClickListener, StoreListAdapter.ItemOnClick {

    RelativeLayout store_list_return;
    ImageView store_list_add;

    EditText store_list_et;

    TextView store_list_tv1;
    TextView store_list_tv2;

    LinearLayout store_list_ll1;
    LinearLayout store_list_ll2;
    LinearLayout store_list_ll3;
    LinearLayout store_list_ll4;

    CheckBox store_list_cb;

    RecyclerView store_list_rv;

    private StoreListAdapter mAdapter;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    private Context context;
    private List<StoreListData> listData;
    private List<StoreListBean.DataBean.RowsBean> rows;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_store_list);

        initView();

    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        store_list_return = findViewById(R.id.store_list_return);
        store_list_add = findViewById(R.id.store_list_add);
        store_list_et = findViewById(R.id.store_list_et);
        store_list_ll1 = findViewById(R.id.store_list_ll1);
        store_list_ll2 = findViewById(R.id.store_list_ll2);
        store_list_ll3 = findViewById(R.id.store_list_ll3);
        store_list_ll4 = findViewById(R.id.store_list_ll4);
        store_list_rv = findViewById(R.id.store_list_rv);
        store_list_tv2 = findViewById(R.id.store_list_tv2);
        store_list_tv1 = findViewById(R.id.store_list_tv1);
        store_list_cb = findViewById(R.id.store_list_cb);

        store_list_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (store_list_cb.isChecked()) {
                    if (store_list_ll2.getVisibility() == View.VISIBLE) {
                        initData("", "", "1");
                    } else {
                        initDatam("", "1");
                    }
                } else {
                    if (store_list_ll2.getVisibility() == View.VISIBLE) {
                        initData("", "", "");
                    } else {
                        initDatam("", "");
                    }
                }
            }
        });

        store_list_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    String s = store_list_et.getText().toString();
                    if (store_list_ll2.getVisibility() == View.VISIBLE) {
                        if (store_list_cb.isChecked()) {
                            initData("", s, "1");
                        } else {
                            initData("", s, "");
                        }
                    } else if (store_list_ll4.getVisibility() == View.VISIBLE) {
                        if (store_list_cb.isChecked()) {
                            initDatam(s, "1");
                        } else {
                            initDatam(s, "");
                        }
                    }
                    return true;
                }
                return false;
            }

        });
        store_list_ll1.setOnClickListener(this);
        store_list_ll3.setOnClickListener(this);
        store_list_return.setOnClickListener(this);
        store_list_add.setOnClickListener(this);

        if (FinalContents.getMyAddType().equals("公司")) {
            store_list_tv2.setTextColor(Color.parseColor("#334485"));
            store_list_tv1.setTextColor(Color.parseColor("#ff333333"));
            store_list_ll4.setVisibility(View.VISIBLE);
            store_list_ll2.setVisibility(View.GONE);
            initDatam("", "");
            FinalContents.setStoreList("2");
            Log.i("专员", "进入公司");
        } else if (FinalContents.getMyAddType().equals("门店")) {
            Log.i("专员", "进入门店");
            Log.i("专员", "FinalContents.getCompanyId()：" + FinalContents.getCompanyManageId());
            store_list_tv1.setTextColor(Color.parseColor("#334485"));
            store_list_tv2.setTextColor(Color.parseColor("#ff333333"));
            store_list_ll2.setVisibility(View.VISIBLE);
            store_list_ll4.setVisibility(View.GONE);
            initData(FinalContents.getCompanyManageId(), "", "");
            FinalContents.setStoreList("1");
        } else {
            Log.i("专员", "else");
            Log.i("专员", "FinalContents.getCompanyId()：" + FinalContents.getCompanyId());
            initData(FinalContents.getCompanyId(), "", "");
            FinalContents.setStoreList("1");
        }


    }

    private void initData(String companyId, String search, String status) {

        context = StoreListActivity.this;
        mContactModels = new ArrayList<>();
        listData = new ArrayList<>();
        store_list_rv.setLayoutManager(new LinearLayoutManager(this));
        decoration = new PinnedHeaderDecoration();


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<StoreListBean> storeList = fzbInterface.getStoreList(companyId, search, status, FinalContents.getUserID(), "1000");
        storeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoreListBean storeListBean) {
                        listData.clear();
                        mContactModels.clear();
                        rows = storeListBean.getData().getRows();
                        for (int i = 0; i < rows.size(); ++i) {
                            if (rows.get(i).getCompanyName().equals("")) {

                            } else {
                                ContactModel contactModel = new ContactModel(rows.get(i).getCompanyName() + "" + rows.get(i).getStoreName());
                                StoreListData storeListData = new StoreListData();

                                storeListData.setStoreName(rows.get(i).getStoreName());
                                storeListData.setStoreId(rows.get(i).getStoreId());
                                storeListData.setShopownerName(rows.get(i).getShopownerName());
                                storeListData.setShopownerPhone(rows.get(i).getShopownerPhone());
                                storeListData.setAgentNum(rows.get(i).getAgentNum());
                                storeListData.setStatus(rows.get(i).getStatus());
                                storeListData.setCompanyId(rows.get(i).getCompanyId());
                                storeListData.setCompanyName(rows.get(i).getCompanyName());
                                storeListData.setStoreIdCode(rows.get(i).getStoreIdCode());
                                storeListData.setCompanyAddress(rows.get(i).getCompanyAddress());
                                storeListData.setStoreNum(rows.get(i).getStoreNum());

                                mContactModels.add(contactModel);
                                listData.add(storeListData);
                                Log.i("MyCL", "请求成功数据：" + rows.get(i).getCompanyName());
                                Log.i("MyCL", "请求成功数据：" + listData.size());
                            }
                        }
                        initDatas();
                    }

                    @Override
                    public void onError(Throwable e) {

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
        store_list_rv.addItemDecoration(decoration);
        mAdapter = new StoreListAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setListData(listData);
        mAdapter.setItemOnClick(this);
        store_list_rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.store_list_return:
                finish();
                break;
            case R.id.store_list_add:

                if (FinalContents.getStoreList().equals("1")) {
                    intent = new Intent(StoreListActivity.this, AddStoreActivity.class);
                    FinalContents.setMyAddType("门店");
                    startActivity(intent);
                } else if (FinalContents.getStoreList().equals("2")) {
                    intent = new Intent(StoreListActivity.this, AddCompanyActivity.class);
                    FinalContents.setMyAddType("公司");
                    startActivity(intent);
                }

                break;
            case R.id.store_list_ll1:
                store_list_tv1.setTextColor(Color.parseColor("#334485"));
                store_list_tv2.setTextColor(Color.parseColor("#ff333333"));
                store_list_ll2.setVisibility(View.VISIBLE);
                store_list_ll4.setVisibility(View.GONE);


               if(FinalContents.getMyAddType().equals("门店")){
                   initData(FinalContents.getCompanyManageId(), "", "");
                   FinalContents.setStoreList("1");
               }else {
                   if (store_list_cb.isChecked()) {
                       initData("", "", "1");
                   } else {
                       initData("", "", "");
                   }
                   FinalContents.setStoreList("1");
               }

                break;
            case R.id.store_list_ll3:
                store_list_tv2.setTextColor(Color.parseColor("#334485"));
                store_list_tv1.setTextColor(Color.parseColor("#ff333333"));
                store_list_ll4.setVisibility(View.VISIBLE);
                store_list_ll2.setVisibility(View.GONE);

                if(FinalContents.getMyAddType().equals("公司")){
                    initDatam("", "");
                    FinalContents.setStoreList("2");
                    Log.i("专员", "进入公司");
                }else {
                    FinalContents.setStoreList("2");
                    if (store_list_cb.isChecked()) {
                        initDatam("", "1");
                    } else {
                        initDatam("", "");
                    }
                }


                break;
        }

    }

    private void initDatam(String search, String status) {

        Log.i("专员", "进入公司1");

        context = StoreListActivity.this;
        mContactModels = new ArrayList<>();
        listData = new ArrayList<>();
        store_list_rv.setLayoutManager(new LinearLayoutManager(this));
        decoration = new PinnedHeaderDecoration();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<StoreListBean> storeList = fzbInterface.getCompanList(search, status, FinalContents.getUserID(), "1000");
        storeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoreListBean storeListBean) {
                        listData.clear();
                        mContactModels.clear();
                        rows = storeListBean.getData().getRows();
                        for (int i = 0; i < StoreListActivity.this.rows.size(); ++i) {
                            if (rows.get(i).getCompanyName().equals("")) {

                            } else {
                                Log.i("专员", "进入公司2");
                                ContactModel contactModel = new ContactModel(StoreListActivity.this.rows.get(i).getCompanyName() + rows.get(i).getStoreName());
                                StoreListData storeListData = new StoreListData();

                                storeListData.setStoreName(StoreListActivity.this.rows.get(i).getStoreName());
                                storeListData.setStoreId(StoreListActivity.this.rows.get(i).getStoreId());
                                storeListData.setShopownerName(StoreListActivity.this.rows.get(i).getShopownerName());
                                storeListData.setShopownerPhone(StoreListActivity.this.rows.get(i).getShopownerPhone());
                                storeListData.setAgentNum(StoreListActivity.this.rows.get(i).getAgentNum());
                                storeListData.setStatus(StoreListActivity.this.rows.get(i).getStatus());
                                storeListData.setCompanyId(StoreListActivity.this.rows.get(i).getCompanyId());
                                storeListData.setCompanyName(rows.get(i).getCompanyName());
                                storeListData.setStoreIdCode(rows.get(i).getStoreIdCode());
                                storeListData.setCompanyAddress(rows.get(i).getCompanyAddress());
                                storeListData.setStoreNum(rows.get(i).getStoreNum());

                                mContactModels.add(contactModel);
                                listData.add(storeListData);
                            }

                        }
                        initDatas();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void itemClick(String itemName) {

        Log.i("专员门店", "itemName：" + itemName);
        Log.i("MyCL", "FinalContents.getStoreList()：" + FinalContents.getStoreList());
        if (FinalContents.getStoreList().equals("1") && FinalContents.getMyAddType().equals("")) {
            Log.i("专员门店", "1");
//          TODO 门店详情
            for (int i = 0; i < listData.size(); ++i) {
                if (listData.get(i).getStoreId().equals(itemName)) {
                    for (int j = 0; j < listData.size(); ++j) {
                        if (mContactModels.get(i).getName().equals((listData.get(j).getCompanyName() + listData.get(j).getStoreName()))) {
                            FinalContents.setStoreId(listData.get(j).getStoreId());
                            FinalContents.setMyAddType("门店");
                            Intent intent = new Intent(StoreListActivity.this, CompanyDetailsActivity.class);
                            startActivity(intent);
                            Log.i("专员门店", "点击事件");
                        }
                    }
                }
            }
        } else if (FinalContents.getStoreList().equals("1") && FinalContents.getMyAddType().equals("门店")) {
            Log.i("专员门店", "2");
            for (int i = 0; i < listData.size(); ++i) {
                if (listData.get(i).getStoreId().equals(itemName)) {
                    for (int j = 0; j < listData.size(); ++j) {
                        if (mContactModels.get(i).getName().equals((listData.get(j).getCompanyName() + listData.get(j).getStoreName()))) {
                            FinalContents.setAddtype1(listData.get(j).getStoreName());
                            FinalContents.setStoreManageId(listData.get(j).getStoreId());
                            Log.i("添加经纪人", "setStoreManageId()：" + FinalContents.getStoreManageId());
                            FinalContents.setMyAddType("");
                            finish();
                        }
                    }

                }
            }
        } else if (FinalContents.getStoreList().equals("2") && FinalContents.getMyAddType().equals("")) {
            Log.i("专员门店", "3");
//            TODO 公司详情
            for (int i = 0; i < listData.size(); ++i) {
                if (listData.get(i).getCompanyId().equals(itemName)) {
                    Log.i("专员门店", "ss1");
                    for (int j = 0; j < listData.size(); ++j) {
                        Log.i("专员门店", "ss2");
                        if (mContactModels.get(i).getName().equals(listData.get(j).getCompanyName())) {
                            Log.i("公司修改", "listData.get(j).getCompanyId()：" + listData.get(j).getCompanyId());
                            FinalContents.setStoreId(listData.get(j).getStoreId());
                            FinalContents.setCompanyId(listData.get(j).getCompanyId());
                            Intent intent = new Intent(StoreListActivity.this, StoreDetailsActivity.class);
                            FinalContents.setMyAddType("公司");
                            startActivity(intent);
                        }
                    }
                }
            }
        } else if (FinalContents.getStoreList().equals("2") && FinalContents.getMyAddType().equals("公司")) {
            Log.i("专员门店", "4");
//          TODO 选择公司
            for (int i = 0; i < listData.size(); ++i) {
                if (listData.get(i).getCompanyId().equals(itemName)) {
                    for (int j = 0; j < listData.size(); ++j) {
                        if (mContactModels.get(i).getName().equals((listData.get(j).getCompanyName() + listData.get(j).getStoreName()))) {
                            FinalContents.setAddtype2(listData.get(j).getCompanyName());
                            FinalContents.setCompanyManageId(listData.get(j).getCompanyId());
                            FinalContents.setMyAddType("");
                            finish();
                        }
                    }

                }
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("MyCL", "FinalContents.getMyAddType()：" + FinalContents.getMyAddType());

        if (FinalContents.getMyAddType().equals("公司")) {
            Log.i("专员", "公司");
            store_list_tv2.setTextColor(Color.parseColor("#334485"));
            store_list_tv1.setTextColor(Color.parseColor("#ff333333"));
            store_list_ll4.setVisibility(View.VISIBLE);
            store_list_ll2.setVisibility(View.GONE);
            FinalContents.setStoreList("2");
            initDatam("", "");
            FinalContents.setMyAddType("");
        } else if (FinalContents.getMyAddType().equals("门店")) {
            Log.i("专员", "门店");
            store_list_tv1.setTextColor(Color.parseColor("#334485"));
            store_list_tv2.setTextColor(Color.parseColor("#ff333333"));
            store_list_ll4.setVisibility(View.GONE);
            store_list_ll2.setVisibility(View.VISIBLE);
            FinalContents.setStoreList("1");
            FinalContents.setMyAddType("");
            initData("", "", "");
        } else {
            Log.i("专员", "else");
            initData("", "", "");
            FinalContents.setStoreList("1");
        }

    }
}
