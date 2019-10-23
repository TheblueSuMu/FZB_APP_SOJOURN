package com.xcy.fzb.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.RecyclerAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.HotBean;
import com.xcy.fzb.project_side.presente.OkHttpPost;
import com.xcy.fzb.project_side.presente.StatusBar;

import java.util.ArrayList;
import java.util.List;

public class SearchInterfaceActivity extends AppCompatActivity implements View.OnClickListener {


    private LinearLayout search_img;
    private EditText search_edit_text;
    private LinearLayout search_l1;
    private LinearLayout search_l2;
    private LinearLayout search_ll1;
    private LinearLayout search_ll2;
    private RelativeLayout nofound;
    private TextView textView;

    private String hotUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectList?";

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private String text = "";
    private String url = hotUrl+"&userId="+ FinalContents.getUserID()+"&city="+FinalContents.getCityID()+"&projectType=3"+"&searchName="+text;
    private String project = "";
    private List<HotBean.DataBean.RowsBean> hotlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_interface);
        StatusBar.makeStatusBarTransparent(this);

        project = getIntent().getStringExtra("project");

        initView();
    }

    private void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        search_img = findViewById(R.id.search_img);
        search_edit_text = findViewById(R.id.search_edit_text);
        search_l1 = findViewById(R.id.search_l1);
        search_l2 = findViewById(R.id.search_l2);
        search_ll1 = findViewById(R.id.search_ll1);
        search_ll2 = findViewById(R.id.search_ll2);
        nofound = findViewById(R.id.nofound);
        textView = findViewById(R.id.search_text);

        recyclerView = findViewById(R.id.search_recyler);

        search_img.setOnClickListener(this);
        search_l1.setOnClickListener(this);
        search_l2.setOnClickListener(this);
        text = search_edit_text.getText().toString();


        search_edit_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    recyclerViewData(url);
                    return true;
                }
                return false;
            }

        });

    }

    @SuppressLint("WrongConstant")
    private void recyclerViewData(String url){

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
            hotlist = hotBeanData.getRows();
            //在此处修改布局排列方向
            if (hotlist.size() == 0) {
                recyclerAdapter.notifyDataSetChanged();
                nofound.setVisibility(View.VISIBLE);
            }else {
                nofound.setVisibility(View.GONE);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerAdapter = new RecyclerAdapter(hotlist);
                recyclerAdapter.setProject(project);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }
        }else {
            Toast.makeText(this, "没有获取到数据", Toast.LENGTH_SHORT).show();
        }
        textView.setVisibility(View.GONE);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动  
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时  
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        //加载更多功能的代码
                        textView.setVisibility(View.VISIBLE);
                    }
                }else {
                    textView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向  
                if (dx > 0) {
                    //大于0表示正在向右滚动  
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向左滚动  
                    isSlidingToLast = false;
                }
            }
        });

        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {

            }
        });
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.search_img:
                finish();
                break;
            case R.id.search_l1:
                search_ll1.setVisibility(View.VISIBLE);
                search_ll2.setVisibility(View.INVISIBLE);
                url = hotUrl+"&userId="+ FinalContents.getUserID()+"&city="+FinalContents.getCityID()+"&searchName="+search_edit_text.getText()+"&projectType=3";
                recyclerViewData(url);
                break;
            case R.id.search_l2:
                search_ll2.setVisibility(View.VISIBLE);
                search_ll1.setVisibility(View.INVISIBLE);
                url = hotUrl+"&userId="+ FinalContents.getUserID()+"&city="+FinalContents.getCityID()+"&searchName="+search_edit_text.getText()+"&projectType=2";
                recyclerViewData(url);
                break;
        }

    }
}
