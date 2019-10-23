package com.xcy.fzb.shopping_guide.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.JourneyBean;
import com.xcy.fzb.shopping_guide.modle.TaskDetailsBean;
import com.xcy.fzb.shopping_guide.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.ViewHolder>{

    private List<TaskDetailsBean.DataBean.RouteInfoBean> listData;
    private Context context;

    public JourneyAdapter(List<TaskDetailsBean.DataBean.RouteInfoBean> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journey_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.item_journey_tv3.setVisibility(View.GONE);
        holder.item_journey_tv4.setVisibility(View.GONE);

        if (listData.get(position).getProject().getProjectName().equals("")) {
            holder.item_journey_tv3.setVisibility(View.GONE);
        }else {
            holder.item_journey_tv3.setVisibility(View.VISIBLE);
            holder.item_journey_tv3.setText(listData.get(position).getProject().getProjectName());
        }

        if (listData.get(position).getPlanningType().equals("1")) {
            holder.item_journey_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("2")) {
            holder.item_journey_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("3")) {
            holder.item_journey_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("4")) {
            holder.item_journey_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("5")) {
            holder.item_journey_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("6")) {
            holder.item_journey_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("7")) {
            holder.item_journey_tv2.setText(listData.get(position).getPlanningName());
            holder.item_journey_tv3.setVisibility(View.VISIBLE);
            holder.item_journey_tv3.setText(listData.get(position).getRemarks());
            holder.item_journey_tv4.setVisibility(View.VISIBLE);
        }

        holder.item_journey_tv1.setText(listData.get(position).getPlanningTime());
        if (listData.get(position).getIsComplete() == 1) {
            holder.item_journey_tv4.setVisibility(View.VISIBLE);
            holder.item_journey_tv4.setText("完成时间："+listData.get(position).getCompleteTime());
            holder.item_journey_fulfill.setText("已完成");
            holder.item_journey_fulfill.setTextColor(R.color.textcolor);
        } else if (listData.get(position).getIsComplete() == 0) {
            holder.item_journey_fulfill.setText("完成");
            holder.item_journey_fulfill.setTextColor(R.color.textcolorcheck);
        }

        holder.item_journey_fulfill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listData.get(position).getIsComplete() == 0) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);
                    Observable<JourneyBean> userMessage = fzbInterface.getCompleteTask(FinalContents.getUserID(),listData.get(position).getId());
                    userMessage.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<JourneyBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @SuppressLint("WrongConstant")
                                @Override
                                public void onNext(JourneyBean journeyBean) {
                                    Toast.makeText(context, ""+journeyBean.getData().getMsg(), Toast.LENGTH_SHORT).show();
                                    holder.item_journey_fulfill.setText("已完成");
                                    holder.item_journey_fulfill.setTextColor(R.color.textcolor);
                                    holder.item_journey_tv4.setVisibility(View.VISIBLE);
                                    holder.item_journey_tv4.setText("完成时间："+listData.get(position).getCompleteTime());
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("列表数据获取错误","错误"+e);
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                } else if (listData.get(position).getIsComplete() == 1) {
                    Toast.makeText(context, "已完成", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_journey_tv1;
        TextView item_journey_fulfill;
        TextView item_journey_tv2;
        TextView item_journey_tv3;
        TextView item_journey_tv4;

        LinearLayout item_journey_lls;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_journey_tv1 = itemView.findViewById(R.id.item_journey_tv1);
            item_journey_fulfill = itemView.findViewById(R.id.item_journey_fulfill);
            item_journey_tv2 = itemView.findViewById(R.id.item_journey_tv2);
            item_journey_tv3 = itemView.findViewById(R.id.item_journey_tv3);
            item_journey_tv4 = itemView.findViewById(R.id.item_journey_tv4);
            item_journey_lls = itemView.findViewById(R.id.item_journey_lls);
        }
    }

}
