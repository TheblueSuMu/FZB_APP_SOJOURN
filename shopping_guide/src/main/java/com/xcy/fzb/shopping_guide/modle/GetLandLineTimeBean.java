package com.xcy.fzb.shopping_guide.modle;

import java.util.List;

public class GetLandLineTimeBean {
    /**
     * code : 1
     * msg : 成功
     * data : [{"id":"118d477c9b9b4312bf9a850c5b0c00f6","remarks":"","createBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"房坐标长春公司总经理","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"createDate":"2019-09-03 10:20:30","updateBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"updateDate":"2019-09-03 10:20:30","route":{"id":"052e77a9a4f94a209ad0cdbbfd6ad373","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","routeName":"人间\u2014\u2014天堂","company":"","projectId":"","endCloseTimeStr":"","startTime":""},"islandTime":"2019/09/10 00:00","endTime":"2019/09/20 00:00","expenses":"100","cityCompany":"c241db93cbd247f5a8aadf501806b56a","company":{"id":"c241db93cbd247f5a8aadf501806b56a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","parentIds":"","name":"长春房坐标（吉海优房）","sort":30,"hasChildren":false,"area":"","code":"","type":"2","grade":"","address":"","zipCode":"","master":"","phone":"","fax":"","email":"","useable":"","primaryPerson":"","deputyPerson":"","childDeptList":"","province":"","city":"","county":"","areas":"","parentId":"0"},"numberPeople":11,"matter":{"id":"042be3bf48f944f0be6669a987f95817","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","title":"今天是测试第一天","matterComment":"<p>啦啦啦啦啦绿绿绿<\/p>","cityCompany":"","projectType":""},"taskStatus":"10","enrollNumber":1,"earnestNumber":0,"tradeNumber":0,"noTradeNumber":"","guideUser":"","isAccept":0,"acceptTime":"","endCloseTime":1568081995000,"travelWay":"0","travelWayImg":"/fangfang/static/common/images/plane2x.png","taskStatusName":"报名中","taskStatusColor":"","startIslandTime":"","endIslandTime":"","datePicker":"","searchSatus":"","endCloseTimeStr":"","projectIds":"fc17eeeb177f4e5e85625264234bc5fe","projectNames":"Chelsea Creek | 切尔西水畔 泰晤士河北岸伦敦西二区","project":"","startTime":"","startEndTime":"2019-09-10 - 2019-09-20","planning":""}]
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
         * id : 118d477c9b9b4312bf9a850c5b0c00f6
         * remarks :
         * createBy : {"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"房坐标长春公司总经理","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""}
         * createDate : 2019-09-03 10:20:30
         * updateBy : {"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""}
         * updateDate : 2019-09-03 10:20:30
         * route : {"id":"052e77a9a4f94a209ad0cdbbfd6ad373","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","routeName":"人间\u2014\u2014天堂","company":"","projectId":"","endCloseTimeStr":"","startTime":""}
         * islandTime : 2019/09/10 00:00
         * endTime : 2019/09/20 00:00
         * expenses : 100
         * cityCompany : c241db93cbd247f5a8aadf501806b56a
         * company : {"id":"c241db93cbd247f5a8aadf501806b56a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","parentIds":"","name":"长春房坐标（吉海优房）","sort":30,"hasChildren":false,"area":"","code":"","type":"2","grade":"","address":"","zipCode":"","master":"","phone":"","fax":"","email":"","useable":"","primaryPerson":"","deputyPerson":"","childDeptList":"","province":"","city":"","county":"","areas":"","parentId":"0"}
         * numberPeople : 11
         * matter : {"id":"042be3bf48f944f0be6669a987f95817","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","title":"今天是测试第一天","matterComment":"<p>啦啦啦啦啦绿绿绿<\/p>","cityCompany":"","projectType":""}
         * taskStatus : 10
         * enrollNumber : 1
         * earnestNumber : 0
         * tradeNumber : 0
         * noTradeNumber :
         * guideUser :
         * isAccept : 0
         * acceptTime :
         * endCloseTime : 1568081995000
         * travelWay : 0
         * travelWayImg : /fangfang/static/common/images/plane2x.png
         * taskStatusName : 报名中
         * taskStatusColor :
         * startIslandTime :
         * endIslandTime :
         * datePicker :
         * searchSatus :
         * endCloseTimeStr :
         * projectIds : fc17eeeb177f4e5e85625264234bc5fe
         * projectNames : Chelsea Creek | 切尔西水畔 泰晤士河北岸伦敦西二区
         * project :
         * startTime :
         * startEndTime : 2019-09-10 - 2019-09-20
         * planning :
         */

