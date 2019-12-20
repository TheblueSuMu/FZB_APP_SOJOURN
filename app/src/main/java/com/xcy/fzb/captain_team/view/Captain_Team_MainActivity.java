package com.xcy.fzb.captain_team.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.androidprogresslayout.ProgressLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.AppPackageBean;
import com.xcy.fzb.all.fragment.MessageFragment;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.ToastUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.all.view.ReportActivity;
import com.xcy.fzb.captain_team.fragment.Captain_Team_MeFragment;
import com.xcy.fzb.captain_team.fragment.Captain_Team_MyClientFragment;
import com.xcy.fzb.captain_team.fragment.ProjectFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Captain_Team_MainActivity extends AllActivity implements View.OnClickListener ,ProjectFragment.FragmentInteraction{
    private RadioButton button_home;
    private RadioButton button_message;
    private RadioButton button_backup;
    private RadioButton button_economics;
    private RadioButton button_me;
    private ImageView img_backup;
    private ProgressLayout progressLayout;


    ProjectFragment projectFragment = new ProjectFragment();
    Captain_Team_MyClientFragment captain_team_myClientFragment = new Captain_Team_MyClientFragment();
    MessageFragment messageFragment = new MessageFragment();
    Captain_Team_MeFragment captain_team_meFragment = new Captain_Team_MeFragment();

    /**
     * 版本下载数据
     */
    //  上下文
//    private Context mContext;
    //  进度条
    private ProgressBar mProgressBar;
    //  对话框
    private Dialog mDownloadDialog;
    //  判断是否停止
    private boolean mIsCancel = false;
    //  进度
    private int mProgress;
    //  文件保存路径
    private String mSavePath;
    //  版本名称
    private String mVersion_name="1.0";
    //  请求链接
    private String url ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_main);

        StatusBar.makeStatusBarTransparent(this);

        FinalContents.setDengLu("团队长");
        FinalContents.setZhuanAn("0");
        FinalContents.setQuanceng("1");
        initfvb();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {

        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            Toast.makeText(this, "当前无网络，请检查网络后再进行登录", Toast.LENGTH_SHORT).show();
        }
    }


    // 3.2 +实现接口，实现回调
    @Override
    public void process(String str) {
        if (str != null) {
            if (str.equals("0")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("2");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(projectFragment);
                transaction.hide(captain_team_myClientFragment);
                transaction.show(messageFragment);
                transaction.hide(captain_team_meFragment);
                Log.i("消息跳转","type2："+str);
                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("2")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("3");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(projectFragment);
                transaction.hide(captain_team_myClientFragment);
                transaction.show(messageFragment);
                transaction.hide(captain_team_meFragment);
                Log.i("消息跳转","type3："+str);
                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("5")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("4");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(projectFragment);
                transaction.hide(captain_team_myClientFragment);
                transaction.show(messageFragment);
                transaction.hide(captain_team_meFragment);
                Log.i("消息跳转","type5："+str);
                transaction.commit();
                button_message.setChecked(true);
            }
        }
    }

    private void initfvb(){
        init_No_Network();
        button_home = findViewById(R.id.button_home);
        button_message = findViewById(R.id.button_message);
        button_backup = findViewById(R.id.button_backup);
        button_economics = findViewById(R.id.button_economics);
        button_me = findViewById(R.id.button_me);
        img_backup = findViewById(R.id.img_backup);
        img_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    Intent intent = new Intent(Captain_Team_MainActivity.this, ReportActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtil.showLongToast(Captain_Team_MainActivity.this, "该城市不是您的主营城市，请切换到您的主营城市后再报备客户");
                }
            }
        });
        click();
    }

    public void click() {
        button_home.setOnClickListener(this);
        button_message.setOnClickListener(this);
        button_backup.setOnClickListener(this);
        button_economics.setOnClickListener(this);
        button_me.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.main_framelayout,projectFragment);
        transaction.add(R.id.main_framelayout,captain_team_myClientFragment);
        transaction.add(R.id.main_framelayout,messageFragment);
        transaction.add(R.id.main_framelayout,captain_team_meFragment);


        transaction.show(projectFragment);
        transaction.hide(captain_team_myClientFragment);
        transaction.hide(messageFragment);
        transaction.hide(captain_team_meFragment);

        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.button_economics:
                FinalContents.setMySelf("1");
                FinalContents.setQuanceng("1");
                FinalContents.setAgentId(FinalContents.getUserID());
                init_No_Network();
                transaction.hide(projectFragment);
                transaction.show(captain_team_myClientFragment);
                transaction.hide(messageFragment);
                transaction.hide(captain_team_meFragment);
