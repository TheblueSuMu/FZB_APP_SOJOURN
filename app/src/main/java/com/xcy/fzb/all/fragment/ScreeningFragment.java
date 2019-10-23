package com.xcy.fzb.all.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;

import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreeningFragment extends Fragment implements View.OnClickListener {
    private CheckBox area1;
    private CheckBox area2;
    private CheckBox area3;
    private CheckBox area4;
    private CheckBox area5;
    private CheckBox area6;
    private CheckBox area7;
    private CheckBox area8;

    private CheckBox feature1;
    private CheckBox feature2;
    private CheckBox feature3;
    private CheckBox feature4;
    private CheckBox feature5;
    private CheckBox feature6;
    private CheckBox feature7;
    private CheckBox feature8;

    private CheckBox type1;
    private CheckBox type2;
    private CheckBox type3;
    private CheckBox type4;
    private CheckBox type5;

    private CheckBox decorate1;
    private CheckBox decorate2;
    private CheckBox decorate3;
    private CheckBox decorate4;

    private Button ensure;

    private String areaSection = "";
    private String ffProjectTrait = "";
    private String procuctType = "";
    private String fitmentState = "";

    private String url = "http://39.98.173.250:8080/fangfang/app/v1/commonSelect/projectList?"+ "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID();
    private String eventUrl;
    private Map<Integer,String> areaMap = new HashMap<>();
    private Map<Integer,String> traitMap = new HashMap<>();
    private Map<Integer,String> typeMap = new HashMap<>();
    private Map<Integer,String> stateMap = new HashMap<>();


    public ScreeningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_screening, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view){
        area1 = view.findViewById(R.id.screen_area_1);
        area2 = view.findViewById(R.id.screen_area_2);
        area3 = view.findViewById(R.id.screen_area_3);
        area4 = view.findViewById(R.id.screen_area_4);
        area5 = view.findViewById(R.id.screen_area_5);
        area6 = view.findViewById(R.id.screen_area_6);
        area7 = view.findViewById(R.id.screen_area_7);
        area8 = view.findViewById(R.id.screen_area_8);

        feature1 = view.findViewById(R.id.screen_feature_1);
        feature2 = view.findViewById(R.id.screen_feature_2);
        feature3 = view.findViewById(R.id.screen_feature_3);
        feature4 = view.findViewById(R.id.screen_feature_4);
        feature5 = view.findViewById(R.id.screen_feature_5);
        feature6 = view.findViewById(R.id.screen_feature_6);
        feature7 = view.findViewById(R.id.screen_feature_7);
        feature8 = view.findViewById(R.id.screen_feature_8);

        type1 = view.findViewById(R.id.screen_type_1);
        type2 = view.findViewById(R.id.screen_type_2);
        type3 = view.findViewById(R.id.screen_type_3);
        type4 = view.findViewById(R.id.screen_type_4);
        type5 = view.findViewById(R.id.screen_type_5);

        decorate1 = view.findViewById(R.id.screen_decorate_1);
        decorate2 = view.findViewById(R.id.screen_decorate_2);
        decorate3 = view.findViewById(R.id.screen_decorate_3);
        decorate4 = view.findViewById(R.id.screen_decorate_4);

        ensure = view.findViewById(R.id.screen_ensure);

        area1.setOnClickListener(this);
        area2.setOnClickListener(this);
        area3.setOnClickListener(this);
        area4.setOnClickListener(this);
        area5.setOnClickListener(this);
        area6.setOnClickListener(this);
        area7.setOnClickListener(this);
        area8.setOnClickListener(this);
        feature1.setOnClickListener(this);
        feature2.setOnClickListener(this);
        feature3.setOnClickListener(this);
        feature4.setOnClickListener(this);
        feature5.setOnClickListener(this);
        feature6.setOnClickListener(this);
        feature7.setOnClickListener(this);
        feature8.setOnClickListener(this);
        type1.setOnClickListener(this);
        type2.setOnClickListener(this);
        type3.setOnClickListener(this);
        type4.setOnClickListener(this);
        type5.setOnClickListener(this);
        decorate1.setOnClickListener(this);
        decorate2.setOnClickListener(this);
        decorate3.setOnClickListener(this);
        decorate4.setOnClickListener(this);
        ensure.setOnClickListener(this);


    }

    private void initData(){
        areaMap.put(0,"");
        areaMap.put(1,"");
        areaMap.put(2,"");
        areaMap.put(3,"");
        areaMap.put(4,"");
        areaMap.put(5,"");
        areaMap.put(6,"");
        areaMap.put(7,"");

        traitMap.put(0,"");
        traitMap.put(1,"");
        traitMap.put(2,"");
        traitMap.put(3,"");
        traitMap.put(4,"");
        traitMap.put(5,"");
        traitMap.put(6,"");
        traitMap.put(7,"");

        typeMap.put(0,"");
        typeMap.put(1,"");
        typeMap.put(2,"");
        typeMap.put(3,"");
        typeMap.put(4,"");

        stateMap.put(0,"");
        stateMap.put(1,"");
        stateMap.put(2,"");
        stateMap.put(3,"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //面积
            case R.id.screen_area_1:
                if (area1.isChecked()) {
                    areaMap.put(0,",-50");
                }else {
                    areaMap.put(0,"");
                }
                break;
            case R.id.screen_area_2:
                if (area2.isChecked()) {
                    areaMap.put(1,",50-70");
                }else {
                    areaMap.put(1,"");
                }
                break;
            case R.id.screen_area_3:
                if (area3.isChecked()) {
                    areaMap.put(2,",70-90");
                }else {
                    areaMap.put(2,"");
                }
                break;
            case R.id.screen_area_4:
                if (area4.isChecked()) {
                    areaMap.put(3,",90-110");
                }else {
                    areaMap.put(3,"");
                }
                break;
            case R.id.screen_area_5:
                if (area5.isChecked()) {
                    areaMap.put(4,",110-130");
                }else {
                    areaMap.put(4,"");
                }
                break;
            case R.id.screen_area_6:
                if (area6.isChecked()) {
                    areaMap.put(5,",130-150");
                }else {
                    areaMap.put(5,"");
                }
                break;
            case R.id.screen_area_7:
                if (area7.isChecked()) {
                    areaMap.put(6,",150-200");
                }else {
                    areaMap.put(6,"");
                }
                break;
            case R.id.screen_area_8:
                if (area8.isChecked()) {
                    areaMap.put(7,",200-");
                }else {
                    areaMap.put(7,"");
                }
                break;
            //楼盘特色
            case R.id.screen_feature_1:
                if (feature1.isChecked()) {
                    traitMap.put(0,",核心地段");
                }else {
                    traitMap.put(0,"");
                }
                break;
            case R.id.screen_feature_2:
                if (feature2.isChecked()) {
                    traitMap.put(1,",品牌房企");
                }else {
                    traitMap.put(1,"");
                }
                break;
            case R.id.screen_feature_3:
                if (feature3.isChecked()) {
                    traitMap.put(2,",地铁沿线");
                }else {
                    traitMap.put(2,"");
                }
                break;
            case R.id.screen_feature_4:
                if (feature4.isChecked()) {
                    traitMap.put(3,",学区房");
                }else {
                    traitMap.put(3,"");
                }
                break;
            case R.id.screen_feature_5:
                if (feature5.isChecked()) {
                    traitMap.put(4,",河景房");
                }else {
                    traitMap.put(4,"");
                }
                break;
            case R.id.screen_feature_6:
                if (feature6.isChecked()) {
                    traitMap.put(5,",现房");
                }else {
                    traitMap.put(5,"");
                }
                break;
            case R.id.screen_feature_7:
                if (feature7.isChecked()) {
                    traitMap.put(6,",户型方正");
                }else {
                    traitMap.put(6,"");
                }
                break;
            case R.id.screen_feature_8:
                if (feature8.isChecked()) {
                    traitMap.put(7,",首付分期");
                }else {
                    traitMap.put(7,"");
                }
                break;
            //类型
            case R.id.screen_type_1:
                if (type1.isChecked()) {
                    typeMap.put(0,",1");
                }else {
                    typeMap.put(0,"");
                }
                break;
            case R.id.screen_type_2:
                if (type2.isChecked()) {
                    typeMap.put(1,",5");
                }else {
                    typeMap.put(1,"");
                }
                break;
            case R.id.screen_type_3:
                if (type3.isChecked()) {
                    typeMap.put(2,",4");
                }else {
                    typeMap.put(2,"");
                }
                break;
            case R.id.screen_type_4:
                if (type4.isChecked()) {
                    typeMap.put(3,",3");
                }else {
                    typeMap.put(3,"");
                }
                break;
            case R.id.screen_type_5:
                if (type5.isChecked()) {
                    typeMap.put(4,",2");
                }else {
                    typeMap.put(4,"");
                }
                break;
            //装修
            case R.id.screen_decorate_1:
                if (decorate1.isChecked()) {
                    stateMap.put(0,",1");
                }else {
                    stateMap.put(0,"");
                }
                break;
            case R.id.screen_decorate_2:
                if (decorate2.isChecked()) {
                    stateMap.put(1,",2");
                }else {
                    stateMap.put(1,"");
                }
                break;
            case R.id.screen_decorate_3:
                if (decorate3.isChecked()) {
                    stateMap.put(2,",3");
                }else {
                    stateMap.put(2,"");
                }
                break;
            case R.id.screen_decorate_4:
                if (decorate4.isChecked()) {
                    stateMap.put(3,",4");
                }else {
                    stateMap.put(3,"");
                }
                break;
            case R.id.screen_ensure:
                for (int i = 0;i < areaMap.size();i++){
                    areaSection = areaSection + areaMap.get(i);
                }
                for (int i = 0;i < traitMap.size();i++){
                    ffProjectTrait = ffProjectTrait + traitMap.get(i);
                }
                for (int i = 0;i < typeMap.size();i++){
                    procuctType = procuctType + typeMap.get(i);
                }
                for (int i = 0;i < stateMap.size();i++){
                    fitmentState = fitmentState + stateMap.get(i);
                }
                eventUrl = url+ "&areaSection="+areaSection+"&ffProjectTrait="+ffProjectTrait+"&procuctType="+procuctType+"&fitmentState="+fitmentState;
                FinalContents.setAreaSection(areaSection);
                FinalContents.setFfProjectTrait(ffProjectTrait);
                FinalContents.setProcuctType(procuctType);
                FinalContents.setFitmentState(fitmentState);
                EventBus.getDefault().post(eventUrl);
                areaSection = "";
                ffProjectTrait = "";
                procuctType = "";
                fitmentState = "";
                break;
        }
    }
}
