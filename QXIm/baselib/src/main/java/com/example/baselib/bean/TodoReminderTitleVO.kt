package com.example.baselib.bean

import android.os.Parcelable
import com.example.baselib.enums.TodoMode
import kotlinx.android.parcel.Parcelize

@Parcelize
class TodoReminderTitleVO(var serviceId:String, var mode: TodoMode, var icon: Int, var logUrl:String?, var title: String, var total:Int) : Parcelable