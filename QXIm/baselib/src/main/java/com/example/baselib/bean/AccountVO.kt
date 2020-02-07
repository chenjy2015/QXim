package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author: chenjy
 * @createBy: 2019/11/22
 * @descript: 账号信息 用于登录时存储的用户名和密码等信息
 */
@Parcelize
class AccountVO(val name: String, val password: String, val userType: Int, val company: String) : Parcelable