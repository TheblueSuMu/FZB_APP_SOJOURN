package com.xcy.fzb.shopping_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xcy.fzb.shopping_guide.R;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    LayoutInflater layoutInflater;
    private ImageView mImageView;

    public GridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size() + 1;//注意此处
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.grid_item, null);
        mImageView = (ImageView) convertView.findViewById(R.id.items);
        if (position < list.size()) {
            Glide.with(context).load("http://39.98.173.250:8080" + list.get(position)).into(mImageView);
//            mImageView.setBackgroundResource(list.get(position));
        } else {
            mImageView.setBackgroundResource(R.mipmap.jiatu);//最后一个显示加号图片
        }
        return convertView;
    }
}
