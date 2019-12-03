package com.xcy.fzb.broker.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.androidprogresslayout.ProgressLayout;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.HomeFragment;
import com.xcy.fzb.all.fragment.MessageFragment;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.utils.VirturlUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.all.view.ReportActivity;
import com.xcy.fzb.broker.fragment.DFragment;
import com.xcy.fzb.broker.fragment.EFragment;

public class Broker_MainActivity extends AllActivity implements View.OnClickListener,HomeFragment.FragmentInteraction {

    private RadioButton button_home;
    private RadioButton button_message;
    private RadioButton button_backup;
    private RadioButton button_economics;
    private RadioButton button_me;
    private ImageView img_backup;
    private ProgressLayout progressLayout;
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();

    HomeFragment home_fragment = new HomeFragment();
    MessageFragment message_fragment = new MessageFragment();
    DFragment dFragment = new DFragment();
    EFragment eFragment = new EFragment();

//    /**
//     * 版本下载数据
//     */
//    //  上下文
////    private Context mContext;
//    //  进度条
//    private ProgressBar mProgressBar;
//    //  对话框
//    private Dialog mDownloadDialog;
//    //  判断是否停止
//    private boolean mIsCancel = false;
//    //  进度
//    private int mProgress;
//    //  文件保存路径
//    private String mSavePath;
//    //  版本名称
//    private String mVersion_name="1.0";
//    //  请求链接
//    private String url ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broker_activity_main);
        StatusBar.makeStatusBarTransparent(this);
        FinalContents.setZhuanAn("0");
        init();
        initfvb();
    }

    private void init(){
        VirturlUtil.assistActivity(findViewById(android.R.id.content));
        init_No_Network();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.main_framelayout,home_fragment);
        transaction.add(R.id.main_framelayout,message_fragment);
        transaction.add(R.id.main_framelayout,dFragment);
        transaction.add(R.id.main_framelayout,eFragment);


        transaction.show(home_fragment);
        transaction.hide(message_fragment);
        transaction.hide(dFragment);
        transaction.hide(eFragment);
        transaction.commit();
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
                message_fragment.setType("2");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(home_fragment);
                transaction.hide(dFragment);
                transaction.show(message_fragment);
                transaction.hide(eFragment);

                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("2")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                message_fragment.setType("3");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(home_fragment);
                transaction.hide(dFragment);
                transaction.show(message_fragment);
                transaction.hide(eFragment);

                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("5")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                message_fragment.setType("4");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(home_fragment);
                transaction.hide(dFragment);
                transaction.show(message_fragment);
                transaction.hide(eFragment);

                transaction.commit();
                button_message.setChecked(true);
            }
        }

    }

    private void initfvb() {
        button_home = findViewById(R.id.button_home);
        button_message = findViewById(R.id.button_message);
        button_backup = findViewById(R.id.button_backup);
        button_economics = findViewById(R.id.button_economics);
        button_me = findViewById(R.id.button_me);
        img_backup = findViewById(R.id.img_backup);
        img_backup.setImageResource(R.mipmap.z13);
        img_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    Intent intent = new Intent(Broker_MainActivity.this, ReportActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Broker_MainActivity.this, "该城市不是您的主营城市，请切换到您的主营城市后再报备客户", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.button_home:
                init_No_Network();
//                HomeFragment home_fragment = new HomeFragment();
//                transaction.replace(R.id.main_framelayout, home_fragment);
                transaction.show(home_fragment);
                transaction.hide(message_fragment);
                transaction.hide(dFragment);
                transaction.hide(eFragment);
                break;
            case R.id.button_message:
                init_No_Network();

//                transaction.replace(R.id.main_framelayout, message_fragment);
                transaction.hide(home_fragment);
                transaction.show(message_fragment);
                transaction.hide(dFragment);
                transaction.hide(eFragment);
                break;
            case R.id.button_backup:
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Broker_MainActivity.this, ReportActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.button_economics:
                init_No_Network();

//                transaction.replace(R.id.main_framelayout, dFragment);
                transaction.hide(home_fragment);
                transaction.hide(message_fragment);
                transaction.show(dFragment);
                transaction.hide(eFragment);
                break;
            case R.id.button_me:
                init_No_Network();

//                transaction.replace(R.id.main_framelayout, eFragment);
                transaction.hide(home_fragment);
                transaction.hide(message_fragment);
                transaction.hide(dFragment);
                transaction.show(eFragment);
                break;
        }
        transaction.commit();
    }


    private void init_No_Network() {
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

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("MyCL","进入了Broker_MainActivity中onResume方法中");

//        initDaown();

    }

