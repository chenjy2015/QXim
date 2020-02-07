package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 要开通应用的信息
 *
 */
@Parcelize
class WaitForOpenAppVO(
    val affiliatedDeveloperUserName: String,//所属开发者userName
    val serviceDesc: String,//应用描述
    val serviceLogoUrl: String,//应用logo的地址
    val serviceName: String,//应用名称
    val verInfoVos: List<VerInfoVo>//应用版本集合
) : Parcelable {
    /**
     * 应用版本
     */
    @Parcelize
    class VerInfoVo(
        val verDesc: String, //版本描述
        val verId: Long, //版本ID
        val verName: String, //版本名称
        val verPriceInfoVos: List<VerPriceInfoVo>, //版本价格集合
        var checked: Boolean //TODO 自定义字段表示当前item是否选中
    ) : Parcelable

    /**
     * 开通时长(版本价格)
     */
    @Parcelize
    class VerPriceInfoVo(
        val openDuration: Int,//允许开通时长(1 7天，2 一个月，3 半年，4 一年，5 三年，6 五年)
        val openDurationDesc: String,//时长描述信息，7天，一个月， 半年，一年，三年，五年
        val verPrice: String,//版本价格
        var checked: Boolean //TODO 自定义字段表示当前item是否选中
    ) : Parcelable
}




