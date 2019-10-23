package com.xcy.fzb.project_side.modle;

import java.util.List;

public class InitiatedBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":1,"rows":[{"date":"认筹时间2019-06-15 20:40:42","customerPhone":"133****6677","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","customerId":"c3a39e3ed0f64d82bc0641752a6993ba","preparationId":"37fde01be56845ecad2f72bcb271dfa7","relatedData":"退筹审核","intentionArea":"","projectName":"丽江时光","projectId":"73c35fda713946de8cd0d411716ff070","customerName":"测试问题344","status":"50"}]}
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
         * rows : [{"date":"认筹时间2019-06-15 20:40:42","customerPhone":"133****6677","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","customerId":"c3a39e3ed0f64d82bc0641752a6993ba","preparationId":"37fde01be56845ecad2f72bcb271dfa7","relatedData":"退筹审核","intentionArea":"","projectName":"丽江时光","projectId":"73c35fda713946de8cd0d411716ff070","customerName":"测试问题344","status":"50"}]
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
             * date : 认筹时间2019-06-15 20:40:42
             * customerPhone : 133****6677
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * projectType : 3
             * customerId : c3a39e3ed0f64d82bc0641752a6993ba
             * preparationId : 37fde01be56845ecad2f72bcb271dfa7
             * relatedData : 退筹审核
             * intentionArea :
             * projectName : 丽江时光
             * projectId : 73c35fda713946de8cd0d411716ff070
             * customerName : 测试问题344
             * status : 50
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
