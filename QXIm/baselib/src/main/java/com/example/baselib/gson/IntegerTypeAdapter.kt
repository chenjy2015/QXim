package com.example.baselib.gson

import com.blankj.utilcode.util.LogUtils
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.NumberFormatException

/**
 * @author: chenjiayou
 * @createBy: 2019/11/20
 * @descript: 自定义解析适配器 若返回json的Int类型为Float或者Double 强制转换为Int 避免解析失败报错
 */
class IntegerTypeAdapter : TypeAdapter<Int>() {

    override fun write(writer: JsonWriter?, value: Int?) {
        writer?.value(value)
    }

    override fun read(reader: JsonReader?): Int {
        if (reader?.peek() == JsonToken.NULL) {
            reader.nextNull()
            return 0
        }
        try {
            val i = reader?.nextDouble()
            return i!!.toInt()//强制转为Int类型 向下兼容 避免原定义int类型的值 json中为float时解析错误
        } catch (e: NumberFormatException) {
            LogUtils.e(e)
            throw JsonSyntaxException(e)
        }
    }

}