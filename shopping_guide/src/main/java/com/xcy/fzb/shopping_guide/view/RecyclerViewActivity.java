package com.xcy.fzb.shopping_guide.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.adapter.RecyclerAdapter;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.HotBean;
import com.xcy.fzb.shopping_guide.persente.MyLinearLayoutManager;
import com.xcy.fzb.shopping_guide.persente.OkHttpPost;
import com.xcy.fzb.shopping_guide.persente.StatusBar;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AllActivity {
    private TextView title;
    private ImageView back;
    private RecyclerView recyclerView;
    private List<HotBean.DataBean.RowsBean> hotlist = new ArrayList<>();
    private String hotUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectList?";
    private String nation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        StatusBar.makeStatusBarTransparent(this);

        nation = getIntent().getStringExtra("nation");
        initfvb();
        initView();
    }

    private void initfvb(){
        title = findViewById(R.id.recycler_title);
        back = findViewById(R.id.recycler_back);
        recyclerView = findViewById(R.id.recycler_rv);
        title.setText(nation+"项目");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView(){
        String url = hotUrl+"&userId="+ FinalContents.getUserID()+"&city="+ FinalContents.getCityID()+"&nation="+nation;

        Log.i("bbb","用户名ID："+FinalContents.getUserID());
        Log.i("bbb","城市ID："+FinalContents.getCityID());

        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();
        Log.i("aaa","项目接收值"+data);

        String substring = data.substring(9, 10);

        if (substring.equals("1")) {
            HotBean hotBean = new Gson().fromJson(data, HotBean.class);
            HotBean.DataBean hotBeanData = hotBean.getData();
            hotlist = hotBeanData.getRows();

            if (hotlist.size() == 0) {
                recyclerView.setVisibility(View.GONE);
            }else {
                //在此处修改布局排列方向
                recyclerView.setVisibility(View.VISIBLE);
                MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(RecyclerViewActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(hotlist);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }
        }else {
            Toast.makeText(RecyclerViewActivity.this, "没有获取到数据", Toast.LENGTH_SHORT).show();
        }
    }
}
