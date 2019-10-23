package com.xcy.fzb.all.modle;

import java.util.List;

public class ProcessDataBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":1,"rows":[{"id":"","remarks":"","createBy":"","createDate":"2019-09-23 11:01:03","updateBy":"","updateDate":"2019-09-23 11:15:01","user":"","agentUser":"","storeId":"","agentId":"","projectId":"","preparationId":"","customerName":"佳佳","customerPhone":"","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"西港云上","agentName":"你好","agentPhone":"18235195941","companyName":"唐老鸭","storeName":"鸭店001","auditStatus":"2","visitStatus":"","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"","status":"0","attacheName":"专员","isMy":"1","search":"","dateStr":"失效时间 2019-09-23 11:15","date":"失效时间 2019-09-23 11:15","statusStr":"失效","relatedData":"失效"}]}
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
         * total : 1
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"2019-09-23 11:01:03","updateBy":"","updateDate":"2019-09-23 11:15:01","user":"","agentUser":"","storeId":"","agentId":"","projectId":"","preparationId":"","customerName":"佳佳","customerPhone":"","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"西港云上","agentName":"你好","agentPhone":"18235195941","companyName":"唐老鸭","storeName":"鸭店001","auditStatus":"2","visitStatus":"","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"","status":"0","attacheName":"专员","isMy":"1","search":"","dateStr":"失效时间 2019-09-23 11:15","date":"失效时间 2019-09-23 11:15","statusStr":"失效","relatedData":"失效"}]
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
             * createDate : 2019-09-23 11:01:03
             * updateBy :
             * updateDate : 2019-09-23 11:15:01
             * user :
             * agentUser :
             * storeId :
             * agentId :
             * projectId :
             * preparationId :
             * customerName : 佳佳
             * customerPhone :
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * projectName : 西港云上
             * agentName : 你好
             * agentPhone : 18235195941
             * companyName : 唐老鸭
             * storeName : 鸭店001
             * auditStatus : 2
             * visitStatus :
             * landingStatus :
             * earnestStatus :
             * tradeStatus :
             * protectDay :
             * status : 0
             * attacheName : 专员
             * isMy : 1
             * search :
             * dateStr : 失效时间 2019-09-23 11:15
             * date : 失效时间 2019-09-23 11:15
             * statusStr : 失效
             * relatedData : 失效
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String user;
            private String agentUser;
            private String storeId;
            private String agentId;
            private String projectId;
            private String preparationId;
            private String customerName;
            private String customerPhone;
            private String customerImg;
            private String projectName;
            private String agentName;
            private String agentPhone;
            private String companyName;
            private String storeName;
            private String auditStatus;
            private String visitStatus;
            private String landingStatus;
            private String earnestStatus;
            private String tradeStatus;
            private String protectDay;
            private String status;
            private String attacheName;
            private String isMy;
            private String search;
            private String dateStr;
            private String date;
            private String statusStr;
            private String relatedData;

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

            public String getAgentUser() {
                return agentUser;
            }

            public void setAgentUser(String agentUser) {
                this.agentUser = agentUser;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getCustomerPhone() {
                return customerPhone;
            }

            public void setCustomerPhone(String customerPhone) {
                this.customerPhone = customerPhone;
            }

            public String getCustomerImg() {
                return customerImg;
            }

            public void setCustomerImg(String customerImg) {
                this.customerImg = customerImg;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getAgentName() {
                return agentName;
            }

            public void setAgentName(String agentName) {
                this.agentName = agentName;
            }

            public String getAgentPhone() {
                return agentPhone;
            }

            public void setAgentPhone(String agentPhone) {
                this.agentPhone = agentPhone;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getAuditStatus() {
                return auditStatus;
            }

            public void setAuditStatus(String auditStatus) {
                this.auditStatus = auditStatus;
            }

            public String getVisitStatus() {
                return visitStatus;
            }

            public void setVisitStatus(String visitStatus) {
                this.visitStatus = visitStatus;
            }

            public String getLandingStatus() {
                return landingStatus;
            }

            public void setLandingStatus(String landingStatus) {
                this.landingStatus = landingStatus;
            }

            public String getEarnestStatus() {
                return earnestStatus;
            }

            public void setEarnestStatus(String earnestStatus) {
                this.earnestStatus = earnestStatus;
            }

            public String getTradeStatus() {
                return tradeStatus;
            }

            public void setTradeStatus(String tradeStatus) {
                this.tradeStatus = tradeStatus;
            }

            public String getProtectDay() {
                return protectDay;
            }

            public void setProtectDay(String protectDay) {
                this.protectDay = protectDay;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAttacheName() {
                return attacheName;
            }

            public void setAttacheName(String attacheName) {
                this.attacheName = attacheName;
            }

            public String getIsMy() {
                return isMy;
            }

            public void setIsMy(String isMy) {
                this.isMy = isMy;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }

            public String getDateStr() {
                return dateStr;
            }

            public void setDateStr(String dateStr) {
                this.dateStr = dateStr;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getStatusStr() {
                return statusStr;
            }

            public void setStatusStr(String statusStr) {
                this.statusStr = statusStr;
            }

            public String getRelatedData() {
                return relatedData;
            }

            public void setRelatedData(String relatedData) {
                this.relatedData = relatedData;
            }
        }
    }
}
