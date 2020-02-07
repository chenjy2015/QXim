package com.example.baselib.viewmodel

import android.annotation.SuppressLint
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.baselib.bean.OpenAppVO
import com.example.baselib.bean.ResponseFile
import com.example.baselib.bean.TuchongEntity
import com.example.baselib.http.HttpRequest
import com.example.baselib.http.HttpThrowable
import com.example.baselib.http.IHttpRequestCall
import com.example.baselib.http.ThrowableHandler
import com.example.baselib.i.ListAction
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call
import java.io.File
import java.lang.Exception
import java.util.*

class WorkbenchViewModel : IModel {

    override fun destroy() {

    }

    fun requestBannerResource(action: ListAction<TuchongEntity.FeedListBean.EntryBean>) {
        //加载网络图片资源
        val url = "https://api.tuchong.com/2/wall-paper/app"
        OkHttpUtils
            .get()
            .url(url)
            .build()
            .execute(object : StringCallback() {
                override fun onResponse(response: String?, id: Int) {
                    val advertiseEntity: TuchongEntity = GsonUtils.fromJson(response, TuchongEntity::class.java)
                    val others = advertiseEntity.feedList
                    val data = ArrayList<TuchongEntity.FeedListBean.EntryBean>()
                    for (i in others.indices) {
                        val feedListBean = others[i]
                        if ("post" == feedListBean.type) {
                            data.add(feedListBean.entry)
                        }
                    }
                    action.invoke(data)
                }

                override fun onError(call: Call?, e: java.lang.Exception?, id: Int) {
                    ToastUtils.showShort("加载广告数据失败")
                    action.invoke(null)
                }
            })
    }

    @SuppressLint("WrongConstant")
    fun postFile(call: IHttpRequestCall<ResponseFile>) {
        val file = File("/storage/emulated/0/DCIM/Camera/IMG_20191029_134736.jpg")

        PermissionUtils.permission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).callback(object : PermissionUtils.SimpleCallback {
            override fun onGranted() {
                HttpRequest.uploadNormalFile(file, call)
            }

            override fun onDenied() {
                LogUtils.e("读取文件权限被拒绝!")
                ToastUtils.showShort("读取文件权限被拒绝!")
            }
        }).request()
    }

}