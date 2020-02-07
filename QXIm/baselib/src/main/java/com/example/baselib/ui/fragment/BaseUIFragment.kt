package com.example.baselib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.example.baselib.presenter.IPresenter
import com.example.baselib.ui.binder.BaseItemViewBinder
import com.example.baselib.utils.RecyclerUtil


/**
 * @route:
 * @descript: Fragment 基类 指定了调用方法顺序逻辑 不可随意切换
 * @create: chenjiayou
 * create at 2018/11/20 20:23
 */
abstract class BaseUIFragment<P : IPresenter, VD : ViewDataBinding> : BaseSwipeBackFragment<P>() {
    private lateinit var dataBinding: VD

    companion object {
        val TAG = this::class.java.simpleName
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun init()
    protected abstract fun initEvent()
    protected abstract fun initData()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return attachToSwipeBack(dataBinding.root)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        preCreate()
        super.onActivityCreated(savedInstanceState)
        init()
        initEvent()
        initData()
    }


    protected fun preCreate() {}

    fun getDataBinding(): VD {
        return dataBinding
    }

    /**
     * 统一注册recyclerView方法
     */
    inline fun <reified T : Any, VD : ViewDataBinding> register(
        rv: RecyclerView,
        adapter: MultiTypeAdapter,
        orientation: Int,
        items: MutableList<T>,
        binder: BaseItemViewBinder<T, VD>,
        itemDecoration: RecyclerView.ItemDecoration?
    ) {
        RecyclerUtil.register(context!!, rv, adapter, orientation, items, binder, itemDecoration)
    }

    fun showShortToast(s: String) {
        Toast.makeText(this.context?.applicationContext, s, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(s: String) {
        Toast.makeText(this.context?.applicationContext, s, Toast.LENGTH_LONG).show()
    }

}