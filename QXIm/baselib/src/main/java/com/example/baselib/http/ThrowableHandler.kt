package com.example.baselib.http

import android.net.ParseException
import com.blankj.rxbus.RxBus
import com.blankj.utilcode.util.BusUtils
import com.example.baselib.constants.RxEvent
import com.example.baselib.utils.ActivityControlHelper
import com.google.gson.JsonParseException
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

object ThrowableHandler {

    fun handleThrowable(throwable: Throwable?): HttpThrowable {
        return if (throwable is HttpException) {
            HttpThrowable(HttpThrowable.HTTP_ERROR, "网络(协议)异常", throwable)
        } else if (throwable is JsonParseException || throwable is JSONException || throwable is ParseException) {
            HttpThrowable(HttpThrowable.PARSE_ERROR, "数据解析异常", throwable)
        } else if (throwable is UnknownHostException) {
            HttpThrowable(HttpThrowable.NO_NET_ERROR, "网络连接失败，请稍后重试", throwable)
        } else if (throwable is SocketTimeoutException) {
            HttpThrowable(HttpThrowable.TIME_OUT_ERROR, "连接超时", throwable)
        } else if (throwable is ConnectException) {
            HttpThrowable(HttpThrowable.CONNECT_ERROR, "连接异常", throwable)
        } else if (throwable is javax.net.ssl.SSLHandshakeException) {
            HttpThrowable(HttpThrowable.SSL_ERROR, "证书验证失败", throwable)
        } else {
            HttpThrowable(HttpThrowable.UNKNOWN, throwable?.message.toString(), throwable!!)
        }
    }

    fun handlerError(code: Int, msg: String): HttpThrowable {
        val handleThrowable = handleThrowable(Throwable(msg))
        when (code) {
            HttpErrorCode.LOGIN_ACCOUNT_AUTHORIZATION_STATUS_FREEZE,
            HttpErrorCode.LOGIN_ACCOUNT_AUTHORIZATION_STATUS_RELEASE,
            HttpErrorCode.LOGIN_ACCOUNT_CANCELLED,
            HttpErrorCode.LOGIN_ACCOUNT_NOT_ACTIVATED,
            HttpErrorCode.LOGIN_ACCOUNT_PERMISSION_CHANGE,
            HttpErrorCode.LOGIN_ACCOUNT_FORZEN,
            HttpErrorCode.PLEASE_LOGIN_AGAIN -> {
                Observable.just(code)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        RxBus.getDefault().post(HttpError(code, msg), RxEvent.LOGIN_NOT_AVAILABLE)
                    }
            }
            HttpErrorCode.VERIFY_CODE_ERROR -> {
                handleThrowable.errorType = code
                handleThrowable.msg = msg
            }
        }
        return handleThrowable
    }


}
