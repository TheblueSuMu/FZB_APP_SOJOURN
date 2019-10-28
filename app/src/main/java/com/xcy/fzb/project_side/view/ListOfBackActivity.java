package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.ListOfBean;
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

//TODO 填写退单说明
public class ListOfBackActivity extends AllActivity {

    RelativeLayout list_of_back_return;

    EditText list_of_back_et;

    Button list_of_back_btn;
    private String message;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_list_of_back);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        list_of_back_return = findViewById(R.id.list_of_back_return);
        list_of_back_et = findViewById(R.id.list_of_back_et);
        list_of_back_btn = findViewById(R.id.list_of_back_btn);

        list_of_back_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        list_of_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = list_of_back_et.getText().toString();
                if (message.equals("")) {
                    Toast.makeText(ListOfBackActivity.this, "请填写退单说明内容", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);
                    Observable<ListOfBean> userMessage = fzbInterface.getChargebackApply(FinalContents.getUserID(),FinalContents.getPreparationId(),message);
                    userMessage.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ListOfBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @SuppressLint("WrongConstant")
                                @Override
                                public void onNext(ListOfBean listOfBean) {
                                    if(listOfBean.getMsg().equals("成功")){
                                        Toast.makeText(ListOfBackActivity.this,listOfBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else {
                                        Toast.makeText(ListOfBackActivity.this,listOfBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("填写退单说明","错误"+e);
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }



            }
        });


    }
}
