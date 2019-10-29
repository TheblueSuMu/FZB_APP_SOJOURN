package com.xcy.fzb.captain_team.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.CommissionListBean;

import java.util.List;

public class Captain_Team_TheProjectEndCommissionAdapter extends RecyclerView.Adapter<Captain_Team_TheProjectEndCommissionAdapter.TheProjectEndCommissionViewHolder> {

    private List<CommissionListBean.DataBean.RowsBean> rowsBeanList;
    private Context context;
    private String substring;

    public Captain_Team_TheProjectEndCommissionAdapter(List<CommissionListBean.DataBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
    }

    @NonNull
    @Override
    public TheProjectEndCommissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.captain_team_item_commission_the_project_end, parent, false);
        context = parent.getContext();
        return new TheProjectEndCommissionViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final TheProjectEndCommissionViewHolder holder, final int position) {

        if (FinalContents.getIdentity().equals("63")) {

            holder.the_project_end_title.setText(rowsBeanList.get(position).getCustomerName() + "[" + rowsBeanList.get(position).getCustomerPhone() + "]");
            holder.the_project_end_name.setText(rowsBeanList.get(position).getProjectName());
            holder.the_project_end_time.setText(rowsBeanList.get(position).getRoomNumber());
            holder.the_project_end_bargain_time.setText(rowsBeanList.get(position).getAgentName() + "(" + rowsBeanList.get(position).getAgentPhone() + ")");

            holder.the_project_end_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rowsBeanList.get(position).getCustomerPhone().equals("")){

                    }else {
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + rowsBeanList.get(position).getCustomerPhone()));//跳转到拨号界面，同时传递电话号码
                        holder.itemView.getContext().startActivity(dialIntent);
                    }
                }
            });
            holder.the_project_end_bargain_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rowsBeanList.get(position).getAgentPhone().equals("")){

                    }else {
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + rowsBeanList.get(position).getAgentPhone()));//跳转到拨号界面，同时传递电话号码
                        holder.itemView.getContext().startActivity(dialIntent);
                    }
                }
            });

            if (rowsBeanList.get(position).getTradeDate().equals("")) {
                holder.the_project_end_company.setVisibility(View.GONE);
            } else {
                holder.the_project_end_company.setText("结清时间" + rowsBeanList.get(position).getTradeDate());
            }

            holder.the_project_end_tv1.setVisibility(View.VISIBLE);
            holder.the_project_end_tv3.setVisibility(View.VISIBLE);
            holder.the_project_end_tv4.setVisibility(View.VISIBLE);
            holder.the_project_end_tv2.setVisibility(View.GONE);
            holder.the_project_end_tv5.setVisibility(View.GONE);
            holder.the_project_end_tv6.setVisibility(View.GONE);
            holder.the_project_end_tv7.setVisibility(View.GONE);

            holder.the_project_end_tv1.setTextColor(Color.parseColor("#999999"));
            holder.the_project_end_tv3.setTextColor(Color.parseColor("#999999"));
            holder.the_project_end_tv4.setTextColor(Color.parseColor("#C25157"));

            holder.the_project_end_tv1.setText("佣金：￥" + rowsBeanList.get(position).getTotalAmount());
            if (rowsBeanList.get(position).getAlreadyAmount().equals("")) {
                holder.the_project_end_tv3.setVisibility(View.GONE);
            } else {
                holder.the_project_end_tv3.setVisibility(View.VISIBLE);
                holder.the_project_end_tv3.setText("已结：￥" + rowsBeanList.get(position).getAlreadyAmount());
            }
            holder.the_project_end_tv4.setText("未结：￥" + rowsBeanList.get(position).getNotAmount());

            if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0")) {
                holder.the_project_end_tv5.setVisibility(View.GONE);
            } else {
                holder.the_project_end_tv5.setVisibility(View.VISIBLE);
                holder.the_project_end_tv5.setText("需退还：￥" + rowsBeanList.get(position).getReturnedMoney());
            }

            if (rowsBeanList.get(position).getMoneyStatus() == 0) {
                holder.the_project_end_img.setVisibility(View.GONE);
            } else if (rowsBeanList.get(position).getMoneyStatus() == 1) {
                holder.the_project_end_img.setVisibility(View.VISIBLE);
                holder.the_project_end_img.setBackgroundResource(R.mipmap.tdg);
            } else if (rowsBeanList.get(position).getMoneyStatus() == 2) {
                holder.the_project_end_img.setVisibility(View.VISIBLE);
                holder.the_project_end_img.setBackgroundResource(R.mipmap.tdr);
                holder.the_project_end_tv1.setVisibility(View.GONE);
                holder.the_project_end_tv2.setVisibility(View.GONE);
                holder.the_project_end_tv3.setVisibility(View.GONE);
                holder.the_project_end_tv4.setVisibility(View.GONE);
            }

        } else {

            holder.the_project_end_title.setText(rowsBeanList.get(position).getProjectName());
            holder.the_project_end_name.setText(rowsBeanList.get(position).getCustomerName() + "  (" + rowsBeanList.get(position).getCustomerPhone() + ")");
            holder.the_project_end_time.setText(rowsBeanList.get(position).getRoomNumber());
            holder.the_project_end_bargain_time.setText("成交时间：" + rowsBeanList.get(position).getTradeDate());

            if (FinalContents.getIdentity().equals("60")) {
                if (rowsBeanList.get(position).getIsMy().equals("1")) {
                    holder.the_project_end_company.setText("自己");
                } else {
                    holder.the_project_end_company.setText(rowsBeanList.get(position).getAgentName());
                    if (rowsBeanList.get(position).getCommission().equals("") || rowsBeanList.get(position).getCommission().equals("0")) {
                        holder.the_project_end_tv2.setVisibility(View.GONE);
                    } else {
                        holder.the_project_end_tv2.setVisibility(View.VISIBLE);
                        holder.the_project_end_tv2.setText("应付：￥ " + rowsBeanList.get(position).getCommission());
                    }
                }

                holder.the_project_end_tv1.setVisibility(View.VISIBLE);
                holder.the_project_end_tv1.setText("佣金：￥ " + rowsBeanList.get(position).getTotalAmount());

                if (rowsBeanList.get(position).getAlreadyAmount().equals("") || rowsBeanList.get(position).getAlreadyAmount().equals("0") || rowsBeanList.get(position).getAlreadyAmount().equals("0.00")) {
                    holder.the_project_end_tv3.setVisibility(View.GONE);
                } else {
                    holder.the_project_end_tv3.setVisibility(View.VISIBLE);
                    holder.the_project_end_tv3.setText("已结：￥ " + rowsBeanList.get(position).getAlreadyAmount());
                }


                if (rowsBeanList.get(position).getNotAmount().equals("")) {
                    holder.the_project_end_tv4.setVisibility(View.GONE);

                } else {
                    holder.the_project_end_tv4.setVisibility(View.VISIBLE);
                    if (FinalContents.getIdentity().equals("61") || FinalContents.getIdentity().equals("62")) {
                        holder.the_project_end_tv4.setText("未结：￥ " + rowsBeanList.get(position).getTotalAmount());
                    } else {
                        holder.the_project_end_tv4.setText("未结：￥ " + rowsBeanList.get(position).getNotAmount());
                    }
                }


                if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0") || rowsBeanList.get(position).getReturnedMoney().equals("0.00")) {
                    holder.the_project_end_tv5.setVisibility(View.GONE);
                } else {
                    holder.the_project_end_tv5.setVisibility(View.VISIBLE);
                    holder.the_project_end_tv5.setText("需退还：￥" + rowsBeanList.get(position).getReturnedMoney());
                }

                if (rowsBeanList.get(position).getStatus().equals("0")) {
                    holder.the_project_end_tv6.setVisibility(View.GONE);
                    holder.the_project_end_tv7.setVisibility(View.GONE);
                } else if (rowsBeanList.get(position).getStatus().equals("1")) {
                    holder.the_project_end_tv2.setVisibility(View.GONE);
                    holder.the_project_end_tv3.setVisibility(View.GONE);
                    holder.the_project_end_tv4.setVisibility(View.GONE);
                    holder.the_project_end_tv5.setVisibility(View.GONE);
                    holder.the_project_end_tv6.setVisibility(View.VISIBLE);
                    holder.the_project_end_tv7.setVisibility(View.VISIBLE);
                    holder.the_project_end_tv7.setText(rowsBeanList.get(position).getClosingTime());
                }

                if (rowsBeanList.get(position).getMoneyStatus() == 0) {
                    holder.the_project_end_img.setVisibility(View.GONE);
                } else if (rowsBeanList.get(position).getMoneyStatus() == 1) {
                    holder.the_project_end_img.setVisibility(View.VISIBLE);
                    holder.the_project_end_img.setBackgroundResource(R.mipmap.tdg);
                } else if (rowsBeanList.get(position).getMoneyStatus() == 2) {
                    holder.the_project_end_img.setVisibility(View.VISIBLE);
                    holder.the_project_end_img.setBackgroundResource(R.mipmap.tdr);
                    holder.the_project_end_tv1.setVisibility(View.GONE);
                    holder.the_project_end_tv2.setVisibility(View.GONE);
                    holder.the_project_end_tv3.setVisibility(View.GONE);
                    holder.the_project_end_tv4.setVisibility(View.GONE);

                }

            } else if (FinalContents.getIdentity().equals("61")) {
                if (rowsBeanList.get(position).getIsMy().equals("1")) {
                    holder.the_project_end_company.setText("自己");
                } else {
                    holder.the_project_end_company.setText(rowsBeanList.get(position).getAgentName());
                    if (rowsBeanList.get(position).getCommission().equals("") || rowsBeanList.get(position).getCommission().equals("0")) {
                        holder.the_project_end_tv2.setVisibility(View.GONE);
                    } else {
                        holder.the_project_end_tv2.setVisibility(View.VISIBLE);
                        holder.the_project_end_tv2.setText("应付：￥ " + rowsBeanList.get(position).getCommission());
                    }
                }

                holder.the_project_end_tv1.setVisibility(View.VISIBLE);
                holder.the_project_end_tv1.setText("佣金：￥ " + rowsBeanList.get(position).getTotalAmount());

                holder.the_project_end_tv3.setVisibility(View.GONE);
                holder.the_project_end_tv4.setVisibility(View.GONE);





                if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0")) {
                    holder.the_project_end_tv5.setVisibility(View.GONE);
                } else {
                    holder.the_project_end_tv5.setVisibility(View.VISIBLE);
                    holder.the_project_end_tv5.setText("需退还：￥" + rowsBeanList.get(position).getReturnedMoney());
                }
                if (rowsBeanList.get(position).getStatus().equals("0")) {
                    holder.the_project_end_tv6.setVisibility(View.GONE);
                    holder.the_project_end_tv7.setVisibility(View.GONE);
                } else if (rowsBeanList.get(position).getStatus().equals("1")) {
                    holder.the_project_end_tv2.setVisibility(View.GONE);
                    holder.the_project_end_tv3.setVisibility(View.GONE);
                    holder.the_project_end_tv4.setVisibility(View.GONE);
                    holder.the_project_end_tv5.setVisibility(View.GONE);
                    holder.the_project_end_tv6.setVisibility(View.VISIBLE);
                    holder.the_project_end_tv7.setVisibility(View.VISIBLE);
                    holder.the_project_end_tv7.setText(rowsBeanList.get(position).getClosingTime());
                }
                if (rowsBeanList.get(position).getMoneyStatus() == 0) {
                    holder.the_project_end_img.setVisibility(View.GONE);
                } else if (rowsBeanList.get(position).getMoneyStatus() == 1) {
                    holder.the_project_end_img.setVisibility(View.VISIBLE);
                    holder.the_project_end_img.setBackgroundResource(R.mipmap.tdg);
                } else if (rowsBeanList.get(position).getMoneyStatus() == 2) {
                    holder.the_project_end_img.setVisibility(View.VISIBLE);
                    holder.the_project_end_img.setBackgroundResource(R.mipmap.tdr);
                    holder.the_project_end_tv1.setVisibility(View.GONE);
                    holder.the_project_end_tv2.setVisibility(View.GONE);
                    holder.the_project_end_tv3.setVisibility(View.GONE);
                    holder.the_project_end_tv4.setVisibility(View.GONE);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return rowsBeanList.size();
    }

    class TheProjectEndCommissionViewHolder extends RecyclerView.ViewHolder {

        ImageView the_project_end_img;

        TextView the_project_end_title;
        TextView the_project_end_name;
        TextView the_project_end_time;
        TextView the_project_end_bargain_time;
        TextView the_project_end_company;
        TextView the_project_end_tv1;
        TextView the_project_end_tv2;
        TextView the_project_end_tv3;
        TextView the_project_end_tv4;
        TextView the_project_end_tv5;
        TextView the_project_end_tv6;
        TextView the_project_end_tv7;

        public TheProjectEndCommissionViewHolder(@NonNull View itemView) {
            super(itemView);

            the_project_end_img = itemView.findViewById(R.id.the_project_end_img);
            the_project_end_title = itemView.findViewById(R.id.the_project_end_title);
            the_project_end_name = itemView.findViewById(R.id.the_project_end_name);
            the_project_end_time = itemView.findViewById(R.id.the_project_end_time);
            the_project_end_bargain_time = itemView.findViewById(R.id.the_project_end_bargain_time);
            the_project_end_company = itemView.findViewById(R.id.the_project_end_company);
            the_project_end_tv1 = itemView.findViewById(R.id.the_project_end_tv1);
            the_project_end_tv2 = itemView.findViewById(R.id.the_project_end_tv2);
            the_project_end_tv3 = itemView.findViewById(R.id.the_project_end_tv3);
            the_project_end_tv4 = itemView.findViewById(R.id.the_project_end_tv4);
            the_project_end_tv5 = itemView.findViewById(R.id.the_project_end_tv5);
            the_project_end_tv6 = itemView.findViewById(R.id.the_project_end_tv6);
            the_project_end_tv7 = itemView.findViewById(R.id.the_project_end_tv7);

        }
    }

}
