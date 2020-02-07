package com.example.baselib.i

import com.example.baselib.bean.UserInfo

interface LoginCallBack {
    fun checkError(error: String)
    fun showLoading()
    fun loginSuccess(userInfo: UserInfo)
    fun loginFail(error: String)
}