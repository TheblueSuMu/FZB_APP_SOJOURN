package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.ListOfBean;
import com.xcy.fzb.project_side.presente.OkHttpPost;
import com.xcy.fzb.project_side.presente.StatusBar;

//TODO 填写退单说明
public class ListOfBackActivity extends AppCompatActivity {

    ImageView list_of_back_return;

    EditText list_of_back_et;

    Button list_of_back_btn;
    private String message;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_back);

        initView();

    }

    private void initView() {



        StatusBar.makeStatusBarTransparent(this);

        list_of_back_return = findViewById(R.id.list_of_back_return);
        list_of_back_et = findViewById(R.id.list_of_back_et);
        list_of_back_btn = findViewById(R.id.list_of_back_btn);

        list_of_back_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        list_of_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message = list_of_back_et.getText().toString();
                url = FinalContents.getBaseUrl()+"specialUpdate/chargebackApply?userId=" + FinalContents.getUserID() + "&preparationId=" + FinalContents.getPreparationId() + "&explain=" + message;
                OkHttpPost okHttpPost = new OkHttpPost(url);
                String data = okHttpPost.post();

                Gson gson = new Gson();
                ListOfBean listOfBean = gson.fromJson(data, ListOfBean.class);
                if(listOfBean.getMsg().equals("成功")){
                    Toast.makeText(ListOfBackActivity.this,listOfBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ListOfBackActivity.this,listOfBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
