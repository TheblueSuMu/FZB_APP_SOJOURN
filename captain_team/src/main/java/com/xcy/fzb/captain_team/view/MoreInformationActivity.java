package com.xcy.fzb.captain_team.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.MoreBean;
import com.xcy.fzb.captain_team.fragment.MoreInformationFragment;
import com.xcy.fzb.captain_team.fragment.MoreProjectFragment;
import com.xcy.fzb.captain_team.fragment.MoreTypeFragment;
import com.xcy.fzb.captain_team.persente.OkHttpPost;
import com.xcy.fzb.captain_team.persente.StatusBar;

import java.util.List;

public class MoreInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private String buildingUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectMoreInfo?";
    private LinearLayout more_img;
    private LinearLayout more_information;
    private LinearLayout more_type;
    private LinearLayout more_project;
    private View more_l1;
    private View more_l2;
    private View more_l3;
    private FrameLayout more_fl;

    FragmentManager manager;
    FragmentTransaction transaction;

    MoreInformationFragment moreInformationFragment = new MoreInformationFragment();
    MoreTypeFragment moreTypeFragment= new MoreTypeFragment();
    MoreProjectFragment moreProjectFragment = new MoreProjectFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);
        StatusBar.makeStatusBarTransparent(this);

        initView();

    }

    private void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        more_img = findViewById(R.id.more_img);
        more_information = findViewById(R.id.more_information);
        more_type = findViewById(R.id.more_type);
        more_project = findViewById(R.id.more_project);
        more_l1 = findViewById(R.id.more_l1);
        more_l2 = findViewById(R.id.more_l2);
        more_l3 = findViewById(R.id.more_l3);
        more_fl = findViewById(R.id.more_fl);

        initData();

        more_img.setOnClickListener(this);
        more_information.setOnClickListener(this);
        more_type.setOnClickListener(this);
        more_project.setOnClickListener(this);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction.replace(R.id.more_fl, moreInformationFragment);

        transaction.commit();

    }

    private void initData(){
        String url = buildingUrl +"&id="+ FinalContents.getProjectID();
        Log.i("aaa","项目ID："+ FinalContents.getProjectID());
        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();
        Log.i("qssm","更多信息："+data);
        String substring = data.substring(9, 10);

        if (substring.equals("1")) {
            MoreBean moreBean = new Gson().fromJson(data, MoreBean.class);
            List<MoreBean.DataBean> list = moreBean.getData();

            Log.i("qssm","changdu"+list.get(0).getValue().get(0).getValue().get(0).getValue());
            moreInformationFragment = new MoreInformationFragment(list);
            moreTypeFragment = new MoreTypeFragment(list);
            moreProjectFragment = new MoreProjectFragment(list);
        }else {
            Toast.makeText(this, "没有获取到数据", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        switch (view.getId()) {
            case R.id.more_img:
                finish();
                break;
            case R.id.more_information:

                more_l1.setVisibility(View.VISIBLE);
                more_l2.setVisibility(View.INVISIBLE);
                more_l3.setVisibility(View.INVISIBLE);

                transaction.replace(R.id.more_fl, moreInformationFragment);

                break;
            case R.id.more_type:

                more_l1.setVisibility(View.INVISIBLE);
                more_l2.setVisibility(View.VISIBLE);
                more_l3.setVisibility(View.INVISIBLE);

                transaction.replace(R.id.more_fl, moreTypeFragment);

                break;
            case R.id.more_project:

                more_l1.setVisibility(View.INVISIBLE);
                more_l2.setVisibility(View.INVISIBLE);
                more_l3.setVisibility(View.VISIBLE);

                transaction.replace(R.id.more_fl, moreProjectFragment);


                break;
        }
        transaction.commit();
    }
}
