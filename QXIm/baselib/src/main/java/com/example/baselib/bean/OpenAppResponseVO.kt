package com.example.baselib.bean

data class OpenAppResponseVO(
    val accountId: String,
    val accountType: Int,
    val companyId: Int,
    val companyName: String,
    val cost: String,
    val createdBy: String,
    val createdTime: String,
    val createdUserId: Int,
    val openDuration: Int,
    val openStatus: Int,
    val openingTime: String,
    val personId: Int,
    val recordId: Int,
    val serviceCode: String,
    val serviceId: Int,
    val serviceName: String,
    val serviceOpenMode: Int,
    val updatedBy: String,
    val updatedTime: String,
    val updatedUserId: Int,
    val useDeadline: String,
    val useStatus: Int,
    val verId: Int
)