package com.example.baselib.presenter

import android.content.Context
import com.example.baselib.bean.UserInfo
import com.example.baselib.bean.AccountVO
import com.example.baselib.constants.Constant
import com.example.baselib.http.HttpRequestManager
import com.example.baselib.http.HttpThrowable
import com.example.baselib.http.IHttpRequestCall
import com.example.baselib.i.LoginCallBack
import com.example.baselib.enums.UserType
import com.example.baselib.viewmodel.LoginViewModel

class LoginPresenter : IPresenter {

    private var loginModel = LoginViewModel()


    fun login(context: Context, account: String, password: String, call: LoginCallBack) {
        val error = checkInfo(account, password)
        if (!error.isNullOrEmpty()) {
            call.checkError(error)
            return
        }
//        val userInfo = getUser()
//        if (userInfo != null) {
//            call.loginSuccess(userInfo)
//            return
//        }
        doLogin(context, account, password, call)
    }

    private fun doLogin(
        context: Context,
        account: String,
        password: String,
        call: LoginCallBack
    ) {
        call.showLoading()
        loginModel.doLogin(account, password, object : IHttpRequestCall<UserInfo> {
            override fun success(t: UserInfo) {
                saveUser(t)
                //todo 这一步不能省略 在登录成功后要刷新hear值
                HttpRequestManager.instance.init(context.applicationContext)
                call.loginSuccess(t)
            }

            override fun <E : HttpThrowable> error(e: E) {
                call.loginFail(e.msg)
            }
        })
    }

    fun register() {

    }

    private fun getUserType(account: String): Int {
        return if (account == "99") {
            UserType.ENTERPRISE.type
        } else {
            UserType.PERSONAL.type
        }
    }

    fun checkInfo(account: String, password: String): String {
        var error = ""
        if (account.isNullOrEmpty()) {
            error = "账号不能为空"
        }
        if (password.isNullOrEmpty()) {
            error = "密码不能为空"
        }

        return error
    }


    fun getUser(): UserInfo? {
        return loginModel.getUser(Constant.USER_INFO)
    }

    fun saveUser(userInfo: UserInfo): Boolean {
        return loginModel.saveUser(userInfo, Constant.USER_INFO)
    }

    fun getAccount(): AccountVO? {
        return loginModel.getUser(Constant.ACCOUNT_INFO)
    }

    fun saveAccount(accountVO: AccountVO): Boolean {
        return loginModel.saveUser(accountVO, Constant.ACCOUNT_INFO)
    }

}