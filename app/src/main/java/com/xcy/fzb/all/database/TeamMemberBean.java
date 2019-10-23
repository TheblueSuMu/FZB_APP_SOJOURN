package com.xcy.fzb.all.database;

import java.util.List;

public class TeamMemberBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":11,"rows":[{"id":"be31913f8a434d13aa0f84246103098d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"gw2","phone":"18235195023","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"62","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6,7dc90605967c44489e0f8b160d2bc2d6,e8c0c21b41ec448ab02ca8da9667996c","user":"","leaderName":"测试团长1","saleName":"测试团长1","saleNum":"","counselorNum":"","ratioName":"顾问一级"},{"id":"8d4a3db6bf99486cb4f451c0d5c1ce31","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"gw1","phone":"18235195900","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"62","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6,7dc90605967c44489e0f8b160d2bc2d6,ca77d0b221844266a07dd60fb4ffd36f","user":"","leaderName":"测试团长1","saleName":"测试团长1","saleNum":"","counselorNum":"","ratioName":"顾问一级"},{"id":"b99979c0d13e40e993a7ae8a98864e1f","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"飞飞哥","phone":"1760103003","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"1463077da2584dd9ab227d2792c45381","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"顾问a","phone":"14836542985","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"174b3b88e3b54c7db1f50372e6ff4008","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈16","phone":"14698887569","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"19684396ce02415ca2903d42fd3fcddd","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试6","phone":"234565465255","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"5306e8b81ffc4166879f35834c9f1cb5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试5","phone":"234565465222","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"2019-05-30 15:22:34","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"0cae78b3637c48b79bcb60402b50cfb1","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试3","phone":"23456546533","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"c8d66eab81504f9ca73256818dabfd44","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试3","phone":"23456546548","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"2019-05-22 19:48:34","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"fa6b11bb9bcc4bbfb2f396a7b4396ac5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试2","phone":"23456546543","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"2019-06-15 22:38:49","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""}]}
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
         * total : 11
         * rows : [{"id":"be31913f8a434d13aa0f84246103098d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"gw2","phone":"18235195023","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"62","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6,7dc90605967c44489e0f8b160d2bc2d6,e8c0c21b41ec448ab02ca8da9667996c","user":"","leaderName":"测试团长1","saleName":"测试团长1","saleNum":"","counselorNum":"","ratioName":"顾问一级"},{"id":"8d4a3db6bf99486cb4f451c0d5c1ce31","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"gw1","phone":"18235195900","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"62","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6,7dc90605967c44489e0f8b160d2bc2d6,ca77d0b221844266a07dd60fb4ffd36f","user":"","leaderName":"测试团长1","saleName":"测试团长1","saleNum":"","counselorNum":"","ratioName":"顾问一级"},{"id":"b99979c0d13e40e993a7ae8a98864e1f","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"飞飞哥","phone":"1760103003","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"1463077da2584dd9ab227d2792c45381","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"顾问a","phone":"14836542985","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"174b3b88e3b54c7db1f50372e6ff4008","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈16","phone":"14698887569","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"19684396ce02415ca2903d42fd3fcddd","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试6","phone":"234565465255","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"5306e8b81ffc4166879f35834c9f1cb5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试5","phone":"234565465222","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"2019-05-30 15:22:34","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"0cae78b3637c48b79bcb60402b50cfb1","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试3","phone":"23456546533","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"c8d66eab81504f9ca73256818dabfd44","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试3","phone":"23456546548","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"2019-05-22 19:48:34","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""},{"id":"fa6b11bb9bcc4bbfb2f396a7b4396ac5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"圈层测试2","phone":"23456546543","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"6","loginDate":"2019-06-15 22:38:49","loginFlag":"1","type":"3","searcName":"","parentId":"","parentIds":"7dc90605967c44489e0f8b160d2bc2d6","user":"","leaderName":"","saleName":"","saleNum":"","counselorNum":"","ratioName":""}]
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
             * id : be31913f8a434d13aa0f84246103098d
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * name : gw2
             * phone : 18235195023
             * photo : /fangfang/static/common/images/flat-avatar.png
             * identity : 62
             * loginDate :
             * loginFlag : 1
             * type : 3
             * searcName :
             * parentId :
             * parentIds : 7dc90605967c44489e0f8b160d2bc2d6,7dc90605967c44489e0f8b160d2bc2d6,e8c0c21b41ec448ab02ca8da9667996c
             * user :
             * leaderName : 测试团长1
             * saleName : 测试团长1
             * saleNum :
             * counselorNum :
             * ratioName : 顾问一级
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
            private String parentIds;
            private String user;
            private String leaderName;
            private String saleName;
            private String saleNum;
            private String counselorNum;
            private String ratioName;

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
        }
    }
}