        private String id;
        private String remarks;
        private CreateByBean createBy;
        private String createDate;
        private UpdateByBean updateBy;
        private String updateDate;
        private RouteBean route;
        private String islandTime;
        private String endTime;
        private String expenses;
        private String cityCompany;
        private CompanyBean company;
        private int numberPeople;
        private MatterBean matter;
        private String taskStatus;
        private int enrollNumber;
        private int earnestNumber;
        private int tradeNumber;
        private String noTradeNumber;
        private String guideUser;
        private int isAccept;
        private String acceptTime;
        private long endCloseTime;
        private String travelWay;
        private String travelWayImg;
        private String taskStatusName;
        private String taskStatusColor;
        private String startIslandTime;
        private String endIslandTime;
        private String datePicker;
        private String searchSatus;
        private String endCloseTimeStr;
        private String projectIds;
        private String projectNames;
        private String project;
        private String startTime;
        private String startEndTime;
        private String planning;

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

        public RouteBean getRoute() {
            return route;
        }

        public void setRoute(RouteBean route) {
            this.route = route;
        }

        public String getIslandTime() {
            return islandTime;
        }

        public void setIslandTime(String islandTime) {
            this.islandTime = islandTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getExpenses() {
            return expenses;
        }

        public void setExpenses(String expenses) {
            this.expenses = expenses;
        }

        public String getCityCompany() {
            return cityCompany;
        }

        public void setCityCompany(String cityCompany) {
            this.cityCompany = cityCompany;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public int getNumberPeople() {
            return numberPeople;
        }

        public void setNumberPeople(int numberPeople) {
            this.numberPeople = numberPeople;
        }

        public MatterBean getMatter() {
            return matter;
        }

        public void setMatter(MatterBean matter) {
            this.matter = matter;
        }

        public String getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
        }

        public int getEnrollNumber() {
            return enrollNumber;
        }

        public void setEnrollNumber(int enrollNumber) {
            this.enrollNumber = enrollNumber;
        }

        public int getEarnestNumber() {
            return earnestNumber;
        }

        public void setEarnestNumber(int earnestNumber) {
            this.earnestNumber = earnestNumber;
        }

        public int getTradeNumber() {
            return tradeNumber;
        }

        public void setTradeNumber(int tradeNumber) {
            this.tradeNumber = tradeNumber;
        }

        public String getNoTradeNumber() {
            return noTradeNumber;
        }

        public void setNoTradeNumber(String noTradeNumber) {
            this.noTradeNumber = noTradeNumber;
        }

        public String getGuideUser() {
            return guideUser;
        }

        public void setGuideUser(String guideUser) {
            this.guideUser = guideUser;
        }

        public int getIsAccept() {
            return isAccept;
        }

        public void setIsAccept(int isAccept) {
            this.isAccept = isAccept;
        }

        public String getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            this.acceptTime = acceptTime;
        }

        public long getEndCloseTime() {
            return endCloseTime;
        }

        public void setEndCloseTime(long endCloseTime) {
            this.endCloseTime = endCloseTime;
        }

        public String getTravelWay() {
            return travelWay;
        }

        public void setTravelWay(String travelWay) {
            this.travelWay = travelWay;
        }

        public String getTravelWayImg() {
            return travelWayImg;
        }

        public void setTravelWayImg(String travelWayImg) {
            this.travelWayImg = travelWayImg;
        }

        public String getTaskStatusName() {
            return taskStatusName;
        }

        public void setTaskStatusName(String taskStatusName) {
            this.taskStatusName = taskStatusName;
        }

        public String getTaskStatusColor() {
            return taskStatusColor;
        }

        public void setTaskStatusColor(String taskStatusColor) {
            this.taskStatusColor = taskStatusColor;
        }

        public String getStartIslandTime() {
            return startIslandTime;
        }

        public void setStartIslandTime(String startIslandTime) {
            this.startIslandTime = startIslandTime;
        }

        public String getEndIslandTime() {
            return endIslandTime;
        }

        public void setEndIslandTime(String endIslandTime) {
            this.endIslandTime = endIslandTime;
        }

        public String getDatePicker() {
            return datePicker;
        }

        public void setDatePicker(String datePicker) {
            this.datePicker = datePicker;
        }

