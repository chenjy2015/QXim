package com.example.baselib.enums

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 消息来源 1.收到的消息 2.发出的消息 3消息设置 4 消息管理
 */
@Parcelize
enum class MsgFrom : Parcelable{
    RECEIVED, SENT, SETTING, MANAGER
}