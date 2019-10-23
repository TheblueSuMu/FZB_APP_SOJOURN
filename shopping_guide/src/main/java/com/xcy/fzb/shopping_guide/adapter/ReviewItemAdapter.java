package com.xcy.fzb.shopping_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.modle.MyClientFragmentBean;

import java.util.List;

public class ReviewItemAdapter extends RecyclerView.Adapter<ReviewItemAdapter.ViewHolder>{
    private List<MyClientFragmentBean.DataBean.ListDataBean.JsonDatasBean> list;
    private Context context;

    public ReviewItemAdapter(List<MyClientFragmentBean.DataBean.ListDataBean.JsonDatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_adapter_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_reivew_adapter_item_key.setText(list.get(position).getKey());
        holder.item_reivew_adapter_item_value.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_reivew_adapter_item_key;
        TextView item_reivew_adapter_item_value;
        ImageView item_reivew_adapter_item_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_reivew_adapter_item_key = itemView.findViewById(R.id.item_reivew_adapter_item_key);
            item_reivew_adapter_item_value = itemView.findViewById(R.id.item_reivew_adapter_item_value);
            item_reivew_adapter_item_img = itemView.findViewById(R.id.item_reivew_adapter_item_img);
        }
    }
}
