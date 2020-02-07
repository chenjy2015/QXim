package com.example.qxim

import android.animation.ArgbEvaluator
import android.content.Context
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.rxbus.RxBus
import com.example.baselib.arouter.ARouterConstants
import com.example.baselib.constants.RxEvent
import com.example.baselib.presenter.LoginPresenter
import com.example.baselib.ui.activity.BaseUIActivity
import com.example.baselib.utils.statusbar.StatusBarUtil
import com.example.qxim.databinding.ActivityGuideBinding

@Route(path = ARouterConstants.router_path_activity.ACTIVITY_PATH_GUIDE)
class GuideActivity : BaseUIActivity<LoginPresenter, ActivityGuideBinding>(),
    ViewPager.OnPageChangeListener {


    private lateinit var adapter: GuideAdapter
    private lateinit var mEvaluator: ArgbEvaluator
    private var mBgColors: MutableList<Int> = mutableListOf()


    override fun getLayoutId(): Int {
        return R.layout.activity_guide
    }

    override fun init() {
        setSwipeBackEnable(false)
        StatusBarUtil.setStatusBarColor(
            this,
            ContextCompat.getColor(this, R.color.background_color_1)
        )
        mEvaluator = ArgbEvaluator()
        adapter = GuideAdapter(supportFragmentManager)
    }

    override fun initEvent() {
        getDataBinding().vpWelcome.adapter = adapter
        getDataBinding().vpWelcome.offscreenPageLimit = 3
        getDataBinding().vpWelcome.addOnPageChangeListener(this@GuideActivity)
    }

    override fun initData() {
        mBgColors = arrayListOf(
            ContextCompat.getColor(this, R.color.background_color_1),
            ContextCompat.getColor(this, R.color.background_color_2),
            ContextCompat.getColor(this, R.color.background_color_3)
        )
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val colorUpdate = mEvaluator.evaluate(
            positionOffset, mBgColors[position],
            mBgColors[if (position == 2) position else position + 1]
        ) as Int

        getDataBinding().vpWelcome.setBackgroundColor(colorUpdate)
        StatusBarUtil.setStatusBarColor(this, colorUpdate)
        RxBus.getDefault().post(ColorIndex(colorUpdate, position), RxEvent.COLOR_INDEX)
    }

    override fun onPageSelected(position: Int) {

        getDataBinding().rgIndicator.clearCheck()
        val radioButton = getDataBinding().rgIndicator.getChildAt(position) as RadioButton
        radioButton.isChecked = true
    }
}
