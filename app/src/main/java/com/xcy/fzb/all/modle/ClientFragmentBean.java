package com.xcy.fzb.all.modle;

import java.util.List;

public class ClientFragmentBean {
    /**
     * code : 1
     * msg : 成功
     * data : {"total":4,"rows":[{"date":"报备时间2019-11-23 09:42:52","agentId":"7b585b28383e485bbbfe1fd35f83d94e","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","isRead":"0","agentName":"经纪人：hh","customerName":"部分结","customerPhone":"18713695566","customerId":"534cfb4f4c584865bb3e6d3a5c9b22ef","preparationId":"f83f75dbfa4d4c03bbc493138fd97d86","relatedData":"报备成功\n保护期剩余30天","intentionArea":"","projectName":"融创孔雀镇","projectId":"a7707dbc70714b7e967a25df7f8008dd","status":"10"},{"date":"报备时间2019-11-25 14:51:39","agentId":"9323691b1f794c37819ba1e7d6e8e366","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","isRead":"1","agentName":"经纪人：happy","customerName":"欧阳娜娜","customerPhone":"15808086363","customerId":"7286288b7f3e4108a67061c68747504a","preparationId":"6043d8b953d84e41aa526fbbdf03062d","relatedData":"报备成功\n保护期剩余30天","intentionArea":"","projectName":"融创孔雀镇","projectId":"a7707dbc70714b7e967a25df7f8008dd","status":"10"},{"date":"报备时间2019-11-25 14:45:49","agentId":"9323691b1f794c37819ba1e7d6e8e366","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","isRead":"0","agentName":"经纪人：happy","customerName":"保护期过期","customerPhone":"186****3366","customerId":"41caf568929147a79ff54b09f3f39a34","preparationId":"f4487b903e1e42b3b9f7564b491bdb6d","relatedData":"报备成功\n保护期剩余1天","intentionArea":"","projectName":"丽江时光","projectId":"ef3f647c7e3643bc882c9fc365634a1a","status":"10"},{"date":"报备时间2019-11-23 10:55:39","agentId":"21d16305c36e4d7a84abd7afa31b0de5","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","isRead":"0","agentName":"经纪人：西毒","customerName":"到访","customerPhone":"158****6652","customerId":"5424bfeecc944b8fb06b855cf1222cd4","preparationId":"16e5057f1bb14d1da4027edc0c18c73f","relatedData":"报备成功\n保护期剩余7天","intentionArea":"","projectName":"大理的小院子","projectId":"7c2eedbf85f149bc95c736653e31ee6b","status":"10"}]}
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
         * total : 4
         * rows : [{"date":"报备时间2019-11-23 09:42:52","agentId":"7b585b28383e485bbbfe1fd35f83d94e","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","isRead":"0","agentName":"经纪人：hh","customerName":"部分结","customerPhone":"18713695566","customerId":"534cfb4f4c584865bb3e6d3a5c9b22ef","preparationId":"f83f75dbfa4d4c03bbc493138fd97d86","relatedData":"报备成功\n保护期剩余30天","intentionArea":"","projectName":"融创孔雀镇","projectId":"a7707dbc70714b7e967a25df7f8008dd","status":"10"},{"date":"报备时间2019-11-25 14:51:39","agentId":"9323691b1f794c37819ba1e7d6e8e366","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","isRead":"1","agentName":"经纪人：happy","customerName":"欧阳娜娜","customerPhone":"15808086363","customerId":"7286288b7f3e4108a67061c68747504a","preparationId":"6043d8b953d84e41aa526fbbdf03062d","relatedData":"报备成功\n保护期剩余30天","intentionArea":"","projectName":"融创孔雀镇","projectId":"a7707dbc70714b7e967a25df7f8008dd","status":"10"},{"date":"报备时间2019-11-25 14:45:49","agentId":"9323691b1f794c37819ba1e7d6e8e366","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","isRead":"0","agentName":"经纪人：happy","customerName":"保护期过期","customerPhone":"186****3366","customerId":"41caf568929147a79ff54b09f3f39a34","preparationId":"f4487b903e1e42b3b9f7564b491bdb6d","relatedData":"报备成功\n保护期剩余1天","intentionArea":"","projectName":"丽江时光","projectId":"ef3f647c7e3643bc882c9fc365634a1a","status":"10"},{"date":"报备时间2019-11-23 10:55:39","agentId":"21d16305c36e4d7a84abd7afa31b0de5","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","isRead":"0","agentName":"经纪人：西毒","customerName":"到访","customerPhone":"158****6652","customerId":"5424bfeecc944b8fb06b855cf1222cd4","preparationId":"16e5057f1bb14d1da4027edc0c18c73f","relatedData":"报备成功\n保护期剩余7天","intentionArea":"","projectName":"大理的小院子","projectId":"7c2eedbf85f149bc95c736653e31ee6b","status":"10"}]
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
             * date : 报备时间2019-11-23 09:42:52
             * agentId : 7b585b28383e485bbbfe1fd35f83d94e
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * projectType : 3
             * isRead : 0
             * agentName : 经纪人：hh
             * customerName : 部分结
             * customerPhone : 18713695566
             * customerId : 534cfb4f4c584865bb3e6d3a5c9b22ef
             * preparationId : f83f75dbfa4d4c03bbc493138fd97d86
             * relatedData : 报备成功
             保护期剩余30天
             * intentionArea :
             * projectName : 融创孔雀镇
             * projectId : a7707dbc70714b7e967a25df7f8008dd
             * status : 10
             */

            private String date;
            private String agentId;
            private String customerImg;
            private String projectType;
            private String isRead;
            private String agentName;
            private String customerName;
            private String customerPhone;
            private String customerId;
            private String preparationId;
            private String relatedData;
            private String intentionArea;
            private String projectName;
            private String projectId;
            private String status;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
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

            public String getIsRead() {
                return isRead;
            }

            public void setIsRead(String isRead) {
                this.isRead = isRead;
            }

            public String getAgentName() {
                return agentName;
            }

            public void setAgentName(String agentName) {
                this.agentName = agentName;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
