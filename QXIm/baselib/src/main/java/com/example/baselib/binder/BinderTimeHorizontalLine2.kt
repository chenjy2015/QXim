package com.example.baselib.binder

import androidx.core.content.ContextCompat
import com.example.baselib.R
import com.example.baselib.bean.TimeLineModel
import com.example.baselib.databinding.ItemTimelineHorizontalBinding
import com.example.baselib.ui.binder.BaseItemViewBinder
import com.example.baselib.ui.binder.BaseViewHolder
import com.example.baselib.widget.timeline.LineType
import com.example.baselib.widget.timeline.OrderStatus

class BinderTimeHorizontalLine2(var spanCount: Int) :

    BaseItemViewBinder<TimeLineModel, ItemTimelineHorizontalBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.item_timeline_horizontal
    }

    override fun bind(holder: BaseViewHolder<ItemTimelineHorizontalBinding>, item: TimeLineModel) {

        holder.viewDataBinding.textTimelineTitle.text = item.title
        /**
         * 如果当前节点状态为OrderStatus.VERIFY  则右边的时间轴线设置为灰色
         * 如果当前节点状态为OrderStatus.STEP    则左右两边的时间轴线都设置为灰色
         */
        if (item.status == OrderStatus.VERIFY) {
            holder.viewDataBinding.timeMarker.setStartLine(
                ContextCompat.getColor(
                    holder.viewDataBinding.root.context,
                    R.color.colorAccent
                ), LineType.BEGIN
            )
            holder.viewDataBinding.timeMarker.setEndLine(
                ContextCompat.getColor(
                    holder.viewDataBinding.root.context,
                    R.color.gray_8f
                ), LineType.END
            )
        }
        if (item.status == OrderStatus.STEP) {
            holder.viewDataBinding.timeMarker.setStartLine(
                ContextCompat.getColor(
                    holder.viewDataBinding.root.context,
                    R.color.gray_8f
                ), LineType.BEGIN
            )
            holder.viewDataBinding.timeMarker.setEndLine(
                ContextCompat.getColor(
                    holder.viewDataBinding.root.context,
                    R.color.gray_8f
                ), LineType.END
            )
        }

        //手动判断是否处于第一个或者最后一个 去掉第一个和最后一个的划线操作
        if (holder.adapterPosition == 0) {
            holder.viewDataBinding.timeMarker.setStartLine(
                ContextCompat.getColor(
                    holder.viewDataBinding.root.context,
                    R.color.transparent
                ), LineType.BEGIN
            )
        } else if (holder.adapterPosition == spanCount - 1) {
            holder.viewDataBinding.timeMarker.setEndLine(
                ContextCompat.getColor(
                    holder.viewDataBinding.root.context,
                    R.color.transparent
                ), LineType.END
            )
        }


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