package com.xcy.fzb.shopping_guide.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.FeedBackBean;
import com.xcy.fzb.shopping_guide.persente.StatusBar;
import com.xcy.fzb.shopping_guide.service.MyService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 意见反馈
public class FeedbackActivity extends AppCompatActivity {

    ImageView feedback_return;
    EditText feedback_editText;
    Button feedback_btn;
    private String message;

    private List<Object> mDatas;

    private final String IMAGE_TYPE = "image/*";

    private final int IMAGE_CODE = 0;
    private Bitmap bm;
    StringBuffer stringBuffer = null;
    private StringBuffer imgurl;
    int sum = 0;
    //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initView();

    }


    public void initView() {

        StatusBar.makeStatusBarTransparent(FeedbackActivity.this);


        feedback_btn = findViewById(R.id.feedback_btn);
        feedback_editText = findViewById(R.id.feedback_editText);
        feedback_return = findViewById(R.id.feedback_return);
        //返回上一级的点击事件
        feedback_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //选择图片路径的点击事件


        View inflate = View.inflate(FeedbackActivity.this, R.layout.item_flowlayout, null);
        mDatas = new ArrayList<>();

        //TODO 提交按钮的点击事件
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = feedback_editText.getText().toString();
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                final Observable<FeedBackBean> feedBack = fzbInterface.getFeedBack(message, "", FinalContents.getUserID());
                feedBack.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<FeedBackBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(FeedBackBean feedBackBean) {
                                String msg = feedBackBean.getMsg();
                                if (msg.equals("成功")) {
                                    Toast.makeText(FeedbackActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(FeedbackActivity.this, "提交失败，请从新提交", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("MyCL", "意见反馈错误信息" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }

}
