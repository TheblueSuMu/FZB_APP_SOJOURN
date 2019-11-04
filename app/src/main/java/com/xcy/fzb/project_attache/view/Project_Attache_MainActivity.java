package com.xcy.fzb.project_attache.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.xcy.fzb.all.utils.VirturlUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_attache.fragment.DFragment;
import com.xcy.fzb.project_attache.fragment.EFragment;
import com.xcy.fzb.project_attache.fragment.HomeFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Project_Attache_MainActivity extends AllActivity implements View.OnClickListener ,HomeFragment.FragmentInteraction {
    private RadioButton button_home;
    private RadioButton button_message;
    private RadioButton button_backup;
    private RadioButton button_economics;
    private RadioButton button_me;


    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ImageView img_backup;
    private String filepath;
    private File path;
    int num = 0;

    String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    List<String> mPermissionList = new ArrayList<>();

    // private ImageView welcomeImg = null;
    private static final int PERMISSION_REQUEST = 1;
    private ProgressLayout progressLayout;

    HomeFragment home_fragment = new HomeFragment();
    DFragment dFragment = new DFragment();
    MessageFragment message_fragment = new MessageFragment();
    EFragment eFragment = new EFragment();

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
        checkPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_main);
        FinalContents.setDengLu("专员");
        init();
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
                button_economics.setChecked(true);
            } else if (str.equals("2")) {
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
                button_economics.setChecked(true);
            } else if (str.equals("5")) {
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
                button_economics.setChecked(true);
            }
        }
    }

    private void init() {
        init_No_Network();
        StatusBar.makeStatusBarTransparent(this);

        VirturlUtil.assistActivity(findViewById(android.R.id.content));
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.main_framelayout,home_fragment);
        transaction.add(R.id.main_framelayout,dFragment);
        transaction.add(R.id.main_framelayout,message_fragment);
        transaction.add(R.id.main_framelayout,eFragment);

        transaction.show(home_fragment);
        transaction.hide(dFragment);
        transaction.hide(message_fragment);
        transaction.hide(eFragment);

        transaction.commit();
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
                GetandSaveCurrentImage();
                String sdCardPath = getSDCardPath();
                Intent intent = new Intent(Project_Attache_MainActivity.this, ContentActivity.class);
                intent.putExtra("img", filepath);
                startActivity(intent);
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
                transaction.show(home_fragment);
                transaction.hide(dFragment);
                transaction.hide(message_fragment);
                transaction.hide(eFragment);
//                transaction.replace(R.id.main_framelayout,home_fragment);
                break;
            case R.id.button_message:
                init_No_Network();
                transaction.hide(home_fragment);
                transaction.show(dFragment);
                transaction.hide(message_fragment);
                transaction.hide(eFragment);
//                transaction.replace(R.id.main_framelayout,dFragment);
                break;
            case R.id.button_backup:
                GetandSaveCurrentImage();
                String sdCardPath = getSDCardPath();
                Intent intent = new Intent(Project_Attache_MainActivity.this, ContentActivity.class);
                intent.putExtra("img", filepath);
                startActivity(intent);
                break;
            case R.id.button_economics:
                init_No_Network();
                transaction.hide(home_fragment);
                transaction.hide(dFragment);
                transaction.show(message_fragment);
                transaction.hide(eFragment);
//                transaction.replace(R.id.main_framelayout,message_fragment);
                break;
            case R.id.button_me:
                init_No_Network();
                transaction.hide(home_fragment);
                transaction.hide(dFragment);
                transaction.hide(message_fragment);
                transaction.show(eFragment);
//                transaction.replace(R.id.main_framelayout,eFragment);
                break;
        }
        transaction.commit();
    }

    /**
     * 获取和保存当前屏幕的截图
     */
    private void GetandSaveCurrentImage() {
        //1.构建Bitmap
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        Bitmap Bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        //2.获取屏幕
        View decorview = getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);
        Bmp = decorview.getDrawingCache();
        String SavePath = getSDCardPath() + "/AndyDemo/ScreenImage";
        //3.保存Bitmap
        try {
            path = new File(SavePath);
            //文件
            num++;
            filepath = SavePath + "/Screen_" + num + ".png";
            File file = new File(filepath);
            if (!path.exists()) {
                path.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            if (null != fos) {
                Bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取SDCard的目录路径功能
     *
     * @return
     */
    private String getSDCardPath() {
        File sdcardDir = null;
        //判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdcardExist) {
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }

    private void checkPermission() {
        mPermissionList.clear();

        //判断哪些权限未授予
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        /**
         * 判断是否为空
         */
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了

        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(Project_Attache_MainActivity.this, permissions, PERMISSION_REQUEST);
        }
    }

    /**
     * 响应授权
     * 这里不管用户是否拒绝，都进入首页，不再重复申请权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST:

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

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
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Project_Attache_MainActivity.this);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(Project_Attache_MainActivity.this);
        builder.setTitle("下载中");
        View view = LayoutInflater.from(Project_Attache_MainActivity.this).inflate(R.layout.dialog_progress, null);
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
        Project_Attache_MainActivity.this.startActivity(intent);
    }
}
