package com.xcy.fzb.shopping_guide;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.fragment.MeFragment;
import com.xcy.fzb.shopping_guide.fragment.MessageFragment;
import com.xcy.fzb.shopping_guide.fragment.ProjectFragment;
import com.xcy.fzb.shopping_guide.fragment.TaskFragment;
import com.xcy.fzb.shopping_guide.modle.ExemplaryUserBean;
import com.xcy.fzb.shopping_guide.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ProjectFragment.FragmentInteraction {
    private RadioButton task;
    private RadioButton project;
    private RadioButton message;
    private RadioButton me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initData();
        initfvb();
    }

    // 3.2 +实现接口，实现回调
    @Override
    public void process(String str) {
        if (str != null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            TaskFragment taskFragment = new TaskFragment();
            transaction.replace(R.id.main_framelayout,taskFragment);
            transaction.commit();
            task.setChecked(true);
        }
    }

    private void initfvb(){
        task = findViewById(R.id.main_task);
        project = findViewById(R.id.main_project);
        message = findViewById(R.id.main_message);
        me = findViewById(R.id.main_me);

        task.setOnClickListener(this);
        project.setOnClickListener(this);
        message.setOnClickListener(this);
        me.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ProjectFragment projectFragment = new ProjectFragment();
        transaction.add(R.id.main_framelayout,projectFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.main_task:
                TaskFragment taskFragment = new TaskFragment();
                transaction.replace(R.id.main_framelayout,taskFragment);
                break;
            case R.id.main_project:
                ProjectFragment projectFragment = new ProjectFragment();
                transaction.replace(R.id.main_framelayout,projectFragment);
                break;
            case R.id.main_message:
                MessageFragment messageFragment = new MessageFragment();
                transaction.replace(R.id.main_framelayout,messageFragment);
                break;
            case R.id.main_me:
                MeFragment meFragment = new MeFragment();
                transaction.replace(R.id.main_framelayout,meFragment);
                break;
        }
        transaction.commit();
    }

    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ExemplaryUserBean> userMessage = fzbInterface.getExemplaryLogin("dg","123456","1");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ExemplaryUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExemplaryUserBean exemplaryUserBean) {
                        ExemplaryUserBean userBean = exemplaryUserBean;

                        FinalContents.setUserID(userBean.getData().getId());
                        FinalContents.setCityID(userBean.getData().getCityId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "您输入的账号或密码有误", Toast.LENGTH_SHORT).show();
                        Log.i("wsw","返回的数据"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
