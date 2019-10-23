package com.xcy.fzb.project_side.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;

public class ScheduleTheProjectEndAdapter extends RecyclerView.Adapter<ScheduleTheProjectEndAdapter.ScheduleTheProjectEndViewHolder> {



    @NonNull
    @Override
    public ScheduleTheProjectEndViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);

        return new ScheduleTheProjectEndViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleTheProjectEndViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ScheduleTheProjectEndViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ScheduleTheProjectEndViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }

}
