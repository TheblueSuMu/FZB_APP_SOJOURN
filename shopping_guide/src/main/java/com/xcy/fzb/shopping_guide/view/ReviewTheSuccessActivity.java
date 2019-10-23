package com.xcy.fzb.shopping_guide.view;

import android.annotation.SuppressLint;
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
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.adapter.ReviewTheSuccessAdapter;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.MyClientFragmentBean;
import com.xcy.fzb.shopping_guide.persente.StatusBar;
import com.xcy.fzb.shopping_guide.service.MyService;

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

    RecyclerView review_the_success_rv;

    Button review_the_success_bt1;
    Button review_the_success_bt3;
    Button review_the_success_bt4;
    Button review_the_success_bt5;


    private Intent intent;

    ReviewTheSuccessAdapter adapter;

    private MyClientFragmentBean.DataBean.InfoDataBean infoData;
    private List<MyClientFragmentBean.DataBean.ListDataBean> processData;

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
        review_the_success_rv = findViewById(R.id.review_the_success_rv);
        review_the_success_bt1 = findViewById(R.id.review_the_success_bt1);
        review_the_success_bt3 = findViewById(R.id.review_the_success_bt3);
        review_the_success_bt4 = findViewById(R.id.review_the_success_bt4);
        review_the_success_bt5 = findViewById(R.id.review_the_success_bt5);


        review_the_success_return.setOnClickListener(this);
        review_the_success_bt1.setOnClickListener(this);
        review_the_success_bt3.setOnClickListener(this);
        review_the_success_bt4.setOnClickListener(this);
        review_the_success_bt5.setOnClickListener(this);
        review_the_success_img2.setOnClickListener(this);

        review_the_success_bt5.setVisibility(View.GONE);
        review_the_success_bt4.setVisibility(View.GONE);
        review_the_success_bt1.setVisibility(View.GONE);
        review_the_success_bt3.setVisibility(View.GONE);

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
        Observable<MyClientFragmentBean> clientFragmentBean = fzbInterface.getClientFragmentBean(FinalContents.getUserID(),FinalContents.getCustomerID(),FinalContents.getLandingId(), FinalContents.getPreparationId());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyClientFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyClientFragmentBean myClientFragmentBean) {
                        for (int i = 0;i < myClientFragmentBean.getData().getMenuData().size();i++){
                            if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("60")) {
                                review_the_success_bt5.setVisibility(View.VISIBLE);
                            } else if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("50")) {
                                review_the_success_bt4.setVisibility(View.VISIBLE);
                            } else if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("400")) {
                                review_the_success_bt1.setVisibility(View.VISIBLE);
                            } else if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("402")) {
                                review_the_success_bt3.setVisibility(View.VISIBLE);
                            }
                        }


                        infoData = myClientFragmentBean.getData().getInfoData();
                        Glide.with(ReviewTheSuccessActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(review_the_success_img1);
                        review_the_success_tv1.setText(infoData.getCustomerName());
                        processData = myClientFragmentBean.getData().getListData();

                        review_the_success_tv2.setText(infoData.getProjectName());
                        review_the_success_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");

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
        adapter = new ReviewTheSuccessAdapter(processData);

        review_the_success_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            //            TODO 返回上一层
            case R.id.review_the_success_return:
                finish();
                break;
            //            TODO 补全信息
            case R.id.review_the_success_bt1:
                intent = new Intent(ReviewTheSuccessActivity.this, ToApplyForAnIslandActivity.class);
                intent.putExtra("name",infoData.getCustomerName());
                startActivity(intent);
                break;
            //            TODO 未成交
            case R.id.review_the_success_bt3:
                intent = new Intent(ReviewTheSuccessActivity.this, FeedbackActivity.class);
                startActivity(intent);
                break;
            //            TODO 申请认筹
            case R.id.review_the_success_bt4:
                Log.i("MyCL", "getProjectName：" + infoData.getProjectName());
                Log.i("MyCL", "getCustomerName：" + infoData.getCustomerName());
                Log.i("MyCL", "getCustomerPhone：" + infoData.getCustomerPhone());
                intent = new Intent(ReviewTheSuccessActivity.this, ConfessToRaiseInformationActivity.class);
                intent.putExtra("projectName", infoData.getProjectName() + "");
                intent.putExtra("name", infoData.getCustomerName() + "");
                intent.putExtra("phone", infoData.getCustomerPhone() + "");
                startActivity(intent);
                break;
            //            TODO 申请成交
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
//            //            TODO 经纪人电话
//            case R.id.review_the_success_tv6:
//                if (processData.get(0).getJsonDatas().get(2).getValue().equals("")) {
//                    Toast.makeText(this, "经纪人暂无电话", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + processData.get(0).getJsonDatas().get(2).getValue()));//跳转到拨号界面，同时传递电话号码
//                    startActivity(dialIntent);
//                }
//                break;
        }

    }
}
