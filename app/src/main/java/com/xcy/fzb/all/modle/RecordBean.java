package com.xcy.fzb.all.modle;

import java.util.List;

public class RecordBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":2,"rows":[{"id":"cdd5515b2cdf4d9ba1118297aff325df","remarks":"","createBy":{"id":"badb8e2d394d4841a8376437471970a5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","industry":"","manageFlag":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","teamParentId":"","commissionLevelId":"","identityStr":"","admin":false,"roleNames":""},"createDate":"2019-11-25 14:41:55","updateBy":"","updateDate":"","storeId":"5beeb664094b411386ab5c71474b4696","location":"117.162647,39.117214","address":"中国天津市南开区白堤路222号","img":"/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664089813.jpg|/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664095616.jpg|/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664100938.jpg","type":"2","effective":"0","start":"","end":"","storeName":"互联网","isMy":"1"},{"id":"83ef36e846444a4dbc9581d9b71052f6","remarks":"","createBy":{"id":"badb8e2d394d4841a8376437471970a5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","industry":"","manageFlag":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","teamParentId":"","commissionLevelId":"","identityStr":"","admin":false,"roleNames":""},"createDate":"2019-11-25 14:41:18","updateBy":"","updateDate":"","storeId":"5beeb664094b411386ab5c71474b4696","location":"117.162647,39.117214","address":"中国天津市南开区白堤路222号","img":"/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664073488.jpg","type":"1","effective":"1","start":"","end":"","storeName":"互联网","isMy":"1"}]}
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
         * rows : [{"id":"cdd5515b2cdf4d9ba1118297aff325df","remarks":"","createBy":{"id":"badb8e2d394d4841a8376437471970a5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","industry":"","manageFlag":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","teamParentId":"","commissionLevelId":"","identityStr":"","admin":false,"roleNames":""},"createDate":"2019-11-25 14:41:55","updateBy":"","updateDate":"","storeId":"5beeb664094b411386ab5c71474b4696","location":"117.162647,39.117214","address":"中国天津市南开区白堤路222号","img":"/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664089813.jpg|/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664095616.jpg|/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664100938.jpg","type":"2","effective":"0","start":"","end":"","storeName":"互联网","isMy":"1"},{"id":"83ef36e846444a4dbc9581d9b71052f6","remarks":"","createBy":{"id":"badb8e2d394d4841a8376437471970a5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","industry":"","manageFlag":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","teamParentId":"","commissionLevelId":"","identityStr":"","admin":false,"roleNames":""},"createDate":"2019-11-25 14:41:18","updateBy":"","updateDate":"","storeId":"5beeb664094b411386ab5c71474b4696","location":"117.162647,39.117214","address":"中国天津市南开区白堤路222号","img":"/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664073488.jpg","type":"1","effective":"1","start":"","end":"","storeName":"互联网","isMy":"1"}]
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
             * id : cdd5515b2cdf4d9ba1118297aff325df
             * remarks :
             * createBy : {"id":"badb8e2d394d4841a8376437471970a5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","industry":"","manageFlag":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","teamParentId":"","commissionLevelId":"","identityStr":"","admin":false,"roleNames":""}
             * createDate : 2019-11-25 14:41:55
             * updateBy :
             * updateDate :
             * storeId : 5beeb664094b411386ab5c71474b4696
             * location : 117.162647,39.117214
             * address : 中国天津市南开区白堤路222号
             * img : /fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664089813.jpg|/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664095616.jpg|/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/feed/2019/11/1574664100938.jpg
             * type : 2
             * effective : 0
             * start :
             * end :
             * storeName : 互联网
             * isMy : 1
             */

            private String id;
            private String remarks;
            private CreateByBean createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String storeId;
            private String location;
            private String address;
            private String img;
            private String type;
            private String effective;
            private String start;
            private String end;
            private String storeName;
            private String isMy;

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

            public CreateByBean getCreateBy() {
                return createBy;
            }

            public void setCreateBy(CreateByBean createBy) {
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

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getEffective() {
                return effective;
            }

            public void setEffective(String effective) {
                this.effective = effective;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getIsMy() {
                return isMy;
            }

            public void setIsMy(String isMy) {
                this.isMy = isMy;
            }

            public static class CreateByBean {
                /**
                 * id : badb8e2d394d4841a8376437471970a5
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * company :
                 * office :
                 * post :
                 * loginName :
                 * no :
                 * name :
                 * email :
                 * phone :
                 * oldPhone :
                 * mobile :
                 * loginIp :
                 * loginDate :
                 * loginFlag : 1
                 * photo : /fangfang/static/common/images/flat-avatar.png
                 * qrCode :
                 * oldLoginName :
                 * newPassword :
                 * sign :
                 * oldLoginIp :
                 * oldLoginDate :
                 * wechatOpenid :
                 * wechatData :
                 * industry :
                 * manageFlag :
                 * role :
                 * sex :
                 * province :
                 * city :
                 * county :
                 * identity :
                 * brokerId :
                 * storeId :
                 * companyManage :
                 * storeManage :
                 * classics : 0
                 * team :
                 * layerTeamVo :
                 * teamParentId :
                 * commissionLevelId :
                 * identityStr :
                 * admin : false
                 * roleNames :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String company;
                private String office;
                private String post;
                private String loginName;
                private String no;
                private String name;
                private String email;
                private String phone;
                private String oldPhone;
                private String mobile;
                private String loginIp;
                private String loginDate;
                private String loginFlag;
                private String photo;
                private String qrCode;
                private String oldLoginName;
                private String newPassword;
                private String sign;
                private String oldLoginIp;
                private String oldLoginDate;
                private String wechatOpenid;
                private String wechatData;
                private String industry;
                private String manageFlag;
                private String role;
                private String sex;
                private String province;
                private String city;
                private String county;
                private String identity;
                private String brokerId;
                private String storeId;
                private String companyManage;
                private String storeManage;
                private int classics;
                private String team;
                private String layerTeamVo;
                private String teamParentId;
                private String commissionLevelId;
                private String identityStr;
                private boolean admin;
                private String roleNames;

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

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getOffice() {
                    return office;
                }

                public void setOffice(String office) {
                    this.office = office;
                }

                public String getPost() {
                    return post;
                }

                public void setPost(String post) {
                    this.post = post;
                }

                public String getLoginName() {
                    return loginName;
                }

                public void setLoginName(String loginName) {
                    this.loginName = loginName;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getOldPhone() {
                    return oldPhone;
                }

                public void setOldPhone(String oldPhone) {
                    this.oldPhone = oldPhone;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getLoginIp() {
                    return loginIp;
                }

                public void setLoginIp(String loginIp) {
                    this.loginIp = loginIp;
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

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public String getQrCode() {
                    return qrCode;
                }

                public void setQrCode(String qrCode) {
                    this.qrCode = qrCode;
                }

                public String getOldLoginName() {
                    return oldLoginName;
                }

                public void setOldLoginName(String oldLoginName) {
                    this.oldLoginName = oldLoginName;
                }

                public String getNewPassword() {
                    return newPassword;
                }

                public void setNewPassword(String newPassword) {
                    this.newPassword = newPassword;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public String getOldLoginIp() {
                    return oldLoginIp;
                }

                public void setOldLoginIp(String oldLoginIp) {
                    this.oldLoginIp = oldLoginIp;
                }

                public String getOldLoginDate() {
                    return oldLoginDate;
                }

                public void setOldLoginDate(String oldLoginDate) {
                    this.oldLoginDate = oldLoginDate;
                }

                public String getWechatOpenid() {
                    return wechatOpenid;
                }

                public void setWechatOpenid(String wechatOpenid) {
                    this.wechatOpenid = wechatOpenid;
                }

                public String getWechatData() {
                    return wechatData;
                }

                public void setWechatData(String wechatData) {
                    this.wechatData = wechatData;
                }

                public String getIndustry() {
                    return industry;
                }

                public void setIndustry(String industry) {
                    this.industry = industry;
                }

                public String getManageFlag() {
                    return manageFlag;
                }

                public void setManageFlag(String manageFlag) {
                    this.manageFlag = manageFlag;
                }

                public String getRole() {
                    return role;
                }

                public void setRole(String role) {
                    this.role = role;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public String getIdentity() {
                    return identity;
                }

                public void setIdentity(String identity) {
                    this.identity = identity;
                }

                public String getBrokerId() {
                    return brokerId;
                }

                public void setBrokerId(String brokerId) {
                    this.brokerId = brokerId;
                }

                public String getStoreId() {
                    return storeId;
                }

                public void setStoreId(String storeId) {
                    this.storeId = storeId;
                }

                public String getCompanyManage() {
                    return companyManage;
                }

                public void setCompanyManage(String companyManage) {
                    this.companyManage = companyManage;
                }

                public String getStoreManage() {
                    return storeManage;
                }

                public void setStoreManage(String storeManage) {
                    this.storeManage = storeManage;
                }

                public int getClassics() {
                    return classics;
                }

                public void setClassics(int classics) {
                    this.classics = classics;
                }

                public String getTeam() {
                    return team;
                }

                public void setTeam(String team) {
                    this.team = team;
                }

                public String getLayerTeamVo() {
                    return layerTeamVo;
                }

                public void setLayerTeamVo(String layerTeamVo) {
                    this.layerTeamVo = layerTeamVo;
                }

                public String getTeamParentId() {
                    return teamParentId;
                }

                public void setTeamParentId(String teamParentId) {
                    this.teamParentId = teamParentId;
                }

                public String getCommissionLevelId() {
                    return commissionLevelId;
                }

                public void setCommissionLevelId(String commissionLevelId) {
                    this.commissionLevelId = commissionLevelId;
                }

                public String getIdentityStr() {
                    return identityStr;
                }

                public void setIdentityStr(String identityStr) {
                    this.identityStr = identityStr;
                }

                public boolean isAdmin() {
                    return admin;
                }

                public void setAdmin(boolean admin) {
                    this.admin = admin;
                }

                public String getRoleNames() {
                    return roleNames;
                }

                public void setRoleNames(String roleNames) {
                    this.roleNames = roleNames;
                }
            }
        }
    }
}
