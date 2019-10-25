package com.xcy.fzb.all.view;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.AddClientBean;
import com.xcy.fzb.all.modle.AddPhotoBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.MatcherUtils;

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

public class MyClientAddActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout client_add_return;
    ImageView client_add_img;
    ImageView client_add_bj;
    ImageView client_add_photo_img_1;
    ImageView client_add_photo_img_2;
    ImageView client_add_photo_img_3;
    ImageView client_add_photo_add;
    ImageView client_add_photo_delete_1;
    ImageView client_add_photo_delete_2;

    TextView client_add_name_tv;
    TextView client_add_photo_tv_1;
    TextView client_add_photo_tv_2;
    TextView client_add_photo_tv_3;

    EditText client_add_name_et;
    EditText client_add_photo_et_1;
    EditText client_add_photo_et_2;
    EditText client_add_photo_et_3;

    LinearLayout client_add_ll_1;
    LinearLayout client_add_ll_2;

    RelativeLayout client_add_rl_1;
    RelativeLayout client_add_rl_2;
    private AlertDialog.Builder builders;
    private String[] cities;

    String name1;
    String name2;
    String name3;

    Button client_add_btn;

    int addNum = 0;

    private final String IMAGE_TYPE = "image/*";

    private final int IMAGE_CODE = 0;
    private Bitmap bm;

    String imgUrl = "";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    Uri saveUri;
    private static final String FILE_PATH = "/sdcard/syscamera.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_client_add);


        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        builders = new AlertDialog.Builder(MyClientAddActivity.this);
        cities = new String[]{"本人手机", "父母手机", "配偶手机", "子女手机"};

        client_add_return = findViewById(R.id.client_add_return);
        client_add_img = findViewById(R.id.client_add_img);
        client_add_bj = findViewById(R.id.client_add_bj);
        client_add_photo_img_1 = findViewById(R.id.client_add_photo_img_1);
        client_add_photo_img_2 = findViewById(R.id.client_add_photo_img_2);
        client_add_photo_img_3 = findViewById(R.id.client_add_photo_img_3);
        client_add_photo_add = findViewById(R.id.client_add_photo_add);
        client_add_photo_delete_1 = findViewById(R.id.client_add_photo_delete_1);
        client_add_photo_delete_2 = findViewById(R.id.client_add_photo_delete_2);
        client_add_name_tv = findViewById(R.id.client_add_name_tv);
        client_add_name_et = findViewById(R.id.client_add_name_et);
        client_add_photo_et_1 = findViewById(R.id.client_add_photo_et_1);
        client_add_photo_et_2 = findViewById(R.id.client_add_photo_et_2);
        client_add_photo_et_3 = findViewById(R.id.client_add_photo_et_3);
        client_add_btn = findViewById(R.id.client_add_btn);

        client_add_ll_1 = findViewById(R.id.client_add_ll_1);
        client_add_ll_2 = findViewById(R.id.client_add_ll_2);

        client_add_rl_1 = findViewById(R.id.client_add_rl_1);
        client_add_rl_2 = findViewById(R.id.client_add_rl_2);

        client_add_photo_tv_1 = findViewById(R.id.client_add_photo_tv_1);
        client_add_photo_tv_2 = findViewById(R.id.client_add_photo_tv_2);
        client_add_photo_tv_3 = findViewById(R.id.client_add_photo_tv_3);

        name1 = client_add_photo_tv_1.getText().toString();
        name2 = client_add_photo_tv_2.getText().toString();
        name3 = client_add_photo_tv_3.getText().toString();

        client_add_return.setOnClickListener(this);
        client_add_img.setOnClickListener(this);
        client_add_photo_img_1.setOnClickListener(this);
        client_add_photo_img_2.setOnClickListener(this);
        client_add_photo_img_3.setOnClickListener(this);
        client_add_photo_add.setOnClickListener(this);
        client_add_photo_delete_1.setOnClickListener(this);
        client_add_photo_delete_2.setOnClickListener(this);
        client_add_btn.setOnClickListener(this);

        saveUri = Uri.fromFile(new File(getExternalFilesDir(Environment.DIRECTORY_DCIM), "test.jpg"));
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.client_add_return:
                finish();
                break;
//                TODO 图片选择
            case R.id.client_add_img:
