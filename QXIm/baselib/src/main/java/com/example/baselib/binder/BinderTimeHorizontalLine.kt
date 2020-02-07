package com.example.baselib.binder

import androidx.core.content.ContextCompat
import com.example.baselib.R
import com.example.baselib.bean.TimeLineModel
import com.example.baselib.databinding.ItemTimelineHorizontalBinding
import com.example.baselib.ui.binder.BaseItemViewBinder
import com.example.baselib.ui.binder.BaseViewHolder
import com.example.baselib.widget.timeline.LineType
import com.example.baselib.widget.timeline.OrderStatus

class BinderTimeHorizontalLine(var spanCount: Int) :

    BaseItemViewBinder<TimeLineModel, ItemTimelineHorizontalBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.item_timeline_horizontal
    }

    override fun bind(holder: BaseViewHolder<ItemTimelineHorizontalBinding>, item: TimeLineModel) {

        holder.viewDataBinding.textTimelineTitle.text = item.title
        /**
         * 左，右划线颜色通过外部数据给定
         */
        holder.viewDataBinding.timeMarker.setStartLine(
            ContextCompat.getColor(
                holder.viewDataBinding.root.context,
                item.startLineColor
            ), LineType.BEGIN
        )
        holder.viewDataBinding.timeMarker.setEndLine(
            ContextCompat.getColor(
                holder.viewDataBinding.root.context,
                item.endLineColor
            ), LineType.END
        )

        val mContext = holder.viewDataBinding.root.context
        when (item.status) {
            OrderStatus.VERIFY -> holder.viewDataBinding.timeMarker.setMarker(
                ContextCompat.getDrawable(mContext, R.mipmap.ic_verify)
            )
            OrderStatus.COMPLETED -> holder.viewDataBinding.timeMarker.setMarker(
                ContextCompat.getDrawable(mContext, R.mipmap.ic_completed)
            )
            else -> {
                when {
                    holder.adapterPosition == 1 -> holder.viewDataBinding.timeMarker.setMarker(
                        ContextCompat.getDrawable(mContext, R.mipmap.ic_step_two)
                    )
                    holder.adapterPosition == 2 -> holder.viewDataBinding.timeMarker.setMarker(
                        ContextCompat.getDrawable(mContext, R.mipmap.ic_step_three)
                    )
                    else -> holder.viewDataBinding.timeMarker.setMarker(
                        ContextCompat.getDrawable(mContext, R.mipmap.ic_step_four)
                    )
                }
            }
        }
    }


}