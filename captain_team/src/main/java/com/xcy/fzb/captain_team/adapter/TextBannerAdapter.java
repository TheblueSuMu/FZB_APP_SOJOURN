package com.xcy.fzb.captain_team.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stx.xmarqueeview.XMarqueeView;
import com.stx.xmarqueeview.XMarqueeViewAdapter;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.database.Bean;

import java.util.List;

public class TextBannerAdapter extends XMarqueeViewAdapter<Bean> {
    private Context mContext;

    public TextBannerAdapter(List<Bean> datas, Context context) {
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
        image.setImageResource(mDatas.get(position).getPic());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
