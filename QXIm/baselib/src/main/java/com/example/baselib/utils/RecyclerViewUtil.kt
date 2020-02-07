package com.example.baselib.utils

import androidx.recyclerview.widget.RecyclerView


class RecyclerViewUtil {

    companion object {
        /**
         * 平滑的滑动到指定位置
         */
        fun smoothMoveToPosition(mRecyclerView: RecyclerView, position: Int) {
            // 第一个可见位置
            val firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0))
            // 最后一个可见位置
            val lastItem =
                mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 1))
            if (position < firstItem) {
                // 第一种可能:跳转位置在第一个可见位置之前
                mRecyclerView.smoothScrollToPosition(position)
            } else if (position <= lastItem) {
                // 第二种可能:跳转位置在第一个可见位置之后
                val movePosition = position - firstItem
                if (movePosition >= 0 && movePosition < mRecyclerView.childCount) {
                    val top = mRecyclerView.getChildAt(movePosition).top
                    mRecyclerView.smoothScrollBy(0, top)
                }
            } else {
                // 第三种可能:跳转位置在最后可见项之后
                mRecyclerView.smoothScrollToPosition(position)
            }
        }
    }


}