package com.xcy.fzb.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.SellingPointsBean;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    List<SellingPointsBean.DataBean.RowsBean> rows;
    private Context context;
    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    public VideoAdapter(List<SellingPointsBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_video_item, parent, false);
        context = parent.getContext();
        return new VideoAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.jzVideoPlayerStandard.TOOL_BAR_EXIST = false;
        holder.jzVideoPlayerStandard.setUp(rows.get(position).getVideo()
                , JZVideoPlayerStandard.SCROLL_AXIS_HORIZONTAL, rows.get(position).getTitle());
        Glide.with(context.getApplicationContext()).load("http://39.98.173.250:8080"+rows.get(position).getImg())
                .into(holder.jzVideoPlayerStandard.thumbImageView);
        holder.title.setText(rows.get(position).getTitle());
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.showShare(rows.get(position).getTitle(),"http://test.fangzuobiao.com:88/VideoSharing?"+"&userId="+FinalContents.getUserID()+"&talkToolId="+rows.get(position).getId(),rows.get(position).getContent(),"http://39.98.173.250:8080"+rows.get(position).getImg(),"http://test.fangzuobiao.com:88/VideoSharing?"+"&userId="+FinalContents.getUserID()+"&talkToolId="+rows.get(position).getId(),context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        JZVideoPlayerStandard jzVideoPlayerStandard;
        TextView title;
        ImageView share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jzVideoPlayerStandard = itemView.findViewById(R.id.project_video_img);
            title = itemView.findViewById(R.id.project_video_title);
            share = itemView.findViewById(R.id.project_video_share);

        }
    }

}
