package com.xcy.fzb.captain_team.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.CountryAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.CountryBean;
import com.xcy.fzb.captain_team.persente.OkHttpPost;
import com.xcy.fzb.captain_team.persente.StatusBar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryFragment extends Fragment {
    private String projectType;
    private String countryUrl = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectCityNationlist?"+"&userId="+ FinalContents.getUserID()+"&city="+FinalContents.getCityID();
    private String url = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectList?"+ "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID();
    private String eventUrl ="";

    private Context context;
    public CountryFragment(String projectType) {
        this.projectType = projectType;
    }

    private List<CountryBean.DataBean> list = new ArrayList<>();

    private RecyclerView recyclerView;
    private Button ensure;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country, container, false);
        StatusBar.makeStatusBarTransparent(getActivity());

        ensure = view.findViewById(R.id.country_ensure);
        recyclerView = view.findViewById(R.id.country_recycler);
        context = container.getContext();
        init();
        return view;
    }

    @SuppressLint("WrongConstant")
    private void init(){
        if (list.size() == 0) {
            countryUrl = countryUrl+"&projectType="+projectType;

            OkHttpPost okHttpPost = new OkHttpPost(countryUrl);
            String data = okHttpPost.post();
            Log.i("qssm","国家的："+data);
            String substring = data.substring(9, 10);

            if (substring.equals("1")) {
                CountryBean countryBean = new Gson().fromJson(data, CountryBean.class);
                list = countryBean.getData();

                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                CountryAdapter recyclerAdapter = new CountryAdapter(list);
                recyclerAdapter.setOnItemClickListener(new CountryAdapter.OnItemClickLisenter() {
                    @Override
                    public void onItemClick(int postion) {
                        FinalContents.setNation(list.get(postion).getNationName());
                    }
                });
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
                ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("event","");
                        EventBus.getDefault().post(eventUrl);
                    }
                });
            } else {
                Toast.makeText(getActivity(), "没有获取到数据", Toast.LENGTH_SHORT).show();
            }
        }else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            CountryAdapter recyclerAdapter = new CountryAdapter(list);
            recyclerAdapter.setOnItemClickListener(new CountryAdapter.OnItemClickLisenter() {
                @Override
                public void onItemClick(int postion) {
                    FinalContents.setNation(list.get(postion).getNationName());
                }
            });
            recyclerView.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();
            ensure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(eventUrl);
                }
            });
        }
    }

}
