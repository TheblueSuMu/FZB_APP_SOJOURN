package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.RecyclerSAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.HotBean;
import com.xcy.fzb.project_side.presente.OkHttpPost;
import com.xcy.fzb.project_side.presente.StatusBar;

import java.util.List;

public class CollectActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView collect_img;
    private LinearLayout collect_l1;
    private LinearLayout collect_l2;
    private LinearLayout collect_ll1;
    private LinearLayout collect_ll2;

    private String hotUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectList?";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        StatusBar.makeStatusBarTransparent(this);
        initView();
        recyclerViewData();

    }

    private void initView() {

        collect_img = findViewById(R.id.collect_img);
        collect_l1 = findViewById(R.id.collect_l1);
        collect_l2 = findViewById(R.id.collect_l2);
        collect_ll1 = findViewById(R.id.collect_ll1);
        collect_ll2 = findViewById(R.id.collect_ll2);

        recyclerView = findViewById(R.id.collect_recyler);

        collect_img.setOnClickListener(this);
        collect_l1.setOnClickListener(this);
        collect_l2.setOnClickListener(this);

    }

    @SuppressLint("WrongConstant")
    private void recyclerViewData(){
        String url = hotUrl+"&userId="+ FinalContents.getUserID()+"&city="+FinalContents.getCityID();

        Log.i("bbb","用户名ID："+FinalContents.getUserID());
        Log.i("bbb","城市ID："+FinalContents.getCityID());
        Log.i("bbb","城市公司ID："+FinalContents.getCityID());

        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();
        Log.i("aaa","项目接收值"+data);

        String substring = data.substring(9, 10);

        if (substring.equals("1")) {
            HotBean hotBean = new Gson().fromJson(data, HotBean.class);
            HotBean.DataBean hotBeanData = hotBean.getData();
            List<HotBean.DataBean.RowsBean> hotlist = hotBeanData.getRows();
            Log.i("aaa","项目名称地址："+hotlist.get(0).getProjectName());

//            Log.i("aaa","图片"+imglist.get(0).getCoverImg());

            //在此处修改布局排列方向
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            RecyclerSAdapter recyclerAdapter = new RecyclerSAdapter(hotlist);
            recyclerView.setAdapter(recyclerAdapter);

        }else {
            Toast.makeText(this, "没有获取到数据", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collect_img:
                finish();
                break;
            case R.id.collect_l1:
                collect_ll1.setVisibility(View.VISIBLE);
                collect_ll2.setVisibility(View.INVISIBLE);
                recyclerViewData();
                break;
            case R.id.collect_l2:
                collect_ll1.setVisibility(View.INVISIBLE);
                collect_ll2.setVisibility(View.VISIBLE);
                recyclerViewData();
                break;
        }
    }
}
