package com.example.baselib.bean

class OrganizationVO(
    val companyId: Long,
    val email: String,
    val headPath: String,
    val mobile: String,
    val orgType: Int,
    val organizationId: Long,
    val projectId: Long,
    val realAuthStatus: Int,
    val realUserName: String,
    val sysMainBodyId: Long,
    val sysMainBodyUserStatus: Int,
    val userAuthStatus: Int,
    val userDetailId: Long,
    val userName: String,
    val userType: Int,
    var selected: Boolean //自定义字段标示是否选中状态
)