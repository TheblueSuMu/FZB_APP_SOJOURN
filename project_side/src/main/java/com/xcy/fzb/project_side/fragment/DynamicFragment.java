package com.xcy.fzb.project_side.fragment;


import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.DynamicAdapter;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.modle.DynamicBean;
import com.xcy.fzb.project_side.presente.StatusBar;
import com.xcy.fzb.project_side.service.MyService;
import com.xcy.fzb.project_side.view.BigPhotoActivity;
import com.xcy.fzb.project_side.view.MessageCommentActivity;
import com.xcy.fzb.project_side.view.ProjectDetails;

import java.io.File;
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

/**
 * A simple {@link Fragment} subclass.
 */
//TODO 消息动态
public class DynamicFragment extends Fragment{
    private RecyclerView recyclerView;
    private String dynamicUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectHousesDynamicsList?";
    private List<DynamicBean.DataBean.RowsBean> list = new ArrayList<>();
    private DynamicAdapter recyclerAdapter;
    private String imgUrl;
    private TextView textView;
    Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());

        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        recyclerView = view.findViewById(R.id.dynamic_rv);
        textView = view.findViewById(R.id.dynamic_text);
        initView();
        return view;
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DynamicBean> userMessage = fzbInterface.getDynamicBeanList(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DynamicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(DynamicBean dynamicBean) {
                        DynamicBean.DataBean dynamicBeanData = dynamicBean.getData();
                        list = dynamicBeanData.getRows();

                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerAdapter = new DynamicAdapter(list);
                        //    TODO 联系
                        recyclerAdapter.setDynamicClick(new DynamicAdapter.LianxiClick() {
                            @Override
                            public void Click(int position) {
                                String phone = list.get(position).getCreateBy().getPhone();
                                if (phone.equals("")) {
                                    Toast.makeText(getContext(), "暂无电话信息，无法拨打", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));//跳转到拨号界面，同时传递电话号码
                                    startActivity(dialIntent);
                                }
                            }
                        });

                        //    TODO 评论
                        recyclerAdapter.setPingLun(new DynamicAdapter.PingLun() {
                            @Override
                            public void pingClick(int position) {
                                FinalContents.setTargetId(list.get(position).getId());
                                Log.i("MyCL", "ID：" + list.get(position).getId());
                                Intent intent = new Intent(getContext(), MessageCommentActivity.class);
                                intent.putExtra("headPortrait", list.get(position).getCreateBy().getPhoto());
                                intent.putExtra("title", list.get(position).getCreateBy().getName());
                                intent.putExtra("message", list.get(position).getContent());
                                intent.putExtra("img", list.get(position).getImgUrl());
                                startActivity(intent);
                            }
                        });

                        //    TODO 一键复制
                        recyclerAdapter.setFuZhi(new DynamicAdapter.FuZhi() {
                            @Override
                            public void FuZhi(int position) {
                                //        TODO 文本复制
                                ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                clip.setText(list.get(position).getContent() + "");
                                //        String imagename = list.get(position).getContent();
                                //        TODO 图片保存到本地
                                getBitmap("http://39.98.173.250:8080" + list.get(position).getImgUrl());
                                Toast.makeText(getContext(), "复制成功", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //  TODO 项目跳转
                        recyclerAdapter.setXiangMu(new DynamicAdapter.XiangMu() {
                            @Override
                            public void xm(int position) {
                                FinalContents.setProjectID(list.get(position).getProject().getId());
                                Intent intent = new Intent(getContext(), ProjectDetails.class);
                                startActivity(intent);
                            }
                        });

                        //  TODO 图片查看
                        recyclerAdapter.setTuPian(new DynamicAdapter.TuPian() {
                            @Override
                            public void tp(int position) {
                                Intent intent = new Intent(getContext(), BigPhotoActivity.class);
                                intent.putExtra("bigPhotoimg",list.get(position).getImg());
                                startActivity(intent);
                            }
                        });
                        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                            //用来标记是否正在向最后一个滑动
                            boolean isSlidingToLast = false;

                            @Override
                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                                // 当不滚动时
                                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                    //获取最后一个完全显示的ItemPosition
                                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                                    int totalItemCount = manager.getItemCount();

                                    // 判断是否滚动到底部，并且是向右滚动
                                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                                        //加载更多功能的代码
                                        textView.setVisibility(View.VISIBLE);
                                    }
                                }else {
                                    textView.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                                if (dx > 0) {
                                    //大于0表示正在向右滚动
                                    isSlidingToLast = true;
                                } else {
                                    //小于等于0表示停止或向左滚动
                                    isSlidingToLast = false;
                                }
                            }
                        });
                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //    TODO 图片转成bitmap图
    public Bitmap getBitmap(final String url) {

        Log.i("MyCL", "start：" + url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;
                Log.i("MyCL", "1");
                try {
                    Log.i("MyCL", "9");
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    Log.i("MyCL", "8");
                    e.printStackTrace();
                }
                Log.i("MyCL", "2");
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    Log.i("MyCL", "3");
                    conn.connect();
                    Log.i("MyCL", "4" + conn);
                    InputStream is = conn.getInputStream();
                    Log.i("MyCL", "5");
                    bitmap = BitmapFactory.decodeStream(is);
                    Log.i("MyCL", "bitmap：" + bitmap);

                    saveImageToGallery(getContext(), bitmap);

                    is.close();
                } catch (IOException e) {
                    Log.i("MyCL", "7");
                    e.printStackTrace();
                }
            }
        }).start();
        return bitmap;
    }

    //TODO 图片保存文件到指定路径
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        Log.i("MyCL", "bmp" + bmp);
        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dearxy";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
