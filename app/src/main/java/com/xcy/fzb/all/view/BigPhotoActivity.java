package com.xcy.fzb.all.view;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.xcy.fzb.R;
import com.xcy.fzb.all.fragment.PhotoFragment;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.shopping_guide.adapter.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class BigPhotoActivity extends AllActivity {

    List<String> listImage;
    TextView big_tv;
    int j = 0;
    int num = 0;
    private List<Fragment> mFragments;
    private String[] mTitles = new String[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_photo);

        setupViewPager();
    }

    private void setupViewPager() {
        StatusBar.makeStatusBarTransparent(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        listImage = new ArrayList<>();

        big_tv = findViewById(R.id.big_tv);

        String bigPhotoimg = getIntent().getStringExtra("bigPhotoimg");


        String[] a  = bigPhotoimg.split("[|]");
        for (int i = 0; i < a.length; i++){
            listImage.add("http://39.98.173.250:8080"+a[i]);
        }

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        int index = getIntent().getIntExtra("index", 0);
        mFragments = new ArrayList<>();
        for (int i = 0; i < listImage.size();i++){
            mFragments.add(PhotoFragment.newInstance(listImage.get(i)));
        }
        big_tv.setText( index+1 + "/" + mFragments.size());
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(index);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int currentPosition = position;
                big_tv.setText(currentPosition + 1 + "/" + mFragments.size());
            }
        });
    }

}