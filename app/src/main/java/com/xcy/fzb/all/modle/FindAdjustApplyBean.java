package com.xcy.fzb.all.modle;

import java.util.List;

public class FindAdjustApplyBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"showData":[{"value":"马云","key":"客户名称"},{"value":"18888888888","key":"客户电话"},{"value":"大理的小院子","key":"报备项目"},{"value":"2019-10-11 10:51:04","key":"报备时间"}],"procuctTypeData":[{"id":"747bc38da3bb40b28ec955cb9b1f91d0","remarks":"","createBy":"","createDate":"2019-03-10 15:36:16","updateBy":"","updateDate":"2019-03-10 15:36:16","label":"住宅","value":"1","sort":"1","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"f53d7f3765284dc893d4dab2b6ba29b8","remarks":"","createBy":"","createDate":"2019-03-10 15:44:01","updateBy":"","updateDate":"2019-03-10 15:44:01","label":"公寓","value":"2","sort":"2","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"a4686a460bbc492aadcf2984cfffa125","remarks":"","createBy":"","createDate":"2019-03-10 15:44:14","updateBy":"","updateDate":"2019-03-10 15:44:14","label":"写字间","value":"3","sort":"3","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"68e8f43b25b148bea5926d686f997a0e","remarks":"","createBy":"","createDate":"2019-03-10 15:49:12","updateBy":"","updateDate":"2019-03-10 15:49:12","label":"商铺","value":"4","sort":"4","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"0c1d91e7e9af42a29c7ea4101d27bb38","remarks":"","createBy":"","createDate":"2019-03-10 15:51:21","updateBy":"","updateDate":"2019-03-10 15:51:21","label":"别墅","value":"5","sort":"5","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}}],"ffServerTrade":{"id":"d64a30e29c92443e9a1ec726ba346eb2","remarks":"","createBy":"","createDate":"2019-10-11 10:55:58","updateBy":"","updateDate":"","preparationId":"da8c1ad64f7c464caedb0cd9c007a1f6","customerId":"f1801c029ea241a7b95fede52559df6e","roomNumber":"258栋100单元2508室","apartment":"6","area":"100","price":"10000","totalPrice":"1000000","paymentMethod":"按揭","tradeDate":"2019年10月11日","commissionId":"8ff4dc96c8344e2a9189c7832dea851c","procuctType":"5","gender":"男","relation":"本人","fullName":"马云","phone":"15358582365","idNumber":"220107198608082356","tradeStatus":"0","tradeComment":"","tradeAutnComment":"","flag":"0","applyBy":"","applyDate":"","auditBy":"","auditDate":"","submitBy":"","submitDate":"","auditSubmitBy":"","auditSubmitDate":"2019-10-11 10:55:58","tung":"258","several":"100","room":"2508","commission":{"id":"8ff4dc96c8344e2a9189c7832dea851c","remarks":"","createBy":"","createDate":"2019-07-02 10:38:16","updateBy":"","updateDate":"2019-09-25 14:09:04","datePicker":"","startDate":"2019-03-01","endDate":"2019-12-31","mainTitle":"住宅佣金","type":"1","percent":3,"amount":"","contractPercent":4,"contractAmount":"","isSeconds":"0","secondsAmount":"","executeState":"","project":{"id":"7c2eedbf85f149bc95c736653e31ee6b","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"大理的小院子","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"onlineState":"1","isProjectShow":1,"percentStr":"3.0","contractPercentStr":"4.0","currentDate":"","commissionFormat":"总房款的3.0%","secondsFormat":"无秒结","teams":"","teamNames":""}},"isEarnestMoney":"0","comesBackData":{"customerId":"f1801c029ea241a7b95fede52559df6e","preparationId":"da8c1ad64f7c464caedb0cd9c007a1f6","economicId":"acc10b71120648d0b49fdcfafb70596b","projectId":"7c2eedbf85f149bc95c736653e31ee6b"},"isTrade":"1"}
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
         * showData : [{"value":"马云","key":"客户名称"},{"value":"18888888888","key":"客户电话"},{"value":"大理的小院子","key":"报备项目"},{"value":"2019-10-11 10:51:04","key":"报备时间"}]
         * procuctTypeData : [{"id":"747bc38da3bb40b28ec955cb9b1f91d0","remarks":"","createBy":"","createDate":"2019-03-10 15:36:16","updateBy":"","updateDate":"2019-03-10 15:36:16","label":"住宅","value":"1","sort":"1","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"f53d7f3765284dc893d4dab2b6ba29b8","remarks":"","createBy":"","createDate":"2019-03-10 15:44:01","updateBy":"","updateDate":"2019-03-10 15:44:01","label":"公寓","value":"2","sort":"2","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"a4686a460bbc492aadcf2984cfffa125","remarks":"","createBy":"","createDate":"2019-03-10 15:44:14","updateBy":"","updateDate":"2019-03-10 15:44:14","label":"写字间","value":"3","sort":"3","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"68e8f43b25b148bea5926d686f997a0e","remarks":"","createBy":"","createDate":"2019-03-10 15:49:12","updateBy":"","updateDate":"2019-03-10 15:49:12","label":"商铺","value":"4","sort":"4","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"0c1d91e7e9af42a29c7ea4101d27bb38","remarks":"","createBy":"","createDate":"2019-03-10 15:51:21","updateBy":"","updateDate":"2019-03-10 15:51:21","label":"别墅","value":"5","sort":"5","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}}]
         * ffServerTrade : {"id":"d64a30e29c92443e9a1ec726ba346eb2","remarks":"","createBy":"","createDate":"2019-10-11 10:55:58","updateBy":"","updateDate":"","preparationId":"da8c1ad64f7c464caedb0cd9c007a1f6","customerId":"f1801c029ea241a7b95fede52559df6e","roomNumber":"258栋100单元2508室","apartment":"6","area":"100","price":"10000","totalPrice":"1000000","paymentMethod":"按揭","tradeDate":"2019年10月11日","commissionId":"8ff4dc96c8344e2a9189c7832dea851c","procuctType":"5","gender":"男","relation":"本人","fullName":"马云","phone":"15358582365","idNumber":"220107198608082356","tradeStatus":"0","tradeComment":"","tradeAutnComment":"","flag":"0","applyBy":"","applyDate":"","auditBy":"","auditDate":"","submitBy":"","submitDate":"","auditSubmitBy":"","auditSubmitDate":"2019-10-11 10:55:58","tung":"258","several":"100","room":"2508","commission":{"id":"8ff4dc96c8344e2a9189c7832dea851c","remarks":"","createBy":"","createDate":"2019-07-02 10:38:16","updateBy":"","updateDate":"2019-09-25 14:09:04","datePicker":"","startDate":"2019-03-01","endDate":"2019-12-31","mainTitle":"住宅佣金","type":"1","percent":3,"amount":"","contractPercent":4,"contractAmount":"","isSeconds":"0","secondsAmount":"","executeState":"","project":{"id":"7c2eedbf85f149bc95c736653e31ee6b","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"大理的小院子","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"onlineState":"1","isProjectShow":1,"percentStr":"3.0","contractPercentStr":"4.0","currentDate":"","commissionFormat":"总房款的3.0%","secondsFormat":"无秒结","teams":"","teamNames":""}}
         * isEarnestMoney : 0
         * comesBackData : {"customerId":"f1801c029ea241a7b95fede52559df6e","preparationId":"da8c1ad64f7c464caedb0cd9c007a1f6","economicId":"acc10b71120648d0b49fdcfafb70596b","projectId":"7c2eedbf85f149bc95c736653e31ee6b"}
         * isTrade : 1
         */

        private FfServerTradeBean ffServerTrade;
        private String isEarnestMoney;
        private ComesBackDataBean comesBackData;
        private String isTrade;
        private List<ShowDataBean> showData;
        private List<ProcuctTypeDataBean> procuctTypeData;

        public FfServerTradeBean getFfServerTrade() {
            return ffServerTrade;
        }

        public void setFfServerTrade(FfServerTradeBean ffServerTrade) {
            this.ffServerTrade = ffServerTrade;
        }

        public String getIsEarnestMoney() {
            return isEarnestMoney;
        }

        public void setIsEarnestMoney(String isEarnestMoney) {
            this.isEarnestMoney = isEarnestMoney;
        }

        public ComesBackDataBean getComesBackData() {
            return comesBackData;
        }

        public void setComesBackData(ComesBackDataBean comesBackData) {
            this.comesBackData = comesBackData;
        }

        public String getIsTrade() {
            return isTrade;
        }

        public void setIsTrade(String isTrade) {
            this.isTrade = isTrade;
        }

        public List<ShowDataBean> getShowData() {
            return showData;
        }

        public void setShowData(List<ShowDataBean> showData) {
            this.showData = showData;
        }

        public List<ProcuctTypeDataBean> getProcuctTypeData() {
            return procuctTypeData;
        }

        public void setProcuctTypeData(List<ProcuctTypeDataBean> procuctTypeData) {
            this.procuctTypeData = procuctTypeData;
        }

        public static class FfServerTradeBean {
            /**
             * id : d64a30e29c92443e9a1ec726ba346eb2
             * remarks :
             * createBy :
             * createDate : 2019-10-11 10:55:58
             * updateBy :
             * updateDate :
             * preparationId : da8c1ad64f7c464caedb0cd9c007a1f6
             * customerId : f1801c029ea241a7b95fede52559df6e
             * roomNumber : 258栋100单元2508室
             * apartment : 6
             * area : 100
             * price : 10000
             * totalPrice : 1000000
             * paymentMethod : 按揭
             * tradeDate : 2019年10月11日
             * commissionId : 8ff4dc96c8344e2a9189c7832dea851c
             * procuctType : 5
             * gender : 男
             * relation : 本人
             * fullName : 马云
             * phone : 15358582365
             * idNumber : 220107198608082356
             * tradeStatus : 0
             * tradeComment :
             * tradeAutnComment :
             * flag : 0
             * applyBy :
             * applyDate :
             * auditBy :
             * auditDate :
             * submitBy :
             * submitDate :
             * auditSubmitBy :
             * auditSubmitDate : 2019-10-11 10:55:58
             * tung : 258
             * several : 100
             * room : 2508
             * commission : {"id":"8ff4dc96c8344e2a9189c7832dea851c","remarks":"","createBy":"","createDate":"2019-07-02 10:38:16","updateBy":"","updateDate":"2019-09-25 14:09:04","datePicker":"","startDate":"2019-03-01","endDate":"2019-12-31","mainTitle":"住宅佣金","type":"1","percent":3,"amount":"","contractPercent":4,"contractAmount":"","isSeconds":"0","secondsAmount":"","executeState":"","project":{"id":"7c2eedbf85f149bc95c736653e31ee6b","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"大理的小院子","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"onlineState":"1","isProjectShow":1,"percentStr":"3.0","contractPercentStr":"4.0","currentDate":"","commissionFormat":"总房款的3.0%","secondsFormat":"无秒结","teams":"","teamNames":""}
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String preparationId;
            private String customerId;
            private String roomNumber;
            private String apartment;
            private String area;
            private String price;
            private String totalPrice;
            private String paymentMethod;
            private String tradeDate;
            private String commissionId;
            private String procuctType;
            private String gender;
            private String relation;
            private String fullName;
            private String phone;
            private String idNumber;
            private String tradeStatus;
            private String tradeComment;
            private String tradeAutnComment;
            private String flag;
            private String applyBy;
            private String applyDate;
            private String auditBy;
            private String auditDate;
            private String submitBy;
            private String submitDate;
            private String auditSubmitBy;
            private String auditSubmitDate;
            private String tung;
            private String several;
            private String room;
            private CommissionBean commission;

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

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getRoomNumber() {
                return roomNumber;
            }

            public void setRoomNumber(String roomNumber) {
                this.roomNumber = roomNumber;
            }

            public String getApartment() {
                return apartment;
            }

            public void setApartment(String apartment) {
                this.apartment = apartment;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }

            public String getPaymentMethod() {
                return paymentMethod;
            }

            public void setPaymentMethod(String paymentMethod) {
                this.paymentMethod = paymentMethod;
            }

            public String getTradeDate() {
                return tradeDate;
            }

            public void setTradeDate(String tradeDate) {
                this.tradeDate = tradeDate;
            }

            public String getCommissionId() {
                return commissionId;
            }

            public void setCommissionId(String commissionId) {
                this.commissionId = commissionId;
            }

            public String getProcuctType() {
                return procuctType;
            }

            public void setProcuctType(String procuctType) {
                this.procuctType = procuctType;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
            }

            public String getTradeStatus() {
                return tradeStatus;
            }

            public void setTradeStatus(String tradeStatus) {
                this.tradeStatus = tradeStatus;
            }

            public String getTradeComment() {
                return tradeComment;
            }

            public void setTradeComment(String tradeComment) {
                this.tradeComment = tradeComment;
            }

            public String getTradeAutnComment() {
                return tradeAutnComment;
            }

            public void setTradeAutnComment(String tradeAutnComment) {
                this.tradeAutnComment = tradeAutnComment;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getApplyBy() {
                return applyBy;
            }

            public void setApplyBy(String applyBy) {
                this.applyBy = applyBy;
            }

            public String getApplyDate() {
                return applyDate;
            }

            public void setApplyDate(String applyDate) {
                this.applyDate = applyDate;
            }

            public String getAuditBy() {
                return auditBy;
            }

            public void setAuditBy(String auditBy) {
                this.auditBy = auditBy;
            }

            public String getAuditDate() {
                return auditDate;
            }

            public void setAuditDate(String auditDate) {
                this.auditDate = auditDate;
            }

            public String getSubmitBy() {
                return submitBy;
            }

            public void setSubmitBy(String submitBy) {
                this.submitBy = submitBy;
            }

            public String getSubmitDate() {
                return submitDate;
            }

            public void setSubmitDate(String submitDate) {
                this.submitDate = submitDate;
            }

            public String getAuditSubmitBy() {
                return auditSubmitBy;
            }

            public void setAuditSubmitBy(String auditSubmitBy) {
                this.auditSubmitBy = auditSubmitBy;
            }

            public String getAuditSubmitDate() {
                return auditSubmitDate;
            }

            public void setAuditSubmitDate(String auditSubmitDate) {
                this.auditSubmitDate = auditSubmitDate;
            }

            public String getTung() {
                return tung;
            }

            public void setTung(String tung) {
                this.tung = tung;
            }

            public String getSeveral() {
                return several;
            }

            public void setSeveral(String several) {
                this.several = several;
            }

            public String getRoom() {
                return room;
            }

            public void setRoom(String room) {
                this.room = room;
            }

            public CommissionBean getCommission() {
                return commission;
            }

            public void setCommission(CommissionBean commission) {
                this.commission = commission;
            }

            public static class CommissionBean {
                /**
                 * id : 8ff4dc96c8344e2a9189c7832dea851c
                 * remarks :
                 * createBy :
                 * createDate : 2019-07-02 10:38:16
                 * updateBy :
                 * updateDate : 2019-09-25 14:09:04
                 * datePicker :
                 * startDate : 2019-03-01
                 * endDate : 2019-12-31
                 * mainTitle : 住宅佣金
                 * type : 1
                 * percent : 3
                 * amount :
                 * contractPercent : 4
                 * contractAmount :
                 * isSeconds : 0
                 * secondsAmount :
                 * executeState :
                 * project : {"id":"7c2eedbf85f149bc95c736653e31ee6b","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"大理的小院子","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""}
                 * onlineState : 1
                 * isProjectShow : 1
                 * percentStr : 3.0
                 * contractPercentStr : 4.0
                 * currentDate :
                 * commissionFormat : 总房款的3.0%
                 * secondsFormat : 无秒结
                 * teams :
                 * teamNames :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String datePicker;
                private String startDate;
                private String endDate;
                private String mainTitle;
                private String type;
                private double percent;
                private String amount;
                private int contractPercent;
                private String contractAmount;
                private String isSeconds;
                private String secondsAmount;
                private String executeState;
                private ProjectBean project;
                private String onlineState;
                private int isProjectShow;
                private String percentStr;
                private String contractPercentStr;
                private String currentDate;
                private String commissionFormat;
                private String secondsFormat;
                private String teams;
                private String teamNames;

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

                public String getDatePicker() {
                    return datePicker;
                }

                public void setDatePicker(String datePicker) {
                    this.datePicker = datePicker;
                }

                public String getStartDate() {
                    return startDate;
                }

                public void setStartDate(String startDate) {
                    this.startDate = startDate;
                }

                public String getEndDate() {
                    return endDate;
                }

                public void setEndDate(String endDate) {
                    this.endDate = endDate;
                }

                public String getMainTitle() {
                    return mainTitle;
                }

                public void setMainTitle(String mainTitle) {
                    this.mainTitle = mainTitle;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public double getPercent() {
                    return percent;
                }

                public void setPercent(double percent) {
                    this.percent = percent;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public int getContractPercent() {
                    return contractPercent;
                }

                public void setContractPercent(int contractPercent) {
                    this.contractPercent = contractPercent;
                }

                public String getContractAmount() {
                    return contractAmount;
                }

                public void setContractAmount(String contractAmount) {
                    this.contractAmount = contractAmount;
                }

                public String getIsSeconds() {
                    return isSeconds;
                }

                public void setIsSeconds(String isSeconds) {
                    this.isSeconds = isSeconds;
                }

                public String getSecondsAmount() {
                    return secondsAmount;
                }

                public void setSecondsAmount(String secondsAmount) {
                    this.secondsAmount = secondsAmount;
                }

                public String getExecuteState() {
                    return executeState;
                }

                public void setExecuteState(String executeState) {
                    this.executeState = executeState;
                }

                public ProjectBean getProject() {
                    return project;
                }

                public void setProject(ProjectBean project) {
                    this.project = project;
                }

                public String getOnlineState() {
                    return onlineState;
                }

                public void setOnlineState(String onlineState) {
                    this.onlineState = onlineState;
                }

                public int getIsProjectShow() {
                    return isProjectShow;
                }

                public void setIsProjectShow(int isProjectShow) {
                    this.isProjectShow = isProjectShow;
                }

                public String getPercentStr() {
                    return percentStr;
                }

                public void setPercentStr(String percentStr) {
                    this.percentStr = percentStr;
                }

                public String getContractPercentStr() {
                    return contractPercentStr;
                }

                public void setContractPercentStr(String contractPercentStr) {
                    this.contractPercentStr = contractPercentStr;
                }

                public String getCurrentDate() {
                    return currentDate;
                }

                public void setCurrentDate(String currentDate) {
                    this.currentDate = currentDate;
                }

                public String getCommissionFormat() {
                    return commissionFormat;
                }

                public void setCommissionFormat(String commissionFormat) {
                    this.commissionFormat = commissionFormat;
                }

                public String getSecondsFormat() {
                    return secondsFormat;
                }

                public void setSecondsFormat(String secondsFormat) {
                    this.secondsFormat = secondsFormat;
                }

                public String getTeams() {
                    return teams;
                }

                public void setTeams(String teams) {
                    this.teams = teams;
                }

                public String getTeamNames() {
                    return teamNames;
                }

                public void setTeamNames(String teamNames) {
                    this.teamNames = teamNames;
                }

                public static class ProjectBean {
                    /**
                     * id : 7c2eedbf85f149bc95c736653e31ee6b
                     * remarks :
                     * createBy :
                     * createDate :
                     * updateBy :
                     * updateDate :
                     * projectName : 大理的小院子
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
            }
        }

        public static class ComesBackDataBean {
            /**
             * customerId : f1801c029ea241a7b95fede52559df6e
             * preparationId : da8c1ad64f7c464caedb0cd9c007a1f6
             * economicId : acc10b71120648d0b49fdcfafb70596b
             * projectId : 7c2eedbf85f149bc95c736653e31ee6b
             */

            private String customerId;
            private String preparationId;
            private String economicId;
            private String projectId;

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getEconomicId() {
                return economicId;
            }

            public void setEconomicId(String economicId) {
                this.economicId = economicId;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }
        }

        public static class ShowDataBean {
            /**
             * value : 马云
             * key : 客户名称
             */

            private String value;
            private String key;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }

        public static class ProcuctTypeDataBean {
            /**
             * id : 747bc38da3bb40b28ec955cb9b1f91d0
             * remarks :
             * createBy :
             * createDate : 2019-03-10 15:36:16
             * updateBy :
             * updateDate : 2019-03-10 15:36:16
             * label : 住宅
             * value : 1
             * sort : 1
             * dictType : {"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String label;
            private String value;
            private String sort;
            private DictTypeBean dictType;

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

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public DictTypeBean getDictType() {
                return dictType;
            }

            public void setDictType(DictTypeBean dictType) {
                this.dictType = dictType;
            }

            public static class DictTypeBean {
                /**
                 * id : d506d59af7ab4063a802650f3aba366d
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * type :
                 * description :
                 * dictValueList : []
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String type;
                private String description;
                private List<?> dictValueList;

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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public List<?> getDictValueList() {
                    return dictValueList;
                }

                public void setDictValueList(List<?> dictValueList) {
                    this.dictValueList = dictValueList;
                }
            }
        }
    }
}
