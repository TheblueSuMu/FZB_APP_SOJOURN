package com.xcy.fzb.captain_team.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.database.ImgData;

import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder>{
    private Context context;
    private List<ImgData.DataBean> beanList;

    public IssueAdapter(List<ImgData.DataBean> beanList) {
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oversea_city_item,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load("http://39.98.173.250:8080"+beanList.get(position).getCoverImg()).into(holder.imageAvatar);
        holder.nameText.setText(beanList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, WebViewActivity.class);
//                intent.putExtra("webview",beanList.get(position).getContent());
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAvatar;
        TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);
            imageAvatar = (ImageView)itemView.findViewById(R.id.oversea_image);
            nameText =(TextView) itemView.findViewById(R.id.oversea_name);

        }
    }

}
