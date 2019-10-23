package com.xcy.fzb.shopping_guide.view;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.adapter.MessageCommentAdapter;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.CommentBean;
import com.xcy.fzb.shopping_guide.modle.CommentZanBean;
import com.xcy.fzb.shopping_guide.modle.DynamicDetailsBean;
import com.xcy.fzb.shopping_guide.persente.OkHttpPost;
import com.xcy.fzb.shopping_guide.persente.StatusBar;

import java.util.List;

public class MessageCommentActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView comment_return;
    ImageView comment_buddha;
    ImageView comment_img;
    TextView comment_title;
    TextView comment_message;
    RecyclerView comment_rv;
    EditText comment_et;
    ImageView comment_zan_img;
    ImageView comment_fb_img;
    ImageView particulars_xiao_img;
    private List<DynamicDetailsBean.DataBean.CommentListBean> commentList;
    private MessageCommentAdapter adapter;
    private String headPortrait;
    private String title;
    private String message;
    private String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_comment);
        StatusBar.makeStatusBarTransparent(this);

        initView();
        initET();

    }

    private void initET() {
        comment_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                int num = 0;
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    Log.i("MyCL", "重复打印");
                    //处理事件
                    if (num == 0) {
                        num++;
                        initDataEt();
                    }
                    return true;
                }
                return false;
            }

        });

    }

    private void initView() {
//        TODO 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        comment_return = findViewById(R.id.comment_return);
        comment_buddha = findViewById(R.id.comment_buddha);
        comment_img = findViewById(R.id.comment_img);
        comment_title = findViewById(R.id.comment_title);
        comment_message = findViewById(R.id.comment_message);
        comment_rv = findViewById(R.id.comment_rv);
        comment_et = findViewById(R.id.comment_et);
        comment_zan_img = findViewById(R.id.comment_zan_img);
        comment_fb_img = findViewById(R.id.comment_fb_img);
        particulars_xiao_img = findViewById(R.id.particulars_xiao_img);

        Glide.with(this).load(R.mipmap.z3).into(particulars_xiao_img);

        comment_return.setOnClickListener(this);
        comment_zan_img.setOnClickListener(this);
        comment_fb_img.setOnClickListener(this);

        headPortrait = getIntent().getStringExtra("headPortrait");
        title = getIntent().getStringExtra("title");
        message = getIntent().getStringExtra("message");
        img = getIntent().getStringExtra("img");

        Glide.with(MessageCommentActivity.this).load("http://39.98.173.250:8080" + headPortrait).into(comment_buddha);
        if (message.equals("")) {
            comment_message.setVisibility(View.GONE);
        } else {
            comment_message.setVisibility(View.VISIBLE);
        }

        comment_title.setText(title);
        comment_message.setText(message);
        if (img.equals("")) {
            comment_img.setVisibility(View.GONE);
        } else {
            comment_img.setVisibility(View.VISIBLE);
            Glide.with(MessageCommentActivity.this).load("http://39.98.173.250:8080" + img).into(comment_img);
        }

        initData();

    }

    private void initDataEt() {
        String et = comment_et.getText().toString();

        String fburl = "http://39.98.173.250:8080/fangfang/app/v1/commonUpdate/housesDynamicCommentLike?type=2" + "&comment=" + et + "&targetId=" + FinalContents.getTargetId() + "&pid=" + FinalContents.getUserID() + "&userId=" + FinalContents.getUserID();

        OkHttpPost fbokHttpPost = new OkHttpPost(fburl);
        String fbdata = fbokHttpPost.post();

        Gson gson1 = new Gson();
        CommentBean commentBean = gson1.fromJson(fbdata, CommentBean.class);
        if (commentBean.getData().getStatus().equals("2")) {
            Toast.makeText(MessageCommentActivity.this, commentBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
            comment_et.setText("");
        } else {
            Toast.makeText(MessageCommentActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
            comment_et.setText("");
        }
        initData();
    }

    @SuppressLint("WrongConstant")
    private void initData() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        comment_rv.setLayoutManager(manager);

        String url = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/housesDynamicDetails?" + "userId=" + FinalContents.getUserID() + "&housesDynamicId=" + FinalContents.getTargetId();//+FinalContents.getTargetId()

        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();

        Gson gson = new Gson();
        DynamicDetailsBean dynamicDetailsBean = gson.fromJson(data, DynamicDetailsBean.class);

        commentList = dynamicDetailsBean.getData().getCommentList();
        adapter = new MessageCommentAdapter();
        adapter.setCommentList(commentList);
        comment_rv.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO  返回上一层
            case R.id.comment_return:
                finish();
                break;
//                TODO 点赞
            case R.id.comment_zan_img:
//        TODO 改
                String url = "http://39.98.173.250:8080/fangfang/app/v1/commonUpdate/housesDynamicCommentLike?" + "type=1" + "&targetId=" + FinalContents.getTargetId() + "&userId=" + FinalContents.getUserID();//+FinalContents.getTargetId()

                OkHttpPost okHttpPost = new OkHttpPost(url);
                String data = okHttpPost.post();

                Gson gson = new Gson();
                CommentZanBean circleBean = gson.fromJson(data, CommentZanBean.class);
                if (circleBean.getData().getStatus().equals("1")) {
                    Toast.makeText(MessageCommentActivity.this, "取消点赞", Toast.LENGTH_SHORT).show();
                } else if (circleBean.getData().getStatus().equals("0")) {
                    Toast.makeText(MessageCommentActivity.this, "点赞成功", Toast.LENGTH_SHORT).show();
                }
                break;
//                TODO 发布
            case R.id.comment_fb_img:

                ClipboardManager clip = (ClipboardManager) MessageCommentActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                clip.setText(message);
                Toast.makeText(MessageCommentActivity.this, "复制成功", Toast.LENGTH_SHORT).show();

                break;
        }

    }
}
