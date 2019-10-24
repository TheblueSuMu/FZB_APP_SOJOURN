package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.NewsDetailsBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebViewActivity extends AllActivity {
    private WebView webView;
    private TextView title;
    private LinearLayout back;
    private LinearLayout web_f1;
    private TextView layout_title;
    private TextView web_time;
    private TextView web_content;
    private String time = "1";
    private LinearLayout web_bottom;
    private TextView web_more;
    private TextView web_call;
    private LinearLayout web_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        StatusBar.makeStatusBarTransparent(this);

        init();
    }
    private void init(){
        webView = findViewById(R.id.webview);
        title = findViewById(R.id.web_title);
        back = findViewById(R.id.web_return);
        web_f1 = findViewById(R.id.web_fl);
        layout_title = findViewById(R.id.web_layout_title);
        web_time = findViewById(R.id.web_time);
        web_content = findViewById(R.id.web_content);
        web_bottom = findViewById(R.id.web_bottom);
        web_more = findViewById(R.id.web_more);
        web_call = findViewById(R.id.web_call);
        web_share = findViewById(R.id.web_share);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("title");
        String webviewUrl = intent.getStringExtra("webview");
        time = intent.getStringExtra("time");
        String content = intent.getStringExtra("content");

        title.setText(stringExtra);
        if (time == null) {
            web_share.setVisibility(View.VISIBLE);
            web_f1.setVisibility(View.GONE);
            webView.loadDataWithBaseURL(null, webviewUrl, "text/html", "utf-8", null);
            web_bottom.setVisibility(View.VISIBLE);
            Log.i("轮播图详情数据","新闻："+FinalContents.getNewID());
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<NewsDetailsBean> userMessage = fzbInterface.getNewsDetails(FinalContents.getUserID(),FinalContents.getNewID());
            userMessage.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsDetailsBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onNext(final NewsDetailsBean newsDetailsBean) {
                            Log.i("轮播图详情数据","走一波");
                            web_share.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.i("轮播图详情数据","点击分享");
                                    FinalContents.showShare(newsDetailsBean.getData().getTitle(),"http://test.fangzuobiao.com:88/NewsSharing?userId="+FinalContents.getUserID()+"&newId="+FinalContents.getNewID(),newsDetailsBean.getData().getProject().getProjectName(),"http://39.98.173.250:8080"+newsDetailsBean.getData().getCoverImg(),"http://test.fangzuobiao.com:88/NewsSharing?userId="+FinalContents.getUserID()+"&newId="+FinalContents.getNewID(),WebViewActivity.this);
                                }
                            });
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("轮播图详情数据","错误"+e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }else {
            web_share.setVisibility(View.GONE);
            web_bottom.setVisibility(View.GONE);
            web_f1.setVisibility(View.VISIBLE);
            layout_title.setText(stringExtra);
            web_time.setText(time);
            web_content.setText("       "+content);
        }

        web_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + FinalContents.getIPhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });

        web_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Log.i("详情","项目中ID"+FinalContents.getProjectID());
                Log.i("详情","用户中ID"+FinalContents.getUserID());
                Intent intent1 = new Intent(WebViewActivity.this,ProjectDetails.class);
                startActivity(intent1);
            }
        });

    }

}