        public String getSearchSatus() {
            return searchSatus;
        }

        public void setSearchSatus(String searchSatus) {
            this.searchSatus = searchSatus;
        }

        public String getEndCloseTimeStr() {
            return endCloseTimeStr;
        }

        public void setEndCloseTimeStr(String endCloseTimeStr) {
            this.endCloseTimeStr = endCloseTimeStr;
        }

        public String getProjectIds() {
            return projectIds;
        }

        public void setProjectIds(String projectIds) {
            this.projectIds = projectIds;
        }

        public String getProjectNames() {
            return projectNames;
        }

        public void setProjectNames(String projectNames) {
            this.projectNames = projectNames;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStartEndTime() {
            return startEndTime;
        }

        public void setStartEndTime(String startEndTime) {
            this.startEndTime = startEndTime;
        }

        public String getPlanning() {
            return planning;
        }

        public void setPlanning(String planning) {
            this.planning = planning;
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
             * name : 房坐标长春公司总经理
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

        public static class RouteBean {
            /**
             * id : 052e77a9a4f94a209ad0cdbbfd6ad373
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * routeName : 人间——天堂
             * company :
             * projectId :
             * endCloseTimeStr :
             * startTime :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String routeName;
            private String company;
            private String projectId;
            private String endCloseTimeStr;
            private String startTime;

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

            public String getRouteName() {
                return routeName;
            }

            public void setRouteName(String routeName) {
                this.routeName = routeName;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getEndCloseTimeStr() {
                return endCloseTimeStr;
            }

            public void setEndCloseTimeStr(String endCloseTimeStr) {
                this.endCloseTimeStr = endCloseTimeStr;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }
        }

        public static class CompanyBean {
            /**
             * id : c241db93cbd247f5a8aadf501806b56a
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * parentIds :
             * name : 长春房坐标（吉海优房）
             * sort : 30
             * hasChildren : false
             * area :
             * code :
             * type : 2
             * grade :
             * address :
             * zipCode :
             * master :
             * phone :
             * fax :
             * email :
             * useable :
             * primaryPerson :
             * deputyPerson :
             * childDeptList :
             * province :
             * city :
             * county :
             * areas :
             * parentId : 0
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String parentIds;
            private String name;
            private int sort;
            private boolean hasChildren;
            private String area;
            private String code;
            private String type;
            private String grade;
            private String address;
            private String zipCode;
            private String master;
            private String phone;
            private String fax;
            private String email;
            private String useable;
            private String primaryPerson;
            private String deputyPerson;
            private String childDeptList;
            private String province;
            private String city;
            private String county;
            private String areas;
            private String parentId;

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

            public String getParentIds() {
                return parentIds;
            }

            public void setParentIds(String parentIds) {
                this.parentIds = parentIds;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public boolean isHasChildren() {
                return hasChildren;
            }

            public void setHasChildren(boolean hasChildren) {
                this.hasChildren = hasChildren;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getZipCode() {
                return zipCode;
            }

            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }

            public String getMaster() {
                return master;
            }

            public void setMaster(String master) {
                this.master = master;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getFax() {
                return fax;
            }

            public void setFax(String fax) {
                this.fax = fax;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getUseable() {
                return useable;
            }

            public void setUseable(String useable) {
                this.useable = useable;
            }

            public String getPrimaryPerson() {
                return primaryPerson;
            }

            public void setPrimaryPerson(String primaryPerson) {
                this.primaryPerson = primaryPerson;
            }

            public String getDeputyPerson() {
                return deputyPerson;
            }

            public void setDeputyPerson(String deputyPerson) {
                this.deputyPerson = deputyPerson;
            }

            public String getChildDeptList() {
                return childDeptList;
            }

            public void setChildDeptList(String childDeptList) {
                this.childDeptList = childDeptList;
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

            public String getAreas() {
                return areas;
            }

            public void setAreas(String areas) {
                this.areas = areas;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }
        }

        public static class MatterBean {
            /**
             * id : 042be3bf48f944f0be6669a987f95817
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * title : 今天是测试第一天
             * matterComment : <p>啦啦啦啦啦绿绿绿</p>
             * cityCompany :
             * projectType :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String title;
            private String matterComment;
            private String cityCompany;
            private String projectType;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMatterComment() {
                return matterComment;
            }

            public void setMatterComment(String matterComment) {
                this.matterComment = matterComment;
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
        }
    }
}
