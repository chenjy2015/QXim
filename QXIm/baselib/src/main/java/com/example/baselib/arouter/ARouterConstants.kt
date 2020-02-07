package com.example.baselib.arouter

import com.example.baselib.arouter.ARouterConstants.group.GROUP_APP
import com.example.baselib.arouter.ARouterConstants.group.GROUP_IM
import com.example.baselib.arouter.ARouterConstants.group.GROUP_LOGIN


class ARouterConstants {

    /**
     * 每个module都要统一用一个group 不同的module不能重复
     * 否则会报错：Program type already present: com.alibaba.android.arouter.routes.ARouter$$Group$$m123$1
     * 如果我们要配置模块 B 中的类，就不能使用 A 来作为组的路径了，要不然就会报错。
     * 在不同的模块中，配置的组路径不能一样，而在同一个模块中，自己的路径不能相同，就是上面 A1 位置不能相同。 
     */
    object group {
        const val GROUP_APP = "app"
        const val GROUP_LOGIN = "login"
        const val GROUP_IM = "im"
    }

    object router_path_service {
        const val SERVICE_LOGIN = "/service/doLogin"
    }

    object router_path_activity {

        //app
        const val ACTIVITY_PATH_DEMO = "/$GROUP_APP/activity/demo"
        const val ACTIVITY_PATH_GUIDE = "/$GROUP_APP/activity/guide"
        const val ACTIVITY_PATH_SPLASH = "/$GROUP_APP/activity/splash"
        const val ACTIVITY_PATH_HOME = "/$GROUP_APP/activity/home"

        //home
        const val ACTIVITY_PATH_BROWSER = "/$GROUP_LOGIN/activity/browser"
        const val ACTIVITY_PATH_NEWS = "/$GROUP_LOGIN/activity/news"
        const val ACTIVITY_PATH_SYSTEM_SUBJECT_SELECT = "/$GROUP_LOGIN/activity/subject"
        const val ACTIVITY_PATH_ORGANIZATION_SELECT = "/$GROUP_LOGIN/activity/organization"
        const val ACTIVITY_PATH_LOGIN = "/$GROUP_LOGIN/activity/doLogin"
        const val ACTIVITY_PATH_ACTIVATION = "/$GROUP_LOGIN/activity/activation"
        const val ACTIVITY_PATH_PROJECT_CENTER = "/$GROUP_LOGIN/activity/project_center"
        const val ACTIVITY_REGISTER_AGREEMENT = "/$GROUP_LOGIN/activity/register_agreement"
        const val ACTIVITY_REGISTER_ACCOUNT_TYPE = "/$GROUP_LOGIN/activity/register_account_type"
        const val ACTIVITY_REGISTER_ACCOUNT_INFO = "/$GROUP_LOGIN/activity/register_info"
        const val ACTIVITY_REGISTER_SUCCESS = "/$GROUP_LOGIN/activity/register_success"
        const val ACTIVITY_NOT_ACTIVE = "/$GROUP_LOGIN/activity/not_active"
        const val ACTIVITY_FIND_PASSWORD = "/$GROUP_LOGIN/activity/find_password"
        const val ACTIVITY_AUTHENTICATION_SELECTION =
            "/$GROUP_LOGIN/activity/authentication_selection"
        const val ACTIVITY_AUTHENTICATION_EMAIL = "/$GROUP_LOGIN/activity/authentication_email"
        const val ACTIVITY_AUTHENTICATION_PHONE = "/$GROUP_LOGIN/activity/authentication_phone"
        const val ACTIVITY_SET_PASSWORD = "/$GROUP_LOGIN/activity/set_password"
        const val ACTIVITY_SET_PASSWORD_SUCCESS = "/$GROUP_LOGIN/activity/set_password_success"
        const val ACTIVITY_BIND_EMAIL = "/$GROUP_LOGIN/activity/bind_email"
        const val ACTIVITY_BIND_EMAIL_SUCCESS = "/$GROUP_LOGIN/activity/bind_email_success"

        //im
        const val ACTIVITY_PATH_CHAT = "/$GROUP_IM/activity/chat"


    }

    object router_path_fragment {
        //home
        const val FRAGMENT_PATH_APP = "/$GROUP_LOGIN/app"
        const val FRAGMENT_PATH_PROJECT = "/$GROUP_LOGIN/project"
        const val FRAGMENT_PATH_SETTING = "/$GROUP_LOGIN/setting"
        const val FRAGMENT_PATH_BROWSER = "/$GROUP_LOGIN/browser"
        const val FRAGMENT_PATH_NEWS = "/$GROUP_LOGIN/news"
        const val FRAGMENT_PATH_DIALOG = "/$GROUP_LOGIN/dialog"
        const val FRAGMENT_PATH_DIALOG_OPEN = "/$GROUP_LOGIN/open"
        const val FRAGMENT_TODO_REMINDER = "/$GROUP_LOGIN/todo/reminder"

