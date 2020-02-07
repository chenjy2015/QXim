package com.example.baselib.http

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.example.baselib.gson.JsonManager
import com.example.baselib.i.Action1
import com.google.gson.*
import okhttp3.ResponseBody
import retrofit2.Response
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject


/**
 *
 * @Author :  chenjiayou
 * @Dscription : 网络结果解析器
 * @Create by : 2019/10/23
 *
 */
class HttpResponseParser {

    companion object {
        val goon: Gson = JsonManager.create()
    }

    inline fun <reified T> getBody(r: Response<ResponseBody>): ResponseBodyBean<T> {
        val code = r.code()
        val body = r.body()
        val errorBody = r.errorBody()
        var response: ResponseBodyBean<T>? = ResponseBodyBean(null, r.message(), code)
        //首先需要判断是否请求成功
        if (code == HttpConstant.HttpResponseCode.REQUEST_OK) {
            try {
                val bodyStr = body?.string()
                val errorStr = errorBody?.string()
                LogUtils.d("getBody", "bodyStr : $bodyStr")
                LogUtils.d("getBody", "errorStr : $errorStr")
                response = when {
                    bodyStr != null -> goon.fromJson(
                        bodyStr,
                        ResponseBodyBean::class.java
                    ) as ResponseBodyBean<T>
                    errorBody != null -> goon.fromJson(
                        errorStr,
                        ResponseBodyBean::class.java
                    ) as ResponseBodyBean<T>
                    else -> null
                }
            } catch (e: JsonParseException) {
                LogUtils.e(e)
            } catch (e2: java.lang.Exception) {
                LogUtils.e(e2)
            }
        } else {
            ThrowableHandler.handlerError(code, r.message())
        }
        return response!!
    }

    inline fun <reified T> parse(response: ResponseBodyBean<T>?): ResponseBean<T> {
        var responseBean: ResponseBean<T>
        if (response?.code == HttpConstant.HttpResponseCode.REQUEST_SUCCESS) {
            try {
                val dataJson = JsonManager.create().toJson(response.data)
                val bean = JsonManager.toBean<T>(dataJson)
                responseBean = ResponseBean(0, null, bean)
            } catch (e: JsonParseException) {
                responseBean = ResponseBean(0, ThrowableHandler.handleThrowable(e), null)

            } catch (e2: JsonIOException) {
                responseBean = ResponseBean(0, ThrowableHandler.handleThrowable(e2), null)

            } catch (e3: JsonSyntaxException) {
                responseBean = ResponseBean(0, ThrowableHandler.handleThrowable(e3), null)

            } catch (e: Exception) {
                responseBean = ResponseBean(0, ThrowableHandler.handleThrowable(e), null)
            }
        } else {
            responseBean = ResponseBean(
                0,
                ThrowableHandler.handlerError(
                    response!!.code,
                    StringUtils.null2Length0(response.msg)
                ),
                null
            )
        }
        return responseBean
    }

    inline fun <reified T> parse(
        response: ResponseBodyBean<T>?,
        throwable: Throwable?,
        success: Action1<T>,
        error: Action1<HttpThrowable>
    ) {
        if (response != null) {
            //判断服务器响应code
            if (response.code == HttpConstant.HttpResponseCode.REQUEST_SUCCESS) {
                try {
                    val dataJson = JsonManager.create().toJson(response.data)
                    success.invoke(JsonManager.toBean(dataJson))

                } catch (e: JsonParseException) {
                    error.invoke(ThrowableHandler.handleThrowable(e))

                } catch (e2: JsonIOException) {
                    error.invoke(ThrowableHandler.handleThrowable(e2))

                } catch (e3: JsonSyntaxException) {
                    error.invoke(ThrowableHandler.handleThrowable(e3))

                } catch (e: Exception) {
                    error.invoke(ThrowableHandler.handleThrowable(e))
                }
            } else {
                error.invoke(
                    ThrowableHandler.handlerError(
                        response.code,
                        StringUtils.null2Length0(response.msg)
                    )
                )
            }
        } else {
            error.invoke(ThrowableHandler.handleThrowable(throwable))
        }
    }

    inline fun <reified E> getBodyList(r: Response<ResponseBody>): ResponseBodyListBean<E> {
        val code = r.code()
        val body = r.body()
        val errorBody = r.errorBody()
        var response: ResponseBodyListBean<E> = ResponseBodyListBean(ArrayList(), "", -1)
        //首先需要判断是否请求成功
        if (code == HttpConstant.HttpResponseCode.REQUEST_OK) {
            try {
                val bodyStr = body?.string()
                val errorStr = errorBody?.string()
                LogUtils.d("getBody", "bodyStr : $bodyStr")
                LogUtils.d("getBody", "errorStr : $errorStr")

                val bodyJson = JSONObject(bodyStr)
                val code = bodyJson.getInt("code")
                val msg = bodyJson.getString("msg")
                //判断服务器响应code
                if (code == HttpConstant.HttpResponseCode.REQUEST_SUCCESS) {
                    val data = bodyJson.getJSONArray("data")
                    if (bodyStr != null) {
                        val items = splicing<E>(data)
                        response = ResponseBodyListBean(code = code, msg = msg, data = items)
                    } else if (errorBody != null) {
                        response =
                            ResponseBodyListBean(code = code, msg = msg, data = arrayListOf())
                    }
                } else {
                    ThrowableHandler.handlerError(code, msg)
                }
            } catch (e: JsonParseException) {
                LogUtils.e(e)
            } catch (e2: java.lang.Exception) {
                LogUtils.e(e2)
            }
        } else {
            ThrowableHandler.handlerError(code, r.message())
        }
        return response
    }

    inline fun <reified O> splicing(data: JSONArray): List<O> {
        val items = ArrayList<O>()
        for (index in 0 until data.length()) {
            val vo: O = Gson().fromJson<O>(data.get(index).toString(), O::class.java)
            items.add(vo)
        }
        return items
    }

}