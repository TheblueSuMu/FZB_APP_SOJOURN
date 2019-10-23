package com.xcy.fzb.project_side.service;

import com.xcy.fzb.project_side.modle.AddClientBean;
import com.xcy.fzb.project_side.modle.AddPhotoBean;
import com.xcy.fzb.project_side.modle.BorkerageDownBean;
import com.xcy.fzb.project_side.modle.BorkerageUpBean;
import com.xcy.fzb.project_side.modle.BrokerBean;
import com.xcy.fzb.project_side.modle.BusinessBean;
import com.xcy.fzb.project_side.modle.ChangePassowrdBean;
import com.xcy.fzb.project_side.modle.ChangePhoneBean;
import com.xcy.fzb.project_side.modle.ChangeSexBean;
import com.xcy.fzb.project_side.modle.CityBean;
import com.xcy.fzb.project_side.modle.ClientBean;
import com.xcy.fzb.project_side.modle.ClientCommissionsBean;
import com.xcy.fzb.project_side.modle.ClientFragmentBean;
import com.xcy.fzb.project_side.modle.DetailsBean;
import com.xcy.fzb.project_side.modle.DictListBean;
import com.xcy.fzb.project_side.modle.DisclaimerBean;
import com.xcy.fzb.project_side.modle.DiscussBean;
import com.xcy.fzb.project_side.modle.DynamicBean;
import com.xcy.fzb.project_side.modle.EconomicCircleBean;
import com.xcy.fzb.project_side.modle.ExemplaryUserBean;
import com.xcy.fzb.project_side.modle.FailureBean;
import com.xcy.fzb.project_side.modle.FeedBackBean;
import com.xcy.fzb.project_side.modle.FinanceBean;
import com.xcy.fzb.project_side.modle.GetLandLineBean;
import com.xcy.fzb.project_side.modle.GetLandLineTimeBean;
import com.xcy.fzb.project_side.modle.GoodNewsBean;
import com.xcy.fzb.project_side.modle.HomeBean;
import com.xcy.fzb.project_side.modle.HomeListBean;
import com.xcy.fzb.project_side.modle.HotBean;
import com.xcy.fzb.project_side.modle.ImgData;
import com.xcy.fzb.project_side.modle.LandSaveBean;
import com.xcy.fzb.project_side.modle.LikeNumBean;
import com.xcy.fzb.project_side.modle.LoginUserBean;
import com.xcy.fzb.project_side.modle.MessageBean;
import com.xcy.fzb.project_side.modle.MessageBean2;
import com.xcy.fzb.project_side.modle.MoneyCountBean;
import com.xcy.fzb.project_side.modle.MyClientFragmentBean;
import com.xcy.fzb.project_side.modle.NickNameBean;
import com.xcy.fzb.project_side.modle.OperationBean;
import com.xcy.fzb.project_side.modle.PersonalPhotoBean;
import com.xcy.fzb.project_side.modle.ReceivableBean;
import com.xcy.fzb.project_side.modle.SellingPointsBean;
import com.xcy.fzb.project_side.modle.TradeSaveBean;
import com.xcy.fzb.project_side.modle.UserBean;
import com.xcy.fzb.project_side.modle.UserMessageBean;
import com.xcy.fzb.project_side.modle.VerificationBean;
import com.xcy.fzb.project_side.modle.VisitSaveBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MyService {

    //http://192.168.79.28:8080/webapi/post?username=itheima&password=123
    //@POST标注当前方法 使用POST请求
    //@Field用来标注post请求参数名
    //@FormUrlEncoded与@Post配合使用，要求对post的参数进行网络编码


    //专案端首页数据
    @POST("specialSelect/findData?")
    Observable<HomeBean> getHomeList(@Query("userId") String userId, @Query("beforeDate") String beforeDate, @Query("afterDate") String afterDate, @Query("type") String type);

    //专案端详情运营数据
    @POST("specialSelect/receivableMoneySingle?")
    Observable<OperationBean> getOperationList(@Query("userId") String userId, @Query("projectId") String projectId, @Query("beforeDate") String beforeDate, @Query("afterDate") String afterDate, @Query("type") String type);

    //专案端详情财务数据
    @POST("specialSelect/operationSingle?")
    Observable<FinanceBean> getFinanceList(@Query("userId") String userId, @Query("projectId") String projectId, @Query("beforeDate") String beforeDate, @Query("afterDate") String afterDate, @Query("type") String type);

    //专案端详情趋势图数据
    @POST("specialSelect/businessTrendSingle?")
    Observable<BusinessBean> getBusinesseList(@Query("userId") String userId, @Query("projectId") String projectId, @Query("beforeDate") String beforeDate, @Query("afterDate") String afterDate, @Query("type") String type, @Query("status") String status);

    //专案端首页列表数据
    @POST("specialSelect/specialList?")
    Observable<HomeListBean> getHomeBeanList(@Query("userId") String userId, @Query("projectType") String projectType);

    //专案端详情页所有数据
    @POST("specialSelect/specialDetail?")
    Observable<DetailsBean> getDetailsBeanList(@Query("userId") String userId, @Query("projectId") String projectId);

    //专案端佣金上半部分数据
    @POST("specialSelect/findReceivableMoneyCount?")
    Observable<MoneyCountBean> getMoneyCountList(@Query("userId") String userId, @Query("projectId") String projectId);

    //专案端佣金下半部分数据
    @POST("specialSelect/findReceivableMoneyList?")
    Observable<ReceivableBean> getMoneyList(@Query("userId") String userId, @Query("search") String search, @Query("status") String status, @Query("projectType") String projectType, @Query("projectId") String projectId, @Query("startTime") String startTime, @Query("endTime") String endTime);

    //专案端用户个人信息数据
    @POST("commonSelect/getUserInfo?")
    Observable<UserBean> getUserBean(@Query("userId") String userId);

    //专案端登录
    @POST("commonSelect/login")
    Observable<ExemplaryUserBean> getExemplaryLogin(@Query("username") String username, @Query("password") String password, @Query("type") String type);

    //首页城市列表数据
    @POST("commonSelect/findCityForParentId")
    Observable<CityBean> getCityList();

    //首页轮播图数据
    @POST("commonSelect/newsList?")
    Observable<ImgData> getBannerList(@Query("userId") String userId, @Query("city") String city, @Query("projectType") String projectType, @Query("arrposid") String arrposid);

    //首页新闻头条数据
    @POST("commonSelect/messageList?")
    Observable<MessageBean2> getMessageTextList(@Query("userId") String userId, @Query("city") String city, @Query("type") String type);

    //消息界面楼盘动态数据
    @POST("commonSelect/projectHousesDynamicsList?")
    Observable<DynamicBean> getDynamicBeanList(@Query("userId") String userId);

    //消息界面通知+房客数据
    @POST("commonSelect/messageList?")
    Observable<MessageBean> getMessageBeanList(@Query("userId") String userId, @Query("city") String city, @Query("type") String type);

    //消息界面通知+房客数据
    @POST("commonSelect/messageList?")
    Observable<GoodNewsBean> getGoodNewsBeanList(@Query("userId") String userId, @Query("city") String city, @Query("type") String type);

    //首页热门列表数据
    @POST("commonSelect/projectList?")
    Observable<HotBean> getHotList(@Query("userId") String userId, @Query("city") String city, @Query("hot") String hot);

    //列表数据
    @POST("commonSelect/projectList?")
    Observable<HotBean> getList(@Query("userId") String userId, @Query("city") String city, @Query("comprehensiveSorting") String comprehensiveSorting, @Query("projectLabel") String projectLabel, @Query("projectType") String projectType, @Query("nation") String nation, @Query("projectPriceStart") String projectPriceStart, @Query("projectPriceEnd") String projectPriceEnd, @Query("apartment") String apartment, @Query("areaSection") String areaSection, @Query("ffProjectTrait") String ffProjectTrait, @Query("procuctType") String procuctType, @Query("fitmentState") String fitmentState);

    //我的登录
    @POST("commonSelect/login")
    Observable<LoginUserBean> getLogin(@Query("username") String username, @Query("password") String password, @Query("type") String type);

    //我的佣金上半部
    @POST("ordinarySelect/amountSummary")
    Observable<BorkerageUpBean> getBrokerageUp(@Query("userId") String userId);

    //我的佣金下半部
    @POST("ordinarySelect/amountList")
    Observable<BorkerageDownBean> getBrokerageDown(@Query("userId") String userId, @Query("projectType") String projectType, @Query("search") String search);

    //我的佣金和客户量
    @POST("ordinarySelect/myDataCount")
    Observable<ClientCommissionsBean> getClientCommissions(@Query("userId") String userId);

    //修改密码
    @POST("commonUpdate/updatePassword")
    Observable<ChangePassowrdBean> getChangePassword(@Query("userId") String userId, @Query("oldPassword") String oldPassword, @Query("newPassword") String newPassword);

    //修改性别
    @POST("commonUpdate/updateUser")
    Observable<ChangeSexBean> getChangeSex(@Query("userId") String userId, @Query("sex") String sex);

    //修改昵称
    @POST("commonUpdate/updateUser")
    Observable<NickNameBean> getNickName(@Query("userId") String userId, @Query("name") String name, @Query("sex") String sex, @Query("photo") String photo, @Query("industry") String industry);

    //免责声明/
    @POST("commonSelect/relevantManageDetails")
    Observable<DisclaimerBean> getDisclaimer(@Query("type") String type, @Query("userId") String userId);

    //经济圈
    @POST("ordinarySelect/economicCircleDetails")
    Observable<EconomicCircleBean> getEconomicCircle(@Query("userId") String userId, @Query("circleId") String circleId);

    //项目卖点
    @POST("commonSelect/projectTalkToolList")
    Observable<SellingPointsBean> getSellingPoints(@Query("type") String type, @Query("userId") String userId, @Query("projectId") String projectId);

    //客户列表页 报备-到访-登岛-认筹-成交-失效
    @POST("commonSelect/reportProcessList")
    Observable<ClientFragmentBean> getClientFragment(@Query("userId") String userId, @Query("projectId") String projectId, @Query("searchName") String searchName, @Query("type") String type);

    //根据用户Id获取用户信息
    @POST("commonSelect/getUserInfo")
    Observable<UserMessageBean> getUserMessage(@Query("userId") String userId);

    //用户反馈
    @POST("commonUpdate/userFeedback")
    Observable<FeedBackBean> getFeedBack(@Query("feedback") String feedback, @Query("img") String img, @Query("userId") String userId);

    //发送验证码
    @POST("commonSelect/sendCode")
    Observable<VerificationBean> getCode(@Query("phone") String phone);

    //更换手机号验证验证码
    @POST("commonUpdate/updatePhone")
    Observable<ChangePhoneBean> getChangePhone(@Query("inputCode") String inputCode, @Query("phone") String phone, @Query("userId") String userId);

    //添加客户
    @POST("commonUpdate/addCustomer")
    Observable<AddClientBean> getAddClient(@Query("customerName") String customerName, @Query("customerImg") String customerImg, @Query("contacts1") String contacts1,
                                           @Query("contactsPhone1") String contactsPhone1, @Query("contacts2") String contacts2, @Query("contactsPhone2") String contactsPhone2,
                                           @Query("contacts3") String contacts3, @Query("contactsPhone3") String contactsPhone3, @Query("userId") String userId);

    //客户列表
    @POST("commonSelect/customerList")
    Observable<ClientBean> getClient(@Query("searchName") String searchName, @Query("userId") String userId);

    //    //创建经济圈
    @POST("ordinaryUpdate/economicCircleSave")
    Observable<DiscussBean> getDiscuss(@Query("userId") String userId, @Query("content") String content, @Query("imgUrl") String imgUrl);

    //客户列表
    @POST("commonUpdate/updateHeadPhoto")
    Observable<PersonalPhotoBean> getPersonalPhoto(@Query("userId") String userId, @Query("photo") String photo);

    //圖片上傳
    @Multipart
    @POST("commonSelect/upload")
    Observable<AddPhotoBean> getAddPhoto(@Query("userId") String userId, @Query("uploadPath") String uploadPath, @Part MultipartBody.Part file);

    //点赞数据
    @POST("commonUpdate/housesDynamicCommentLike")
    Observable<LikeNumBean> getLikeNum(@Query("targetId") String targetId, @Query("userId") String userId, @Query("type") String type);

    //专案端申请登岛添加的数据
    @POST("nodeUpdate/islandSave")
    Observable<LandSaveBean> getIslandSave (@Query("preparationId") String preparationId, @Query("routeId") String routeId, @Query("projectType") String projectType, @Query("isColleague") String isColleague, @Query("colleagueUserNum") int colleagueUserNum, @Query("idNumber") String idNumber, @Query("age") String age, @Query("gender") String gender, @Query("passportNumber") String passportNumber, @Query("passportImg") String passportImg, @Query("route") String route, @Query("islandTime") String islandTime, @Query("isPay") String isPay, @Query("sumCost") String sumCost, @Query("landingImg") String landingImg, @Query("occupation") String occupation, @Query("focus") String focus, @Query("userId") String userId, @Query("jsonStr") String jsonStr, @Query("customerName") String customerName);

    //专案端成交添加的数据
    @POST("nodeUpdate/tradeSave")
    Observable<TradeSaveBean> getTradeSave (@Query("id") String id, @Query("projectId") String projectId, @Query("economicId") String economicId, @Query("preparationId") String preparationId, @Query("customerId") String customerId, @Query("roomNumber") String roomNumber, @Query("apartment") String apartment, @Query("area") String area, @Query("price") String price, @Query("totalPrice") String totalPrice, @Query("paymentMethod") String paymentMethod, @Query("commissionId") String commissionId, @Query("procuctType") String procuctType, @Query("gender") String gender, @Query("relation") String relation, @Query("fullName") String fullName, @Query("phone") String phone, @Query("idNumber") String idNumber, @Query("userId") String userId, @Query("tradeDateStr") String tradeDateStr);

    //专案端查询登岛路线数据
    @POST("nodeSelect/getlandLine")
    Observable<GetLandLineBean> getlandLine (@Query("userId") String userId, @Query("projectId") String projectId);

    //专案端登岛路线时间数据
    @POST("nodeSelect/getlandLinetime")
    Observable<GetLandLineTimeBean> getlandLinetime (@Query("routeid") String routeid);

    //字典数据获取
    @POST("commonSelect/getDictList")
    Observable<DictListBean> getDictList (@Query("userId") String userId, @Query("type") String type);

    //字典数据获取
    @POST("nodeSelect/timeRange")
    Observable<BrokerBean> getTimeRange (@Query("currentDate") String currentDate, @Query("projectId") String projectId);

    //专案端到访添加的数据
    @POST("nodeUpdate/visitSave")
    Observable<VisitSaveBean> getVisitSave (@Query("preparationId") String preparationId, @Query("location") String location, @Query("locationName") String locationName, @Query("accessingImg") String accessingImg, @Query("userId") String userId);

    //报备流程详情
    @POST("commonSelect/reportProcessDetails")
    Observable<MyClientFragmentBean> getClientFragmentBean (@Query("userId") String userId, @Query("preparationId") String preparationId);


    //报备流程失效详情
    @POST("commonSelect/reportProcessDetails")
    Observable<FailureBean> getFailureBean(@Query("userId") String userId, @Query("preparationId") String preparationId);

}
