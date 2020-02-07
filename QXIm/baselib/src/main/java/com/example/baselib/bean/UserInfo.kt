package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author: chenjy
 * @createBy: 2019/11/22
 * @description: 用户具体信息
 */
@Parcelize
class UserInfo(
    var account: String,
    var password:String,
    var headUrl: String,
    var token: String
) : Parcelable

