package com.xcy.fzb.captain_team.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.persente.ContactModel;
import com.xcy.fzb.captain_team.persente.TeamMemberData;

import java.util.ArrayList;
import java.util.List;

public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder> {


    List<ContactModel> contacts = new ArrayList<>();
    List<TeamMemberData> list;

    public void setList(List<TeamMemberData> list) {
        this.list = list;
    }

    private ContactModel contact;
    ItemOnClick itemOnClick;

    public void setContacts(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    @NonNull
    @Override
    public TeamMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_member, parent, false);
        return new TeamMemberViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamMemberViewHolder holder, final int position) {

        Log.i("MyCL", "集合长度：" + contacts.size());
        contact = contacts.get(position);
        Log.e("MyCL", "onBindViewHolder: index:" + contact.getIndex());
        if (position == 0 || !contacts.get(position - 1).getIndex().equals(contact.getIndex())) {
            holder.tvIndex.setVisibility(View.VISIBLE);
            holder.tvIndex.setText(contact.getIndex());
        } else {
            holder.tvIndex.setVisibility(View.GONE);
        }
        holder.tvName.setText(contact.getName());
        Glide.with(holder.itemView.getContext()).load("http://39.98.173.250:8080" + list.get(position).getPhoto()).into(holder.member_name_img);
        if(list.get(position).getType().equals("2")){
            holder.member_name_tv1.setText("销售：" + list.get(position).getIdentity());
        }else if(list.get(position).getType().equals("3")){
            holder.member_name_tv1.setText("顾问：" + list.get(position).getIdentity() + " 人");
        }
        holder.member_name_tv2.setText("最后上线时间" + list.get(position).getLoginDate());
        holder.member_name_tv3.setText(list.get(position).getPhone());
        if (list.get(position).getLoginFlag().equals("0")) {
            holder.member_name_rl.setVisibility(View.VISIBLE);
        } else {
            holder.member_name_rl.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClick.itemClick(contacts.get(position).getName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class TeamMemberViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndex;
        TextView tvName;
        RelativeLayout member_name_rl;
        ImageView member_name_img;
        TextView member_name_tv1;
        TextView member_name_tv2;
        TextView member_name_tv3;

        public TeamMemberViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIndex = itemView.findViewById(R.id.member_index_s);
            tvName = itemView.findViewById(R.id.member_name_s);
            member_name_rl = itemView.findViewById(R.id.member_name_rl);
            member_name_img = itemView.findViewById(R.id.member_name_img);
            member_name_tv1 = itemView.findViewById(R.id.member_name_tv1);
            member_name_tv2 = itemView.findViewById(R.id.member_name_tv2);
            member_name_tv3 = itemView.findViewById(R.id.member_name_tv3);

        }
    }

    public interface ItemOnClick {
        void itemClick(String itemName);
    }
}
