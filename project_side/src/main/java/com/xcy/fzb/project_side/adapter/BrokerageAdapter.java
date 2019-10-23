package com.xcy.fzb.project_side.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.modle.BorkerageDownBean;

import java.util.List;

public class BrokerageAdapter extends RecyclerView.Adapter<BrokerageAdapter.BrokerageViewHolder> {

    List<BorkerageDownBean.DataBean.RowsBean> rows;

    public void setRows(List<BorkerageDownBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public BrokerageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_brokerage_item1, parent, false);

        return new BrokerageViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BrokerageViewHolder holder, int position) {

        holder.brokerage_item_name.setText(rows.get(position).getCustomerName() + "(" + rows.get(position).getCustomerPhone() + ")");
        holder.brokerage_item_roomNumber.setText(rows.get(position).getRoomNumber());
        holder.brokerage_item_projectName.setText(rows.get(position).getProjectName());
        holder.brokerage_item_ziji.setText("自己");
        holder.brokerage_item_tradeDat.setText(rows.get(position).getTradeDate() + "");
        holder.brokerage_item_totalAmount.setText(rows.get(position).getTotalAmount() + "");
        holder.brokerage_item_secondsAmount.setText(rows.get(position).getSecondsAmount() + "");
        holder.brokerage_item_alreadyAmount.setText(rows.get(position).getAlreadyAmount() + "");
        holder.brokerage_item_notAmount.setText(rows.get(position).getNotAmount() + "");

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class BrokerageViewHolder extends RecyclerView.ViewHolder {

        TextView brokerage_item_name;
        TextView brokerage_item_roomNumber;
        TextView brokerage_item_projectName;
        TextView brokerage_item_ziji;
        TextView brokerage_item_tradeDat;
        TextView brokerage_item_totalAmount;
        TextView brokerage_item_secondsAmount;
        TextView brokerage_item_alreadyAmount;
        TextView brokerage_item_notAmount;


        public BrokerageViewHolder(@NonNull View itemView) {
            super(itemView);

            brokerage_item_name = itemView.findViewById(R.id.brokerage_item_name);
            brokerage_item_roomNumber = itemView.findViewById(R.id.brokerage_item_roomNumber);
            brokerage_item_projectName = itemView.findViewById(R.id.brokerage_item_projectName);
            brokerage_item_ziji = itemView.findViewById(R.id.brokerage_item_ziji);
            brokerage_item_tradeDat = itemView.findViewById(R.id.brokerage_item_tradeDat);
            brokerage_item_totalAmount = itemView.findViewById(R.id.brokerage_item_totalAmount);
            brokerage_item_secondsAmount = itemView.findViewById(R.id.brokerage_item_secondsAmount);
            brokerage_item_alreadyAmount = itemView.findViewById(R.id.brokerage_item_alreadyAmount);
            brokerage_item_notAmount = itemView.findViewById(R.id.brokerage_item_notAmount);

        }


    }

}
