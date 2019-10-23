package com.xcy.fzb.captain_team.database;

import java.util.List;

public class CheckPendingBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":2,"rows":[{"date":"报备时间2019-05-05 13:16:51","customerPhone":"123456","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"","customerId":"82c05675e5f247939ab28f9b1d8b3ff4","preparationId":"980f45eee2154c5e9e2794450f26ce08","relatedData":"待审核","intentionArea":"","projectName":"Chelsea Creek | 切尔西水畔 泰晤士河北岸伦敦西二区","projectId":"","customerName":"aaa","status":"10"},{"date":"报备时间2019-04-30 11:45:25","customerPhone":"18964641","customerImg":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment//ff/server/customer/2019/4/1556540329209.jpeg","projectType":"","customerId":"9360ddf8912c475aa65f9fe3ffccae28","preparationId":"5a9a15bbc7574a0e855b2638fc244e90","relatedData":"待审核","intentionArea":"","projectName":"Chelsea Creek | 切尔西水畔 泰晤士河北岸伦敦西二区","projectId":"","customerName":"帮你暖暖","status":"10"}]}
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
         * total : 2
         * rows : [{"date":"报备时间2019-05-05 13:16:51","customerPhone":"123456","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"","customerId":"82c05675e5f247939ab28f9b1d8b3ff4","preparationId":"980f45eee2154c5e9e2794450f26ce08","relatedData":"待审核","intentionArea":"","projectName":"Chelsea Creek | 切尔西水畔 泰晤士河北岸伦敦西二区","projectId":"","customerName":"aaa","status":"10"},{"date":"报备时间2019-04-30 11:45:25","customerPhone":"18964641","customerImg":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment//ff/server/customer/2019/4/1556540329209.jpeg","projectType":"","customerId":"9360ddf8912c475aa65f9fe3ffccae28","preparationId":"5a9a15bbc7574a0e855b2638fc244e90","relatedData":"待审核","intentionArea":"","projectName":"Chelsea Creek | 切尔西水畔 泰晤士河北岸伦敦西二区","projectId":"","customerName":"帮你暖暖","status":"10"}]
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
             * date : 报备时间2019-05-05 13:16:51
             * customerPhone : 123456
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * projectType :
             * customerId : 82c05675e5f247939ab28f9b1d8b3ff4
             * preparationId : 980f45eee2154c5e9e2794450f26ce08
             * relatedData : 待审核
             * intentionArea :
             * projectName : Chelsea Creek | 切尔西水畔 泰晤士河北岸伦敦西二区
             * projectId :
             * customerName : aaa
             * status : 10
             */

            private String date;
            private String customerPhone;
            private String customerImg;
            private String projectType;
            private String customerId;
            private String preparationId;
            private String relatedData;
            private String intentionArea;
            private String projectName;
            private String projectId;
            private String customerName;
            private String status;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
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

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getRelatedData() {
                return relatedData;
            }

            public void setRelatedData(String relatedData) {
                this.relatedData = relatedData;
            }

            public String getIntentionArea() {
                return intentionArea;
            }

            public void setIntentionArea(String intentionArea) {
                this.intentionArea = intentionArea;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
