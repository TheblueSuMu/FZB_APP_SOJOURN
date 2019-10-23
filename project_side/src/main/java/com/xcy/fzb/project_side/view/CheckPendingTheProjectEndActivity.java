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
import com.xcy.fzb.project_side.adapter.CheckPendingTheProjectEndAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.CheckPendingBean;
import com.xcy.fzb.project_side.presente.OkHttpPost;
import com.xcy.fzb.project_side.presente.StatusBar;

import java.util.List;

//TODO 待我审核
public class CheckPendingTheProjectEndActivity extends AppCompatActivity implements View.OnClickListener, CheckPendingTheProjectEndAdapter.OnItemClick {

    ImageView check_pending_the_project_end_return;
    TextView check_pending_the_project_end_tv;
    LinearLayout check_pending_the_project_end_ll1;
    LinearLayout check_pending_the_project_end_ll2;
    LinearLayout check_pending_the_project_end_ll3;
    LinearLayout check_pending_the_project_end_ll4;
    LinearLayout check_pending_the_project_end_ll5;
    LinearLayout check_pending_the_project_end_ll6;
    LinearLayout check_pending_the_project_end_ll7;
    LinearLayout check_pending_the_project_end_ll8;
    RecyclerView check_pending_the_project_end_rv;
    CheckPendingTheProjectEndAdapter adapter;
    private List<CheckPendingBean.DataBean.RowsBean> rows;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pending_the_project_end);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        check_pending_the_project_end_return = findViewById(R.id.check_pending_the_project_end_return);
        check_pending_the_project_end_tv = findViewById(R.id.check_pending_the_project_end_tv);
        check_pending_the_project_end_ll1 = findViewById(R.id.check_pending_the_project_end_ll1);
        check_pending_the_project_end_ll2 = findViewById(R.id.check_pending_the_project_end_ll2);
        check_pending_the_project_end_ll3 = findViewById(R.id.check_pending_the_project_end_ll3);
        check_pending_the_project_end_ll4 = findViewById(R.id.check_pending_the_project_end_ll4);
        check_pending_the_project_end_ll5 = findViewById(R.id.check_pending_the_project_end_ll5);
        check_pending_the_project_end_ll6 = findViewById(R.id.check_pending_the_project_end_ll6);
        check_pending_the_project_end_ll7 = findViewById(R.id.check_pending_the_project_end_ll7);
        check_pending_the_project_end_ll8 = findViewById(R.id.check_pending_the_project_end_ll8);
        check_pending_the_project_end_rv = findViewById(R.id.check_pending_the_project_end_rv);

        check_pending_the_project_end_return.setOnClickListener(this);
        check_pending_the_project_end_tv.setOnClickListener(this);
        check_pending_the_project_end_ll1.setOnClickListener(this);
        check_pending_the_project_end_ll3.setOnClickListener(this);
        check_pending_the_project_end_ll5.setOnClickListener(this);
        check_pending_the_project_end_ll7.setOnClickListener(this);

        initData(1);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.check_pending_the_project_end_return:
                finish();
                break;
            //            TODO 拒绝记录
            case R.id.check_pending_the_project_end_tv:
                Intent intent = new Intent(CheckPendingTheProjectEndActivity.this, RefuseTheProjectEndActivity.class);
                FinalContents.setJJ("待我审核");
                startActivity(intent);
                break;
            //            TODO 报备
            case R.id.check_pending_the_project_end_ll1:

                initData(1);

                check_pending_the_project_end_ll2.setVisibility(View.VISIBLE);
                check_pending_the_project_end_ll4.setVisibility(View.GONE);
                check_pending_the_project_end_ll6.setVisibility(View.GONE);
                check_pending_the_project_end_ll8.setVisibility(View.GONE);

                break;
            //            TODO 到访
            case R.id.check_pending_the_project_end_ll3:

                initData(2);

                check_pending_the_project_end_ll2.setVisibility(View.GONE);
                check_pending_the_project_end_ll4.setVisibility(View.VISIBLE);
                check_pending_the_project_end_ll6.setVisibility(View.GONE);
                check_pending_the_project_end_ll8.setVisibility(View.GONE);
                break;
            //            TODO 认筹
            case R.id.check_pending_the_project_end_ll5:

                initData(3);

                check_pending_the_project_end_ll2.setVisibility(View.GONE);
                check_pending_the_project_end_ll4.setVisibility(View.GONE);
                check_pending_the_project_end_ll6.setVisibility(View.VISIBLE);
                check_pending_the_project_end_ll8.setVisibility(View.GONE);

                break;
            //            TODO 成功
            case R.id.check_pending_the_project_end_ll7:

                initData(4);

                check_pending_the_project_end_ll2.setVisibility(View.GONE);
                check_pending_the_project_end_ll4.setVisibility(View.GONE);
                check_pending_the_project_end_ll6.setVisibility(View.GONE);
                check_pending_the_project_end_ll8.setVisibility(View.VISIBLE);

                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initData(int position) {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        check_pending_the_project_end_rv.setLayoutManager(manager);
        adapter = new CheckPendingTheProjectEndAdapter();

        String url = FinalContents.getBaseUrl() + "specialSelect/toAuditList?userId=" + FinalContents.getUserID() + "&type=1&status=" + position;

        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();

        Gson gson = new Gson();
        CheckPendingBean checkPendingBean = gson.fromJson(data, CheckPendingBean.class);
        rows = checkPendingBean.getData().getRows();
        adapter.setRows(rows);
        adapter.setOnItemClick(this);
        check_pending_the_project_end_rv.setAdapter(adapter);

    }

    @Override
    public void itemItem(int position) {

        intent = new Intent(CheckPendingTheProjectEndActivity.this, CheckPendingActivity.class);

        if (rows.get(position).getRelatedData().equals("认筹") || rows.get(position).getRelatedData().equals("成功")) {
            intent.putExtra("Mycheck", "2");
        } else {
            intent.putExtra("Mycheck", "1");
        }
        if (check_pending_the_project_end_ll2.getVisibility() == View.VISIBLE) {
            intent.putExtra("name", "报备");
            FinalContents.setNumS(1);
        } else if (check_pending_the_project_end_ll4.getVisibility() == View.VISIBLE) {
            FinalContents.setNumS(3);
            intent.putExtra("name", "到访");
        } else if (check_pending_the_project_end_ll6.getVisibility() == View.VISIBLE) {
            FinalContents.setNumS(3);
            intent.putExtra("name", "认筹");
        } else if (check_pending_the_project_end_ll8.getVisibility() == View.VISIBLE) {
            FinalContents.setNumS(4);
            intent.putExtra("name", "失效");
        }

        FinalContents.setPreparationId(rows.get(position).getPreparationId());
        FinalContents.setCustomerID(rows.get(position).getCustomerId());
        FinalContents.setStatus(rows.get(position).getStatus());
        Log.i("MyCL", "getPreparationId：" + rows.get(position).getPreparationId());
        Log.i("MyCL", "getCustomerId：" + rows.get(position).getCustomerId());
        startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        initData(FinalContents.getNumS());

    }


}
