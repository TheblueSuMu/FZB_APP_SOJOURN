package com.xcy.fzb.captain_team.fragment;


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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.GuestRoomAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.MessageBean;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
//TODO 消息房客
public class GuestRoomFragment extends Fragment{

    RecyclerView guest_room_rv;
    private String url;
    GuestRoomAdapter adapter;
    private List<MessageBean.DataBean.RowsBean> rows;
    Bitmap bitmap;

    public GuestRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_guest_room, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        guest_room_rv = getActivity().findViewById(R.id.guest_room_rv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        guest_room_rv.setLayoutManager(manager);

        initData();

    }

    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MessageBean> userMessage = fzbInterface.getMessageBeanList(FinalContents.getUserID(),FinalContents.getCityID(),"2");
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

                        adapter = new GuestRoomAdapter();
                        adapter.setRows(rows);

                        adapter.setClick(new GuestRoomAdapter.Click() {
                            @Override
                            public void ItemOnClick(int position) {
                                String phone = rows.get(position).getPhone();
                                if (phone.equals("")) {
                                    Toast.makeText(getContext(), "暂无电话信息，无法拨打", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));//跳转到拨号界面，同时传递电话号码
                                    startActivity(dialIntent);
                                }
                            }
                        });
                        adapter.setFzClick(new GuestRoomAdapter.FZClick() {
                            @Override
                            public void ItemFZOnClick(int position) {
                                //        TODO 文本复制
                                ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                clip.setText(rows.get(position).getContent() + "");
                                //        TODO 图片保存到本地
                                getBitmap("http://39.98.173.250:8080" + rows.get(position).getImgPath());
                                Toast.makeText(getContext(), "复制成功", Toast.LENGTH_SHORT).show();
                            }
                        });

                        guest_room_rv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
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
