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
import com.xcy.fzb.R;
import com.xcy.fzb.all.database.TeamMemberBean;
import com.xcy.fzb.all.persente.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class Captain_Team_TeamMemberAdapter extends RecyclerView.Adapter<Captain_Team_TeamMemberAdapter.TeamMemberViewHolder> {


    List<ContactModel> contacts = new ArrayList<>();
    List<TeamMemberBean.DataBean.RowsBean> list;

    public void setList(List<TeamMemberBean.DataBean.RowsBean> list) {
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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.captain_team_item_team_member, parent, false);
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
        for (int i = 0; i < list.size(); ++i) {
            if (contact.getName().equals(list.get(i).getName())) {
                Glide.with(holder.itemView.getContext()).load("http://39.98.173.250:8080" + list.get(i).getPhoto()).into(holder.member_name_img);
                if (list.get(i).getType().equals("1")) {
                    holder.tvName.setText(contact.getName());
                    /**
                     * 修改
                     */
                    if (list.get(i).getCounselorNum().equals("")) {
                        if (list.get(i).getSaleNum().equals("")) {
                            holder.member_name_tv1.setText("销售：0 人  顾问0人");
                        }else {
                            holder.member_name_tv1.setText("销售：" + list.get(i).getSaleNum() + "人  顾问0人");
                        }
                    } else {
                        if (list.get(i).getSaleNum().equals("")) {
                            holder.member_name_tv1.setText("销售：0 人  顾问：" + list.get(i).getCounselorNum() + "人");
                        }else {
                            holder.member_name_tv1.setText("销售：" + list.get(i).getSaleNum() + "人  顾问：" + list.get(i).getCounselorNum() + "人");
                        }
                    }

                } else if (list.get(i).getType().equals("2")) {
                    holder.tvName.setText(contact.getName() + "(" + list.get(i).getRatioName() + ")");
                    if (list.get(i).getCounselorNum().equals("")) {
                        if (list.get(i).getLeaderName().equals("")) {
                            holder.member_name_tv1.setText("团队长：暂无  顾问：0人");
                        }else {
                            holder.member_name_tv1.setText("团队长：" + list.get(i).getLeaderName() + "  顾问：0人");
                        }
                    } else {
                        if (list.get(i).getLeaderName().equals("")) {
                            holder.member_name_tv1.setText("团队长：暂无  顾问：" + list.get(i).getCounselorNum() + "人");
                        }else {
                            holder.member_name_tv1.setText("团队长：" + list.get(i).getLeaderName() + "  顾问：" + list.get(i).getCounselorNum() + "人");
                        }
                    }

                } else if (list.get(i).getType().equals("3")) {
                    holder.tvName.setText(contact.getName() + "(" + list.get(i).getRatioName() + ")");
                    if (list.get(i).getSaleName().equals("")) {
                        if (list.get(i).getLeaderName().equals("")) {
                            holder.member_name_tv1.setText("团队长：暂无  暂无销售");
                        }else {
                            holder.member_name_tv1.setText("团队长：" + list.get(i).getLeaderName() + "  暂无销售");
                        }
                    } else {
                        if (list.get(i).getLeaderName().equals("")) {
                            holder.member_name_tv1.setText("团队长：暂无  销售：" + list.get(i).getSaleName());
                        }else {
                            holder.member_name_tv1.setText("团队长：" + list.get(i).getLeaderName() + "  销售：" + list.get(i).getSaleName());
                        }
                    }
                }

                if (list.get(i).getLoginDate().equals("")) {
                    holder.member_name_tv2.setVisibility(View.GONE);
                } else {
                    holder.member_name_tv2.setText("最后上线时间" + list.get(i).getLoginDate());
                }
                holder.member_name_tv3.setText(list.get(i).getPhone());
                if (list.get(i).getLoginFlag().equals("0")) {
                    holder.member_name_rl.setVisibility(View.VISIBLE);
                } else {
                    holder.member_name_rl.setVisibility(View.GONE);
                }
            }
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
