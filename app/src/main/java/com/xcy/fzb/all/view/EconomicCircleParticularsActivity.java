package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.CommentListAdapter;
import com.xcy.fzb.all.api.AndroidBug5497Workaround;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.CircleBean;
import com.xcy.fzb.all.modle.EconomicCircleBean;
import com.xcy.fzb.all.modle.TotalZanBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 经济圈详情
public class EconomicCircleParticularsActivity extends AllActivity implements View.OnClickListener {

    ImageView particulars_buddha;
    ImageView particulars_img_1;
    ImageView particulars_img_2;
    ImageView particulars_img_3;
    ImageView particulars_img_4;
    ImageView particulars_img_5;
    ImageView particulars_img_6;
    RelativeLayout particulars_return;
    ImageView particulars_xiao_img;
    ImageView particulars_img_s1;
    ImageView particulars_img_s2;
    ImageView particulars_img_s3;
    ImageView particulars_zan_img;
    ImageView particulars_fb_img;
    TextView particulars_title;
    TextView particulars_message;
    TextView particulars_time;
    RecyclerView particulars_rv_comment;
    EditText particulars_et_comment;
    ImageView particulars_zan;
    TextView particulars_like;
    LinearLayout particulars_ll_zan;
    LinearLayout particulars_img_ll;

    CommentListAdapter adapter;
    private List<EconomicCircleBean.DataBean.CommentListBean> commentList;
    private EconomicCircleBean.DataBean.CircleBean circle;