        const val FRAGMENT_PATH_WORKBENCH = "/$GROUP_LOGIN/workbench"
        const val FRAGMENT_PATH_ENTERPRISE_WORKBENCH = "/$GROUP_LOGIN/enterprise_workbench"
        const val FRAGMENT_PATH_WORKBENCH_NEXT_LEVEL = "/$GROUP_LOGIN/workbench_next_level"
        const val FRAGMENT_PATH_ENTERPRISE_WORKBENCH_NATIVE_TOP =
            "/$GROUP_LOGIN/enterprise_workbench_native_top"
        const val FRAGMENT_PATH_ENTERPRISE_WORKBENCH_NEXT_LEVEL =
            "/$GROUP_LOGIN/enterprise_workbench_next_level"
        const val FRAGMENT_PATH_SYSTEM_SUBJECT_SELECT = "/$GROUP_LOGIN/systemSubjectSelect"
        const val FRAGMENT_APP_MANAGER_CENTER = "/$GROUP_LOGIN/app_manager_center"
        const val FRAGMENT_APP_MANAGER_NEXT_LEVEL = "/$GROUP_LOGIN/app_manager_next_level"
        const val FRAGMENT_APP_MANAGER_DETAILS = "/$GROUP_LOGIN/app_manager_details"
        const val FRAGMENT_ACCOUNT_CENTER = "/$GROUP_LOGIN/account_center"
        const val FRAGMENT_ACCOUNT_DETAIL = "/$GROUP_LOGIN/account_detail"
        const val FRAGMENT_ACCOUNT_SECURITY = "/$GROUP_LOGIN/account_security"
        const val FRAGMENT_AUTHENTICATION_SELECTION = "/$GROUP_LOGIN/authentication_selection"
        const val FRAGMENT_AUTHENTICATION_BY_EMAIL = "/$GROUP_LOGIN/authentication_by_email"
        const val FRAGMENT_AUTHENTICATION_BY_PHONE = "/$GROUP_LOGIN/authentication_by_phone"
        const val FRAGMENT_BIND_EMAIL = "/$GROUP_LOGIN/bind_email"
        const val FRAGMENT_BIND_EMAIL_SUCCESS = "/$GROUP_LOGIN/bind_email_success"
        const val FRAGMENT_BIND_PHONE = "/$GROUP_LOGIN/bind_phone"
        const val FRAGMENT_BIND_PHONE_SUCCESS = "/$GROUP_LOGIN/bind_phone_success"
        const val FRAGMENT_CHANGE_EMAIL = "/$GROUP_LOGIN/change/email"
        const val FRAGMENT_CHANGE_EMAIL_SUCCESS = "/$GROUP_LOGIN/change/email_success"
        const val FRAGMENT_CHANGE_PHONE = "/$GROUP_LOGIN/change/phone"
        const val FRAGMENT_CHANGE_PHONE_SUCCESS = "/$GROUP_LOGIN/change/phone_success"


        const val FRAGMENT_SET_NEW_PASSWORD = "/$GROUP_LOGIN/set_new_password"
        const val FRAGMENT_SET_NEW_PASSWORD_SUCCESS = "/$GROUP_LOGIN/set_new_password_success"

        const val FRAGMENT_MESSAGE_CENTER = "/$GROUP_LOGIN/msg_center"
        const val FRAGMENT_MESSAGE_SECOND = "/$GROUP_LOGIN/msg_second"
        const val FRAGMENT_MESSAGE_DETAIL = "/$GROUP_LOGIN/msg_detail"

        const val FRAGMENT_ENTERPRISE_ACCOUNT_ACTIVATION = "/$GROUP_LOGIN/activation/enterprise"
        const val FRAGMENT_PERSONAL_ACCOUNT_ACTIVATION = "/$GROUP_LOGIN/activation/personal"

        const val FRAGMENT_CHAT_MORE = "/$GROUP_IM/fragment/chat/more"
        const val FRAGMENT_CHAT_MORE_MENU = "/$GROUP_IM/fragment/chat/more/menu"

    }

    object intent_key {
        const val PROJECT_KEY = "project"
        const val PROJECT_NAME_KEY = "name"
        const val BROWSER_URL_KEY = "url"
        const val REGISTER_AGREEMENT_TYPE = "agreement_type"
        const val PROJECTS = "projects" //project列表传值
        const val BROWSER_BEAN = "browser_bean"
        const val WAIT_OPEN_BACKGROUND = "wait_open_bg" //未开通页背景
        const val REGISTER_INFO = "register_info" //注册信息传递
        const val ACCOUNT = "account" //账号
        const val SYS_MAIN_BODY_ID = "sys_main_body_id" //主体ID
        const val USER_INFO = "user_info" //用户信息
        const val VERIFY_MODE = "verify_mode" //验证类型
        const val VERIFY_CODE = "verify_code"
        const val OPERATOR_TYPE = "operator_type" //账号安全操作类型
        const val EMAIL = "email" //邮箱地址
        const val MOBILE = "mobile" //电话号码
        const val MSG_CENTER_BEAN = "msg_center_bean" //消息中心bean对象
        const val MSG_SYSTEM = "msg_system" //消息中心 - 系统消息
        const val OPEN_APP = "open_app" //已开通的应用列表bean
        const val WAIT_FOR_OPEN_APP = "WaitForOpenAppVO"// 待开通应用bean
        const val APP_SERVICE_ID = "app_service_id"// 待开通应用id
        const val INDEX = "index"// 若当前fragment中有两个item 则代表要进入的某个item索引位置
        const val MSG_FROM = "MsgFrom" // 消息来源
        const val MSG_VO = "msg_vo" // 消息列表对象
        const val ORGANIZATION_CODE = "organization_code" //请求我加入的组织主体code
        const val CHAT_MORE_MENU = "chat_more_menu" //聊天界面 更多菜单
    }


    /**
     *
     * description: 跳转拦截器 优先级 因为不能重复设置 统一在此设定 便于管理
     *
     */
    object router_intercept_priority {
        const val LOGIN_INTERCEPT_PRIORITY = 5 //跳转登录界面拦截器优先级
    }

}