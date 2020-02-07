package com.example.baselib.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.drakeet.multitype.MultiTypeAdapter
import com.example.baselib.R
import com.example.baselib.arouter.ARouterManager
import com.example.baselib.constants.Constant
import com.example.baselib.i.Orientation
import com.example.baselib.presenter.IPresenter
import com.example.baselib.ui.binder.BaseItemViewBinder
import com.example.baselib.utils.ViewUtils
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration


abstract class BaseUIActivity<P : IPresenter, out VD : ViewDataBinding> : BaseSwipeBackActivity<P>() {

    private lateinit var dataBinding: VD
    private lateinit var viewUtils: ViewUtils
    private var orientation: Int = Orientation.NOTHING.value // 转场动画默认不设置专场动画对应Orientation enum类型值

    abstract fun getLayoutId(): Int
    abstract fun init()
    abstract fun initEvent()
    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this@BaseUIActivity, getLayoutId()) as VD
        ARouterManager.instance.inject(this@BaseUIActivity)
        viewUtils = ViewUtils()
        try {
            orientation = intent.getIntExtra(Constant.OVERRIDE_TRANSITION_ORIENTATION, Orientation.NOTHING.value)
        } catch (e: Exception) {
            LogUtils.e(e)
        }
        init()
        initEvent()
        initData()
    }

    fun getDataBinding(): VD {
        return dataBinding
    }

    fun showShortToast(s: String) {
        Toast.makeText(this.applicationContext, s, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(s: String) {
        Toast.makeText(this.applicationContext, s, Toast.LENGTH_LONG).show()
    }

    /**
     * 滑动监听 包含到顶部监听和到底部监听
     *
     * @param recyclerView
     * @param onScrollListener
     */
    fun registerScrollChangeListener(recyclerView: RecyclerView, onScrollListener: ViewUtils.OnScrollListener) {
        viewUtils.registerScrollChangeListener(recyclerView, onScrollListener)
    }

    /**
     * 根据当前recyclerview 获取当前可见item的索引值
     *
     * @param recyclerView
     * @param onScrollListener 可见索引值变化监听
     */
    fun getVisibleIndex(recyclerView: RecyclerView, onScrollListener: ViewUtils.OnScrollListener) {
        viewUtils.getVisibleIndexs(recyclerView, onScrollListener)
    }

    /**
     * 获取当前recyclerview 一页可加载item的数量
     *
     * @param view
     */
    fun getVisibleCount(view: View, orientation: Int): Int {
        return viewUtils.getVisibleCount(view, orientation)
    }

    /**
     * 统一注册recyclerView方法
     */
    inline fun <reified T : Any, VD : ViewDataBinding> register(
        rv: RecyclerView,
        adapter: MultiTypeAdapter,
        orientation: Int,
        items: MutableList<T>,
        binder: BaseItemViewBinder<T, VD>
    ) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = orientation
        rv.layoutManager = layoutManager

        adapter.register(T::class.java, binder)
        adapter.items = listOf(items)
        rv.adapter = adapter
        val itemDecoration: RecyclerView.ItemDecoration =
            if (orientation == LinearLayoutManager.HORIZONTAL) {
                VerticalDividerItemDecoration.Builder(this).build()
            } else {
                HorizontalDividerItemDecoration.Builder(this).build()
            }
        rv.addItemDecoration(itemDecoration)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        if (orientation != Orientation.NOTHING.value) {
            when (orientation) {
                Orientation.RIGHT.value -> overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out)
                Orientation.LEFT.value -> overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out)
                Orientation.BOTTOM.value -> overridePendingTransition(R.anim.slide_bottom_in, R.anim.slide_bottom_out)
            }
        }
    }
}