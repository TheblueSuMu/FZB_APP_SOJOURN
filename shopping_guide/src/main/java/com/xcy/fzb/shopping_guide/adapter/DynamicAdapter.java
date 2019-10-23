package com.xcy.fzb.shopping_guide.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.DynamicBean;
import com.xcy.fzb.shopping_guide.modle.LikeNumBean;
import com.xcy.fzb.shopping_guide.persente.OkHttpPost;
import com.xcy.fzb.shopping_guide.service.MyService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.ViewHolder> {

    private List<DynamicBean.DataBean.RowsBean> list = new ArrayList<>();
    private Context context;
    LianxiClick lianxiClick;
    PingLun pingLun;
    FuZhi fuZhi;
    XiangMu xiangMu;
    TuPian tuPian;
    private StringBuffer stringBuffer;
    int num = 0;
    int j = 0;

    public void setTuPian(TuPian tuPian) {
        this.tuPian = tuPian;
    }

    private int builing = 0;

    public void setBuiling(int builing) {
        this.builing = builing;
    }

    public void setXiangMu(XiangMu xiangMu) {
        this.xiangMu = xiangMu;
    }

    public void setFuZhi(FuZhi fuZhi) {
        this.fuZhi = fuZhi;
    }

    public void setPingLun(PingLun pingLun) {
        this.pingLun = pingLun;
    }

    public void setDynamicClick(LianxiClick lianxiClick) {
        this.lianxiClick = lianxiClick;
    }

    public DynamicAdapter(List<DynamicBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dynamic, parent, false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(context).load("http://39.98.173.250:8080" + list.get(position).getCreateBy().getPhoto()).into(holder.circle_img);

        if (list.get(position).getImg().equals("")) {
            holder.item_dynamic_ll.setVisibility(View.VISIBLE);
            String img = list.get(position).getImg();
            stringBuffer = new StringBuffer();
            stringBuffer.append(img);
            for (int i = 0; i < stringBuffer.length(); ++i) {
                if (stringBuffer.substring(i, i + 1).equals("|") && num == 0) {
                    j = i + 1;
                    num++;
                }
            }
            if (num == 0) {
                Glide.with(context).load("http://39.98.173.250:8080" + list.get(position).getImg()).into(holder.circle_img_rv);
            } else {
                Glide.with(context).load("http://39.98.173.250:8080" + stringBuffer.substring(0, j)).into(holder.circle_img_rv);
                Log.i("MyCL", "楼盘动态图片：" + stringBuffer.substring(0, j));
            }
        } else {
            holder.item_dynamic_ll.setVisibility(View.GONE);
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LikeNumBean> userMessage = fzbInterface.getLikeNum(list.get(position).getId(), FinalContents.getUserID(), "1");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LikeNumBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LikeNumBean likeNumBean) {
                        LikeNumBean.DataBean data1 = likeNumBean.getData();
                        holder.circle_like.setText("" + data1.getLikeNum());

                        if (data1.getMessage().equals("点赞成功")) {
                            Glide.with(context).load(R.mipmap.icon_like).into(holder.item_dynamic_zan);
                            holder.circle_like.setText("" + data1.getLikeNum());
                        } else {
                            Glide.with(context).load(R.mipmap.icon_2).into(holder.item_dynamic_zan);
                            holder.circle_like.setText("" + data1.getLikeNum());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("点赞数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Glide.with(context).load("http://39.98.173.250:8080" + list.get(position).getProject().getProjectImg()).into(holder.circle_img_1);
        holder.circle_title.setText(list.get(position).getTitle());
        holder.circle_message.setText(list.get(position).getContent());
        holder.circle_time.setText(list.get(position).getCreateDate());
        holder.circle_name.setText(list.get(position).getProject().getProjectName());
        holder.circle_tv_name.setText(list.get(position).getCreateBy().getName());
        holder.circle_tv_phone.setText(list.get(position).getCreateBy().getPhone());

        holder.circle_img_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuPian.tp(position);
            }
        });

//TODO 点赞
        holder.circle_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String LikeNum = "http://39.98.173.250:8080/fangfang/app/v1/commonUpdate/housesDynamicCommentLike?" + "type=1" + "&targetId=" + list.get(position).getId() + "&userId=" + FinalContents.getUserID();
                OkHttpPost okHttpPost = new OkHttpPost(LikeNum);
                String data = okHttpPost.post();

                Gson gson = new Gson();
                LikeNumBean likeNumBean = gson.fromJson(data, LikeNumBean.class);
                LikeNumBean.DataBean data1 = likeNumBean.getData();
                holder.circle_like.setText("" + data1.getLikeNum());

                if (data1.getMessage().equals("点赞成功")) {
                    Glide.with(context).load(R.mipmap.icon_like).into(holder.item_dynamic_zan);
                } else {
                    Glide.with(context).load(R.mipmap.icon_2).into(holder.item_dynamic_zan);
                }

            }
        });

//        TODO 复制
        holder.circle_fuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fuZhi.FuZhi(position);
            }
        });

//        TODO 评论
        holder.circle_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pingLun.pingClick(position);
            }
        });
//        TODO 联系
        holder.circle_lianxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lianxiClick.Click(position);
            }
        });
//        TODO 项目跳转
        holder.dynamic_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (builing == 1) {
//                    Intent intent = new Intent(context, ProjectDetails.class);
//                    context.startActivity(intent);
//                } else {
//                    xiangMu.xm(position);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView circle_img;
        TextView circle_title;
        TextView circle_time;
        ImageView circle_img_rv;
        ImageView circle_img_1;
        ImageView item_dynamic_zan;
        TextView circle_message;
        TextView circle_name;
        TextView circle_like;
        LinearLayout circle_zan;
        TextView circle_pinglun;
        TextView circle_lianxi;
        TextView circle_fuzhi;
        LinearLayout dynamic_ll;
        LinearLayout item_dynamic_ll;
        TextView circle_tv_phone;
        TextView circle_tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            circle_img = (ImageView) itemView.findViewById(R.id.circle_img);
            circle_title = (TextView) itemView.findViewById(R.id.circle_title);
            circle_time = (TextView) itemView.findViewById(R.id.circle_time);
            circle_img_rv = itemView.findViewById(R.id.circle_img_rv);
            circle_img_1 = itemView.findViewById(R.id.circle_img_1);
            circle_message = (TextView) itemView.findViewById(R.id.circle_message);
            circle_name = (TextView) itemView.findViewById(R.id.circle_name);
            circle_like = itemView.findViewById(R.id.circle_like);
            circle_zan = itemView.findViewById(R.id.circle_zan);
            item_dynamic_zan = itemView.findViewById(R.id.item_dynamic_zan);
            circle_pinglun = itemView.findViewById(R.id.circle_pinglun);
            circle_lianxi = itemView.findViewById(R.id.circle_lianxi);
            circle_fuzhi = itemView.findViewById(R.id.circle_fuzhi);
            dynamic_ll = itemView.findViewById(R.id.dynamic_ll);
            circle_tv_name = itemView.findViewById(R.id.circle_tv_name);
            circle_tv_phone = itemView.findViewById(R.id.circle_tv_phone);
            item_dynamic_ll = itemView.findViewById(R.id.item_dynamic_ll);
        }
    }

    //TODO 接口
    public interface LianxiClick {
        void Click(int position);
    }

    public interface PingLun {
        void pingClick(int position);
    }

    public interface FuZhi {
        void FuZhi(int position);
    }

    public interface XiangMu {
        void xm(int position);
    }

    public interface TuPian {
        void tp(int position);
    }
}
