package com.xcy.fzb.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.CityFragment;
import com.xcy.fzb.all.fragment.EnterpriseFragment;
import com.xcy.fzb.all.fragment.FlowFragment;
import com.xcy.fzb.all.modle.HouseBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//TODO 购房须知
public class PurchaseNoticeActivity extends AllActivity implements View.OnClickListener {


    private LinearLayout notice_img;
    private TextView notice_t1;
    private TextView notice_t2;
    private TextView notice_t3;
    private LinearLayout notice_l1;
    private LinearLayout notice_l2;
    private LinearLayout notice_l3;
    private LinearLayout notice_fl;
    private Button notice_btn;

    private TextView title;
    private TextView time;
    private TextView content;
    private String houseTypeUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectPropertyHouseList?";
    private String talkToolId = "";

    FlowFragment flowFragment = new FlowFragment();
    CityFragment cityFragment = new CityFragment();
    EnterpriseFragment enterpriseFragment = new EnterpriseFragment();
    private HouseBean.DataBean houseDataData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_notice);
        init_No_Network();

    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
            init();
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

    public void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        notice_img = findViewById(R.id.notice_img);
        notice_t1 = findViewById(R.id.notice_t1);
        notice_t2 = findViewById(R.id.notice_t2);
        notice_t3 = findViewById(R.id.notice_t3);
        notice_l1 = findViewById(R.id.notice_l1);
        notice_l2 = findViewById(R.id.notice_l2);
        notice_l3 = findViewById(R.id.notice_l3);
        notice_fl = findViewById(R.id.notice_fl);
        notice_btn = findViewById(R.id.notice_btn);

        title = findViewById(R.id.notice_title);
        time = findViewById(R.id.notice_time);
        content = findViewById(R.id.notice_content);


        notice_l1.setVisibility(View.VISIBLE);
        notice_l2.setVisibility(View.INVISIBLE);
        notice_l3.setVisibility(View.INVISIBLE);


        notice_img.setOnClickListener(this);
        notice_t1.setOnClickListener(this);
        notice_t2.setOnClickListener(this);
        notice_t3.setOnClickListener(this);
        notice_btn.setOnClickListener(this);

    }

    private void init() {

        StatusBar.makeStatusBarTransparent(this);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HouseBean> houseBean1 = fzbInterface.getHouseBean(FinalContents.getUserID(), FinalContents.getProjectID(), "1", "1000");
        houseBean1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HouseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HouseBean houseBean) {
                        houseDataData = houseBean.getData();
                        if (houseDataData.getPropertyHouseList().size() >= 1) {
                            title.setText(houseDataData.getPropertyHouseList().get(0).getTitle());
                            time.setText(houseDataData.getPropertyHouseList().get(0).getCreateDate());
                            content.setText("       " + houseDataData.getPropertyHouseList().get(0).getContent());
                            talkToolId = houseDataData.getPropertyHouseList().get(0).getId();
                        } else {
                            title.setText("");
                            time.setText("");
                            content.setText("");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "PurchaseNoticeActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.notice_img) {
            finish();
        } else if (id == R.id.notice_t1) {
            notice_l1.setVisibility(View.VISIBLE);
            notice_l2.setVisibility(View.INVISIBLE);
            notice_l3.setVisibility(View.INVISIBLE);
            if (houseDataData.getPropertyHouseList().size() >= 1) {
                title.setText(houseDataData.getPropertyHouseList().get(0).getTitle());
                time.setText(houseDataData.getPropertyHouseList().get(0).getCreateDate());
                content.setText("       " + houseDataData.getPropertyHouseList().get(0).getContent());
                talkToolId = houseDataData.getPropertyHouseList().get(0).getId();
            } else {
                title.setText("");
                time.setText("");
                content.setText("");
            }

        } else if (id == R.id.notice_t2) {

            notice_l1.setVisibility(View.INVISIBLE);
            notice_l2.setVisibility(View.VISIBLE);
            notice_l3.setVisibility(View.INVISIBLE);
            if (houseDataData.getPropertyHouseList().size() >= 2) {
                title.setText(houseDataData.getPropertyHouseList().get(1).getTitle());
                time.setText(houseDataData.getPropertyHouseList().get(1).getCreateDate());
                content.setText("       " + houseDataData.getPropertyHouseList().get(1).getContent());
                talkToolId = houseDataData.getPropertyHouseList().get(1).getId();
            } else {
                title.setText("");
                time.setText("");
                content.setText("");
            }

        } else if (id == R.id.notice_t3) {

            notice_l1.setVisibility(View.INVISIBLE);
            notice_l2.setVisibility(View.INVISIBLE);
            notice_l3.setVisibility(View.VISIBLE);
            if (houseDataData.getPropertyHouseList().size() >= 3) {
                title.setText(houseDataData.getPropertyHouseList().get(2).getTitle());
                time.setText(houseDataData.getPropertyHouseList().get(2).getCreateDate());
                content.setText("       " + houseDataData.getPropertyHouseList().get(2).getContent());
                talkToolId = houseDataData.getPropertyHouseList().get(2).getId();
            } else {
                title.setText("");
                time.setText("");
                content.setText("");
            }

        } else if (id == R.id.notice_btn) {
            FinalContents.showShare(title.getText().toString(), "http://test.fangzuobiao.com:88/sellingPoint?" + "&userId=" + FinalContents.getUserID() + "&talkToolId=" + talkToolId, content.getText().toString(), "http://39.98.173.250:8080" + houseDataData.getPropertyHouseList().get(0).getShareIcon(), "http://test.fangzuobiao.com:88/sellingPoint?" + "&userId=" + FinalContents.getUserID() + "&talkToolId=" + talkToolId, this);
        }
    }


}
