package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.ClientParticularsAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.ClientParticularsBean;
import com.xcy.fzb.captain_team.persente.OkHttpPost;
import com.xcy.fzb.captain_team.persente.StatusBar;

import java.util.List;

public class ClientParticularsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView client_particulars_img;
    ImageView client_particulars_return;
    ImageView client_particulars_call;
    TextView client_particulars_name;
    TextView client_particulars_phone;
    RecyclerView client_particulars_rv;

    ClientParticularsAdapter clientParticularsAdapter;
    private ClientParticularsBean.DataBean.InfoDataBean infoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_particulars);
        StatusBar.makeStatusBarTransparent(this);

        initView();

    }

    private void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        client_particulars_img = findViewById(R.id.client_particulars_img);
        client_particulars_return = findViewById(R.id.client_particulars_return);
        client_particulars_call = findViewById(R.id.client_particulars_call);
        client_particulars_name = findViewById(R.id.client_particulars_name);
        client_particulars_phone = findViewById(R.id.client_particulars_phone);
        client_particulars_rv = findViewById(R.id.client_particulars_rv);

        client_particulars_img.setOnClickListener(this);
        client_particulars_return.setOnClickListener(this);

        initData();

    }

    @SuppressLint("WrongConstant")
    private void initData() {

        String url = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/customerDetails?userId=" + FinalContents.getUserID() + "&customerId=" + FinalContents.getCustomerID();

        OkHttpPost okHttpPost = new OkHttpPost(url);
        String data = okHttpPost.post();

        Gson gson = new Gson();
        ClientParticularsBean clientParticularsBean = gson.fromJson(data, ClientParticularsBean.class);
        infoData = clientParticularsBean.getData().getInfoData();

        Glide.with(ClientParticularsActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(client_particulars_img);
        client_particulars_name.setText(infoData.getCustomerName() + "");
        client_particulars_phone.setText(infoData.getContactsPhone1() + "");

//        TODO RecyclerView
        List<ClientParticularsBean.DataBean.ListDataBean> listData = clientParticularsBean.getData().getListData();
        clientParticularsAdapter = new ClientParticularsAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        client_particulars_rv.setLayoutManager(manager);
        clientParticularsAdapter.setListData(listData);
        client_particulars_rv.setAdapter(clientParticularsAdapter);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.client_particulars_img:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + infoData.getContacts1()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;
            case R.id.client_particulars_return:
                finish();
                break;
        }

    }
}
