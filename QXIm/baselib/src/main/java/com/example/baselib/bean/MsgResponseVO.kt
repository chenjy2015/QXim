package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MsgResponseVO(
    val current: Int,
    val pages: Int,
    val records: List<Record>,
    val size: Int,
    val total: Int
)

/**
 * 此对象包含发送消息，接受消息，消息管理三种类型数据的结合 部分字段不同大部分相同
 */
@Parcelize
data class Record(
    val id: Int,//主键
    val name: String, //名称
    val sendIcon: String,//发送方logo
    val sendId: Int,//发送方ID
    val sendName: String,//发送方名称
    val sendTime: String,//发送时间
    val status: Int,//状态 0 待发送 1 已发送
    val title: String, //消息标题
    val msgTypeName: String, //消息类型名称
    val createdBy: String, //操作人名称
    val recevingTypeNmaes: String, //接收方范围
    val resourceType: Int //消息归属类别 1 系统/应用 2 运营商/产品 3 企业

) : Parcelable