package com.xcy.fzb.all.modle;

import java.util.List;

public class SideHomeBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":1,"rows":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectId":"603e127528f747b68bee14d37b3f6239","projectName":"西港云上","listPageCover":"/fangfang/userfiles/1/attachment//ff/server/ffServerProjectManage/2019/5/(1)(2).jpg","projectImg":"/fangfang/userfiles/1/attachment//ff/server/ffServerProjectManage/2019/5/(1)(1).jpg","detailAddress":"柬埔寨西哈努克友谊三区（索卡海滩旁）","browseNum":"1961","forwardingAmount":"4","reportAmount":"153","collectionNum":"2","awardRules":"","commissionRules":"","productFeature":"永久产权,托管,公寓,海景房","buildingArea":"200000","saleStatus":"3","location":"103.575622,10.547139","amountIncentiveId":"","guideRuleId":"24f86ba3024f4df3abf3657898902770","isPhone":"1","digit":"4,5,6,7","isPapers":"0","projectType":"2","type":"0","percent":"","amount":"10000","contractAmount":"","contractPercent":"","secondsAmount":"","isSeconds":"0","area":"柬埔寨","isgroup":"0","groupNum":"0","salesOfficeLocation":"","onlineState":"1","productTypeSize":1,"productUnitPrice":"13000","productTotalPrice":"40","referenceToatlPrice":"40.00万元/套","areaIntervalStart":"40","areaIntervalEnd":"90","areaInterval":"40-90㎡","monetaryUnit":"万元/套","commission":"10000元/套","secondPay":"无秒结","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.create_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":""}]}
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
         * total : 1
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectId":"603e127528f747b68bee14d37b3f6239","projectName":"西港云上","listPageCover":"/fangfang/userfiles/1/attachment//ff/server/ffServerProjectManage/2019/5/(1)(2).jpg","projectImg":"/fangfang/userfiles/1/attachment//ff/server/ffServerProjectManage/2019/5/(1)(1).jpg","detailAddress":"柬埔寨西哈努克友谊三区（索卡海滩旁）","browseNum":"1961","forwardingAmount":"4","reportAmount":"153","collectionNum":"2","awardRules":"","commissionRules":"","productFeature":"永久产权,托管,公寓,海景房","buildingArea":"200000","saleStatus":"3","location":"103.575622,10.547139","amountIncentiveId":"","guideRuleId":"24f86ba3024f4df3abf3657898902770","isPhone":"1","digit":"4,5,6,7","isPapers":"0","projectType":"2","type":"0","percent":"","amount":"10000","contractAmount":"","contractPercent":"","secondsAmount":"","isSeconds":"0","area":"柬埔寨","isgroup":"0","groupNum":"0","salesOfficeLocation":"","onlineState":"1","productTypeSize":1,"productUnitPrice":"13000","productTotalPrice":"40","referenceToatlPrice":"40.00万元/套","areaIntervalStart":"40","areaIntervalEnd":"90","areaInterval":"40-90㎡","monetaryUnit":"万元/套","commission":"10000元/套","secondPay":"无秒结","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.create_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":""}]
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
             * id :
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * projectId : 603e127528f747b68bee14d37b3f6239
             * projectName : 西港云上
             * listPageCover : /fangfang/userfiles/1/attachment//ff/server/ffServerProjectManage/2019/5/(1)(2).jpg
             * projectImg : /fangfang/userfiles/1/attachment//ff/server/ffServerProjectManage/2019/5/(1)(1).jpg
             * detailAddress : 柬埔寨西哈努克友谊三区（索卡海滩旁）
             * browseNum : 1961
             * forwardingAmount : 4
             * reportAmount : 153
             * collectionNum : 2
             * awardRules :
             * commissionRules :
             * productFeature : 永久产权,托管,公寓,海景房
             * buildingArea : 200000
             * saleStatus : 3
             * location : 103.575622,10.547139
             * amountIncentiveId :
             * guideRuleId : 24f86ba3024f4df3abf3657898902770
             * isPhone : 1
             * digit : 4,5,6,7
             * isPapers : 0
             * projectType : 2
             * type : 0
             * percent :
             * amount : 10000
             * contractAmount :
             * contractPercent :
             * secondsAmount :
             * isSeconds : 0
             * area : 柬埔寨
             * isgroup : 0
             * groupNum : 0
             * salesOfficeLocation :
             * onlineState : 1
             * productTypeSize : 1
             * productUnitPrice : 13000
             * productTotalPrice : 40
             * referenceToatlPrice : 40.00万元/套
             * areaIntervalStart : 40
             * areaIntervalEnd : 90
             * areaInterval : 40-90㎡
             * monetaryUnit : 万元/套
             * commission : 10000元/套
             * secondPay : 无秒结
             * hot :
             * city :
             * nation :
             * searchContent :
             * searchName :
             * attacheId :
             * businessType :
             * collectionId :
             * isCollection :
             * ffAttacheList :
             * brokerUserPhone :
             * projectLabel :
             * projectLabelStr :
             * comprehensiveSorting :
             * comprehensiveSortingStr : pm.create_date DESC
             * apartment :
             * projectPriceStart :
             * projectPriceEnd :
             * areaSection :
             * ffProjectTrait :
             * procuctType :
             * fitmentState :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String projectId;
            private String projectName;
            private String listPageCover;
            private String projectImg;
            private String detailAddress;
            private String browseNum;
            private String forwardingAmount;
            private String reportAmount;
            private String collectionNum;
            private String awardRules;
            private String commissionRules;
            private String productFeature;
            private String buildingArea;
            private String saleStatus;
            private String location;
            private String amountIncentiveId;
            private String guideRuleId;
            private String isPhone;
            private String digit;
            private String isPapers;
            private String projectType;
            private String type;
            private String percent;
            private String amount;
            private String contractAmount;
            private String contractPercent;
            private String secondsAmount;
            private String isSeconds;
            private String area;
            private String isgroup;
            private String groupNum;
            private String salesOfficeLocation;
            private String onlineState;
            private int productTypeSize;
            private String productUnitPrice;
            private String productTotalPrice;
            private String referenceToatlPrice;
            private String areaIntervalStart;
            private String areaIntervalEnd;
            private String areaInterval;
            private String monetaryUnit;
            private String commission;
            private String secondPay;
            private String hot;
            private String city;
            private String nation;
            private String searchContent;
            private String searchName;
            private String attacheId;
            private String businessType;
            private String collectionId;
            private String isCollection;
            private String ffAttacheList;
            private String brokerUserPhone;
            private String projectLabel;
            private String projectLabelStr;
            private String comprehensiveSorting;
            private String comprehensiveSortingStr;
            private String apartment;
            private String projectPriceStart;
            private String projectPriceEnd;
            private String areaSection;
            private String ffProjectTrait;
            private String procuctType;
            private String fitmentState;

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

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getListPageCover() {
                return listPageCover;
            }

            public void setListPageCover(String listPageCover) {
                this.listPageCover = listPageCover;
            }

            public String getProjectImg() {
                return projectImg;
            }

            public void setProjectImg(String projectImg) {
                this.projectImg = projectImg;
            }

            public String getDetailAddress() {
                return detailAddress;
            }

            public void setDetailAddress(String detailAddress) {
                this.detailAddress = detailAddress;
            }

            public String getBrowseNum() {
                return browseNum;
            }

            public void setBrowseNum(String browseNum) {
                this.browseNum = browseNum;
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

            public String getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(String collectionNum) {
                this.collectionNum = collectionNum;
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

            public String getProductFeature() {
                return productFeature;
            }

            public void setProductFeature(String productFeature) {
                this.productFeature = productFeature;
            }

            public String getBuildingArea() {
                return buildingArea;
            }

            public void setBuildingArea(String buildingArea) {
                this.buildingArea = buildingArea;
            }

            public String getSaleStatus() {
                return saleStatus;
            }

            public void setSaleStatus(String saleStatus) {
                this.saleStatus = saleStatus;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getAmountIncentiveId() {
                return amountIncentiveId;
            }

            public void setAmountIncentiveId(String amountIncentiveId) {
                this.amountIncentiveId = amountIncentiveId;
            }

            public String getGuideRuleId() {
                return guideRuleId;
            }

            public void setGuideRuleId(String guideRuleId) {
                this.guideRuleId = guideRuleId;
            }

            public String getIsPhone() {
                return isPhone;
            }

            public void setIsPhone(String isPhone) {
                this.isPhone = isPhone;
            }

            public String getDigit() {
                return digit;
            }

            public void setDigit(String digit) {
                this.digit = digit;
            }

            public String getIsPapers() {
                return isPapers;
            }

            public void setIsPapers(String isPapers) {
                this.isPapers = isPapers;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPercent() {
                return percent;
            }

            public void setPercent(String percent) {
                this.percent = percent;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getContractAmount() {
                return contractAmount;
            }

            public void setContractAmount(String contractAmount) {
                this.contractAmount = contractAmount;
            }

            public String getContractPercent() {
                return contractPercent;
            }

            public void setContractPercent(String contractPercent) {
                this.contractPercent = contractPercent;
            }

            public String getSecondsAmount() {
                return secondsAmount;
            }

            public void setSecondsAmount(String secondsAmount) {
                this.secondsAmount = secondsAmount;
            }

            public String getIsSeconds() {
                return isSeconds;
            }

            public void setIsSeconds(String isSeconds) {
                this.isSeconds = isSeconds;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
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

            public String getSalesOfficeLocation() {
                return salesOfficeLocation;
            }

            public void setSalesOfficeLocation(String salesOfficeLocation) {
                this.salesOfficeLocation = salesOfficeLocation;
            }

            public String getOnlineState() {
                return onlineState;
            }

            public void setOnlineState(String onlineState) {
                this.onlineState = onlineState;
            }

            public int getProductTypeSize() {
                return productTypeSize;
            }

            public void setProductTypeSize(int productTypeSize) {
                this.productTypeSize = productTypeSize;
            }

            public String getProductUnitPrice() {
                return productUnitPrice;
            }

            public void setProductUnitPrice(String productUnitPrice) {
                this.productUnitPrice = productUnitPrice;
            }

            public String getProductTotalPrice() {
                return productTotalPrice;
            }

            public void setProductTotalPrice(String productTotalPrice) {
                this.productTotalPrice = productTotalPrice;
            }

            public String getReferenceToatlPrice() {
                return referenceToatlPrice;
            }

            public void setReferenceToatlPrice(String referenceToatlPrice) {
                this.referenceToatlPrice = referenceToatlPrice;
            }

            public String getAreaIntervalStart() {
                return areaIntervalStart;
            }

            public void setAreaIntervalStart(String areaIntervalStart) {
                this.areaIntervalStart = areaIntervalStart;
            }

            public String getAreaIntervalEnd() {
                return areaIntervalEnd;
            }

            public void setAreaIntervalEnd(String areaIntervalEnd) {
                this.areaIntervalEnd = areaIntervalEnd;
            }

            public String getAreaInterval() {
                return areaInterval;
            }

            public void setAreaInterval(String areaInterval) {
                this.areaInterval = areaInterval;
            }

            public String getMonetaryUnit() {
                return monetaryUnit;
            }

            public void setMonetaryUnit(String monetaryUnit) {
                this.monetaryUnit = monetaryUnit;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getSecondPay() {
                return secondPay;
            }

            public void setSecondPay(String secondPay) {
                this.secondPay = secondPay;
            }

            public String getHot() {
                return hot;
            }

            public void setHot(String hot) {
                this.hot = hot;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getNation() {
                return nation;
            }

            public void setNation(String nation) {
                this.nation = nation;
            }

            public String getSearchContent() {
                return searchContent;
            }

            public void setSearchContent(String searchContent) {
                this.searchContent = searchContent;
            }

            public String getSearchName() {
                return searchName;
            }

            public void setSearchName(String searchName) {
                this.searchName = searchName;
            }

            public String getAttacheId() {
                return attacheId;
            }

            public void setAttacheId(String attacheId) {
                this.attacheId = attacheId;
            }

            public String getBusinessType() {
                return businessType;
            }

            public void setBusinessType(String businessType) {
                this.businessType = businessType;
            }

            public String getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(String collectionId) {
                this.collectionId = collectionId;
            }

            public String getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(String isCollection) {
                this.isCollection = isCollection;
            }

            public String getFfAttacheList() {
                return ffAttacheList;
            }

            public void setFfAttacheList(String ffAttacheList) {
                this.ffAttacheList = ffAttacheList;
            }

            public String getBrokerUserPhone() {
                return brokerUserPhone;
            }

            public void setBrokerUserPhone(String brokerUserPhone) {
                this.brokerUserPhone = brokerUserPhone;
            }

            public String getProjectLabel() {
                return projectLabel;
            }

            public void setProjectLabel(String projectLabel) {
                this.projectLabel = projectLabel;
            }

            public String getProjectLabelStr() {
                return projectLabelStr;
            }

            public void setProjectLabelStr(String projectLabelStr) {
                this.projectLabelStr = projectLabelStr;
            }

            public String getComprehensiveSorting() {
                return comprehensiveSorting;
            }

            public void setComprehensiveSorting(String comprehensiveSorting) {
                this.comprehensiveSorting = comprehensiveSorting;
            }

            public String getComprehensiveSortingStr() {
                return comprehensiveSortingStr;
            }

            public void setComprehensiveSortingStr(String comprehensiveSortingStr) {
                this.comprehensiveSortingStr = comprehensiveSortingStr;
            }

            public String getApartment() {
                return apartment;
            }

            public void setApartment(String apartment) {
                this.apartment = apartment;
            }

            public String getProjectPriceStart() {
                return projectPriceStart;
            }

            public void setProjectPriceStart(String projectPriceStart) {
                this.projectPriceStart = projectPriceStart;
            }

            public String getProjectPriceEnd() {
                return projectPriceEnd;
            }

            public void setProjectPriceEnd(String projectPriceEnd) {
                this.projectPriceEnd = projectPriceEnd;
            }

            public String getAreaSection() {
                return areaSection;
            }

            public void setAreaSection(String areaSection) {
                this.areaSection = areaSection;
            }

            public String getFfProjectTrait() {
                return ffProjectTrait;
            }

            public void setFfProjectTrait(String ffProjectTrait) {
                this.ffProjectTrait = ffProjectTrait;
            }

            public String getProcuctType() {
                return procuctType;
            }

            public void setProcuctType(String procuctType) {
                this.procuctType = procuctType;
            }

            public String getFitmentState() {
                return fitmentState;
            }

            public void setFitmentState(String fitmentState) {
                this.fitmentState = fitmentState;
            }
        }
    }
}
