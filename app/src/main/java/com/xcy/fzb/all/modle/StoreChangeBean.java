package com.xcy.fzb.all.modle;

public class StoreChangeBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"isstorePerson":1,"storePerson":{"id":"66d293e451db4400979d8b3bac0b167f","remarks":"","createBy":"","createDate":"2019-10-26 16:43:15","updateBy":"","updateDate":"2019-10-30 10:00:45","company":"","office":"","post":"","loginName":"nyn","newPassword":"","oldLoginName":"","no":"","name":"你呀你","position":"","directLeaderId":"","email":"","phone":"18655221470","oldPhone":"","mobile":"","photo":"","loginIp":"","loginDate":"","loginFlag":"1","qrcode":"","sign":"","sex":"","province":"湖南省","city":"长沙市","county":"长沙县","identity":"3","companyManage":{"id":"71bfda821c1841c182ce32568689bc78","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","companyCityName":"","companyName":"娇娇公司","area":"","zoneId":"","location":"","address":"","companyInfo":"","attacheId":"","managerId":"","directorId":"","flag":"","officeId":"","userId":"","userName":"","phone":"","loginName":"","password":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""},"storeManage":{"id":"7233ef790059406881e8121622bbf979","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company1":"","company":"","storeName":"娇娇设置的门店","area":"","zoneId":"","location":"","address":"","storeInfo":"","attache":"","managerId":"","directorId":"","flag":"","lProvince":"","lCity":"","lRegion":"","officeId":"","storeRise":"","storeImg":"","reason":"","userId":"","storeNum":"","userName":"","phone":"","loginName":"","password":"","attacheName":"","attachePhone":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""},"borkerId":"","storeId":"7233ef790059406881e8121622bbf979","role":"","isCircle":"","parent":"","industry":"","type":"","teamLeader":"","level":"","teamRatio":"","manager":"","startTime":"","endTime":"","startTime1":"","endTime1":"","manageFlag":"","commissionLevelId":"","admin":false,"roleNames":""},"storeManage":{"id":"7233ef790059406881e8121622bbf979","remarks":"","createBy":"","createDate":"2019-10-26 15:32:46","updateBy":"","updateDate":"2019-11-01 11:34:27","company1":"","company":{"id":"946ee5b7800443f4a6379e4df42712b9","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","companyCityName":"","companyName":"大大经纪公司","area":"","zoneId":"","location":"","address":"","companyInfo":"","attacheId":"","managerId":"","directorId":"","flag":"","officeId":"","userId":"","userName":"","phone":"","loginName":"","password":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""},"storeName":"娇娇设置的门店","area":"湖南省/长沙市/长沙县","zoneId":"","location":"","address":"长泾大陆街道处","storeInfo":"","attache":"","managerId":"","directorId":"","flag":"1","lProvince":"湖南省","lCity":"长沙市","lRegion":"长沙县","officeId":"c241db93cbd247f5a8aadf501806b56a","storeRise":"/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/storerise_20191029143726/2019/10/file.png","storeImg":"/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/zhuanyuan/2019/10/Ask2(10).png","reason":"","userId":"66d293e451db4400979d8b3bac0b167f","storeNum":"0000534","userName":"你呀你","phone":"18655221470","loginName":"nyn","password":"","attacheName":"叫我亲爱的","attachePhone":"18513271146","startTime":"","endTime":"","lprovince":"湖南省","lcity":"长沙市","lregion":"长沙县"}}
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
         * isstorePerson : 1
         * storePerson : {"id":"66d293e451db4400979d8b3bac0b167f","remarks":"","createBy":"","createDate":"2019-10-26 16:43:15","updateBy":"","updateDate":"2019-10-30 10:00:45","company":"","office":"","post":"","loginName":"nyn","newPassword":"","oldLoginName":"","no":"","name":"你呀你","position":"","directLeaderId":"","email":"","phone":"18655221470","oldPhone":"","mobile":"","photo":"","loginIp":"","loginDate":"","loginFlag":"1","qrcode":"","sign":"","sex":"","province":"湖南省","city":"长沙市","county":"长沙县","identity":"3","companyManage":{"id":"71bfda821c1841c182ce32568689bc78","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","companyCityName":"","companyName":"娇娇公司","area":"","zoneId":"","location":"","address":"","companyInfo":"","attacheId":"","managerId":"","directorId":"","flag":"","officeId":"","userId":"","userName":"","phone":"","loginName":"","password":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""},"storeManage":{"id":"7233ef790059406881e8121622bbf979","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company1":"","company":"","storeName":"娇娇设置的门店","area":"","zoneId":"","location":"","address":"","storeInfo":"","attache":"","managerId":"","directorId":"","flag":"","lProvince":"","lCity":"","lRegion":"","officeId":"","storeRise":"","storeImg":"","reason":"","userId":"","storeNum":"","userName":"","phone":"","loginName":"","password":"","attacheName":"","attachePhone":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""},"borkerId":"","storeId":"7233ef790059406881e8121622bbf979","role":"","isCircle":"","parent":"","industry":"","type":"","teamLeader":"","level":"","teamRatio":"","manager":"","startTime":"","endTime":"","startTime1":"","endTime1":"","manageFlag":"","commissionLevelId":"","admin":false,"roleNames":""}
         * storeManage : {"id":"7233ef790059406881e8121622bbf979","remarks":"","createBy":"","createDate":"2019-10-26 15:32:46","updateBy":"","updateDate":"2019-11-01 11:34:27","company1":"","company":{"id":"946ee5b7800443f4a6379e4df42712b9","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","companyCityName":"","companyName":"大大经纪公司","area":"","zoneId":"","location":"","address":"","companyInfo":"","attacheId":"","managerId":"","directorId":"","flag":"","officeId":"","userId":"","userName":"","phone":"","loginName":"","password":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""},"storeName":"娇娇设置的门店","area":"湖南省/长沙市/长沙县","zoneId":"","location":"","address":"长泾大陆街道处","storeInfo":"","attache":"","managerId":"","directorId":"","flag":"1","lProvince":"湖南省","lCity":"长沙市","lRegion":"长沙县","officeId":"c241db93cbd247f5a8aadf501806b56a","storeRise":"/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/storerise_20191029143726/2019/10/file.png","storeImg":"/fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/zhuanyuan/2019/10/Ask2(10).png","reason":"","userId":"66d293e451db4400979d8b3bac0b167f","storeNum":"0000534","userName":"你呀你","phone":"18655221470","loginName":"nyn","password":"","attacheName":"叫我亲爱的","attachePhone":"18513271146","startTime":"","endTime":"","lprovince":"湖南省","lcity":"长沙市","lregion":"长沙县"}
         */

        private int isstorePerson;
        private StorePersonBean storePerson;
        private StoreManageBeanX storeManage;

        public int getIsstorePerson() {
            return isstorePerson;
        }

        public void setIsstorePerson(int isstorePerson) {
            this.isstorePerson = isstorePerson;
        }

        public StorePersonBean getStorePerson() {
            return storePerson;
        }

        public void setStorePerson(StorePersonBean storePerson) {
            this.storePerson = storePerson;
        }

        public StoreManageBeanX getStoreManage() {
            return storeManage;
        }

        public void setStoreManage(StoreManageBeanX storeManage) {
            this.storeManage = storeManage;
        }

        public static class StorePersonBean {
            /**
             * id : 66d293e451db4400979d8b3bac0b167f
             * remarks :
             * createBy :
             * createDate : 2019-10-26 16:43:15
             * updateBy :
             * updateDate : 2019-10-30 10:00:45
             * company :
             * office :
             * post :
             * loginName : nyn
             * newPassword :
             * oldLoginName :
             * no :
             * name : 你呀你
             * position :
             * directLeaderId :
             * email :
             * phone : 18655221470
             * oldPhone :
             * mobile :
             * photo :
             * loginIp :
             * loginDate :
             * loginFlag : 1
             * qrcode :
             * sign :
             * sex :
             * province : 湖南省
             * city : 长沙市
             * county : 长沙县
             * identity : 3
             * companyManage : {"id":"71bfda821c1841c182ce32568689bc78","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","companyCityName":"","companyName":"娇娇公司","area":"","zoneId":"","location":"","address":"","companyInfo":"","attacheId":"","managerId":"","directorId":"","flag":"","officeId":"","userId":"","userName":"","phone":"","loginName":"","password":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""}
             * storeManage : {"id":"7233ef790059406881e8121622bbf979","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company1":"","company":"","storeName":"娇娇设置的门店","area":"","zoneId":"","location":"","address":"","storeInfo":"","attache":"","managerId":"","directorId":"","flag":"","lProvince":"","lCity":"","lRegion":"","officeId":"","storeRise":"","storeImg":"","reason":"","userId":"","storeNum":"","userName":"","phone":"","loginName":"","password":"","attacheName":"","attachePhone":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""}
             * borkerId :
             * storeId : 7233ef790059406881e8121622bbf979
             * role :
             * isCircle :
             * parent :
             * industry :
             * type :
             * teamLeader :
             * level :
             * teamRatio :
             * manager :
             * startTime :
             * endTime :
             * startTime1 :
             * endTime1 :
             * manageFlag :
             * commissionLevelId :
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
            private String newPassword;
            private String oldLoginName;
            private String no;
            private String name;
            private String position;
            private String directLeaderId;
            private String email;
            private String phone;
            private String oldPhone;
            private String mobile;
            private String photo;
            private String loginIp;
            private String loginDate;
            private String loginFlag;
            private String qrcode;
            private String sign;
            private String sex;
            private String province;
            private String city;
            private String county;
            private String identity;
            private CompanyManageBean companyManage;
            private StoreManageBean storeManage;
            private String borkerId;
            private String storeId;
            private String role;
            private String isCircle;
            private String parent;
            private String industry;
            private String type;
            private String teamLeader;
            private String level;
            private String teamRatio;
            private String manager;
            private String startTime;
            private String endTime;
            private String startTime1;
            private String endTime1;
            private String manageFlag;
            private String commissionLevelId;
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

            public String getNewPassword() {
                return newPassword;
            }

            public void setNewPassword(String newPassword) {
                this.newPassword = newPassword;
            }

            public String getOldLoginName() {
                return oldLoginName;
            }

            public void setOldLoginName(String oldLoginName) {
                this.oldLoginName = oldLoginName;
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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getDirectLeaderId() {
                return directLeaderId;
            }

            public void setDirectLeaderId(String directLeaderId) {
                this.directLeaderId = directLeaderId;
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

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
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

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
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

            public CompanyManageBean getCompanyManage() {
                return companyManage;
            }

            public void setCompanyManage(CompanyManageBean companyManage) {
                this.companyManage = companyManage;
            }

            public StoreManageBean getStoreManage() {
                return storeManage;
            }

            public void setStoreManage(StoreManageBean storeManage) {
                this.storeManage = storeManage;
            }

            public String getBorkerId() {
                return borkerId;
            }

            public void setBorkerId(String borkerId) {
                this.borkerId = borkerId;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getIsCircle() {
                return isCircle;
            }

            public void setIsCircle(String isCircle) {
                this.isCircle = isCircle;
            }

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTeamLeader() {
                return teamLeader;
            }

            public void setTeamLeader(String teamLeader) {
                this.teamLeader = teamLeader;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getTeamRatio() {
                return teamRatio;
            }

            public void setTeamRatio(String teamRatio) {
                this.teamRatio = teamRatio;
            }

            public String getManager() {
                return manager;
            }

            public void setManager(String manager) {
                this.manager = manager;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getStartTime1() {
                return startTime1;
            }

            public void setStartTime1(String startTime1) {
                this.startTime1 = startTime1;
            }

            public String getEndTime1() {
                return endTime1;
            }

            public void setEndTime1(String endTime1) {
                this.endTime1 = endTime1;
            }

            public String getManageFlag() {
                return manageFlag;
            }

            public void setManageFlag(String manageFlag) {
                this.manageFlag = manageFlag;
            }

            public String getCommissionLevelId() {
                return commissionLevelId;
            }

            public void setCommissionLevelId(String commissionLevelId) {
                this.commissionLevelId = commissionLevelId;
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

            public static class CompanyManageBean {
                /**
                 * id : 71bfda821c1841c182ce32568689bc78
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * companyCityName :
                 * companyName : 娇娇公司
                 * area :
                 * zoneId :
                 * location :
                 * address :
                 * companyInfo :
                 * attacheId :
                 * managerId :
                 * directorId :
                 * flag :
                 * officeId :
                 * userId :
                 * userName :
                 * phone :
                 * loginName :
                 * password :
                 * startTime :
                 * endTime :
                 * lprovince :
                 * lcity :
                 * lregion :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String companyCityName;
                private String companyName;
                private String area;
                private String zoneId;
                private String location;
                private String address;
                private String companyInfo;
                private String attacheId;
                private String managerId;
                private String directorId;
                private String flag;
                private String officeId;
                private String userId;
                private String userName;
                private String phone;
                private String loginName;
                private String password;
                private String startTime;
                private String endTime;
                private String lprovince;
                private String lcity;
                private String lregion;

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

                public String getCompanyCityName() {
                    return companyCityName;
                }

                public void setCompanyCityName(String companyCityName) {
                    this.companyCityName = companyCityName;
                }

                public String getCompanyName() {
                    return companyName;
                }

                public void setCompanyName(String companyName) {
                    this.companyName = companyName;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getZoneId() {
                    return zoneId;
                }

                public void setZoneId(String zoneId) {
                    this.zoneId = zoneId;
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

                public String getCompanyInfo() {
                    return companyInfo;
                }

                public void setCompanyInfo(String companyInfo) {
                    this.companyInfo = companyInfo;
                }

                public String getAttacheId() {
                    return attacheId;
                }

                public void setAttacheId(String attacheId) {
                    this.attacheId = attacheId;
                }

                public String getManagerId() {
                    return managerId;
                }

                public void setManagerId(String managerId) {
                    this.managerId = managerId;
                }

                public String getDirectorId() {
                    return directorId;
                }

                public void setDirectorId(String directorId) {
                    this.directorId = directorId;
                }

                public String getFlag() {
                    return flag;
                }

                public void setFlag(String flag) {
                    this.flag = flag;
                }

                public String getOfficeId() {
                    return officeId;
                }

                public void setOfficeId(String officeId) {
                    this.officeId = officeId;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getLoginName() {
                    return loginName;
                }

                public void setLoginName(String loginName) {
                    this.loginName = loginName;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public String getLprovince() {
                    return lprovince;
                }

                public void setLprovince(String lprovince) {
                    this.lprovince = lprovince;
                }

                public String getLcity() {
                    return lcity;
                }

                public void setLcity(String lcity) {
                    this.lcity = lcity;
                }

                public String getLregion() {
                    return lregion;
                }

                public void setLregion(String lregion) {
                    this.lregion = lregion;
                }
            }

            public static class StoreManageBean {
                /**
                 * id : 7233ef790059406881e8121622bbf979
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * company1 :
                 * company :
                 * storeName : 娇娇设置的门店
                 * area :
                 * zoneId :
                 * location :
                 * address :
                 * storeInfo :
                 * attache :
                 * managerId :
                 * directorId :
                 * flag :
                 * lProvince :
                 * lCity :
                 * lRegion :
                 * officeId :
                 * storeRise :
                 * storeImg :
                 * reason :
                 * userId :
                 * storeNum :
                 * userName :
                 * phone :
                 * loginName :
                 * password :
                 * attacheName :
                 * attachePhone :
                 * startTime :
                 * endTime :
                 * lprovince :
                 * lcity :
                 * lregion :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String company1;
                private String company;
                private String storeName;
                private String area;
                private String zoneId;
                private String location;
                private String address;
                private String storeInfo;
                private String attache;
                private String managerId;
                private String directorId;
                private String flag;
                private String lProvince;
                private String lCity;
                private String lRegion;
                private String officeId;
                private String storeRise;
                private String storeImg;
                private String reason;
                private String userId;
                private String storeNum;
                private String userName;
                private String phone;
                private String loginName;
                private String password;
                private String attacheName;
                private String attachePhone;
                private String startTime;
                private String endTime;
                private String lprovince;
                private String lcity;
                private String lregion;

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

                public String getCompany1() {
                    return company1;
                }

                public void setCompany1(String company1) {
                    this.company1 = company1;
                }

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getStoreName() {
                    return storeName;
                }

                public void setStoreName(String storeName) {
                    this.storeName = storeName;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getZoneId() {
                    return zoneId;
                }

                public void setZoneId(String zoneId) {
                    this.zoneId = zoneId;
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

                public String getStoreInfo() {
                    return storeInfo;
                }

                public void setStoreInfo(String storeInfo) {
                    this.storeInfo = storeInfo;
                }

                public String getAttache() {
                    return attache;
                }

                public void setAttache(String attache) {
                    this.attache = attache;
                }

                public String getManagerId() {
                    return managerId;
                }

                public void setManagerId(String managerId) {
                    this.managerId = managerId;
                }

                public String getDirectorId() {
                    return directorId;
                }

                public void setDirectorId(String directorId) {
                    this.directorId = directorId;
                }

                public String getFlag() {
                    return flag;
                }

                public void setFlag(String flag) {
                    this.flag = flag;
                }

                public String getLProvince() {
                    return lProvince;
                }

                public void setLProvince(String lProvince) {
                    this.lProvince = lProvince;
                }

                public String getLCity() {
                    return lCity;
                }

                public void setLCity(String lCity) {
                    this.lCity = lCity;
                }

                public String getLRegion() {
                    return lRegion;
                }

                public void setLRegion(String lRegion) {
                    this.lRegion = lRegion;
                }

                public String getOfficeId() {
                    return officeId;
                }

                public void setOfficeId(String officeId) {
                    this.officeId = officeId;
                }

                public String getStoreRise() {
                    return storeRise;
                }

                public void setStoreRise(String storeRise) {
                    this.storeRise = storeRise;
                }

                public String getStoreImg() {
                    return storeImg;
                }

                public void setStoreImg(String storeImg) {
                    this.storeImg = storeImg;
                }

                public String getReason() {
                    return reason;
                }

                public void setReason(String reason) {
                    this.reason = reason;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getStoreNum() {
                    return storeNum;
                }

                public void setStoreNum(String storeNum) {
                    this.storeNum = storeNum;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getLoginName() {
                    return loginName;
                }

                public void setLoginName(String loginName) {
                    this.loginName = loginName;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getAttacheName() {
                    return attacheName;
                }

                public void setAttacheName(String attacheName) {
                    this.attacheName = attacheName;
                }

                public String getAttachePhone() {
                    return attachePhone;
                }

                public void setAttachePhone(String attachePhone) {
                    this.attachePhone = attachePhone;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public String getLprovince() {
                    return lprovince;
                }

                public void setLprovince(String lprovince) {
                    this.lprovince = lprovince;
                }

                public String getLcity() {
                    return lcity;
                }

                public void setLcity(String lcity) {
                    this.lcity = lcity;
                }

                public String getLregion() {
                    return lregion;
                }

                public void setLregion(String lregion) {
                    this.lregion = lregion;
                }
            }
        }

        public static class StoreManageBeanX {
            /**
             * id : 7233ef790059406881e8121622bbf979
             * remarks :
             * createBy :
             * createDate : 2019-10-26 15:32:46
             * updateBy :
             * updateDate : 2019-11-01 11:34:27
             * company1 :
             * company : {"id":"946ee5b7800443f4a6379e4df42712b9","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","companyCityName":"","companyName":"大大经纪公司","area":"","zoneId":"","location":"","address":"","companyInfo":"","attacheId":"","managerId":"","directorId":"","flag":"","officeId":"","userId":"","userName":"","phone":"","loginName":"","password":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""}
             * storeName : 娇娇设置的门店
             * area : 湖南省/长沙市/长沙县
             * zoneId :
             * location :
             * address : 长泾大陆街道处
             * storeInfo :
             * attache :
             * managerId :
             * directorId :
             * flag : 1
             * lProvince : 湖南省
             * lCity : 长沙市
             * lRegion : 长沙县
             * officeId : c241db93cbd247f5a8aadf501806b56a
             * storeRise : /fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/storerise_20191029143726/2019/10/file.png
             * storeImg : /fangfang/userfiles/badb8e2d394d4841a8376437471970a5/attachment/zhuanyuan/2019/10/Ask2(10).png
             * reason :
             * userId : 66d293e451db4400979d8b3bac0b167f
             * storeNum : 0000534
             * userName : 你呀你
             * phone : 18655221470
             * loginName : nyn
             * password :
             * attacheName : 叫我亲爱的
             * attachePhone : 18513271146
             * startTime :
             * endTime :
             * lprovince : 湖南省
             * lcity : 长沙市
             * lregion : 长沙县
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String company1;
            private CompanyBean company;
            private String storeName;
            private String area;
            private String zoneId;
            private String location;
            private String address;
            private String storeInfo;
            private String attache;
            private String managerId;
            private String directorId;
            private String flag;
            private String lProvince;
            private String lCity;
            private String lRegion;
            private String officeId;
            private String storeRise;
            private String storeImg;
            private String reason;
            private String userId;
            private String storeNum;
            private String userName;
            private String phone;
            private String loginName;
            private String password;
            private String attacheName;
            private String attachePhone;
            private String startTime;
            private String endTime;
            private String lprovince;
            private String lcity;
            private String lregion;

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

            public String getCompany1() {
                return company1;
            }

            public void setCompany1(String company1) {
                this.company1 = company1;
            }

            public CompanyBean getCompany() {
                return company;
            }

            public void setCompany(CompanyBean company) {
                this.company = company;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getZoneId() {
                return zoneId;
            }

            public void setZoneId(String zoneId) {
                this.zoneId = zoneId;
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

            public String getStoreInfo() {
                return storeInfo;
            }

            public void setStoreInfo(String storeInfo) {
                this.storeInfo = storeInfo;
            }

            public String getAttache() {
                return attache;
            }

            public void setAttache(String attache) {
                this.attache = attache;
            }

            public String getManagerId() {
                return managerId;
            }

            public void setManagerId(String managerId) {
                this.managerId = managerId;
            }

            public String getDirectorId() {
                return directorId;
            }

            public void setDirectorId(String directorId) {
                this.directorId = directorId;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getLProvince() {
                return lProvince;
            }

            public void setLProvince(String lProvince) {
                this.lProvince = lProvince;
            }

            public String getLCity() {
                return lCity;
            }

            public void setLCity(String lCity) {
                this.lCity = lCity;
            }

            public String getLRegion() {
                return lRegion;
            }

            public void setLRegion(String lRegion) {
                this.lRegion = lRegion;
            }

            public String getOfficeId() {
                return officeId;
            }

            public void setOfficeId(String officeId) {
                this.officeId = officeId;
            }

            public String getStoreRise() {
                return storeRise;
            }

            public void setStoreRise(String storeRise) {
                this.storeRise = storeRise;
            }

            public String getStoreImg() {
                return storeImg;
            }

            public void setStoreImg(String storeImg) {
                this.storeImg = storeImg;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getStoreNum() {
                return storeNum;
            }

            public void setStoreNum(String storeNum) {
                this.storeNum = storeNum;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getAttacheName() {
                return attacheName;
            }

            public void setAttacheName(String attacheName) {
                this.attacheName = attacheName;
            }

            public String getAttachePhone() {
                return attachePhone;
            }

            public void setAttachePhone(String attachePhone) {
                this.attachePhone = attachePhone;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getLprovince() {
                return lprovince;
            }

            public void setLprovince(String lprovince) {
                this.lprovince = lprovince;
            }

            public String getLcity() {
                return lcity;
            }

            public void setLcity(String lcity) {
                this.lcity = lcity;
            }

            public String getLregion() {
                return lregion;
            }

            public void setLregion(String lregion) {
                this.lregion = lregion;
            }

            public static class CompanyBean {
                /**
                 * id : 946ee5b7800443f4a6379e4df42712b9
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * companyCityName :
                 * companyName : 大大经纪公司
                 * area :
                 * zoneId :
                 * location :
                 * address :
                 * companyInfo :
                 * attacheId :
                 * managerId :
                 * directorId :
                 * flag :
                 * officeId :
                 * userId :
                 * userName :
                 * phone :
                 * loginName :
                 * password :
                 * startTime :
                 * endTime :
                 * lprovince :
                 * lcity :
                 * lregion :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String companyCityName;
                private String companyName;
                private String area;
                private String zoneId;
                private String location;
                private String address;
                private String companyInfo;
                private String attacheId;
                private String managerId;
                private String directorId;
                private String flag;
                private String officeId;
                private String userId;
                private String userName;
                private String phone;
                private String loginName;
                private String password;
                private String startTime;
                private String endTime;
                private String lprovince;
                private String lcity;
                private String lregion;

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

                public String getCompanyCityName() {
                    return companyCityName;
                }

                public void setCompanyCityName(String companyCityName) {
                    this.companyCityName = companyCityName;
                }

                public String getCompanyName() {
                    return companyName;
                }

                public void setCompanyName(String companyName) {
                    this.companyName = companyName;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getZoneId() {
                    return zoneId;
                }

                public void setZoneId(String zoneId) {
                    this.zoneId = zoneId;
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

                public String getCompanyInfo() {
                    return companyInfo;
                }

                public void setCompanyInfo(String companyInfo) {
                    this.companyInfo = companyInfo;
                }

                public String getAttacheId() {
                    return attacheId;
                }

                public void setAttacheId(String attacheId) {
                    this.attacheId = attacheId;
                }

                public String getManagerId() {
                    return managerId;
                }

                public void setManagerId(String managerId) {
                    this.managerId = managerId;
                }

                public String getDirectorId() {
                    return directorId;
                }

                public void setDirectorId(String directorId) {
                    this.directorId = directorId;
                }

                public String getFlag() {
                    return flag;
                }

                public void setFlag(String flag) {
                    this.flag = flag;
                }

                public String getOfficeId() {
                    return officeId;
                }

                public void setOfficeId(String officeId) {
                    this.officeId = officeId;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getLoginName() {
                    return loginName;
                }

                public void setLoginName(String loginName) {
                    this.loginName = loginName;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public String getLprovince() {
                    return lprovince;
                }

                public void setLprovince(String lprovince) {
                    this.lprovince = lprovince;
                }

                public String getLcity() {
                    return lcity;
                }

                public void setLcity(String lcity) {
                    this.lcity = lcity;
                }

                public String getLregion() {
                    return lregion;
                }

                public void setLregion(String lregion) {
                    this.lregion = lregion;
                }
            }
        }
    }
}
