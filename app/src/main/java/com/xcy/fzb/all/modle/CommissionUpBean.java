package com.xcy.fzb.all.modle;

public class CommissionUpBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"totalAmount":"0.00","notAmount":"0.00","alreadyAmount":"0.00"}
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
         * totalAmount : 0.00
         * notAmount : 0.00
         * alreadyAmount : 0.00
         */

        private String totalAmount;
        private String notAmount;
        private String alreadyAmount;

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getNotAmount() {
            return notAmount;
        }

        public void setNotAmount(String notAmount) {
            this.notAmount = notAmount;
        }

        public String getAlreadyAmount() {
            return alreadyAmount;
        }

        public void setAlreadyAmount(String alreadyAmount) {
            this.alreadyAmount = alreadyAmount;
        }
    }
}