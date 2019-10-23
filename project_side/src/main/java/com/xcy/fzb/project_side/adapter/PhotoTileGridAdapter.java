package com.xcy.fzb.project_side.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.modle.PhotoBean;
import com.xcy.fzb.project_side.view.BannerPhotoActivity;

import java.util.List;

public class PhotoTileGridAdapter extends RecyclerView.Adapter<PhotoTileGridAdapter.ViewHolder>{

    private List<PhotoBean.DataBean.DataListBean> list;
    private Context context;

    public PhotoTileGridAdapter(List<PhotoBean.DataBean.DataListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_tile_item_grid,
                viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        context = viewGroup.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load("http://39.98.173.250:8080" + list.get(i).getImgPath()).into(viewHolder.imageAvatar);
        viewHolder.imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BannerPhotoActivity.class);
                intent.putExtra("index",i);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            imageAvatar = (ImageView)itemView.findViewById(R.id.photo_tile_item_grid_pic);
        }
    }
}
