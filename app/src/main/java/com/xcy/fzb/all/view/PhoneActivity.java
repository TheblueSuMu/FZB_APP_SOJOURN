package com.xcy.fzb.all.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nanchen.wavesidebar.WaveSideBarView;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.ContactsAdapter;
import com.xcy.fzb.all.adapter.PhoneAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.AddPhoneBean;
import com.xcy.fzb.all.modle.ClientBean;
import com.xcy.fzb.all.modle.PhoneDto;
import com.xcy.fzb.all.persente.ContactModel;
import com.xcy.fzb.all.persente.LetterComparator;
import com.xcy.fzb.all.persente.PinnedHeaderDecoration;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.PhoneUtil;

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

public class PhoneActivity extends AllActivity{


    private TextView all_activity_phone_cancle;
    private EditText all_activity_phone_search;
    private TextView all_activity_phone_ensure;
    private RecyclerView all_activity_phone_rv;
    private ImageView all_activity_phone_no_information;
    private List<PhoneDto> phoneDtos;
    private CheckBox all_activity_phone_checkbox_all;
    private TextView all_activity_phone_checkbox_all_number;
    private PhoneAdapter phoneAdapter;
    private WaveSideBarView all_activity_phone_bar;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    private List<ClientBean.DataBean> data;
    private ContactsAdapter mAdapter;
    private boolean aBoolean = true;
    private List<PhoneDto> jsonList = new ArrayList<>();
    private String fieldbeanlist = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_phone);
        initfvb();
    }

    private void initfvb(){
        all_activity_phone_cancle = findViewById(R.id.all_activity_phone_cancle);
        all_activity_phone_search = findViewById(R.id.all_activity_phone_search);
        all_activity_phone_ensure = findViewById(R.id.all_activity_phone_ensure);
        all_activity_phone_rv = findViewById(R.id.all_activity_phone_Rv);
        all_activity_phone_no_information = findViewById(R.id.all_activity_phone_no_information);
        all_activity_phone_checkbox_all = findViewById(R.id.all_activity_phone_checkbox_all);
        all_activity_phone_checkbox_all_number = findViewById(R.id.all_activity_phone_checkbox_all_number);
        all_activity_phone_bar = findViewById(R.id.all_activity_phone_bar);
        data = new ArrayList<>();
        mContactModels = new ArrayList<>();
        decoration = new PinnedHeaderDecoration();
        check();
        initClick();
    }

    private void initClick(){

        all_activity_phone_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        all_activity_phone_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneDtos = phoneAdapter.getList();
                for (int i = 0;i < phoneDtos.size();i++){
                    if (phoneDtos.get(i).isStatus()) {
                        //  TODO    导入通讯录
                        jsonList.add(phoneDtos.get(i));
                        Log.i("每次","添加显示的数据"+phoneDtos.get(i).getName());
                    }
                    Log.i("每次","显示的数据"+phoneDtos.get(i).getName());
                    if (i == phoneDtos.size()-1) {
                        Log.i("每次","最后显示的数据"+phoneDtos.get(i).getName());
                        if (jsonList.size() != 0) {
                            initadd();
                        }else {
                            finish();
                        }
                    }
                }
            }
        });
    }

    /**
     * 检查权限
     */
    private void check() {
        //判断是否有权限
        if(ContextCompat.checkSelfPermission(PhoneActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PhoneActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
            Toast.makeText(this, "开启权限", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "已经开启权限", Toast.LENGTH_SHORT).show();
            initViews();
        }
    }

    //回调方法，无论哪种结果，最终都会回调该方法，之后在判断用户是否授权，
    // 用户同意则调用readContacts（）方法，失败则会弹窗提示失败
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initViews();
                } else {
                    Toast.makeText(this, "获取联系人权限失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void initViews() {
        PhoneUtil phoneUtil = new PhoneUtil(this);
        phoneDtos = phoneUtil.getPhone();

        for (int i = 0; i < phoneDtos.size(); ++i) {
            ContactModel contactModel = new ContactModel(phoneDtos.get(i).getName() + "@" + phoneDtos.get(i).getTelPhone());
            mContactModels.add(contactModel);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PhoneActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        all_activity_phone_rv.setLayoutManager(linearLayoutManager);

        // RecyclerView设置相关
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        all_activity_phone_rv.addItemDecoration(decoration);
        phoneAdapter = new PhoneAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        phoneAdapter.setList(phoneDtos);
        phoneAdapter.setContacts(mContactModels);
        all_activity_phone_rv.setAdapter(phoneAdapter);
        phoneAdapter.notifyDataSetChanged();

// 侧边设置相关
        all_activity_phone_bar.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i = 0; i < mContactModels.size(); i++) {
                    if (mContactModels.get(i).getIndex().equals(letter)) {
                        ((LinearLayoutManager) all_activity_phone_rv.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });

        phoneAdapter.setItemOnClick(new PhoneAdapter.ItemOnClick() {
            @Override
            public void itemClick(int position) {
                int size = 0;
                phoneDtos = phoneAdapter.getList();
                all_activity_phone_checkbox_all.setChecked(false);
                all_activity_phone_checkbox_all.setText("全选");
                for (int i = 0;i < phoneDtos.size();i++){
                    if (phoneDtos.get(i).isStatus()) {
                        size++;
                        all_activity_phone_checkbox_all_number.setText("已选"+size);
                    }
                }
                if (size == 0) {
                    all_activity_phone_checkbox_all_number.setText("已选0");
                } else if (size == phoneDtos.size()) {
                    all_activity_phone_checkbox_all.setChecked(true);
                    all_activity_phone_checkbox_all.setText("取消全选");
                    phoneAdapter.setAll("1");
                    phoneAdapter.notifyDataSetChanged();
                }
            }
        });

        all_activity_phone_checkbox_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (all_activity_phone_checkbox_all.getText().toString().equals("取消全选")) {
                    all_activity_phone_checkbox_all.setText("全选");
                    all_activity_phone_checkbox_all_number.setText("已选0");
                    phoneAdapter.setAll("2");
                    phoneAdapter.notifyDataSetChanged();
                }else if (all_activity_phone_checkbox_all.getText().toString().equals("全选")){
                    all_activity_phone_checkbox_all.setText("取消全选");
                    all_activity_phone_checkbox_all_number.setText("已选"+mContactModels.size());
                    phoneAdapter.setAll("1");
                    phoneAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initadd(){
        Gson gson = new Gson();
        fieldbeanlist = gson.toJson(jsonList);

        Log.i("MyCL","fieldbeanlist:" + fieldbeanlist);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<AddPhoneBean> addClient = fzbInterface.getCustomerImport(fieldbeanlist,FinalContents.getUserID());
        addClient.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddPhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddPhoneBean addPhoneBean) {
                        String msg = addPhoneBean.getData().getMessage();
                        if (msg.equals("添加成功")) {
                            Toast.makeText(PhoneActivity.this, addPhoneBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(PhoneActivity.this, addPhoneBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的客户添加客户错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
