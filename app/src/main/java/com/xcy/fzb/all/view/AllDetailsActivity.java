package com.xcy.fzb.all.view;

import android.os.Bundle;

import com.xcy.fzb.R;
import com.xcy.fzb.all.persente.StatusBar;

public class AllDetailsActivity extends AllActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details);
        StatusBar.makeStatusBarTransparent(this);

    }
}
