package com.xcy.fzb.project_side.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.project_side.R;
import com.xcy.fzb.project_side.adapter.MoreTypeAdapter;
import com.xcy.fzb.project_side.modle.MoreBean;
import com.xcy.fzb.project_side.presente.StatusBar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreTypeFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<MoreBean.DataBean> list;
    private View view;

    public MoreTypeFragment(List<MoreBean.DataBean> list) {
        this.list = list;
    }

    public MoreTypeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        view  = inflater.inflate(R.layout.fragment_more_type, container, false);
        recyclerView = view.findViewById(R.id.more_type_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MoreTypeAdapter recyclerAdapter = new MoreTypeAdapter(list);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }


}
