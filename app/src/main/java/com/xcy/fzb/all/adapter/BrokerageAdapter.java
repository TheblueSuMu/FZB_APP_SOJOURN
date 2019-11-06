package com.xcy.fzb.all.adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.all.database.BorkerageDownBean;
import com.xcy.fzb.R;

import java.util.List;

public class BrokerageAdapter extends RecyclerView.Adapter<BrokerageAdapter.BrokerageViewHolder> {

    List<BorkerageDownBean.DataBean.RowsBean> rows;

    public void setRows(List<BorkerageDownBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public BrokerageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i("MyCL","测试1:");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_brokerage_item1, parent, false);

        return new BrokerageViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final BrokerageViewHolder holder, final int position) {

        Log.i("MyCL","测试:");

        holder.brokerage_item_name.setText(rows.get(position).getCustomerName() + "(" + rows.get(position).getCustomerPhone() + ")");

        holder.brokerage_item_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rows.get(position).getCustomerPhone().equals("")){

                }else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + rows.get(position).getCustomerPhone()));//跳转到拨号界面，同时传递电话号码
                    holder.itemView.getContext().startActivity(dialIntent);
                }
            }
        });

        holder.brokerage_item_roomNumber.setText(rows.get(position).getRoomNumber());
        holder.brokerage_item_projectName.setText(rows.get(position).getProjectName());
        holder.brokerage_item_ziji.setText("自己");
        holder.brokerage_item_tradeDat.setText(rows.get(position).getTradeDate() + "");

        if (rows.get(position).getTotalAmount().equals("") || rows.get(position).getTotalAmount().equals("0") || rows.get(position).getTotalAmount().equals("0.00")) {
            holder.brokerage_item_totalAmount.setVisibility(View.GONE);
        }else {
            holder.brokerage_item_totalAmount.setVisibility(View.VISIBLE);
            holder.brokerage_item_totalAmount.setText("总佣金：￥" + rows.get(position).getTotalAmount());
        }

        if (rows.get(position).getSecondsAmount().equals("")  || rows.get(position).getSecondsAmount().equals("0") || rows.get(position).getSecondsAmount().equals("0.00")) {
            holder.brokerage_item_secondsAmount.setVisibility(View.VISIBLE);
            holder.brokerage_item_secondsAmount.setText("无秒结");
        } else {
            holder.brokerage_item_secondsAmount.setText("秒结：￥" + rows.get(position).getSecondsAmount() + "");
        }
        if(rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")){
            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
        }else {
            holder.brokerage_item_alreadyAmount.setVisibility(View.VISIBLE);
            holder.brokerage_item_alreadyAmount.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
        }

        if (rows.get(position).getNotAmount().equals("") || rows.get(position).getNotAmount().equals("0") || rows.get(position).getNotAmount().equals("0.00")) {
            holder.brokerage_item_notAmount.setVisibility(View.GONE);
        }else {
            holder.brokerage_item_notAmount.setVisibility(View.VISIBLE);
            holder.brokerage_item_notAmount.setText("未结：￥"+rows.get(position).getNotAmount() + "");
        }


        if (rows.get(position).getStatus().equals("0")) {
            holder.the_project_end_tv6.setVisibility(View.GONE);
            holder.the_project_end_tv7.setVisibility(View.GONE);
        } else if (rows.get(position).getStatus().equals("1")) {
            holder.the_project_end_tv6.setVisibility(View.VISIBLE);
            holder.the_project_end_tv7.setVisibility(View.VISIBLE);
            holder.the_project_end_tv7.setText(rows.get(position).getClosingTime());
        }

        if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00") ) {
            holder.the_project_end_tv8.setVisibility(View.GONE);
        }else {
            holder.the_project_end_tv8.setVisibility(View.VISIBLE);
            holder.the_project_end_tv8.setText("需退还：￥"+rows.get(position).getReturnedMoney());
        }
        Log.i("总佣金","佣金"+rows.get(position).getTotalAmount()+" :  " +position);
        if (rows.get(position).getStatus().equals("0")) {
            holder.the_project_end_tv6.setVisibility(View.GONE);
            holder.the_project_end_tv7.setVisibility(View.GONE);
        } else if (rows.get(position).getStatus().equals("1")) {
            holder.brokerage_item_secondsAmount.setVisibility(View.GONE);
            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
            holder.brokerage_item_notAmount.setVisibility(View.GONE);
            holder.the_project_end_tv6.setVisibility(View.VISIBLE);
            holder.the_project_end_tv7.setVisibility(View.VISIBLE);
            holder.the_project_end_tv7.setText(rows.get(position).getClosingTime());
        }

        if (rows.get(position).getMoneyStatus() == 0) {
            holder.brokerage_item_img.setVisibility(View.GONE);
        }else if (rows.get(position).getMoneyStatus() == 1){
            holder.brokerage_item_img.setVisibility(View.VISIBLE);
            holder.brokerage_item_img.setBackgroundResource(R.mipmap.tdg);
        }else if (rows.get(position).getMoneyStatus() == 2){
            holder.brokerage_item_img.setVisibility(View.VISIBLE);
            holder.brokerage_item_img.setBackgroundResource(R.mipmap.tdr);
            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
            holder.brokerage_item_notAmount.setVisibility(View.GONE);
        }

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
        ImageView brokerage_item_img;
        TextView the_project_end_tv6;
        TextView the_project_end_tv7;
        TextView the_project_end_tv8;

        public BrokerageViewHolder(@NonNull View itemView) {
            super(itemView);
            brokerage_item_img = itemView.findViewById(R.id.brokerage_item_img);
            brokerage_item_name = itemView.findViewById(R.id.brokerage_item_name);
            brokerage_item_roomNumber = itemView.findViewById(R.id.brokerage_item_roomNumber);
            brokerage_item_projectName = itemView.findViewById(R.id.brokerage_item_projectName);
            brokerage_item_ziji = itemView.findViewById(R.id.brokerage_item_ziji);
            brokerage_item_tradeDat = itemView.findViewById(R.id.brokerage_item_tradeDat);
            brokerage_item_totalAmount = itemView.findViewById(R.id.brokerage_item_totalAmount);
            brokerage_item_secondsAmount = itemView.findViewById(R.id.brokerage_item_secondsAmount);
            brokerage_item_alreadyAmount = itemView.findViewById(R.id.brokerage_item_alreadyAmount);
            brokerage_item_notAmount = itemView.findViewById(R.id.brokerage_item_notAmount);
            the_project_end_tv6 = itemView.findViewById(R.id.the_project_end_tv6);
            the_project_end_tv7 = itemView.findViewById(R.id.the_project_end_tv7);
            the_project_end_tv8 = itemView.findViewById(R.id.the_project_end_tv8);
        }


    }

}
