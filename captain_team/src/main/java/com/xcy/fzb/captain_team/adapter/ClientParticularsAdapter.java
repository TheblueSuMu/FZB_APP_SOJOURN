package com.xcy.fzb.captain_team.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.database.ClientParticularsBean;

import java.util.List;

public class ClientParticularsAdapter extends RecyclerView.Adapter<ClientParticularsAdapter.ClientParticularsViewHolder> {

    List<ClientParticularsBean.DataBean.ListDataBean> listData;

    public void setListData(List<ClientParticularsBean.DataBean.ListDataBean> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ClientParticularsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client_particulars, parent, false);
        return new ClientParticularsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientParticularsViewHolder holder, int position) {
        int num = 0;
        String bhq = "";
        int a = 0;
        holder.item_client_title.setText(listData.get(position).getProjectName());
        List<ClientParticularsBean.DataBean.ListDataBean.AttacheListBean> attacheList = listData.get(position).getAttacheList();
        for (int i = 0; i < attacheList.size(); ++i) {
            if (num == 0) {
                holder.item_client_name1.setText(attacheList.get(i).getName());
                num++;
            } else if (num == 1) {
                holder.item_client_name2.setText(attacheList.get(i).getName());
                num++;
            } else if (num == 2) {
                holder.item_client_name3.setText(attacheList.get(i).getName());
                num++;
            }
        }
        List<ClientParticularsBean.DataBean.ListDataBean.ListMapBean> listMap = listData.get(position).getListMap();
        for (int j = 0; j < listMap.size(); ++j) {
            String value = listMap.get(j).getValue();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(value);
            if (stringBuffer.substring(0, 1).equals("2")) {
                a = 1;
            } else {
                bhq = value;
            }
        }

        if (listData.get(position).getStatusName().equals("失效") || a == 1) {
            holder.item_client_tv1.setText(listData.get(position).getStatusName());
            holder.item_client_tv1.setTextColor(Color.parseColor(listData.get(position).getStatusColor()));
            holder.item_client_tv2.setVisibility(View.GONE);
        } else {
            holder.item_client_tv1.setText(listData.get(position).getStatusName());
            holder.item_client_tv1.setTextColor(Color.parseColor(listData.get(position).getStatusColor()));
            holder.item_client_tv2.setText("保护期" + bhq);
            holder.item_client_tv2.setTextColor(Color.parseColor("#C3555B"));
        }

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ClientParticularsViewHolder extends RecyclerView.ViewHolder {

        TextView item_client_title;
        TextView item_client_name1;
        TextView item_client_name2;
        TextView item_client_name3;
        TextView item_client_tv1;
        TextView item_client_tv2;

        public ClientParticularsViewHolder(@NonNull View itemView) {
            super(itemView);

            item_client_title = itemView.findViewById(R.id.item_client_title);
            item_client_name1 = itemView.findViewById(R.id.item_client_name1);
            item_client_name2 = itemView.findViewById(R.id.item_client_name2);
            item_client_name3 = itemView.findViewById(R.id.item_client_name3);
            item_client_tv1 = itemView.findViewById(R.id.item_client_tv1);
            item_client_tv2 = itemView.findViewById(R.id.item_client_tv2);

        }
    }


}
