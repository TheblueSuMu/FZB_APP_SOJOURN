package com.xcy.fzb.captain_team.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.BackToBean;
import com.xcy.fzb.captain_team.persente.OkHttpPost;
import com.xcy.fzb.captain_team.persente.StatusBar;

import org.json.JSONException;
import org.json.JSONObject;

//TODO 填写退筹说明
public class BackToRaiseThatActivity extends AppCompatActivity {


    ImageView back_to_raise_that_return;

    EditText back_to_raise_that_et;

    Button back_to_raise_that_btn;
    private String message;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_to_raise_that);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        back_to_raise_that_return = findViewById(R.id.back_to_raise_that_return);
        back_to_raise_that_et = findViewById(R.id.back_to_raise_that_et);
        back_to_raise_that_btn = findViewById(R.id.back_to_raise_that_btn);
 back_to_raise_that_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        back_to_raise_that_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message = back_to_raise_that_et.getText().toString();
                url = FinalContents.getBaseUrl()+"specialUpdate/refundMoneyApply?preparationId=" + FinalContents.getPreparationId() + "&earnestComment=" + message + "&userId=" + FinalContents.getUserID();
                Log.i("MyCL","1");
                try {
                    Log.i("MyCL","2");
                    OkHttpPost okHttpPost = new OkHttpPost(url);
                    String data = okHttpPost.post();
                    JSONObject jsonObject = new JSONObject(data);
                    String msg = jsonObject.getString("msg");
                    if(msg.equals("成功")){

                        Gson gson = new Gson();
                        BackToBean listOfBean = gson.fromJson(data, BackToBean.class);
                        if(listOfBean.getMsg().equals("成功")){
                            Toast.makeText(BackToRaiseThatActivity.this,listOfBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(BackToRaiseThatActivity.this,listOfBean.getData().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(BackToRaiseThatActivity.this,msg,Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });

    }
}
