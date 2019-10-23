package com.xcy.fzb.shopping_guide.modle;

import java.util.List;

public class ClientFragmentBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":3,"rows":[{"date":"报备时间2019-09-05 17:08:34","customerPhone":"15800000000","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","customerId":"189045bed351420ea0d903635a7af94d","preparationId":"3c8759c3889a4809b069c6b5ffd1fca0","relatedData":"","intentionArea":"","projectName":"丽江时光","projectId":"73c35fda713946de8cd0d411716ff070","customerName":"asd","status":"10"},{"date":"报备时间2019-07-01 15:46:28","customerPhone":"13380003366","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","customerId":"855cebb6befa483985ab33af19b34322","preparationId":"bd5e8ff2a181438c9b5dea338ded7b0f","relatedData":"","intentionArea":"","projectName":"丽江时光","projectId":"73c35fda713946de8cd0d411716ff070","customerName":"黄家驹","status":"10"},{"date":"报备时间2019-06-28 17:56:41","customerPhone":"123456","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","customerId":"82c05675e5f247939ab28f9b1d8b3ff4","preparationId":"ee7edc2537384d3d8f99ce15fb809e4e","relatedData":"","intentionArea":"","projectName":"丽江时光","projectId":"73c35fda713946de8cd0d411716ff070","customerName":"aaa","status":"10"}]}
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
         * total : 3
         * rows : [{"date":"报备时间2019-09-05 17:08:34","customerPhone":"15800000000","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","customerId":"189045bed351420ea0d903635a7af94d","preparationId":"3c8759c3889a4809b069c6b5ffd1fca0","relatedData":"","intentionArea":"","projectName":"丽江时光","projectId":"73c35fda713946de8cd0d411716ff070","customerName":"asd","status":"10"},{"date":"报备时间2019-07-01 15:46:28","customerPhone":"13380003366","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","customerId":"855cebb6befa483985ab33af19b34322","preparationId":"bd5e8ff2a181438c9b5dea338ded7b0f","relatedData":"","intentionArea":"","projectName":"丽江时光","projectId":"73c35fda713946de8cd0d411716ff070","customerName":"黄家驹","status":"10"},{"date":"报备时间2019-06-28 17:56:41","customerPhone":"123456","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","customerId":"82c05675e5f247939ab28f9b1d8b3ff4","preparationId":"ee7edc2537384d3d8f99ce15fb809e4e","relatedData":"","intentionArea":"","projectName":"丽江时光","projectId":"73c35fda713946de8cd0d411716ff070","customerName":"aaa","status":"10"}]
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
             * date : 报备时间2019-09-05 17:08:34
             * customerPhone : 15800000000
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * projectType : 3
             * customerId : 189045bed351420ea0d903635a7af94d
             * preparationId : 3c8759c3889a4809b069c6b5ffd1fca0
             * relatedData :
             * intentionArea :
             * projectName : 丽江时光
             * projectId : 73c35fda713946de8cd0d411716ff070
             * customerName : asd
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
