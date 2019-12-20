package com.xcy.fzb.all.database;

import java.util.List;

public class TeamMemberBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":3,"rows":[{"id":"ced1c672062f4d3a8c417d5515e67be0","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"玉帛","phone":"15807854546","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","loginFlag":"1","type":"2","searcName":"","parentId":"6dbcb070374a416da1a96171a8000daf","teamLeaderId":"","parentIds":"6dbcb070374a416da1a96171a8000daf","user":"","leaderName":"王楠","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"销售一级","levelName":""},{"id":"d0857fa88fb443dfa1dda2a610feae7a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"花花","phone":"13844365565","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","loginFlag":"0","type":"2","searcName":"","parentId":"6dbcb070374a416da1a96171a8000daf","teamLeaderId":"","parentIds":"6dbcb070374a416da1a96171a8000daf","user":"","leaderName":"王楠","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"销售四级","levelName":""},{"id":"5804480c28674712af48f13a63195ad8","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"王俊凯","phone":"13080030058","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"2019-12-03 10:32:51","loginFlag":"1","type":"2","searcName":"","parentId":"6dbcb070374a416da1a96171a8000daf","teamLeaderId":"","parentIds":"6dbcb070374a416da1a96171a8000daf","user":"","leaderName":"王楠","saleName":"","saleNum":"0","counselorNum":"1","ratioName":"销售四级","levelName":""}]}
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
         * rows : [{"id":"ced1c672062f4d3a8c417d5515e67be0","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"玉帛","phone":"15807854546","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","loginFlag":"1","type":"2","searcName":"","parentId":"6dbcb070374a416da1a96171a8000daf","teamLeaderId":"","parentIds":"6dbcb070374a416da1a96171a8000daf","user":"","leaderName":"王楠","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"销售一级","levelName":""},{"id":"d0857fa88fb443dfa1dda2a610feae7a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"花花","phone":"13844365565","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","loginFlag":"0","type":"2","searcName":"","parentId":"6dbcb070374a416da1a96171a8000daf","teamLeaderId":"","parentIds":"6dbcb070374a416da1a96171a8000daf","user":"","leaderName":"王楠","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"销售四级","levelName":""},{"id":"5804480c28674712af48f13a63195ad8","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"王俊凯","phone":"13080030058","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"2019-12-03 10:32:51","loginFlag":"1","type":"2","searcName":"","parentId":"6dbcb070374a416da1a96171a8000daf","teamLeaderId":"","parentIds":"6dbcb070374a416da1a96171a8000daf","user":"","leaderName":"王楠","saleName":"","saleNum":"0","counselorNum":"1","ratioName":"销售四级","levelName":""}]
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
             * id : ced1c672062f4d3a8c417d5515e67be0
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * name : 玉帛
             * phone : 15807854546
             * photo : /fangfang/static/common/images/flat-avatar.png
             * identity : 61
             * loginDate :
             * loginFlag : 1
             * type : 2
             * searcName :
             * parentId : 6dbcb070374a416da1a96171a8000daf
             * teamLeaderId :
             * parentIds : 6dbcb070374a416da1a96171a8000daf
             * user :
             * leaderName : 王楠
             * saleName :
             * saleNum : 0
             * counselorNum : 0
             * ratioName : 销售一级
             * levelName :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String name;
            private String phone;
            private String photo;
            private String identity;
            private String loginDate;
            private String loginFlag;
            private String type;
            private String searcName;
            private String parentId;
            private String teamLeaderId;
            private String parentIds;
            private String user;
            private String leaderName;
            private String saleName;
            private String saleNum;
            private String counselorNum;
            private String ratioName;
            private String levelName;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getIdentity() {
                return identity;
            }

            public void setIdentity(String identity) {
                this.identity = identity;
            }

            public String getLoginDate() {
                return loginDate;
            }

            public void setLoginDate(String loginDate) {
                this.loginDate = loginDate;
            }

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSearcName() {
                return searcName;
            }

            public void setSearcName(String searcName) {
                this.searcName = searcName;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getTeamLeaderId() {
                return teamLeaderId;
            }

            public void setTeamLeaderId(String teamLeaderId) {
                this.teamLeaderId = teamLeaderId;
            }

            public String getParentIds() {
                return parentIds;
            }

            public void setParentIds(String parentIds) {
                this.parentIds = parentIds;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getLeaderName() {
                return leaderName;
            }

            public void setLeaderName(String leaderName) {
                this.leaderName = leaderName;
            }

            public String getSaleName() {
                return saleName;
            }

            public void setSaleName(String saleName) {
                this.saleName = saleName;
            }

            public String getSaleNum() {
                return saleNum;
            }

            public void setSaleNum(String saleNum) {
                this.saleNum = saleNum;
            }

            public String getCounselorNum() {
                return counselorNum;
            }

            public void setCounselorNum(String counselorNum) {
                this.counselorNum = counselorNum;
            }

            public String getRatioName() {
                return ratioName;
            }

            public void setRatioName(String ratioName) {
                this.ratioName = ratioName;
            }

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }
        }
    }
}
