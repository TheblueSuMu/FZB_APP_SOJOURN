package com.xcy.fzb.all.database;

public class StoreListData {

    String StoreName;
    String StoreId;
    String ShopownerName;
    String ShopownerPhone;
    String AgentNum;
    String Status;
    String CompanyId;

    String companyName;
    String storeIdCode;
    String companyAddress;
    String storeNum;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStoreIdCode() {
        return storeIdCode;
    }

    public void setStoreIdCode(String storeIdCode) {
        this.storeIdCode = storeIdCode;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public StoreListData() {
    }

    public StoreListData(String storeName, String storeId, String shopownerName, String shopownerPhone, String agentNum, String status,String companyId) {
        StoreName = storeName;
        StoreId = storeId;
        ShopownerName = shopownerName;
        ShopownerPhone = shopownerPhone;
        AgentNum = agentNum;
        Status = status;
        CompanyId = companyId;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public String getShopownerName() {
        return ShopownerName;
    }

    public void setShopownerName(String shopownerName) {
        ShopownerName = shopownerName;
    }

    public String getShopownerPhone() {
        return ShopownerPhone;
    }

    public void setShopownerPhone(String shopownerPhone) {
        ShopownerPhone = shopownerPhone;
    }

    public String getAgentNum() {
        return AgentNum;
    }

    public void setAgentNum(String agentNum) {
        AgentNum = agentNum;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
