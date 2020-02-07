package com.example.baselib.i

import com.example.baselib.bean.UnReadTotalCountVO
import com.example.baselib.bean.UserInfo

/**
 * 主页数据操作服务
 */
interface HomeDataService {

    fun getUserInfo(): UserInfo?

    // 进入控制台
    fun enterConsole()

    // 当前账号不可用状态下的提示
    fun showAccountUnavailableStatusHint()

    // 判断当前账号状态是否可用
    fun getAccountAvailabilityStatus(): Boolean

    // 设置首页标题栏 未读数合集
    fun setUnReadTotalCount(todoTotalCountVO: UnReadTotalCountVO)
}