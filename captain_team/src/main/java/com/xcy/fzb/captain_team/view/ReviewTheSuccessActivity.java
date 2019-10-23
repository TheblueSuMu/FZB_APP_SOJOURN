package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.MakeABargainAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.FailureAlertBean;
import com.xcy.fzb.captain_team.database.MyClientFragmentBean;
import com.xcy.fzb.captain_team.persente.OkHttpPost;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 报备—审核成功
public class ReviewTheSuccessActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView review_the_success_return;
    ImageView review_the_success_img1;
    ImageView review_the_success_img2;

    TextView review_the_success_tv1;
    TextView review_the_success_tv2;
    TextView review_the_success_tv3;
    TextView review_the_success_tv4;
    TextView review_the_success_tv5;
    TextView review_the_success_tv6;
    TextView review_the_success_tv7;

    RecyclerView review_the_success_rv;

    Button review_the_success_bt1;
    Button review_the_success_bt2;
    Button review_the_success_bt3;
    Button review_the_success_bt4;
    Button review_the_success_bt5;


    private Intent intent;

    MakeABargainAdapter adapter;

    private MyClientFragmentBean.DataBean.InfoDataBean infoData;
    private List<MyClientFragmentBean.DataBean.ProcessDataBean> processData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_the_success);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        review_the_success_return = findViewById(R.id.review_the_success_return);
        review_the_success_img1 = findViewById(R.id.review_the_success_img1);
        review_the_success_img2 = findViewById(R.id.review_the_success_img2);
        review_the_success_tv1 = findViewById(R.id.review_the_success_tv1);
        review_the_success_tv2 = findViewById(R.id.review_the_success_tv2);
        review_the_success_tv3 = findViewById(R.id.review_the_success_tv3);
        review_the_success_tv4 = findViewById(R.id.review_the_success_tv4);
        review_the_success_tv5 = findViewById(R.id.review_the_success_tv5);
        review_the_success_tv6 = findViewById(R.id.review_the_success_tv6);
        review_the_success_tv7 = findViewById(R.id.review_the_success_tv7);
        review_the_success_rv = findViewById(R.id.review_the_success_rv);
        review_the_success_bt1 = findViewById(R.id.review_the_success_bt1);
        review_the_success_bt2 = findViewById(R.id.review_the_success_bt2);
        review_the_success_bt3 = findViewById(R.id.review_the_success_bt3);
        review_the_success_bt4 = findViewById(R.id.review_the_success_bt4);
        review_the_success_bt5 = findViewById(R.id.review_the_success_bt5);


        review_the_success_return.setOnClickListener(this);
        review_the_success_bt1.setOnClickListener(this);
        review_the_success_bt2.setOnClickListener(this);
        review_the_success_bt3.setOnClickListener(this);
        review_the_success_bt4.setOnClickListener(this);
        review_the_success_bt5.setOnClickListener(this);
        review_the_success_img2.setOnClickListener(this);
        review_the_success_tv6.setOnClickListener(this);

        initData();

    }

    private void initData() {

        Log.i("MyCL", "getCustomerId：" + FinalContents.getCustomerID());
        Log.i("MyCL", "userID：" + FinalContents.getUserID());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MyClientFragmentBean> clientFragmentBean = fzbInterface.getClientFragmentBean(FinalContents.getUserID(), FinalContents.getPreparationId());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyClientFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyClientFragmentBean myClientFragmentBean) {
                        infoData = myClientFragmentBean.getData().getInfoData();
                        Glide.with(ReviewTheSuccessActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(review_the_success_img1);
                        review_the_success_tv1.setText(infoData.getCustomerName());
                        processData = myClientFragmentBean.getData().getProcessData();

                        review_the_success_tv2.setText(infoData.getProjectName());
                        review_the_success_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");

//                        FinalContents.setPreparationId(processData.get(0).getPreparationId());
                        if (processData.get(0).getJsonDatas().size() == 0) {
                            review_the_success_tv4.setText("");
                            review_the_success_tv5.setText("");
                            review_the_success_tv6.setText("");
                            review_the_success_tv7.setText("");
                        } else {
                            review_the_success_tv4.setText(processData.get(0).getJsonDatas().get(0).getValue());
                            review_the_success_tv5.setText(processData.get(0).getJsonDatas().get(1).getValue());
                            review_the_success_tv6.setText(processData.get(0).getJsonDatas().get(2).getValue());
                            review_the_success_tv7.setText(processData.get(0).getJsonDatas().get(3).getValue());
                        }

                        initRV();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("WrongConstant")
    private void initRV() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        review_the_success_rv.setLayoutManager(manager);
        adapter = new MakeABargainAdapter();
        adapter.setListData(processData);

        review_the_success_rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            //            TODO 返回上一层
            case R.id.review_the_success_return:
                finish();
                break;
            //            TODO 失效
            case R.id.review_the_success_bt1:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定失效操作？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = "http://39.98.173.250:8080/fangfang/app/v1/specialUpdate/failureSave?userId=" + FinalContents.getUserID() + "&preparationId=" + infoData.getPreparationId();
                        OkHttpPost okHttpPost = new OkHttpPost(s);
                        String post = okHttpPost.post();
                        Gson gson = new Gson();
                        FailureAlertBean failureAlertBean = gson.fromJson(post, FailureAlertBean.class);
                        if(failureAlertBean.getMsg().equals("成功")){
                            Toast.makeText(ReviewTheSuccessActivity.this,failureAlertBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(ReviewTheSuccessActivity.this,failureAlertBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                break;
            //            TODO 申请登岛
            case R.id.review_the_success_bt2:
                intent = new Intent(ReviewTheSuccessActivity.this, ToApplyForAnIslandActivity.class);
                intent.putExtra("name",infoData.getCustomerName());
                startActivity(intent);
                break;
            //            TODO 到访
            case R.id.review_the_success_bt3:
                intent = new Intent(ReviewTheSuccessActivity.this, ConfirmTheVisitActivity.class);
                intent.putExtra("name",infoData.getCustomerName());
                intent.putExtra("project",processData.get(0).getRecordName());
                startActivity(intent);
                break;
            //            TODO 认筹
            case R.id.review_the_success_bt4:
                FinalContents.setPreparationId(infoData.getPreparationId());
                FinalContents.setProjectID(infoData.getProjectId());
                FinalContents.setCustomerID(infoData.getCustomerId());
                Log.i("MyCL", "getProjectName：" + infoData.getProjectName());
                Log.i("MyCL", "getCustomerName：" + infoData.getCustomerName());
                Log.i("MyCL", "getCustomerPhone：" + infoData.getCustomerPhone());
                intent = new Intent(ReviewTheSuccessActivity.this, ConfessToRaiseInformationActivity.class);
                intent.putExtra("projectName", infoData.getProjectName() + "");
                intent.putExtra("name", infoData.getCustomerName() + "");
                intent.putExtra("phone", infoData.getCustomerPhone() + "");
                startActivity(intent);
                break;
            //            TODO 成交
            case R.id.review_the_success_bt5:
                intent = new Intent(ReviewTheSuccessActivity.this, FillInTransactionInformationActivity.class);
                intent.putExtra("name","填写成交信息");
                startActivity(intent);
                break;
            //            TODO 项目负责人电话
            case R.id.review_the_success_img2:
                if (infoData.getCustomerPhone().equals("")) {
                    Toast.makeText(this, "负责人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + infoData.getCustomerPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            //            TODO 经纪人电话
            case R.id.review_the_success_tv6:
                if (processData.get(0).getJsonDatas().get(2).getValue().equals("")) {
                    Toast.makeText(this, "经纪人暂无电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + processData.get(0).getJsonDatas().get(2).getValue()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
        }

    }
}
