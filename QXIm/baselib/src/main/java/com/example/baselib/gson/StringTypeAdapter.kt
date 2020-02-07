package com.example.baselib.gson

import com.blankj.utilcode.util.LogUtils
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

/**
 * @author: chenjiayou
 * @createBy: 2019/11/20
 * @descript: 自定义解析适配器 若返回json的String为null 转为空字符串“”
 */
class StringTypeAdapter : TypeAdapter<String>() {
    override fun write(writer: JsonWriter?, value: String?) {
        try {
            if (value == null) {
                writer?.nullValue()
                return
            }
            writer?.value(value)
        } catch (e: Exception) {
            LogUtils.e(e)
        }
    }

    override fun read(reader: JsonReader?): String {
        try {
            if (reader?.peek() == JsonToken.NULL) {
                reader.nextNull()
                return ""
            }
            reader?.nextString()
        } catch (e: Exception) {
            LogUtils.e(e)
        }
        return ""
    }

}