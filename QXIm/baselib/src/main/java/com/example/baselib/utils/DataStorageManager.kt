package com.example.baselib.utils

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.example.baselib.gson.JsonManager

/**
 * @author: chenjy
 * @createBy: 2019/11/22
 * @descript: 数据管理
 */
class DataStorageManager {

    companion object {
        inline fun <reified T : Any> get(key: String): T? {
            try {
                val userStr = SPUtils.getInstance().getString(key)
                if (!userStr.isNullOrEmpty()) {
                    return JsonManager.toBean<T>(userStr)
                }
            } catch (e: Exception) {
                LogUtils.e(e)
            }
            return null
        }

        inline fun <reified T : Any> set(user: T, key: String): Boolean {
            try {
                SPUtils.getInstance().put(key, JsonManager.toJson(user))
            } catch (e: Exception) {
                LogUtils.e(e)
                return false
            }
            return true
        }

        fun clear(key: String): Boolean {
            try {
                SPUtils.getInstance().put(key, "")
            } catch (e: Exception) {
                LogUtils.e(e)
                return false
            }
            return true
        }
    }
}