package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class OpenAppVO() : Parcelable {
    var accountId: String = ""
    var accountType: Int = 0
    var affiliatedDeveloperUserId: Long = 0
    var affiliatedDeveloperUserName: String = ""
    var companyId: Long = 0
    var companyName: String = ""
    var cost: String = ""
    var createdBy: String = ""
    var createdUserId: Long = 0
    var id: Long = 0
    var openStatus: Int = 0
    var openingTime: String = ""
    var personId: Long = 0
    var serviceCode: String = ""
    var serviceDesc: String = ""
    var serviceHomeUrl: String = ""
    var serviceId: Long = 0
    var serviceLogoUrl: String = ""
    var serviceName: String = ""
    var serviceOpenMode: Int = 0
    var serviceUseUrl: String = ""
    var useDeadline: String = ""
    var useStatus: Int = 0
    var userId: Long = 0
    var verId: Long = 0
    var verName: String = ""

    constructor(
        accountId: String,
        accountType: Int,
        affiliatedDeveloperUserId: Long,
        affiliatedDeveloperUserName: String,
        companyId: Long,
        companyName: String,
        cost: String,
        createdBy: String,
        createdUserId: Long,
        id: Long,
        openStatus: Int,
        openingTime: String,
        personId: Long,
        serviceCode: String,
        serviceDesc: String,
        serviceHomeUrl: String,
        serviceId: Long,
        serviceLogoUrl: String,
        serviceName: String,
        serviceOpenMode: Int,
        serviceUseUrl: String,
        useDeadline: String,
        useStatus: Int,
        userId: Long,
        verId: Long,
        verName: String
    ) : this() {
        this.accountId = accountId
        this.accountType = accountType
        this.affiliatedDeveloperUserId = affiliatedDeveloperUserId
        this.affiliatedDeveloperUserName = affiliatedDeveloperUserName
        this.companyId = companyId
        this.companyName = companyName
        this.cost = cost
        this.createdBy = createdBy
        this.createdUserId = createdUserId
        this.id = id
        this.openStatus = openStatus
        this.openingTime = openingTime
        this.personId = personId
        this.serviceCode = serviceCode
        this.serviceDesc = serviceDesc
        this.serviceHomeUrl = serviceHomeUrl
        this.serviceId = serviceId
        this.serviceLogoUrl = serviceLogoUrl
        this.serviceName = serviceName
        this.serviceOpenMode = serviceOpenMode
        this.serviceUseUrl = serviceUseUrl
        this.useDeadline = useDeadline
        this.useStatus = useStatus
        this.userId = userId
        this.verId = verId
        this.verName = verName
    }

}