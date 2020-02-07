package com.example.qxim

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselib.arouter.ARouterConstants
import com.example.baselib.bean.UserInfo
import com.example.baselib.constants.Constant
import com.example.baselib.presenter.LoginPresenter
import com.example.baselib.ui.activity.BaseUIActivity
import com.example.baselib.utils.ActivityControlHelper
import com.example.baselib.viewmodel.LoginViewModel
import com.example.qxim.databinding.ActivitySplashBinding

/**
 *
 * @Author :  chenjiayou
 * @Dscription :  app 主界面 做中转 不做任何界面
 * @Create by : 2019/10/21
 *
 */
@Route(path = ARouterConstants.router_path_activity.ACTIVITY_PATH_SPLASH)
class SplashActivity : BaseUIActivity<LoginPresenter, ActivitySplashBinding>() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun init() {
        val anim: ObjectAnimator = ObjectAnimator.ofFloat(getDataBinding().tvLaunchWords, "alpha", 0.1f, 1.0f)
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                if (LoginViewModel().getUser<UserInfo>(Constant.USER_INFO) == null) {
                    startActivity(Intent(this@SplashActivity, GuideActivity::class.java))
                } else {
                    ActivityControlHelper.intentHomeActivity()
                }
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }
        })
        anim.duration = 1000
        anim.start()
    }

    override fun initEvent() {
    }

    override fun initData() {
    }

}
