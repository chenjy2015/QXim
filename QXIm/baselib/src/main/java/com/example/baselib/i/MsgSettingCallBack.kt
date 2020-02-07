package com.example.baselib.i

import com.example.baselib.bean.MsgSettingMenuVO
import com.example.baselib.bean.Record

interface MsgSettingCallBack {
    fun setMsgSettingMenus(menus: List<MsgSettingMenuVO>)
    fun setMsgSettingList(records: List<Record>)
    fun failed(e:Exception)
}