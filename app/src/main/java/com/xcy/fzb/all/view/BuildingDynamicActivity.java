package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.Dynamic2Adapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.Dynamic2Bean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

public class BuildingDynamicActivity extends AllActivity implements Dynamic2Adapter.PingLun, Dynamic2Adapter.FuZhi, Dynamic2Adapter.LianxiClick {

    LinearLayout dynamic_return;
    RecyclerView dynamic_rv;
    private String dynamicUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectHousesDynamicsList?";
    private Dynamic2Bean.DataBean dynamicBeanData;
    private List<Dynamic2Bean.DataBean.RowsBean> list;
    private Dynamic2Adapter recyclerAdapter;

    private int j;
    private int num;
    List<String> lists;
    private String imgURl;//图片的URL地址
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    Toast.makeText(BuildingDynamicActivity.this, "开始保存图片...", Toast.LENGTH_SHORT).show();
//                    mSaveBtn.setClickable(false);
                    break;
                case SAVE_SUCCESS:
                    Toast.makeText(BuildingDynamicActivity.this, "图片保存成功,请到相册查找...", Toast.LENGTH_SHORT).show();
//                    mSaveBtn.setClickable(true);
                    break;
                case SAVE_FAILURE:
                    Toast.makeText(BuildingDynamicActivity.this, "图片保存失败,请稍后再试...", Toast.LENGTH_SHORT).show();
//                    mSaveBtn.setClickable(true);
                    break;
            }
        }
    };
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_dynamic);


        initView();

    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        dynamic_return = findViewById(R.id.dynamic_return);
        dynamic_rv = findViewById(R.id.dynamic_rv);
//        TODO 返回上一级
        dynamic_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        TODO 数据处理类
        initData();

    }

    @SuppressLint("WrongConstant")
    private void initData() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dynamic_rv.setLayoutManager(layoutManager);
        recyclerAdapter = new Dynamic2Adapter();
        recyclerAdapter.setDynamicClick(this);
        recyclerAdapter.setPingLun(this);
        recyclerAdapter.setFuZhi(this);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<Dynamic2Bean> dynamicBean1 = fzbInterface.getDynamicBean(FinalContents.getUserID(), FinalContents.getProjectID(),"1000");
        dynamicBean1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Dynamic2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Dynamic2Bean dynamicBean) {
                        dynamicBeanData = dynamicBean.getData();
                        list = dynamicBeanData.getRows();
                        FinalContents.setTargetId(FinalContents.getProjectID());
//  TODO 图片查看
                        recyclerAdapter.setTuPian(new Dynamic2Adapter.TuPian() {
                            @Override
                            public void tp(int position) {
                                Intent intent = new Intent(BuildingDynamicActivity.this, BigPhotoActivity.class);
                                intent.putExtra("index",position);
                                intent.putExtra("bigPhotoimg", list.get(position).getImg());
                                startActivity(intent);
                            }
                        });
                        recyclerAdapter.setList(list);
                        recyclerAdapter.setBuiling(1);
                        dynamic_rv.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "BuildingDynamicActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void pingClick(int position) {
        FinalContents.setTargetId(list.get(position).getId());
        Log.i("MyCL", "ID：" + list.get(position).getId());
        Intent intent = new Intent(BuildingDynamicActivity.this, MessageCommentActivity.class);
        intent.putExtra("headPortrait", list.get(position).getCreateBy().getPhoto());
        intent.putExtra("title", list.get(position).getCreateBy().getName());
        intent.putExtra("message", list.get(position).getContent());
        intent.putExtra("img", list.get(position).getImgUrl());
        startActivity(intent);
    }

    @Override
    public void FuZhi(int position) {
        if (list.get(position).getImgUrl().equals("")) {

        }else {
            lists = new ArrayList<>();
            //        TODO 文本复制
            ClipboardManager clip = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip.setText(list.get(position).getContent() + "");
            //        String imagename = list.get(position).getContent();
            //        TODO 图片保存到本地

            String UrlImage = list.get(position).getImgUrl();
            final String[] a  = list.get(position).getImgUrl().split("[|]");
            for (int i = 0; i < a.length; i++){
                Log.i("分割图片","图片："+ a[i]);
                final  int finalI = i;
                Log.i("分割图片","图片151："+imgURl);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        url = a[finalI];
                        imgURl = "http://39.98.173.250:8080" + url;
                        mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                        Bitmap bp = returnBitMap(imgURl);
                        Log.i("MyCL", "bp：" + bp);
                        saveImageToPhotos(BuildingDynamicActivity.this, bp);
                    }
                }).start();
            }
            Toast.makeText(BuildingDynamicActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            num = 0;

        }
    }

    @Override
    public void Click(final int position) {

        String phone = list.get(position).getAttaches().get(0).getPhone();
        if (phone.equals("")) {
            Toast.makeText(BuildingDynamicActivity.this, "暂无电话信息，无法拨打", Toast.LENGTH_SHORT).show();
        } else {
            final List<String> arrayList = new ArrayList<>();
            for (int i = 0; i < list.get(position).getAttaches().size(); i++) {
                arrayList.add(list.get(position).getAttaches().get(i).getName());
            }
            //      监听选中
            OptionsPickerView pvOptions = new OptionsPickerBuilder(BuildingDynamicActivity.this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //               返回的分别是三个级别的选中位置
                    //              展示选中数据
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(position).getAttaches().get(options1).getPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
            })
                    .setSelectOptions(0)//设置选择第一个
                    .setOutSideCancelable(false)//点击背的地方不消失
                    .build();//创建
            //      把数据绑定到控件上面
            pvOptions.setPicker(arrayList);
            //      展示
            pvOptions.show();
        }

    }

    /**
     * 保存二维码到本地相册
     */
    private void saveImageToPhotos(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            mHandler.obtainMessage(SAVE_FAILURE).sendToTarget();
//            return;
//        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        mHandler.obtainMessage(SAVE_SUCCESS).sendToTarget();
    }

    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    public final static Bitmap returnBitMap(String url) {
        URL myFileUrl;
        Bitmap bitmap = null;
        Log.i("MyCL", "1");
        try {
            myFileUrl = new URL(url);
            Log.i("MyCL", "2");
            HttpURLConnection conn;
            Log.i("MyCL", "3");
            conn = (HttpURLConnection) myFileUrl.openConnection();
            Log.i("MyCL", "4");
            conn.setDoInput(true);
            Log.i("MyCL", "5");
            conn.connect();
            Log.i("MyCL", "6");
            InputStream is = conn.getInputStream();
            Log.i("MyCL", "7");
            bitmap = BitmapFactory.decodeStream(is);
            Log.i("MyCL", "8");
        } catch (MalformedURLException e) {
            Log.i("MyCL", "9");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("MyCL", "10");
            e.printStackTrace();
        }
        Log.i("MyCL", "11");
        return bitmap;
    }
}
