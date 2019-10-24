package com.xcy.fzb.project_attache.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.AddPhotoBean;
import com.xcy.fzb.all.modle.AddStoreBean;
import com.xcy.fzb.all.modle.AddStoreNumBean;
import com.xcy.fzb.all.modle.StoreChangeBean;
import com.xcy.fzb.all.persente.OkHttpPost;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.AllActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 添加门店
public class AddStoreActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout add_broker_return;
    ImageView add_broker_img1;
    ImageView add_broker_img2;

    TextView add_broker_tv1;
    TextView add_broker_tvs;

    TextView add_broker_tv2;
    EditText add_broker_et2;
    TextView add_broker_et3;
    EditText add_broker_et4;

    Button add_broker_btn;

    RadioButton add_broker_rb1;
    RadioButton add_broker_rb2;
    RadioButton add_broker_rb3;

    RelativeLayout add_broker_rl1;
    RelativeLayout add_store_rl2;
    private Intent intent;

    int sum = 0;
    Bitmap bm;
    static int ifsearch = 0;
    private StringBuffer imgurl;
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private String url1 = "";
    private String url2 = "";
    private int flag;
    private StoreChangeBean.DataBean.StoreManageBean storeManage;
    private File files;

    int isPhoto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_add_store);

        initView();

    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);

        add_broker_return = findViewById(R.id.add_broker_return);
        add_broker_img1 = findViewById(R.id.add_broker_img1);
        add_broker_tvs = findViewById(R.id.add_broker_tvs);
        add_broker_img2 = findViewById(R.id.add_broker_img2);
        add_broker_tv1 = findViewById(R.id.add_broker_tv1);
        add_broker_tv2 = findViewById(R.id.add_broker_tv2);
        add_broker_et2 = findViewById(R.id.add_broker_et2);
        add_broker_et3 = findViewById(R.id.add_broker_et3);
        add_broker_et4 = findViewById(R.id.add_broker_et4);
        add_broker_btn = findViewById(R.id.add_broker_btn);
        add_broker_rb1 = findViewById(R.id.add_broker_rb1);
        add_broker_rb2 = findViewById(R.id.add_broker_rb2);
        add_broker_rb3 = findViewById(R.id.add_broker_rb3);
        add_broker_rl1 = findViewById(R.id.add_broker_rl1);
        add_store_rl2 = findViewById(R.id.add_store_rl2);

        add_broker_return.setOnClickListener(this);
        add_broker_img1.setOnClickListener(this);
        add_broker_img2.setOnClickListener(this);
        add_broker_btn.setOnClickListener(this);
        add_broker_rl1.setOnClickListener(this);
        add_store_rl2.setOnClickListener(this);

        initData();

    }

    private void initData() {


        if (FinalContents.getStoreChange().equals("修改")) {

            add_broker_tvs.setText("修改门店");

            add_broker_rb3.setVisibility(View.VISIBLE);

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<StoreChangeBean> storeChangeBean = fzbInterface.getStoreChangeBean(FinalContents.getUserID(), FinalContents.getStoreId());
            storeChangeBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<StoreChangeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(StoreChangeBean storeChangeBean) {
                            storeManage = storeChangeBean.getData().getStoreManage();
                            add_broker_tv1.setText(storeChangeBean.getData().getStoreManage().getStoreNum());
                            add_broker_tv2.setText(storeChangeBean.getData().getStoreManage().getCompany().getCompanyName());
                            add_broker_et2.setText(storeChangeBean.getData().getStoreManage().getStoreName());
                            add_broker_et3.setText(storeChangeBean.getData().getStoreManage().getArea());
                            add_broker_et4.setText(storeChangeBean.getData().getStoreManage().getAddress());
                            Glide.with(AddStoreActivity.this).load("http://39.98.173.250:8080/" + storeChangeBean.getData().getStoreManage().getStoreRise()).into(add_broker_img1);
                            Glide.with(AddStoreActivity.this).load("http://39.98.173.250:8080/" + storeChangeBean.getData().getStoreManage().getStoreImg()).into(add_broker_img2);
                            FinalContents.setCompanyManageId(storeManage.getCompany().getId());
                            url1 = storeChangeBean.getData().getStoreManage().getStoreRise();
                            url1 = storeChangeBean.getData().getStoreManage().getStoreImg();
                            if (storeChangeBean.getData().getStoreManage().getFlag().equals("0")) {
                                add_broker_rb2.setChecked(true);
                            } else if (storeChangeBean.getData().getStoreManage().getFlag().equals("1")) {
                                add_broker_rb1.setChecked(true);
                            } else if (storeChangeBean.getData().getStoreManage().getFlag().equals("2")) {
                                add_broker_rb3.setChecked(true);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            add_broker_tvs.setText("添加门店");
            add_broker_rb3.setVisibility(View.GONE);
            String storeUrl = FinalContents.getBaseUrl() + "commissionerUpdate/setStoreNum?userId=" + FinalContents.getUserID();
            OkHttpPost okHttpPost = new OkHttpPost(storeUrl);
            String post = okHttpPost.post();
            Gson gson = new Gson();
            AddStoreNumBean addStoreNumBean = gson.fromJson(post, AddStoreNumBean.class);
            String storeNum = addStoreNumBean.getData().getStoreNum();
            add_broker_tv1.setText(storeNum);
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.add_broker_return:
                FinalContents.setStoreChange("");
                FinalContents.setMyAddType("");
                finish();
                break;
            case R.id.add_broker_img1:
                FinalContents.setImage1("0");
                initDataImg1();
                break;
            case R.id.add_broker_img2:
                FinalContents.setImage1("1");
                initDataImg2();
                break;
            case R.id.add_broker_rl1:
                intent = new Intent(AddStoreActivity.this, StoreListActivity.class);
                FinalContents.setMyAddType("公司");
                startActivity(intent);
                break;
            case R.id.add_store_rl2:
                getAddress();
                break;
            case R.id.add_broker_btn:
                initDatas();
                break;

        }

    }

    private void initDataImg1() {

        AlertDialog.Builder builder = new AlertDialog.Builder(AddStoreActivity.this);
        builder.setTitle("请选择图片来源");
        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {

                    try {
                        //检测是否有写的权限
                        int permission = ActivityCompat.checkSelfPermission(AddStoreActivity.this,
                                "android.permission.WRITE_EXTERNAL_STORAGE");
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            // 没有写的权限，去申请写的权限，
                            ActivityCompat.requestPermissions(AddStoreActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String SDState = Environment.getExternalStorageState();
                    if (SDState.equals(Environment.MEDIA_MOUNTED)) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
                        /***
                         * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                         * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
                         */
                        ContentValues values = new ContentValues();
                        Uri photoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//TODO Uri图片转换成file类型的 start
                        files = uri2File(photoUri);
                        Log.i("MyCL", "Uri图片转换成file类型的：" + files);
                        isPhoto = 1;
//TODO Uri图片转换成file类型的 end
                        Log.i("MyCL", "图片路径：" + photoUri);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, 1);
                    } else {

                    }
                } else if (i == 1) {
                    Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                    getAlbum.setType(IMAGE_TYPE);
                    startActivityForResult(getAlbum, IMAGE_CODE);
                }
            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    private void initDataImg2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddStoreActivity.this);
        builder.setTitle("请选择图片来源");
        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {

                    try {
                        //检测是否有写的权限
                        int permission = ActivityCompat.checkSelfPermission(AddStoreActivity.this,
                                "android.permission.WRITE_EXTERNAL_STORAGE");
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            // 没有写的权限，去申请写的权限，
                            ActivityCompat.requestPermissions(AddStoreActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String SDState = Environment.getExternalStorageState();
                    if (SDState.equals(Environment.MEDIA_MOUNTED)) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
                        /***
                         * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                         * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
                         */
                        ContentValues values = new ContentValues();
                        Uri photoUri = AddStoreActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//TODO Uri图片转换成file类型的 start
                        files = uri2File(photoUri);

                        if(isPhoto == 0){
                            isPhoto = 1;
                        }

                        Log.i("MyCL", "Uri图片转换成file类型的：" + files);

//                        new Thread() {
//                            @Override
//                            public void run() {
//
//                                Retrofit.Builder builder = new Retrofit.Builder();
//                                builder.baseUrl(FinalContents.getBaseUrl());
//                                builder.addConverterFactory(GsonConverterFactory.create());
//                                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
//                                Retrofit build = builder.build();
//                                MyService fzbInterface = build.create(MyService.class);
//
//                                RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
//
//                                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
//
//                                Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "经济圈图片", part);
//                                Log.i("MyCL", "addPhoto：" + addPhoto.toString());
//                                addPhoto.subscribeOn(Schedulers.io())
//                                        .observeOn(AndroidSchedulers.mainThread())
//                                        .subscribe(new Observer<AddPhotoBean>() {
//                                            @Override
//                                            public void onSubscribe(Disposable d) {
//
//                                            }
//
//                                            @Override
//                                            public void onNext(AddPhotoBean addPhotoBean) {
//                                                url2 = addPhotoBean.getData().getUrl();
//                                            }
//
//                                            @Override
//                                            public void onError(Throwable e) {
//                                                Log.i("MyCL", "经济圈发布图片上传错误信息：" + e.getMessage());
//                                            }
//
//                                            @Override
//                                            public void onComplete() {
//
//                                            }
//                                        });
//                            }
//                        }.start();

//TODO Uri图片转换成file类型的 end
                        Log.i("MyCL", "图片路径：" + photoUri);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, 1);
                    } else {

                    }
                } else if (i == 1) {
                    Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                    getAlbum.setType(IMAGE_TYPE);
                    startActivityForResult(getAlbum, IMAGE_CODE);
                }
            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    private void initDatas() {

        Log.i("MyCL", "图片一：" + url1);
        Log.i("MyCL", "图片二：" + url2);
        if (FinalContents.getStoreChange().equals("")) {
            String s1 = add_broker_et2.getText().toString();
            String s2 = add_broker_et3.getText().toString();
            String s3 = add_broker_et4.getText().toString();
            String s = add_broker_tv1.getText().toString();
            if (add_broker_rb1.isChecked()) {
                flag = 1;
            }
            if (add_broker_rb2.isChecked()) {
                flag = 0;
            }
            if (add_broker_rb3.isChecked()) {
                flag = 2;
            }

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<AddStoreBean> addStoreBean = fzbInterface.getAddStoreBean("", s, s1, s2, s3, "", url1, url2, flag + "", FinalContents.getCompanyManageId(), FinalContents.getUserID());
            addStoreBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AddStoreBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AddStoreBean addStoreBean) {
                            if (addStoreBean.getData().getMessage().equals("保存成功")) {
                                Toast.makeText(AddStoreActivity.this, addStoreBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(AddStoreActivity.this, addStoreBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else if (FinalContents.getStoreChange().equals("修改")) {

            String s1 = add_broker_et2.getText().toString();
            String s2 = add_broker_et3.getText().toString();
            String s3 = add_broker_et4.getText().toString();
            String s = add_broker_tv1.getText().toString();
            if (add_broker_rb1.isChecked()) {
                flag = 1;
            }
            if (add_broker_rb2.isChecked()) {
                flag = 0;
            }
            if (add_broker_rb3.isChecked()) {
                flag = 2;
            }

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<AddStoreBean> addStoreBean = fzbInterface.getAddStoreBean(storeManage.getId(), s, s1, s2, s3, "", url1, url2, flag + "", FinalContents.getCompanyManageId(), FinalContents.getUserID());
            addStoreBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AddStoreBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AddStoreBean addStoreBean) {
                            if (addStoreBean.getData().getMessage().equals("保存成功")) {
                                Toast.makeText(AddStoreActivity.this, addStoreBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(AddStoreActivity.this, addStoreBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

            FinalContents.setStoreChange("");
        }


    }

    /*TODO 添加市/区*/
    private void getAddress() {

        CityPicker cityPicker = new CityPicker.Builder(AddStoreActivity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听事件，获取结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为展示区赋值
                add_broker_et3.setText(city.trim() + "/" + district.trim());
            }
        });

    }

    //TODO 从相册获取图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        sum++;
//TODO  获取相册图片地址
        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量
            Log.e("MyCL", "ActivityResult resultCode error");
            return;
        }
        bm = null;
        //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口

        ContentResolver resolver = getContentResolver();


        //此处的用于判断接收的Activity是不是你想要的那个

        if (requestCode == IMAGE_CODE) {

            try {

                Uri originalUri = data.getData();        //获得图片的uri

                Log.i("MyCL", "图片uri" + originalUri);
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);        //显得到bitmap图片
//                if (ifsearch == 1) {

//                } else {
//                    Glide.with(AddStoreActivity.this).load(bm).into(add_broker_img2);
//                }
                final File san = saveFile(bm, sum + ".png");
                Log.i("MyCL", "bm:" + bm);
                new Thread() {
                    @Override
                    public void run() {

                        Retrofit.Builder builder = new Retrofit.Builder();
                        builder.baseUrl(FinalContents.getBaseUrl());
                        builder.addConverterFactory(GsonConverterFactory.create());
                        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                        Retrofit build = builder.build();
                        MyService fzbInterface = build.create(MyService.class);

                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), san);

                        MultipartBody.Part part = MultipartBody.Part.createFormData("file", san.getName(), requestBody);
                        Log.i("MyCL", "");
                        Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "zhuanyuan", part);
                        addPhoto.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<AddPhotoBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(AddPhotoBean addPhotoBean) {
                                        if (FinalContents.getImage1().equals("0")) {
                                            url1 = addPhotoBean.getData().getUrl();
                                            Glide.with(AddStoreActivity.this).load("http://39.98.173.250:8080" + url1).into(add_broker_img1);
                                        } else if (FinalContents.getImage1().equals("1")) {
                                            url2 = addPhotoBean.getData().getUrl();
                                            Glide.with(AddStoreActivity.this).load("http://39.98.173.250:8080" + url2).into(add_broker_img2);
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.i("MyCL", "经济圈发布图片上传错误信息：" + e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }.start();

                String[] proj = {MediaStore.Images.Media.DATA};
                //好像是android多媒体数据库的封装接口，具体的看Android文档

                Cursor cursor = managedQuery(originalUri, proj, null, null, null);

                //按我个人理解 这个是获得用户选择的图片的索引值

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                //将光标移至开头 ，这个很重要，不小心很容易引起越界

                cursor.moveToFirst();

                //最后根据索引值获取图片路径
                String path = cursor.getString(column_index);
                Log.i("MyCL", "图片路径：" + path);

            } catch (IOException e) {

                Log.e("MyCL", e.toString());

            }

        }
    }

    //TODO 将Uri转换成File
    private File uri2File(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }

    //TODO 将bitmap转换成File
    public File saveFile(Bitmap bm, String fileName) throws IOException {//将Bitmap类型的图片转化成file类型，便于上传到服务器
        String path = Environment.getExternalStorageDirectory() + "/Ask";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        initData();

        add_broker_tv2.setText(FinalContents.getAddtype2());

        if (isPhoto == 1) {

            new Thread() {
                @Override
                public void run() {

                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png/jpg"), files);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", files.getName(), requestBody);
                    Log.i("MyCL", "file.getName()：" + files.getName());
                    final Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "cesda", part);
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    if (FinalContents.getImage1().equals("0")) {
                                        url1 = addPhotoBean.getData().getUrl();
                                        Glide.with(AddStoreActivity.this).load("http://39.98.173.250:8080" + url1).into(add_broker_img1);
                                    } else if (FinalContents.getImage1().equals("1")) {
                                        url2 = addPhotoBean.getData().getUrl();
                                        Glide.with(AddStoreActivity.this).load("http://39.98.173.250:8080" + url2).into(add_broker_img2);
                                    }
                                    isPhoto = 0;
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("MyCL", "经济圈发布图片上传错误信息：" + e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }.start();
        }
    }


}
