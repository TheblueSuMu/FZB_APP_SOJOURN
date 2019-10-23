package com.xcy.fzb.project_attache.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.fragment.MessageFragment;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.utils.VirturlUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.project_attache.fragment.DFragment;
import com.xcy.fzb.project_attache.fragment.EFragment;
import com.xcy.fzb.project_attache.fragment.HomeFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_main);
        FinalContents.setDengLu("专员");
        init();
        initfvb();

    }

    // 3.2 +实现接口，实现回调
    @Override
    public void process(String str) {
        if (str != null) {
            if (str.equals("0")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("2");
                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("2")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("3");
                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("5")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("4");
                transaction.replace(R.id.main_framelayout,messageFragment);
                transaction.commit();
                button_message.setChecked(true);
            }
        }
    }

    private void init() {

        StatusBar.makeStatusBarTransparent(this);

        VirturlUtil.assistActivity(findViewById(android.R.id.content));

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HomeFragment home_fragment = new HomeFragment();
        transaction.replace(R.id.main_framelayout,home_fragment);
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
                Log.i("MyCL", "sdCardPath：" + sdCardPath);
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
                HomeFragment home_fragment = new HomeFragment();
                transaction.replace(R.id.main_framelayout,home_fragment);
                break;
            case R.id.button_message:
                DFragment dFragment = new DFragment();
                transaction.replace(R.id.main_framelayout,dFragment);
                break;
            case R.id.button_backup:
                GetandSaveCurrentImage();
                String sdCardPath = getSDCardPath();
                Log.i("MyCL", "sdCardPath：" + sdCardPath);
                Intent intent = new Intent(Project_Attache_MainActivity.this, ContentActivity.class);
                intent.putExtra("img", filepath);
                startActivity(intent);
                break;
            case R.id.button_economics:
                MessageFragment message_fragment = new MessageFragment();
                transaction.replace(R.id.main_framelayout,message_fragment);
                break;
            case R.id.button_me:
                EFragment eFragment = new EFragment();
                transaction.replace(R.id.main_framelayout,eFragment);
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
        Log.i("MyCL", "Bmp：" + Bmp);
        String SavePath = getSDCardPath() + "/AndyDemo/ScreenImage";
        Log.i("MyCL", "SavePath：" + SavePath);
        //3.保存Bitmap
        try {
            path = new File(SavePath);
            //文件
            num++;
            filepath = SavePath + "/Screen_" + num + ".png";
            Log.i("MyCL", "filepath：" + filepath);
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
}
