package com.example.baselib.http

import android.annotation.SuppressLint
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.example.baselib.bean.*
import com.example.baselib.enums.MsgFrom
import com.example.baselib.enums.OperatorType
import com.example.baselib.enums.UserType
import com.example.baselib.enums.VerityBy
import com.example.baselib.presenter.LoginPresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File

@SuppressLint("CheckResult")
class HttpRequest {

    companion object {

        const val TAG: String = "HttpRequest"

        val service: IHttpService = HttpRequestManager.instance.create(IHttpService::class.java)


        /**
         * 普通文件上传
         */
        fun uploadNormalFile(file: File, call: IHttpRequestCall<ResponseFile>) {
            val imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file.path)
            val multipartBody = MultipartBody.Part.createFormData("file", file.name, imageBody)
            post(service.uploadNormalFile(file.name, multipartBody), call)
        }

        /**
         * 登录
         */
        fun doLogin(account: String, password: String, call: IHttpRequestCall<UserInfo>) {
            val map = HashMap<String, String>()
            map["account"] = account
            map["password"] = password
            post(service.doLogin(map), call)
//            post(service.doLogin(account, password), call)
        }


        inline fun <reified T, C : IHttpRequestCall<T>> post(
            single: Single<Response<ResponseBody>>,
            call: C
        ) {
            single.map { rsp: Response<ResponseBody> ->
                HttpResponseParser().getBody<T>(rsp)
            }.map {
                HttpResponseParser().parse(it)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { rsp: ResponseBean<T>?, e: Throwable? ->
                    LogUtils.e("post", rsp)
                    try {
                        if (e != null) {
                            val httpThrowable = ThrowableHandler.handleThrowable(e)
                            call.error(httpThrowable)
                            LogUtils.e("post", "rsp parse error ${httpThrowable}!")
                            return@subscribe
                        }
                        if (rsp?.code == HttpConstant.HttpResponseCode.REQUEST_SUCCESS && rsp.bean != null) {
                            call.success(rsp.bean!!)
                        } else {
                            call.error(rsp?.error!!)
                        }
                    } catch (e: java.lang.Exception) {
                        LogUtils.e(e)
                        call.error(HttpThrowable(HttpThrowable.PARSE_ERROR, "数据解析异常!", e))
                    }
                }
        }

        /**
         * 针对于没有返回值的请求解析
         */
        fun <C : IHttpRequestCall<String>> postResponseNoData(
            single: Single<Response<ResponseBody>>,
            call: C
        ) {
            single.map { rsp: Response<ResponseBody> ->
                HttpResponseParser().getBody<String>(rsp)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { rsp: ResponseBodyBean<String>?, e: Throwable? ->
                    LogUtils.e("post", rsp)
                    try {
                        if (e != null) {
                            val httpThrowable = ThrowableHandler.handleThrowable(e)
                            call.error(httpThrowable)
                            LogUtils.e("post", "rsp parse error ${httpThrowable}!")
                            return@subscribe
                        }
                        if (rsp?.code == HttpConstant.HttpResponseCode.REQUEST_SUCCESS) {
                            call.success(StringUtils.null2Length0(rsp.msg))
                        } else {
                            val httpThrowable = ThrowableHandler.handlerError(rsp!!.code, StringUtils.null2Length0(rsp.msg))
                            call.error(httpThrowable)
                        }
                    } catch (e: java.lang.Exception) {
                        LogUtils.e(e)
                        call.error(HttpThrowable(HttpThrowable.PARSE_ERROR, "数据解析异常!", e))
                    }
                }
        }


        inline fun <reified T, C : IHttpRequestCall<List<T>>> postList(
            single: Single<Response<ResponseBody>>,
            call: C
        ) {
            single
                .map { rsp: Response<ResponseBody> ->
                    HttpResponseParser().getBodyList<T>(rsp)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { rsp: ResponseBodyListBean<T>?, e: Throwable? ->
                    //                    if (rsp?.data == null) {
//                        call.error(HttpThrowable(HttpThrowable.PARSE_ERROR, "数据解析异常", e!!))
//                        LogUtils.e("post", "rsp is null!")
//                        return@subscribe
//                    }
//                    call.success(rsp.data!!)
                    if (e != null) {
                        val httpThrowable = ThrowableHandler.handleThrowable(e)
                        call.error(httpThrowable)
                        LogUtils.e("post", "rsp parse error ${httpThrowable}!")
                        return@subscribe
                    }
                    if (rsp?.code == HttpConstant.HttpResponseCode.REQUEST_SUCCESS) {
                        call.success(rsp.data!!)
                    } else {
                        val httpThrowable = ThrowableHandler.handlerError(rsp!!.code, rsp.msg)
                        call.error(httpThrowable)
                    }
                }
        }
    }
}


