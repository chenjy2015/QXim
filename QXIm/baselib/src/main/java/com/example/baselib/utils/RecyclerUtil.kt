package com.example.baselib.utils

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.example.baselib.ui.binder.BaseItemViewBinder
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration

class RecyclerUtil {
    companion object {

        /**
         * 统一注册recyclerView方法
         */
        inline fun <reified T : Any, VD : ViewDataBinding> register(
            context: Context,
            rv: RecyclerView,
            adapter: MultiTypeAdapter,
            orientation: Int,
            items: MutableList<T>,
            binder: BaseItemViewBinder<T, VD>,
            itemDecoration: RecyclerView.ItemDecoration?
        ) {
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = orientation
            rv.layoutManager = layoutManager

            adapter.register(T::class.java, binder)
            adapter.items = items
            rv.adapter = adapter
            if (itemDecoration == null) {
                rv.addItemDecoration(
                    if (orientation == LinearLayoutManager.HORIZONTAL) {
                        VerticalDividerItemDecoration.Builder(context).build()
                    } else {
                        HorizontalDividerItemDecoration.Builder(context).build()
                    }
                )
            } else {
                rv.addItemDecoration(itemDecoration)
            }
        }

    }
}