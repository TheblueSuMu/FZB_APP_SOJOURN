package com.xcy.fzb.shopping_guide.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.modle.MoreBean;
import com.xcy.fzb.shopping_guide.persente.StatusBar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreInformationFragment extends AllFragment {
    private View view;
    private List<MoreBean.DataBean> list;
    private TextView projectName;
    private TextView feature;
    private TextView site;
    private TextView location;
    private TextView developersName;
    private TextView developersBrand;
    private TextView state;
    private RelativeLayout office_location;
    private RelativeLayout project_location;

    private Context context;

    public MoreInformationFragment(List<MoreBean.DataBean> list) {
        this.list = list;
    }

    public MoreInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());

        view = inflater.inflate(R.layout.fragment_more_information, container, false);
        context = container.getContext();
        initfvb();
        return view;
    }

    private void initfvb(){
        projectName = view.findViewById(R.id.more_information_project_name);
        feature = view.findViewById(R.id.more_information_feature);
        site = view.findViewById(R.id.more_information_site);
        location = view.findViewById(R.id.more_information_location);
        developersName = view.findViewById(R.id.more_information_developers_name);
        developersBrand = view.findViewById(R.id.more_information_developers_brand);
        state = view.findViewById(R.id.more_information_state);
        project_location = view.findViewById(R.id.project_location);
        office_location = view.findViewById(R.id.office_location);

        project_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, MapActivity.class);
//                intent.putExtra("office","1");
//                startActivity(intent);
            }
        });

        office_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, MapActivity.class);
//                intent.putExtra("office","0");
//                startActivity(intent);
            }
        });
        initView();
    }

    private void initView(){
        projectName.setText(list.get(0).getValue().get(0).getValue().get(0).getValue());
        feature.setText(list.get(0).getValue().get(0).getValue().get(1).getValue());
        site.setText(list.get(0).getValue().get(0).getValue().get(2).getValue());
        location.setText(list.get(0).getValue().get(0).getValue().get(3).getValue());
        developersName.setText(list.get(0).getValue().get(0).getValue().get(4).getValue());
        developersBrand.setText(list.get(0).getValue().get(0).getValue().get(5).getValue());
        state.setText(list.get(0).getValue().get(0).getValue().get(6).getValue());
    }

}
