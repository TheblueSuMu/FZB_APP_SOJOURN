package com.xcy.fzb.all.database;

import java.util.List;

public class StoreListBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":10,"rows":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"bf38ccb2df8243cd86c320f309df44e0","companyName":"001","companyAddress":"姑姑呼呼呼不不不阿拉","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"001","shopownerPhone":"15423546213","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"d44a13b6141d4600bd59b5157149bec5","companyName":"开发测试一","companyAddress":"金钟河大街","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"baa267b6cd0340e4bad097ba0a1b9014","companyName":"于","companyAddress":"","storeNum":"0","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"4f3bbbb8643f43b7816f5b32e4fde2b2","companyName":"雪茄","companyAddress":"123","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"雪茄01","shopownerPhone":"15506014085","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"bab6ae2572ec4c25bad6b3875be36c28","companyName":"测试公司","companyAddress":"朝阳区南湖大路","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"c56d4aea06454af0a77d435160710214","companyName":"杨进礼测试","companyAddress":"还不睡呢是呢你呢","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"qazxc","shopownerPhone":"12345678901","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","companyAddress":"123","storeNum":"2","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"苹果1店01","shopownerPhone":"16948732565","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"004c70f2438249039728411b50d996a7","companyName":"唐老鸭","companyAddress":"吉林省长春市南关区人民大街辅路","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"cs5","shopownerPhone":"18235195941","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"221408846cce4194a60a7e7d647daf3e","companyName":"刘喆测试分店","companyAddress":"长春市南三环亚泰大街交汇","storeNum":"0","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"c1af35d848ec45ae9aab71429c9005ab","companyName":"刘喆测试","companyAddress":"长春市南三环亚泰大街交汇","storeNum":"0","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""}]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 10
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"bf38ccb2df8243cd86c320f309df44e0","companyName":"001","companyAddress":"姑姑呼呼呼不不不阿拉","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"001","shopownerPhone":"15423546213","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"d44a13b6141d4600bd59b5157149bec5","companyName":"开发测试一","companyAddress":"金钟河大街","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"baa267b6cd0340e4bad097ba0a1b9014","companyName":"于","companyAddress":"","storeNum":"0","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"4f3bbbb8643f43b7816f5b32e4fde2b2","companyName":"雪茄","companyAddress":"123","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"雪茄01","shopownerPhone":"15506014085","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"bab6ae2572ec4c25bad6b3875be36c28","companyName":"测试公司","companyAddress":"朝阳区南湖大路","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"c56d4aea06454af0a77d435160710214","companyName":"杨进礼测试","companyAddress":"还不睡呢是呢你呢","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"qazxc","shopownerPhone":"12345678901","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","companyAddress":"123","storeNum":"2","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"苹果1店01","shopownerPhone":"16948732565","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"004c70f2438249039728411b50d996a7","companyName":"唐老鸭","companyAddress":"吉林省长春市南关区人民大街辅路","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"cs5","shopownerPhone":"18235195941","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"221408846cce4194a60a7e7d647daf3e","companyName":"刘喆测试分店","companyAddress":"长春市南三环亚泰大街交汇","storeNum":"0","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"c1af35d848ec45ae9aab71429c9005ab","companyName":"刘喆测试","companyAddress":"长春市南三环亚泰大街交汇","storeNum":"0","storeName":"","storeId":"","storeIdCode":"","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id :
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * user :
             * companyId : bf38ccb2df8243cd86c320f309df44e0
             * companyName : 001
             * companyAddress : 姑姑呼呼呼不不不阿拉
             * storeNum : 1
             * storeName :
             * storeId :
             * storeIdCode :
             * isShopowner : 1
             * shopownerName : 001
             * shopownerPhone : 15423546213
             * loginName :
             * status : 1
             * area :
             * address :
             * storeRise :
             * storeImg :
             * location :
             * reason :
             * agentNum :
             * search :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String user;
            private String companyId;
            private String companyName;
            private String companyAddress;
            private String storeNum;
            private String storeName;
            private String storeId;
            private String storeIdCode;
            private String isShopowner;
            private String shopownerName;
            private String shopownerPhone;
            private String loginName;
            private String status;
            private String area;
            private String address;
            private String storeRise;
            private String storeImg;
            private String location;
            private String reason;
            private String agentNum;
            private String search;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
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

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getStoreIdCode() {
                return storeIdCode;
            }

            public void setStoreIdCode(String storeIdCode) {
                this.storeIdCode = storeIdCode;
            }

            public String getIsShopowner() {
                return isShopowner;
            }

            public void setIsShopowner(String isShopowner) {
                this.isShopowner = isShopowner;
            }

            public String getShopownerName() {
                return shopownerName;
            }

            public void setShopownerName(String shopownerName) {
                this.shopownerName = shopownerName;
            }

            public String getShopownerPhone() {
                return shopownerPhone;
            }

            public void setShopownerPhone(String shopownerPhone) {
                this.shopownerPhone = shopownerPhone;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getStoreRise() {
                return storeRise;
            }

            public void setStoreRise(String storeRise) {
                this.storeRise = storeRise;
            }

            public String getStoreImg() {
                return storeImg;
            }

            public void setStoreImg(String storeImg) {
                this.storeImg = storeImg;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getAgentNum() {
                return agentNum;
            }

            public void setAgentNum(String agentNum) {
                this.agentNum = agentNum;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }
        }
    }
}

