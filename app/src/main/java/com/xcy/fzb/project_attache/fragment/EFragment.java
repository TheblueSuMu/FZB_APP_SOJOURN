package com.xcy.fzb.project_attache.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.Login.LoginActivity;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.ZYDataBean;
import com.xcy.fzb.all.persente.CleanDataUtils;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.AboutFZBActivity;
import com.xcy.fzb.all.view.CollectActivity;
import com.xcy.fzb.all.view.FeedbackActivity;
import com.xcy.fzb.all.view.PersonalInformationActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EFragment extends Fragment implements View.OnClickListener {

    RelativeLayout my_collect;
    RelativeLayout my_comment;
    RelativeLayout my_about;
    RelativeLayout my_empty;
    RelativeLayout my_exit;
    RelativeLayout me_gr;
    RelativeLayout my_rl_1;

    ImageView me_photo;
    TextView me_name;
    TextView me_identity;
    TextView me_city;
    TextView me_store;
    private TextView my_tv_huancun;
    private Intent intent;
    private ZYDataBean.DataBean data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.project_attache_modulebroker_fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//TODO 设置导航栏、标题栏透明
        StatusBar.makeStatusBarTransparent(getActivity());

        me_gr = getActivity().findViewById(R.id.me_gr);//个人中心
        my_collect = getActivity().findViewById(R.id.my_collect);//我的收藏
        my_comment = getActivity().findViewById(R.id.my_comment);//意见反馈
        my_about = getActivity().findViewById(R.id.my_about);//关于房坐标
        my_empty = getActivity().findViewById(R.id.my_empty);//清空缓存
        my_exit = getActivity().findViewById(R.id.my_exit);//退出登录

        my_rl_1 = getActivity().findViewById(R.id.my_rl_1);
        me_photo = getActivity().findViewById(R.id.me_photo);
        me_name = getActivity().findViewById(R.id.me_name);
        me_identity = getActivity().findViewById(R.id.me_identity);
        me_city = getActivity().findViewById(R.id.me_city);
        me_store = getActivity().findViewById(R.id.me_store);

        my_tv_huancun = getActivity().findViewById(R.id.my_tv_huancun);
        try {
            my_tv_huancun.setText(CleanDataUtils.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        my_rl_1.setOnClickListener(this);
        my_collect.setOnClickListener(this);
        my_comment.setOnClickListener(this);
        my_about.setOnClickListener(this);
        my_empty.setOnClickListener(this);
        my_exit.setOnClickListener(this);
//        根据用户Id获取用户信息
        initUserMessage();

    }

    private void initUserMessage() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ZYDataBean> userMessage = fzbInterface.getZYDataBean(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZYDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZYDataBean userMessageBean) {
                        data = userMessageBean.getData();

                        String s = data.getPhoto().toString();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(s);

                        Glide.with(getActivity()).load("http://39.98.173.250:8080" + data.getPhoto()).into(me_photo);

                        me_name.setText(data.getName());
                        if (userMessageBean.getData().getIdentity().equals("5")) {
                            me_identity.setText("专员");
                        }
                        me_city.setText(data.getCity());
                        me_store.setText(data.getStoreManage()+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "根据用户id获取用户信息错误:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //点击事件

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.my_collect) {
//            TODO 我的收藏
            Log.i("MyCL", "我的收藏");
            intent = new Intent(getContext(), CollectActivity.class);

            startActivity(intent);
        }else if (id == R.id.my_rl_1) {
//            TODO 个人信息
            Log.i("MyCL", "个人信息");
            intent = new Intent(getContext(), PersonalInformationActivity.class);

            startActivity(intent);
        } else if (id == R.id.my_comment) {
//            TODO 意见反馈
//            Log.i("MyCL", "意见反馈");
            intent = new Intent(getContext(), FeedbackActivity.class);
            startActivity(intent);
        } else if (id == R.id.my_about) {
//            TODO 关于房坐标
            intent = new Intent(getContext(), AboutFZBActivity.class);
            startActivity(intent);
        } else if (id == R.id.my_empty) {
//            TODO 清空缓存
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("确认清除缓存吗?");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        String totalCacheSize = CleanDataUtils.getTotalCacheSize(getActivity());
                        CleanDataUtils.clearAllCache(getActivity());
                        Toast.makeText(getActivity(), "清理缓存成功,共清理了" + totalCacheSize + "内存", Toast.LENGTH_SHORT).show();
                        my_tv_huancun.setText("0 M");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "取消清理", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        } else if (id == R.id.my_exit) {
//            TODO 退出登录
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("退出完成");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FinalContents.setIFSP("1");
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    FinalContents.setDengLu("0");
                }
            });
            builder.show();

        }

    }

    @Override
    public void onResume() {
        super.onResume();

        initUserMessage();
    }


}