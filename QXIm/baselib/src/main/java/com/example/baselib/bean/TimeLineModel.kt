package com.example.baselib.bean

import com.example.baselib.widget.timeline.OrderStatus

class TimeLineModel(
    var title: String,
    var date: String,
    var status: OrderStatus,
    var startLineColor: Int, //左边线颜色
    var endLineColor: Int //右边线颜色
)