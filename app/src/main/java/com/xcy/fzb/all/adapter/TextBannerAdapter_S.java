package com.xcy.fzb.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stx.xmarqueeview.XMarqueeView;
import com.stx.xmarqueeview.XMarqueeViewAdapter;
import com.xcy.fzb.R;
import com.xcy.fzb.all.modle.Bean;
import com.xcy.fzb.all.modle.Bean_S;

import java.util.List;

public class TextBannerAdapter_S extends XMarqueeViewAdapter<Bean_S> {
    private Context mContext;
    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    public TextBannerAdapter_S(List<Bean_S> datas, Context context) {
        super(datas);
        mContext = context;
    }

    @Override
    public View onCreateView(XMarqueeView parent) {
        //跑马灯单个显示条目布局，自定义
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_marqueeview, null);
    }

    @Override
    public void onBindView(View parent, View view, final int position) {
        //布局内容填充
        TextView text = (TextView) view.findViewById(R.id.textbanner);
        ImageView image =  view.findViewById(R.id.imagebanner);
        text.setText(mDatas.get(position).getName());
        if(mDatas.get(position).getPic() == R.mipmap.no_information){
            image.setVisibility(View.GONE);
        }else {
            image.setVisibility(View.VISIBLE);
            image.setImageResource(mDatas.get(position).getPic());
        };
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLisenter != null){
                    onItemClickLisenter.onItemClick(position);
                }
            }
        });
    }
}