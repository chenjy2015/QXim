package com.example.baselib.bean

import android.os.Parcelable
import com.example.baselib.enums.MsgFrom
import kotlinx.android.parcel.Parcelize

@Parcelize
class MsgCenterAllVO() : Parcelable {

    var type: Int = 1 //消息类型 1 全部消息 2 系统消息 3 运营消息 4 企业消息
    lateinit var from: MsgFrom // 消息来源
    var title: String? = null //标题
    var unReadNum: Int = 0//未读数量
    var icon: Int = 0 //应用icon
    var appName: String? = null //应用名称
    var content: String? = null //发布内容
    var date: String? = null //发布时间
    var appVersion: String? = null  //应用版本

    constructor(type: Int, title: String, unReadNum: Int) : this() {
        this.type = type
        this.title = title
        this.unReadNum = unReadNum
    }

    constructor(
        type: Int,
        from: MsgFrom,
        title: String,
        unReadNum: Int,
        icon: Int,
        appName: String,
        content: String,
        date: String,
        appVersion: String
    ) : this() {
        this.type = type
        this.from = from
        this.title = title
        this.unReadNum = unReadNum
        this.icon = icon
        this.appName = appName
        this.content = content
        this.date = date
        this.appVersion = appVersion
    }


}