package com.xcy.fzb.all.modle;

public class HomeBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"tradeNumber":0,"invoiceMoney":"0","applyCount":0,"loseEfficacy":1,"reportNumber":0,"earnestMoneyNumber":0,"surplusMoney":"0","backMoney":"0","receivableMoney":"0","accessingNumber":0,"prodjectCount":1,"isIslandNumber":0,"auditNumber":1,"landingNumber":0}
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
         * tradeNumber : 0
         * invoiceMoney : 0
         * applyCount : 0
         * loseEfficacy : 1
         * reportNumber : 0
         * earnestMoneyNumber : 0
         * surplusMoney : 0
         * backMoney : 0
         * receivableMoney : 0
         * accessingNumber : 0
         * prodjectCount : 1
         * isIslandNumber : 0
         * auditNumber : 1
         * landingNumber : 0
         */

        private int tradeNumber;
        private String invoiceMoney;
        private int applyCount;
        private int loseEfficacy;
        private int reportNumber;
        private int earnestMoneyNumber;
        private String surplusMoney;
        private String backMoney;
        private String receivableMoney;
        private int accessingNumber;
        private int prodjectCount;
        private int isIslandNumber;
        private int auditNumber;
        private int landingNumber;

        public int getTradeNumber() {
            return tradeNumber;
        }

        public void setTradeNumber(int tradeNumber) {
            this.tradeNumber = tradeNumber;
        }

        public String getInvoiceMoney() {
            return invoiceMoney;
        }

        public void setInvoiceMoney(String invoiceMoney) {
            this.invoiceMoney = invoiceMoney;
        }

        public int getApplyCount() {
            return applyCount;
        }

        public void setApplyCount(int applyCount) {
            this.applyCount = applyCount;
        }

        public int getLoseEfficacy() {
            return loseEfficacy;
        }

        public void setLoseEfficacy(int loseEfficacy) {
            this.loseEfficacy = loseEfficacy;
        }

        public int getReportNumber() {
            return reportNumber;
        }

        public void setReportNumber(int reportNumber) {
            this.reportNumber = reportNumber;
        }

        public int getEarnestMoneyNumber() {
            return earnestMoneyNumber;
        }

        public void setEarnestMoneyNumber(int earnestMoneyNumber) {
            this.earnestMoneyNumber = earnestMoneyNumber;
        }

        public String getSurplusMoney() {
            return surplusMoney;
        }

        public void setSurplusMoney(String surplusMoney) {
            this.surplusMoney = surplusMoney;
        }

        public String getBackMoney() {
            return backMoney;
        }

        public void setBackMoney(String backMoney) {
            this.backMoney = backMoney;
        }

        public String getReceivableMoney() {
            return receivableMoney;
        }

        public void setReceivableMoney(String receivableMoney) {
            this.receivableMoney = receivableMoney;
        }

        public int getAccessingNumber() {
            return accessingNumber;
        }

        public void setAccessingNumber(int accessingNumber) {
            this.accessingNumber = accessingNumber;
        }

        public int getProdjectCount() {
            return prodjectCount;
        }

        public void setProdjectCount(int prodjectCount) {
            this.prodjectCount = prodjectCount;
        }

        public int getIsIslandNumber() {
            return isIslandNumber;
        }

        public void setIsIslandNumber(int isIslandNumber) {
            this.isIslandNumber = isIslandNumber;
        }

        public int getAuditNumber() {
            return auditNumber;
        }

        public void setAuditNumber(int auditNumber) {
            this.auditNumber = auditNumber;
        }

        public int getLandingNumber() {
            return landingNumber;
        }

        public void setLandingNumber(int landingNumber) {
            this.landingNumber = landingNumber;
        }
    }
}