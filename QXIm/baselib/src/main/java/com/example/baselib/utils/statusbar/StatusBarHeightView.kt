package com.example.baselib.utils.statusbar

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.Nullable
import com.example.baselib.R

/**
 * @author: chenjiayou
 * @createBy: 2019/11/1
 * @descript:
 *
 *   代码很简单, 就是写一个View, 支持paddingTop= 状态栏高度值 的View,
 *   解释下两个类型:
 *   use_height: 设置当前布局高度=状态栏高度值 用于无子View时的占位
 *   use_padding_top: 设置当前顶部padding=状态栏高度值 用于有子View时的占位
 *   适配低于4.4时 占位View的高度为0 所以不可见*
 *   使用方法, 用StatusBarHeightView 来包住你要往下移动的内容! 单独留出要沉浸的View不包住,
 *   TODO ： 不要忘记了, 在当前activity onCreate中设置 取消padding,  因为这个padding 我们用代码实现了,不需要系统帮我
 *   TODO ： StatusBarUtil.setRootViewFitsSystemWindows(this,false);
 */
class StatusBarHeightView : LinearLayout {
    private var statusBarHeight: Int = 0
    private var type: Int = 0

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)


    }

    private fun init(@Nullable attrs: AttributeSet?) {

        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (resourceId > 0) {
                statusBarHeight = resources.getDimensionPixelSize(resourceId)
            }
        } else {
            //低版本 直接设置0
            statusBarHeight = 0
        }
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusBarHeightView)
            type = typedArray.getInt(R.styleable.StatusBarHeightView_use_type, 0)
            typedArray.recycle()
        }
        if (type == 1) {
            setPadding(paddingLeft, statusBarHeight, paddingRight, paddingBottom)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (type == 0) {
            setMeasuredDimension(
                getDefaultSize(suggestedMinimumWidth, widthMeasureSpec),
                statusBarHeight
            )
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

}