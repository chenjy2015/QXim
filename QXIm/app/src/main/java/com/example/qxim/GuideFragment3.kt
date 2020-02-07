package com.example.qxim

import android.app.Activity
import com.blankj.rxbus.RxBus
import com.example.baselib.constants.RxEvent
import com.example.baselib.i.Orientation
import com.example.baselib.presenter.LoginPresenter
import com.example.baselib.ui.fragment.BaseUIFragment
import com.example.baselib.utils.ActivityControlHelper
import com.example.qxim.databinding.FragmentGuide3Binding

class GuideFragment3 : BaseUIFragment<LoginPresenter, FragmentGuide3Binding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_guide3
    }

    override fun init() {
    }

    override fun initEvent() {
        RxBus.getDefault()
            .subscribe(this, RxEvent.COLOR_INDEX, object : RxBus.Callback<ColorIndex>() {
                override fun onEvent(color: ColorIndex?) {
                    if (color!!.position == 2) {
                        getDataBinding().root.setBackgroundColor(color!!.color)
                    }
                }
            })

        getDataBinding().btnEnter.setOnClickListener {
            ActivityControlHelper.intentLoginActivity(this.context!!, Orientation.BOTTOM)
            (this.context as Activity).finish()
        }
    }

    override fun initData() {
    }

}