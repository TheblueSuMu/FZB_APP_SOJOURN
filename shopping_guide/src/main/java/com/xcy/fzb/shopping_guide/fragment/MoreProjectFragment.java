package com.xcy.fzb.shopping_guide.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.modle.MoreBean;
import com.xcy.fzb.shopping_guide.persente.StatusBar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreProjectFragment extends Fragment {

    private List<MoreBean.DataBean> list;
    private TextView value1;
    private TextView value2;
    private TextView value3;
    private TextView value4;
    private TextView value5;
    private TextView value6;
    private TextView value7;
    private TextView value8;


    public MoreProjectFragment(List<MoreBean.DataBean> list) {
        this.list = list;
    }

    public MoreProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_more_project, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        value1 = getActivity().findViewById(R.id.value1);
        value2 = getActivity().findViewById(R.id.value2);
        value3 = getActivity().findViewById(R.id.value3);
        value4 = getActivity().findViewById(R.id.value4);
        value5 = getActivity().findViewById(R.id.value5);
        value6 = getActivity().findViewById(R.id.value6);
        value7 = getActivity().findViewById(R.id.value7);
        value8 = getActivity().findViewById(R.id.value8);

        value1.setText(list.get(2).getValue().get(0).getValue().get(0).getValue());
        value2.setText(list.get(2).getValue().get(0).getValue().get(1).getValue());
        value3.setText(list.get(2).getValue().get(0).getValue().get(2).getValue());
        value4.setText(list.get(2).getValue().get(0).getValue().get(3).getValue());
        value5.setText(list.get(2).getValue().get(0).getValue().get(4).getValue());
        value6.setText(list.get(2).getValue().get(0).getValue().get(5).getValue());
        value7.setText(list.get(2).getValue().get(0).getValue().get(6).getValue());
        value8.setText(list.get(2).getValue().get(0).getValue().get(7).getValue());

    }
}
