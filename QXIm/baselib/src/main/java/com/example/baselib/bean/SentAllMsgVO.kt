package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SentAllMsgVO(
    val messageCompanyNum: Int,
    val messageOperateNum: Int,
    val messageSysNum: Int
) : Parcelable {
    override fun toString(): String {
        return "MsgCenterUnReadVO(messageCompanyNum=$messageCompanyNum, messageOperateNum=$messageOperateNum, messageSysNum=$messageSysNum)"
    }
}