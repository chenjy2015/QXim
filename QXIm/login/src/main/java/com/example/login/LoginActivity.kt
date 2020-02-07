package com.example.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.StringUtils
import com.example.baselib.arouter.ARouterConstants
import com.example.baselib.bean.UserInfo
import com.example.baselib.i.LoginCallBack
import com.example.baselib.i.Orientation
import com.example.baselib.i.SimpleTextWatcher
import com.example.baselib.presenter.LoginPresenter
import com.example.baselib.ui.activity.BaseUIActivity
import com.example.baselib.utils.ActivityControlHelper
import com.example.baselib.utils.DialogManager
import com.example.baselib.widget.dialog.LoadingSunflowerDialog
import com.example.login.databinding.ActivityLoginBinding
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

@Route(path = ARouterConstants.router_path_activity.ACTIVITY_PATH_LOGIN)
class LoginActivity : BaseUIActivity<LoginPresenter, ActivityLoginBinding>(), LoginCallBack {

    var account: String? = null
    var password: String? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun init() {
    }

    override fun initEvent() {

        getDataBinding().etAct.addTextChangedListener(object : SimpleTextWatcher() {

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                account = p0.toString()
                getDataBinding().btnLogin.isEnabled = isEnable()
            }
        })

        getDataBinding().etPsw.addTextChangedListener(object : SimpleTextWatcher() {

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                password = p0.toString()
                getDataBinding().btnLogin.isEnabled = isEnable()
            }
        })

        getDataBinding().btnLogin.setOnClickListener {
            getPresenter().login(this, account!!, password!!, this)
        }
    }

    override fun initData() {
    }

    fun isEnable(): Boolean {
        return !StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)
    }

    override fun checkError(error: String) {
        showShortToast(error)
        DialogManager.dismiss()
    }

    override fun showLoading() {
        DialogManager.create(
            this@LoginActivity,
            "",
            LoadingSunflowerDialog(this@LoginActivity)
        )
    }

    override fun loginSuccess(userInfo: UserInfo) {
        showShortToast("login success !")
        DialogManager.dismiss()
        addDisposable(
            Observable.just(userInfo)
                .delay(100, TimeUnit.MILLISECONDS)
                .subscribe {
                    ActivityControlHelper.intentActivityByPath(
                        ARouterConstants.router_path_activity.ACTIVITY_PATH_CHAT,
                        Orientation.BOTTOM
                    )
                }
        )
    }

    override fun loginFail(error: String) {
        showShortToast(error)
        DialogManager.dismiss()
    }

}