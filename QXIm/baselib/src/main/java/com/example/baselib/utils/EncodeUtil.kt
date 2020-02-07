package com.example.baselib.utils

import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class EncodeUtil {

    companion object {

        /**
         * 字符串转换unicode
         */
        fun stringToUnicode(string: String): String? {
            val unicode = StringBuffer()
            for (element in string) { // 取出每一个字符
                val c = element
                // 转换为unicode
                unicode.append("\\u" + Integer.toHexString(c.toInt()))
            }
            return unicode.toString()
        }

        /**
         * 字符串换成UTF-8
         *
         * @param str
         * @return
         */
        fun stringToUtf8(str: String?): String? {
            var result: String? = null
            try {
                result = URLEncoder.encode(str, "UTF-8")
            } catch (e: UnsupportedEncodingException) { // TODO Auto-generated catch block
                e.printStackTrace()
            }
            return result
        }

    }
}