package com.example.baselib.presenter

import com.example.baselib.bean.UserInfo

class AccountCenterPresenter : IPresenter {

    fun getUserInfo(): UserInfo? {
        return LoginPresenter().getUser()
    }

    fun hideEmailCenter(email: String): String {
        if (email.isNullOrEmpty()) {
            return ""
        }
        val head = email.substring(0, 1)
        val footer = email.substring(email.indexOf("@") - 1, email.length - 1)
        val numberLength = email.indexOf("@") - 1
        val sb = StringBuilder()
        for (index in 0..numberLength) {
            sb.append("*")
        }
        val center = sb.toString()
        return StringBuilder().append(head).append(center).append(footer).toString()
    }

    fun hidePhoneCenter(mobile: String): String {
        if (mobile.isNullOrEmpty()) {
            return ""
        }
        var head = ""
        val center = "****"
        var footer = ""
        if (mobile.length > 8) {
            head = mobile.substring(0, 4)
            footer = mobile.substring(7, mobile.length - 1)
        } else {
            head = mobile.substring(0, 2)
            footer = mobile.substring(5, mobile.length - 1)
        }
        return StringBuilder().append(head).append(center).append(footer).toString()
    }

}