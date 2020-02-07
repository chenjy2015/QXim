package com.example.baselib.i

import com.example.baselib.bean.UserInfo

interface RegisterCallBack {
    fun showLoading()
    fun registerSuccess(userInfo: UserInfo)
    fun registerFail(e: Exception)
    fun updateRegisterSuccess()
    fun updateRegisterIdFail(e: Exception)
}