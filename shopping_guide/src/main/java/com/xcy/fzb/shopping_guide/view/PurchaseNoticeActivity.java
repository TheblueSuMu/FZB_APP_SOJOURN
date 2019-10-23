package com.xcy.fzb.shopping_guide.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.fragment.CityFragment;
import com.xcy.fzb.shopping_guide.fragment.EnterpriseFragment;
import com.xcy.fzb.shopping_guide.fragment.FlowFragment;
import com.xcy.fzb.shopping_guide.modle.HouseBean;
import com.xcy.fzb.shopping_guide.persente.OkHttpPost;
import com.xcy.fzb.shopping_guide.persente.StatusBar;


//TODO 购房须知
public class PurchaseNoticeActivity extends AppCompatActivity implements View.OnClickListener {


    private LinearLayout notice_img;
    private TextView notice_t1;
    private TextView notice_t2;
    private TextView notice_t3;
    private LinearLayout notice_l1;
    private LinearLayout notice_l2;
    private LinearLayout notice_l3;
    private LinearLayout notice_fl;
    private Button notice_btn;

    private TextView title;
    private TextView time;
    private TextView content;
    private String houseTypeUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectPropertyHouseList?";
    private String talkToolId = "";

    FlowFragment flowFragment = new FlowFragment();
    CityFragment cityFragment = new CityFragment();
    EnterpriseFragment enterpriseFragment = new EnterpriseFragment();
    private HouseBean.DataBean houseDataData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_notice);
        StatusBar.makeStatusBarTransparent(this);

        init();
        initView();

    }

    private void init(){
        String houseUrl = houseTypeUrl+"&userId="+ FinalContents.getUserID()+"&projectId="+FinalContents.getProjectID()+"&status=1";
        Log.i("bbb","项目ID："+FinalContents.getProjectID());
        OkHttpPost houseOkHttpPost = new OkHttpPost(houseUrl);
        String houseData = houseOkHttpPost.post();
        String houseSubstring = houseData.substring(9, 10);

        if (houseSubstring.equals("1")) {
            HouseBean houseBean = new Gson().fromJson(houseData, HouseBean.class);
            houseDataData = houseBean.getData();

        }else {
            Toast.makeText(this, "没有获取到数据", Toast.LENGTH_SHORT).show();
        }
    }

    public void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        notice_img = findViewById(R.id.notice_img);
        notice_t1 = findViewById(R.id.notice_t1);
        notice_t2 = findViewById(R.id.notice_t2);
        notice_t3 = findViewById(R.id.notice_t3);
        notice_l1 = findViewById(R.id.notice_l1);
        notice_l2 = findViewById(R.id.notice_l2);
        notice_l3 = findViewById(R.id.notice_l3);
        notice_fl = findViewById(R.id.notice_fl);
        notice_btn = findViewById(R.id.notice_btn);

        title = findViewById(R.id.notice_title);
        time = findViewById(R.id.notice_time);
        content = findViewById(R.id.notice_content);


        notice_l1.setVisibility(View.VISIBLE);
        notice_l2.setVisibility(View.INVISIBLE);
        notice_l3.setVisibility(View.INVISIBLE);
        title.setText(houseDataData.getPropertyHouseList().get(0).getTitle());
        time.setText(houseDataData.getPropertyHouseList().get(0).getCreateDate());
        content.setText("       "+houseDataData.getPropertyHouseList().get(0).getContent());
        talkToolId = houseDataData.getPropertyHouseList().get(0).getId();

        notice_img.setOnClickListener(this);
        notice_t1.setOnClickListener(this);
        notice_t2.setOnClickListener(this);
        notice_t3.setOnClickListener(this);
        notice_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.notice_img) {
            finish();
        } else if (id == R.id.notice_t1) {
            notice_l1.setVisibility(View.VISIBLE);
            notice_l2.setVisibility(View.INVISIBLE);
            notice_l3.setVisibility(View.INVISIBLE);
            title.setText(houseDataData.getPropertyHouseList().get(0).getTitle());
            time.setText(houseDataData.getPropertyHouseList().get(0).getCreateDate());
            content.setText("       "+houseDataData.getPropertyHouseList().get(0).getContent());
            talkToolId = houseDataData.getPropertyHouseList().get(0).getId();
        } else if (id == R.id.notice_t2) {

            notice_l1.setVisibility(View.INVISIBLE);
            notice_l2.setVisibility(View.VISIBLE);
            notice_l3.setVisibility(View.INVISIBLE);
            title.setText(houseDataData.getPropertyHouseList().get(1).getTitle());
            time.setText(houseDataData.getPropertyHouseList().get(1).getCreateDate());
            content.setText("       "+houseDataData.getPropertyHouseList().get(1).getContent());
            talkToolId = houseDataData.getPropertyHouseList().get(1).getId();
        } else if (id == R.id.notice_t3) {

            notice_l1.setVisibility(View.INVISIBLE);
            notice_l2.setVisibility(View.INVISIBLE);
            notice_l3.setVisibility(View.VISIBLE);
            title.setText(houseDataData.getPropertyHouseList().get(2).getTitle());
            time.setText(houseDataData.getPropertyHouseList().get(2).getCreateDate());
            content.setText("       "+houseDataData.getPropertyHouseList().get(2).getContent());
            talkToolId = houseDataData.getPropertyHouseList().get(2).getId();
        } else if (id == R.id.notice_btn) {
//            FinalContents.showShare(title.getText().toString(),"http://test.fangzuobiao.com:88/sellingPoint?"+"&userId="+FinalContents.getUserID()+"&talkToolId="+talkToolId,content.getText().toString(),"http://39.98.173.250:8080"+houseDataData.getPropertyHouseList().get(0).getShareIcon(),"http://test.fangzuobiao.com:88/sellingPoint?"+"&userId="+FinalContents.getUserID()+"&talkToolId="+talkToolId,this);
        }
    }


}
