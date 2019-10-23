package com.xcy.fzb.all.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.R;
import com.xcy.fzb.all.modle.MoreBean;

import java.util.List;

public class MoreTypeAdapter extends RecyclerView.Adapter<MoreTypeAdapter.ViewHolder>{
    private List<MoreBean.DataBean.ValueBeanX> list;
    private int more = 0;
    private int[] img = {R.drawable.more_type_1, R.drawable.more_type_2};

    public MoreTypeAdapter(List<MoreBean.DataBean.ValueBeanX> list) {
        this.list = list;
        Log.i("长度","list"+list.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_type_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.nameText.setText(list.get(position).getKey());
        holder.area.setText(list.get(position).getValue().get(0).getValue());
        holder.year.setText(list.get(position).getValue().get(2).getValue());
        holder.price.setText(list.get(position).getValue().get(1).getValue());
        holder.form.setText(list.get(position).getValue().get(3).getValue());
        holder.more_img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (more == 0) {
                    more = 1;
                    holder.more_img_1.setImageResource(img[0]);
                    holder.more_ll_1.setVisibility(View.GONE);
                    holder.more_ll_2.setVisibility(View.GONE);
                    holder.more_ll_3.setVisibility(View.GONE);
                    holder.more_ll_4.setVisibility(View.GONE);
                    holder.more_ll_5.setVisibility(View.GONE);
                    holder.more_ll_6.setVisibility(View.GONE);
                    holder.more_ll_7.setVisibility(View.GONE);
                    holder.more_ll_8.setVisibility(View.GONE);
                } else if (more == 1) {
                    more = 0;
                    holder.more_img_1.setImageResource(img[1]);
                    holder.more_ll_1.setVisibility(View.VISIBLE);
                    holder.more_ll_2.setVisibility(View.VISIBLE);
                    holder.more_ll_3.setVisibility(View.VISIBLE);
                    holder.more_ll_4.setVisibility(View.VISIBLE);
                    holder.more_ll_5.setVisibility(View.VISIBLE);
                    holder.more_ll_6.setVisibility(View.VISIBLE);
                    holder.more_ll_7.setVisibility(View.VISIBLE);
                    holder.more_ll_8.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView area;
        TextView year;
        TextView price;
        TextView form;
        ImageView more_img_1;

        LinearLayout more_ll_1;
        LinearLayout more_ll_2;
        LinearLayout more_ll_3;
        LinearLayout more_ll_4;
        LinearLayout more_ll_5;
        LinearLayout more_ll_6;
        LinearLayout more_ll_7;
        LinearLayout more_ll_8;


        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.more_type_name);
            area = itemView.findViewById(R.id.more_type_area);
            price = itemView.findViewById(R.id.more_type_price);
            year = itemView.findViewById(R.id.more_type_year);
            form = itemView.findViewById(R.id.more_type_form);
            more_img_1 = itemView.findViewById(R.id.more_img_1);

            more_ll_1 = itemView.findViewById(R.id.more_ll_1);
            more_ll_2 = itemView.findViewById(R.id.more_ll_2);
            more_ll_3 = itemView.findViewById(R.id.more_ll_3);
            more_ll_4 = itemView.findViewById(R.id.more_ll_4);
            more_ll_5 = itemView.findViewById(R.id.more_ll_5);
            more_ll_6 = itemView.findViewById(R.id.more_ll_6);
            more_ll_7 = itemView.findViewById(R.id.more_ll_7);
            more_ll_8 = itemView.findViewById(R.id.more_ll_8);
        }
    }
}
