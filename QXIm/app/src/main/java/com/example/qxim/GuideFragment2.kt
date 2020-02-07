package com.example.qxim

import com.blankj.rxbus.RxBus
import com.example.baselib.constants.RxEvent
import com.example.baselib.presenter.LoginPresenter
import com.example.baselib.ui.fragment.BaseUIFragment
import com.example.qxim.databinding.FragmentGuide2Binding

class GuideFragment2 : BaseUIFragment<LoginPresenter, FragmentGuide2Binding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_guide2
    }

    override fun init() {
    }

    override fun initEvent() {
        RxBus.getDefault()
            .subscribe(this, RxEvent.COLOR_INDEX, object : RxBus.Callback<ColorIndex>() {
                override fun onEvent(color: ColorIndex?) {
                    if (color!!.position == 1) {
                        getDataBinding().root.setBackgroundColor(color!!.color)
                    }
                }
            })
    }

    override fun initData() {
    }

}