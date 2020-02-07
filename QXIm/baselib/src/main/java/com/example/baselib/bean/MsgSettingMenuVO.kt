package com.example.baselib.bean

data class MsgSettingMenuVO(
    val id: Long,
    val menuName: String,
    var selected: Boolean = false //自定义属性 标示当前选中状态
)