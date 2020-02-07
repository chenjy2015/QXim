package com.example.baselib.bean

import android.os.Parcelable
import com.example.baselib.enums.TodoMode
import kotlinx.android.parcel.Parcelize

/**
 * 组织管理系统待办事项
 */
@Parcelize
class TodoReminderSystemVO(
    var mode: TodoMode, //数据源类型 详见：TodoMode.ORGANIZATION_MANAGER_CENTER
    var icon: Int,
    var title: String,
    var total: Int,
    var msg: String,
    var unReadCount: Int
) : Parcelable