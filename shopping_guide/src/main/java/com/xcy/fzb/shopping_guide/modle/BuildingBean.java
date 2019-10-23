package com.xcy.fzb.shopping_guide.modle;

import java.util.List;

public class BuildingBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"id":"68fe2d226d8f4b8299ebaf06367ae006","remarks":"","createBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"createDate":"2019-08-19 16:54:56","updateBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"updateDate":"2019-08-19 16:54:56","project":{"id":"a7707dbc70714b7e967a25df7f8008dd","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"融创孔雀镇","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"buildingName":"A1地块2栋，A2地块10栋栋","saleStatus":"在售","elevator":"","family":"","pliesNumber":"","elementNumber":"","buildTime":"","checkInTime":"2020-12-31","fitmentState":"精装","fitmentStandard":"","fitmentStandardStr":"","houseType":"33㎡(1室1厅1卫)","houseInfoList":[{"area":"33m2","slideImgList":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerFamilyInfomation/2019/8/33_WPS图片.jpg","orientation":"南北","floorPlan":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerFamilyInfomation/2019/8/33_WPS图片.jpg","model":"1室1厅1卫"}]}]
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
         * id : 68fe2d226d8f4b8299ebaf06367ae006
         * remarks :
         * createBy : {"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""}
         * createDate : 2019-08-19 16:54:56
         * updateBy : {"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""}
         * updateDate : 2019-08-19 16:54:56
         * project : {"id":"a7707dbc70714b7e967a25df7f8008dd","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"融创孔雀镇","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""}
         * buildingName : A1地块2栋，A2地块10栋栋
         * saleStatus : 在售
         * elevator :
         * family :
         * pliesNumber :
         * elementNumber :
         * buildTime :
         * checkInTime : 2020-12-31
         * fitmentState : 精装
         * fitmentStandard :
         * fitmentStandardStr :
         * houseType : 33㎡(1室1厅1卫)
         * houseInfoList : [{"area":"33m2","slideImgList":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerFamilyInfomation/2019/8/33_WPS图片.jpg","orientation":"南北","floorPlan":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerFamilyInfomation/2019/8/33_WPS图片.jpg","model":"1室1厅1卫"}]
         */

        private String id;
        private String remarks;
        private CreateByBean createBy;
        private String createDate;
        private UpdateByBean updateBy;
        private String updateDate;
        private ProjectBean project;
        private String buildingName;
        private String saleStatus;
        private String elevator;
        private String family;
        private String pliesNumber;
        private String elementNumber;
        private String buildTime;
        private String checkInTime;
        private String fitmentState;
        private String fitmentStandard;
        private String fitmentStandardStr;
        private String houseType;
        private List<HouseInfoListBean> houseInfoList;

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

        public UpdateByBean getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(UpdateByBean updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public ProjectBean getProject() {
            return project;
        }

        public void setProject(ProjectBean project) {
            this.project = project;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getSaleStatus() {
            return saleStatus;
        }

        public void setSaleStatus(String saleStatus) {
            this.saleStatus = saleStatus;
        }

        public String getElevator() {
            return elevator;
        }

        public void setElevator(String elevator) {
            this.elevator = elevator;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        public String getPliesNumber() {
            return pliesNumber;
        }

        public void setPliesNumber(String pliesNumber) {
            this.pliesNumber = pliesNumber;
        }

        public String getElementNumber() {
            return elementNumber;
        }

        public void setElementNumber(String elementNumber) {
            this.elementNumber = elementNumber;
        }

        public String getBuildTime() {
            return buildTime;
        }

        public void setBuildTime(String buildTime) {
            this.buildTime = buildTime;
        }

        public String getCheckInTime() {
            return checkInTime;
        }

        public void setCheckInTime(String checkInTime) {
            this.checkInTime = checkInTime;
        }

        public String getFitmentState() {
            return fitmentState;
        }

        public void setFitmentState(String fitmentState) {
            this.fitmentState = fitmentState;
        }

        public String getFitmentStandard() {
            return fitmentStandard;
        }

        public void setFitmentStandard(String fitmentStandard) {
            this.fitmentStandard = fitmentStandard;
        }

        public String getFitmentStandardStr() {
            return fitmentStandardStr;
        }

        public void setFitmentStandardStr(String fitmentStandardStr) {
            this.fitmentStandardStr = fitmentStandardStr;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public List<HouseInfoListBean> getHouseInfoList() {
            return houseInfoList;
        }

        public void setHouseInfoList(List<HouseInfoListBean> houseInfoList) {
            this.houseInfoList = houseInfoList;
        }

        public static class CreateByBean {
            /**
             * id : 3c37d25396a940f9b784b4c180f7db37
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

        public static class UpdateByBean {
            /**
             * id : 3c37d25396a940f9b784b4c180f7db37
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

        public static class ProjectBean {
            /**
             * id : a7707dbc70714b7e967a25df7f8008dd
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * projectName : 融创孔雀镇
             * developer :
             * houseCompany :
             * cityCompany :
             * projectType :
             * attache :
             * cooperationState :
             * onlineState :
             * belongsArea :
             * address :
             * location :
             * totalBuildings :
             * hot :
             * sort :
             * remark :
             * province :
             * city :
             * region :
             * forwardingAmount :
             * reportAmount :
             * nation :
             * hotSort :
             * projectImg :
             * projectListImg :
             * buildingImg :
             * browseNum :
             * awardRules :
             * commissionRules :
             * searchName :
             * collectionNum :
             * productTypeId :
             * productTypeSize :
             * amountIncentiveId :
             * cityName :
             * haveInformation :
             * projectTypeName :
             * isgroup :
             * groupNum :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String projectName;
            private String developer;
            private String houseCompany;
            private String cityCompany;
            private String projectType;
            private String attache;
            private String cooperationState;
            private String onlineState;
            private String belongsArea;
            private String address;
            private String location;
            private String totalBuildings;
            private String hot;
            private String sort;
            private String remark;
            private String province;
            private String city;
            private String region;
            private String forwardingAmount;
            private String reportAmount;
            private String nation;
            private String hotSort;
            private String projectImg;
            private String projectListImg;
            private String buildingImg;
            private String browseNum;
            private String awardRules;
            private String commissionRules;
            private String searchName;
            private String collectionNum;
            private String productTypeId;
            private String productTypeSize;
            private String amountIncentiveId;
            private String cityName;
            private String haveInformation;
            private String projectTypeName;
            private String isgroup;
            private String groupNum;

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

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getDeveloper() {
                return developer;
            }

            public void setDeveloper(String developer) {
                this.developer = developer;
            }

            public String getHouseCompany() {
                return houseCompany;
            }

            public void setHouseCompany(String houseCompany) {
                this.houseCompany = houseCompany;
            }

            public String getCityCompany() {
                return cityCompany;
            }

            public void setCityCompany(String cityCompany) {
                this.cityCompany = cityCompany;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getAttache() {
                return attache;
            }

            public void setAttache(String attache) {
                this.attache = attache;
            }

            public String getCooperationState() {
                return cooperationState;
            }

            public void setCooperationState(String cooperationState) {
                this.cooperationState = cooperationState;
            }

            public String getOnlineState() {
                return onlineState;
            }

            public void setOnlineState(String onlineState) {
                this.onlineState = onlineState;
            }

            public String getBelongsArea() {
                return belongsArea;
            }

            public void setBelongsArea(String belongsArea) {
                this.belongsArea = belongsArea;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getTotalBuildings() {
                return totalBuildings;
            }

            public void setTotalBuildings(String totalBuildings) {
                this.totalBuildings = totalBuildings;
            }

            public String getHot() {
                return hot;
            }

            public void setHot(String hot) {
                this.hot = hot;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
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

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getForwardingAmount() {
                return forwardingAmount;
            }

            public void setForwardingAmount(String forwardingAmount) {
                this.forwardingAmount = forwardingAmount;
            }

            public String getReportAmount() {
                return reportAmount;
            }

            public void setReportAmount(String reportAmount) {
                this.reportAmount = reportAmount;
            }

            public String getNation() {
                return nation;
            }

            public void setNation(String nation) {
                this.nation = nation;
            }

            public String getHotSort() {
                return hotSort;
            }

            public void setHotSort(String hotSort) {
                this.hotSort = hotSort;
            }

            public String getProjectImg() {
                return projectImg;
            }

            public void setProjectImg(String projectImg) {
                this.projectImg = projectImg;
            }

            public String getProjectListImg() {
                return projectListImg;
            }

            public void setProjectListImg(String projectListImg) {
                this.projectListImg = projectListImg;
            }

            public String getBuildingImg() {
                return buildingImg;
            }

            public void setBuildingImg(String buildingImg) {
                this.buildingImg = buildingImg;
            }

            public String getBrowseNum() {
                return browseNum;
            }

            public void setBrowseNum(String browseNum) {
                this.browseNum = browseNum;
            }

            public String getAwardRules() {
                return awardRules;
            }

            public void setAwardRules(String awardRules) {
                this.awardRules = awardRules;
            }

            public String getCommissionRules() {
                return commissionRules;
            }

            public void setCommissionRules(String commissionRules) {
                this.commissionRules = commissionRules;
            }

            public String getSearchName() {
                return searchName;
            }

            public void setSearchName(String searchName) {
                this.searchName = searchName;
            }

            public String getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(String collectionNum) {
                this.collectionNum = collectionNum;
            }

            public String getProductTypeId() {
                return productTypeId;
            }

            public void setProductTypeId(String productTypeId) {
                this.productTypeId = productTypeId;
            }

            public String getProductTypeSize() {
                return productTypeSize;
            }

            public void setProductTypeSize(String productTypeSize) {
                this.productTypeSize = productTypeSize;
            }

            public String getAmountIncentiveId() {
                return amountIncentiveId;
            }

            public void setAmountIncentiveId(String amountIncentiveId) {
                this.amountIncentiveId = amountIncentiveId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getHaveInformation() {
                return haveInformation;
            }

            public void setHaveInformation(String haveInformation) {
                this.haveInformation = haveInformation;
            }

            public String getProjectTypeName() {
                return projectTypeName;
            }

            public void setProjectTypeName(String projectTypeName) {
                this.projectTypeName = projectTypeName;
            }

            public String getIsgroup() {
                return isgroup;
            }

            public void setIsgroup(String isgroup) {
                this.isgroup = isgroup;
            }

            public String getGroupNum() {
                return groupNum;
            }

            public void setGroupNum(String groupNum) {
                this.groupNum = groupNum;
            }
        }

        public static class HouseInfoListBean {
            /**
             * area : 33m2
             * slideImgList : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerFamilyInfomation/2019/8/33_WPS图片.jpg
             * orientation : 南北
             * floorPlan : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerFamilyInfomation/2019/8/33_WPS图片.jpg
             * model : 1室1厅1卫
             */

            private String area;
            private String slideImgList;
            private String orientation;
            private String floorPlan;
            private String model;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getSlideImgList() {
                return slideImgList;
            }

            public void setSlideImgList(String slideImgList) {
                this.slideImgList = slideImgList;
            }

            public String getOrientation() {
                return orientation;
            }

            public void setOrientation(String orientation) {
                this.orientation = orientation;
            }

            public String getFloorPlan() {
                return floorPlan;
            }

            public void setFloorPlan(String floorPlan) {
                this.floorPlan = floorPlan;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }
        }
    }
}
