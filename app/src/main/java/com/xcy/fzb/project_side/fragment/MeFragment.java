package com.xcy.fzb.project_side.fragment;


import android.annotation.SuppressLint;
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
import com.xcy.fzb.all.modle.UserBean;
import com.xcy.fzb.all.persente.CleanDataUtils;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {

    ImageView img_my_the_project_end;
    TextView name_my_the_project_end;
    TextView city_my_the_project_end;
    TextView position_my_the_project_end;
    TextView shop_my_the_project_end;

    RelativeLayout collect_my_the_project_end;
    RelativeLayout comment_my_the_project_end;
    RelativeLayout about_my_the_project_end;
    RelativeLayout empty_my_the_project_end;
    RelativeLayout exit_my_the_project_end;
    RelativeLayout side_rl;
    private Intent intent;
    private TextView my_tv_huancun;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.project_side_fragment_my_the_project_end, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        img_my_the_project_end = getActivity().findViewById(R.id.img_my_the_project_end);
        name_my_the_project_end = getActivity().findViewById(R.id.name_my_the_project_end);
        city_my_the_project_end = getActivity().findViewById(R.id.city_my_the_project_end);
        position_my_the_project_end = getActivity().findViewById(R.id.position_my_the_project_end);
        shop_my_the_project_end = getActivity().findViewById(R.id.shop_my_the_project_end);
        side_rl = getActivity().findViewById(R.id.side_rl);

        collect_my_the_project_end = getActivity().findViewById(R.id.collect_my_the_project_end);
        comment_my_the_project_end = getActivity().findViewById(R.id.comment_my_the_project_end);
        about_my_the_project_end = getActivity().findViewById(R.id.about_my_the_project_end);
        empty_my_the_project_end = getActivity().findViewById(R.id.empty_my_the_project_end);
        exit_my_the_project_end = getActivity().findViewById(R.id.exit_my_the_project_end);

        my_tv_huancun = getActivity().findViewById(R.id.my_tv_huancun);
        try {
            my_tv_huancun.setText(CleanDataUtils.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        side_rl.setOnClickListener(this);
        collect_my_the_project_end.setOnClickListener(this);
        comment_my_the_project_end.setOnClickListener(this);
        about_my_the_project_end.setOnClickListener(this);
        empty_my_the_project_end.setOnClickListener(this);
        exit_my_the_project_end.setOnClickListener(this);


        initUser();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 我的收藏
            case R.id.collect_my_the_project_end:
                intent = new Intent(getContext(), CollectActivity.class);
                startActivity(intent);
                break;
//                TODO 个人信息
            case R.id.side_rl:
                intent = new Intent(getContext(), PersonalInformationActivity.class);
                startActivity(intent);
                break;

//                TODO 意见反馈
            case R.id.comment_my_the_project_end:
                intent = new Intent(getContext(), FeedbackActivity.class);
                startActivity(intent);
                break;
//                TODO 关于房坐标
            case R.id.about_my_the_project_end:
                intent = new Intent(getContext(), AboutFZBActivity.class);
                startActivity(intent);
                break;
//                TODO 清空缓存
            case R.id.empty_my_the_project_end:
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
                break;
//                TODO 退出登录
            case R.id.exit_my_the_project_end:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                builder2.setTitle("退出完成");
                builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FinalContents.setIFSP("1");
                        intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        FinalContents.setDengLu("0");
                    }
                });
                builder2.show();
                break;
        }

    }

    //TODO 用户信息赋值
    private void initUser(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UserBean> userMessage = fzbInterface.getUserBean(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(UserBean userBean) {
                        Glide.with(getActivity()).load("http://39.98.173.250:8080" + userBean.getData().getPhoto()).into(img_my_the_project_end);
                        name_my_the_project_end.setText(userBean.getData().getName());
                        city_my_the_project_end.setText(userBean.getData().getCity());
                        shop_my_the_project_end.setText(userBean.getData().getCityName());
                        if (userBean.getData().getIdentity().equals("4")) {
                            position_my_the_project_end.setText("专案");
                        }
                        FinalContents.setCityID(userBean.getData().getCityId());
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

    @Override
    public void onResume() {
        super.onResume();

        initUser();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //TODO now visible to user 不显示fragment
        } else {
            onResume();
            //TODO now invisible to user 显示fragment
        }
    }
}
