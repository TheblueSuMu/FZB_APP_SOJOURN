package com.xcy.fzb.all.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.R;
import com.xcy.fzb.all.modle.PhoneDto;
import com.xcy.fzb.all.persente.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder>{
    private List<PhoneDto> list;
    private Context context;
    private View view;
    List<ContactModel> contacts = new ArrayList<>();
    private ContactModel contact;
    private String all = "";
    ItemOnClick itemOnClick;

    public interface ItemOnClick {
        void itemClick(int position);
    }

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public void setAll(String all) {
        this.all = all;
        notifyDataSetChanged();
    }

    public void setContacts(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

    public List<PhoneDto> getList() {
        return list;
    }

    public void setList(List<PhoneDto> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.i("MyCL", "集合长度：" + contacts.size());
        contact = contacts.get(position);
        Log.e("MyCL", "onBindViewHolder: index:" + contact.getIndex());
        if (position == 0 || !contacts.get(position - 1).getIndex().equals(contact.getIndex())) {
            holder.phone_CheckBox_index.setVisibility(View.VISIBLE);
            holder.phone_CheckBox_index.setText(contact.getIndex());
        } else {
            holder.phone_CheckBox_index.setVisibility(View.GONE);
        }

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append = stringBuffer.append(contact.getName());
        for (int i = 0; i < append.length(); ++i) {
            if (append.substring(i, i + 1).equals("@")) {
                holder.phone_CheckBox.setText(append.substring(0, i));
                holder.phone_number.setText(append.substring(i+1,append.length()));
            }
        }



        holder.phone_CheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.phone_CheckBox.isChecked()) {
                    list.get(position).setStatus(true);
                }else {
                    list.get(position).setStatus(false);
                }
                itemOnClick.itemClick(position);
            }
        });

        if (all.equals("1")) {
            holder.phone_CheckBox.setChecked(true);
            list.get(position).setStatus(true);
        } else if (all.equals("2")) {
            holder.phone_CheckBox.setChecked(false);
            list.get(position).setStatus(false);
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox phone_CheckBox;
        TextView phone_CheckBox_index;
        TextView phone_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phone_CheckBox = itemView.findViewById(R.id.phone_CheckBox);
            phone_CheckBox_index = itemView.findViewById(R.id.phone_CheckBox_index);
            phone_number = itemView.findViewById(R.id.phone_number);
        }

    }
}
