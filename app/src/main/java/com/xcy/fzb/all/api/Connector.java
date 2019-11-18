package com.xcy.fzb.all.api;

import com.xcy.fzb.all.database.ExemplaryUserBean;
import com.xcy.fzb.all.modle.CustomerListBean;
import com.xcy.fzb.all.modle.GWDataBean;
import com.xcy.fzb.all.modle.UserBean;
import com.xcy.fzb.all.modle.UserMessageBean;
import com.xcy.fzb.all.modle.XSDataBean;
import com.xcy.fzb.all.modle.ZYDataBean;
import com.xcy.fzb.all.modle.ZhangBingDataBean;

public class Connector {
    public static boolean JJQ = false;


    public static UserMessageBean userMessageBean;

    public static XSDataBean xsDataBean;

    public static ZYDataBean zyDataBean;

    public static ZhangBingDataBean zhangBingDataBean;

    public static GWDataBean gwDataBean;

    public static UserBean userBean;

    public static ExemplaryUserBean exemplaryUserBean;

    static CustomerListBean customerListBean;

    public static CustomerListBean getCustomerListBean() {
        return customerListBean;
    }

    public static void setCustomerListBean(CustomerListBean customerListBean) {
        Connector.customerListBean = customerListBean;
    }

    public static ExemplaryUserBean getExemplaryUserBean() {
        return exemplaryUserBean;
    }

    public static void setExemplaryUserBean(ExemplaryUserBean exemplaryUserBean) {
        Connector.exemplaryUserBean = exemplaryUserBean;
    }

    public static UserBean getUserBean() {
        return userBean;
    }

    public static void setUserBean(UserBean userBean) {
        Connector.userBean = userBean;
    }

    public static GWDataBean getGwDataBean() {
        return gwDataBean;
    }

    public static void setGwDataBean(GWDataBean gwDataBean) {
        Connector.gwDataBean = gwDataBean;
    }

    public static ZhangBingDataBean getZhangBingDataBean() {
        return zhangBingDataBean;
    }

    public static void setZhangBingDataBean(ZhangBingDataBean zhangBingDataBean) {
        Connector.zhangBingDataBean = zhangBingDataBean;
    }

    public static ZYDataBean getZyDataBean() {
        return zyDataBean;
    }

    public static void setZyDataBean(ZYDataBean zyDataBean) {
        Connector.zyDataBean = zyDataBean;
    }

    public static XSDataBean getXsDataBean() {
        return xsDataBean;
    }

    public static void setXsDataBean(XSDataBean xsDataBean) {
        Connector.xsDataBean = xsDataBean;
    }

    public static UserMessageBean getUserMessageBean() {
        return userMessageBean;
    }

    public static void setUserMessageBean(UserMessageBean userMessageBean) {
        Connector.userMessageBean = userMessageBean;
    }

    public static boolean isJJQ() {
        return JJQ;
    }

    public static void setJJQ(boolean JJQ) {
        Connector.JJQ = JJQ;
    }
}
