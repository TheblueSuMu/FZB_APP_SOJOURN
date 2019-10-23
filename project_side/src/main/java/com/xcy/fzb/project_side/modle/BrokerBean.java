package com.xcy.fzb.project_side.modle;

import java.util.List;

public class BrokerBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"id":"f8f3c3e8099040cc80860dca06850e76","remarks":"","createBy":"","createDate":"2019-07-29 10:48:28","updateBy":"","updateDate":"2019-08-02 13:53:28","datePicker":"","startDate":"2019-07-01","endDate":"2019-12-31","mainTitle":"住宅佣金","type":"1","percent":6,"amount":"","contractPercent":"","contractAmount":"","isSeconds":"0","secondsAmount":"","executeState":"","project":"","onlineState":"1","isProjectShow":1,"percentStr":"6.0","contractPercentStr":"","currentDate":"","commissionFormat":"总房款的6.0%","secondsFormat":"无秒结","teams":"","teamNames":""}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : f8f3c3e8099040cc80860dca06850e76
         * remarks :
         * createBy :
         * createDate : 2019-07-29 10:48:28
         * updateBy :
         * updateDate : 2019-08-02 13:53:28
         * datePicker :
         * startDate : 2019-07-01
         * endDate : 2019-12-31
         * mainTitle : 住宅佣金
         * type : 1
         * percent : 6
         * amount :
         * contractPercent :
         * contractAmount :
         * isSeconds : 0
         * secondsAmount :
         * executeState :
         * project :
         * onlineState : 1
         * isProjectShow : 1
         * percentStr : 6.0
         * contractPercentStr :
         * currentDate :
         * commissionFormat : 总房款的6.0%
         * secondsFormat : 无秒结
         * teams :
         * teamNames :
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String datePicker;
        private String startDate;
        private String endDate;
        private String mainTitle;
        private String type;
        private int percent;
        private String amount;
        private String contractPercent;
        private String contractAmount;
        private String isSeconds;
        private String secondsAmount;
        private String executeState;
        private String project;
        private String onlineState;
        private int isProjectShow;
        private String percentStr;
        private String contractPercentStr;
        private String currentDate;
        private String commissionFormat;
        private String secondsFormat;
        private String teams;
        private String teamNames;

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

        public String getDatePicker() {
            return datePicker;
        }

        public void setDatePicker(String datePicker) {
            this.datePicker = datePicker;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getMainTitle() {
            return mainTitle;
        }

        public void setMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getContractPercent() {
            return contractPercent;
        }

        public void setContractPercent(String contractPercent) {
            this.contractPercent = contractPercent;
        }

        public String getContractAmount() {
            return contractAmount;
        }

        public void setContractAmount(String contractAmount) {
            this.contractAmount = contractAmount;
        }

        public String getIsSeconds() {
            return isSeconds;
        }

        public void setIsSeconds(String isSeconds) {
            this.isSeconds = isSeconds;
        }

        public String getSecondsAmount() {
            return secondsAmount;
        }

        public void setSecondsAmount(String secondsAmount) {
            this.secondsAmount = secondsAmount;
        }

        public String getExecuteState() {
            return executeState;
        }

        public void setExecuteState(String executeState) {
            this.executeState = executeState;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getOnlineState() {
            return onlineState;
        }

        public void setOnlineState(String onlineState) {
            this.onlineState = onlineState;
        }

        public int getIsProjectShow() {
            return isProjectShow;
        }

        public void setIsProjectShow(int isProjectShow) {
            this.isProjectShow = isProjectShow;
        }

        public String getPercentStr() {
            return percentStr;
        }

        public void setPercentStr(String percentStr) {
            this.percentStr = percentStr;
        }

        public String getContractPercentStr() {
            return contractPercentStr;
        }

        public void setContractPercentStr(String contractPercentStr) {
            this.contractPercentStr = contractPercentStr;
        }

        public String getCurrentDate() {
            return currentDate;
        }

        public void setCurrentDate(String currentDate) {
            this.currentDate = currentDate;
        }

        public String getCommissionFormat() {
            return commissionFormat;
        }

        public void setCommissionFormat(String commissionFormat) {
            this.commissionFormat = commissionFormat;
        }

        public String getSecondsFormat() {
            return secondsFormat;
        }

        public void setSecondsFormat(String secondsFormat) {
            this.secondsFormat = secondsFormat;
        }

        public String getTeams() {
            return teams;
        }

        public void setTeams(String teams) {
            this.teams = teams;
        }

        public String getTeamNames() {
            return teamNames;
        }

        public void setTeamNames(String teamNames) {
            this.teamNames = teamNames;
        }
    }
}
