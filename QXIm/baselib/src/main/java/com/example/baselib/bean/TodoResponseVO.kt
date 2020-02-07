package com.example.baselib.bean

/**
 * 待办事项列表 包含企业控制台与个人控制台时请求
 */
data class TodoResponseVO(
    val receivedSum: Int, //组织中心-收到的邀请数
    val sum: Int, //今日待办总数
    val waitDoStatisticVos: List<WaitDoStatisticVo>, //组织管理系统
    val coopApplyWorkbenchVo: CoopApplyWorkbenchVo,//合作中心待办
    val serviceAuditNumVo: ServiceAuditNumVo,// 应用中心待办
    val serviceUnfinishedVo: ServiceUnfinishedVo, //服务中心待办
    val upcomingAuthenticationTypeVo: UpcomingAuthenticationTypeVo //认证中心待办
)

/**
 * 合作中心待办
 */
data class CoopApplyWorkbenchVo(
    val ambassadorApplyCount: Int,//领筑大使申请审核
    val coopApplyCount: Int//运营商申请审核
)

/**
 * 应用中心待办
 */
data class ServiceAuditNumVo(
    val authAuditNum: Int,//功能审核数量
    val baseAuditNum: Int,//应用审核数量
    val joinAuditNum: Int,//接入审核数量
    val verAuditNum: Int//版本审核数量
)

/**
 *服务中心待办
 */
data class ServiceUnfinishedVo(
    val companyApplyNum: Int,//服务商审核
    val personApplyNum: Int//服务提供者审核
)

/**
 *认证中心待办
 */
data class UpcomingAuthenticationTypeVo(
    val registrationOfPracticeNumber: Int,//执业注册类型
    val typeOfEnterpriseNumber: Int//企业类型
)

/**
 *组织管理待办
 */
data class WaitDoStatisticVo(
    val companySubApplyCount: Int, //企业关联申请数量
    val empApplyNum: Int, //收到的授权申请
    val joinApplyNum: Int, //合伙人审核/联营角色
    val logoPath: String, //
    val orgName: String, //组织名称
    val organizationId: Int //组织ID
)
