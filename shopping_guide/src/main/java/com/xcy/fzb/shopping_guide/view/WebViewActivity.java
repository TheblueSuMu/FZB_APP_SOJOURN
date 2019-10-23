package com.xcy.fzb.shopping_guide.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.persente.StatusBar;

public class WebViewActivity extends AllActivity {
    private WebView webView;
    private TextView title;
    private LinearLayout back;
    private LinearLayout web_f1;
    private TextView layout_title;
    private TextView web_time;
    private TextView web_content;
    private String time = "1";

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
            web_f1.setVisibility(View.GONE);
            webView.loadDataWithBaseURL(null, webviewUrl, "text/html", "utf-8", null);
        }else {
            web_f1.setVisibility(View.VISIBLE);
            layout_title.setText(stringExtra);
            web_time.setText(time);
            web_content.setText("       "+content);
        }
    }
}
