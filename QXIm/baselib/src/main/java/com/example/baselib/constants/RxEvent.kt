package com.example.baselib.constants

class RxEvent {
    companion object {
        const val SYSTEM_SUBJECT_SELECTED: String = "system_subject_selected" // 系统主体切换选中
        const val ENTER_NEXT_LEVEL: String = "enter_next_level" //进入下一级页面
        const val ENTER_NEXT_ENTERPRISE_LEVEL: String = "enter_next_enterprise_level" // 控制台进入下一级页面
        const val LOGIN_NOT_AVAILABLE: String = "login_not_available" //登录失效 需要重新登录
        const val ACCOUNT_HEADER_CROP = "account_header_crop"//账号头像裁剪
        const val UPDATE_USER_INFO = "update_user_info" //更新用户信息
        const val COLOR_INDEX = "color_index" //引导页切换索引与颜色值变化
    }
}