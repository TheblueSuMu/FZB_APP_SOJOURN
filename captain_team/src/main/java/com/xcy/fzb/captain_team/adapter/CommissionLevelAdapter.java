package com.xcy.fzb.captain_team.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.database.CommissionLevelSelectBean;

import java.util.List;

public class CommissionLevelAdapter extends RecyclerView.Adapter<CommissionLevelAdapter.CommissionLevelViewHolder> {

    List<CommissionLevelSelectBean.DataBean> data;
    onBtnDelete onBtnDelete;
    private int start;
    private int end;
    private StringBuffer stringBuffer;

    public void setOnBtnDelete(CommissionLevelAdapter.onBtnDelete onBtnDelete) {
        this.onBtnDelete = onBtnDelete;
    }

    public void setList(List<CommissionLevelSelectBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CommissionLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commission_level, parent, false);
        return new CommissionLevelViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommissionLevelViewHolder holder, final int position) {

        holder.textView1.setText(data.get(position).getName());
        holder.textView2.setText(data.get(position).getPercent() + "%");

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = data.get(position).getName();
                stringBuffer = new StringBuffer();
                stringBuffer.append(name);
                start = 0;
                end = 0;

                for (int i = 0; i < stringBuffer.length(); ++i) {
                    if (stringBuffer.substring(i, i + 1).equals("(")) {
                        start = i;
                    } else if (stringBuffer.substring(i, i + 1).equals(")")) {
                        end = i;
                    }
                }
                if(end == 0){
                    onBtnDelete.onDelete(data.get(position).getId());
                }else {
                    if (stringBuffer.substring(start + 1,end).equals("一级")){
                        Toast.makeText(holder.itemView.getContext(),"一级不能删除",Toast.LENGTH_SHORT).show();
                    }else {
                        onBtnDelete.onDelete(data.get(position).getId());
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CommissionLevelViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;
        Button btnDelete;

        public CommissionLevelViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.item_commission_level_tv1);
            textView2 = itemView.findViewById(R.id.item_commission_level_tv2);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

    }

    public interface onBtnDelete {
        void onDelete(String position);
    }

}
