package com.example.qxim

import com.blankj.rxbus.RxBus
import com.example.baselib.constants.RxEvent
import com.example.baselib.presenter.LoginPresenter
import com.example.baselib.ui.fragment.BaseUIFragment
import com.example.qxim.databinding.FragmentGuideBinding

class GuideFragment : BaseUIFragment<LoginPresenter, FragmentGuideBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_guide
    }

    override fun init() {

    }

    override fun initEvent() {
        RxBus.getDefault()
            .subscribe(this, RxEvent.COLOR_INDEX, object : RxBus.Callback<ColorIndex>() {
                override fun onEvent(color: ColorIndex?) {
                    if (color!!.position == 0) {
                        getDataBinding().root.setBackgroundColor(color!!.color)
                    }
                }
            })

    }

    override fun initData() {
    }
}