package com.xcy.fzb.shopping_guide.modle;

import java.util.List;

public class EconomicCircleBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"commentList":[{"id":"d33ae8d7166b46e8bc80db78f166736d","remarks":"","createBy":"","createDate":"2019-04-14 16:02:47","updateBy":"","updateDate":"","targetId":"11a420244df445d2983f10c1e35b8b07","user":{"id":"f9c564e067e84192a6d80a4e33227a00","name":"yjl","photo":""},"type":"2","comment":"记得记得接电话","imgUrl":"","pid":""}],"giveList":[{"id":"f9c1786e7f99433391321c0022e2a910","remarks":"","createBy":"","createDate":"2019-08-21 11:21:25","updateBy":"","updateDate":"","targetId":"a18040d003454fd19afd8452cc59a9bd","user":"","type":"1","comment":"","imgUrl":"","status":"","pid":""}],"circle":{"id":"a18040d003454fd19afd8452cc59a9bd","remarks":"","createBy":"","createDate":"2019-08-21 10:26:22","updateBy":"","updateDate":"","content":"测试","imgUrl":"android.graphics.Bitmap@dbcd110","likeNum":"1","commentNum":"","city":"长春市","createByName":"cs5","createByPhoto":"/fangfang/static/common/images/flat-avatar.png","isLike":"","cityCompany":"c241db93cbd247f5a8aadf501806b56a"}}
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
         * commentList : [{"id":"d33ae8d7166b46e8bc80db78f166736d","remarks":"","createBy":"","createDate":"2019-04-14 16:02:47","updateBy":"","updateDate":"","targetId":"11a420244df445d2983f10c1e35b8b07","user":{"id":"f9c564e067e84192a6d80a4e33227a00","name":"yjl","photo":""},"type":"2","comment":"记得记得接电话","imgUrl":"","pid":""}]
         * giveList : [{"id":"f9c1786e7f99433391321c0022e2a910","remarks":"","createBy":"","createDate":"2019-08-21 11:21:25","updateBy":"","updateDate":"","targetId":"a18040d003454fd19afd8452cc59a9bd","user":"","type":"1","comment":"","imgUrl":"","status":"","pid":""}]
         * circle : {"id":"a18040d003454fd19afd8452cc59a9bd","remarks":"","createBy":"","createDate":"2019-08-21 10:26:22","updateBy":"","updateDate":"","content":"测试","imgUrl":"android.graphics.Bitmap@dbcd110","likeNum":"1","commentNum":"","city":"长春市","createByName":"cs5","createByPhoto":"/fangfang/static/common/images/flat-avatar.png","isLike":"","cityCompany":"c241db93cbd247f5a8aadf501806b56a"}
         */

        private CircleBean circle;
        private List<CommentListBean> commentList;
        private List<GiveListBean> giveList;

        public CircleBean getCircle() {
            return circle;
        }

        public void setCircle(CircleBean circle) {
            this.circle = circle;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public List<GiveListBean> getGiveList() {
            return giveList;
        }

        public void setGiveList(List<GiveListBean> giveList) {
            this.giveList = giveList;
        }

        public static class CircleBean {
            /**
             * id : a18040d003454fd19afd8452cc59a9bd
             * remarks :
             * createBy :
             * createDate : 2019-08-21 10:26:22
             * updateBy :
             * updateDate :
             * content : 测试
             * imgUrl : android.graphics.Bitmap@dbcd110
             * likeNum : 1
             * commentNum :
             * city : 长春市
             * createByName : cs5
             * createByPhoto : /fangfang/static/common/images/flat-avatar.png
             * isLike :
             * cityCompany : c241db93cbd247f5a8aadf501806b56a
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String content;
            private String imgUrl;
            private String likeNum;
            private String commentNum;
            private String city;
            private String createByName;
            private String createByPhoto;
            private String isLike;
            private String cityCompany;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getLikeNum() {
                return likeNum;
            }

            public void setLikeNum(String likeNum) {
                this.likeNum = likeNum;
            }

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCreateByName() {
                return createByName;
            }

            public void setCreateByName(String createByName) {
                this.createByName = createByName;
            }

            public String getCreateByPhoto() {
                return createByPhoto;
            }

            public void setCreateByPhoto(String createByPhoto) {
                this.createByPhoto = createByPhoto;
            }

            public String getIsLike() {
                return isLike;
            }

            public void setIsLike(String isLike) {
                this.isLike = isLike;
            }

            public String getCityCompany() {
                return cityCompany;
            }

            public void setCityCompany(String cityCompany) {
                this.cityCompany = cityCompany;
            }
        }

        public static class CommentListBean {
            /**
             * id : d33ae8d7166b46e8bc80db78f166736d
             * remarks :
             * createBy :
             * createDate : 2019-04-14 16:02:47
             * updateBy :
             * updateDate :
             * targetId : 11a420244df445d2983f10c1e35b8b07
             * user : {"id":"f9c564e067e84192a6d80a4e33227a00","name":"yjl","photo":""}
             * type : 2
             * comment : 记得记得接电话
             * imgUrl :
             * pid :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String targetId;
            private UserBean user;
            private String type;
            private String comment;
            private String imgUrl;
            private String pid;

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

            public String getTargetId() {
                return targetId;
            }

            public void setTargetId(String targetId) {
                this.targetId = targetId;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public static class UserBean {
                /**
                 * id : f9c564e067e84192a6d80a4e33227a00
                 * name : yjl
                 * photo :
                 */

                private String id;
                private String name;
                private String photo;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }
            }
        }

        public static class GiveListBean {
            /**
             * id : f9c1786e7f99433391321c0022e2a910
             * remarks :
             * createBy :
             * createDate : 2019-08-21 11:21:25
             * updateBy :
             * updateDate :
             * targetId : a18040d003454fd19afd8452cc59a9bd
             * user :
             * type : 1
             * comment :
             * imgUrl :
             * status :
             * pid :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String targetId;
            private String user;
            private String type;
            private String comment;
            private String imgUrl;
            private String status;
            private String pid;

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

            public String getTargetId() {
                return targetId;
            }

            public void setTargetId(String targetId) {
                this.targetId = targetId;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }
        }
    }
}
