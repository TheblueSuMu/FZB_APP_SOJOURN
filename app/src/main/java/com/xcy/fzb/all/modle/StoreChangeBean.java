package com.xcy.fzb.all.modle;

public class StoreChangeBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"isstorePerson":0,"storePerson":"","storeManage":{"id":"49fa29a5f2214487ae7c8537a20512ba","remarks":"","createBy":"","createDate":"2019-10-09 17:37:43","updateBy":"","updateDate":"2019-10-09 17:37:43","company":{"id":"b8524ae34a78433d9fcc8aa9d1bcfe7c","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","companyCityName":"","companyName":"mjx公司","area":"","zoneId":"","location":"","address":"","companyInfo":"","attacheId":"","managerId":"","directorId":"","flag":"","officeId":"","userId":"","userName":"","phone":"","loginName":"","password":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""},"storeName":"添加门店222","area":"常州市/天宁区","zoneId":"","location":"","address":"你猜","storeInfo":"","attache":"","managerId":"","directorId":"","flag":"1","lProvince":"常州市","lCity":"","lRegion":"","officeId":"c241db93cbd247f5a8aadf501806b56a","storeRise":"","storeImg":"","reason":"","userId":"","storeNum":"0000141","userName":"","phone":"","loginName":"","password":"","attacheName":"王专员","attachePhone":"19904308159","startTime":"","endTime":"","lprovince":"常州市","lcity":"","lregion":""}}
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
         * isstorePerson : 0
         * storePerson :
         * storeManage : {"id":"49fa29a5f2214487ae7c8537a20512ba","remarks":"","createBy":"","createDate":"2019-10-09 17:37:43","updateBy":"","updateDate":"2019-10-09 17:37:43","company":{"id":"b8524ae34a78433d9fcc8aa9d1bcfe7c","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","companyCityName":"","companyName":"mjx公司","area":"","zoneId":"","location":"","address":"","companyInfo":"","attacheId":"","managerId":"","directorId":"","flag":"","officeId":"","userId":"","userName":"","phone":"","loginName":"","password":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""},"storeName":"添加门店222","area":"常州市/天宁区","zoneId":"","location":"","address":"你猜","storeInfo":"","attache":"","managerId":"","directorId":"","flag":"1","lProvince":"常州市","lCity":"","lRegion":"","officeId":"c241db93cbd247f5a8aadf501806b56a","storeRise":"","storeImg":"","reason":"","userId":"","storeNum":"0000141","userName":"","phone":"","loginName":"","password":"","attacheName":"王专员","attachePhone":"19904308159","startTime":"","endTime":"","lprovince":"常州市","lcity":"","lregion":""}
         */

        private int isstorePerson;
        private String storePerson;
        private StoreManageBean storeManage;

        public int getIsstorePerson() {
            return isstorePerson;
        }

        public void setIsstorePerson(int isstorePerson) {
            this.isstorePerson = isstorePerson;
        }

        public String getStorePerson() {
            return storePerson;
        }

        public void setStorePerson(String storePerson) {
            this.storePerson = storePerson;
        }

        public StoreManageBean getStoreManage() {
            return storeManage;
        }

        public void setStoreManage(StoreManageBean storeManage) {
            this.storeManage = storeManage;
        }

        public static class StoreManageBean {
            /**
             * id : 49fa29a5f2214487ae7c8537a20512ba
             * remarks :
             * createBy :
             * createDate : 2019-10-09 17:37:43
             * updateBy :
             * updateDate : 2019-10-09 17:37:43
             * company : {"id":"b8524ae34a78433d9fcc8aa9d1bcfe7c","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","companyCityName":"","companyName":"mjx公司","area":"","zoneId":"","location":"","address":"","companyInfo":"","attacheId":"","managerId":"","directorId":"","flag":"","officeId":"","userId":"","userName":"","phone":"","loginName":"","password":"","startTime":"","endTime":"","lprovince":"","lcity":"","lregion":""}
             * storeName : 添加门店222
             * area : 常州市/天宁区
             * zoneId :
             * location :
             * address : 你猜
             * storeInfo :
             * attache :
             * managerId :
             * directorId :
             * flag : 1
             * lProvince : 常州市
             * lCity :
             * lRegion :
             * officeId : c241db93cbd247f5a8aadf501806b56a
             * storeRise :
             * storeImg :
             * reason :
             * userId :
             * storeNum : 0000141
             * userName :
             * phone :
             * loginName :
             * password :
             * attacheName : 王专员
             * attachePhone : 19904308159
             * startTime :
             * endTime :
             * lprovince : 常州市
             * lcity :
             * lregion :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
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
                 * id : b8524ae34a78433d9fcc8aa9d1bcfe7c
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * companyCityName :
                 * companyName : mjx公司
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