//                transaction.replace(R.id.main_framelayout,captain_team_myClientFragment);
                break;
            case R.id.button_home:
                init_No_Network();
                transaction.show(projectFragment);
                transaction.hide(captain_team_myClientFragment);
                transaction.hide(messageFragment);
                transaction.hide(captain_team_meFragment);
//                transaction.replace(R.id.main_framelayout,projectFragment);
                break;
            case R.id.button_message:
                init_No_Network();
                transaction.hide(projectFragment);
                transaction.hide(captain_team_myClientFragment);
                transaction.show(messageFragment);
                transaction.hide(captain_team_meFragment);
//                transaction.replace(R.id.main_framelayout,messageFragment);
                break;
            case R.id.button_me:
                init_No_Network();
                transaction.hide(projectFragment);
                transaction.hide(captain_team_myClientFragment);
                transaction.hide(messageFragment);
                transaction.show(captain_team_meFragment);
//                transaction.replace(R.id.main_framelayout,captain_team_meFragment);
                break;
        }
        transaction.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();

        Log.i("MyCL","进入了Broker_MainActivity中onResume方法中");

//        initDaown();

    }


    private void initDaown(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<AppPackageBean> appPackage = fzbInterface.getAppPackage("android","com.xcy.fzb",  FinalContents.getVersionNumBer());
        appPackage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppPackageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final AppPackageBean appPackageBean) {
//                        Toast.makeText(AboutFZBActivity.this, appPackageBean.getData().getComment(), Toast.LENGTH_SHORT).show();
                        if(appPackageBean.getData().getIsUpgrade().equals("0")){
//                            Toast.makeText(LoginActivity.this,"当前版本已是最新版本",Toast.LENGTH_SHORT).show();
                        }else if(appPackageBean.getData().getIsUpgrade().equals("1")){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Captain_Team_MainActivity.this);
                            builder1.setTitle("提示");
                            builder1.setMessage("是否更新当前版本");
                            builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    url = appPackageBean.getData().getAppurl();
                                    showDownloadDialog();
                                }
                            });
                            builder1.show();
                        }else if(appPackageBean.getData().getIsUpgrade().equals("2")){
                            url = appPackageBean.getData().getAppurl();
                            showDownloadDialog();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("版本更新","错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 显示正在下载对话框
     */
    protected void showDownloadDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Captain_Team_MainActivity.this);
        builder.setTitle("下载中");
        View view = LayoutInflater.from(Captain_Team_MainActivity.this).inflate(R.layout.dialog_progress, null);
        mProgressBar = (ProgressBar) view.findViewById(R.id.id_progress);
        builder.setView(view);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
                // 设置下载状态为取消
                mIsCancel = true;
            }
        });

        mDownloadDialog = builder.create();
        mDownloadDialog.show();

        // 下载文件
        downloadAPK();
    }
    /**
     * 开启新线程下载apk文件
     */
    private void downloadAPK() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        String sdPath = Environment.getExternalStorageDirectory() + "/";
//                      文件保存路径
                        mSavePath = sdPath + "fzbdownload";

                        File dir = new File(mSavePath);
                        if (!dir.exists()){
                            dir.mkdir();
                        }
                        // 下载文件
                        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                        conn.connect();
                        InputStream is = conn.getInputStream();
                        int length = conn.getContentLength();

                        File apkFile = new File(mSavePath, mVersion_name);
                        FileOutputStream fos = new FileOutputStream(apkFile);

                        int count = 0;
                        byte[] buffer = new byte[1024];
                        while (!mIsCancel){
                            int numread = is.read(buffer);
                            count += numread;
                            // 计算进度条的当前位置
                            mProgress = (int) (((float)count/length) * 100);
                            // 更新进度条
                            mUpdateProgressHandler.sendEmptyMessage(1);

                            // 下载完成
                            if (numread < 0){
                                mUpdateProgressHandler.sendEmptyMessage(2);
                                break;
                            }
                            fos.write(buffer, 0, numread);
                        }
                        fos.close();
                        is.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 接收消息
     */
    private Handler mUpdateProgressHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    // 设置进度条
                    mProgressBar.setProgress(mProgress);
                    break;
                case 2:
                    // 隐藏当前下载对话框
                    mDownloadDialog.dismiss();
                    // 安装 APK 文件
                    installAPK();
            }
        };
    };

    /**
     * 下载到本地后执行安装
     */
    protected void installAPK() {
        File apkFile = new File(mSavePath, mVersion_name);
        if (!apkFile.exists()){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
//      安装完成后，启动app（源码中少了这句话）
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.parse("file://" + apkFile.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        Captain_Team_MainActivity.this.startActivity(intent);
    }
}
