package com.example.baselib.ui.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.drakeet.multitype.ItemViewBinder
import com.example.baselib.ui.activity.BaseActivity

/**
 *
 * @Author :  chenjiayou
 * @Dscription : binder
 * @Create by : 2019/10/22
 *
 */
abstract class BaseItemViewBinder<T : Any, VD : ViewDataBinding> : ItemViewBinder<T, BaseViewHolder<VD>>() {

    private lateinit var act: BaseActivity<*>

    private lateinit var viewDataBinding: VD

    abstract fun getLayoutId(): Int

    abstract fun bind(holder: BaseViewHolder<VD>, item: T)

    override fun onBindViewHolder(holder: BaseViewHolder<VD>, item: T) {
        if (holder.viewDataBinding.root.context is BaseActivity<*>) {
            act = holder.viewDataBinding.root.context as BaseActivity<*>
        }
        bind(holder, item)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder<VD> {
        val convertView: View = inflater.inflate(getLayoutId(), parent, false)
        viewDataBinding = DataBindingUtil.bind(convertView)!!
        return BaseViewHolder(viewDataBinding)
    }
}