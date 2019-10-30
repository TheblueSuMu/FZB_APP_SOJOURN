package com.xcy.fzb.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.SellingPointsBean;
import com.xcy.fzb.all.view.WebActivity;

import java.util.List;

public class SellingPointsAdapter extends RecyclerView.Adapter<SellingPointsAdapter.SellingPointsViewHolder> {

    private List<SellingPointsBean.DataBean.RowsBean> rows;
    private Context context;

    public void setRows(List<SellingPointsBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public SellingPointsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_selling_pointsn_item, parent, false);
        context = parent.getContext();
        return new SellingPointsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final SellingPointsViewHolder holder, final int position) {
        holder.selling_title.setText(rows.get(position).getTitle());
        holder.selling_time.setText(rows.get(position).getCreateDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("webUrl","http://yanshi.fangzuobiao.com:88/sellingPoint?"+"&userId="+ FinalContents.getUserID()+"&talkToolId="+rows.get(position).getId());
                intent.putExtra("title","卖点详情");
                FinalContents.setTalkToolId(rows.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class SellingPointsViewHolder extends RecyclerView.ViewHolder {
        ImageView selling_intent;
        TextView selling_title;
        TextView selling_time;

        public SellingPointsViewHolder(@NonNull View itemView) {
            super(itemView);
            selling_intent = itemView.findViewById(R.id.selling_intent);
            selling_title = itemView.findViewById(R.id.selling_title);
            selling_time = itemView.findViewById(R.id.selling_time);

        }
    }

}
