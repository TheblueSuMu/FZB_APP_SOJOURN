package com.xcy.fzb.shopping_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.modle.BuildingBean;

import java.util.List;

public class BuildingInformationAdapter extends RecyclerView.Adapter<BuildingInformationAdapter.ViewHolder>{


    private List<BuildingBean.DataBean.HouseInfoListBean> list;
    private Context context;

    public BuildingInformationAdapter(List<BuildingBean.DataBean.HouseInfoListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.building_item,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load("http://39.98.173.250:8080"+list.get(position).getFloorPlan()).into(holder.imageAvatar);
        holder.room.setText(list.get(position).getModel());
        holder.area.setText("面积约："+list.get(position).getArea());
        holder.orientation.setText("朝向："+list.get(position).getOrientation());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAvatar;
        TextView room;
        TextView area;
        TextView orientation;

        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            imageAvatar = (ImageView)itemView.findViewById(R.id.building_item_pic);
            room =(TextView) itemView.findViewById(R.id.building_item_room);
            area =(TextView) itemView.findViewById(R.id.building_item_area);
            orientation =(TextView) itemView.findViewById(R.id.building_item_orientation);
        }
    }
}
