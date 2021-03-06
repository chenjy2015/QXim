package com.example.baselib.constants

class Constant {
    companion object {
        const val OVERRIDE_TRANSITION_ORIENTATION = "orientation" // 界面切换动画key
        const val USER_INFO = "user_info" //存储用户信息的key
        const val ACCOUNT_INFO = "account_info" //存储登录时所需要的账号信息
        const val JPUSH_REGISTRATION_ID = "jpush_registration_id"//极光推送registration id
        const val MAIN_ID = "main_id"//全局变量 主体ID

        /**
         * 3 / 004-001 / 领筑组织中心
         * 10 / 004-004 / 领筑企业控制台
         * 11 / 004-003 / 领筑个人控制台
         * 12 / 004-005 / 领筑账号中心
         * 13 / 004-006 / 领筑用户中心
         * 14 / 004-007 / 领筑消息中心
         * 15 / 004-008 / 领筑文件中心
         * 16 / 004-009 / 领筑交易中心
         * 17 / 004-010 / 领筑客户中心
         * 18 / 004-011 / 领筑产品中心
         * 19 / 004-012 / 领筑合作中心
         * 21 / 004-013 / 领筑应用中心
         * 22 / 004-014 / 领筑服务中心
         * 23 / 004-015 / 领筑项目中心
         * 24 / 004-016 / 领筑认证服务系统
         * 26 / 004-018 / 领筑安全中心
         * 27 / 004-019 / 领筑组织管理系统
         * 28 / 004-020 / 领筑待办提醒系统
         */
        val names = arrayOf(
            "领筑组织中心", "领筑企业控制台", "领筑个人控制台", "领筑账号中心", "领筑用户中心"
            , "领筑消息中心", "领筑文件中心", "领筑交易中心", "领筑客户中心", "领筑产品中心"
            , "领筑合作中心", "领筑应用中心", "领筑服务中心", "领筑项目中心", "领筑认证服务系统"
            , "领筑安全中心", "领筑组织管理系统", "领筑待办提醒系统"
        )

        val codes = arrayOf(
            "004-001", "004-004", "004-003", "004-005", "004-006"
            , "004-007", "004-008", "004-009", "004-010", "004-011"
            , "004-012", "004-013", "004-014", "004-015", "004-016"
            , "004-018", "004-019", "004-020"
        )

        val ids = arrayOf(
            "3", "10", "11", "12", "13",
            "14", "15", "16", "17", "18",
            "19", "21", "22", "23", "24",
            "26", "27", "28"
        )

        /**
         * 请求组织主体列表参数platformCode值
         */
        val organization_codes = arrayOf(
            "ZZZX", // 组织中心
            "QYKZT",// 企业控制台
            "GRKZT",//个人控制台
            "ZHZX",// 账号中心
            "YHZX",//用户中心
            "XXZX",  //  消息中心
            "WJZX", // 文件中心
            "JYZX", // 交易中心
            "KHZX",       //  客户中心
            "CPZX", // 产品中心
            "HZZX",// 合作中心
            "YYZX",// 应用中心
            "FWZX",// 服务中心
            "XMZX", // 项目中心
            "RZFWXT",//认证服务系统
            "AQZX",// 安全中心
            "ZZGLXT",//组织管理系统
            "DBTXXT" //待办中心
        )
    }
}