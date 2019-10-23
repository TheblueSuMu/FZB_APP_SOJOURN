package com.xcy.fzb.captain_team.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.persente.StatusBar;

public class WebActivity extends AllActivity {
    private WebView webView;
    private TextView title;
    private ImageView back;
    private ImageView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        StatusBar.makeStatusBarTransparent(this);

        init();
    }

    private void init(){
        webView = findViewById(R.id.web);
        title = findViewById(R.id.webview_title);
        back = findViewById(R.id.webview_return);
        share = findViewById(R.id.webview_share);

        Intent intent = getIntent();
        final String webUrl = intent.getStringExtra("webUrl");
        final String titleUrl = intent.getStringExtra("title");
        title.setText(titleUrl);
        webView.loadUrl(webUrl);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FinalContents.showShare(titleUrl,webUrl,titleUrl,"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3055880154,1625749017&fm=26&gp=0.jpg",webUrl,WebActivity.this);
//
//            }
//        });

    }
}
