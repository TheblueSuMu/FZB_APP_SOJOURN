package com.xcy.fzb.shopping_guide.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.adapter.PhotoTileAdapter;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.PhotoBean;
import com.xcy.fzb.shopping_guide.persente.OkHttpPost;
import com.xcy.fzb.shopping_guide.persente.StatusBar;

import java.util.List;

public class PhotoTileActivity extends AppCompatActivity {
    private LinearLayout photo_img;
    private RecyclerView photoTileRv;
    private String photoUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectPhoto?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_tile);
        StatusBar.makeStatusBarTransparent(this);

        initView();
        initRv();
    }

    private void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        photo_img = findViewById(R.id.photo_tile_back);;
        photoTileRv = findViewById(R.id.photo_tile_rv);

        photo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void initRv(){
        String url = photoUrl+"&userId="+ FinalContents.getUserID()+"&projectId="+FinalContents.getProjectID();
        Log.i("aaa","项目ID："+ FinalContents.getProjectID());
        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();
        Log.i("qssm","项目图片："+data);

        PhotoBean photoBean = new Gson().fromJson(data, PhotoBean.class);
        List<PhotoBean.DataBean> list = photoBean.getData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        photoTileRv.setLayoutManager(layoutManager);
        PhotoTileAdapter recyclerAdapter = new PhotoTileAdapter(list);
        photoTileRv.setAdapter(recyclerAdapter);
    }
}
