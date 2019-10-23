package com.xcy.fzb.project_side.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.presente.StatusBar;

//TODO 拒绝原因
public class TheReasonForRefusalActivity extends AppCompatActivity {

    ImageView the_reason_for_refusal_return;

    EditText the_reason_for_refusal_et;

    Button the_reason_for_refusal_btn;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_reason_for_refusal);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        the_reason_for_refusal_return = findViewById(R.id.the_reason_for_refusal_return);
        the_reason_for_refusal_et = findViewById(R.id.the_reason_for_refusal_et);
        the_reason_for_refusal_btn = findViewById(R.id.the_reason_for_refusal_btn);

        the_reason_for_refusal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message = the_reason_for_refusal_et.getText().toString();


            }
        });

    }
}
