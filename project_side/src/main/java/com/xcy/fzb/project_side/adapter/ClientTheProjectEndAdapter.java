package com.xcy.fzb.project_side.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;


public class ClientTheProjectEndAdapter extends RecyclerView.Adapter<ClientTheProjectEndAdapter.ClientTheProjectEndViewHolder> {


    @NonNull
    @Override
    public ClientTheProjectEndViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_refuse_the_project_end, parent, false);

        return new ClientTheProjectEndViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientTheProjectEndViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ClientTheProjectEndViewHolder extends RecyclerView.ViewHolder {

        ImageView item_refuse_the_project_end_img;
        TextView item_refuse_the_project_end_tv1;
        TextView item_refuse_the_project_end_tv2;
        TextView item_refuse_the_project_end_tv3;
        TextView item_refuse_the_project_end_tv4;


        public ClientTheProjectEndViewHolder(@NonNull View itemView) {
            super(itemView);

            item_refuse_the_project_end_img = itemView.findViewById(R.id.item_refuse_the_project_end_img);
            item_refuse_the_project_end_tv1 = itemView.findViewById(R.id.item_refuse_the_project_end_tv1);
            item_refuse_the_project_end_tv2 = itemView.findViewById(R.id.item_refuse_the_project_end_tv2);
            item_refuse_the_project_end_tv3 = itemView.findViewById(R.id.item_refuse_the_project_end_tv3);
            item_refuse_the_project_end_tv4 = itemView.findViewById(R.id.item_refuse_the_project_end_tv4);

        }
    }

}
