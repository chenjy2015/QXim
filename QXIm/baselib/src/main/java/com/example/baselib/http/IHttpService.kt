package com.example.baselib.http

import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface IHttpService {

    /**
     * 农历查询API接口
     * TODO; 测试地址 https://www.sojson.com/api/lunar.html
     */
    @GET("json.shtml")
    fun getLunarCalendar(@Query("date") date: String): Single<Response<ResponseBody>>

    @GET("2/wall-paper/app")
    fun getWallPaper(): Single<Response<ResponseBody>>

    @GET("book/{id}")
    fun getBook(@Path("id") id: Int): Single<Response<ResponseBody>>


    @FormUrlEncoded
    @POST("book/info/")
    fun getInfo(@Field("key") key: String, @Field("value") value: String): Single<Response<ResponseBody>>

    @FormUrlEncoded
    @POST("book/info/")
    fun getInfo(@FieldMap map: Map<String, String>): Single<Response<ResponseBody>>

    @Multipart
    @POST("book/upload/")
    fun upLoadTextAndFile(@Part("textKey") textBody: RequestBody, @Part("fileKey") fileBody: RequestBody): Single<Response<ResponseBody>>

    @Multipart
    @POST("book/upload/")
    fun upLoadTextAndFile(@PartMap textBody: Map<String, RequestBody>, @PartMap fileBody: Map<String, RequestBody>): Single<Response<ResponseBody>>

    @Multipart
    @POST("book/upload/")
    fun upLoadTextAndFile(@Body multipartBody: MultipartBody): Single<Response<ResponseBody>>


    /**
     * 普通文件上传
     */
    @Multipart
    @POST("fileUpload/uploadFileSimple")
    fun uploadNormalFile(@Part("file") file: String, @Part multipartBody: MultipartBody.Part): Single<Response<ResponseBody>>

    /**
     * 账号中心 - 登录
     */
//    @POST("/QXServer/LoginServer")
    @POST("/message/login")
    fun doLogin(@Body body: Map<String, String>): Single<Response<ResponseBody>>
//    @GET("/QXServer/LoginServer")
//    fun doLogin(@Query("account") account: String, @Query("password") password: String): Single<Response<ResponseBody>>

    /**
     * 账号中心 - 注册
     */
    @POST("/base-api/user/userRegister")
    fun userRegister(@Body body: Map<String, String>): Single<Response<ResponseBody>>

    /**
     * 账号中心 - 修改密码
     */
    @POST("/user/user/updatePassword")
    fun updatePassword(@Body body: Map<String, String>): Single<Response<ResponseBody>>

    /**
     * 账号中心 - 更新推送ID
     */
    @GET("/app/user/updateRegistrationId")
    fun updateRegistrationId(@Query("registrationId") registrationId: String): Single<Response<ResponseBody>>


    /**
     * 账号中心 - 发送邮箱验证码
     * @param operType 1、绑定邮箱 2、找回密码 3、修改密码 4、绑定手机 5、变更邮箱 6、变更手机 7、合作意向 8、大使意向
     */
    @JvmSuppressWildcards
    @POST("/app/account/sendEmailVerificationCode")
    fun sendEmailVerityCode(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>


    /**
     * 账号中心 - 验证邮箱验证码是否正确
     */
    @JvmSuppressWildcards
    @POST("/app/account/verificationEmailCode")
    fun verityEmailCode(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>


    /**
     * 账号中心 - 发送手机验证码 已登录接口
     * @param operType 1、绑定邮箱 2、找回密码 3、修改密码 4、绑定手机 5、变更邮箱 6、变更手机 7、合作意向 8、大使意向
     */
    @POST("/user/user/sendSMS")
    @JvmSuppressWildcards
    fun sendMobileVerityCode(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>

    /**
     * 账号中心 - 发送手机验证码 未登录接口
     * @param operType 1、绑定邮箱 2、找回密码 3、修改密码 4、绑定手机 5、变更邮箱 6、变更手机 7、合作意向 8、大使意向
     */
    @POST("/user/user/sendSMSNotLogged")
    @JvmSuppressWildcards
    fun sendMobileVerityCodeNotLogged(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>

    /**
     * 账号中心 - 验证手机验证码是否正确
     */
    @POST("/user/user/validateMobilePhoneCaptcha")
    @JvmSuppressWildcards
    fun verityMobileCode(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>

    /**
     * 账号中心 - 注册/忘记密码时调用 检测该账号是否存在
     */
    @POST("/user/user/existUserInfo")
    @JvmSuppressWildcards
    fun checkAccountExists(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>

    /**
     * 账号中心 - 找回密码 （查询用户信息）
     */

    @POST("/user/user/findPassword")
    fun queryUserInfo(@Body body: Map<String, String>): Single<Response<ResponseBody>>

    /**
     * 查询已开通的应用列表
     */
    @GET("/app/application/serviceOpened")
    fun queryOpenApps(@Query("userId") userId: String): Single<Response<ResponseBody>>

    /**
     * 产品中心 - 产品列表
     */
    @GET("/app/product/prodDetailList")
    fun queryProjectCenterList(@Query("showTop") showTop: Boolean): Single<Response<ResponseBody>>

    /**
     * 消息中心 - 收到消息 - 全部消息
     */
    @POST("/app/message/receving/app/statistics")
    fun queryReceiveAllMsg(): Single<Response<ResponseBody>>

    /**
     * 消息中心 - 待办总数
     */
    @GET("/base-api/enterpriseConsole/enterpriseWorkTable/getAppHeadData")
    fun queryTodoTotalCount(@Query("sysMainBodyId") sysMainBodyId: Long, @Query("sysMainBodyId") type: Int): Single<Response<ResponseBody>>

    /**
     * 消息中心 - 收到的消息 一级列表
     */
    @JvmSuppressWildcards
    @POST("/app/message/receving/app/manage/list")
    fun queryReceiveMsgList(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>

    /**
     * 消息中心 - 收到的消息 二级列表
     */
    @JvmSuppressWildcards
    @POST("/app/message/receving/app/manage/detail/list")
    fun queryReceiveMsgDetailList(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>

    /**
     * 消息中心 - 发出消息 - 全部消息
     */
    @POST("/app/message/send/app/statistics")
    fun querySentAllMsg(): Single<Response<ResponseBody>>

    /**
     * 消息中心 - 发出的消息 - 一级列表
     */
    @POST("/app/message/send/app/list")
    fun querySendMsgList(@Body body: Map<String, String>): Single<Response<ResponseBody>>

    /**
     * 消息中心 - 发出的消息 - 二级列表
     */
    @POST("/app/message/send/app/detail/list")
    fun querySendMsgDetailList(@Body body: Map<String, String>): Single<Response<ResponseBody>>


    /**
     * 消息中心 - 消息管理 - 全部消息
     */
    @POST("/app/message/send/app/manage/statistics")
    fun queryManagerAllMsg(): Single<Response<ResponseBody>>

    /**
     * 消息中心 - 消息管理 - 一级列表
     */
    @POST("/app/message/send/app/manage/list")
    fun queryManagerMsgList(@Body body: Map<String, String>): Single<Response<ResponseBody>>

    /**
     * 消息中心 - 消息管理 - 二级列表
     */
    @POST("/app/message/send/app/manage/list")
    fun queryManagerMsgDetailList(@Body body: Map<String, String>): Single<Response<ResponseBody>>

    /**
     * 消息中心 - 消息设置 - 动态菜单
     */
    @GET("/app/message/type/app/menulist")
    fun querySettingMsgMenuList(@Query("sysMainBodyId") sysMainBodyId: Long): Single<Response<ResponseBody>>

    /**
     * 消息中心 - 消息设置 - 一级列表
     */
    @JvmSuppressWildcards
    @POST("/app/message/type/list")
    fun querySettingMsgList(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>


    /**
     * 根据应用Code查询服务详情（各个应用头部标题信息 ）
     */
    @GET("/app/application/queryDetailInfoByCode")
    fun queryDetailInfoByCode(@Query("serviceCode") serviceCode: String): Single<Response<ResponseBody>>

    /**
     * 查询是否允许开通该应用
     */
    @POST("/control/companyservicebuy/companyIsService")
    fun checkIsOpen(@Body body: Map<String, Long>): Single<Response<ResponseBody>>

    /**
     * 查询需要开通应用的信息
     */
    @GET("/app/application/findApplicationInfo")
    fun findApplicationInfo(@Query("serviceId") serviceId: Long): Single<Response<ResponseBody>>

    /**
     * 开通应用
     */
    @JvmSuppressWildcards
    @POST("/control/companyservicebuy/saveCompanyServiceBuy")
    fun saveCompanyServiceBuy(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>

    /**
     * 获取我加入的组织主体
     */
    @JvmSuppressWildcards
    @POST("/base-api/organizationDetail/getHeadJoinOrganization")
    fun queryOrganizationList(@Body body: Map<String, Any>): @JvmSuppressWildcards Single<Response<ResponseBody>>

    /**
     * 获取个人控制台待办事项列表
     */
    @GET("/base-api/personalConsole/personalWorkTable/todayUnfinished2")
    fun queryTodayUnfinished(@Query("sysMainBodyId") sysMainBodyId: Long): Single<Response<ResponseBody>>

    /**
     * 获取企业控制台待办事项列表
     */
    @GET("/base-api/enterpriseConsole/enterpriseWorkTable/todayUnfinished2")
    fun queryTodayEnterpriseFinished(@Query("sysMainBodyId") sysMainBodyId: Long, @Query("type") type: Int): Single<Response<ResponseBody>>
}