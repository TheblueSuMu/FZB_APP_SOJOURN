package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.GridViewAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.ProjectProgressApi;
import com.xcy.fzb.all.modle.AddPhotoBean;
import com.xcy.fzb.all.modle.ChangeAddress;
import com.xcy.fzb.all.modle.VisitSaveBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

//TODO 确认到访
public class ConfirmTheVisitActivity extends AllActivity {
    RelativeLayout confirm_the_visit_return;
    private TextView comfirm_client_name;
    private TextView comfirm_project_name;
    private TextView comfirm_location;
    private Button confirm_the_visit_btn;

    GridView confirm_the_visit_gv;
    private GridViewAdapter adapter;
    private List<Object> mDatas;

    private final String IMAGE_TYPE = "image/*";

    private final int IMAGE_CODE = 0;

    RelativeLayout visit_rl;

    private Bitmap bm;
    StringBuffer stringBuffer = new StringBuffer();
    int sum = 0;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    int isPhoto = 0;
    private File file;

    int isIF = 0;
    private String getLongitude = "";
    private String getLatitude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_the_visit);
        init_No_Network();

    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
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

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        comfirm_client_name = findViewById(R.id.comfirm_client_name);
        confirm_the_visit_return = findViewById(R.id.confirm_the_visit_return);
        comfirm_project_name = findViewById(R.id.comfirm_project_name);
        comfirm_location = findViewById(R.id.comfirm_location);
        confirm_the_visit_btn = findViewById(R.id.confirm_the_visit_btn);
        confirm_the_visit_gv = findViewById(R.id.confirm_the_visit_gv);
        visit_rl = findViewById(R.id.visit_rl);
        confirm_the_visit_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        comfirm_client_name.setText(ProjectProgressApi.getCustomerName());
        comfirm_project_name.setText(ProjectProgressApi.getProjectName());
        confirm_the_visit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                initVisitSave();
            }
        });

        initData();

        visit_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isIF = 1;
                Intent intent = new Intent(ConfirmTheVisitActivity.this, TestMapActivity.class);
                intent.putExtra("La",getLatitude);
                intent.putExtra("Lo",getLongitude);
                startActivityForResult(intent, 1);
            }
        });

    }

    private void initData() {

        View inflate = View.inflate(ConfirmTheVisitActivity.this, R.layout.item_flowlayout, null);
        mDatas = new ArrayList<>();
        adapter = new GridViewAdapter(ConfirmTheVisitActivity.this, mDatas);
        confirm_the_visit_gv.setAdapter(adapter);
        confirm_the_visit_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (mDatas.size() == 9) {
                    Toast.makeText(ConfirmTheVisitActivity.this,"图片最多九张",Toast.LENGTH_SHORT).show();
                } else {
                    if (position == parent.getChildCount() - 1) {

//                        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmTheVisitActivity.this);
//                        builder.setTitle("请选择图片来源");
//                        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                if (i == 0) {

                                    try {
                                        //检测是否有写的权限
                                        int permission = ActivityCompat.checkSelfPermission(ConfirmTheVisitActivity.this,
                                                "android.permission.WRITE_EXTERNAL_STORAGE");
                                        if (permission != PackageManager.PERMISSION_GRANTED) {
                                            // 没有写的权限，去申请写的权限，
                                            ActivityCompat.requestPermissions(ConfirmTheVisitActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                                        Uri photoUri = ConfirmTheVisitActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


//TODO Uri图片转换成file类型的 start
                                        file = uri2File(photoUri);
                                        Log.i("MyCL", "Uri图片转换成file类型的：" + file);

                                        isPhoto = 1;

//TODO Uri图片转换成file类型的 end
                                        Log.i("MyCL", "图片路径：" + photoUri);
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                        startActivityForResult(intent, 1);
                                    } else {

                                    }
                                }
//                                else if (i == 1) {
//                                    Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
//                                    getAlbum.setType(IMAGE_TYPE);
//                                    startActivityForResult(getAlbum, IMAGE_CODE);
//                                }
                            }
//                        });
//                        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        });
//                        builder.show();
                        adapter = new GridViewAdapter(ConfirmTheVisitActivity.this, mDatas);
                        confirm_the_visit_gv.setAdapter(adapter);

//                    }
//                }

            }
        });

    }


    // TODO 添加到访信息数据
    private void initVisitSave() {

        String string = comfirm_location.getText().toString();

        String locationS = getLongitude + "," + getLatitude;

        if (comfirm_location.getText().toString().equals("")) {
            Toast.makeText(this, "请进入地图选择地址", Toast.LENGTH_SHORT).show();
            return;
        }

        if (stringBuffer.toString().equals("")) {
            Toast.makeText(this, "请选择照片", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<VisitSaveBean> userMessage = fzbInterface.getVisitSave(FinalContents.getPreparationId(), locationS, string, stringBuffer.toString(), FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VisitSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(VisitSaveBean visitSaveBean) {
                        Toast.makeText(ConfirmTheVisitActivity.this, visitSaveBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        FinalContents.setTiaozhuang("到访成功");
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 从相册获取图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(isIF == 1){
//            if (requestCode == 1 && resultCode == RESULT_OK) {
//                add_company_tv3.setText(data.getStringExtra("getLatitude") + "\n" + data.getStringExtra("getLongitude"));
//            }
            getLatitude = data.getStringExtra("getLatitude");
            getLongitude = data.getStringExtra("getLongitude");

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);

            Observable<ChangeAddress> changeAddress = fzbInterface.getChangeAddress(getLongitude, getLatitude);
            changeAddress.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ChangeAddress>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ChangeAddress changeAddress) {

                            comfirm_location.setText(changeAddress.getData().getValue());

                        }

                        @Override
                        public void onError(Throwable e) {

                            Log.i("经纬度转坐标","经纬度转坐标错误信息：" + e.getMessage());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

            isIF = 0;
        }else {

        }
        //TODO  获取相册图片地址
        sum++;
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
                long l = System.currentTimeMillis();
//TODO bitmap图片转换成file类型的 start
                final File san = saveFile(bm, "" + l + ".png");
                Log.i("MyCL", "Uri图片转换成file类型的：" + san);

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

                        Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "feed", part);
                        Log.i("MyCL", "addPhoto：" + addPhoto.toString());
                        addPhoto.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<AddPhotoBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(AddPhotoBean addPhotoBean) {
                                        if (stringBuffer.length() == 0) {
                                            stringBuffer.append(addPhotoBean.getData().getUrl());
                                        } else {
                                            stringBuffer.append("|" + addPhotoBean.getData().getUrl());
                                        }
                                        Log.i("MyCL", "解析完成后图片路径：" + stringBuffer.toString());
                                        mDatas.add(addPhotoBean.getData().getUrl());

                                        adapter.notifyDataSetChanged();
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
////TODO bitmap图片转换成file类型的 end

                String[] proj = {MediaStore.Images.Media.DATA};


                //好像是android多媒体数据库的封装接口，具体的看Android文档

                Cursor cursor = managedQuery(originalUri, proj, null, null, null);

                //按我个人理解 这个是获得用户选择的图片的索引值

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                //将光标移至开头 ，这个很重要，不小心很容易引起越界

                cursor.moveToFirst();

                //最后根据索引值获取图片路径

                String path = cursor.getString(column_index);
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

                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);

                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                    Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "feed", part);
                    Log.i("MyCL", "addPhoto：" + addPhoto.toString());
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    if (stringBuffer.length() == 0) {
                                        stringBuffer.append(addPhotoBean.getData().getUrl());
                                    } else {
                                        stringBuffer.append("|" + addPhotoBean.getData().getUrl());
                                    }
                                    Log.i("MyCL", "解析完成后图片路径：" + stringBuffer.toString());
                                    mDatas.add(addPhotoBean.getData().getUrl());
                                    adapter.notifyDataSetChanged();
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
        isPhoto = 0;
    }



}
