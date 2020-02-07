package com.example.baselib.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.text.DateFormat

class JsonManager {
    companion object {

        fun create(): Gson {
            return GsonBuilder()
                .registerTypeAdapter(Int::class.javaPrimitiveType, GsonIntegerDefaultAdapter())
                .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerTypeAdapter())
//                .registerTypeAdapter(String::class.java, StringTypeAdapter()) //TODO 此adapter注册后 解析json数据异常 原因待查
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
//            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写
                .setPrettyPrinting()
                .setVersion(1.0)
                .create()
        }

        inline fun <reified T> toBean(json: String): T {
            val clazz = T::class.java
            return create().fromJson(json, clazz)
        }

        inline fun <reified T> toJson(t: T): String {
            return create().toJson(t)
        }

        inline fun <reified T> toBeanList(json: String): List<T> {
            return create().fromJson<List<T>>(json, ParameterizedTypeImpl(T::class.java))
        }

        inline fun <reified T> listToJson(list: List<T>): String {
            return create().toJson(list)
        }
    }
}