//
//    private void initDaown(){
//        String versionName = APKVersionCodeUtils.getVerName(this);
//        Retrofit.Builder builder = new Retrofit.Builder();
//        builder.baseUrl(FinalContents.getBaseUrl());
//        builder.addConverterFactory(GsonConverterFactory.create());
//        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
//        Retrofit build = builder.build();
//        MyService fzbInterface = build.create(MyService.class);
//        final Observable<AppPackageBean> appPackage = fzbInterface.getAppPackage("android","com.xcy.fzb", versionName);
//        appPackage.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<AppPackageBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(final AppPackageBean appPackageBean) {
////                        Toast.makeText(AboutFZBActivity.this, appPackageBean.getData().getComment(), Toast.LENGTH_SHORT).show();
//                        if(appPackageBean.getData().getIsUpgrade().equals("0")){
////                            Toast.makeText(LoginActivity.this,"当前版本已是最新版本",Toast.LENGTH_SHORT).show();
//                        }else if(appPackageBean.getData().getIsUpgrade().equals("1")){
//                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Broker_MainActivity.this);
//                            builder1.setTitle("提示");
//                            builder1.setMessage("是否更新当前版本");
//                            builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            });
//                            builder1.setPositiveButton("更新", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    url = appPackageBean.getData().getAppurl();
//                                    showDownloadDialog();
//                                }
//                            });
//                            builder1.show();
//                        }else if(appPackageBean.getData().getIsUpgrade().equals("2")){
//                            url = appPackageBean.getData().getAppurl();
//                            showDownloadDialog();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("版本更新","错误信息：" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//    /**
//     * 显示正在下载对话框
//     */
//    protected void showDownloadDialog() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(Broker_MainActivity.this);
//        builder.setTitle("下载中");
//        View view = LayoutInflater.from(Broker_MainActivity.this).inflate(R.layout.dialog_progress, null);
//        mProgressBar = (ProgressBar) view.findViewById(R.id.id_progress);
//        builder.setView(view);
//
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // 隐藏当前对话框
//                dialog.dismiss();
//                // 设置下载状态为取消
//                mIsCancel = true;
//            }
//        });
//
//        mDownloadDialog = builder.create();
//        mDownloadDialog.show();
//
//        // 下载文件
//        downloadAPK();
//    }
//    /**
//     * 开启新线程下载apk文件
//     */
//    private void downloadAPK() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//                        String sdPath = Environment.getExternalStorageDirectory() + "/";
////                      文件保存路径
//                        mSavePath = sdPath + "fzbdownload";
//
//                        File dir = new File(mSavePath);
//                        if (!dir.exists()){
//                            dir.mkdir();
//                        }
//                        // 下载文件
//                        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
//                        conn.connect();
//                        InputStream is = conn.getInputStream();
//                        int length = conn.getContentLength();
//
//                        File apkFile = new File(mSavePath, mVersion_name);
//                        FileOutputStream fos = new FileOutputStream(apkFile);
//
//                        int count = 0;
//                        byte[] buffer = new byte[1024];
//                        while (!mIsCancel){
//                            int numread = is.read(buffer);
//                            count += numread;
//                            // 计算进度条的当前位置
//                            mProgress = (int) (((float)count/length) * 100);
//                            // 更新进度条
//                            mUpdateProgressHandler.sendEmptyMessage(1);
//
//                            // 下载完成
//                            if (numread < 0){
//                                mUpdateProgressHandler.sendEmptyMessage(2);
//                                break;
//                            }
//                            fos.write(buffer, 0, numread);
//                        }
//                        fos.close();
//                        is.close();
//                    }
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    /**
//     * 接收消息
//     */
//    private Handler mUpdateProgressHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 1:
//                    // 设置进度条
//                    mProgressBar.setProgress(mProgress);
//                    break;
//                case 2:
//                    // 隐藏当前下载对话框
//                    mDownloadDialog.dismiss();
//                    // 安装 APK 文件
//                    installAPK();
//            }
//        };
//    };
//
//    /**
//     * 下载到本地后执行安装
//     */
//    protected void installAPK() {
//        File apkFile = new File(mSavePath, mVersion_name);
//        if (!apkFile.exists()){
//            return;
//        }
//        Intent intent = new Intent(Intent.ACTION_VIEW);
////      安装完成后，启动app（源码中少了这句话）
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.parse("file://" + apkFile.toString());
//        intent.setDataAndType(uri, "application/vnd.android.package-archive");
//        Broker_MainActivity.this.startActivity(intent);
//    }

}
