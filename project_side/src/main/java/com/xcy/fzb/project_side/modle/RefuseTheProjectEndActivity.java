package com.xcy.fzb.project_side.modle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.InitiatedAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.presente.OkHttpPost;
import com.xcy.fzb.project_side.presente.StatusBar;
import com.xcy.fzb.project_side.view.InitiatedActivity;

import java.util.List;
//TODO 拒绝记录
public class RefuseTheProjectEndActivity extends AppCompatActivity implements View.OnClickListener, InitiatedAdapter.OnItemClick {

    ImageView the_project_end_refuse_return;
    RecyclerView the_project_end_refuse_rv;
    LinearLayout the_project_end_refuse_ll1;
    LinearLayout the_project_end_refuse_ll2;
    LinearLayout the_project_end_refuse_ll3;
    LinearLayout the_project_end_refuse_ll4;
    LinearLayout the_project_end_refuse_ll5;
    LinearLayout the_project_end_refuse_ll6;
    InitiatedAdapter adapter;
    private List<InitiatedBean.DataBean.RowsBean> rows;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuse_the_project_end);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        the_project_end_refuse_return = findViewById(R.id.the_project_end_refuse_return);
        the_project_end_refuse_rv = findViewById(R.id.the_project_end_refuse_rv);
        the_project_end_refuse_ll1 = findViewById(R.id.the_project_end_refuse_ll1);
        the_project_end_refuse_ll2 = findViewById(R.id.the_project_end_refuse_ll2);
        the_project_end_refuse_ll3 = findViewById(R.id.the_project_end_refuse_ll3);
        the_project_end_refuse_ll4 = findViewById(R.id.the_project_end_refuse_ll4);
        the_project_end_refuse_ll5 = findViewById(R.id.the_project_end_refuse_ll5);
        the_project_end_refuse_ll6 = findViewById(R.id.the_project_end_refuse_ll6);

        initData(1);

        the_project_end_refuse_return.setOnClickListener(this);
        the_project_end_refuse_ll1.setOnClickListener(this);
        the_project_end_refuse_ll3.setOnClickListener(this);
        the_project_end_refuse_ll5.setOnClickListener(this);
    }

    @SuppressLint("WrongConstant")
    private void initData(int position) {

        adapter = new InitiatedAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        the_project_end_refuse_rv.setLayoutManager(manager);
        if(FinalContents.getJJ().equals("待我审核")){
            url = "http://39.98.173.250:8080/fangfang/app/v1/specialSelect/toAuditList?userId=" + FinalContents.getUserID() + "&type=2&status=" + position;
        }else if(FinalContents.getJJ().equals("我发起的审核")){
            url = "http://39.98.173.250:8080/fangfang/app/v1/specialSelect/myExaminelist?userId=" + FinalContents.getUserID() + "&type=2&status=" + position;
        }

        OkHttpPost okHttpPost = new OkHttpPost(url);
        String post = okHttpPost.post();

        Gson gson = new Gson();
        InitiatedBean initiatedBean = gson.fromJson(post, InitiatedBean.class);
        rows = initiatedBean.getData().getRows();

        adapter.setRows(rows);
        adapter.setOnItemClick(this);
        the_project_end_refuse_rv.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //            TODO 返回上一层
            case R.id.the_project_end_refuse_return:
                finish();
                break;
            //TODO 退筹
            case R.id.the_project_end_refuse_ll1:
                initData(1);
                Log.i("MyCL", "退筹");
                the_project_end_refuse_ll2.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);
                break;
            //            TODO 调单
            case R.id.the_project_end_refuse_ll3:
                initData(2);
                Log.i("MyCL", "调单");
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);
                break;
            //            TODO 退单
            case R.id.the_project_end_refuse_ll5:
                initData(3);
                Log.i("MyCL", "退单");
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void Item(int position) {

        Intent intent = new Intent(this, InitiatedActivity.class);
        FinalContents.setCustomerID(rows.get(position).getCustomerId());
        startActivity(intent);

    }
}
