package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.InitiatedAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.InitiatedBean;
import com.xcy.fzb.project_side.presente.OkHttpPost;
import com.xcy.fzb.project_side.presente.StatusBar;

import java.util.List;

public class InitiatedTheReviewActivity extends AppCompatActivity implements View.OnClickListener, InitiatedAdapter.OnItemClick {

    ImageView initiated_the_review_return;
    TextView initiated_the_review_tv;
    LinearLayout initiated_the_review_ll1;
    LinearLayout initiated_the_review_ll2;
    LinearLayout initiated_the_review_ll3;
    LinearLayout initiated_the_review_ll4;
    LinearLayout initiated_the_review_ll5;
    LinearLayout initiated_the_review_ll6;
    RecyclerView initiated_the_review_rv;

    InitiatedAdapter adapter;
    private List<InitiatedBean.DataBean.RowsBean> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiated_the_review);

        initView();

    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        initiated_the_review_return = findViewById(R.id.initiated_the_review_return);
        initiated_the_review_tv = findViewById(R.id.initiated_the_review_tv);
        initiated_the_review_ll1 = findViewById(R.id.initiated_the_review_ll1);
        initiated_the_review_ll2 = findViewById(R.id.initiated_the_review_ll2);
        initiated_the_review_ll3 = findViewById(R.id.initiated_the_review_ll3);
        initiated_the_review_ll4 = findViewById(R.id.initiated_the_review_ll4);
        initiated_the_review_ll5 = findViewById(R.id.initiated_the_review_ll5);
        initiated_the_review_ll6 = findViewById(R.id.initiated_the_review_ll6);
        initiated_the_review_rv = findViewById(R.id.initiated_the_review_rv);

        initiated_the_review_return.setOnClickListener(this);
        initiated_the_review_tv.setOnClickListener(this);
        initiated_the_review_ll1.setOnClickListener(this);
        initiated_the_review_ll3.setOnClickListener(this);
        initiated_the_review_ll5.setOnClickListener(this);

        initData(1);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.initiated_the_review_return:
                finish();
                break;
            //TODO 拒绝记录
            case R.id.initiated_the_review_tv:
                Intent intent = new Intent(InitiatedTheReviewActivity.this, RefuseTheProjectEndActivity.class);
                FinalContents.setJJ("我发起的审核");
                startActivity(intent);
                break;
            //TODO 退筹
            case R.id.initiated_the_review_ll1:

                initData(1);
                Log.i("MyCL", "退筹");
                initiated_the_review_ll2.setVisibility(View.VISIBLE);
                initiated_the_review_ll4.setVisibility(View.GONE);
                initiated_the_review_ll6.setVisibility(View.GONE);

                break;
            //            TODO 调单
            case R.id.initiated_the_review_ll3:

                initData(2);
                Log.i("MyCL", "调单");
                initiated_the_review_ll2.setVisibility(View.GONE);
                initiated_the_review_ll4.setVisibility(View.VISIBLE);
                initiated_the_review_ll6.setVisibility(View.GONE);
                break;
            //            TODO 退单
            case R.id.initiated_the_review_ll5:

                initData(3);
                Log.i("MyCL", "退单");
                initiated_the_review_ll2.setVisibility(View.GONE);
                initiated_the_review_ll4.setVisibility(View.GONE);
                initiated_the_review_ll6.setVisibility(View.VISIBLE);

                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initData(int position) {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        initiated_the_review_rv.setLayoutManager(manager);
        adapter = new InitiatedAdapter();
        String url = "http://39.98.173.250:8080/fangfang/app/v1/specialSelect/myExaminelist?userId=" + FinalContents.getUserID() + "&type=1&status=" + position;
        OkHttpPost okHttpPost = new OkHttpPost(url);
        String post = okHttpPost.post();

        Gson gson = new Gson();
        InitiatedBean initiatedBean = gson.fromJson(post, InitiatedBean.class);
        rows = initiatedBean.getData().getRows();

        adapter.setRows(rows);
        adapter.setOnItemClick(this);
        initiated_the_review_rv.setAdapter(adapter);


    }

    @Override
    public void Item(int position) {

        Intent intent = new Intent(this, InitiatedActivity.class);
        FinalContents.setCustomerID(rows.get(position).getCustomerId());
        startActivity(intent);

    }
}
