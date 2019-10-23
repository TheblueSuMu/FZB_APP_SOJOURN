package com.xcy.fzb.shopping_guide.view;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.adapter.DynamicAdapter;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.DynamicBean;
import com.xcy.fzb.shopping_guide.persente.OkHttpPost;
import com.xcy.fzb.shopping_guide.persente.StatusBar;

import java.util.List;

public class BuildingDynamicActivity extends AppCompatActivity implements DynamicAdapter.PingLun, DynamicAdapter.FuZhi, DynamicAdapter.LianxiClick {

    LinearLayout dynamic_return;
    RecyclerView dynamic_rv;
    private String dynamicUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectHousesDynamicsList?";
    private DynamicBean.DataBean dynamicBeanData;
    private List<DynamicBean.DataBean.RowsBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_dynamic);
        StatusBar.makeStatusBarTransparent(this);

        initView();

    }

    private void initView() {

        dynamic_return = findViewById(R.id.dynamic_return);
        dynamic_rv = findViewById(R.id.dynamic_rv);
//        TODO 返回上一级
        dynamic_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        TODO 数据处理类
        initData();

    }

    @SuppressLint("WrongConstant")
    private void initData() {
        String url = dynamicUrl + "&userId=" + FinalContents.getUserID() + "&projectId=" + FinalContents.getProjectID();

        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();
        Log.i("mlgb", "楼盘动态列表：" + data);
        String substring = data.substring(9, 10);

        if (substring.equals("1")) {
            DynamicBean dynamicBean = new Gson().fromJson(data, DynamicBean.class);
            dynamicBeanData = dynamicBean.getData();
            list = dynamicBeanData.getRows();

            FinalContents.setTargetId(FinalContents.getProjectID());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            dynamic_rv.setLayoutManager(layoutManager);
            DynamicAdapter recyclerAdapter = new DynamicAdapter(list);
            recyclerAdapter.setDynamicClick(this);
            recyclerAdapter.setPingLun(this);
            recyclerAdapter.setFuZhi(this);

            recyclerAdapter.setBuiling(1);
            dynamic_rv.setAdapter(recyclerAdapter);
        } else {
            Toast.makeText(this, "没有获取到数据", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void pingClick(int position) {
        FinalContents.setTargetId(list.get(position).getId());
        Log.i("MyCL", "ID：" + list.get(position).getId());
        Intent intent = new Intent(BuildingDynamicActivity.this, MessageCommentActivity.class);
        intent.putExtra("headPortrait", list.get(position).getCreateBy().getPhoto());
        intent.putExtra("title", list.get(position).getCreateBy().getName());
        intent.putExtra("message", list.get(position).getContent());
        intent.putExtra("img", list.get(position).getImgUrl());
        startActivity(intent);
    }

    @Override
    public void FuZhi(int position) {
        ClipboardManager clip = (ClipboardManager) BuildingDynamicActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(list.get(position).getContent() + "");
        Toast.makeText(BuildingDynamicActivity.this, "复制成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Click(int position) {

        String phone = list.get(position).getCreateBy().getPhone();
        if (phone.equals("")) {
            Toast.makeText(BuildingDynamicActivity.this, "暂无电话信息，无法拨打", Toast.LENGTH_SHORT).show();
        } else {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));//跳转到拨号界面，同时传递电话号码
            startActivity(dialIntent);
        }

    }
}
