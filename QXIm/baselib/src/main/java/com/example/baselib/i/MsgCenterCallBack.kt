package com.example.baselib.i

import com.example.baselib.bean.MsgCenterAllVO
import com.example.baselib.bean.MsgResponseVO
import com.example.baselib.bean.MsgSettingMenuVO
import com.example.baselib.bean.Record

interface MsgCenterCallBack {
    /**
     * 消息中心 发送的全部消息
     */
    fun setSentAllMsg(data: MutableList<MsgCenterAllVO>)

    /**
     * 消息中心 - 接收的全部消息
     */
    fun setReceiveAllMsg(data: MutableList<MsgCenterAllVO>)

    /**
     * 消息中心 - 系统/运营/企业消息一级列表
     */
    fun setMsgList(data: MutableList<Record>?)

}