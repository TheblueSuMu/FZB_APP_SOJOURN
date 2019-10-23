package com.xcy.fzb.captain_team.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.captain_team.R;

public class SelectBrokerAdapter extends RecyclerView.Adapter<SelectBrokerAdapter.BatchModifyingViewHolder> {


    @NonNull
    @Override
    public BatchModifyingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_batch_modifying, parent, false);
        return new BatchModifyingViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchModifyingViewHolder holder, int position) {

//        Glide.with(holder.itemView.getContext()).load().into(holder.item_batch_modifying_img);
//        holder.item_batch_modifying_tv1.setText();
//        holder.item_batch_modifying_tv2.setText();
//        holder.item_batch_modifying_tv3.setText();

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class BatchModifyingViewHolder extends RecyclerView.ViewHolder {

        ImageView item_batch_modifying_img;

        TextView item_batch_modifying_tv1;
        TextView item_batch_modifying_tv2;
        TextView item_batch_modifying_tv3;

        RadioButton item_batch_modifying_rb;

        public BatchModifyingViewHolder(@NonNull View itemView) {
            super(itemView);

            item_batch_modifying_img = itemView.findViewById(R.id.item_batch_modifying_img);
            item_batch_modifying_tv1 = itemView.findViewById(R.id.item_batch_modifying_tv1);
            item_batch_modifying_tv2 = itemView.findViewById(R.id.item_batch_modifying_tv2);
            item_batch_modifying_tv3 = itemView.findViewById(R.id.item_batch_modifying_tv3);
            item_batch_modifying_rb = itemView.findViewById(R.id.item_batch_modifying_rb);

        }
    }

}
