package com.xcy.fzb.all.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.MoreTypeAdapter;
import com.xcy.fzb.all.modle.MoreBean;
import com.xcy.fzb.all.persente.StatusBar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreTypeFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<MoreBean.DataBean> list;
    private List<MoreBean.DataBean.ValueBeanX> array;
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
        array = list.get(1).getValue();
        view  = inflater.inflate(R.layout.fragment_more_type, container, false);
        recyclerView = view.findViewById(R.id.more_type_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MoreTypeAdapter recyclerAdapter = new MoreTypeAdapter(array);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }


}
