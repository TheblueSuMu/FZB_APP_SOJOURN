package com.xcy.fzb.project_side.view;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.GridViewAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.AddPhotoBean;
import com.xcy.fzb.project_side.modle.FeedBackBean;
import com.xcy.fzb.project_side.presente.StatusBar;
import com.xcy.fzb.project_side.service.MyService;

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

//TODO 意见反馈
public class FeedbackTheProjectEndActivity extends AppCompatActivity {

    ImageView feedback_return;
    EditText feedback_editText;
    Button feedback_btn;
    private String message;

    private List<String> mDatas;
    GridView mGridView;
    private GridViewAdapter adapter;

    private final String IMAGE_TYPE = "image/*";

    private final int IMAGE_CODE = 0;
    private Bitmap bm;
    StringBuffer stringBuffer = new StringBuffer();
    String myImgurl;
    private StringBuffer imgurl;
    int sum = 0;
    //
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_the_project_end);

        initView();

    }


    public void initView() {

        StatusBar.makeStatusBarTransparent(this);

        mGridView = findViewById(R.id.gv_the_project_test);

        feedback_btn = findViewById(R.id.feedback_the_project_btn);
        feedback_editText = findViewById(R.id.feedback_the_project_editText);
        feedback_return = findViewById(R.id.feedback_the_project_return);
        //返回上一级的点击事件
        feedback_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //选择图片路径的点击事件
        initData();

        //TODO 提交按钮的点击事件
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = feedback_editText.getText().toString();
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                final Observable<FeedBackBean> feedBack = fzbInterface.getFeedBack(message, stringBuffer.toString(), FinalContents.getUserID());
                feedBack.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<FeedBackBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(FeedBackBean feedBackBean) {
                                String msg = feedBackBean.getMsg();
                                if (msg.equals("成功")) {
                                    Toast.makeText(FeedbackTheProjectEndActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(FeedbackTheProjectEndActivity.this, "提交失败，请从新提交", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("MyCL", "意见反馈错误信息" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }

    private void initData() {

        View inflate = View.inflate(FeedbackTheProjectEndActivity.this, R.layout.item_flowlayout, null);
        mDatas = new ArrayList<>();
        adapter = new GridViewAdapter(FeedbackTheProjectEndActivity.this, mDatas);
        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(FeedbackTheProjectEndActivity.this);
                    builder.setTitle("请选择图片来源");
                    builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i == 0) {

                                try {
                                    //检测是否有写的权限
                                    int permission = ActivityCompat.checkSelfPermission(FeedbackTheProjectEndActivity.this,
                                            "android.permission.WRITE_EXTERNAL_STORAGE");
                                    if (permission != PackageManager.PERMISSION_GRANTED) {
                                        // 没有写的权限，去申请写的权限，
                                        ActivityCompat.requestPermissions(FeedbackTheProjectEndActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                                    Uri photoUri = FeedbackTheProjectEndActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


//TODO Uri图片转换成file类型的 start
                                    final File file = uri2File(photoUri);
                                    Log.i("MyCL", "Uri图片转换成file类型的：" + file);

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

                                            Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "经济圈图片", part);
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
                                                            myImgurl = addPhotoBean.getData().getUrl();
                                                            mDatas.add(myImgurl);
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
                    adapter = new GridViewAdapter(FeedbackTheProjectEndActivity.this, mDatas);
                    mGridView.setAdapter(adapter);

                }

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
                final File san = saveFile(bm, sum + ".png");
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

                        Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "经济圈图片", part);
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
                                        myImgurl = addPhotoBean.getData().getUrl();
                                        mDatas.add(myImgurl);
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
}
