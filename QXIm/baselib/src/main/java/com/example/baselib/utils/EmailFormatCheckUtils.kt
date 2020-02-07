package com.example.baselib.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author: chenjiayou
 * @createBy: 2020/1/9
 * @description: 邮箱检测工具类
 */
class EmailFormatCheckUtils {
    companion object {

        /**
         * 判断邮箱是否合法
         * @param email
         * @return
         */
        fun isEmailLegal(email: String?): Boolean {
            if (null == email || "" == email) return false
            //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
            val p: Pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") //复杂匹配
            val m: Matcher = p.matcher(email)
            return m.matches()
        }
    }
}