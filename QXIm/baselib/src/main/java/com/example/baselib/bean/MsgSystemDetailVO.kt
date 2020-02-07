package com.example.baselib.bean

import android.os.Parcelable
import com.example.baselib.enums.MsgFrom
import kotlinx.android.parcel.Parcelize

@Parcelize
class MsgSystemDetailVO(
    var from: MsgFrom,
    var title: String,
    var content: String,
    var sender: String,
    var senderIcon: Int,
    var operator: String,
    var receiver: String,
    var date: String,
    var msgType: String,
    var url: String
) : Parcelable