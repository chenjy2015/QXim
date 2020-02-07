package com.example.baselib.i

import com.example.baselib.bean.MsgCenterAllVO

interface SimpleMsgCenterCallBack : MsgCenterCallBack {
    override fun setReceiveAllMsg(data: MutableList<MsgCenterAllVO>) {
    }

    override fun setSentAllMsg(data: MutableList<MsgCenterAllVO>) {

    }
}