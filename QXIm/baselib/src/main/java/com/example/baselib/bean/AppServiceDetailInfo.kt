package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 应用模块请求信息
 * @param id 应用ID 用于 【查询该应用是否已开通】 接口
 * @param serviceCode 应用code 用于 【根据应用Code查询服务详情】 接口
 * @param serviceName 应用名称 如：领筑组织中心，领筑安全中心等。
 */
@Parcelize
class AppServiceDetailInfo():Parcelable {
    var serviceId: Long = 0
    var serviceAgentNumber: String = ""
    var serviceCode: String = ""
    var serviceDesc: String = ""
    var serviceLogoUrl: String = ""
    var serviceName: String = ""
    var serviceSlogan: String = ""
    var waitForOpenBg:Int = 0

    constructor(serviceId: Long, serviceCode: String, serviceName: String, waitForOpenBg: Int) : this() {
        this.serviceId = serviceId
        this.serviceCode = serviceCode
        this.serviceName = serviceName
        this.waitForOpenBg = waitForOpenBg
    }

    constructor(
        serviceId: Long,
        serviceAgentNumber: String,
        serviceCode: String,
        serviceDesc: String,
        serviceLogoUrl: String,
        serviceName: String,
        serviceSlogan: String
    ) : this() {
        this.serviceId = serviceId
        this.serviceAgentNumber = serviceAgentNumber
        this.serviceCode = serviceCode
        this.serviceDesc = serviceDesc
        this.serviceLogoUrl = serviceLogoUrl
        this.serviceName = serviceName
        this.serviceSlogan = serviceSlogan
    }
}