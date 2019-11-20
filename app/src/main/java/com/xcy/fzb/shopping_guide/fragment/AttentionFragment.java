package com.xcy.fzb.shopping_guide.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xcy.fzb.R;
import com.xcy.fzb.all.fragment.AllFragment;
import com.xcy.fzb.all.modle.TaskDetailsBean;

public class AttentionFragment extends AllFragment {

    private View view;
    private TextView attention_content;
    private TaskDetailsBean taskDetailsBean;

    public AttentionFragment(TaskDetailsBean taskDetailsBean) {
        this.taskDetailsBean = taskDetailsBean;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_guide_fragment_attention, null);
        attention_content = view.findViewById(R.id.attention_content);
        initData();
        return view;
    }

    private void initData(){
        attention_content.setText(Html.fromHtml(taskDetailsBean.getData().getRouteTimeInfo().getMatter().getMatterComment()));
    }

}