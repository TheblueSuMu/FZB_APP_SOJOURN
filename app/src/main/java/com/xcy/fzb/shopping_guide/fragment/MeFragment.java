package com.xcy.fzb.shopping_guide.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.Login.LoginActivity;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.Connector;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.modle.UserBean;
import com.xcy.fzb.all.persente.CleanDataUtils;
import com.xcy.fzb.all.persente.SingleClick;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.ToastUtil;
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
public class MeFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

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
    RelativeLayout guide_rl;
    private Intent intent;
    private TextView my_tv_huancun;
    private SwipeRefreshLayout layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.shopping_guide_fragment_my_the_project_end, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        img_my_the_project_end = getActivity().findViewById(R.id.img_my_the_project_end);
        name_my_the_project_end = getActivity().findViewById(R.id.name_my_the_project_end);
        city_my_the_project_end = getActivity().findViewById(R.id.city_my_the_project_end);
        position_my_the_project_end = getActivity().findViewById(R.id.position_my_the_project_end);
        shop_my_the_project_end = getActivity().findViewById(R.id.shop_my_the_project_end);
        guide_rl = getActivity().findViewById(R.id.guide_rl);
        layout = getActivity().findViewById(R.id.e_ssrfl_2);
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
        layout.setOnRefreshListener(this);
        guide_rl.setOnClickListener(this);
        collect_my_the_project_end.setOnClickListener(this);
        comment_my_the_project_end.setOnClickListener(this);
        about_my_the_project_end.setOnClickListener(this);
        empty_my_the_project_end.setOnClickListener(this);
        exit_my_the_project_end.setOnClickListener(this);

        initUser();

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

//                TODO 我的动态
            case R.id.collect_my_the_project_end:
                intent = new Intent(getContext(), CollectActivity.class);
                startActivity(intent);
                break;
            case R.id.guide_rl:
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
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                View inflate2 = LayoutInflater.from(getActivity()).inflate(R.layout.binding_report, null, false);
                builder2.setView(inflate2);
                final AlertDialog show2 = builder2.show();
                show2.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);

                WindowManager m2 = getActivity().getWindowManager();
                Display d2 = m2.getDefaultDisplay();
                WindowManager.LayoutParams attributes2 = show2.getWindow().getAttributes();
                attributes2.width = (int)(d2.getWidth() - 200);
                show2.getWindow().setAttributes(attributes2);
                show2.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);
                TextView report_binding_title2 = inflate2.findViewById(R.id.report_binding_title);
                TextView report_binding_confirm_tv2 = inflate2.findViewById(R.id.report_binding_confirm_tv);
                TextView report_binding_cancel_tv2 = inflate2.findViewById(R.id.report_binding_cancel_tv);
                RelativeLayout report_binding_cancel2 = inflate2.findViewById(R.id.report_binding_cancel);
                RelativeLayout report_binding_confirm2 = inflate2.findViewById(R.id.report_binding_confirm);
                report_binding_title2.setText("确认清除缓存吗?");//内容
                report_binding_confirm_tv2.setText("确定");
                report_binding_cancel_tv2.setText("取消");
                report_binding_title2.setTextColor(Color.parseColor("#111111"));
                report_binding_cancel_tv2.setTextColor(Color.parseColor("#334485"));
                report_binding_confirm_tv2.setTextColor(Color.parseColor("#334485"));
                report_binding_cancel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show2.dismiss();
                    }
                });
                report_binding_confirm2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String totalCacheSize = CleanDataUtils.getTotalCacheSize(getActivity());
                            CleanDataUtils.clearAllCache(getActivity());
                            ToastUtil.showLongToast(getContext(),"清理缓存成功,共清理了" + totalCacheSize + "内存");
                            my_tv_huancun.setText("0 M");
                            show2.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
//                TODO 退出登录
            case R.id.exit_my_the_project_end:
                //            TODO 退出登录
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.binding_report, null, false);
                builder1.setView(inflate);
                final AlertDialog show1 = builder1.show();
                show1.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);

                WindowManager m = getActivity().getWindowManager();
                Display d = m.getDefaultDisplay();
                WindowManager.LayoutParams attributes = show1.getWindow().getAttributes();
                attributes.width = (int)(d.getWidth() - 200);
                show1.getWindow().setAttributes(attributes);
                show1.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);
                TextView report_binding_title = inflate.findViewById(R.id.report_binding_title);
                TextView report_binding_confirm_tv = inflate.findViewById(R.id.report_binding_confirm_tv);
                TextView report_binding_cancel_tv = inflate.findViewById(R.id.report_binding_cancel_tv);
                RelativeLayout report_binding_cancel = inflate.findViewById(R.id.report_binding_cancel);
                RelativeLayout report_binding_confirm = inflate.findViewById(R.id.report_binding_confirm);
                report_binding_title.setText("确定要退出程序吗?");//内容
                report_binding_confirm_tv.setText("确定");
                report_binding_cancel_tv.setText("取消");
                report_binding_title.setTextColor(Color.parseColor("#111111"));
                report_binding_cancel_tv.setTextColor(Color.parseColor("#334485"));
                report_binding_confirm_tv.setTextColor(Color.parseColor("#334485"));
                report_binding_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show1.dismiss();
                    }
                });
                report_binding_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FinalContents.setIFSP("1");
                        intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        FinalContents.setDengLu("0");
                        show1.dismiss();
                    }
                });
                break;
        }

    }


    //TODO 用户信息赋值
    private void initUser() {
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
                        Glide.with(getActivity()).load(FinalContents.getImageUrl() + userBean.getData().getPhoto()).into(img_my_the_project_end);
                        name_my_the_project_end.setText(userBean.getData().getName());
                        city_my_the_project_end.setText(userBean.getData().getCity());
                        shop_my_the_project_end.setText(userBean.getData().getCityName());
                        if (userBean.getData().getIdentity().equals("7")) {
                            position_my_the_project_end.setText("导购");
                        }
                        FinalContents.setCityID(userBean.getData().getCityId());

                        Connector.setUserBean(userBean);
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

    private void init(){
        UserBean userBean = Connector.getUserBean();
        Glide.with(getActivity()).load(FinalContents.getImageUrl() + userBean.getData().getPhoto()).into(img_my_the_project_end);
        name_my_the_project_end.setText(userBean.getData().getName());
        city_my_the_project_end.setText(userBean.getData().getCity());
        shop_my_the_project_end.setText(userBean.getData().getCityName());
        if (userBean.getData().getIdentity().equals("7")) {
            position_my_the_project_end.setText("导购");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NewlyIncreased.getUserMessage().equals("7")){
            init();
            NewlyIncreased.setUserMessage("");
        }
    }

    @Override
    public void onRefresh() {
        if (layout.isRefreshing()) {//如果正在刷新
//            initView();
//            initHotList();
            initUser();
            layout.setRefreshing(false);//取消刷新
        }
    }
}