    int[] zan = {R.mipmap.icon_2, R.mipmap.icon_like};
    private String likeNum;
    private String isLike;
    int flag = 0;
    private EconomicCircleBean economicCircleBean;
    private TextView particulars_xiao_size;
    private boolean whehter = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economic_circle_particulars);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            AndroidBug5497Workaround.assistActivity(this);
            initView();
            initET();
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

    private void initET() {

        StatusBar.makeStatusBarTransparent(this);

        particulars_et_comment.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    if (flag == 0) {
                        String s = particulars_et_comment.getText().toString();
                        if (s.equals("")) {
                            Toast.makeText(EconomicCircleParticularsActivity.this, "评论不能为空", Toast.LENGTH_SHORT).show();
                            flag = 0;
                        } else {
                            flag = 1;
                            if (whehter) {
                                Retrofit.Builder builder = new Retrofit.Builder();
                                builder.baseUrl(FinalContents.getBaseUrl());
                                builder.addConverterFactory(GsonConverterFactory.create());
                                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                                Retrofit build = builder.build();
                                MyService fzbInterface = build.create(MyService.class);
                                Observable<CircleBean> circleBean = fzbInterface.getCircleBean("2", s, FinalContents.getEconomicCircleID(), FinalContents.getUserID(), FinalContents.getUserID());
                                circleBean.subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Observer<CircleBean>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onNext(CircleBean circleBean) {
                                                circleBean.getData().getMessage();
                                                if (circleBean.getMsg().equals("成功")) {
                                                    Toast.makeText(EconomicCircleParticularsActivity.this, circleBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                                    particulars_et_comment.setText("");
                                                    initData();
                                                    flag = 0;

                                                } else {
                                                    Toast.makeText(EconomicCircleParticularsActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                                                    particulars_et_comment.setText("");
                                                }
                                                initData();
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                Log.i("MyCL", "EconomicCircleParticularsActivity错误信息：" + e.getMessage());
                                            }

                                            @Override
                                            public void onComplete() {

                                            }
                                        });
                                whehter = false;
                            }
                        }
                    }
                    return true;
                }
                return false;
            }

        });


    }


    private void initView() {

        particulars_xiao_size = findViewById(R.id.particulars_xiao_size);

        particulars_buddha = findViewById(R.id.particulars_buddha);
        particulars_return = findViewById(R.id.particulars_return);
        particulars_xiao_img = findViewById(R.id.particulars_xiao_img);
        particulars_img_s1 = findViewById(R.id.particulars_img_s1);
        particulars_img_s2 = findViewById(R.id.particulars_img_s2);
        particulars_img_s3 = findViewById(R.id.particulars_img_s3);
        particulars_zan_img = findViewById(R.id.particulars_zan_img);
        particulars_fb_img = findViewById(R.id.particulars_fb_img);
        particulars_title = findViewById(R.id.particulars_title);
        particulars_message = findViewById(R.id.particulars_message);
        particulars_time = findViewById(R.id.particulars_time);
        particulars_rv_comment = findViewById(R.id.particulars_rv_comment);
        particulars_et_comment = findViewById(R.id.particulars_et_comment);

        particulars_img_1 = findViewById(R.id.particulars_img_1);
        particulars_img_2 = findViewById(R.id.particulars_img_2);
        particulars_img_3 = findViewById(R.id.particulars_img_3);
        particulars_img_4 = findViewById(R.id.particulars_img_4);
        particulars_img_5 = findViewById(R.id.particulars_img_5);
        particulars_img_6 = findViewById(R.id.particulars_img_6);

        particulars_zan = findViewById(R.id.particulars_zan);
        particulars_like = findViewById(R.id.particulars_like);

        particulars_ll_zan = findViewById(R.id.particulars_ll_zan);
        particulars_img_ll = findViewById(R.id.particulars_img_ll);

        particulars_zan_img.setOnClickListener(this);
        particulars_fb_img.setOnClickListener(this);
        particulars_return.setOnClickListener(this);
        particulars_ll_zan.setOnClickListener(this);

        initData();

    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<EconomicCircleBean> economicCircleBean = fzbInterface.getEconomicCircleBean(FinalContents.getUserID(), FinalContents.getEconomicCircleID());
        economicCircleBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EconomicCircleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EconomicCircleBean economicCircleBean) {
                        circle = economicCircleBean.getData().getCircle();
//        TODO 解析完成后的操作

                        String imgUrl = circle.getImgUrl();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(imgUrl);
                        if (imgUrl.equals("")) {
                            particulars_img_ll.setVisibility(View.GONE);
                        } else {
                            particulars_img_ll.setVisibility(View.VISIBLE);
                        }
                        int j = 0;
                        int sum = 0;
//        TODO 图片加载
                        for (int i = 0; i < stringBuffer.length(); ++i) {
                            if (stringBuffer.substring(i, i + 1).equals("|")) {
                                if (sum == 0) {
                                    Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + stringBuffer.substring(0, i)).into(particulars_img_s1);
                                    sum++;
                                } else if (sum == 1) {
                                    Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + stringBuffer.substring(j, i)).into(particulars_img_s2);
                                    sum++;
                                } else if (sum == 2) {
                                    Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + stringBuffer.substring(j, i)).into(particulars_img_s3);
                                    sum++;
                                }
                                j = i + 1;
                            } else if (sum == 0 && i == stringBuffer.length() - 1) {
                                Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + stringBuffer.substring(0)).into(particulars_img_s1);
                            } else if (sum == 1 && i == stringBuffer.length() - 1) {
                                Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + stringBuffer.substring(j)).into(particulars_img_s2);
                            } else if (sum == 2 && i == stringBuffer.length() - 1) {
                                Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + stringBuffer.substring(j)).into(particulars_img_s3);
                            }
                        }


//        TODO 点赞数
                        if (circle.getIsLike().equals("0")) {
                            particulars_zan.setImageResource(zan[0]);
                            particulars_like.setText(circle.getLikeNum());
                        } else if (circle.getIsLike().equals("1")) {
                            particulars_zan.setImageResource(zan[1]);
                            particulars_like.setText(circle.getLikeNum());
                        } else {
                            particulars_zan.setImageResource(zan[0]);
                            if (circle.getLikeNum().equals("")) {
                                particulars_like.setText("0");
                            } else {
                                particulars_like.setText(circle.getLikeNum());
                            }
                        }

                        FinalContents.setTargetId(circle.getId());
//        TODO 头图像
                        Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + circle.getCreateByPhoto()).into(particulars_buddha);
//        TODO 姓名
                        particulars_title.setText(circle.getCreateByName());
                        particulars_time.setText(circle.getCreateDate());
//        TODO 内容
                        if (circle.getContent().equals("")) {
                            particulars_message.setVisibility(View.GONE);
                        } else {
                            particulars_message.setVisibility(View.VISIBLE);
                        }
                        particulars_message.setText(circle.getContent());
//        TODO 评论记录
                        commentList = economicCircleBean.getData().getCommentList();
                        if (commentList.toString().equals("")) {

                        } else {
                            initLinear();
                        }
