package com.example.baselib.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout


class SameWithLinearlayout : LinearLayout {
    constructor(
        context: Context?, attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?) : super(context) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthMeasureSpec = widthMeasureSpec
        var heightMeasureSpec = heightMeasureSpec
        setMeasuredDimension(
            View.getDefaultSize(0, widthMeasureSpec),
            View.getDefaultSize(0, heightMeasureSpec)
        )
        val childWidthSize = measuredWidth
        // 高度和宽度一样
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(
            childWidthSize, MeasureSpec.EXACTLY
        )
        heightMeasureSpec = widthMeasureSpec
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}


