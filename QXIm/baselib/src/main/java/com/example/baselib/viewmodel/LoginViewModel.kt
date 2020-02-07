package com.example.baselib.viewmodel

import android.annotation.SuppressLint
import android.os.Parcelable
import android.util.Log
import com.example.baselib.bean.UserInfo
import com.example.baselib.constants.Constant
import com.example.baselib.http.*
import com.example.baselib.utils.DataStorageManager

@SuppressLint("CheckResult")
class LoginViewModel : IModel {

    override fun destroy() {
        Log.d("LoginViewModel", "destroy")
    }

    inline fun <reified T : Parcelable> getUser(key: String): T? {
        return DataStorageManager.get(key)
    }

    inline fun <reified T : Parcelable> saveUser(user: T, key: String): Boolean {
        return DataStorageManager.set(user, key)
    }

    fun clearUser(): Boolean {
        return DataStorageManager.clear(Constant.USER_INFO)
    }

    fun clearUserAll(): Boolean {
        return DataStorageManager.clear(Constant.USER_INFO) && DataStorageManager.clear(Constant.ACCOUNT_INFO)
    }

    fun getMainId(): Long? {
        return DataStorageManager.get(Constant.MAIN_ID) ?: return 0L
    }

    fun setMainId(mainId: Long): Boolean {
        return DataStorageManager.set(Constant.MAIN_ID, mainId.toString())
    }

    fun doLogin(account: String, password: String, call: IHttpRequestCall<UserInfo>) {
        HttpRequest.doLogin(account, password, call)
    }

}