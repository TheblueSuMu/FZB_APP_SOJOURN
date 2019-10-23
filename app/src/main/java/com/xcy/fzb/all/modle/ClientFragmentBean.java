package com.xcy.fzb.all.modle;

import java.util.List;

public class ClientFragmentBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":5,"rows":[{"date":"报备时间2019-10-19 17:48:51","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"川普","customerPhone":"16494648484","customerId":"bd493ffb276b4bdcadc4c7c37d332776","preparationId":"74debff96e0348e88f814b186df2d874","relatedData":"待审核","intentionArea":"","projectName":"丽江项目测试1","projectId":"a2a23fc831c844629a600d2b781570dd","status":"10"},{"date":"报备时间2019-10-19 17:46:18","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"多次","customerPhone":"15236952463","customerId":"d6688034b8b540068f7ba35d38d6eaa2","preparationId":"7a745bf2593c4e148cbb3ecb7e91fdbc","relatedData":"保护期剩余18天","intentionArea":"","projectName":"丽江项目测试1","projectId":"a2a23fc831c844629a600d2b781570dd","status":"10"},{"date":"报备时间2019-10-19 17:41:14","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"多次","customerPhone":"15236952463","customerId":"d6688034b8b540068f7ba35d38d6eaa2","preparationId":"734cb5f9b29743869ed8aa5819ce9aaf","relatedData":"待审核","intentionArea":"","projectName":"融创孔雀镇","projectId":"a7707dbc70714b7e967a25df7f8008dd","status":"10"},{"date":"报备时间2019-10-19 17:27:14","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"嗯ヽ(○^㉨^)ﾉ♪","customerPhone":"2737382882","customerId":"dee20c12ae9a4db6ae89f4212ffa8154","preparationId":"51d9c449326f4e95b306655c0f29587f","relatedData":"待审核","intentionArea":"","projectName":"丽江项目测试1","projectId":"a2a23fc831c844629a600d2b781570dd","status":"10"},{"date":"报备时间2019-10-19 17:22:27","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"嗯ヽ(○^㉨^)ﾉ♪","customerPhone":"2737382882","customerId":"dee20c12ae9a4db6ae89f4212ffa8154","preparationId":"ac9c9671a61145dfa8d93b926fb77b20","relatedData":"待审核","intentionArea":"","projectName":"融创孔雀镇","projectId":"a7707dbc70714b7e967a25df7f8008dd","status":"10"}]}
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
         * total : 5
         * rows : [{"date":"报备时间2019-10-19 17:48:51","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"川普","customerPhone":"16494648484","customerId":"bd493ffb276b4bdcadc4c7c37d332776","preparationId":"74debff96e0348e88f814b186df2d874","relatedData":"待审核","intentionArea":"","projectName":"丽江项目测试1","projectId":"a2a23fc831c844629a600d2b781570dd","status":"10"},{"date":"报备时间2019-10-19 17:46:18","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"多次","customerPhone":"15236952463","customerId":"d6688034b8b540068f7ba35d38d6eaa2","preparationId":"7a745bf2593c4e148cbb3ecb7e91fdbc","relatedData":"保护期剩余18天","intentionArea":"","projectName":"丽江项目测试1","projectId":"a2a23fc831c844629a600d2b781570dd","status":"10"},{"date":"报备时间2019-10-19 17:41:14","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"多次","customerPhone":"15236952463","customerId":"d6688034b8b540068f7ba35d38d6eaa2","preparationId":"734cb5f9b29743869ed8aa5819ce9aaf","relatedData":"待审核","intentionArea":"","projectName":"融创孔雀镇","projectId":"a7707dbc70714b7e967a25df7f8008dd","status":"10"},{"date":"报备时间2019-10-19 17:27:14","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"嗯ヽ(○^㉨^)ﾉ♪","customerPhone":"2737382882","customerId":"dee20c12ae9a4db6ae89f4212ffa8154","preparationId":"51d9c449326f4e95b306655c0f29587f","relatedData":"待审核","intentionArea":"","projectName":"丽江项目测试1","projectId":"a2a23fc831c844629a600d2b781570dd","status":"10"},{"date":"报备时间2019-10-19 17:22:27","agentId":"52b294a7098f4137a86f2ace1221f78f","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"经纪人：c001","customerName":"嗯ヽ(○^㉨^)ﾉ♪","customerPhone":"2737382882","customerId":"dee20c12ae9a4db6ae89f4212ffa8154","preparationId":"ac9c9671a61145dfa8d93b926fb77b20","relatedData":"待审核","intentionArea":"","projectName":"融创孔雀镇","projectId":"a7707dbc70714b7e967a25df7f8008dd","status":"10"}]
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
             * date : 报备时间2019-10-19 17:48:51
             * agentId : 52b294a7098f4137a86f2ace1221f78f
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * projectType : 3
             * agentName : 经纪人：c001
             * customerName : 川普
             * customerPhone : 16494648484
             * customerId : bd493ffb276b4bdcadc4c7c37d332776
             * preparationId : 74debff96e0348e88f814b186df2d874
             * relatedData : 待审核
             * intentionArea :
             * projectName : 丽江项目测试1
             * projectId : a2a23fc831c844629a600d2b781570dd
             * status : 10
             */

            private String date;
            private String agentId;
            private String customerImg;
            private String projectType;
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
