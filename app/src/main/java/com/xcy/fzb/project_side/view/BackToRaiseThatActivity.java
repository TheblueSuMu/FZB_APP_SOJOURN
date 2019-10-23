package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.BackToBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.AllActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 填写退筹说明
public class BackToRaiseThatActivity extends AllActivity {


    RelativeLayout back_to_raise_that_return;

    EditText back_to_raise_that_et;

    Button back_to_raise_that_btn;
    private String message;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_back_to_raise_that);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        back_to_raise_that_return = findViewById(R.id.back_to_raise_that_return);
        back_to_raise_that_et = findViewById(R.id.back_to_raise_that_et);
        back_to_raise_that_btn = findViewById(R.id.back_to_raise_that_btn);
        back_to_raise_that_return.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
        back_to_raise_that_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("退筹","："+FinalContents.getPreparationId()+"：中间："+FinalContents.getUserID());
                message = back_to_raise_that_et.getText().toString();
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Observable<BackToBean> teamMemberBeane = fzbInterface.getRefundMoneyApply(FinalContents.getPreparationId(),message,FinalContents.getUserID());
                teamMemberBeane.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BackToBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BackToBean backToBean) {
                                Toast.makeText(BackToRaiseThatActivity.this,backToBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("MyCL", "TeamMemberActivity错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }
}
