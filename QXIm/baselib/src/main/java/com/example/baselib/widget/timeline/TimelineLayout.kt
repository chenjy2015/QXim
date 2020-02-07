package com.example.baselib.widget.timeline

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.example.baselib.R
import com.example.baselib.bean.TimeLineModel
import com.example.baselib.binder.BinderTimeHorizontalLine


class TimelineLayout : FrameLayout {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MultiTypeAdapter
    private lateinit var datas: MutableList<TimeLineModel>
    private var spanCount = 4 //多少列

    /**
     * 外部设置列数
     */
    fun setSpanCount(count: Int) {
        this.spanCount = count
        val layoutManager = GridLayoutManager(context, spanCount)
        recyclerView.layoutManager = layoutManager
        recyclerView.invalidate()
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init(context, attributeSet)
    }

    @SuppressLint("Recycle")
    private fun init(context: Context, attr: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attr!!, R.styleable.TimelineLayout)
        spanCount = typedArray.getInteger(R.styleable.TimelineLayout_span_count, 4)

        recyclerView = RecyclerView(context)
        recyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        recyclerView.layoutParams =
            RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        datas = ArrayList()
        adapter = MultiTypeAdapter()
        adapter.register(TimeLineModel::class.java, BinderTimeHorizontalLine(spanCount))
        adapter.items = datas
//        val layoutManager = LinearLayoutManager(context)
//        layoutManager.orientation = RecyclerView.HORIZONTAL

        typedArray.recycle()
        val layoutManager = GridLayoutManager(context, spanCount)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        addView(recyclerView)

    }

    fun setItems(datas: List<TimeLineModel>) {
        this.datas = datas as MutableList<TimeLineModel>
        adapter.items = datas
        adapter.notifyDataSetChanged()
    }
}