package com.xcy.fzb.project_attache.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.view.AllActivity;

public class ContentActivity extends AllActivity {

    ImageView imageView;
    ImageView content_img;

    LinearLayout content_ll1;
    LinearLayout content_ll2;
    LinearLayout content_ll3;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_content);

        StatusBar.makeStatusBarTransparent(this);

        String img = getIntent().getStringExtra("img");
        Log.i("MyCL", "Img：" + img);
        imageView = findViewById(R.id.img);

        content_img = findViewById(R.id.content_img);
        content_ll1 = findViewById(R.id.content_ll1);
        content_ll2 = findViewById(R.id.content_ll2);
        content_ll3 = findViewById(R.id.content_ll3);

        Glide.with(this).load(img).into(imageView);

        content_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        content_ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ContentActivity.this, AddCompanyActivity.class);
                startActivity(intent);
                finish();
            }
        });
        content_ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ContentActivity.this, AddStoreActivity.class);
                startActivity(intent);
                finish();
            }
        });

        content_ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ContentActivity.this, AddBrokerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
