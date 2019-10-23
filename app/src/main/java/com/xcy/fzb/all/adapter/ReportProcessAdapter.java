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

import com.bumptech.glide.Glide;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.ReportProcessBean;
import com.xcy.fzb.all.view.ReviewTheSuccessActivity;

import java.util.List;

public class ReportProcessAdapter extends RecyclerView.Adapter<ReportProcessAdapter.ClientFragmentViewHolder> {

    List<ReportProcessBean.DataBean.RowsBean> rows;
    private Context context;


    public void setRows(List<ReportProcessBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public ClientFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        context = parent.getContext();
        return new ClientFragmentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientFragmentViewHolder holder, final int position) {

        Glide.with(holder.itemView.getContext()).load("http://39.98.173.250:8080" + rows.get(position).getCustomerImg()).into(holder.client_item_img);
        FinalContents.setPreparationId(rows.get(position).getPreparationId());
        holder.client_item_name.setText(rows.get(position).getCustomerName());
        holder.client_item_photo.setText("(" + rows.get(position).getCustomerPhone() + ")");
        holder.client_item_time.setText(rows.get(position).getDate());
        holder.client_item_project_name.setText(rows.get(position).getProjectName());
        holder.client_item_title.setText(rows.get(position).getRelatedData());

        holder.client_item_project_add.setText(rows.get(position).getAgentName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReviewTheSuccessActivity.class);
                FinalContents.setPreparationId(rows.get(position).getPreparationId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class ClientFragmentViewHolder extends RecyclerView.ViewHolder {

        ImageView client_item_img;
        TextView client_item_name;
        TextView client_item_photo;
        TextView client_item_project_name;
        TextView client_item_time;
        TextView client_item_cg;
        TextView client_item_title;
        TextView client_item_project_add;

        public ClientFragmentViewHolder(@NonNull View itemView) {
            super(itemView);

            client_item_img = itemView.findViewById(R.id.client_item_img);
            client_item_name = itemView.findViewById(R.id.client_item_name);
            client_item_photo = itemView.findViewById(R.id.client_item_photo);
            client_item_project_name = itemView.findViewById(R.id.client_item_project_name);
            client_item_time = itemView.findViewById(R.id.client_item_time);
            client_item_cg = itemView.findViewById(R.id.client_item_cg);
            client_item_title = itemView.findViewById(R.id.client_item_title);
            client_item_project_add = itemView.findViewById(R.id.client_item_project_add);

        }
    }

}
