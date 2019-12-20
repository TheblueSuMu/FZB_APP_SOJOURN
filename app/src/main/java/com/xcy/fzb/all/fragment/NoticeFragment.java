package com.xcy.fzb.all.fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.NoticeAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.MessageBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.ToastUtil;
import com.xcy.fzb.all.view.BigPhotoActivity;

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

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
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
//TODO 消息通知
public class NoticeFragment extends Fragment {
    PtrClassicFrameLayout ptrClassicFrameLayout;
    private int j;
    private int num;
    List<String> list;
    private String imgURl;//图片的URL地址
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    Toast.makeText(getContext(), "开始保存图片...", Toast.LENGTH_SHORT).show();
//                    mSaveBtn.setClickable(false);
                    break;
                case SAVE_SUCCESS:
                    Toast.makeText(getContext(), "图片保存成功,请到相册查找...", Toast.LENGTH_SHORT).show();
//                    mSaveBtn.setClickable(true);
                    break;
                case SAVE_FAILURE:
                    Toast.makeText(getContext(), "图片保存失败,请稍后再试...", Toast.LENGTH_SHORT).show();
//                    mSaveBtn.setClickable(true);
                    break;
            }
        }
    };

    RecyclerView notice_rv;
    private String url;
    private List<MessageBean.DataBean.RowsBean> rows;
    Bitmap bitmap;
    private ProgressDialog progressDialog;
    private ImageView all_no_information_notice;
    private NoticeAdapter adapter;

    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_notice, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        notice_rv = getActivity().findViewById(R.id.notice_rv);
        all_no_information_notice = getActivity().findViewById(R.id.all_no_information_notice);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        notice_rv.setLayoutManager(manager);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) getActivity().findViewById(R.id.store_house_ptr_frame_12);

        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        initData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        initData();
    }

    @SuppressLint("WrongConstant")
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MessageBean> userMessage = fzbInterface.getMessageBeanList(FinalContents.getUserID(), FinalContents.getCityID(), "0", "1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MessageBean messageBean) {
                        MessageBean.DataBean data1 = messageBean.getData();
                        rows = data1.getRows();
                        Log.i("列表数据加载", "加载");
                        if (rows.size() != 0) {
                            try {
                                all_no_information_notice.setVisibility(View.GONE);
                                notice_rv.setVisibility(View.VISIBLE);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                adapter = new NoticeAdapter();
                                adapter.setRows(rows);
                                notice_rv.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            adapter.setClick(new NoticeAdapter.Click() {
                                @Override
                                public void ItemOnClick(int position) {
                                    String phone = rows.get(position).getPhone();
                                    if (phone.equals("")) {
                                        ToastUtil.showLongToast(getContext(),"暂无电话信息，无法拨打");
                                    } else {
                                        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));//跳转到拨号界面，同时传递电话号码
                                        startActivity(dialIntent);
                                    }
                                }
                            });

                            adapter.setMyImage(new NoticeAdapter.MyImage() {
                                @Override
                                public void MyImage(int position) {
                                    Intent intent = new Intent(getContext(), BigPhotoActivity.class);
                                    intent.putExtra("index", position);
                                    intent.putExtra("bigPhotoimg", rows.get(position).getImgPath());
                                    startActivity(intent);
                                }
                            });

                            adapter.setFzClick(new NoticeAdapter.FZClick() {
                                @Override
                                public void ItemFZOnClick(int position) {
                                    //        TODO 文本复制
                                    ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                    clip.setText(rows.get(position).getContent());

                                    list = new ArrayList<>();
                                    String UrlImage = rows.get(position).getImgPath();
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append(UrlImage);
                                    j = 0;
                                    num = 0;
                                    for (int i = 0; i < stringBuffer.length(); ++i) {
                                        if (stringBuffer.substring(i, i + 1).equals("|")) {
                                            list.add(stringBuffer.substring(j, i));
                                            j = i + 1;
                                            num = 1;
                                        }
                                    }
                                    if (num == 1) {
                                        list.add(stringBuffer.substring(j));
                                    }
//        String imagename = list.get(position).getContent();
////        TODO 图片保存到本地
                                    if (rows.get(position).getImgPath().equals("")) {

                                    } else {
                                        if (num == 0) {
                                            imgURl = FinalContents.getImageUrl() + rows.get(position).getImgPath();
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                                                    Bitmap bp = returnBitMap(imgURl);
                                                    Log.i("MyCL", "bp：" + bp);
                                                    saveImageToPhotos(getContext(), bp);
                                                }
                                            }).start();
                                        } else {
                                            for (int i = 0; i < list.size(); ++i) {
                                                imgURl = FinalContents.getImageUrl() + list.get(i);
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                                                        Bitmap bp = returnBitMap(imgURl);
                                                        Log.i("MyCL", "bp：" + bp);
                                                        saveImageToPhotos(getContext(), bp);
                                                    }
                                                }).start();
                                            }
                                        }
                                    }
                                    ToastUtil.showLongToast(getContext(),"复制成功");
                                    num = 0;
                                }
                            });

                        }else {
                            try {
                                all_no_information_notice.setVisibility(View.VISIBLE);
                                notice_rv.setVisibility(View.GONE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            all_no_information_notice.setVisibility(View.VISIBLE);
                            notice_rv.setVisibility(View.GONE);
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            mHandler.obtainMessage(SAVE_FAILURE).sendToTarget();
            return;
        }
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //TODO now visible to user 不显示fragment
        } else {
            initData();
            //TODO now invisible to user 显示fragment
        }
    }

}
