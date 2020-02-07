package com.example.baselib.bean

/**
 * 首页标题栏 未读数合集
 */
data class UnReadTotalCountVO(
    val unfinishedNum: Int, //待办总数
    val unreadMessageNum: Int //消息未读总数
)