//                TODO 获取相册图片地址

                AlertDialog.Builder builder = new AlertDialog.Builder(MyClientAddActivity.this);
                builder.setTitle("请选择图片来源");
                builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            try {
                                //检测是否有写的权限
                                int permission = ActivityCompat.checkSelfPermission(MyClientAddActivity.this,
                                        "android.permission.WRITE_EXTERNAL_STORAGE");
                                if (permission != PackageManager.PERMISSION_GRANTED) {
                                    // 没有写的权限，去申请写的权限，会弹出对话框
                                    ActivityCompat.requestPermissions(MyClientAddActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                                Uri photoUri = MyClientAddActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                Log.i("MyCL", "图片路径：" + photoUri);
                                final File file = uri2File(photoUri);

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

                                        Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "myClient", part);
                                        Log.i("MyCL", "addPhoto：" + addPhoto.toString());
                                        addPhoto.subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Observer<AddPhotoBean>() {
                                                    @Override
                                                    public void onSubscribe(Disposable d) {

                                                    }

                                                    @Override
                                                    public void onNext(AddPhotoBean addPhotoBean) {
                                                        imgUrl = addPhotoBean.getData().getUrl();
                                                        Log.i("MyCL", "解析完成后图片路径：" + imgUrl);
                                                        Glide.with(MyClientAddActivity.this).load("http://39.98.173.250:8080" + imgUrl).into(client_add_img);
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

                                Glide.with(MyClientAddActivity.this).load(photoUri).into(client_add_img);
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
                break;
//                TODO 编辑
            case R.id.client_add_btn:
                if (!MatcherUtils.isMobile(client_add_photo_et_1.getText().toString())) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (client_add_name_et.getText().toString().equals("")) {
                    }else {
                        if(addNum == 0){
                            initData();
                            addNum = 1;
                        }
                    }
                }

                break;
//                TODO 选择联系人
            case R.id.client_add_photo_img_1:

                builders.setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        client_add_photo_tv_1.setText(cities[which] + "");
                        name1 = cities[which] + "";
                    }
                });
                builders.show();
                break;
            case R.id.client_add_photo_img_2:
                builders.setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        client_add_photo_tv_2.setText(cities[which] + "");
                        name2 = cities[which] + "";
                    }
                });
                builders.show();
                break;
            case R.id.client_add_photo_img_3:
                builders.setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        client_add_photo_tv_3.setText(cities[which] + "");
                        name3 = cities[which] + "";
                    }
                });
                builders.show();
                break;
//                TODO 添加
            case R.id.client_add_photo_add:
                if (client_add_rl_1.getVisibility() == View.GONE) {
                    client_add_rl_1.setVisibility(View.VISIBLE);
                    client_add_ll_1.setVisibility(View.VISIBLE);
                } else if (client_add_rl_1.getVisibility() == View.VISIBLE && client_add_rl_2.getVisibility() == View.GONE) {
                    client_add_rl_2.setVisibility(View.VISIBLE);
                    client_add_ll_2.setVisibility(View.VISIBLE);
                } else if (client_add_rl_1.getVisibility() == View.VISIBLE && client_add_rl_2.getVisibility() == View.VISIBLE) {
                    Toast.makeText(MyClientAddActivity.this, "最多添加三个联系人", Toast.LENGTH_SHORT).show();
                }
                break;
//                TODO 删除
            case R.id.client_add_photo_delete_1:
                client_add_rl_1.setVisibility(View.GONE);
                client_add_ll_1.setVisibility(View.GONE);
                break;
            case R.id.client_add_photo_delete_2:
                client_add_rl_2.setVisibility(View.GONE);
                client_add_ll_2.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
                final File san = saveFile(bm, "tx.png");

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

                        Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "myClient", part);
                        Log.i("MyCL", "addPhoto：" + addPhoto.toString());
                        addPhoto.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<AddPhotoBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(AddPhotoBean addPhotoBean) {
                                        imgUrl = addPhotoBean.getData().getUrl();
                                        Glide.with(MyClientAddActivity.this).load("http://39.98.173.250:8080" + imgUrl).into(client_add_img);
                                        Log.i("MyCL", "解析完成后图片路径：" + imgUrl);

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

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<AddClientBean> addClient = fzbInterface.getAddClient(client_add_name_et.getText().toString(), imgUrl, name1, client_add_photo_et_1.getText().toString(), name2, client_add_photo_et_2.getText().toString(), name3, client_add_photo_et_3.getText().toString(), FinalContents.getUserID());
        addClient.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddClientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddClientBean addClientBean) {
                        String msg = addClientBean.getMsg();
                        if (msg.equals("成功")) {
                            Toast.makeText(MyClientAddActivity.this, "添加客户成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(MyClientAddActivity.this, "添加客户失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的客户添加客户错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


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
}
