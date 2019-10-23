package com.xcy.fzb.project_side.application;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.xcy.fzb.project_side.api.FinalContents;
import com.xcy.fzb.project_side.fragment.ComprehensiveFragment;
import com.xcy.fzb.project_side.fragment.CountryFragment;
import com.xcy.fzb.project_side.fragment.HomeFragment;
import com.xcy.fzb.project_side.fragment.HouseTypeFragment;
import com.xcy.fzb.project_side.fragment.MessageFragment;
import com.xcy.fzb.project_side.fragment.PriceFragment;
import com.xcy.fzb.project_side.fragment.ScreeningFragment;
import com.xcy.fzb.project_side.modle.CityBean;
import com.xcy.fzb.project_side.modle.HotBean;
import com.xcy.fzb.project_side.modle.ImgData;
import com.xcy.fzb.project_side.modle.MessageBean2;
import com.xcy.fzb.project_side.modle.NationBean;
import com.xcy.fzb.project_side.modle.NewsBean;

import java.util.ArrayList;
import java.util.List;


public class DemoApplication extends Application {

//    public DFragment dFragment;
//    public EFragment eFragment;
    public MessageFragment message_fragment;
    public HomeFragment home_fragment;

    public List<NewsBean.DataBean> newsList;
    public List<ImgData.DataBean> imglist;
    public List<MessageBean2.DataBean.RowsBean> messagelist;
    public List<CityBean.DataBean> citylist;
    public List<HotBean.DataBean.RowsBean> hotlist;

    public ComprehensiveFragment comprehensiveFragment;
    public CountryFragment countryFragment;
    public PriceFragment priceFragment;
    public HouseTypeFragment houseTypeFragment;
    public ScreeningFragment screeningFragment;

    public List<NationBean.DataBean> nationlist;
    public List<ImgData.DataBean> imagelist;

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        setCitylist(new ArrayList<CityBean.DataBean>());
//        setdFragment(new DFragment());
//        seteFragment(new EFragment());
        setHome_fragment(new HomeFragment());
        setHotlist(new ArrayList<HotBean.DataBean.RowsBean>());
        setImglist(new ArrayList<ImgData.DataBean>());
        setMessage_fragment(new MessageFragment());
        setMessagelist(new ArrayList<MessageBean2.DataBean.RowsBean>());
        setNewsList(new ArrayList<NewsBean.DataBean>());

        setComprehensiveFragment(new ComprehensiveFragment());
        setCountryFragment(new CountryFragment(FinalContents.getProjectType()));
        setPriceFragment(new PriceFragment());
        setHouseTypeFragment(new HouseTypeFragment());
        setScreeningFragment(new ScreeningFragment());
        setNationlist(new ArrayList<NationBean.DataBean>());
        setImagelist(new ArrayList<ImgData.DataBean>());
    }

    public ComprehensiveFragment getComprehensiveFragment() {
        return comprehensiveFragment;
    }

    public void setComprehensiveFragment(ComprehensiveFragment comprehensiveFragment) {
        this.comprehensiveFragment = comprehensiveFragment;
    }

    public CountryFragment getCountryFragment() {
        return countryFragment;
    }

    public void setCountryFragment(CountryFragment countryFragment) {
        this.countryFragment = countryFragment;
    }

    public PriceFragment getPriceFragment() {
        return priceFragment;
    }

    public void setPriceFragment(PriceFragment priceFragment) {
        this.priceFragment = priceFragment;
    }

    public HouseTypeFragment getHouseTypeFragment() {
        return houseTypeFragment;
    }

    public void setHouseTypeFragment(HouseTypeFragment houseTypeFragment) {
        this.houseTypeFragment = houseTypeFragment;
    }

    public ScreeningFragment getScreeningFragment() {
        return screeningFragment;
    }

    public void setScreeningFragment(ScreeningFragment screeningFragment) {
        this.screeningFragment = screeningFragment;
    }

    public List<NationBean.DataBean> getNationlist() {
        return nationlist;
    }

    public void setNationlist(List<NationBean.DataBean> nationlist) {
        this.nationlist = nationlist;
    }

    public List<ImgData.DataBean> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<ImgData.DataBean> imagelist) {
        this.imagelist = imagelist;
    }

//    public DFragment getdFragment() {
//        return dFragment;
//    }
//
//    public void setdFragment(DFragment dFragment) {
//        this.dFragment = dFragment;
//    }
//
//    public EFragment geteFragment() {
//        return eFragment;
//    }
//
//    public void seteFragment(EFragment eFragment) {
//        this.eFragment = eFragment;
//    }

    public MessageFragment getMessage_fragment() {
        return message_fragment;
    }

    public void setMessage_fragment(MessageFragment message_fragment) {
        this.message_fragment = message_fragment;
    }

    public HomeFragment getHome_fragment() {
        return home_fragment;
    }

    public void setHome_fragment(HomeFragment home_fragment) {
        this.home_fragment = home_fragment;
    }

    public List<NewsBean.DataBean> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsBean.DataBean> newsList) {
        this.newsList = newsList;
    }

    public List<ImgData.DataBean> getImglist() {
        return imglist;
    }

    public void setImglist(List<ImgData.DataBean> imglist) {
        this.imglist = imglist;
    }

    public List<MessageBean2.DataBean.RowsBean> getMessagelist() {
        return messagelist;
    }

    public void setMessagelist(List<MessageBean2.DataBean.RowsBean> messagelist) {
        this.messagelist = messagelist;
    }

    public List<CityBean.DataBean> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CityBean.DataBean> citylist) {
        this.citylist = citylist;
    }

    public List<HotBean.DataBean.RowsBean> getHotlist() {
        return hotlist;
    }

    public void setHotlist(List<HotBean.DataBean.RowsBean> hotlist) {
        this.hotlist = hotlist;
    }
}