//        TODO 点赞头像
                        List<EconomicCircleBean.DataBean.GiveListBean> giveList = economicCircleBean.getData().getGiveList();
                        int num = 0;
                        if (giveList.size() == 0) {
                            particulars_img_2.setVisibility(View.GONE);
                        } else if (giveList.size() > 0) {
                            particulars_img_2.setVisibility(View.GONE);
                            particulars_img_3.setVisibility(View.GONE);
                            particulars_img_4.setVisibility(View.GONE);
                            particulars_img_5.setVisibility(View.GONE);
                            particulars_img_6.setVisibility(View.GONE);

                            for (int i = 0; i < giveList.size(); ++i) {
                                num++;
                                if (num == 1) {
                                    particulars_img_2.setVisibility(View.VISIBLE);
                                    Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + giveList.get(i).getUser().getPhoto()).into(particulars_img_2);
                                } else if (num == 2) {
                                    particulars_img_3.setVisibility(View.VISIBLE);
                                    Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + giveList.get(i).getUser().getPhoto()).into(particulars_img_3);
                                } else if (num == 3) {
                                    particulars_img_4.setVisibility(View.VISIBLE);
                                    Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + giveList.get(i).getUser().getPhoto()).into(particulars_img_4);
                                } else if (num == 4) {
                                    particulars_img_5.setVisibility(View.VISIBLE);
                                    Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + giveList.get(i).getUser().getPhoto()).into(particulars_img_5);
                                } else if (num == 5) {
                                    particulars_img_6.setVisibility(View.VISIBLE);
                                    Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + giveList.get(i).getUser().getPhoto()).into(particulars_img_6);
                                } else if (num >= 6) {
                                    particulars_img_1.setVisibility(View.VISIBLE);
                                }
                            }
                        }

                        flag = 0;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "EconomicCircleParticularsActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @SuppressLint("WrongConstant")
    private void initLinear() {
        particulars_xiao_size.setText("全部"+commentList.size()+"条评论");
        LinearLayoutManager manager = new LinearLayoutManager(EconomicCircleParticularsActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        particulars_rv_comment.setLayoutManager(manager);
//        TODO 适配器
        adapter = new CommentListAdapter();
        adapter.setCommentList(commentList);
        particulars_rv_comment.setAdapter(adapter);
        whehter = true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.particulars_return:
                finish();
                break;
//            TODO 赞
            case R.id.particulars_zan_img:

                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Observable<TotalZanBean> totalLikeNum = fzbInterface.getTotalLikeNum(FinalContents.getTargetId(), FinalContents.getUserID(), "1");
                totalLikeNum.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TotalZanBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TotalZanBean totalZanBean) {
//                                if (totalZanBean.getData().getStatus().equals("0")) {
//                                    particulars_like.setText(totalZanBean.getData().getLikeNum() + "");
//                                    particulars_zan.setImageResource(zan[1]);
//                                } else {
//                                    particulars_zan.setImageResource(zan[0]);
//                                    particulars_like.setText(totalZanBean.getData().getLikeNum() + "");
//                                }
                                initData();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("经济圈点赞", "点赞错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
//              TODO 评论
            case R.id.particulars_fb_img:


                break;
            case R.id.particulars_ll_zan:
                Retrofit.Builder builder1 = new Retrofit.Builder();
                builder1.baseUrl(FinalContents.getBaseUrl());
                builder1.addConverterFactory(GsonConverterFactory.create());
                builder1.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build1 = builder1.build();
                MyService fzbInterface1 = build1.create(MyService.class);
                Observable<TotalZanBean> totalLikeNum1 = fzbInterface1.getTotalLikeNum(FinalContents.getTargetId(), FinalContents.getUserID(), "1");
                totalLikeNum1.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TotalZanBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TotalZanBean totalZanBean) {
//                                if (totalZanBean.getData().getStatus().equals("0")) {
//                                    particulars_like.setText(totalZanBean.getData().getLikeNum() + "");
//                                    particulars_zan.setImageResource(zan[1]);
//                                } else {
//                                    particulars_zan.setImageResource(zan[0]);
//                                    particulars_like.setText(totalZanBean.getData().getLikeNum() + "");
//                                }
                                initData();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("经济圈点赞", "点赞错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }

    }
}
