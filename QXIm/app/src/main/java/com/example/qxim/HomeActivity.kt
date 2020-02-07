package com.example.qxim

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselib.arouter.ARouterConstants
import com.example.baselib.i.Orientation
import com.example.baselib.presenter.LoginPresenter
import com.example.baselib.ui.activity.BaseActivity
import com.example.baselib.utils.ActivityControlHelper

@Route(path = ARouterConstants.router_path_activity.ACTIVITY_PATH_HOME)
class HomeActivity : BaseActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ActivityControlHelper.intentLoginActivity(this, Orientation.BOTTOM)
    }
}
